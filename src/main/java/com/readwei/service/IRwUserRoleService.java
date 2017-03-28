package com.readwei.service;

import com.baomidou.mybatisplus.service.IService;
import com.readwei.entity.RwUserRole;

/**
 *
 * UserRole 表数据服务层接口
 *
 */
public interface IRwUserRoleService extends IService<RwUserRole> {

	/**
	 * <p>
	 * 判断是否存在角色对应的用户
	 * </p>
	 * 
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	boolean existRoleUser(Long roleId);

}