package cn.org.faster.framework.test.grpc.service;

import cn.org.faster.framework.grpc.client.annotation.GRpcService;
import cn.org.faster.framework.grpc.core.annotation.GRpcMethod;
import cn.org.faster.framework.test.grpc.model.TestData;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Map;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@GRpcService(value = "test-unary", scheme = "unary")
public interface UnaryService {

    @GRpcMethod
    int testInt(int data);

    @GRpcMethod
    TestData testModel(TestData data);

    @GRpcMethod
    List<TestData> testModelList(List<TestData> data);

    @GRpcMethod
    List<Map<String, Object>> testMapList(List<Map<String, Object>> data);

    @GRpcMethod
    Map<String, Object> testModelMap(Map<String, Object> data);

    @GRpcMethod
    Map<String, Object> testMap(Map<String, Object> data);

    @GRpcMethod
    Map<String, List<Object>> testListMap(Map<String, List<Object>> data);

    @GRpcMethod
    ListenableFuture<Integer> testListenableFuture(int data);

    @GRpcMethod
    void testStreamObserver(int data, StreamObserver<Integer> streamObserver);

    @GRpcMethod
    void testIntEmpty();

    @GRpcMethod
    void testIntEmptyReturn(int data);

    @GRpcMethod
    int testIntEmptyParam();

}
