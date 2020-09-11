package cn.org.faster.framework.admin.basic.controller;

import cn.org.faster.framework.web.captcha.controller.AbstractCaptchaController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin", ""})
public class CaptchaController extends AbstractCaptchaController {
}
