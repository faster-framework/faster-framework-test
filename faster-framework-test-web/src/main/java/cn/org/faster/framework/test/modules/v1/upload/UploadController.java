package cn.org.faster.framework.test.modules.v1.upload;

import cn.org.faster.framework.web.upload.controller.AbstractUploadController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbowen 2018/6/12 15:40
 */
@RestController
@RequestMapping("/{version}")
public class UploadController extends AbstractUploadController {
}
