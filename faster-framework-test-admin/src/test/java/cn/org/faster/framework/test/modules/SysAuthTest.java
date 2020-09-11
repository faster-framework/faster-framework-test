package cn.org.faster.framework.test.modules;

import cn.org.faster.framework.admin.auth.model.LoginReq;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangbowen 2018/6/6 9:34
 */
public class SysAuthTest extends BaseTest {

    /**
     * 登录
     * @throws Exception
     */
    @Test
    public void login() throws Exception {
        LoginReq loginReq = new LoginReq();
        loginReq.setAccount("admin");
        loginReq.setPassword("admin");
        buildRequest(() -> MockMvcRequestBuilders.post("/login").content(JSON.toJSONString(loginReq))).andExpect(status().is2xxSuccessful());
    }

    /**
     * 获取权限列表
     * @throws Exception
     */
    @Test
    public void permission() throws Exception {
        buildRequest(() -> MockMvcRequestBuilders.get("/permissions")).andExpect(status().is2xxSuccessful());
    }

    /**
     * 注销
     * @throws Exception
     */
    @Test
    public void logout() throws Exception {
        buildRequest(() -> MockMvcRequestBuilders.delete("/logout")).andExpect(status().is2xxSuccessful());
    }


    @Override
    public String getBaseHeader() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMjkwNzUzODg3MzcxMjY0MCIsInRpbWVzdGFtcCI6MTUyODI3ODUyNjE5M30.nAj8IMVoc-JjjcwkWZgS53aW-wdgMmUr63C3Tt3XXRQ";
    }
}
