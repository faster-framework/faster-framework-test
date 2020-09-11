package cn.org.faster.framework.test.showdoc.service;

import cn.org.faster.framework.test.showdoc.bean.GitLabPushEvent;
import cn.org.faster.framework.test.showdoc.bean.MdFileBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

/**
 * @author zhangbowen
 * @since 2019/4/11
 */
@Component
public class GitCodePullTask {
    @Autowired
    private RestTemplate restTemplate;

    public List<MdFileBean> pullGitCode(GitLabPushEvent gitLabPushEvent) throws IOException {
        List<MdFileBean> resultFilePath = new ArrayList<>();
        String homeUrl = gitLabPushEvent.getProject().getHomepage();
        String ref = gitLabPushEvent.getRef().replace("refs/heads/", "");
        String zipUrl = homeUrl + "/repository/archive.zip?ref=" + ref;
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(zipUrl, byte[].class);
        byte[] bytes = responseEntity.getBody();
        if (bytes == null) {
            return null;
        }
        responseEntity.getBody();
        String fileDirPath = "/Users/zhangbowen/Documents/" + Timestamp.valueOf(LocalDateTime.now()).getTime();
        String zipFileName = fileDirPath + ".zip";
        Path path = Paths.get(zipFileName);

        Files.write(path, bytes);
        ZipFile zipFile = new ZipFile(zipFileName);
        zipFile.stream().forEach(entry -> {
            try {
                if (entry.isDirectory()) {
                    return;
                }
                String zipEntryName = entry.getName();
                zipEntryName = zipEntryName.substring(zipEntryName.indexOf("/") + 1);
                InputStream in = zipFile.getInputStream(entry);
                //指定解压后的文件夹+当前zip文件的名称
                String outPath = (fileDirPath + "/" + zipEntryName);
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(StreamUtils.copyToByteArray(zipFile.getInputStream(entry)));
                in.close();
                fileOutputStream.close();
                MdFileBean mdFileBean = new MdFileBean();
                mdFileBean.setPath(outPath);
                mdFileBean.setFileName(file.getName());
                mdFileBean.setParentName(ref + "/" + zipEntryName.substring(0, zipEntryName.lastIndexOf("/")));
                resultFilePath.add(mdFileBean);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return resultFilePath;
    }
}
