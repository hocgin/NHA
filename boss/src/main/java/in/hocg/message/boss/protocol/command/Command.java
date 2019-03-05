package in.hocg.message.boss.protocol.command;

import in.hocg.message.boss.protocol.command.packet.AbstractPacket;
import in.hocg.message.boss.protocol.command.packet.TestPacket;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum Command {
    
    /**
     * 测试指令
     */
    TEST(((byte) 1), TestPacket.class);
    
    @NonNull
    private byte commend;
    @NonNull
    private Class<? extends AbstractPacket> packetClazz;
    
    /**
     * 获取指令
     *
     * @param commend
     * @return
     */
    public static Optional<Class<? extends AbstractPacket>> getPacket(byte commend) {
        for (Command command : Command.values()) {
            if (command.commend == commend) {
                return Optional.ofNullable(command.packetClazz);
            }
        }
        return Optional.empty();
    }
}
