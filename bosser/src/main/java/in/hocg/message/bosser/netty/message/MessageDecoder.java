package in.hocg.message.bosser.netty.message;

import in.hocg.message.core.protocol.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 *
 * @author hocgin
 */
public class MessageDecoder extends ByteToMessageDecoder {
    
    public final static MessageDecoder INSTANCE = new MessageDecoder();

//    private MessageDecoder() {
//    }
    
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> list) {
        list.add(Codec.decode(msg));
    }
}
