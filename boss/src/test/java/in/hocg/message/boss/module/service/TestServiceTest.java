package in.hocg.message.boss.module.service;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
class TestServiceTest {
    
    @Inject
    private TestService testService;
    
    @Test
    void worked() {
        testService.worked();
    }
}