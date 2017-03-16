package com.readwei.service;

import com.baomidou.mybatisplus.service.IService;
import com.readwei.entity.RolePermission;

import java.util.List;

/**
 * RolePermission 表数据服务层接口
 */
public interface IRolePermissionService extends IService<RolePermission> {

    /**
     * <p>
     * 判断是否存在角色对应的权限
     * </p>
     *
     * @param permissionId 权限ID
     * @return
     */
    boolean existRolePermission(Long permissionId);

    boolean existRolePermission(Long permissionId, Long roleId);


    /**
     * 根据角色ID获取对应的所有关联权限的ID
     *
     * @param id
     * @return
     */
    List<Long> selecPermissionIdsByRoleId(Long id);

}