package com.ylkget.modules.sys.form;

import lombok.Data;

/**
 * <p>
 * 登录表单
 * </p>
 *
 * @author joe 2021/3/11 16:15
 */
@Data
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
