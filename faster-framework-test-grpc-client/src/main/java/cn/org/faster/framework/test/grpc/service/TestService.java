package cn.org.faster.framework.test.grpc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhangbowen
 * @since 2019-11-15
 */
@Service
public class TestService {
    @Autowired
    private UnaryService unaryService;

    public void test(){
        unaryService.testInt(1);
    }
}
