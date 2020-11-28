package com.example.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.MyTimedTask;
import com.example.service.IMyTimedTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/28 11:09
 * @description:
 */
@Component
@Slf4j
public class MyTimedTaskConfig implements SchedulingConfigurer {

    @Autowired
    private IMyTimedTaskService iMyTimedTaskService;

    /**
     * 添加定时任务和定时时间
     *
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        List<MyTimedTask> tasks = getMyTimeTaskList();

        //region 循环添加任务
        for (int i = 0; i < tasks.size(); i++) {
            scheduledTaskRegistrar.addTriggerTask(getRunnable(tasks.get(i)), getTrigger(tasks.get(i)));
        }
        //endregion
    }

    /**
     * 获取可运行的任务
     * @param task
     * @return
     */
    private Runnable getRunnable(MyTimedTask task) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Class clazz = this.getClass().getClassLoader().loadClass(task.getClassName());
                    Object obj = SpringUtil.getBean(clazz);
                    Method method = obj.getClass().getMethod(task.getMethodName(), null);
                    method.invoke(obj);
                } catch (InvocationTargetException e) {
                    log.error("refect exception:" + task.getClassName() + ";" + task.getMethodName() + ";" + e.getMessage());
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        };
    }

    /**
     * 获取Trigger
     *
     * @param task
     * @return
     */
    private Trigger getTrigger(MyTimedTask task) {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //将Cron 0/1 * * * * ?
                CronTrigger trigger = new CronTrigger(task.getCron());
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };
    }

    /**
     * 校验数据
     *
     * @param list
     * @return
     */
    private List<MyTimedTask> checkDataList(List<MyTimedTask> list) {
        String msg = "";
        for (int i = 0; i < list.size(); i++) {
            if (!checkOneData(list.get(i)).equalsIgnoreCase("ok")) {
                msg += list.get(i).getTaskName() + ";";
                list.remove(list.get(i));
                i--;
            }
            ;
        }
        if (msg.equals("")) {
            msg = "未启动的任务:" + msg;
            log.error(msg);
        }
        return list;
    }

    /**
     * 按每一条校验数据
     *
     * @param task
     * @return
     */
    private String checkOneData(MyTimedTask task) {
        String result = "ok";
        Class cal = null;
        try {
            cal = Class.forName(task.getClassName());
            Object obj = SpringUtil.getBean(cal);
            Method method = obj.getClass().getMethod(task.getMethodName(), null);
            String cron = task.getCron();
            if (cron.equals("")) {
                result = "no found the cron:" + task.getTaskName();
                log.error(result);
            }
        } catch (ClassNotFoundException e) {
            result = "not found the class:" + task.getClassName() + e.getMessage();
            log.error(result);
        } catch (NoSuchMethodException e) {
            result = "not found the method:" + task.getClassName() + ";" + task.getMethodName() + ";" + e.getMessage();
            log.error(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 获取所有任务
     *
     * @return
     */
    private List<MyTimedTask> getMyTimeTaskList() {
        QueryWrapper<MyTimedTask> myTimedTaskQueryWrapper = new QueryWrapper<>();
        myTimedTaskQueryWrapper.eq("status", "0");
        return iMyTimedTaskService.list(myTimedTaskQueryWrapper);
    }


}
