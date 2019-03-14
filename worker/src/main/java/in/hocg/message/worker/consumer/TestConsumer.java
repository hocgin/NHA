package in.hocg.message.worker.consumer;

import in.hocg.message.core.constant.MessageConstant;
import in.hocg.message.core.constant.MessageHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author hocgin
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = MessageConstant.TOPIC, consumerGroup = MessageConstant.CONSUMER_GROUP)
public class TestConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        Optional<String> algorithmKey = Optional.ofNullable(message.getUserProperty("USERS_" + MessageHeader.ALGORITHM));
        Optional<String> moduleKey = Optional.ofNullable(message.getUserProperty("USERS_" + MessageHeader.MODULE));
        Optional<String> commandKey = Optional.ofNullable(message.getUserProperty("USERS_" + MessageHeader.COMMAND));
        if (!algorithmKey.isPresent() || !moduleKey.isPresent() || !commandKey.isPresent()) {
            // error package
            log.error("消息包结构错误，请仔细进行检查");
            return;
        }
        byte algorithm = Byte.valueOf(algorithmKey.get());
        byte module = Byte.valueOf(moduleKey.get());
        byte commend = Byte.valueOf(commandKey.get());
        byte[] body = message.getBody();
        log.debug("接收到消息: {}, 算法: {}, 模块: {}, 指令: {}", body, algorithm, module, commend);
//        Optional<Serializer> serializerOptional = SerializerAlgorithm.getSerializer(algorithm);
//        if (!serializerOptional.isPresent()) {
//            return;
//        }
    
    }
}
