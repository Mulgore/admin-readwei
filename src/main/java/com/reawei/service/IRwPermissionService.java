package com.reawei.service;

import com.baomidou.kisso.Token;
import com.baomidou.mybatisplus.service.IService;
import com.reawei.entity.RwPermission;
import com.reawei.entity.vo.MenuVO;

import java.util.List;

/**
 *
 * Permission 表数据服务层接口
 *
 */
public interface IRwPermissionService extends IService<RwPermission> {

	List<MenuVO> selectMenuVOByUserId(Long userId);


	List<RwPermission> selectAllByUserId(Long userId);

	boolean isPermitted(Token token, String permission);

	/**
	 * <p>
	 * 是否为可操作的权限
	 * </p>
	 * @param token
	 * 				SSO 票据顶级父类 
	 * @param permission
	 * 				操作权限编码
	 * @return
	 */
	boolean isActionable(Token token, String permission);
}