package cn.org.faster.framework.admin.basic.controller;

import cn.org.faster.framework.web.upload.controller.AbstractUploadController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin", ""})
public class UploadController extends AbstractUploadController {
}
