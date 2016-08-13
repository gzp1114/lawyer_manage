/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : lawyer_manage

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-08-13 11:08:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chinacourt_fygg
-- ----------------------------
DROP TABLE IF EXISTS `chinacourt_fygg`;
CREATE TABLE `chinacourt_fygg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `debtor_name` text COMMENT '债务人名称',
  `announcement_court` text COMMENT '公告法院',
  `announcement_date` text COMMENT '公告时间',
  `published_page` text COMMENT '刊登版面',
  `bak` text COMMENT '备注',
  `url` text COMMENT '详情链接地址',
  `savetime` datetime DEFAULT NULL COMMENT '保存时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `url_unique` (`url`(255))
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for court
-- ----------------------------
DROP TABLE IF EXISTS `court`;
CREATE TABLE `court` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `caseId` varchar(255) DEFAULT NULL COMMENT '案件标号',
  `pname` varchar(255) DEFAULT NULL COMMENT '被执行人姓名/名称',
  `partyCardNum` varchar(255) DEFAULT NULL COMMENT '身份证号码/组织机构代码',
  `execCourtName` varchar(255) DEFAULT NULL COMMENT '执行法院',
  `courtcode` varchar(255) DEFAULT NULL COMMENT '法院编号（自己编号）',
  `casecodeself` varchar(255) DEFAULT NULL COMMENT '案件编号（自己编号）',
  `caseCreateTime` varchar(255) DEFAULT NULL COMMENT '立案时间',
  `caseCode` varchar(255) DEFAULT NULL COMMENT '案号',
  `execMoney` double DEFAULT NULL COMMENT '执行标的',
  `caseState` varchar(255) DEFAULT NULL COMMENT '案件状态',
  `savetime` datetime DEFAULT NULL,
  `beijingCourtState` int(1) DEFAULT '0' COMMENT '北京企业信息采集状态',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `caseId_unique` (`caseId`)
) ENGINE=InnoDB AUTO_INCREMENT=114847 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_announcement
-- ----------------------------
DROP TABLE IF EXISTS `sys_announcement`;
CREATE TABLE `sys_announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `debtor_company_id` int(11) DEFAULT NULL COMMENT '债务人id',
  `announcement_court` varchar(20) DEFAULT NULL COMMENT '公告法院',
  `announcement_date` varchar(20) DEFAULT NULL COMMENT '公告日期',
  `published_page` varchar(50) DEFAULT NULL COMMENT '刊登版面',
  `note` text COMMENT '备注',
  `url` varchar(100) DEFAULT NULL COMMENT '详情链接地址',
  `createtime` char(10) DEFAULT NULL COMMENT '创建时间',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公告信息';

-- ----------------------------
-- Table structure for sys_case_clear_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_case_clear_record`;
CREATE TABLE `sys_case_clear_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',
  `case_id` int(11) DEFAULT NULL COMMENT '����id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������������';

-- ----------------------------
-- Table structure for sys_claim_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_claim_company`;
CREATE TABLE `sys_claim_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `organization_code` varchar(20) DEFAULT NULL COMMENT '组织结构代码',
  `register_number` varbinary(32) DEFAULT NULL COMMENT '公司注册号',
  `type` varchar(30) DEFAULT NULL COMMENT '公司类型',
  `corporate_name` varchar(20) DEFAULT NULL COMMENT '公司法人',
  `corporate_cardnum` varchar(50) DEFAULT NULL COMMENT '法人身份证号',
  `establish_date` varchar(20) DEFAULT NULL COMMENT '公司成立日期',
  `register_capital` varchar(20) DEFAULT NULL COMMENT '注册资金',
  `business_start_date` varchar(20) DEFAULT NULL COMMENT '营业开始日期',
  `business_end_date` varchar(20) DEFAULT NULL COMMENT '营业结束日期',
  `registration_authority` varchar(20) DEFAULT NULL COMMENT '登记机关',
  `address` varchar(100) DEFAULT NULL COMMENT '公司地址',
  `operate_status` varchar(10) DEFAULT NULL COMMENT '企业经营状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `market_mark` tinyint(2) DEFAULT NULL COMMENT '市场标记    0其他   1是    2否    3北京   4非',
  `mark_time` datetime DEFAULT NULL COMMENT '市场标记时间',
  `mark_note` text COMMENT '市场标记备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='债权人信息';

-- ----------------------------
-- Table structure for sys_claim_shareholder
-- ----------------------------
DROP TABLE IF EXISTS `sys_claim_shareholder`;
CREATE TABLE `sys_claim_shareholder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `claim_id` int(11) DEFAULT NULL COMMENT '债权人id',
  `name` varchar(20) DEFAULT NULL COMMENT '股东名称',
  `description` varchar(30) DEFAULT NULL COMMENT '股东描述',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_contect_email
-- ----------------------------
DROP TABLE IF EXISTS `sys_contect_email`;
CREATE TABLE `sys_contect_email` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` int(11) DEFAULT NULL COMMENT '案源信息id',
  `email_address` int(30) DEFAULT NULL COMMENT '邮件地址',
  `user_id` int(11) DEFAULT NULL COMMENT '发送用户id',
  `send_time` varchar(20) DEFAULT NULL COMMENT '快递发送时间',
  `send_result` varchar(30) DEFAULT NULL COMMENT '发送结果',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系邮件';

