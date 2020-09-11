package cn.org.faster.sms.test.modules.smsCode;

import cn.org.faster.framework.sms.modules.sms.service.ISmsService;
import cn.org.faster.framework.sms.modules.smsCode.service.ISmsCaptchaService;
import cn.org.faster.framework.sms.spring.boot.autoconfigure.SmsProperties;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangbowen
 * @since 2018/9/4
 */
@Service
public class AliSmsCaptchaService extends ISmsCaptchaService {
    @Autowired
    private ISmsService<SendSmsRequest> smsService;

    public AliSmsCaptchaService(SmsProperties smsProperties) {
        super(smsProperties.isDebug(), smsProperties.getCaptcha().getExpire());
    }

    @Override
    protected boolean sendCode(String phone, String code) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(phone);
        return smsService.send(sendSmsRequest);
    }

}
