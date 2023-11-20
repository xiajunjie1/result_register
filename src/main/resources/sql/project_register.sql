/*
Navicat MySQL Data Transfer

Source Server         : register
Source Server Version : 50736
Source Host           : 192.168.179.189:13306
Source Database       : registerdb

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2023-11-20 15:00:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_register
-- ----------------------------
DROP TABLE IF EXISTS `project_register`;
CREATE TABLE `project_register` (
  `pid` varchar(255) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `money` varchar(10) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `phost` varchar(255) DEFAULT NULL,
  `account` varchar(255) NOT NULL,
  `informant` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `isGrant` tinyint(1) DEFAULT '0',
  `bonus` double(10,2) DEFAULT '0.00',
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
