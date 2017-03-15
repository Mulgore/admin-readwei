package com.readwei.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.readwei.entity.Role;
import com.readwei.entity.UserRole;
import com.readwei.mapper.RoleMapper;
import com.readwei.mapper.UserRoleMapper;
import com.readwei.service.IRoleService;
import com.readwei.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Role 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public void deleteByUserId(Long userId) {
		UserRole ur = new UserRole();
		ur.setUid(userId);
		userRoleMapper.delete(new EntityWrapper<UserRole>(ur));
	}

}
