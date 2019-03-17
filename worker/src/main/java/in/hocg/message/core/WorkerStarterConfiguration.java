package in.hocg.message.core;

import in.hocg.message.core.ioc.Command;
import in.hocg.message.core.ioc.Invoker;
import in.hocg.message.core.ioc.InvokerManager;
import in.hocg.message.core.ioc.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author hocgin
 */
@Slf4j
@Configuration
public class WorkerStarterConfiguration implements ApplicationContextAware, SmartInitializingSingleton {
    private static ConfigurableApplicationContext APPLICATION;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION = (ConfigurableApplicationContext) applicationContext;
    }
    
    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> beans = APPLICATION.getBeansWithAnnotation(Module.class);
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
    
}
