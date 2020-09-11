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
@GRpcApi("bidi-stream")
@Slf4j
public class BidiStreamService {

    @GRpcMethod(type = MethodDescriptor.MethodType.BIDI_STREAMING)
    public StreamObserver<Integer> testInt(StreamObserver<Integer> response) {
        return new StreamObserver<Integer>() {
            @Override
            public void onNext(Integer value) {
                log.info("请求数据:{}", value);
                response.onNext(1);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                response.onCompleted();
            }
        };
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.BIDI_STREAMING)
    public StreamObserver<TestData> testModel(StreamObserver<TestData> response) {
        return new StreamObserver<TestData>() {
            @Override
            public void onNext(TestData value) {
                log.info("请求数据:{}", value);
                response.onNext(DataFactory.buildModel());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                response.onCompleted();
            }
        };
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.BIDI_STREAMING)
    public StreamObserver<List<TestData>> testModelList(StreamObserver<List<TestData>> response) {
        return new StreamObserver<List<TestData>>() {
            @Override
            public void onNext(List<TestData> value) {
                log.info("请求数据:{}", value);
                response.onNext(DataFactory.buildModelList());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                response.onCompleted();
            }
        };
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.BIDI_STREAMING)
    public StreamObserver<List<Map<String, Object>>> testMapList(StreamObserver<List<Map<String, Object>>> response) {
        return new StreamObserver<List<Map<String, Object>>>() {
            @Override
            public void onNext(List<Map<String, Object>> value) {
                log.info("请求数据:{}", value);
                response.onNext(DataFactory.buildMapList());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                response.onCompleted();
            }
        };
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.BIDI_STREAMING)
    public StreamObserver<Map<String, Object>> testModelMap(StreamObserver<Map<String, Object>> response) {
        return new StreamObserver<Map<String, Object>>() {
            @Override
            public void onNext(Map<String, Object> value) {
                log.info("请求数据:{}", value);
                response.onNext(DataFactory.buildModelMap());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                response.onCompleted();
            }
        };
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.BIDI_STREAMING)
    public StreamObserver<Map<String, Object>> testMap(StreamObserver<Map<String, Object>> response) {
        return new StreamObserver<Map<String, Object>>() {
            @Override
            public void onNext(Map<String, Object> value) {
                log.info("请求数据:{}", value);
                response.onNext(DataFactory.buildMap());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                response.onCompleted();
            }
        };
    }

    @GRpcMethod(type = MethodDescriptor.MethodType.BIDI_STREAMING)
    public StreamObserver<Map<String, List<Object>>> testListMap(StreamObserver<Map<String, List<Object>>> response) {
        return new StreamObserver<Map<String, List<Object>>>() {
            @Override
            public void onNext(Map<String, List<Object>> value) {
                log.info("请求数据:{}", value);
                response.onNext(DataFactory.buildListMap());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                response.onCompleted();
            }
        };
    }
}
