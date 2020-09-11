package cn.org.faster.framework.test.grpc.service;

import cn.org.faster.framework.grpc.client.annotation.GRpcService;
import cn.org.faster.framework.grpc.core.annotation.GRpcMethod;
import cn.org.faster.framework.test.grpc.model.TestData;
import io.grpc.MethodDescriptor;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@GRpcService(value = "test-client-stream", scheme = "client-stream")
public interface ClientStreamService {

    @GRpcMethod(type = MethodDescriptor.MethodType.CLIENT_STREAMING)
    StreamObserver<Integer> testInt(StreamObserver<Integer> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.CLIENT_STREAMING)
    StreamObserver<TestData> testModel(StreamObserver<TestData> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.CLIENT_STREAMING)
    StreamObserver<List<TestData>> testModelList(StreamObserver<List<TestData>> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.CLIENT_STREAMING)
    StreamObserver<List<Map<String, Object>>> testMapList(StreamObserver<List<Map<String, Object>>> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.CLIENT_STREAMING)
    StreamObserver<Map<String, Object>> testModelMap(StreamObserver<Map<String, Object>> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.CLIENT_STREAMING)
    StreamObserver<Map<String, Object>> testMap(StreamObserver<Map<String, Object>> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.CLIENT_STREAMING)
    StreamObserver<Map<String, List<Object>>> testListMap(StreamObserver<Map<String, List<Object>>> data);
}
