package com.example.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 定时任务表
 * </p>
 *
 * @author yds
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("myTimedTask")
public class MyTimedTask implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 数据类别编号
     */
    private String cron;

    /**
     * 全类名
     */
    private String className;

    /**
     * 执行的方法名
     */
    private String methodName;

    /**
     * 状态：0.启用，1.停用
     */
    private String status;

    /**
     * 数据描述
     */
    private String dataDescribe;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;


}
