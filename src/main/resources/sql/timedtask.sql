CREATE TABLE IF NOT EXISTS myTimedTask
(
	`id` varchar(64) not null PRIMARY KEY COMMENT "uuid主键",
	`task_name` varchar(64) COMMENT "任务名称",
	`cron` varchar(50) COMMENT "数据类别编号",
	`class_name` varchar(50) COMMENT "全类名",
	`method_name` varchar(50) COMMENT "执行的方法名",
	`status` varchar(10) default 0 COMMENT "状态：0.启用，1.停用",
	`data_describe` varchar(500) COMMENT "数据描述",
	`create_time` TIMESTAMP default now() COMMENT "创建时间",
	`modify_time` datetime default now() COMMENT "修改时间"
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务表';