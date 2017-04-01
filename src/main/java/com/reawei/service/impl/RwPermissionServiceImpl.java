package com.reawei.service.impl;

import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.Token;
import com.reawei.entity.RwPermission;
import com.reawei.entity.vo.MenuVO;
import com.reawei.mapper.RwPermissionMapper;
import com.reawei.service.IRwPermissionService;
import com.reawei.service.support.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Service
public class RwPermissionServiceImpl extends BaseServiceImpl<RwPermissionMapper, RwPermission> implements IRwPermissionService, SSOAuthorization {

    @Cacheable(value = "permissionCache", key = "#userId")
    @Override
    public List<MenuVO> selectMenuVOByUserId(Long userId) {
        List<MenuVO> perList = baseMapper.selectMenuByUserId(userId, 0L);
        if (perList == null || perList.isEmpty()) {
            return null;
        }
        List<MenuVO> mvList = new ArrayList<MenuVO>();
        for (MenuVO mv : perList) {
            mv.setMvList(baseMapper.selectMenuByUserId(userId, mv.getId()));
            mvList.add(mv);
        }
        return mvList;
    }

    @Override
    public boolean isPermitted(Token token, String permission) {
        /**
         *
         * 菜单级别、权限验证，生产环境建议加上缓存处理。
         *
         */
        if (StringUtils.isNotBlank(permission)) {
            List<RwPermission> pl = this.selectAllByUserId(token.getId());
            if (pl != null) {
                for (RwPermission p : pl) {
                    if (permission.equals(p.getPermCode())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Cacheable(value = "permissionCache", key = "#userId")
    @Override
    public List<RwPermission> selectAllByUserId(Long userId) {
        return baseMapper.selectAllByUserId(userId);
    }

    @Override
    public boolean isActionable(Token token, String permission) {
        /**
         *
         * 按钮级别、权限验证，生产环境建议加上缓存处理。
         * <br>
         * 演示  admin 返回 true
         *
         */
        System.err.println(" isActionable =" + permission);
        if (token.getId() == 1L) {
            return true;
        }
        return false;
    }
}
