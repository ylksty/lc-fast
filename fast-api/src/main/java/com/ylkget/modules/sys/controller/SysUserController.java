package com.ylkget.modules.sys.controller;

import com.ylkget.base.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author joe 2021/3/11 17:32
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public R info(){
        return R.ok().put("user", getUser());
    }
}
