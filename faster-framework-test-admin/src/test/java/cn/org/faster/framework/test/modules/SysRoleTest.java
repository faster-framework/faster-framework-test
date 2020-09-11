package cn.org.faster.framework.test.modules;

import cn.org.faster.framework.admin.role.model.SysRoleReq;
import cn.org.faster.framework.admin.rolePermission.entity.SysRolePermission;
import cn.org.faster.framework.web.model.ListWrapper;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangbowen 2018/6/11 9:52
 */
public class SysRoleTest extends BaseTest {
    @Override
    public String getBaseHeader() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwIiwidGltZXN0YW1wIjoxNTMwMDkwOTgxMTU0fQ.poil-rEwYvW58GiUUygkGLf8XOugTwkbz2cMIONIPyA";
    }

    @Test
    public void add() throws Exception {
        SysRoleReq roleReq = new SysRoleReq();
        roleReq.setName("运营");
        this.buildRequest(() -> MockMvcRequestBuilders.post("/sys/roles").content(JSON.toJSONString(roleReq))).andExpect(status().is2xxSuccessful());
    }

    /**
     * 角色列表
     */
    @Test
    public void list() throws Exception {
        this.buildRequest(() -> MockMvcRequestBuilders.get("/sys/roles?pageSize={pageSize}&pageNum={pageNum}", 10, 1)).andExpect(status().is2xxSuccessful());
    }
    /**
     * 角色详情
     */
    @Test
    public void info() throws Exception {
        this.buildRequest(() -> MockMvcRequestBuilders.get("/sys/roles/{id}", 37514606662385664L)).andExpect(status().is2xxSuccessful());
    }
    /**
     * 角色更新
     */
    @Test
    public void update() throws Exception {
        SysRoleReq sysRoleReq = new SysRoleReq();
        sysRoleReq.setName("运营改名字了");
        this.buildRequest(() -> MockMvcRequestBuilders.put("/sys/roles/{id}", 37514606662385664L).content(JSON.toJSONString(sysRoleReq))).andExpect(status().is2xxSuccessful());
    }
    /**
     * 角色删除
     */
    @Test
    public void delete() throws Exception {
        this.buildRequest(() -> MockMvcRequestBuilders.delete("/sys/roles/{id}", 37514606662385664L)).andExpect(status().is2xxSuccessful());
    }

    /**
     * 批量选择角色的权限
     */
    @Test
    public void batchChoosePermissions() throws Exception {
        SysRolePermission rolePermission = new SysRolePermission();
        rolePermission.setPermissionId(2L);
        SysRolePermission rolePermission1 = new SysRolePermission();
        rolePermission1.setPermissionId(3L);
        List<SysRolePermission> list = new ArrayList<>();
        list.add(rolePermission);
        list.add(rolePermission1);
        ListWrapper<SysRolePermission> listWrapper = new ListWrapper<>();
        listWrapper.setList(list);
        this.buildRequest(()-> MockMvcRequestBuilders.put("/sys/roles/{roleId}/permissions/choose",37514606662385664L).content(JSON.toJSONString(listWrapper))).andExpect(status().is2xxSuccessful());
    }

    /**
     * 角色权限列表
     */
    @Test
    public void permissions() throws Exception {
        this.buildRequest(() -> MockMvcRequestBuilders.get("/sys/roles/0/permissions")).andExpect(status().is2xxSuccessful());
    }
}
