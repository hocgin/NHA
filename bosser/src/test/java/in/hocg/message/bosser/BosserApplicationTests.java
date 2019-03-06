package in.hocg.message.bosser;

import in.hocg.message.bosser.module.message.request.TestRequest;
import in.hocg.message.bosser.netty.initializer.SocketInitializer;
import in.hocg.message.bosser.netty.ioc.InvokerManager;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Before;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
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
