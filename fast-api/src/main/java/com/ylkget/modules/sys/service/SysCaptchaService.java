package com.ylkget.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylkget.modules.sys.entity.SysCaptchaEntity;

import java.awt.image.BufferedImage;

/**
 * <p>
 *
 * </p>
 *
 * @author joe 2021/3/11 09:30
 */
public interface SysCaptchaService extends IService<SysCaptchaEntity> {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    boolean validate(String uuid, String captcha);
}
