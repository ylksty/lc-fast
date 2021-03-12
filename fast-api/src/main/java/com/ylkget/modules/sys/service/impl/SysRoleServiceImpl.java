package com.ylkget.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.modules.sys.dao.SysRoleDao;
import com.ylkget.modules.sys.entity.SysRoleEntity;
import com.ylkget.modules.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author joe 2021/3/12 08:49
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void saveRole(SysRoleEntity role) {

    }

    @Override
    public void update(SysRoleEntity role) {

    }

    @Override
    public void deleteBatch(Long[] roleIds) {

    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }
}
