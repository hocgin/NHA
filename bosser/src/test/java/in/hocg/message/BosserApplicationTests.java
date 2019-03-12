package in.hocg.message;

import in.hocg.message.bosser.module.message.request.TestRequest;
import in.hocg.message.netty.initializer.SocketInitializer;
import in.hocg.message.netty.ioc.InvokerManager;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BosserApplicationTests {
    
    
    private EmbeddedChannel channel = new EmbeddedChannel(new SocketInitializer());
    
    @Test
    public void decoder() {
        TestRequest testRequest = new TestRequest();
        testRequest.setMessage("ggg");
        channel.writeInbound(testRequest);
    }
    
    
    @Test
    public void test2() throws IllegalAccessException {
        log.info("{}", InvokerManager.METHODS);
        InvokerManager.METHODS.get(InvokerManager.genKey(0, 1))
                .invoke("gg");
        System.out.println();
    }
}
