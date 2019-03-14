package in.hocg.message.bosser.netty.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 *
 * @author hocgin
 */
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
        byte algorithm = msg.readByte();
        
        // 模块(1)
        byte module = msg.readByte();
        
        // 指令(1)
        byte command = msg.readByte();
        
        // 数据长度(4)
        int length = msg.readInt();
        
        // 数据(n)
        byte[] content = new byte[length];
        
        msg.readBytes(content);
        
        list.add(new Packet(version, algorithm, module, command, content));
    }
}
