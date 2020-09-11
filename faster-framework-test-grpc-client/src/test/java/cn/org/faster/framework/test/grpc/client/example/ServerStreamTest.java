package cn.org.faster.framework.test.grpc.client.example;

import cn.org.faster.framework.grpc.core.annotation.GRpcMethod;
import cn.org.faster.framework.test.grpc.DataFactory;
import cn.org.faster.framework.test.grpc.client.BaseTest;
import cn.org.faster.framework.test.grpc.model.TestData;
import cn.org.faster.framework.test.grpc.service.ServerStreamService;
import io.grpc.MethodDescriptor;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@Slf4j
public class ServerStreamTest extends BaseTest {
    @Autowired
    private ServerStreamService serverStream;

    @Test
    public void testInt() {
        Iterator<Integer> result = serverStream.testInt(1);
        result.forEachRemaining(item -> {
            log.info("返回数据:{}", item);
        });
    }

    @Test
    public void testModel() {
        Iterator<TestData> result = serverStream.testModel(DataFactory.buildModel());
        result.forEachRemaining(item -> {
            log.info("返回数据:{}", item);
        });
    }

    @Test
    public void testModelList() {
        Iterator<List<TestData>> result = serverStream.testModelList(DataFactory.buildModelList());
        result.forEachRemaining(item -> {
            log.info("返回数据:{}", item);
        });
    }

    @Test
    public void testMapList() {
        Iterator<List<Map<String, Object>>> result = serverStream.testMapList(DataFactory.buildMapList());
        result.forEachRemaining(item -> {
            log.info("返回数据:{}", item);
        });


    }

    @Test
    public void testModelMap() {
        Iterator<Map<String, Object>> result = serverStream.testModelMap(DataFactory.buildModelMap());
        result.forEachRemaining(item -> {
            log.info("返回数据:{}", item);
        });
    }

    @Test
    public void testMap() {
        Iterator<Map<String, Object>> result = serverStream.testMap(DataFactory.buildMap());
        result.forEachRemaining(item -> {
            log.info("返回数据:{}", item);
        });
    }

    @Test
    public void testListMap() {
        Iterator<Map<String, List<Object>>> result = serverStream.testListMap(DataFactory.buildListMap());
        result.forEachRemaining(item -> {
            log.info("返回数据:{}", item);
        });
    }
    @Test
    public void testIntEmptyParam() {
        Iterator<Integer> result = serverStream.testIntEmptyParam();
        result.forEachRemaining(item -> {
            log.info("返回数据:{}", item);
        });
    }
    @Test
    public void all(){
        testInt();
        testIntEmptyParam();
        testListMap();
        testMap();
        testMapList();
        testModel();
        testModelList();
        testModelMap();
    }

}
