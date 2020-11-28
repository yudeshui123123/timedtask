package com.example.service;

import com.example.entity.MyTimedTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 定时任务表 服务类
 * </p>
 *
 * @author yds
 * @since 2020-11-28
 */
public interface IMyTimedTaskService extends IService<MyTimedTask> {

    List<MyTimedTask> queryList();

    List<MyTimedTask> queryList2();
}
