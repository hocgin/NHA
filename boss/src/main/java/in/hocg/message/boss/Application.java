package in.hocg.message.boss;

import com.google.inject.Guice;
import com.google.inject.Injector;
import in.hocg.message.boss.module.AppModule;
import in.hocg.message.boss.server.DefaultServer;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Application {
    public static Injector ALL;
    
    public static void main(String[] args) {
        ALL = Guice.createInjector(new AppModule());
        
        new DefaultServer(10086).start();
    }
    
}
