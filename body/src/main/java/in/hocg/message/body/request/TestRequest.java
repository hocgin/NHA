package in.hocg.message.body.request;

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