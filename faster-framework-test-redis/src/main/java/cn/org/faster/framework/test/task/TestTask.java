package cn.org.faster.framework.test.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangbowen
 * @since 2019/3/6
 */
@Component
public class TestTask {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(fixedDelay = 100, initialDelay = 100)
    public void testLock() throws InterruptedException {
        Boolean success = redisTemplate.opsForValue().setIfAbsent("test_lock", "0");
        if (success == null || !success) {
            System.out.println("获取锁失败，结束定时任务");
            return;
        }
        Thread.sleep(10000);
        Boolean delSuccess = redisTemplate.delete("test_lock");
        System.out.println("删除test_lock:" + delSuccess);
    }
}