-- ----------------------------
-- Table structure for sys_contect_express
-- ----------------------------
DROP TABLE IF EXISTS `sys_contect_express`;
CREATE TABLE `sys_contect_express` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` int(11) DEFAULT NULL COMMENT '案源信息id',
  `track_number` int(30) DEFAULT NULL COMMENT '运单编号',
  `user_id` int(11) DEFAULT NULL COMMENT '发送用户id',
  `send_time` varchar(20) DEFAULT NULL COMMENT '快递发送时间',
  `send_result` varchar(30) DEFAULT NULL COMMENT '发送结果',
  `accept_name` varchar(20) DEFAULT NULL COMMENT '接收人',
  `accept_time` varchar(20) DEFAULT NULL COMMENT '接收时间',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系快递';

-- ----------------------------
-- Table structure for sys_contect_fax
-- ----------------------------
DROP TABLE IF EXISTS `sys_contect_fax`;
CREATE TABLE `sys_contect_fax` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` int(11) DEFAULT NULL COMMENT '案源信息id',
  `user_id` int(11) DEFAULT NULL COMMENT '发送用户id',
  `send_time` varchar(20) DEFAULT NULL COMMENT '快递发送时间',
  `send_result` varchar(30) DEFAULT NULL COMMENT '发送结果',
  `accept_name` varchar(20) DEFAULT NULL COMMENT '接收人',
  `accept_number` varchar(20) DEFAULT NULL COMMENT '接收时间',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系传真';

-- ----------------------------
-- Table structure for sys_contect_interview
-- ----------------------------
DROP TABLE IF EXISTS `sys_contect_interview`;
CREATE TABLE `sys_contect_interview` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` int(11) DEFAULT NULL COMMENT '案源信息id',
  `user_id` int(11) DEFAULT NULL COMMENT '跟进人id',
  `send_time` varchar(20) DEFAULT NULL COMMENT '快递发送时间',
  `send_result` varchar(100) DEFAULT NULL COMMENT '发送结果',
  `interview_name` varchar(20) DEFAULT NULL COMMENT '约谈人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系约谈';

-- ----------------------------
-- Table structure for sys_contect_phone
-- ----------------------------
DROP TABLE IF EXISTS `sys_contect_phone`;
CREATE TABLE `sys_contect_phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` int(11) DEFAULT NULL COMMENT '案源信息id',
  `user_id` int(11) DEFAULT NULL COMMENT '打电话人id',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '打电话号码',
  `phone_time` varchar(20) DEFAULT NULL COMMENT '打电话时间',
  `phone_result` varchar(30) DEFAULT NULL COMMENT '打电话结果',
  `contect_name` varchar(20) DEFAULT NULL COMMENT '联系人名称',
  `contect_phone` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系电话';

-- ----------------------------
-- Table structure for sys_contect_sign
-- ----------------------------
DROP TABLE IF EXISTS `sys_contect_sign`;
CREATE TABLE `sys_contect_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` int(11) DEFAULT NULL COMMENT '案源信息id',
  `sign_number` varchar(30) DEFAULT NULL COMMENT '签约号',
  `user_id` int(11) DEFAULT NULL COMMENT '签约人id',
  `sign_time` varchar(20) DEFAULT NULL COMMENT '签约时间',
  `sign_result` varchar(30) DEFAULT NULL COMMENT '签约比例',
  `contect_name` varchar(20) DEFAULT NULL COMMENT '签约联系人',
  `contect_phone` varchar(20) DEFAULT NULL COMMENT '签约联系电话',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='签约信息';

-- ----------------------------
-- Table structure for sys_contect_visit
-- ----------------------------
DROP TABLE IF EXISTS `sys_contect_visit`;
CREATE TABLE `sys_contect_visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` int(11) DEFAULT NULL COMMENT '案源信息id',
  `user_id` int(11) DEFAULT NULL COMMENT '拜访人id',
  `visit_time` varchar(20) DEFAULT NULL COMMENT '拜访时间',
  `visit_result` varchar(30) DEFAULT NULL COMMENT '拜访结果',
  `contect_name` varchar(20) DEFAULT NULL COMMENT '被拜访人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系登门拜访';

-- ----------------------------
-- Table structure for sys_debtor
-- ----------------------------
DROP TABLE IF EXISTS `sys_debtor`;
CREATE TABLE `sys_debtor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `debtor_company_id` int(11) DEFAULT NULL COMMENT '债务公司id',
  `case_id` varchar(20) DEFAULT NULL COMMENT '案件标号',
  `exec_courtname` varchar(20) DEFAULT NULL COMMENT '执行法院',
  `courtcode` varchar(10) DEFAULT NULL COMMENT '法院编号',
  `case_createTime` varchar(20) DEFAULT NULL COMMENT '立案时间',
  `casecode` varchar(20) DEFAULT NULL COMMENT '案号',
  `execute_money` double DEFAULT NULL COMMENT '执行标的',
  `case_state` varchar(10) DEFAULT NULL COMMENT '案件状态',
  `note` text COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cid_casecode` (`debtor_company_id`,`casecode`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='被执行信息';

-- ----------------------------
-- Table structure for sys_debtor_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_debtor_company`;
CREATE TABLE `sys_debtor_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `organization_code` varchar(20) DEFAULT NULL COMMENT '组织结构代码',
  `register_number` varchar(32) DEFAULT NULL COMMENT '公司注册号',
  `type` varchar(30) DEFAULT NULL COMMENT '公司类型',
  `corporate_name` varchar(20) DEFAULT NULL COMMENT '公司法人',
  `corporate_cardnum` varchar(50) DEFAULT NULL COMMENT '法人身份证号',
  `establish_date` varchar(20) DEFAULT NULL COMMENT '公司成立日期',
  `register_capital` varchar(20) DEFAULT NULL COMMENT '注册资金',
  `business_start_date` varchar(20) DEFAULT NULL COMMENT '营业开始日期',
  `business_end_date` varchar(20) DEFAULT NULL COMMENT '营业结束日期',
  `registration_authority` varchar(20) DEFAULT NULL COMMENT '登记机关',
  `address` varchar(100) DEFAULT NULL COMMENT '公司地址',
  `operate_status` varchar(10) DEFAULT NULL COMMENT '企业经营状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='债务人信息';

-- ----------------------------
-- Table structure for sys_debtor_shareholder
-- ----------------------------
DROP TABLE IF EXISTS `sys_debtor_shareholder`;
CREATE TABLE `sys_debtor_shareholder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `debtor_id` int(11) DEFAULT NULL COMMENT '债务人id',
  `name` varchar(20) DEFAULT NULL COMMENT '股东名称',
  `description` varchar(30) DEFAULT NULL COMMENT '股东描述',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_lawyer_case
