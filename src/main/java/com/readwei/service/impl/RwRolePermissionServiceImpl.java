package com.readwei.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.readwei.entity.RwRolePermission;
import com.readwei.mapper.RwRolePermissionMapper;
import com.readwei.service.IRwRolePermissionService;
import com.readwei.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Service
public class RwRolePermissionServiceImpl extends BaseServiceImpl<RwRolePermissionMapper, RwRolePermission> implements IRwRolePermissionService {

    @Resource
    private RwRolePermissionMapper rolePermissionMapper;

    @Log("菜单查询")
    @Override
    public boolean existRolePermission(Long permissionId) {
        RwRolePermission rp = new RwRolePermission();
        rp.setPid(permissionId);
        int rlt = baseMapper.selectCount(new EntityWrapper<RwRolePermission>(rp));
        return rlt >= 1;
    }

    @Override
    public boolean existRolePermission(Long permissionId, Long roleId) {
        RwRolePermission rp = new RwRolePermission();
        rp.setPid(permissionId);
        rp.setRid(roleId);
        int rlt = baseMapper.selectCount(new EntityWrapper<RwRolePermission>(rp));
        return rlt >= 1;
    }

    @Log("角色关联菜单查询")
    @Override
    public List<Long> selectPermissionIdsByRoleId(Long id) {
        return rolePermissionMapper.selectPermissionIdsByRoleId(id);
    }
}
