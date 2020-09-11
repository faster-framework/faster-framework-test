package cn.org.faster.framework.test.web;

import cn.org.faster.framework.test.TestApplication;
import cn.org.faster.framework.test.modules.v1.users.DemoService;
import cn.org.faster.framework.test.modules.v2.TestBean;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author zhangbowen
 * @since 2019/4/13
 */
@SpringBootTest(classes = TestApplication.class)
public class ApiTest extends BaseTest {

    @Mock
    @Autowired
    private RestTemplate restTemplate;
    //标识该template为mock生成的
    @Autowired
    @InjectMocks
    private DemoService demoService;

    @Override
    public String getBaseHeader() {
        return null;
    }

    /**
     * get请求测试
     */
    @Test
    public void testGet() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .get("/demo/testGet/{id}", 1111);
        mockHttpServletRequestBuilder.param("name", "张三");
        super.buildRequest(() -> mockHttpServletRequestBuilder)
                .andExpect(jsonPath("$.code", is("0000")));
    }

    /**
     * post请求测试
     */
    @Test
    public void testPost() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/demo/testPost");
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "张三");
        mockHttpServletRequestBuilder.content(JSON.toJSONString(requestMap));
        super.buildRequest(() -> mockHttpServletRequestBuilder)
                .andExpect(jsonPath("$.code", is("0000")));
    }

    /**
     * mock restTemplate 测试
     */
    @Test
    public void restTemplate() throws Exception {

        //Mockito.when表示当执行某个方法时进行模拟
        //Mockito.any()代表任意参数
        //thenReturn表示期望模拟返回的结果
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(new ResponseEntity<>("bbbb", HttpStatus.OK));
        //模拟成功后，调用test()方法时，其中的restTemplate的getForEntity返回的就是我们thenReturn中设置的值了。
        ResponseEntity responseEntity = this.demoService.test();
        System.out.println(responseEntity);
        //这里直接打印了，也可以使用MockMvc进行测试
    }



}
