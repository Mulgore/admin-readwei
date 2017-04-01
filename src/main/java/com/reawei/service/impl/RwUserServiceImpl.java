package com.reawei.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.reawei.entity.RwUser;
import com.reawei.mapper.RwUserMapper;
import com.reawei.service.*;
import com.reawei.service.IRwUserService;
import com.reawei.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Service
public class RwUserServiceImpl extends BaseServiceImpl<RwUserMapper, RwUser> implements IRwUserService {

    @Autowired
    private IRwRoleService roleService;

    @Log("登录")
    @Override
    public RwUser selectByLoginName(String loginName) {
        RwUser user = new RwUser();
        user.setLoginName(loginName);
        return super.selectOne(new EntityWrapper<RwUser>(user));
    }

    @Log("删除用户")
    @Override
    public void deleteUser(Long userId) {
        // 删除用户角色，再删除用户
        roleService.deleteByUserId(userId);
        super.deleteById(userId);
    }

    @Log("添加用户")
    public boolean insert(RwUser entity) {
        System.err.println(" 覆盖父类方法 ");
        return super.insert(entity);
    }
}
