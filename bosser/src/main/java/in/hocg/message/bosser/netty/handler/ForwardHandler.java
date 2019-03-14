package in.hocg.message.bosser.netty.handler;

import in.hocg.message.bosser.netty.ioc.Invoker;
import in.hocg.message.bosser.netty.ioc.InvokerManager;
import in.hocg.message.body.packet.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Created by hocgin on 2019/3/6.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@ChannelHandler.Sharable
public class ForwardHandler extends SimpleChannelInboundHandler<AbstractPacket> {
    public static final ForwardHandler INSTANCE = new ForwardHandler();
    
    private ForwardHandler() {
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket msg) {
        log.debug("分发消息 {} {}", msg.getModule(), msg.getCommand());
        Optional<Invoker> invokerOptional = InvokerManager.getInvoker(msg.getModule(), msg.getCommand());
        invokerOptional.ifPresent(invoker -> invoker.invoke(ctx, msg));
    }
}
