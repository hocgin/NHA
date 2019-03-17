package in.hocg.message.worker.module;

import in.hocg.message.body.constant.TestModule;
import in.hocg.message.body.request.TestRequest;
import in.hocg.message.body.response.TestResponse;
import in.hocg.message.core.constant.MessageConstant;
import in.hocg.message.core.constant.MessageHeader;
import in.hocg.message.core.ioc.Command;
import in.hocg.message.core.ioc.Module;
import in.hocg.message.core.protocol.Codec;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author hocgin
 */
@Slf4j
@AllArgsConstructor
@Module(TestModule.MODULE_VALUE)
public class TestModuleService {
    
    private final RocketMQTemplate mqTemplate;
    
    @Command(TestModule.TEST_REQUEST)
    public void testCommand(String source, TestRequest testRequest) {
        log.debug("接收到的信息", testRequest.getMessage());
        
//        ========================================================
//        todo: 从 redis 检索出发送目标的 topic 添加topic
//        ========================================================
        TestResponse response = new TestResponse();
        response.setMessage("响应内容");
        mqTemplate.sendOneWay(MessageConstant.BOSSER_TOPIC, MessageBuilder
                .withPayload(Codec.encode(response))
                .setHeader(MessageHeader.DESTINATION, source)
                .build());
    }
}
