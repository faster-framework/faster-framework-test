package cn.org.faster.framework.test.grpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangbowen
 * @since 2019/1/17
 */
public class DataFactory {

    /**
     * @return 基础数据类型
     */
    public static int buildInt() {
        return 111;
    }

    /**
     * @return 业务实体
     */
    public static TestData buildModel() {
        TestData testData = new TestData();
        testData.setMsg("hello");
        List<TestData> list = new ArrayList<>();
        TestData testData1 = new TestData();
        testData1.setMsg("list1");
        list.add(testData1);
        testData.setList(list);
        return testData;
    }


    /**
     * @return 业务类型list
     */
    public static List<TestData> buildModelList() {
        TestData testData = new TestData();
        testData.setMsg("hello");
        List<TestData> list = new ArrayList<>();
        TestData testData1 = new TestData();
        testData1.setMsg("list1");
        list.add(testData1);
        testData.setList(list);
        List<TestData> resultList = new ArrayList<>();
        resultList.add(testData);
        resultList.add(testData);
        return resultList;
    }

    /**
     * @return 业务类型list，泛型Map
     */
    public static List<Map<String, Object>> buildMapList() {
        Map<String, Object> map = new HashMap<>();
        map.put("hello", "你好");
        map.put("key", "v");
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("hello", "你好");
        map2.put("key", "v");
        resultList.add(map);
        resultList.add(map2);
        return resultList;
    }


    /**
     * @return 业务类型map
     */
    public static Map<String, Object> buildModelMap() {
        TestData testData = new TestData();
        testData.setMsg("hello");
        List<TestData> list = new ArrayList<>();
        TestData testData1 = new TestData();
        testData1.setMsg("list1");
        list.add(testData1);
        testData.setList(list);
        Map<String, Object> testDataMap = new HashMap<>();
        testDataMap.put("test", testData);
        return testDataMap;
    }

    /**
     * @return 基础类型map
     */
    public static Map<String, Object> buildMap() {
        Map<String, Object> testDataMap = new HashMap<>();
        testDataMap.put("test", "hello");
        return testDataMap;
    }

    /**
     * @return 基础类型map，泛型为List
     */
    public static Map<String, List<Object>> buildListMap() {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        TestData testData = new TestData();
        testData.setMsg("hello");
        List<TestData> list1 = new ArrayList<>();
        TestData testData1 = new TestData();
        testData1.setMsg("list");
        list1.add(testData1);
        testData.setList(list1);
        list.add(testData);
        Map<String, List<Object>> testDataMap = new HashMap<>();
        testDataMap.put("test", list);
        return testDataMap;
    }
}
