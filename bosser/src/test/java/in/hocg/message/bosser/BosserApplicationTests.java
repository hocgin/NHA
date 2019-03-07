package in.hocg.message.bosser;

import in.hocg.message.bosser.module.message.request.TestRequest;
import in.hocg.message.netty.initializer.SocketInitializer;
import in.hocg.message.netty.ioc.InvokerManager;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class BosserApplicationTests {
    
    
    private EmbeddedChannel channel = new EmbeddedChannel(new SocketInitializer());
    
    @Before
    public void before() throws Exception {
        InvokerManager.scan(BosserApplication.class);
    }
    
    @Test
    public void decoder() {
        TestRequest testRequest = new TestRequest();
        testRequest.setMessage("ggg");
        channel.writeInbound(testRequest);
    }
    
    
    @Test
    public void contextLoads() throws Exception {
        InvokerManager.scan(BosserApplication.class);
    }
    
}
