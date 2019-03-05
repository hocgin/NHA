package in.hocg.message.boss.protocol;

import in.hocg.message.boss.protocol.command.Command;
import in.hocg.message.boss.protocol.command.packet.AbstractPacket;
import in.hocg.message.boss.serializer.Serializer;
import in.hocg.message.boss.serializer.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Optional;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 * <p>
 * 魔数(4) | 版本号(1) | 序列化算法(1) | 指令(1) | 数据长度(4) | 数据(n)
 *
 * @author hocgin
 */
public class Protocol {
    public static int MAGIC_NUMBER = 4;
    public static int VERSION = 1;
    public static int SERIALIZER_ALGORITHM = 1;
    public static int COMMEND = 1;
    public static int DATA_LENGTH = 1;
    
    /**
     * 解码
     *
     * @param buf
     * @return
     */
    public static AbstractPacket decode(ByteBuf buf) {
        
        // 魔数(4)
        buf.skipBytes(MAGIC_NUMBER);
        
        // 版本号(1)
        buf.skipBytes(VERSION);
        
        // 序列化算法(1)
        byte serializeAlgorithm = buf.readByte();
        
        // 指令(1)
        byte command = buf.readByte();
        
        // 数据长度(4)
        int length = buf.readInt();
        
        // 数据(n)
        byte[] content = new byte[length];
        buf.readBytes(content);
        
        Optional<Class<? extends AbstractPacket>> packetClazz = Command.getPacket(command);
        Optional<Serializer> serializer = SerializerAlgorithm.getSerializer(serializeAlgorithm);
        
        AbstractPacket packet;
        if (packetClazz.isPresent() && serializer.isPresent()) {
            packet = serializer.get().deserialize(packetClazz.get(), content);
            return packet;
        }
        
        return null;
    }
    
    /**
     * 编码
     *
     * @param packet
     * @return
     */
    public static ByteBuf encode(AbstractPacket packet) {
        SerializerAlgorithm defaultSerializerAlgorithm = SerializerAlgorithm.JSON;
        
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = defaultSerializerAlgorithm.serializer()
                .serialize(packet);
        
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(defaultSerializerAlgorithm.algorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        
        return byteBuf;
    }
}
