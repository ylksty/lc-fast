package com.ylkget.modules.sys.controller;

import com.ylkget.base.common.utils.R;
import com.ylkget.modules.sys.entity.SysMenuEntity;
import com.ylkget.modules.sys.service.ShiroService;
import com.ylkget.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author joe 2021/3/11 19:38
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public R nav(){
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        return R.ok().put("menuList", menuList).put("permissions", permissions);
    }
}
