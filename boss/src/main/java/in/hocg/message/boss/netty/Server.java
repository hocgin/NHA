package in.hocg.message.boss.netty;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface Server {
    /**
     * 启动
     */
    void start();
    
    /**
     * 摧毁
     */
    void destroy();
}
