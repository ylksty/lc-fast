package com.ylkget.modules.sys.service;

import com.ylkget.modules.sys.entity.SysUserEntity;
import com.ylkget.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author joe 2021/3/11 18:16
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
