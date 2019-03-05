package in.hocg.message.boss.message;

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
    private static final int LENGTH_FIELD_OFFSET = WordWidth.MAGIC_NUMBER + WordWidth.VERSION + WordWidth.SERIALIZER_ALGORITHM + WordWidth.COMMEND;
    private static final int LENGTH_FIELD_LENGTH = WordWidth.DATA_LENGTH;
    
    public Splitter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
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
