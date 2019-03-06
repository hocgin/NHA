package in.hocg.message.boss.netty.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * 拆包器
 *
 * @author hocgin
 */
public class Splitter extends LengthFieldBasedFrameDecoder {
    
    public Splitter() {
        super(Integer.MAX_VALUE, WordWidth.LENGTH_FIELD_OFFSET, WordWidth.LENGTH_FIELD_LENGTH);
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != WordWidth.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        
        return super.decode(ctx, in);
    }
}
