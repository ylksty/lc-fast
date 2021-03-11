package com.ylkget.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylkget.base.common.utils.R;
import com.ylkget.modules.sys.entity.SysUserTokenEntity;

/**
 * <p>
 *
 * </p>
 *
 * @author joe 2021/3/11 16:30
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {
    /**
     * 生成token
     * @param userId  用户ID
     */
    R createToken(long userId);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);
}
