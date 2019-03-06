package in.hocg.message.bosser.netty.ioc;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author hocgin
 * @date 18-7-31
 **/
@Data
public class Invoker {
    private Object target;
    private Method method;
    
    static Invoker valueOf(Method method, Object target){
        Invoker invoker = new Invoker();
        invoker.setTarget(target);
        invoker.setMethod(method);
        return invoker;
    }
    
    public Object invoke(Object... paramValues){
        try {
            return method.invoke(target, paramValues);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
