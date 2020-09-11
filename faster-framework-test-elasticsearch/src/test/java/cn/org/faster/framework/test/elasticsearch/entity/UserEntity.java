package cn.org.faster.framework.test.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author zhangbowen
 * @since 2019/3/8
 */
@Document(indexName = "user", type = "_doc")
@Data
public class UserEntity {
    @Id
    private String phone;
    private String name;
}
