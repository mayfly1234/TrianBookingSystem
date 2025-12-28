-- ----------------------------
-- 1. 先更新字典表（适配硬座/卧铺类型）
-- ----------------------------
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_code`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `remark`) VALUES
(6, 'seat_type_coefficient_5', '硬座', '1.0', 'seat_type_coefficient', '', '', 'N', '0', 'admin', NOW(), 'admin', NOW(), '硬座价格系数'),
(7, 'seat_type_coefficient_6', '卧铺', '1.5', 'seat_type_coefficient', '', '', 'N', '0', 'admin', NOW(), 'admin', NOW(), '卧铺价格系数');

-- ----------------------------
-- 2. 列车基础信息（train_basic）- 10条
-- ----------------------------
INSERT INTO `train_basic` (`train_no`, `train_type`, `railway_bureau`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
('G1', '0', '北京局', '0', 'admin', NOW(), 'admin', NOW(), '北京南→上海虹桥'),
('G2', '0', '上海局', '0', 'admin', NOW(), 'admin', NOW(), '上海虹桥→北京南'),
('G3', '0', '北京局', '0', 'admin', NOW(), 'admin', NOW(), '北京南→上海虹桥'),
('G4', '0', '上海局', '0', 'admin', NOW(), 'admin', NOW(), '上海虹桥→北京南'),
('G5', '0', '北京局', '0', 'admin', NOW(), 'admin', NOW(), '北京南→杭州东'),
('G6', '0', '上海局', '0', 'admin', NOW(), 'admin', NOW(), '杭州东→北京南'),
('G7', '0', '广州局', '0', 'admin', NOW(), 'admin', NOW(), '北京西→广州南'),
('G8', '0', '广州局', '0', 'admin', NOW(), 'admin', NOW(), '广州南→北京西'),
('G9', '0', '郑州局', '0', 'admin', NOW(), 'admin', NOW(), '郑州东→深圳北'),
('G10', '0', '郑州局', '0', 'admin', NOW(), 'admin', NOW(), '深圳北→郑州东');

-- ----------------------------
-- 3. 车次信息（train_schedule）- 10条（2025-12-28发车）
-- ----------------------------
INSERT INTO `train_schedule` (`train_no`, `depart_date`, `start_station`, `end_station`, `total_duration`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
('G1', '2025-12-28', '北京南站', '上海虹桥站', '4小时30分', '0', 'admin', NOW(), 'admin', NOW(), '08:00发车'),
('G2', '2025-12-28', '上海虹桥站', '北京南站', '4小时30分', '0', 'admin', NOW(), 'admin', NOW(), '08:00发车'),
('G3', '2025-12-28', '北京南站', '上海虹桥站', '4小时40分', '0', 'admin', NOW(), 'admin', NOW(), '09:00发车'),
('G4', '2025-12-28', '上海虹桥站', '北京南站', '4小时40分', '0', 'admin', NOW(), 'admin', NOW(), '09:00发车'),
('G5', '2025-12-28', '北京南站', '杭州东站', '5小时10分', '0', 'admin', NOW(), 'admin', NOW(), '08:30发车'),
('G6', '2025-12-28', '杭州东站', '北京南站', '5小时10分', '0', 'admin', NOW(), 'admin', NOW(), '08:30发车'),
('G7', '2025-12-28', '北京西站', '广州南站', '8小时05分', '0', 'admin', NOW(), 'admin', NOW(), '07:00发车'),
('G8', '2025-12-28', '广州南站', '北京西站', '8小时05分', '0', 'admin', NOW(), 'admin', NOW(), '07:00发车'),
('G9', '2025-12-28', '郑州东站', '深圳北站', '6小时20分', '0', 'admin', NOW(), 'admin', NOW(), '08:15发车'),
('G10', '2025-12-28', '深圳北站', '郑州东站', '6小时20分', '0', 'admin', NOW(), 'admin', NOW(), '08:15发车');

-- ----------------------------
-- 4. 车次经停站点（修正depart_time为NULL的问题）
-- 注意：schedule_id需替换为你数据库中实际的自增ID！
-- ----------------------------
-- G1（schedule_id=1）
INSERT INTO `train_schedule_station` (`schedule_id`, `station_name`, `station_order`, `arrive_time`, `depart_time`, `platform_no`, `stop_duration`, `cumulative_mileage`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, '北京南站', 1, NULL, '08:00:00', '10', NULL, 0, 'admin', NOW(), 'admin', NOW(), '始发站'),
(1, '济南西站', 2, '09:30:00', '09:35:00', '5', '5分钟', 406, 'admin', NOW(), 'admin', NOW(), '经停站'),
(1, '上海虹桥站', 3, '12:30:00', NULL, '15', NULL, 1318, 'admin', NOW(), 'admin', NOW(), '终点站');

-- G7（schedule_id=7）
INSERT INTO `train_schedule_station` (`schedule_id`, `station_name`, `station_order`, `arrive_time`, `depart_time`, `platform_no`, `stop_duration`, `cumulative_mileage`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(7, '北京西站', 1, NULL, '07:00:00', '8', NULL, 0, 'admin', NOW(), 'admin', NOW(), '始发站'),
(7, '郑州东站', 2, '09:30:00', '09:35:00', '6', '5分钟', 693, 'admin', NOW(), 'admin', NOW(), '经停站'),
(7, '广州南站', 3, '15:05:00', NULL, '12', NULL, 2298, 'admin', NOW(), 'admin', NOW(), '终点站');

-- G9（schedule_id=9）
INSERT INTO `train_schedule_station` (`schedule_id`, `station_name`, `station_order`, `arrive_time`, `depart_time`, `platform_no`, `stop_duration`, `cumulative_mileage`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(9, '郑州东站', 1, NULL, '08:15:00', '9', NULL, 0, 'admin', NOW(), 'admin', NOW(), '始发站'),
(9, '广州南站', 2, '12:50:00', '12:55:00', '11', '5分钟', 1605, 'admin', NOW(), 'admin', NOW(), '经停站'),
(9, '深圳北站', 3, '14:35:00', NULL, '8', NULL, 2136, 'admin', NOW(), 'admin', NOW(), '终点站');

-- ----------------------------
-- 5. 车厢信息（每个车次3节：01硬座、02硬座、03卧铺）
-- 注意：schedule_id需替换为实际ID！
-- ----------------------------
INSERT INTO `train_carriage` (`schedule_id`, `carriage_no`, `carriage_type`, `total_seats`, `available_seats`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
-- G1（schedule_id=1）
(1, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(1, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(1, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G2（schedule_id=2）
(2, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(2, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(2, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G3（schedule_id=3）
(3, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(3, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(3, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G4（schedule_id=4）
(4, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(4, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(4, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G5（schedule_id=5）
(5, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(5, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(5, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G6（schedule_id=6）
(6, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(6, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(6, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G7（schedule_id=7）
(7, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(7, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(7, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G8（schedule_id=8）
(8, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(8, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(8, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G9（schedule_id=9）
(9, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(9, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(9, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢'),
-- G10（schedule_id=10）
(10, '01', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(10, '02', '0', 50, 50, 'admin', NOW(), 'admin', NOW(), '硬座车厢'),
(10, '03', '1', 10, 10, 'admin', NOW(), 'admin', NOW(), '卧铺车厢');

-- ----------------------------
-- 6. 座位信息（G1车次完整示例）
-- 注意：carriage_id需替换为实际ID！
-- ----------------------------
-- G1-01硬座车厢（carriage_id=1）- 50个座位
INSERT INTO `train_seat` (`carriage_id`, `seat_no`, `seat_type`, `seat_status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, '01', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '02', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '03', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '04', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '05', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '06', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '07', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '08', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '09', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '10', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '11', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '12', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '13', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '14', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '15', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '16', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '17', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '18', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '19', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '20', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '21', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '22', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '23', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '24', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '25', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '26', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '27', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '28', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '29', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '30', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '31', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '32', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '33', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '34', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '35', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '36', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '37', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '38', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '39', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '40', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '41', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '42', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '43', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '44', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '45', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '46', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '47', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '48', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '49', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(1, '50', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座');

-- G1-02硬座车厢（carriage_id=2）- 50个座位
INSERT INTO `train_seat` (`carriage_id`, `seat_no`, `seat_type`, `seat_status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(2, '01', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '02', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '03', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '04', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '05', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '06', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '07', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '08', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '09', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '10', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '11', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '12', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '13', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '14', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '15', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '16', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '17', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '18', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '19', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '20', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '21', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '22', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '23', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '24', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '25', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '26', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '27', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '28', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '29', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '30', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '31', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '32', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '33', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '34', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '35', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '36', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '37', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '38', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '39', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '40', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '41', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '42', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '43', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '44', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '45', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '46', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '47', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '48', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '49', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座'),
(2, '50', '0', '0', 'admin', NOW(), 'admin', NOW(), '硬座');

-- G1-03卧铺车厢（carriage_id=3）- 10个铺位
INSERT INTO `train_seat` (`carriage_id`, `seat_no`, `seat_type`, `seat_status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(3, '01', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '02', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '03', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '04', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '05', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '06', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '07', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '08', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '09', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺'),
(3, '10', '1', '0', 'admin', NOW(), 'admin', NOW(), '卧铺');