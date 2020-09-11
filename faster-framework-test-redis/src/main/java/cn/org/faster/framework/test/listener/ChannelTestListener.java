package cn.org.faster.framework.test.listener;


import cn.org.faster.framework.redis.annotation.RedisListener;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangbowen
 * @since 2018/10/19
 */
@Component
public class ChannelTestListener {
    @RedisListener("channel1")
    public void onMessage(String s) {
        System.out.println(s);
    }
    @RedisListener("channel1")
    public void onMessage2(String s) {
        System.out.println(s);
    }
    @RedisListener("channel3")
    public void onMessage3(String s) {
        System.out.println(s);
    }
}
