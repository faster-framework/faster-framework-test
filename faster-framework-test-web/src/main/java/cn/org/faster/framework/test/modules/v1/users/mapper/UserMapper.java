package cn.org.faster.framework.test.modules.v1.users.mapper;

import cn.org.faster.framework.test.modules.v1.users.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author zhangbowen
 * @date 2018/5/25 15:46
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    List<UserEntity> test();
}
