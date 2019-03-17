package in.hocg.message.body.request;

import in.hocg.message.body.constant.DefaultModule;
import in.hocg.message.core.protocol.AbstractPacket;

public class HeartBeatRequest extends AbstractPacket {
    @Override
    public byte getCommand() {
        return DefaultModule.HEART_BEAT_REQUEST;
    }
    
    @Override
    public byte getModule() {
        return DefaultModule.MODULE_VALUE;
    }
    
}
