package cn.org.faster.framework.test.grpc.client.example;

import cn.org.faster.framework.test.grpc.DataFactory;
import cn.org.faster.framework.test.grpc.client.BaseTest;
import cn.org.faster.framework.test.grpc.model.TestData;
import cn.org.faster.framework.test.grpc.service.UnaryService;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@Slf4j
public class UnaryTest extends BaseTest {
    @Autowired
    private UnaryService unaryService;

    @Test
    public void testInt() {
        int result = unaryService.testInt(1);
        log.info("返回数据:{}", result);
    }

    @Test
    public void testModel() {
        TestData result = unaryService.testModel(DataFactory.buildModel());
        log.info("返回数据:{}", result);
    }

    @Test
    public void testModelList() {
        List<TestData> result = unaryService.testModelList(DataFactory.buildModelList());
        log.info("返回数据:{}", result);
    }

    @Test
    public void testMapList() {
        List<Map<String, Object>> result = unaryService.testMapList(DataFactory.buildMapList());
        result.forEach(item -> {
            log.info("返回数据:{}", item.get("hello"));
            log.info("返回数据:{}", item.get("key"));
        });

    }

    @Test
    public void testModelMap() {
        Map<String, Object> result = unaryService.testModelMap(DataFactory.buildModelMap());
        log.info("返回数据:{}", result);
    }

    @Test
    public void testMap() {
        Map<String, Object> result = unaryService.testMap(DataFactory.buildMap());
        log.info("返回数据:{}", result);
    }

    @Test
    public void testListMap() {
        Map<String, List<Object>> result = unaryService.testListMap(DataFactory.buildListMap());
        log.info("返回数据:{}", result);
    }

    @Test
    public void testListenableFuture() throws ExecutionException, InterruptedException {
        ListenableFuture<Integer> listenableFuture = unaryService.testListenableFuture(1);
        int result = listenableFuture.get();
        log.info("返回数据:{}", result);
    }

    @Test
    public void testStreamObserver() throws InterruptedException {
        unaryService.testStreamObserver(1, new StreamObserver<Integer>() {
            @Override
            public void onNext(Integer value) {
                log.info("返回数据:{}", value);
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {
            }
        });
        Thread.sleep(10000);
    }

    @Test
    public void testIntEmpty() {
        unaryService.testIntEmpty();
    }

    @Test
    public void testIntEmptyReturn() {
        unaryService.testIntEmptyReturn(20);
    }

    @Test
    public void testIntEmptyParam() {
        int result = unaryService.testIntEmptyParam();
        log.info("返回数据:{}", result);
    }

    @Test
    public void all() throws ExecutionException, InterruptedException {
        testInt();
        testIntEmpty();
        testIntEmptyParam();
        testIntEmptyReturn();
        testListenableFuture();
        testListMap();
        testMap();
        testMapList();
        testModel();
        testModelList();
        testModelMap();
        testStreamObserver();
    }
}
