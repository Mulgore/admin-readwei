package com.readwei.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.readwei.entity.Permission;
import com.readwei.entity.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * Permission 表数据库控制层接口
 *
 */
public interface PermissionMapper extends BaseMapper<Permission> {

	List<MenuVO> selectMenuByUserId(@Param("userId") Long userId, @Param("pid") Long pid);

	List<Permission> selectAllByUserId(@Param("userId") Long userId);

}