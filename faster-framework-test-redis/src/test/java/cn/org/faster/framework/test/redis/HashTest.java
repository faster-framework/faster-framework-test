package cn.org.faster.framework.test.redis;

import cn.org.faster.framework.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhangbowen
 * @since 2018/10/10
 */
@Slf4j
public class HashTest extends BaseTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public String getBaseHeader() {
        return "";
    }

    @Test
    public void getSetTest() {
        log.info("hash get set测试开始");
        log.info("设置key为hashTest，key为key1,value为hello");
        BoundHashOperations<String,String,Object> boundHashOperations = redisTemplate.boundHashOps("hashTest");
        boundHashOperations.put("key1","hello");
        log.info("设置完成");
        log.info("获取key为hashTest的值,{}", boundHashOperations.entries());
    }
    @Test
    public void del() {
        log.info("hash del测试开始");
        BoundHashOperations<String,String,Object> boundHashOperations = redisTemplate.boundHashOps("hashTest");
        log.info("删除hashTest中的key1");
        boundHashOperations.delete("key1");
        log.info("获取key为hashTest的值,{}", boundHashOperations.entries());
        log.info("删除hashTest");
        redisTemplate.delete("hashTest");
        log.info("是否还存在hashTest,{}", redisTemplate.hasKey("hashTest"));
    }
}
