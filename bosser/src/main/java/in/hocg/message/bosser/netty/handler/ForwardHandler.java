package in.hocg.message.bosser.netty.handler;

import in.hocg.message.core.protocol.Packet;
import in.hocg.message.bosser.netty.session.SessionManager;
import in.hocg.message.bosser.netty.support.NettyStarterConfiguration;
import in.hocg.message.core.constant.MessageConstant;
import in.hocg.message.core.constant.MessageHeader;
import io.netty.channel.Channel;
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
public class ForwardHandler extends SimpleChannelInboundHandler<Packet> {
    public static final ForwardHandler INSTANCE = new ForwardHandler();
    
    
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) {
        byte module = packet.getModule();
        byte command = packet.getCommand();
        byte algorithm = packet.getAlgorithm();
        byte[] data = packet.getData();
        
        RocketMQTemplate mqTemplate = NettyStarterConfiguration.APPLICATION.getBean(RocketMQTemplate.class);
        Message message = MessageBuilder.withPayload(data)
                .setHeader(MessageHeader.SOURCE, channelHandlerContext.channel().id().asLongText())
                .setHeader(MessageHeader.ALGORITHM, algorithm)
                .setHeader(MessageHeader.COMMAND, command)
                .setHeader(MessageHeader.MODULE, module)
                .build();
    
        mqTemplate.sendOneWay(MessageConstant.WORKER_TOPIC, message);
        log.debug("发送消息至MQ, Destination: {}\n data: {} \n dataString: {}", MessageConstant.WORKER_TOPIC, data, new String(data));
    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被添加：handlerAdded()");
        super.handlerAdded(ctx);
    }
    
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 绑定到线程(NioEventLoop)：channelRegistered()");
        super.channelRegistered(ctx);
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 准备就绪：channelActive()");
        Channel channel = ctx.channel();
        channel.writeAndFlush("Hi Login Ok");
        SessionManager.add(channel.id().asLongText(), channel);
        super.channelActive(ctx);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channel 有数据可读：channelRead()");
        super.channelRead(ctx, msg);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 某次数据读完：channelReadComplete()");
        super.channelReadComplete(ctx);
        
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 被关闭：channelInactive()");
        Channel channel = ctx.channel();
        SessionManager.remove(channel.id().asLongText());
        super.channelInactive(ctx);
    }
    
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()");
        super.channelUnregistered(ctx);
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被移除：handlerRemoved()");
        super.handlerRemoved(ctx);
    }
}
