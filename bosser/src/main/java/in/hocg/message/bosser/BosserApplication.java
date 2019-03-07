package in.hocg.message.bosser;

import in.hocg.message.netty.DefaultServer;
import in.hocg.message.netty.ioc.InvokerManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hocgin
 */
@SpringBootApplication
public class BosserApplication {
    
    public static void main(String[] args) throws Exception {
        InvokerManager.scan(BosserApplication.class);
        new DefaultServer(18080).start();
        SpringApplication.run(BosserApplication.class, args);
    }
    
}
