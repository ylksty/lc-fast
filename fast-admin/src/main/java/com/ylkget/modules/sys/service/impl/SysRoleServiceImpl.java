package com.ylkget.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylkget.base.common.exception.FastException;
import com.ylkget.common.utils.Constant;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.common.utils.Query;
import com.ylkget.modules.sys.dao.SysRoleDao;
import com.ylkget.modules.sys.entity.SysRoleEntity;
import com.ylkget.modules.sys.service.SysRoleMenuService;
import com.ylkget.modules.sys.service.SysRoleService;
import com.ylkget.modules.sys.service.SysUserRoleService;
import com.ylkget.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
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
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String)params.get("roleName");
        Long createUserId = (Long)params.get("createUserId");

        IPage<SysRoleEntity> page = this.page(
                new Query<SysRoleEntity>().getPage(params),
                new QueryWrapper<SysRoleEntity>()
                        .like(StringUtils.isNotBlank(roleName),"role_name", roleName)
                        .eq(createUserId != null,"create_user_id", createUserId)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);

        //????????????????????????
        checkPrems(role);

        //???????????????????????????
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleEntity role) {
        this.updateById(role);

        //????????????????????????
        checkPrems(role);

        //???????????????????????????
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //????????????
        this.removeByIds(Arrays.asList(roleIds));

        //???????????????????????????
        sysRoleMenuService.deleteBatch(roleIds);

        //???????????????????????????
        sysUserRoleService.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }

    /**
     * ????????????????????????
     */
    private void checkPrems(SysRoleEntity role){
        //???????????????????????????????????????????????????????????????????????????????????????
        if(role.getCreateUserId() == Constant.SUPER_ADMIN){
            return ;
        }

        //????????????????????????????????????
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //??????????????????
        if(!menuIdList.containsAll(role.getMenuIdList())){
            throw new FastException("???????????????????????????????????????????????????");
        }
    }
}
