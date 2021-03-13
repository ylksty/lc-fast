package com.ylkget.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.common.utils.Query;
import com.ylkget.modules.oss.dao.SysOssDao;
import com.ylkget.modules.oss.entity.SysOssEntity;
import com.ylkget.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author joe 2021/3/13 08:20
 */
@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysOssEntity> page = this.page(
                new Query<SysOssEntity>().getPage(params)
        );

        return new PageUtils(page);
    }
}
