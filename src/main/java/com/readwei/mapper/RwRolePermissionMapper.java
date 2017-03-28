package com.readwei.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.readwei.entity.RwRolePermission;

import java.util.List;

/**
 *
 * RolePermission 表数据库控制层接口
 *
 */
public interface RwRolePermissionMapper extends BaseMapper<RwRolePermission> {

	
	/**
	 * 根据角色ID获取对应的所有关联权限的ID
	 * @param id 角色Id
	 * @return
	 */
	List<Long> selectPermissionIdsByRoleId(Long id);


}