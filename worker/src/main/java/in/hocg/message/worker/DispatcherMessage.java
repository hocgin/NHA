package in.hocg.message.worker;

import com.alibaba.fastjson.JSON;
import in.hocg.message.core.constant.MessageConstant;
import in.hocg.message.core.constant.MessageHeader;
import in.hocg.message.core.ioc.Invoker;
import in.hocg.message.core.ioc.InvokerManager;
import in.hocg.message.core.serializer.Serializer;
import in.hocg.message.core.serializer.SerializerAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * 消息分发器
 * @author hocgin
 */
@Slf4j
@Service
@AllArgsConstructor
@RocketMQMessageListener(topic = MessageConstant.WORKER_TOPIC, consumerGroup = MessageConstant.WORKER_CONSUMER_GROUP)
public class DispatcherMessage implements RocketMQListener<MessageExt> {
    
    @Override
    public void onMessage(MessageExt messageExt) {
        Optional<String> algorithmKey = Optional.ofNullable(messageExt.getUserProperty("USERS_" + MessageHeader.ALGORITHM));
        Optional<String> moduleKey = Optional.ofNullable(messageExt.getUserProperty("USERS_" + MessageHeader.MODULE));
        Optional<String> commandKey = Optional.ofNullable(messageExt.getUserProperty("USERS_" + MessageHeader.COMMAND));
        if (!algorithmKey.isPresent() || !moduleKey.isPresent() || !commandKey.isPresent()) {
            // error package
            log.error("消息包结构错误，请仔细进行检查");
            return;
        }
        byte algorithm = Byte.valueOf(algorithmKey.get());
        byte module = Byte.valueOf(moduleKey.get());
        byte commend = Byte.valueOf(commandKey.get());
    
        String bodyStr = new String(messageExt.getBody(), Charset.forName("UTF-8"));
        byte[] body = JSON.parseObject(bodyStr, byte[].class);
    
        log.debug("接收到消息: {} \n 消息String: {} \n 算法: {} \n 模块: {} \n 指令: {}", body, new String(body), algorithm, module, commend);
        Optional<Serializer> serializerOptional = SerializerAlgorithm.getSerializer(algorithm);
        if (!serializerOptional.isPresent()) {
            return;
        }
        String source = messageExt.getUserProperty("USERS_" + MessageHeader.SOURCE);
        Optional<Invoker> invokerOptional = InvokerManager.getInvoker(module, commend);
    
        // 根据函数接收的类型进行解析
        if (invokerOptional.isPresent()) {
            Invoker invoker = invokerOptional.get();
            Method method = invoker.getMethod();
            if (method.getParameterCount() != 2) {
                throw new UnsupportedOperationException("指令函数接收参数有误");
            }
            Class<?> parameterType = method
                    .getParameterTypes()[1];
            Object request = serializerOptional.get()
                    .deserialize(parameterType, body);
            invoker.invoke(source, request);
        }
        
    }
}
