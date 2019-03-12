package in.hocg.message.bosser.netty.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hocgin on 2019/3/6.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class DefaultSessionManager implements SessionManager {
    private static final Map<Serializable, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();
    private final Map<Serializable, ChannelGroup> CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();
    
    
    @Override
    public Channel add(Serializable key, Channel channel) {
        if (CHANNEL_MAP.containsKey(key)) {
            throw new UnsupportedOperationException("Key已经存在");
        }
        return CHANNEL_MAP.put(key, channel);
    }
    
    @Override
    public Channel remove(Serializable key) {
        return CHANNEL_MAP.remove(key);
    }
    
    @Override
    public Channel get(Serializable key) {
        return CHANNEL_MAP.get(key);
    }
    
    @Override
    public ChannelGroup addGroup(Serializable key, ChannelGroup group) {
        if (CHANNEL_GROUP_MAP.containsKey(key)) {
            throw new UnsupportedOperationException("Key已经存在");
        }
        return CHANNEL_GROUP_MAP.put(key, group);
    }
    
    @Override
    public ChannelGroup removeGroup(Serializable key) {
        return CHANNEL_GROUP_MAP.remove(key);
    }
    
    @Override
    public ChannelGroup getGroup(Serializable key) {
        return CHANNEL_GROUP_MAP.get(key);
    }
}
