package cn.org.faster.framework.test.redis;

import cn.org.faster.framework.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * @author zhangbowen
 * @since 2018/10/16
 */
@Slf4j
public class SortSetTest extends BaseTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public String getBaseHeader() {
        return "";
    }

    @Test
    public void addAndRank() {
        BoundZSetOperations<String, Object> boundZSetOperations = redisTemplate.boundZSetOps("zSetTest");
        boundZSetOperations.add("v1", 3);
        boundZSetOperations.add("v2", 1);
        boundZSetOperations.add("v3", 2);
        boundZSetOperations.add("v4", 4);
        log.info("v1正序排名：{}", boundZSetOperations.rank("v1") + 1);

    }

    @Test
    public void countAndCard() {
        BoundZSetOperations<String, Object> boundZSetOperations = redisTemplate.boundZSetOps("zSetTest");
        log.info("总数：{}", boundZSetOperations.zCard());
        log.info("指定区间分数2-4：{}", boundZSetOperations.count(2, 4));
    }

    @Test
    public void increment() {
        BoundZSetOperations<String, Object> boundZSetOperations = redisTemplate.boundZSetOps("zSetTest");
        boundZSetOperations.incrementScore("v1", 10);
        log.info("v1分数增加10后，正序排名：{}", boundZSetOperations.rank("v1") + 1);
    }

    @Test
    public void interAndStore() {
        BoundZSetOperations<String, Object> boundZSetOperations = redisTemplate.boundZSetOps("zSetTest1");
        boundZSetOperations.add("v1", 1);
        boundZSetOperations.add("v2", 2);
        boundZSetOperations.intersectAndStore("zSetTest", "zSetTest2");
        BoundZSetOperations<String, Object> boundZSetOperations2 = redisTemplate.boundZSetOps("zSetTest2");
        //以zSetTest1为主，则分数以它的为主
        log.info("zSetTest和zSetTest1合并后，v1排名：{}", boundZSetOperations2.rank("v1") + 1);
        log.info("zSetTest和zSetTest1合并后，数量：{}", boundZSetOperations2.zCard());
    }

    @Test
    public void range() {
        BoundZSetOperations<String, Object> boundZSetOperations = redisTemplate.boundZSetOps("zSetTest");
        log.info("range0-2：{}", boundZSetOperations.range(0, 2));
        log.info("range score 1-2：{}", boundZSetOperations.rangeByScore(1, 2));
        log.info("reverse range 0-2：{}", boundZSetOperations.reverseRange(0, 2));
        log.info("reverse range score 0-2：{}", boundZSetOperations.reverseRangeByScore(1, 2));

        Set<ZSetOperations.TypedTuple<Object>> set = boundZSetOperations.rangeWithScores(0, boundZSetOperations.zCard());
        set.forEach(item -> {
            log.info("range with score:  value: {},score:{}", item.getValue(), item.getScore());
        });

    }

    @Test
    public void reverse() {
        BoundZSetOperations<String, Object> boundZSetOperations = redisTemplate.boundZSetOps("zSetTest");
        log.info("v1倒序排名：{}", boundZSetOperations.reverseRank("v1") + 1);
    }

    @Test
    public void score() {
        BoundZSetOperations<String, Object> boundZSetOperations = redisTemplate.boundZSetOps("zSetTest");
        log.info("v1分数：{}", boundZSetOperations.score("v1"));
    }

    @Test
    public void unionStore() {
        BoundZSetOperations<String, Object> boundZSetOperations = redisTemplate.boundZSetOps("zSetTest");
        boundZSetOperations.unionAndStore("zSetTest1", "zSetTest3");
        redisTemplate.opsForZSet().range("zSetTest3", 0, 10).forEach(System.out::println);
    }


}
