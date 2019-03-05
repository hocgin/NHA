package in.hocg.message.boss.server;

import in.hocg.message.boss.Application;
import in.hocg.message.boss.handler.TextWebSocketFrameHandler;
import in.hocg.message.boss.module.service.TestService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RequiredArgsConstructor
public final class DefaultServer extends ChannelInitializer<Channel> implements Server {
    @NonNull
    private int port;
    private ServerBootstrap bootstrap;
    
    @Override
    public void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(this)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .bind(port)
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
    
    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                // 打印
                .addLast("LOG", new LoggingHandler(LogLevel.INFO))
                /*
                  心跳检测
                  - 500秒内未读取到客户端数据,会触发读超时
                  - 1秒内未向客户端发送数据,会触发写超时
                 */
//                .addLast(new IdleStateHandler(5, 1, 5, TimeUnit.SECONDS))
                // HTTP协议,编解码
//                .addLast("http-codec", new HttpServerCodec())
                /*
                  HTTP 消息合并
                  - 当消息超过 65536 会发生异常,可以对消息进行分包或增大参数容量
                 */
//                .addLast("aggregator", new HttpObjectAggregator(65536))
                // 分块处理
//                .addLast("http-chunk", new ChunkedWriteHandler())
                // Socket 协议处理
//                .addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/ws"))
                // 业务处理器
                .addLast("HANDLER", new TextWebSocketFrameHandler())
        ;
    }
    
    @Override
    public void destroy() {
        bootstrap.config().group().shutdownGracefully();
        bootstrap.config().childGroup().shutdownGracefully();
    }
}
