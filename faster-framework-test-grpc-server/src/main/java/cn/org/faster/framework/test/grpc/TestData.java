package cn.org.faster.framework.test.grpc;

import lombok.Data;

import java.util.List;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@Data
public class TestData {
    private String msg;
    private List<TestData> list;


}
