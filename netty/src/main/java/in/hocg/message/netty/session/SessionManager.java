package in.hocg.message.netty.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.io.Serializable;

/**
 * Created by hocgin on 2019/3/6.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface SessionManager {
    
    /**
     * 登陆在线
     * @param key
     * @param channel
     */
    Channel add(Serializable key, Channel channel);
    
    /**
     * 移除在线
     * @param key
     */
    Channel remove(Serializable key);
    
    /**
     * 获取 Channel
     * @param key
     * @return
     */
    Channel get(Serializable key);
    
    /**
     * 加入组
     * @param key
     * @param group
     */
    ChannelGroup addGroup(Serializable key, ChannelGroup group);
    
    /**
     * 解散组
     * @param key
     */
    ChannelGroup removeGroup(Serializable key);
    
    /**
     * 获取组
     * @param key
     * @return
     */
    ChannelGroup getGroup(Serializable key);
}
