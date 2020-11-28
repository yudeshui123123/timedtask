package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.MyTimedTask;
import com.example.mapper.MyTimedTaskMapper;
import com.example.service.IMyTimedTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 定时任务表 服务实现类
 * </p>
 *
 * @author yds
 * @since 2020-11-28
 */
@Service
public class MyTimedTaskServiceImpl extends ServiceImpl<MyTimedTaskMapper, MyTimedTask> implements IMyTimedTaskService {

    @Autowired
    private MyTimedTaskMapper myTimedTaskMapper;

    @Override
    public List<MyTimedTask> queryList() {
        QueryWrapper<MyTimedTask> myTimedTaskQueryWrapper = new QueryWrapper<>();
        myTimedTaskQueryWrapper.eq("status","0");
        List<MyTimedTask> tasks = myTimedTaskMapper.selectList(myTimedTaskQueryWrapper);
        System.out.println("方法1");
        return tasks;
    }

    @Override
    public List<MyTimedTask> queryList2() {
        System.out.println("方法2");
        return null;
    }
}
