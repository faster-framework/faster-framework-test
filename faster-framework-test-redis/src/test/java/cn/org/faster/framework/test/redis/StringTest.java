package cn.org.faster.framework.test.redis;

import cn.org.faster.framework.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author zhangbowen
 * @since 2018/10/10
 */
@Slf4j
public class StringTest extends BaseTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getBaseHeader() {
        return "";
    }

    @Test
    public void getSetTest() {
        log.info("string get set测试开始");
        log.info("设置key为stringTest，value为hello");
        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps("stringTest");
        boundValueOperations.set("hello");
        log.info("设置完成");
        log.info("获取key为stringTest的值,{}", boundValueOperations.get());
        log.info("获取key为stringTest的旧值为{}，并设置新值为new", boundValueOperations.getAndSet("new"));
        log.info("获取key为stringTest的新值,{}", boundValueOperations.get());
    }

    @Test
    public void appendTest() {
        log.info("string append 测试开始");
        log.info("为stringTest增加appendStr");
        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps("stringTest");
        boundValueOperations.set("hello");
        boundValueOperations.append("appendStr");
        log.info("获取key为stringTest的值,{}", boundValueOperations.get());
    }

    @Test
    public void setIfAbsentTest() {
        log.info("string setnx(不存在才设置) 测试开始");
        log.info("如果stringTest不存在，设置值为hello");
        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps("stringTest");
        boundValueOperations.setIfAbsent("hello");
        log.info("获取key为stringTest的值,{}", boundValueOperations.get());
    }

    @Test
    public void incrementTest() {
        log.info("string increment 测试开始");
        log.info("新建stringIncrementTest值为1");
        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps("stringIncrementTest");
        boundValueOperations.set("1");
        log.info("stringIncrementTest增加1000");
        boundValueOperations.increment(1000);
        log.info("获取key为stringIncrementTest的值,{}", boundValueOperations.get());
    }

    @Test
    public void delTest() {
        log.info("string del 测试开始");
        log.info("删除stringTest和stringIncrementTest");
        stringRedisTemplate.delete("stringTest");
        stringRedisTemplate.delete("stringIncrementTest");
        log.info("获取key为stringIncrementTest的值,{}", stringRedisTemplate.opsForValue().get("stringIncrementTest"));
        log.info("获取key为stringTest的值,{}", stringRedisTemplate.opsForValue().get("stringTest"));
    }
}
