package in.hocg.message.boss.message;

import in.hocg.message.boss.message.command.Command;
import in.hocg.message.boss.message.command.packet.AbstractPacket;
import in.hocg.message.boss.serializer.Serializer;
import in.hocg.message.boss.serializer.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;
import java.util.Optional;

/**
 * Created by hocgin on 2019/3/5.
 * email: hocgin@gmail.com
 * 魔数(4) | 版本号(1) | 序列化算法(1) | 指令(1) | 数据长度(4) | 数据(n)
 *
 * @author hocgin
 */
@ChannelHandler.Sharable
public class MessageCodec extends MessageToMessageCodec<ByteBuf, AbstractPacket> {
    
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
        
        byteBuf.writeInt(WordWidth.MAGIC_NUMBER);
        byteBuf.writeByte(msg.getVersion());
        byteBuf.writeByte(defaultSerializerAlgorithm.algorithm());
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
        
        // 魔数(4)
        msg.skipBytes(WordWidth.MAGIC_NUMBER);
        
        // 版本号(1)
        msg.skipBytes(WordWidth.VERSION);
        
        // 序列化算法(1)
        byte serializeAlgorithm = msg.readByte();
        
        // 指令(1)
        byte command = msg.readByte();
        
        // 数据长度(4)
        int length = msg.readInt();
        
        // 数据(n)
        byte[] content = new byte[length];
        msg.readBytes(content);
        
        Optional<Class<? extends AbstractPacket>> packetClazz = Command.getPacket(command);
        Optional<Serializer> serializer = SerializerAlgorithm.getSerializer(serializeAlgorithm);
        
        AbstractPacket packet = null;
        if (packetClazz.isPresent() && serializer.isPresent()) {
            packet = serializer.get().deserialize(packetClazz.get(), content);
        }
        
        out.add(packet);
    }
}
