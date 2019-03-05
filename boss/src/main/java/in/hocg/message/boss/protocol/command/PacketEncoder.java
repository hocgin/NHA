package in.hocg.message.boss.protocol.command;

import in.hocg.message.boss.protocol.Protocol;
import in.hocg.message.boss.protocol.command.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {
    
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, ByteBuf out) throws Exception {
        out.writeBytes(Protocol.encode(msg));
    }
}
