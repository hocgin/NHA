package in.hocg.message.bosser.module;

import in.hocg.message.bosser.module.message.MessageConstant;
import in.hocg.message.bosser.module.message.ModuleConstant;
import in.hocg.message.bosser.module.message.request.TestRequest;
import in.hocg.message.netty.ioc.Command;
import in.hocg.message.netty.ioc.Module;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/3/6.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Module(ModuleConstant.MODULE_1)
@Slf4j
public class M1Module {
    
    @Command(MessageConstant.TEST_REQUEST)
    public void test(ChannelHandlerContext ctx, TestRequest msg) {
        log.debug("Nice {}", msg.getMessage());
    }
}
