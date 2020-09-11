package cn.org.faster.framework.test.grpc.controller;

import cn.org.faster.framework.test.grpc.service.TestService;
import cn.org.faster.framework.test.grpc.service.UnaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbowen
 * @since 2019/4/3
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("/test/exception")
    public String test(){
        testService.test();
        return "success";
    }
}
