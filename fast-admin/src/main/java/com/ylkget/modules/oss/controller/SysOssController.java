package com.ylkget.modules.oss.controller;

import com.google.gson.Gson;
import com.ylkget.base.common.exception.FastException;
import com.ylkget.base.common.utils.R;
import com.ylkget.base.common.validator.ValidatorUtils;
import com.ylkget.base.common.validator.group.AliyunGroup;
import com.ylkget.base.common.validator.group.QcloudGroup;
import com.ylkget.base.common.validator.group.QiniuGroup;
import com.ylkget.common.utils.ConfigConstant;
import com.ylkget.common.utils.Constant;
import com.ylkget.common.utils.PageUtils;
import com.ylkget.modules.oss.cloud.CloudStorageConfig;
import com.ylkget.modules.oss.cloud.OSSFactory;
import com.ylkget.modules.oss.entity.SysOssEntity;
import com.ylkget.modules.oss.service.SysOssService;
import com.ylkget.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author joe 2021/3/13 08:16
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:oss:all")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysOssService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }

    /**
     * 保存云存储配置信息
     */
    @PostMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public R saveConfig(@RequestBody CloudStorageConfig config){
        //校验类型
        ValidatorUtils.validateEntity(config);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

        return R.ok();
    }

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new FastException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);

        return R.ok().put("url", url);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:oss:all")
    public R delete(@RequestBody Long[] ids){
        sysOssService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
