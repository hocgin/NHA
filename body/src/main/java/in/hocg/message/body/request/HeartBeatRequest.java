package in.hocg.message.body.request;

import in.hocg.message.body.CommandConstant;
import in.hocg.message.body.ModuleConstant;
import in.hocg.message.core.protocol.AbstractPacket;

public class HeartBeatRequest extends AbstractPacket {
    @Override
    public byte getCommand() {
        return CommandConstant.HEART_BEAT_REQUEST;
    }
    
    @Override
    public byte getModule() {
        return ModuleConstant.DEFAULT_MODULE;
    }
    
    public static byte s() {
        return 1;
    }
}
