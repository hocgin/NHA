package in.hocg.message;

import in.hocg.message.netty.DefaultServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hocgin
 */
@SpringBootApplication
public class BosserApplication {
    
    public static void main(String[] args) throws Exception {
        new DefaultServer(18080).start();
        SpringApplication.run(BosserApplication.class, args);
    }
    
}
