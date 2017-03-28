package com.readwei.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.readwei.entity.RwPermission;
import com.readwei.entity.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * Permission 表数据库控制层接口
 *
 */
public interface RwPermissionMapper extends BaseMapper<RwPermission> {

	List<MenuVO> selectMenuByUserId(@Param("userId") Long userId, @Param("pid") Long pid);

	List<RwPermission> selectAllByUserId(@Param("userId") Long userId);

}