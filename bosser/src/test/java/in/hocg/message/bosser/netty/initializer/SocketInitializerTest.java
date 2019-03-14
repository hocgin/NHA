package in.hocg.message.bosser.netty.initializer;

import in.hocg.message.body.request.TestRequest;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

public class SocketInitializerTest {
    EmbeddedChannel embeddedChannel = new EmbeddedChannel(new SocketInitializer());
    
    @Test
    public void initChannel() {
        TestRequest testRequest = new TestRequest();
        testRequest.setMessage("Hello");
        embeddedChannel.writeInbound(testRequest);
    }
}