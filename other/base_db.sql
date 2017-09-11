/*
Navicat MySQL Data Transfer

Source Server         : CentOS
Source Server Version : 50636
Source Host           : 47.94.203.164:3306
Source Database       : base_db

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-09-11 17:11:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for e_event
-- ----------------------------
DROP TABLE IF EXISTS `e_event`;
CREATE TABLE `e_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_event
-- ----------------------------

-- ----------------------------
-- Table structure for e_record
-- ----------------------------
DROP TABLE IF EXISTS `e_record`;
CREATE TABLE `e_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `detail` varchar(1000) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `sys_ver` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `ex` varchar(255) DEFAULT NULL,
  `ex1` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_record
-- ----------------------------

-- ----------------------------
-- Table structure for u_role
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `role_type` int(11) NOT NULL,
  `role_des` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES ('1', 'Admin', '0', 'Admin User', '2017-09-07 15:55:49', '2017-09-07 15:55:56');
INSERT INTO `u_role` VALUES ('2', 'Normal', '1', 'Normal User', '2017-09-07 15:58:53', '2017-09-07 15:58:57');

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `rule_id` int(11) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_salt` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', 'ea521a70c007c80d5ae24c95c6b410dd', '1', 'admin', 'uxq2jzqs77RoWT26m6Nraw==', '7919faf4f90a2a89e8aab878993c2a3d', '1', '2017-09-07 15:58:01', '2017-09-09 01:21:41', 'AddBean');

-- ----------------------------
-- Table structure for u_user_detail
-- ----------------------------
DROP TABLE IF EXISTS `u_user_detail`;
CREATE TABLE `u_user_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `age` varchar(256) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user_detail
-- ----------------------------
INSERT INTO `u_user_detail` VALUES ('4', '1', '24', '18788480955', '0', '上海市', '', '');
INSERT INTO `u_user_detail` VALUES ('5', '2', null, null, '0', null, null, null);
INSERT INTO `u_user_detail` VALUES ('6', '3', null, null, null, null, null, null);
INSERT INTO `u_user_detail` VALUES ('7', '4', null, null, null, null, null, null);
INSERT INTO `u_user_detail` VALUES ('8', '5', null, null, null, null, null, null);
