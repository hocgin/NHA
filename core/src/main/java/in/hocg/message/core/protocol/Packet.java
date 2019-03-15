package in.hocg.message.core.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author hocgin
 */
@Data
@ToString
@AllArgsConstructor
public class Packet implements Serializable {
    
    /**
     * 协议版本
     */
    private byte version;
    /**
     * 签名方式
     */
    private byte algorithm;
    /**
     * 模块
     */
    private byte module;
    /**
     * 命令
     */
    private byte command;
    /**
     * 所有数据
     */
    private byte[] data;
}
