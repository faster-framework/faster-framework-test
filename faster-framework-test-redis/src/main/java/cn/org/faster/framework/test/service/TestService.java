//package cn.org.faster.framework.test.service;
//
//import cn.org.faster.framework.test.service.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @author zhangbowen
// * @since 2019/1/11
// */
//@Service
//@Transactional
//public class TestService {
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    public int testAdd() {
//        redisTemplate.convertAndSend("channel1", "呵呵哒");
//        redisTemplate.opsForValue().set("a", "heihei");
//        userMapper.deleteById(1042352402690592770L);
////        System.out.println(1 / 0);
//        return 0;
//    }
//}
