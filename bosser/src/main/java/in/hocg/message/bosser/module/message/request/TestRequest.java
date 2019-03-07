package in.hocg.message.bosser.module.message.request;

import in.hocg.message.bosser.module.message.MessageConstant;
import in.hocg.message.bosser.module.message.ModuleConstant;
import in.hocg.message.netty.message.packet.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 * <p>
 * 测试消息
 *
 * @author hocgin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TestRequest extends AbstractPacket {
    
    private String message;
    
    @Override
    public byte getCommand() {
        return MessageConstant.TEST_REQUEST;
    }
    
    @Override
    public byte getModule() {
        return ModuleConstant.MODULE_1;
    }
}
