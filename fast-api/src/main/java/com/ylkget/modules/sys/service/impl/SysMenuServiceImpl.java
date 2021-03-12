package com.ylkget.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylkget.base.common.utils.MapUtils;
import com.ylkget.common.utils.Constant;
import com.ylkget.modules.sys.dao.SysMenuDao;
import com.ylkget.modules.sys.entity.SysMenuEntity;
import com.ylkget.modules.sys.service.SysMenuService;
import com.ylkget.modules.sys.service.SysRoleMenuService;
import com.ylkget.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author joe 2021/3/11 19:41
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * @author joe 2021/3/12 14:08
     * 父子一对多，过滤更深的层次
     * @param parentId: 0 获取菜单 其他 获取按钮
     * @param menuIdList: 用户拥有的菜单
     * @return {@link List< SysMenuEntity> }
     **/
    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for(SysMenuEntity menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * @author joe 2021/3/12 14:05
     * 父子一对多
     * @param parentId: 父id
     * @return {@link List< SysMenuEntity> }
     **/
    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    // 用户拥有权限的，目录+菜单+按钮的SysMenuEntity
    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户所有角色下的menu_id
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        //过滤掉根菜单以外的
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Long menuId) {
        //删除菜单
        this.removeById(menuId);
        //删除菜单与角色关联
        sysRoleMenuService.removeByMap(new MapUtils().put("menu_id", menuId));
    }

    /**
     * 获取所有的菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
        //查询有权限的，根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取目录子菜单，菜单子按钮
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }
    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for(SysMenuEntity entity : menuList){
            //目录
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
