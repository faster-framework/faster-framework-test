package cn.org.faster.framework.test.modules;

import cn.org.faster.framework.admin.user.model.SysUserAddReq;
import cn.org.faster.framework.admin.user.model.SysUserChangePwdReq;
import cn.org.faster.framework.admin.user.model.SysUserUpdateReq;
import cn.org.faster.framework.admin.userRole.entity.SysUserRole;
import cn.org.faster.framework.web.model.ListWrapper;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangbowen 2018/6/6 9:34
 */
public class SysUserTest extends BaseTest{

    @Override
    public String getBaseHeader() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwIiwidGltZXN0YW1wIjoxNTI4NzEwNjMwMzk2fQ.M4BGupc8vd3hw5TmF4xdO9VyOPiEvdTbN7MbuIKJ5GM";
    }

    @Test
    public void add() throws Exception {
        SysUserAddReq sysUserAddReq = new SysUserAddReq();
        sysUserAddReq.setAccount("test");
        sysUserAddReq.setName("张三");
        sysUserAddReq.setPassword("123456");
        this.buildRequest(()-> MockMvcRequestBuilders.post("/sys/users").content(JSON.toJSONString(sysUserAddReq)));
    }

    @Test
    public void update() throws Exception {
        SysUserUpdateReq userUpdateReq = new SysUserUpdateReq();
        userUpdateReq.setName("张三改名了");
        this.buildRequest(()-> MockMvcRequestBuilders.put("/sys/users/37592481923072000").content(JSON.toJSONString(userUpdateReq))).andExpect(status().is2xxSuccessful());
    }


    @Test
    public void list() throws Exception {
        this.buildRequest(()-> MockMvcRequestBuilders.get("/sys/users?pageNum={pageNum}&pageSize={pageSize}",1,10)).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void info() throws Exception {
        this.buildRequest(()-> MockMvcRequestBuilders.get("/sys/users/37592481923072000")).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void changePwd() throws Exception {
        SysUserChangePwdReq changePwdReq = new SysUserChangePwdReq();
        changePwdReq.setOldPwd("123456");
        changePwdReq.setPassword("1234567");
        this.buildRequest(()-> MockMvcRequestBuilders.put("/sys/users/37592481923072000/password/change").content(JSON.toJSONString(changePwdReq)));
    }

    @Test
    public void resetPwd() throws Exception {
        this.buildRequest(()-> MockMvcRequestBuilders.put("/sys/users/37592481923072000/password/reset")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void delete() throws Exception {
        this.buildRequest(()-> MockMvcRequestBuilders.delete("/sys/users/37592481923072000")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void chooseRoles() throws Exception {
        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(0L);
        List<SysUserRole> list = new ArrayList<>();
        list.add(userRole);
        ListWrapper<SysUserRole> listWrapper = new ListWrapper<>();
        listWrapper.setList(list);
        this.buildRequest(()-> MockMvcRequestBuilders.put("/sys/users/{userId}/roles/choose",32907538873712640L).content(JSON.toJSONString(listWrapper))).andExpect(status().is2xxSuccessful());
    }
}
