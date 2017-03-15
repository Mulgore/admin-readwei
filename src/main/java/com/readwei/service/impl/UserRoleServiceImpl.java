package com.readwei.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.readwei.entity.UserRole;
import com.readwei.mapper.UserRoleMapper;
import com.readwei.service.IUserRoleService;
import com.readwei.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

	@Override
	public boolean existRoleUser( Long roleId ) {
		UserRole ur = new UserRole();
		ur.setRid(roleId);
		int rlt = baseMapper.selectCount(new EntityWrapper<UserRole>(ur));
		return rlt >= 1;
	}

}