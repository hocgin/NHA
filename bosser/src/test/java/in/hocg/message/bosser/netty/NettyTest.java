package in.hocg.message.bosser.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

/**
 * Created by hocgin on 2019/3/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class NettyTest {
    
    public void client(int port) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    public void initChannel(Channel ch) {
                        ch.pipeline()
                                .addLast(new SimpleChannelInboundHandler<Channel>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, Channel msg) throws Exception {
                                        msg.writeAndFlush("GOOD");
                                    }
                                });
                    }
                });
        bootstrap.connect("127.0.0.1", port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功!");
                    } else {
                        System.err.println("连接失败!");
                    }
                    
                });
    }
    
    @Test
    public void worked() {
        client(18765);
    }
}
