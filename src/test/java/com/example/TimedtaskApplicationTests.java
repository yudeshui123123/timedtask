package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.MyTimedTask;
import com.example.service.IMyTimedTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
class TimedtaskApplicationTests {

    @Autowired
    private IMyTimedTaskService myTimedTaskService;

    @Test
    void contextLoads() {

        List<MyTimedTask> list = myTimedTaskService.list();
        QueryWrapper<MyTimedTask> queryWrapper = new QueryWrapper();
        queryWrapper
                .eq("status","0")
                .isNotNull("task_name");
        List<MyTimedTask> list1 = myTimedTaskService.list(queryWrapper);
        System.out.println(list);
        //myTimedTaskService.list();
    }

}
