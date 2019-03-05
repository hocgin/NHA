package in.hocg.message.boss.protocol.command.packet;

import in.hocg.message.boss.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * 测试消息
 *
 * @author hocgin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TestPacket extends AbstractPacket {
    
    private String message;
    
    @Override
    public byte getCommand() {
        return Command.TEST.commend();
    }
}
