package cn.org.faster.framework.test.grpc.service;

import cn.org.faster.framework.grpc.client.annotation.GRpcService;
import cn.org.faster.framework.grpc.core.annotation.GRpcMethod;
import cn.org.faster.framework.test.grpc.model.TestData;
import io.grpc.MethodDescriptor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
@GRpcService(value = "test-server-stream", scheme = "server-stream")
public interface ServerStreamService {

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    Iterator<Integer> testInt(int data);

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    Iterator<TestData> testModel(TestData data);

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    Iterator<List<TestData>> testModelList(List<TestData> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    Iterator<List<Map<String, Object>>> testMapList(List<Map<String, Object>> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    Iterator<Map<String, Object>> testModelMap(Map<String, Object> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    Iterator<Map<String, Object>> testMap(Map<String, Object> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    Iterator<Map<String, List<Object>>> testListMap(Map<String, List<Object>> data);

    @GRpcMethod(type = MethodDescriptor.MethodType.SERVER_STREAMING)
    Iterator<Integer> testIntEmptyParam();
}
