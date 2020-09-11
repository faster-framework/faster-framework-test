package cn.org.faster.framework.test.showdoc.bean;

import lombok.Data;

/**
 * @author zhangbowen
 * @since 2019/4/11
 */
@Data
public class GitLabPushEvent {
    private String ref;
    private GitLabProjectBean project;
}
