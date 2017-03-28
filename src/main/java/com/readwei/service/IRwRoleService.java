package com.readwei.service;

import com.baomidou.mybatisplus.service.IService;
import com.readwei.entity.RwRole;

/**
 *
 * Role 表数据服务层接口
 *
 */
public interface IRwRoleService extends IService<RwRole> {

	void deleteByUserId(Long userId);

}
