package in.hocg.message.netty.support;

import in.hocg.message.netty.DefaultNettyServer;
import in.hocg.message.netty.NettyServer;
import in.hocg.message.netty.ioc.Command;
import in.hocg.message.netty.ioc.Invoker;
import in.hocg.message.netty.ioc.InvokerManager;
import in.hocg.message.netty.ioc.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author hocgin
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(NettyProperties.class)
public class ModuleContainerConfiguration implements ApplicationContextAware, SmartInitializingSingleton {
    
    private ConfigurableApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }
    
    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(Module.class);
        beans.forEach((k, v)->{
            Class<?> clazz = v.getClass();
            Module module = clazz.getAnnotation(Module.class);
            for (Method method : clazz.getMethods()) {
                Command command = method.getAnnotation(Command.class);
                if (Objects.isNull(command)) {
                    continue;
                }
                String key = InvokerManager.genKey(module.value(), command.value());
                registerContainer(key, Invoker.valueOf(method, v));
            }
        });
    }
    
    private void registerContainer(String key, Invoker invoker) {
        if (InvokerManager.METHODS.containsKey(key)) {
            log.error("标识({})已经存在", key);
        } else {
            log.debug("类名: {}, 函数: {} => {}", invoker.getTarget().getClass(), invoker.getMethod().getName(), key);
            InvokerManager.METHODS.put(key, invoker);
        }
    }
    
    @Bean
    @ConditionalOnMissingBean(NettyServer.class)
    public NettyServer server(NettyProperties nettyProperties) {
        NettyServer server = new DefaultNettyServer(nettyProperties.getPort());
        server.start();
        return server;
    }
}
