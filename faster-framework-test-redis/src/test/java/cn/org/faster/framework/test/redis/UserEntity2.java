package cn.org.faster.framework.test.redis;

import lombok.Data;

import java.util.List;

/**
 * @author zhangbowen
 * @since 2019/1/11
 */
@Data
public class UserEntity2 {
    private String name;
    private UserEntity2 entity;
    private List<UserEntity2> list;
}
