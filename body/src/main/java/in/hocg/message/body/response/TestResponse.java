package in.hocg.message.body.response;

import in.hocg.message.body.CommandConstant;
import in.hocg.message.body.ModuleConstant;
import in.hocg.message.core.protocol.AbstractPacket;
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
public class TestResponse extends AbstractPacket {
    
    private String message;
    
    @Override
    public byte getCommand() {
        return CommandConstant.TEST_RESPONSE;
    }
    
    @Override
    public byte getModule() {
        return ModuleConstant.MODULE_1;
    }
}
