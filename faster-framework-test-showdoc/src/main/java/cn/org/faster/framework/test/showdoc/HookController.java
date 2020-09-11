package cn.org.faster.framework.test.showdoc;

import cn.org.faster.framework.test.showdoc.bean.GitLabPushEvent;
import cn.org.faster.framework.test.showdoc.bean.GitLabToken;
import cn.org.faster.framework.test.showdoc.bean.MdFileBean;
import cn.org.faster.framework.test.showdoc.service.GitCodePullTask;
import cn.org.faster.framework.test.showdoc.service.ShowDocPushTask;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author zhangbowen
 * @since 2019/4/11
 */
@RequestMapping("/showdoc/hook")
@RestController
public class HookController {
    @Autowired
    private GitCodePullTask gitCodePullTask;
    @Autowired
    private ShowDocPushTask showDocPushTask;


    @PostMapping
    public String test(@RequestBody GitLabPushEvent ref, @RequestHeader("X-Gitlab-Token") String token) throws IOException {
        System.out.println(ref);
        System.out.println(token);
        //拉取git代码
        List<MdFileBean> filePathList = gitCodePullTask.pullGitCode(ref);
        //提交至showdoc
        GitLabToken gitLabToken = JSON.parseObject(token, GitLabToken.class);
        showDocPushTask.pushMd(filePathList, gitLabToken);
        return "";
    }
}
