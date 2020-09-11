package cn.org.faster.framework.test.showdoc.bean;

import lombok.Data;

/**
 * @author zhangbowen
 * @since 2019/4/11
 */
@Data
public class ShowDocPushBean {
    private String api_key;
    private String api_token;
    private String cat_name;
    private String page_title;
    private String page_content;
}
