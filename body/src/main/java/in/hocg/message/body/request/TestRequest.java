package in.hocg.message.body.request;

import in.hocg.message.body.constant.TestModule;
import in.hocg.message.core.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ToString
public class TestRequest extends AbstractPacket {
    
    private String message;
    
    @Override
    public byte getCommand() {
        return TestModule.TEST_REQUEST;
    }
    
    @Override
    public byte getModule() {
        return TestModule.MODULE_VALUE;
    }
}
