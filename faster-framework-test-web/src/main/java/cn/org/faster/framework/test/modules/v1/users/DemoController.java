package cn.org.faster.framework.test.modules.v1.users;

import cn.org.faster.framework.test.modules.v1.users.entity.UserEntity;
import cn.org.faster.framework.web.secret.annotation.SecretBody;
import cn.org.faster.framework.web.secret.utils.DesCbcUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangbowen 2018/5/25 13:39
 */
@RestController
@RequestMapping("/{version}/demo")
@AllArgsConstructor
@Slf4j
@SecretBody
public class DemoController {
    private DemoService demoService;

    @GetMapping
    public IPage<UserEntity> page(UserEntity userEntity) {
        return demoService.selectPage(userEntity);
    }

    @GetMapping("/list")
    public List<UserEntity> list(UserEntity userEntity) {
        return demoService.list(userEntity);
    }

    @GetMapping("test")
    public ResponseEntity test() {
        return demoService.test();
    }

    @GetMapping("testAdd")
    public int testAdd() {
        return demoService.testAdd();
    }

    @PostMapping("secretBody")
    public int secretBody(@RequestBody UserEntity userEntity) {
        log.info("{}", userEntity);
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(DesCbcUtil.encode("{\"name\":\"张三\"}", "b2c17b46e2b1415392aab5a82869856c", "61960842"));
    }
}
