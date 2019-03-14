package in.hocg.message.worker.consumer;

import in.hocg.message.worker.constant.MQConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author hocgin
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = MQConstant.Topic.TEST_TOPIC, consumerGroup = MQConstant.Group.TEST_CONSUMER)
public class TestConsumer implements RocketMQListener<byte[]> {
    @Override
    public void onMessage(byte[] data) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byteBuf.writeBytes(data);
        log.debug("接收到消息: {}", data);
    }
}
