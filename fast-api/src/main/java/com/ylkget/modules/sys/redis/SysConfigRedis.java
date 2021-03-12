package com.ylkget.modules.sys.redis;

import com.ylkget.common.utils.RedisKeys;
import com.ylkget.common.utils.RedisUtils;
import com.ylkget.modules.sys.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 系统配置Redis
 * </p>
 *
 * @author joe 2021/3/12 18:18
 */
@Component
public class SysConfigRedis {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysConfigEntity config) {
        if(config == null){
            return ;
        }
        String key = RedisKeys.getSysConfigKey(config.getParamKey());
        redisUtils.set(key, config);
    }

    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(key);
    }

    public SysConfigEntity get(String configKey){
        String key = RedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, SysConfigEntity.class);
    }
}
