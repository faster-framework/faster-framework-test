package cn.org.faster.framework.test.redis;

import cn.org.faster.framework.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhangbowen
 * @since 2018/10/18
 */
@Slf4j
public class HyperLogLogTest extends BaseTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getBaseHeader() {
        return "";
    }

    @Test
    public void pfadd() {
        //我们可以使用此方式，统计平台用户数，并且判断当前ip是否存在。已存在的value，add后返回0，成功插入返回1
        log.info("hyper add : {} ", redisTemplate.opsForHyperLogLog().add("hyper1", "192.168.0.1", "192.168.0.2", "192.168.0.3"));
        log.info("hyper size : {} ", redisTemplate.opsForHyperLogLog().size("hyper1"));
    }

    @Test
    public void pfMerge() {
        log.info("hyper add : {} ", redisTemplate.opsForHyperLogLog().add("hyper2", "192.168.0.1", "192.168.0.2", "192.168.0.4"));
        redisTemplate.opsForHyperLogLog().union("hyper3", "hyper1", "hyper2");
        log.info("hyper3 size : {} ", redisTemplate.opsForHyperLogLog().size("hyper3"));
    }
}
