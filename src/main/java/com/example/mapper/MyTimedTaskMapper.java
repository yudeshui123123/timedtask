package com.example.mapper;

import com.example.entity.MyTimedTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 定时任务表 Mapper 接口
 * </p>
 *
 * @author yds
 * @since 2020-11-28
 */
@Repository
public interface MyTimedTaskMapper extends BaseMapper<MyTimedTask> {

}
