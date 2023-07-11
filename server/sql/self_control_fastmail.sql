/*
 Navicat Premium Data Transfer

 Source Server         : hcj
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : self_control_fastmail

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 11/07/2023 22:29:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fast_emps
-- ----------------------------
DROP TABLE IF EXISTS `fast_emps`;
CREATE TABLE `fast_emps`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '员工性别',
  `record` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工学历',
  `age` int(11) NOT NULL COMMENT '员工年龄',
  `job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工职位',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `fast_emps_ibfk_1` FOREIGN KEY (`id`) REFERENCES `fast_users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fast_emps
-- ----------------------------
INSERT INTO `fast_emps` VALUES ('1630588154602', '宋远桥', '男', '大专', 35, '管理');
INSERT INTO `fast_emps` VALUES ('1630588287777', '周颠', '男', '专科', 23, '员工');

-- ----------------------------
-- Table structure for fast_packs_logs
-- ----------------------------
DROP TABLE IF EXISTS `fast_packs_logs`;
CREATE TABLE `fast_packs_logs`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `send_address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄送地址',
  `send_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄送时间',
  `gain_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人姓名',
  `gain_address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收获人地址',
  `gain_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人联系电话',
  `collect_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '揽收时间',
  `collect_point` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '揽收网点',
  `pay_total` double NOT NULL COMMENT '支付费用',
  `status` int(11) NOT NULL COMMENT '快递状态 0:下单 1:揽收 2:运输 3:派送 4:签收',
  `send_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄件人姓名',
  `send_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄件人电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '快件记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fast_packs_logs
-- ----------------------------
INSERT INTO `fast_packs_logs` VALUES ('1630676223619', '武当十八号', '2022-01-03 21:37:03', '殷天正', '秦淮13号', '2301230231', '2022-01-04 09:57:03', '1626856139736', 20, 4, '张三丰', '27809231029');
INSERT INTO `fast_packs_logs` VALUES ('1630726034584', '昆仑区四单元120室', '2022-01-04 11:27:14', '成昆', '铁塔区三单元1029', '29001902912', '2022-01-04 11:27:14', '1626856139736', 23, 3, '何太冲', '29801020921');
INSERT INTO `fast_packs_logs` VALUES ('1631152001496', '武当十八号', '2022-01-09 09:46:41', '殷野王', '秦淮三十号', '321020392', '2022-01-09 09:46:41', '1630586526830', 12, 1, '张三丰', '2900821293');

-- ----------------------------
-- Table structure for fast_send_logs
-- ----------------------------
DROP TABLE IF EXISTS `fast_send_logs`;
CREATE TABLE `fast_send_logs`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `emp_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工ID',
  `send_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '派送时间',
  `gain_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件时间',
  `pack_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快件ID',
  `service_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务网点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '派送记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fast_send_logs
-- ----------------------------
INSERT INTO `fast_send_logs` VALUES ('1630750907721', '1626856139736', '2022-01-04 18:21:47', '2022-01-04 21:15:33', '1630676223619', '1626856139736');
INSERT INTO `fast_send_logs` VALUES ('1631152320470', '1630588154602', '2022-01-09 09:52:00', NULL, '1630726034584', '1630586581278');

-- ----------------------------
-- Table structure for fast_service_point
-- ----------------------------
DROP TABLE IF EXISTS `fast_service_point`;
CREATE TABLE `fast_service_point`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网点名称',
  `comm` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网点描述',
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网点信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fast_service_point
-- ----------------------------
INSERT INTO `fast_service_point` VALUES ('1630586526830', '北京营业部', '北京营业部', '2022-01-02 20:42:06');
INSERT INTO `fast_service_point` VALUES ('1630586562425', '保定营业部', '保定营业部222', '2022-01-02 20:42:42');
INSERT INTO `fast_service_point` VALUES ('1630586581278', '石家庄营业部', '石家庄营业部', '2022-01-02 20:43:01');
INSERT INTO `fast_service_point` VALUES ('1630586581279', '南京徐庄营业部', '南京徐庄营业部三楼', '2023-06-15 09:47:30');
INSERT INTO `fast_service_point` VALUES ('1630586581280', '南京马群营业部', '南京马群营业部', '2023-06-15 09:47:30');
INSERT INTO `fast_service_point` VALUES ('1630586581281', '南京邮电大学营业部', '学生活动中心一楼', '2023-06-15 09:47:30');
INSERT INTO `fast_service_point` VALUES ('1630586581282', '紫金学院营业部', '办公楼营业部', '2023-06-15 09:47:30');
INSERT INTO `fast_service_point` VALUES ('1686800966218', '江科大2', '一站式服务2', '2023-06-15 11:49:26');

-- ----------------------------
-- Table structure for fast_transit_logs
-- ----------------------------
DROP TABLE IF EXISTS `fast_transit_logs`;
CREATE TABLE `fast_transit_logs`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `send_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '运出时间',
  `send_point` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运出网点',
  `next_point` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下一站网点',
  `fast_pack_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快件ID',
  `emp_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理员工ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运输记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fast_transit_logs
-- ----------------------------
INSERT INTO `fast_transit_logs` VALUES ('1630743430527', '2022-01-04 16:17:10', '1626856139736', '1630586581278', '1630726034584', '1626856139736');
INSERT INTO `fast_transit_logs` VALUES ('1630748006828', '2022-01-04 17:33:26', '1626856139736', '1630586581278', '1630676223619', '1626856139736');
INSERT INTO `fast_transit_logs` VALUES ('1630749577530', '2022-01-04 17:59:37', '1630586581278', '1630586562425', '1630676223619', '1626856139736');

-- ----------------------------
-- Table structure for fast_users
-- ----------------------------
DROP TABLE IF EXISTS `fast_users`;
CREATE TABLE `fast_users`  (
  `id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `user_name` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `pass_word` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `type` int(11) NOT NULL COMMENT '用户身份  0:系统管理员  1:网点管理员  2:网点员工',
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册时间',
  `service_id` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网点ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `service_id`(`service_id`) USING BTREE,
  CONSTRAINT `fast_users_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `fast_service_point` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '快件用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fast_users
-- ----------------------------
INSERT INTO `fast_users` VALUES ('1630588154602', 'fater', 'fater', 1, '2022-01-02 21:09:14', '1630586581278');
INSERT INTO `fast_users` VALUES ('1630588287777', 'test1', 'test', 2, '2022-01-02 21:11:27', '1630586581278');
INSERT INTO `fast_users` VALUES ('1631151448513', 'zhang', 'zhang', 1, '2022-01-09 09:37:28', '1630586526830');
INSERT INTO `fast_users` VALUES ('1631151464767', 'wang', 'wang', 2, '2022-01-09 09:37:44', '1630586526830');
INSERT INTO `fast_users` VALUES ('1686816091742', 'hcj222', '', 1, '2023-06-15 16:01:31', '1630586581282');

SET FOREIGN_KEY_CHECKS = 1;
