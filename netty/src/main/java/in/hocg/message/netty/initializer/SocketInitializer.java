package in.hocg.message.netty.initializer;

import in.hocg.message.netty.handler.ForwardHandler;
import in.hocg.message.netty.message.MessageCodec;
import in.hocg.message.netty.message.Splitter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * Created by hocgin on 2019/3/5.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class SocketInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        
        ch.pipeline()
                .addLast("SPLITTER", new Splitter())
                .addLast("CODEC", new MessageCodec())
                // 业务处理器
                .addLast("FORWARD-HANDLER", ForwardHandler.INSTANCE)
        ;
    }
}
