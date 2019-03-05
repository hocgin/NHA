package in.hocg.message.boss.server;

import in.hocg.message.boss.core.Splitter;
import in.hocg.message.boss.handler.TextHandler;
import in.hocg.message.boss.protocol.command.PacketDecoder;
import in.hocg.message.boss.protocol.command.PacketEncoder;
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
public final class TextServer extends ChannelInitializer<Channel> implements Server {
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
                .addLast("SPLITTER", new Splitter())
                .addLast("ENCODE", new PacketEncoder())
                .addLast("DECODER", new PacketDecoder())
                // 业务处理器
                .addLast("HANDLER", new TextHandler())
        ;
    }
    
    @Override
    public void destroy() {
        bootstrap.config().group().shutdownGracefully();
        bootstrap.config().childGroup().shutdownGracefully();
    }
}
