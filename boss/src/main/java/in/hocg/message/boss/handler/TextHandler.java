package in.hocg.message.boss.handler;

import in.hocg.message.boss.protocol.command.packet.AbstractPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class TextHandler extends SimpleChannelInboundHandler<AbstractPacket> {
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket msg) throws Exception {
        if (msg == null) {
            log.error("Packet解析失败 {}", msg);
            return;
        }
        log.debug("消息类型: {}", msg.getClass());
    }
}
