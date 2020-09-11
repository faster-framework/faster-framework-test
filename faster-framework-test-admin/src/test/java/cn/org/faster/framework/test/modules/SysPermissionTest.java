package cn.org.faster.framework.test.modules;

import cn.org.faster.framework.admin.permission.model.SysPermissionAddReq;
import cn.org.faster.framework.admin.permission.model.SysPermissionUpdateReq;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangbowen 2018/6/7 16:31
 */
public class SysPermissionTest extends BaseTest{
    @Override
    public String getBaseHeader() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMjkwNzUzODg3MzcxMjY0MCIsInRpbWVzdGFtcCI6MTUyODI3ODUyNjE5M30.nAj8IMVoc-JjjcwkWZgS53aW-wdgMmUr63C3Tt3XXRQ";
    }

    /**
     * 权限树
     * @throws Exception
     */
    @Test
    public void permissions() throws Exception {
        buildRequest(() -> MockMvcRequestBuilders.get("/sys/permissions")).andExpect(status().is2xxSuccessful());
    }

    /**
     * 权限详情
     * @throws Exception
     */
    @Test
    public void infoById() throws Exception {
        buildRequest(() -> MockMvcRequestBuilders.get("/sys/permissions/32907540605960192")).andExpect(status().is2xxSuccessful());
    }
    /**
     * 新增权限
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        SysPermissionAddReq insert = new SysPermissionAddReq();
        insert.setCode("users:roles:choose");
        insert.setName("选择角色");
        insert.setParentId(37615593091760128L);
        buildRequest(() -> MockMvcRequestBuilders.post("/sys/permissions").content(JSON.toJSONString(insert))).andExpect(status().is2xxSuccessful());
    }
    /**
     * 新增整个功能权限父级
     * @throws Exception
     */
    @Test
    public void addParent() throws Exception {
        SysPermissionAddReq insert = new SysPermissionAddReq();
        insert.setCode("users:manage");
        insert.setName("用户管理");
        insert.setParentId(0L);
        buildRequest(() -> MockMvcRequestBuilders.post("/sys/permissions").content(JSON.toJSONString(insert))).andExpect(status().is2xxSuccessful());
    }

    /**
     * 新增整个功能权限
     * @throws Exception
     */
    @Test
    public void addChildren() throws Exception {
        SysPermissionAddReq insert = new SysPermissionAddReq();
        insert.setCode("users:list");
        insert.setName("列表");
        insert.setParentId(37615593091760128L);
        buildRequest(() -> MockMvcRequestBuilders.post("/sys/permissions").content(JSON.toJSONString(insert))).andExpect(status().is2xxSuccessful());

        insert.setCode("users:add");
        insert.setName("添加");
        insert.setParentId(37615593091760128L);
        buildRequest(() -> MockMvcRequestBuilders.post("/sys/permissions").content(JSON.toJSONString(insert))).andExpect(status().is2xxSuccessful());

        insert.setCode("users:delete");
        insert.setName("删除");
        insert.setParentId(37615593091760128L);
        buildRequest(() -> MockMvcRequestBuilders.post("/sys/permissions").content(JSON.toJSONString(insert))).andExpect(status().is2xxSuccessful());

        insert.setCode("users:update");
        insert.setName("编辑");
        insert.setParentId(37615593091760128L);
        buildRequest(() -> MockMvcRequestBuilders.post("/sys/permissions").content(JSON.toJSONString(insert))).andExpect(status().is2xxSuccessful());

        insert.setCode("users:info");
        insert.setName("详情");
        insert.setParentId(37615593091760128L);
        buildRequest(() -> MockMvcRequestBuilders.post("/sys/permissions").content(JSON.toJSONString(insert))).andExpect(status().is2xxSuccessful());
    }
    /**
     * 编辑权限
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        SysPermissionUpdateReq updateReq = new SysPermissionUpdateReq();
        updateReq.setCode("test:list:open");
        updateReq.setName("测试开放");
        buildRequest(() -> MockMvcRequestBuilders.put("/sys/permissions/36167405512163328").content(JSON.toJSONString(updateReq))).andExpect(status().is2xxSuccessful());
    }

    /**
     * 删除权限
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        buildRequest(() -> MockMvcRequestBuilders.delete("/sys/permissions/36167405512163328")).andExpect(status().is2xxSuccessful());
    }
}
