package in.hocg.message.bosser.netty.initializer;

import in.hocg.message.bosser.netty.handler.ForwardHandler;
import in.hocg.message.core.protocol.IdleStateCheck;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * Created by hocgin on 2019/3/5.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        
        ch.pipeline()
                // 心跳检测
                .addLast(new IdleStateCheck())
                // HTTP协议,编解码
                .addLast("http-codec", new HttpServerCodec())
                /*
                  HTTP 消息合并
                  - 当消息超过 65536 会发生异常,可以对消息进行分包或增大参数容量
                 */
                .addLast("aggregator", new HttpObjectAggregator(65536))
                // 分块处理
                .addLast("http-chunk", new ChunkedWriteHandler())
                // Socket 协议处理
                .addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/ws"))
                // 业务处理器
                .addLast("HANDLER", ForwardHandler.INSTANCE)
        ;
    }
}
