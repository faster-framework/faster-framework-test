package cn.org.faster.framework.test.modules.v1.users;

import cn.hutool.core.bean.BeanUtil;
import cn.org.faster.framework.test.modules.v1.users.entity.UserEntity;
import cn.org.faster.framework.test.modules.v1.users.mapper.UserMapper;
import cn.org.faster.framework.test.modules.v2.TestBean;
import cn.org.faster.framework.web.exception.BusinessException;
import cn.org.faster.framework.web.exception.model.BasicErrorCode;
import cn.org.faster.framework.web.exception.model.ResponseErrorEntity;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author zhangbowen
 * @date 2018/5/25 13:46
 */
@Service
@AllArgsConstructor
public class DemoService extends ServiceImpl {

    private RestTemplate restTemplate;
    @Autowired
    private UserMapper userMapper;

    public IPage<UserEntity> selectPage(UserEntity userEntity) {
        IPage<UserEntity> list = userMapper.selectPage(userEntity.toPage(), null);
        BasicErrorCode.DISCARD_ERROR.throwException();
        return list;
    }

    public ResponseEntity<String> test() {
        return restTemplate.getForEntity("xxx", String.class);
    }

    public List<UserEntity> list(UserEntity userEntity) {
        return userMapper.selectList(null);
    }

    public int testAdd() {


        return userMapper.deleteById(1042352402690592770L);
    }

    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random(101);

//        for(int i = 0 ; i < 5;i++){
//            System.out.println(rand.nextInt(5));
//        }
//        Random rand2 = new Random(10);
//        for(int i = 0 ; i < 5;i++){
//            System.out.println(rand2.nextInt(5));
//        }
        List<TestBean> list = new ArrayList<>();
//        for (int i = 0; i < 100000; i++) {
//            list.add(new TestBean(i, Math.random() + ""));
//        }
//
        list.sort(new Comparator<TestBean>() {
            @Override
            public int compare(TestBean o1, TestBean o2) {
                return rand.nextInt();
            }
        });
//
//        list.forEach(System.out::println);
    }
}
