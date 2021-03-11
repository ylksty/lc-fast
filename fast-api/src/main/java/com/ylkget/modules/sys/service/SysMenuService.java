package com.ylkget.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylkget.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author joe 2021/3/11 19:39
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取用户菜单列表
     */
    List<SysMenuEntity> getUserMenuList(Long userId);
}
