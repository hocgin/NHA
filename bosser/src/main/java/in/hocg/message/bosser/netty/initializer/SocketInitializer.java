package in.hocg.message.bosser.netty.initializer;

import in.hocg.message.bosser.netty.handler.ForwardMQHandler;
import in.hocg.message.bosser.netty.message.Splitter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2019/3/5.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class SocketInitializer extends ChannelInitializer<Channel> {
    
    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline()
                .addLast(new LoggingHandler(LogLevel.DEBUG))
                .addLast("SPLITTER", new Splitter())
//                .addLast("CODEC", MessageCodec.INSTANCE)
                // 业务处理器
                .addLast("FORWARD-HANDLER", ForwardMQHandler.INSTANCE)
        ;
    }
}
