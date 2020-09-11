package cn.org.faster.framework.test.redis;

import cn.org.faster.framework.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhangbowen
 * @since 2018/10/19
 */
public class TransactionalTest extends BaseTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public String getBaseHeader() {
        return "";
    }

    @Test
    public void multiAndExec(){
        redisTemplate.multi();
        redisTemplate.delete("test2");
        redisTemplate.boundValueOps("test3").set("11111");
        redisTemplate.exec();
    }
}
