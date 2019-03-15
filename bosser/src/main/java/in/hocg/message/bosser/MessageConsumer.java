package in.hocg.message.bosser;

import com.alibaba.fastjson.JSON;
import in.hocg.message.bosser.netty.session.SessionManager;
import in.hocg.message.core.constant.MessageConstant;
import in.hocg.message.core.constant.MessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * @author hocgin
 */
@Slf4j
@Service
@AllArgsConstructor
@RocketMQMessageListener(topic = MessageConstant.BOSSER_TOPIC, consumerGroup = MessageConstant.BOSSER_CONSUMER_GROUP)
public class MessageConsumer implements RocketMQListener<MessageExt> {
    
    @Override
    public void onMessage(MessageExt messageExt) {
        String destination = messageExt.getUserProperty("USERS_" + MessageHeader.DESTINATION);
        String bodyStr = new String(messageExt.getBody(), Charset.forName("UTF-8"));
        Channel channel = SessionManager.get(destination);
        if (channel == null) {
            log.debug("查找不到用户 {} \n 消息内容: {}", destination, bodyStr);
            return;
        }
        byte[] body = JSON.parseObject(bodyStr, byte[].class);
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byteBuf.writeBytes(body);
        channel.writeAndFlush(byteBuf).addListener(future -> log.debug("Listener:: {}", future));
        log.debug("\n -> 消息体: {} \n -> 接收者: {}", body, destination);
    }
}
