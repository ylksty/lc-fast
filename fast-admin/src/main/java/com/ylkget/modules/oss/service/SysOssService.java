package com.ylkget.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author joe 2021/3/13 08:18
 */
public interface SysOssService extends IService<SysOssEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
