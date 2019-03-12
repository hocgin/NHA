package in.hocg.message.bosser.netty;

import in.hocg.message.bosser.netty.initializer.SocketInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@RequiredArgsConstructor
public final class DefaultNettyServer implements NettyServer {
    @NonNull
    private int port;
    private ServerBootstrap bootstrap;
    
    @Override
    public void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new SocketInitializer())
                .bind(port)
                .addListener(future -> {
                    log.debug("端口[{}]绑定{}", port, future.isSuccess() ? "成功" : "失败");
                });
    }
    
    @Override
    public void destroy() {
        bootstrap.config().group().shutdownGracefully();
        bootstrap.config().childGroup().shutdownGracefully();
    }
}
