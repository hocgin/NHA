package in.hocg.message.worker.module;

import in.hocg.message.body.CommandConstant;
import in.hocg.message.body.ModuleConstant;
import in.hocg.message.body.request.TestRequest;
import in.hocg.message.body.response.TestResponse;
import in.hocg.message.core.constant.MessageConstant;
import in.hocg.message.core.constant.MessageHeader;
import in.hocg.message.core.ioc.Command;
import in.hocg.message.core.ioc.Module;
import in.hocg.message.core.protocol.Codec;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author hocgin
 */
@AllArgsConstructor
@Module(ModuleConstant.MODULE_1)
public class TestModule {
    
    private final RocketMQTemplate mqTemplate;
    
    @Command(CommandConstant.TEST_REQUEST)
    public void testCommand(String source, TestRequest testRequest) {
//        todo: 从 redis 检索出发送目标的 topic 添加topic
        TestResponse response = new TestResponse();
        response.setMessage("响应内容");
        mqTemplate.sendOneWay(MessageConstant.BOSSER_TOPIC, MessageBuilder
                .withPayload(Codec.encode(response))
                .setHeader(MessageHeader.DESTINATION, source)
                .build());
    }
}
