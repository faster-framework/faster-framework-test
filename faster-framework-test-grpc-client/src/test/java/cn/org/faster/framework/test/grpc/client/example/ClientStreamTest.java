package cn.org.faster.framework.test.grpc.client.example;

import cn.org.faster.framework.test.grpc.DataFactory;
import cn.org.faster.framework.test.grpc.client.BaseTest;
import cn.org.faster.framework.test.grpc.model.TestData;
import cn.org.faster.framework.test.grpc.service.ClientStreamService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@Slf4j
public class ClientStreamTest extends BaseTest {
    @Autowired
    private ClientStreamService clientStreamService;

    @Test
    public void testInt() throws InterruptedException {
        StreamObserver<Integer> request = clientStreamService.testInt(new StreamObserver<Integer>() {
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
        request.onNext(1);
        request.onNext(2);
        request.onCompleted();
        Thread.sleep(10000);
    }

    @Test
    public void testModel() throws InterruptedException {
        StreamObserver<TestData> request = clientStreamService.testModel(new StreamObserver<TestData>() {
            @Override
            public void onNext(TestData value) {
                log.info("返回数据:{}", value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
        request.onNext(DataFactory.buildModel());
        request.onNext(DataFactory.buildModel());
        request.onCompleted();
        Thread.sleep(10000);
    }

    @Test
    public void testModelList() throws InterruptedException {
        StreamObserver<List<TestData>> request = clientStreamService.testModelList(new StreamObserver<List<TestData>>() {
            @Override
            public void onNext(List<TestData> value) {
                log.info("返回数据:{}", value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
        request.onNext(DataFactory.buildModelList());
        request.onNext(DataFactory.buildModelList());
        request.onCompleted();
        Thread.sleep(10000);
    }

    @Test
    public void testMapList() throws InterruptedException {
        StreamObserver<List<Map<String, Object>>> request = clientStreamService.testMapList(new StreamObserver<List<Map<String, Object>>>() {
            @Override
            public void onNext(List<Map<String, Object>> value) {
                log.info("返回数据:{}", value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
        request.onNext(DataFactory.buildMapList());
        request.onNext(DataFactory.buildMapList());
        request.onCompleted();
        Thread.sleep(10000);
    }

    @Test
    public void testModelMap() throws InterruptedException {
        StreamObserver<Map<String, Object>> request = clientStreamService.testModelMap(new StreamObserver<Map<String, Object>>() {
            @Override
            public void onNext(Map<String, Object> value) {
                log.info("返回数据:{}", value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
        request.onNext(DataFactory.buildModelMap());
        request.onNext(DataFactory.buildModelMap());
        request.onCompleted();
        Thread.sleep(10000);
    }

    @Test
    public void testMap() throws InterruptedException {
        StreamObserver<Map<String, Object>> request = clientStreamService.testMap(new StreamObserver<Map<String, Object>>() {
            @Override
            public void onNext(Map<String, Object> value) {
                log.info("返回数据:{}", value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
        request.onNext(DataFactory.buildMap());
        request.onNext(DataFactory.buildMap());
        request.onCompleted();
        Thread.sleep(10000);
    }

    @Test
    public void testListMap() throws InterruptedException {
        StreamObserver<Map<String, List<Object>>> request = clientStreamService.testListMap(new StreamObserver<Map<String, List<Object>>>() {
            @Override
            public void onNext(Map<String, List<Object>> value) {
                log.info("返回数据:{}", value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
        request.onNext(DataFactory.buildListMap());
        request.onNext(DataFactory.buildListMap());
        request.onCompleted();
        Thread.sleep(10000);
    }
    @Test
    public void all() throws InterruptedException {
        testInt();
        testListMap();
        testMap();
        testMapList();
        testModel();
        testModelList();
        testModelMap();
    }
}
