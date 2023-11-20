/*
Navicat MySQL Data Transfer

Source Server         : register
Source Server Version : 50736
Source Host           : 192.168.179.189:13306
Source Database       : registerdb

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2023-11-20 14:59:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for paper_register
-- ----------------------------
DROP TABLE IF EXISTS `paper_register`;
CREATE TABLE `paper_register` (
  `doi` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '论文名称',
  `sn` varchar(255) DEFAULT NULL COMMENT '刊号',
  `jtitle_vol` varchar(255) DEFAULT NULL COMMENT '刊名及卷期',
  `paper_class` tinyint(2) NOT NULL COMMENT '论文等级',
  `informant` varchar(20) DEFAULT NULL COMMENT '填报人',
  `account` varchar(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `date` varchar(20) NOT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `thanks` tinyint(1) DEFAULT NULL,
  `is_grant` tinyint(1) DEFAULT NULL,
  `bonus` double(6,2) DEFAULT '0.00',
  `purl` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`doi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
