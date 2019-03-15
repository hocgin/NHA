package in.hocg.message.core.ioc;

import io.netty.util.internal.PlatformDependent;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Optional;

/**
 * @author hocgin
 * @date 18-7-31
 **/
@UtilityClass
public final class InvokerManager {
    public static final Map<String, Invoker> METHODS = PlatformDependent.newConcurrentHashMap();
    
    /**
     * 键码生成策略
     *
     * @param module
     * @param command
     * @return
     */
    public static String genKey(int module, int command) {
        return String.format("%d-%d", module, command);
    }
    
    
    /**
     * 获取执行器
     *
     * @param module
     * @param command
     * @return
     */
    public static Optional<Invoker> getInvoker(int module, int command) {
        return Optional.ofNullable(METHODS.get(genKey(module, command)));
    }
}