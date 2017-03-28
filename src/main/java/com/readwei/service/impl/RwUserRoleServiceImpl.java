package com.readwei.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.readwei.entity.RwUserRole;
import com.readwei.mapper.RwUserRoleMapper;
import com.readwei.service.IRwUserRoleService;
import com.readwei.service.support.BaseServiceImpl;
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
