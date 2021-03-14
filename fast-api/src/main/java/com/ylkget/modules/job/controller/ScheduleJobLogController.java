package com.ylkget.modules.job.controller;

import com.ylkget.base.common.utils.R;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.modules.job.entity.ScheduleJobLogEntity;
import com.ylkget.modules.job.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * ScheduleJobLogController 定时任务日志
 * </p>
 *
 * @author joe 2021/3/14 11:34
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:log")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scheduleJobLogService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 定时任务日志信息
     */
    @RequestMapping("/info/{logId}")
    public R info(@PathVariable("logId") Long logId){
        ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);

        return R.ok().put("log", log);
    }
}
