package com.reawei.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.reawei.entity.RwUserRole;
import com.reawei.mapper.RwUserRoleMapper;
import com.reawei.service.IRwUserRoleService;
import com.reawei.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Service
public class RwUserRoleServiceImpl extends BaseServiceImpl<RwUserRoleMapper, RwUserRole> implements IRwUserRoleService {

    @Override
    public boolean existRoleUser( Long roleId ) {
        RwUserRole ur = new RwUserRole();
        ur.setRid(roleId);
        int rlt = baseMapper.selectCount(new EntityWrapper<RwUserRole>(ur));
        return rlt >= 1;
    }
}
