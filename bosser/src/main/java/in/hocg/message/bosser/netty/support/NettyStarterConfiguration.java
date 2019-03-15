package in.hocg.message.bosser.netty.support;

import in.hocg.message.bosser.netty.DefaultNettyServer;
import in.hocg.message.bosser.netty.NettyServer;
import in.hocg.message.bosser.netty.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hocgin
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(NettyProperties.class)
public class NettyStarterConfiguration implements ApplicationContextAware {
    
    public static ConfigurableApplicationContext APPLICATION;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION = (ConfigurableApplicationContext) applicationContext;
    }
    
    @Bean
    @ConditionalOnMissingBean(NettyServer.class)
    public NettyServer server(NettyProperties nettyProperties) {
        NettyServer server = new DefaultNettyServer(nettyProperties.getPort());
        server.start();
        return server;
    }
}
