package in.hocg.message.bosser.netty.handler;

import in.hocg.message.bosser.netty.support.ModuleContainerConfiguration;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author hocgin
 */
@Slf4j
@ChannelHandler.Sharable
public class ForwardMQHandler extends SimpleChannelInboundHandler<ByteBuf> {
    public static final ForwardMQHandler INSTANCE = new ForwardMQHandler();
    
    
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf o) throws Exception {
        byte[] bytes = ByteBufUtil.getBytes(o);
        RocketMQTemplate mqTemplate = ModuleContainerConfiguration.APPLICATION.getBean(RocketMQTemplate.class);
        Message message = MessageBuilder
                .withPayload(bytes)
                .build();
        mqTemplate.sendOneWay("test-topic", message);
        log.debug("ForwardMQHandler:: {}", bytes);
    }
}