-- ----------------------------
DROP TABLE IF EXISTS `sys_lawyer_case`;
CREATE TABLE `sys_lawyer_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_id` int(11) DEFAULT NULL COMMENT '案源id',
  `sign_id` int(11) DEFAULT NULL COMMENT '签约信息id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案件信息';

-- ----------------------------
-- Table structure for sys_lawyer_source
-- ----------------------------
DROP TABLE IF EXISTS `sys_lawyer_source`;
CREATE TABLE `sys_lawyer_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `debtor_id` int(11) DEFAULT NULL COMMENT '债务人id',
  `claim_id` int(11) DEFAULT NULL COMMENT '债权人id',
  `user_id` int(11) DEFAULT NULL COMMENT '联系人id',
  `contect_time` datetime DEFAULT NULL COMMENT '联系时间',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `note` text COMMENT '备注',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案源信息';

-- ----------------------------
-- Table structure for sys_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_function`;
CREATE TABLE `sys_menu_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) DEFAULT NULL COMMENT '上一级菜单主键',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户编号',
  `menu_name` varchar(200) DEFAULT NULL COMMENT '菜单名',
  `menu_type` tinyint(2) DEFAULT NULL COMMENT '菜单类型，值：1为模块，2为菜单，3为功能',
  `is_valid` tinyint(2) DEFAULT NULL COMMENT '是否有效，值：0为无效，1为有效',
  `menu_url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `menu_info` text COMMENT '菜单功能说明',
  `note` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间截',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='菜单功能表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `status` int(5) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) DEFAULT NULL COMMENT '父角色主键',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户编号,租户表外键',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `note` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间截',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户编号,租户表外键',
  `menu_function_id` int(11) DEFAULT NULL COMMENT '菜单功能外键ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色信息表外键ID',
  `note` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间截',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3561 DEFAULT CHARSET=utf8 COMMENT='角色菜单权限关系表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户编号,租户表外键',
  `user_session_id` int(11) DEFAULT NULL COMMENT '用户状态表外键ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色信息表外键ID',
  `note` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间截',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Table structure for sys_user_session
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_session`;
CREATE TABLE `sys_user_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_account` varchar(100) DEFAULT NULL COMMENT '登录账户',
  `login_password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `username` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL COMMENT '状态，值：1为正常、2为停用、3为删除',
  `session_id` varchar(100) DEFAULT NULL COMMENT '会话ID',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登陆时间',
  `login_times` int(8) DEFAULT '0' COMMENT '登陆次数',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '登陆IP',
  `note` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间截',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COMMENT='用户状态表';
