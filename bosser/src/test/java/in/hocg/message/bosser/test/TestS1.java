package in.hocg.message.bosser.test;

import in.hocg.message.netty.ioc.Command;
import in.hocg.message.netty.ioc.Module;
import org.springframework.beans.factory.annotation.Autowired;

@Module(0)
public class TestS1 {
    
    @Autowired
    private TestS2 testS2;
    
    public void test(String a) {
    
    }
    
    @Command(1)
    public void test2(String a) {
        System.out.println(testS2);
    }
    
}
