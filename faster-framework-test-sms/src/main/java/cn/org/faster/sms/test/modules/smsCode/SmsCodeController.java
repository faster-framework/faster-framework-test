package cn.org.faster.sms.test.modules.smsCode;

import cn.org.faster.framework.sms.modules.smsCode.service.ISmsCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbowen
 * @since 2018/9/3
 */
@RestController
@RequestMapping("/smsCode")
public class SmsCodeController {
    @Autowired
    private ISmsCaptchaService smsCaptchaService;
    @GetMapping
    public ResponseEntity send() {
        smsCaptchaService.send("18764050615", "reg");
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
