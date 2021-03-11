package com.ylkget.modules.sys.service.impl;

import com.ylkget.modules.sys.dao.SysUserDao;
import com.ylkget.modules.sys.dao.SysUserTokenDao;
import com.ylkget.modules.sys.entity.SysUserEntity;
import com.ylkget.modules.sys.entity.SysUserTokenEntity;
import com.ylkget.modules.sys.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author joe 2021/3/11 18:23
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        return null;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}
