package cn.org.faster.framework.test.showdoc.service;

import cn.org.faster.framework.test.showdoc.bean.GitLabToken;
import cn.org.faster.framework.test.showdoc.bean.MdFileBean;
import cn.org.faster.framework.test.showdoc.bean.ShowDocPushBean;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangbowen
 * @since 2019/4/11
 */
@Component
public class ShowDocPushTask {
    @Autowired
    private RestTemplate restTemplate;
    private static final String url = "http://localhost:4999/server/index.php?s=/api/item/updateByApi";

    public void pushMd(List<MdFileBean> filePathList, GitLabToken gitLabToken) throws IOException {

        filePathList.forEach(item -> {
            try {
                Path path = Paths.get(item.getPath());
                String content = Files.lines(path).collect(Collectors.joining("\n"));
                String title = item.getFileName().substring(0, item.getFileName().lastIndexOf("."));
                ShowDocPushBean showDocPushBean = new ShowDocPushBean();
                showDocPushBean.setApi_token(gitLabToken.getApiToken());
                showDocPushBean.setApi_key(gitLabToken.getApiKey());
                showDocPushBean.setPage_title(title);
                showDocPushBean.setCat_name(item.getParentName());
                showDocPushBean.setPage_content(content);
                ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, JSON.toJSON(showDocPushBean), String.class);
                System.out.println(responseEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
