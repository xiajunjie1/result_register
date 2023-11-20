/*
Navicat MySQL Data Transfer

Source Server         : register
Source Server Version : 50736
Source Host           : 192.168.179.189:13306
Source Database       : registerdb

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2023-11-20 15:00:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for result_summary
-- ----------------------------
DROP TABLE IF EXISTS `result_summary`;
CREATE TABLE `result_summary` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  `charge` double(14,2) DEFAULT '0.00',
  `total_result` double(14,2) DEFAULT '0.00',
  `paper` double(14,2) DEFAULT '0.00',
  `project` double(14,2) DEFAULT '0.00',
  `reward` double(14,2) DEFAULT '0.00',
  `reward_residue` double(14,2) DEFAULT '0.00',
  `reward_residue_last` double(14,2) DEFAULT '0.00',
  `charge_residue` double(14,2) DEFAULT '0.00',
  `charge_residue_last` double(14,2) DEFAULT '0.00',
  `note` varchar(255) DEFAULT NULL,
  `year` varchar(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
