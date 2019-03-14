package in.hocg.message.bosser.netty.message;

import in.hocg.message.bosser.netty.ioc.Invoker;
import in.hocg.message.bosser.netty.ioc.InvokerManager;
import in.hocg.message.body.packet.AbstractPacket;
import in.hocg.message.bosser.netty.serializer.Serializer;
import in.hocg.message.bosser.netty.serializer.SerializerAlgorithm;
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
 * 魔数(4) | 版本号(1) | 序列化算法(1) | 模块(1) | 指令(1) | 数据长度(4) | 数据(n)
 *
 * @author hocgin
 */
@ChannelHandler.Sharable
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
        
        // 魔数(4)
        msg.skipBytes(WordConstant.Width.MAGIC_NUMBER);
        
        // 版本号(1)
        msg.skipBytes(WordConstant.Width.VERSION);
        
        // 序列化算法(1)
        byte serializeAlgorithm = msg.readByte();
        
        // 模块(1)
        byte module = msg.readByte();
        
        // 指令(1)
        byte command = msg.readByte();
        
        // 数据长度(4)
        int length = msg.readInt();
        
        // 数据(n)
        byte[] content = new byte[length];
        msg.readBytes(content);
        
        Optional<Invoker> invokerOptional = InvokerManager.getInvoker(module, command);
        Optional<Serializer> serializer = SerializerAlgorithm.getSerializer(serializeAlgorithm);
        
        AbstractPacket packet = null;
        if (invokerOptional.isPresent() && serializer.isPresent()) {
            Class<?>[] parameterTypes = invokerOptional.get()
                    .getMethod()
                    .getParameterTypes();
            Class<?> parameterType = AbstractPacket.class;
            if (parameterTypes.length > 1) {
                parameterType = parameterTypes[1];
            }
            packet = (AbstractPacket) serializer.get().deserialize(parameterType, content);
        }
        
        out.add(packet);
    }
}
