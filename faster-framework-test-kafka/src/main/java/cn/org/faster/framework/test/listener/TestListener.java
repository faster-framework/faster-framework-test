package cn.org.faster.framework.test.listener;

import cn.org.faster.framework.test.model.UserModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author zhangbowen
 * @since 2018/12/25
 */
@Component
public class TestListener {

    @KafkaListener(topics = "consumer-batch-test-topic")
    public void kafkaBatchListener(List<UserModel> records) {
        System.out.println(records);
    }

    /**
     * 抛出异常
     * @param userModel
     */
    @KafkaListener(topics = "error-test")
    public void errorTest(UserModel userModel) {
        System.out.println("错误验证：");
        System.out.println(userModel);
        throw new RuntimeException("1");
    }

    /**
     * 死信监听
     *
     *     KafkaHeaders.DLT_EXCEPTION_FQCN - 异常类名称
     *     KafkaHeaders.DLT_EXCEPTION_STACKTRACE -  异常堆栈跟踪。
     *     KafkaHeaders.DLT_EXCEPTION_MESSAGE -异常消息。
     *     KafkaHeaders.DLT_ORIGINAL_TOPIC - 原始主题。
     *     KafkaHeaders.DLT_ORIGINAL_PARTITION 原始分区。
     *     KafkaHeaders.DLT_ORIGINAL_OFFSET - 原始偏移量。
     *     KafkaHeaders.DLT_ORIGINAL_TIMESTAMP - 原始时间戳。
     *     KafkaHeaders.DLT_ORIGINAL_TIMESTAMP_TYPE -  原始时间戳类型。
     *
     * @param userModel
     */
    @KafkaListener(topics = "error-test.DLT")
    public void errorTestDLT(UserModel userModel,@Header(KafkaHeaders.DLT_EXCEPTION_STACKTRACE) String stack) {
        System.out.println("死信消息：");
        System.out.println(userModel);
    }

    @KafkaListener(topics = "validation-test")
    public void validationTest(@Validated UserModel userModel) {
        System.out.println("验证测试：");
        System.out.println(userModel);
    }

    @KafkaListener(topics = "reply-send-topic")
    @SendTo
    public UserModel listen(UserModel userModel, Acknowledgment acknowledgment) {
        System.out.println(userModel);
        UserModel success = new UserModel();
        success.setName("成功的");
        acknowledgment.acknowledge();
        return success;
    }
}
