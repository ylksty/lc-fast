package com.ylkget.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.modules.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author joe 2021/3/14 09:21
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存定时任务
     */
    void saveJob(ScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     */
    void update(ScheduleJobEntity scheduleJob);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 暂停运行
     */
    void pause(Long[] jobIds);

    /**
     * 立即执行
     */
    void run(Long[] jobIds);

    /**
     * 恢复运行
     */
    void resume(Long[] jobIds);
}
