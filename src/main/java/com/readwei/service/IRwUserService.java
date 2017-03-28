package com.readwei.service;

import com.baomidou.mybatisplus.service.IService;
import com.readwei.entity.RwUser;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IRwUserService extends IService<RwUser> {

	RwUser selectByLoginName(String loginName);

	void deleteUser(Long userId);

}