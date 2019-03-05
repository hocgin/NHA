package in.hocg.message.boss.handler;

import in.hocg.message.boss.message.command.packet.TestPacket;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class TextHandlerTest {
    private EmbeddedChannel channel = new EmbeddedChannel(new TextHandler());
    
    @Test
    void decoder() {
        TestPacket packet = new TestPacket();
        packet.setMessage("消息");
        channel.writeInbound(packet);
    }
    
    @Test
    void object() {
        channel.writeInbound(new Object());
    }
}