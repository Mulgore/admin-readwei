package com.readwei.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.readwei.entity.RolePermission;
import com.readwei.mapper.RolePermissionMapper;
import com.readwei.service.IRolePermissionService;
import com.readwei.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * RolePermission 表数据服务层接口实现类
 *
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionMapper, RolePermission>
		implements IRolePermissionService {

	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Log("菜单查询")
	@Override
	public boolean existRolePermission(Long permissionId) {
		RolePermission rp = new RolePermission();
		rp.setPid(permissionId);
		int rlt = baseMapper.selectCount(new EntityWrapper<RolePermission>(rp));
		return rlt >= 1;
	}

	@Log("角色关联菜单查询")
	@Override
	public List<Long> selecPermissionIdsByRoleId(Long id) {
		return rolePermissionMapper.selecPermissionIdsByRoleId(id);
	}

}