package in.hocg.message.netty.message.packet;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public abstract class AbstractPacket implements Serializable {
    /**
     * 协议版本
     */
    private byte version = 1;
    
    /**
     * 获取指令
     *
     * @return
     */
    public abstract byte getCommand();
    
    /**
     * 获取模块
     *
     * @return
     */
    public abstract byte getModule();
}
