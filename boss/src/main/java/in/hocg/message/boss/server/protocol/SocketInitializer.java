package in.hocg.message.boss.server.protocol;

import in.hocg.message.boss.message.Splitter;
import in.hocg.message.boss.handler.TextHandler;
import in.hocg.message.boss.message.MessageCodec;
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
                // 打印
                .addLast("SPLITTER", new Splitter())
                .addLast("CODEC", new MessageCodec())
                // 业务处理器
                .addLast("HANDLER", new TextHandler())
        ;
    }
}
