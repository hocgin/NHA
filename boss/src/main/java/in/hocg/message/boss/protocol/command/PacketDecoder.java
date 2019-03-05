package in.hocg.message.boss.protocol.command;

import in.hocg.message.boss.protocol.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * 解码器
 *
 * @author hocgin
 */
public class PacketDecoder extends ByteToMessageDecoder {
    
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(Protocol.decode(in));
    }
}
