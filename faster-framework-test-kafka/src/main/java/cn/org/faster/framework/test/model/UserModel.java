package cn.org.faster.framework.test.model;

import lombok.Data;
import org.springframework.kafka.annotation.EnableKafka;

import javax.validation.constraints.NotNull;

/**
 * @author zhangbowen
 * @since 2018/12/24
 */
@Data
@EnableKafka
public class UserModel {
    @NotNull
    private String id;
    @NotNull
    private String name;
}
