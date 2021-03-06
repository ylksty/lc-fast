package com.ylkget.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylkget.base.common.exception.FastException;
import com.ylkget.common.utils.Constant;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.common.utils.Query;
import com.ylkget.modules.sys.dao.SysUserDao;
import com.ylkget.modules.sys.entity.SysUserEntity;
import com.ylkget.modules.sys.service.SysRoleService;
import com.ylkget.modules.sys.service.SysUserRoleService;
import com.ylkget.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
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
 * @author joe 2021/3/11 16:26
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String)params.get("username");
        Long createUserId = (Long)params.get("createUserId");

        IPage<SysUserEntity> page = this.page(
                new Query<SysUserEntity>().getPage(params),
                new QueryWrapper<SysUserEntity>()
                        .like(StringUtils.isNotBlank(username),"username", username)
                        .eq(createUserId != null,"create_user_id", createUserId)
        );

        return new PageUtils(page);
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return null;
    }

    // sys_user_role left join sys_role_menu ??????????????????????????????????????????+??????+?????????menue_id
    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    @Transactional
    public void saveUser(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256??????
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        this.save(user);

        //????????????????????????
        checkRole(user);

        //???????????????????????????
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);

        //????????????????????????
        checkRole(user);

        //???????????????????????????
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        this.removeByIds(Arrays.asList(userIds));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        return false;
    }

    /**
     * ????????????????????????
     */
    private void checkRole(SysUserEntity user){
        if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
            return;
        }
        //??????????????????????????????????????????????????????????????????????????????
        if(user.getCreateUserId() == Constant.SUPER_ADMIN){
            return ;
        }

        //?????????????????????????????????
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //??????????????????
        if(!roleIdList.containsAll(user.getRoleIdList())){
            throw new FastException("?????????????????????????????????????????????");
        }
    }
}
