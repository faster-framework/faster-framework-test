package cn.org.faster.framework.test.grpc.api;

import cn.org.faster.framework.grpc.core.annotation.GRpcMethod;
import cn.org.faster.framework.grpc.server.annotation.GRpcApi;
import cn.org.faster.framework.test.grpc.DataFactory;
import cn.org.faster.framework.test.grpc.TestData;
import io.grpc.MethodDescriptor;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@GRpcApi("server-stream")
@Slf4j
public class ServerStreamService {

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    public void testInt(int data, StreamObserver<Integer> response) {
        log.info("请求数据:{}", data);
        response.onNext(1);
        response.onNext(2);
        response.onCompleted();
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    public void testModel(TestData data, StreamObserver<TestData> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildModel());
        response.onNext(DataFactory.buildModel());
        response.onCompleted();
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    public void testModelList(List<TestData> data, StreamObserver<List<TestData>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildModelList());
        response.onNext(DataFactory.buildModelList());
        response.onCompleted();
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    public void testMapList(List<Map<String, Object>> data, StreamObserver<List<Map<String, Object>>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildMapList());
        response.onNext(DataFactory.buildMapList());
        response.onCompleted();
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    public void testModelMap(Map<String, Object> data, StreamObserver<Map<String, Object>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildModelMap());
        response.onNext(DataFactory.buildModelMap());
        response.onCompleted();
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    public void testMap(Map<String, Object> data, StreamObserver<Map<String, Object>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildMap());
        response.onNext(DataFactory.buildMap());
        response.onCompleted();
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    public void testListMap(Map<String, List<Object>> data, StreamObserver<Map<String, List<Object>>> response) {
        log.info("请求数据:{}", data);
        response.onNext(DataFactory.buildListMap());
        response.onNext(DataFactory.buildListMap());
        response.onCompleted();
    }


    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    public void testIntEmptyParam(StreamObserver<Integer> streamObserver) throws InterruptedException {
        log.info("空参数，存在返回值");
        streamObserver.onNext(1);
        streamObserver.onNext(2);
        streamObserver.onNext(3);
        streamObserver.onCompleted();
    }
}
