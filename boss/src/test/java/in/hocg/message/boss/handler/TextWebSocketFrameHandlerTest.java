package in.hocg.message.boss.handler;

import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class TextWebSocketFrameHandlerTest {
    EmbeddedChannel channel = new EmbeddedChannel(new TextWebSocketFrameHandler());
    
    @Test
    void channelRead0() {
    }
}