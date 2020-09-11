package cn.org.faster.framework.test.redis;

import cn.org.faster.framework.test.BaseTest;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangbowen
 * @since 2018/10/15
 */
@Slf4j
public class ListTest extends BaseTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String getBaseHeader() {
        return "";
    }

    @Test
    public void pushPopTest() {
        log.info("list pop push测试开始");
        log.info("设置key为listTest。push开始");
        BoundListOperations<String,String> boundListOperations = redisTemplate.boundListOps("listTest");
        for (int i = 0; i < 10; i++) {
            boundListOperations.leftPush(i+"");
        }
        log.info("push完成");
        log.info("查看结果");
        for (long i = 0; i < boundListOperations.size(); i++) {
            log.info(boundListOperations.index(i).toString());
        }
    }

    @Test
    public void trimTest(){
        BoundListOperations<String, String> boundListOperations = redisTemplate.boundListOps("listTest");
        boundListOperations.trim(0, 5);
        boundListOperations.range(0, 100).forEach(item -> {
            log.info(item.toString());
        });
    }

    /**
     * == lrem
     */
    @Test
    public void removeTest(){
        BoundListOperations<String, String> boundListOperations = redisTemplate.boundListOps("listTest");
        boundListOperations.range(0, 100).forEach(item -> {
            log.info(item.toString());
        });
        //count：正为从左到右count个，负从右到左count个，0为全部匹配
        boundListOperations.remove(0, 1);
        boundListOperations.range(0, 100).forEach(item -> {
            log.info(item.toString());
        });
    }
}
