-- ----------------------------
-- 1. 列车基础信息表（train_basic）- 固定列车信息
-- ----------------------------
DROP TABLE IF EXISTS `train_basic`;
CREATE TABLE `train_basic` (
  `basic_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID（自增）',
  `train_no` varchar(20) NOT NULL COMMENT '列车号（如G123、D456）',
  `train_type` varchar(10) NOT NULL COMMENT '列车类型（0-高铁/1-动车/2-普快）',
  `railway_bureau` varchar(30) NOT NULL COMMENT '所属铁路局（如北京局）',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0-正常/1-停运）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`basic_id`),
  UNIQUE KEY `uk_train_no` (`train_no`) COMMENT '列车号唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='列车基础信息表（固定不变）';

-- ----------------------------
-- 2. 车次信息表（train_schedule）- 按日期区分的车次
-- ----------------------------
DROP TABLE IF EXISTS `train_schedule`;
CREATE TABLE `train_schedule` (
  `schedule_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID（自增）',
  `train_no` varchar(20) NOT NULL COMMENT '列车号（关联train_basic.train_no）',
  `depart_date` date NOT NULL COMMENT '发车日期（如2025-12-28）',
  `start_station` varchar(30) NOT NULL COMMENT '始发站（如北京南站）',
  `end_station` varchar(30) NOT NULL COMMENT '终点站（如上海虹桥站）',
  `total_duration` varchar(20) DEFAULT NULL COMMENT '总时长（如4小时20分）',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '车次状态（0-正常/1-停运）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`schedule_id`),
  UNIQUE KEY `uk_train_no_date` (`train_no`,`depart_date`) COMMENT '同一列车同一日期唯一',
  KEY `idx_start_end_station` (`start_station`,`end_station`) COMMENT '起点终点查询索引',
  KEY `idx_depart_date` (`depart_date`) COMMENT '发车日期查询索引',
  -- 外键关联列车基础表
  CONSTRAINT `fk_schedule_train_basic` FOREIGN KEY (`train_no`) REFERENCES `train_basic` (`train_no`)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车次信息表（按日期区分）';

-- ----------------------------
-- 3. 车次经停站点表（train_schedule_station）- 经停站点+里程
-- ----------------------------
DROP TABLE IF EXISTS `train_schedule_station`;
CREATE TABLE `train_schedule_station` (
  `station_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID（自增）',
  `schedule_id` bigint NOT NULL COMMENT '车次ID（关联train_schedule.schedule_id）',
  `station_name` varchar(30) NOT NULL COMMENT '站点名称（如济南西站）',
  `station_order` int NOT NULL COMMENT '站点顺序（1-始发站/2-经停站）',
  `arrive_time` time DEFAULT NULL COMMENT '到站时间（如08:30:00，始发站无）',
  `depart_time` time NOT NULL COMMENT '发车时间（如08:35:00，终点站无）',
  `platform_no` varchar(10) DEFAULT NULL COMMENT '站台号（如3站台）',
  `stop_duration` varchar(10) DEFAULT NULL COMMENT '停靠时长（如5分钟）',
  `cumulative_mileage` int NOT NULL COMMENT '累计里程（单位：公里，始发站为0）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`station_id`),
  KEY `idx_schedule_id` (`schedule_id`) COMMENT '车次ID查询索引',
  KEY `idx_schedule_station` (`schedule_id`,`station_name`) COMMENT '车次+站点精准查询',
  KEY `idx_station_name` (`station_name`) COMMENT '站点名称查询索引',
  -- 外键关联车次表
  CONSTRAINT `fk_station_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `train_schedule` (`schedule_id`)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车次经停站点表';

-- ----------------------------
-- 4. 车厢信息表（train_carriage）- 车次下的车厢
-- ----------------------------
DROP TABLE IF EXISTS `train_carriage`;
CREATE TABLE `train_carriage` (
  `carriage_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID（自增）',
  `schedule_id` bigint NOT NULL COMMENT '车次ID（关联train_schedule.schedule_id）',
  `carriage_no` varchar(10) NOT NULL COMMENT '车厢号（如02车厢）',
  `carriage_type` varchar(10) NOT NULL COMMENT '车厢类型（0-二等座/1-一等座/2-商务座/3-硬卧/4-软卧）',
  `total_seats` int NOT NULL COMMENT '总座位数（如80）',
  `available_seats` int NOT NULL DEFAULT 0 COMMENT '可用座位数（如78）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`carriage_id`),
  KEY `idx_schedule_carriage` (`schedule_id`) COMMENT '车次ID查询索引',
  UNIQUE KEY `uk_schedule_carriage_no` (`schedule_id`,`carriage_no`) COMMENT '同一车次车厢号唯一',
  -- 外键关联车次表
  CONSTRAINT `fk_carriage_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `train_schedule` (`schedule_id`)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车厢信息表（归属车次）';

-- ----------------------------
-- 5. 座位信息表（train_seat）- 车厢下的座位
-- ----------------------------
DROP TABLE IF EXISTS `train_seat`;
CREATE TABLE `train_seat` (
  `seat_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID（自增）',
  `carriage_id` bigint NOT NULL COMMENT '车厢ID（关联train_carriage.carriage_id）',
  `seat_no` varchar(10) NOT NULL COMMENT '座位编号（如02A/05B）',
  `seat_type` varchar(10) NOT NULL COMMENT '座位类型（同车厢类型）',
  `seat_status` char(1) NOT NULL DEFAULT '0' COMMENT '座位状态（0-未售/1-已售/2-锁定）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`seat_id`),
  KEY `idx_carriage_id` (`carriage_id`) COMMENT '车厢ID查询索引',
  UNIQUE KEY `uk_carriage_seat_no` (`carriage_id`,`seat_no`) COMMENT '同一车厢座位号唯一',
  KEY `idx_seat_status` (`seat_status`) COMMENT '座位状态查询索引',
  -- 外键关联车厢表
  CONSTRAINT `fk_seat_carriage` FOREIGN KEY (`carriage_id`) REFERENCES `train_carriage` (`carriage_id`)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='座位信息表（归属车厢）';

-- ----------------------------
-- 6. 系统参数表（sys_system_param）- 用于配置票价基础单价（可选）
-- ----------------------------
DROP TABLE IF EXISTS `sys_system_param`;
CREATE TABLE `sys_system_param` (
  `param_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `param_key` varchar(50) NOT NULL COMMENT '参数键',
  `param_value` varchar(50) NOT NULL COMMENT '参数值',
  `param_desc` varchar(200) DEFAULT NULL COMMENT '参数描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`param_id`),
  UNIQUE KEY `uk_param_key` (`param_key`) COMMENT '参数键唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统参数表（存储票价基础单价等）';

-- 插入票价基础单价参数（1元/公里）
INSERT INTO `sys_system_param` (`param_key`, `param_value`, `param_desc`)
VALUES ('train_base_price', '1.0', '列车基础票价单价（单位：元/公里）');

-- ----------------------------
-- 7. 若依字典数据初始化（座位类型价格系数）
-- ----------------------------
-- 假设若依字典表已存在，插入座位类型系数数据
-- 字典类型：seat_type_coefficient（座位类型价格系数）
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_code`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `remark`)
VALUES
(1, 'seat_type_coefficient_0', '二等座', '1.0', 'seat_type_coefficient', '', '', 'Y', '0', 'admin', NOW(), '二等座价格系数'),
(2, 'seat_type_coefficient_1', '一等座', '1.8', 'seat_type_coefficient', '', '', 'N', '0', 'admin', NOW(), '一等座价格系数'),
(3, 'seat_type_coefficient_2', '商务座', '3.0', 'seat_type_coefficient', '', '', 'N', '0', 'admin', NOW(), '商务座价格系数'),
(4, 'seat_type_coefficient_3', '硬卧', '1.5', 'seat_type_coefficient', '', '', 'N', '0', 'admin', NOW(), '硬卧价格系数'),
(5, 'seat_type_coefficient_4', '软卧', '2.0', 'seat_type_coefficient', '', '', 'N', '0', 'admin', NOW(), '软卧价格系数');