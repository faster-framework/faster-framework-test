package cn.org.faster.framework.test;

import cn.org.faster.framework.web.version.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbowen
 * @since 2019-11-13
 */
@RequestMapping("/api/{version}")
@ApiVersion(2)
@RestController
public class ApiController {
    @GetMapping("/aaa")
    public String test() {
        return "aaa";
    }
}
