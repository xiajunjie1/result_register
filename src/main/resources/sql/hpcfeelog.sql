/*
Navicat MySQL Data Transfer

Source Server         : register
Source Server Version : 50736
Source Host           : 192.168.179.189:13306
Source Database       : registerdb

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2023-11-20 14:59:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hpcfeelog
-- ----------------------------
DROP TABLE IF EXISTS `hpcfeelog`;
CREATE TABLE `hpcfeelog` (
  `id` int(10) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `factcharge` double DEFAULT NULL,
  `feemonth` varchar(30) DEFAULT NULL,
  `feetype` varchar(255) DEFAULT NULL,
  `now` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
