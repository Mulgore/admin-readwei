package com.reawei.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.reawei.entity.RwRole;
import com.reawei.entity.RwUserRole;
import com.reawei.mapper.RwRoleMapper;
import com.reawei.mapper.RwUserRoleMapper;
import com.reawei.service.IRwRoleService;
import com.reawei.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Service
public class RwRoleServiceImpl extends BaseServiceImpl<RwRoleMapper, RwRole> implements IRwRoleService {

    @Autowired
    private RwUserRoleMapper userRoleMapper;

    @Override
    public void deleteByUserId(Long userId) {
        RwUserRole ur = new RwUserRole();
        ur.setUid(userId);
        userRoleMapper.delete(new EntityWrapper<RwUserRole>(ur));
    }
}
