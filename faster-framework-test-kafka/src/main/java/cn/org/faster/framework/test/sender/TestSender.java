package cn.org.faster.framework.test.sender;

import cn.org.faster.framework.test.model.UserModel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author zhangbowen
 * @since 2018/12/25
 */
@RestController
@RequestMapping("/kafka")
public class TestSender {
    @Autowired
    private ReplyingKafkaTemplate<Object, Object, Object> replyingKafkaTemplate;

    @GetMapping("/batch")
    public String batch() {
        UserModel userModel = new UserModel();
        userModel.setName("批量测试");
        for (int i = 0; i < 10; i++) {
            userModel.setId(i + "");
            replyingKafkaTemplate.send("consumer-batch-test-topic", userModel);
        }
        return "success";
    }

    @GetMapping("/error")
    public String error() {
        UserModel userModel = new UserModel();
        userModel.setId("测试错误验证");
        replyingKafkaTemplate.send("error-test", userModel);
        return "success";
    }

    @GetMapping("/validation")
    public String validator(@Validated UserModel userModel) {
        userModel = new UserModel();
        replyingKafkaTemplate.send("validation-test", userModel);
        return "success";
    }


    /**
     * 发送消息后等待消费者回复
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/send")
    public String testSend() throws ExecutionException, InterruptedException {
        UserModel userModel = new UserModel();
        userModel.setId("heiehi");
        userModel.setName("hehehehe");
        ProducerRecord<Object, Object> record = new ProducerRecord<>("reply-send-topic", userModel);
        RequestReplyFuture<Object, Object, Object> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
        SendResult<Object, Object> sendResult = replyFuture.getSendFuture().get();
        System.out.println("Sent ok: " + sendResult.getRecordMetadata());
        ConsumerRecord<Object, Object> consumerRecord = replyFuture.get();
        System.out.println("Return value: " + consumerRecord.value());
        return "success";
    }

}
