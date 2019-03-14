package in.hocg.message.bosser.netty.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 * @author hocgin
 */
@ChannelHandler.Sharable
public class MessageDecode extends ByteToMessageDecoder {
    
    public final static MessageDecode INSTANCE = new MessageDecode();
    
//    private MessageDecode() {
//    }
    
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> list) throws Exception {
    
        // 魔数(4)
        msg.skipBytes(WordConstant.Width.MAGIC_NUMBER);
    
        // 版本号(1)
        byte version = msg.readByte();
    
        // 序列化算法(1)
        msg.skipBytes(WordConstant.Width.SERIALIZER_ALGORITHM);
    
        // 模块(1)
        byte module = msg.readByte();
    
        // 指令(1)
        byte command = msg.readByte();
    
        list.add(new Packet(version, module, command, ByteBufUtil.getBytes(msg)));
    }
}
