package cn.org.faster.framework.test.modules.v1.captcha;

import cn.org.faster.framework.web.captcha.controller.AbstractCaptchaController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController extends AbstractCaptchaController {

    @GetMapping("/valid/captcha")
    private boolean valid(String captcha, String token) {
        return super.captchaService.valid(captcha, token);
    }
}
