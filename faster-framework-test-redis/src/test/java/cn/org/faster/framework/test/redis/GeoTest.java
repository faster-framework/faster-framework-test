package cn.org.faster.framework.test.redis;

import cn.org.faster.framework.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhangbowen
 * @since 2018/10/18
 * geo真实在在redis中存储sort set类型
 */
@Slf4j
public class GeoTest extends BaseTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getBaseHeader() {
        return "";
    }

    @Test
    public void geoAdd() {
        redisTemplate.opsForGeo().add("geo1", new RedisGeoCommands.GeoLocation<>("test", new Point(118.803805, 32.060168)));
        log.info("{}", redisTemplate.opsForGeo().position("geo1", "test"));
        log.info("type: {}", redisTemplate.type("geo1"));

        redisTemplate.opsForGeo().add("geo2", new Point(118.803805, 32.060168), "test");
        log.info("{}", redisTemplate.opsForGeo().position("geo2", "test"));
        log.info("type: {}", redisTemplate.type("geo2"));
    }

    @Test
    public void geoRadius() {
        log.info("{}", redisTemplate.opsForGeo().radius("geo1", new Circle(new Point(118.6038, 32.05016), new Distance(10000000, Metrics.KILOMETERS)), RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance()));

    }
    @Test
    public void geoDist() {
        log.info("{}", redisTemplate.opsForGeo().distance("geo1", "xxxx","test"));

    }
}
