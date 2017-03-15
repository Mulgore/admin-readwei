package com.readwei.service;

import com.baomidou.mybatisplus.service.IService;
import com.readwei.entity.User;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

	User selectByLoginName(String loginName);

	void deleteUser(Long userId);

}