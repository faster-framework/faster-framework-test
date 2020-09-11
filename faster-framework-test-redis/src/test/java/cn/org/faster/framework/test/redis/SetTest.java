package cn.org.faster.framework.test.redis;

import cn.org.faster.framework.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangbowen
 * @since 2018/10/15
 */
@Slf4j
public class SetTest extends BaseTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getBaseHeader() {
        return "";
    }

    @Test
    public void addPopTest() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet");
        setOperations.add("testSet", "12", 1221);
        log.info("获取数据：{}", setOperations.members());
        // size == scard
        log.info("成员总数：{}}", setOperations.size());
    }

    @Test
    public void isMember() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet");
        log.info("isMember:{}", setOperations.isMember("12"));
    }

    @Test
    public void diff() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet");
        BoundSetOperations<String, Object> setOperations2 = redisTemplate.boundSetOps("testSet2");
        setOperations2.add("aaa", "12", "12", 1221);
        setOperations.diffAndStore("testSet2", "testSet3");
        BoundSetOperations<String, Object> setOperations3 = redisTemplate.boundSetOps("testSet3");
        log.info("members:{}", setOperations3.members());
    }

    @Test
    public void inter() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet");
        setOperations.intersectAndStore("testSet2", "testSet4");
        BoundSetOperations<String, Object> setOperations4 = redisTemplate.boundSetOps("testSet4");
        log.info("members:{}", setOperations4.members());
    }

    @Test
    public void move() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet");
        setOperations.move("testSet5", "12");
        BoundSetOperations<String, Object> setOperations5 = redisTemplate.boundSetOps("testSet5");
        log.info("members:{}", setOperations5.members());
    }

    @Test
    public void random() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet");
        log.info("random one :{}", setOperations.randomMember());
        for (int i = 0; i < 50; i++) {
            //会重复 count == 负数
            log.info("random many:{}", setOperations.randomMembers(2));
            //不会重复 count == 正数
            log.info("distinctRandomMembers many:{}", setOperations.distinctRandomMembers(2));
        }
    }

    @Test
    public void rem() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet5");
        log.info("testSet5:{}", setOperations.members());
        setOperations.remove("12");
        log.info("testSet5:{}", setOperations.members());
    }

    @Test
    public void union() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet1");
        List<String> unionKeys = new ArrayList<>();
        unionKeys.add("testSet2");
        unionKeys.add("testSet3");
        unionKeys.add("testSet4");
        unionKeys.add("testSet5");
        setOperations.unionAndStore(unionKeys, "testSet6");
        BoundSetOperations<String, Object> setOperations6 = redisTemplate.boundSetOps("testSet6");
        log.info("testSet6:{}", setOperations6.members());
    }

    @Test
    public void scan() {
        BoundSetOperations<String, Object> setOperations = redisTemplate.boundSetOps("testSet6");
        ScanOptions scanOptions = ScanOptions.scanOptions().match("*").count(10).build();
        Cursor cursor = setOperations.scan(scanOptions);
        while (cursor.hasNext()) {
            log.info("data:{}", cursor.next());
        }
    }
}
