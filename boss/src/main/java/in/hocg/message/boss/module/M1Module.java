package in.hocg.message.boss.module;

import in.hocg.message.boss.module.message.MessageConstant;
import in.hocg.message.boss.module.message.ModuleConstant;
import in.hocg.message.boss.module.message.request.TestRequest;
import in.hocg.message.boss.netty.ioc.Command;
import in.hocg.message.boss.netty.ioc.Module;
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
