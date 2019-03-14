package in.hocg.message.body.response;

import in.hocg.message.body.MessageConstant;
import in.hocg.message.body.ModuleConstant;
import in.hocg.message.body.packet.AbstractPacket;
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
        return MessageConstant.TEST_RESPONSE;
    }
    
    @Override
    public byte getModule() {
        return ModuleConstant.MODULE_1;
    }
}
