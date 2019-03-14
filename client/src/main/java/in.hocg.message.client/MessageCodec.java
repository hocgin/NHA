package in.hocg.message.client;

import in.hocg.message.body.packet.AbstractPacket;
import in.hocg.message.core.serializer.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * Created by hocgin on 2019/3/5.
 * email: hocgin@gmail.com
 * 魔数(4) | 版本号(1) | 序列化算法(1) | 模块(1) | 指令(1) | 数据长度(4) | 数据(n)
 *
 * @author hocgin
 */
public class MessageCodec extends MessageToMessageCodec<ByteBuf, AbstractPacket> {
    
    private MessageCodec() {
    }
    
    public final static MessageCodec INSTANCE = new MessageCodec();
    /**
     * 编码
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
        SerializerAlgorithm defaultSerializerAlgorithm = SerializerAlgorithm.JSON;
        
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = defaultSerializerAlgorithm.serializer()
                .serialize(msg);
        
        byteBuf.writeInt(WordConstant.Content.MAGIC_NUMBER_CONTENT);
        byteBuf.writeByte(msg.getVersion());
        byteBuf.writeByte(defaultSerializerAlgorithm.algorithm());
        byteBuf.writeByte(msg.getModule());
        byteBuf.writeByte(msg.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        
        out.add(byteBuf);
    }
    
    /**
     * 解码
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        // 解码先不做处理
        System.out.println("解码先不做处理");
    }
}
