package cn.org.faster.framework.test.grpc.api;

import cn.org.faster.framework.grpc.core.annotation.GRpcMethod;
import cn.org.faster.framework.grpc.server.annotation.GRpcApi;
import cn.org.faster.framework.test.grpc.DataFactory;
import cn.org.faster.framework.test.grpc.TestData;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@GRpcApi("unary")
@Slf4j
public class UnaryService {

    @GRpcMethod
    public void testInt(int data, StreamObserver<Integer> response) {
        log.info("请求数据:{}", data);
        response.onNext(1);
        response.onCompleted();
    }
    @GRpcMethod
    public void testListenableFuture(int data, StreamObserver<Integer> response) {
        log.info("请求数据:{}", data);
        response.onNext(1);
        response.onCompleted();
    }


    @GRpcMethod
    public void testModel(TestData data, StreamObserver<TestData> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildModel());
        response.onCompleted();
    }

    @GRpcMethod
    public void testModelList(List<TestData> data, StreamObserver<List<TestData>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildModelList());
        response.onCompleted();
    }

    @GRpcMethod
    public void testMapList(List<Map<String, Object>> data, StreamObserver<List<Map<String, Object>>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildMapList());
        response.onCompleted();
    }

    @GRpcMethod
    public void testModelMap(Map<String, Object> data, StreamObserver<Map<String, Object>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildModelMap());
        response.onCompleted();
    }

    @GRpcMethod
    public void testMap(Map<String, Object> data, StreamObserver<Map<String, Object>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildMap());
        response.onCompleted();
    }

    @GRpcMethod
    public void testListMap(Map<String, List<Object>> data, StreamObserver<Map<String, List<Object>>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildListMap());
        response.onCompleted();
    }

    @GRpcMethod
    public void testIntEmpty() {
        log.info("空请求，空返回值");
    }

    @GRpcMethod
    public void testIntEmptyReturn(int data) {
        log.info("空返回值，请求数据:{}", data);
    }

    @GRpcMethod
    public void testIntEmptyParam(StreamObserver<Integer> streamObserver) {
        log.info("空参数，存在返回值");
        streamObserver.onNext(3);
        streamObserver.onCompleted();
    }
}
