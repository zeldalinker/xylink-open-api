/*
 Navicat Premium Data Transfer

 Source Server         : dev-lanxin
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 172.25.48.14:3306
 Source Schema         : lanxin

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 07/12/2020 17:54:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lanxin_call_history
-- ----------------------------
DROP TABLE IF EXISTS `meeting_call`;
CREATE TABLE `meeting_call` (
  `id` varchar(32) NOT NULL,
  `call_number` varchar(30) DEFAULT NULL,
  `user_phone` varchar(32) DEFAULT NULL COMMENT '创建人手机号',
  `display_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `call_time` bigint(20) DEFAULT NULL COMMENT '通话时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for lanxin_meeting_room
-- ----------------------------
DROP TABLE IF EXISTS `meeting_room`;
CREATE TABLE `meeting_room` (
  `id` varchar(32) NOT NULL,
  `meeting_id` varchar(100) DEFAULT NULL,
  `meeting_room_number` varchar(32) DEFAULT NULL,
  `user_phone` varchar(32) DEFAULT NULL COMMENT '创建人手机号',
  `user_display_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `title` varchar(255) DEFAULT NULL COMMENT '会议室标题',
  `start_time` bigint(20) DEFAULT '-1' COMMENT '会议开始时间',
  `end_time` bigint(20) DEFAULT '-1' COMMENT '会议结束时间',
  `meeting_url` varchar(255) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL COMMENT '第三方账号的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for lanxin_meeting_user
-- ----------------------------
DROP TABLE IF EXISTS `meeting_user`;
CREATE TABLE `meeting_user` (
  `id` varchar(32) NOT NULL,
  `meeting_id` varchar(32) DEFAULT NULL,
  `user_phone` varchar(32) DEFAULT NULL COMMENT '创建人手机号',
  `user_display_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `uid` varchar(32) DEFAULT NULL COMMENT '第三方账号的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
