package in.hocg.message.bosser.netty.handler;

import in.hocg.message.bosser.netty.message.Packet;
import in.hocg.message.bosser.netty.support.NettyStarterConfiguration;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * @author hocgin
 */
@Slf4j
@ChannelHandler.Sharable
public class ForwardHandler extends SimpleChannelInboundHandler<Packet> {
    public static final ForwardHandler INSTANCE = new ForwardHandler();
    
    
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) {
        byte module = packet.getModule();
        byte command = packet.getCommand();
        byte[] data = packet.getData();
        String destination = String.format("%s:%s-%s", "test-topic", module, command);
    
        RocketMQTemplate mqTemplate = NettyStarterConfiguration.APPLICATION.getBean(RocketMQTemplate.class);
        mqTemplate.sendOneWay(destination, data);
        log.debug("发送消息至MQ, Destination: {}, data: {}", destination, data);
    }
}
