package in.hocg.message.netty.handler;

import in.hocg.message.netty.ioc.Invoker;
import in.hocg.message.netty.ioc.InvokerManager;
import in.hocg.message.netty.message.packet.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Optional;

/**
 * Created by hocgin on 2019/3/6.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ChannelHandler.Sharable
public class ForwardHandler extends SimpleChannelInboundHandler<AbstractPacket> {
    public static final ForwardHandler INSTANCE = new ForwardHandler();
    
    private ForwardHandler() {
    
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket msg) throws Exception {
        
        Optional<Invoker> invokerOptional = InvokerManager.getInvoker(msg.getModule(), msg.getCommand());
        invokerOptional.ifPresent(invoker -> invoker.invoke(ctx, msg));
    }
}
