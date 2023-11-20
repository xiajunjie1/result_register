/*
Navicat MySQL Data Transfer

Source Server         : register
Source Server Version : 50736
Source Host           : 192.168.179.189:13306
Source Database       : registerdb

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2023-11-20 14:59:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for feeuser
-- ----------------------------
DROP TABLE IF EXISTS `feeuser`;
CREATE TABLE `feeuser` (
  `userid` varchar(20) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
