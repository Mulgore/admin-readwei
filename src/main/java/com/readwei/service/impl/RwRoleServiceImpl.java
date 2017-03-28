package com.readwei.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.readwei.entity.RwRole;
import com.readwei.entity.RwUserRole;
import com.readwei.mapper.RwRoleMapper;
import com.readwei.mapper.RwUserRoleMapper;
import com.readwei.service.IRwRoleService;
import com.readwei.service.support.BaseServiceImpl;
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
