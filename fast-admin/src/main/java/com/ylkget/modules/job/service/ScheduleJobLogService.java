package com.ylkget.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * <p>
 * 定时任务日志
 * </p>
 *
 * @author joe 2021/3/14 09:44
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
