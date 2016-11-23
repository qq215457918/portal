/*
Navicat MySQL Data Transfer

Source Server         : crm
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-08-28 18:00:40
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `customer_culture_info`
-- ----------------------------
DROP TABLE IF EXISTS `customer_culture_info`;
CREATE TABLE `customer_culture_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `culture_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '文交所名称',
  `account_date` date DEFAULT NULL COMMENT '开户日期',
  `bank_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否银商绑定',
  `customer_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '客户姓名',
  `phone` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `code` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '会员代码',
  `update_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户文交所信息表';

-- ----------------------------
-- Records of customer_culture_info
-- ----------------------------

-- ----------------------------
-- Table structure for `customer_info`
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '客户分类\n0 空白客户\n1 重复登门\n2说明会\n3成单\n4锁定\n5转介绍',
  `season2` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '客户分类二级 无用',
  `season3` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '客户分类三级 无用',
  `season4` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '客户分类四级 无用',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `sex` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '性别 0男 1女',
  `phone_staff_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '客户人员id',
  `receiver_staff_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '业务人员id',
  `business_phone` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商务电话',
  `home_phone` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '电宅',
  `phone` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `visit_date` date DEFAULT NULL COMMENT '登门时间',
  `area` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '地区：大连、沈阳',
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `phone2` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '其他电话2',
  `relation_id` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '关联亲友',
  `qq` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq',
  `msn` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'msn',
  `site` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '网页',
  `id_card` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证',
  `birthday` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '生日',
  `product` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '产品',
  `transaction_amount` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `recent_visit_date` datetime DEFAULT NULL COMMENT '最近拜访时间',
  `recent_import_date` datetime DEFAULT NULL COMMENT '最近导入时间',
  `recent_export_date` datetime DEFAULT NULL COMMENT '最近导出时间',
  `blacklist_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '黑名单标志',
  `update_date` date DEFAULT NULL COMMENT '变成本类型客户时间',
  `update_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否更新标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表'
-- ----------------------------
-- Records of customer_info
-- ----------------------------

-- ----------------------------
-- Table structure for `employee_info`
-- ----------------------------
DROP TABLE IF EXISTS `employee_info`;
CREATE TABLE `employee_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `group_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '组ID',
  `department_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '部门ID',
  `organization_id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '机构ID',
  `name` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `login_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '登录名称',
  `password` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '登录密码',
  `reception_flag` varchar(1) COLLATE utf8_bin DEFAULT '0' COMMENT '接待标示(0未接待 1 正在接待)',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='员工信息表';

-- ----------------------------
-- Records of employee_info
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_info`
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `sort_id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '种类ID',
  `type` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '商品分类 0:常规商品 1：礼品 2：配售 3：配送 4:兑换',
  `code` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '商品序号',
  `name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '产品名称',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品金额',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(60) DEFAULT NULL COMMENT '单位',
  `trusteeship_flag` varchar(1) COLLATE utf8_bin DEFAULT '0' COMMENT '是否可托管',
  `repurchase_flag` varchar(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '回购标志',
  `repurchase_info` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '回购信息',
  `repurchase_starttime` date DEFAULT NULL COMMENT '回购开始时间',
  `repurchase_endtime` date DEFAULT NULL COMMENT '回购结束时间',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `update_date` date DEFAULT NULL COMMENT '更新日期',
  `create_userid` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人id',
  `update_userid` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人id',
  `delete_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '删除标志 0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品信息表';

-- ----------------------------
-- Records of goods_info
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_sort`
-- ----------------------------
DROP TABLE IF EXISTS `goods_sort`;
CREATE TABLE `goods_sort` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `parents_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '父节点',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `description` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品分类表';

-- ----------------------------
-- Records of goods_sort
-- ----------------------------

-- ----------------------------
-- Table structure for `group_info`
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `parents_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '上级ID',
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织结构表';

-- ----------------------------
-- Records of group_info
-- ----------------------------

-- ----------------------------
-- Table structure for `order_detail_info`
-- ----------------------------
DROP TABLE IF EXISTS `order_detail_info`;
CREATE TABLE `order_detail_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `order_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '订单ID',
  `good_sort_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商品分类ID',
  `good_sort_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '商品分类名称',
  `good_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商品ID',
  `good_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '商品类型（同good_info type）',
  `good_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `amount` int(11) DEFAULT NULL COMMENT '数量 如果退货数量为负',
  `delete_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '删除标志 0正常 1删除',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  `update_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail_info
-- ----------------------------

-- ----------------------------
-- Table structure for `order_info`
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `order_number` varchar(14) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `customer_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '客户id',
  `phone_staff_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '客服id',
  `receiver_staff_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '接待人员id',
  `status` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '订单状态 : 0未支付 1已支付 2已出库 3文交所已审核 4 已完成',
  `order_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '订单类型 1正常 2退货 3换货 4赠品',
  `pay_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支付类型  0全额支付 1定金支付 2派送支付 3余款支付',
  `pay_price` decimal(10,0) DEFAULT NULL COMMENT '订单金额',
  `actual_price` decimal(10,0) DEFAULT NULL COMMENT '实际支付金额',
  `finance_flag` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '支付状态(财务审核标志)\n0 未支付\n1已支付 如果order_type=4 为赠品，此字段就为领导审查状态',
  `finance_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支付接口\n接口1 接口2 微信支付等.',
  `finance_operator_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '财务审批人员',
  `finance_date` datetime DEFAULT NULL COMMENT '财务审批日期',
  `warehouse_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '仓库审批标志\n0未审核\n1 已审核',
  `warehouse_operator_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '仓库人员id',
  `warehouse_date` datetime DEFAULT NULL COMMENT '财务审批日期',
  `culture_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '文交所审批标志 \n0未审核\n1 已审核',
  `culture_operator_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '文交所人员id',
  `culture_date` datetime DEFAULT NULL COMMENT '文交所审批日期',
  `Remarks` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `create_date` datetime DEFAULT NULL,
  `create_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `delete_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

-- ----------------------------
-- Records of order_info
-- ----------------------------

-- ----------------------------
-- Table structure for `permission_info`
-- ----------------------------
DROP TABLE IF EXISTS `permission_info`;
CREATE TABLE `permission_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `menu_url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单url',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

-- ----------------------------
-- Records of permission_info
-- ----------------------------

-- ----------------------------
-- Table structure for `reception_info`
-- ----------------------------
DROP TABLE IF EXISTS `reception_info`;
CREATE TABLE `reception_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `customer_id` varchar(16) COLLATE utf8_bin NOT NULL,
  `phone_staff_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `receiver_staff_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '开始接待时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束接待时间',
  `order_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '订单id可为空',
  `present_order_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '赠品orderId可为空',
  `present_name` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '赠品orderId可为空',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日常接待表';

-- ----------------------------
-- Records of reception_info
-- ----------------------------

-- ----------------------------
-- Table structure for `report_good`
-- ----------------------------
DROP TABLE IF EXISTS `report_good`;
CREATE TABLE `report_good` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `good_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商品id',
  `good_type` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '商品类型',
  `good_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `amount` int(11) DEFAULT NULL COMMENT '商品数量',
  `price` decimal(10,0) DEFAULT NULL COMMENT '单价',
  `order_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='每日产品报表';

-- ----------------------------
-- Records of report_good
-- ----------------------------

-- ----------------------------
-- Table structure for `report_track`
-- ----------------------------
DROP TABLE IF EXISTS `report_track`;
CREATE TABLE `report_track` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `phone_staff_id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '客服id',
  `receiver_staff_id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '接待id',
  `customer_id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '客户id',
  `customer_type` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '客户类型 :0锁定 1 成单 2重复登门 3新登门 4说明会\n',
  `report_date` datetime DEFAULT NULL COMMENT '报表时间',
  `vistor_count` int(11) DEFAULT NULL COMMENT '登门数量',
  `order_amount` int(11) DEFAULT NULL COMMENT '成单数',
  `order_price` decimal(10,0) DEFAULT NULL COMMENT '成单金额',
  `new_change_customer` int(11) DEFAULT NULL COMMENT '新客户总数',
  `create_date` datetime DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '审核状态 : 0未审核 1已审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='每日业绩报表';

-- ----------------------------
-- Records of report_track
-- ----------------------------

-- ----------------------------
-- Table structure for `role_info`
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色信息表';

-- ----------------------------
-- Records of role_info
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `role_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `permission_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限关联表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `user_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `role_id` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------

-- ----------------------------
-- Table structure for visit_everyday_info
-- ----------------------------
DROP TABLE IF EXISTS `visit_everyday_info`;
CREATE TABLE `visit_everyday_info` (
  `id` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键ID',
  `customer_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '客户ID',
  `customer_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '客户名称',
  `customer_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '客户分类 0--空白客户  1--重复登门  2--说明会  3--成单  4--锁定  5--转介绍',
  `customer_area` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户所在地区：0--沈阳  1--大连',
  `custom_service_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '客服ID',
  `custom_service_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '客户名称',
  `receiver_staff_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '接待者ID',
  `receiver_staff_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '接待者名称',
  `transaction_amount` decimal(10,0) DEFAULT NULL COMMENT '成单金额',
  `exercise` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '活动：0--无  1--HZ  2--4DS  3--QB  4--回款  5--DS  6--LY  7--LY+HZ  8--XY  9--HZ+38国家  10--3B',
  `goods_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `out_or_indent` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '出单或订单：1--出单  2--订单',
  `visit_date` datetime NOT NULL COMMENT '登门日期',
  `remark` varchar(200) DEFAULT NULL COMMENT '情况/备注',
  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`) USING BTREE,
  KEY `idx_custom_service_id` (`custom_service_id`) USING BTREE,
  KEY `idx_receiver_staff_id` (`receiver_staff_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='每日登门表';

-- ----------------------------
-- Table structure for visit_report_info
-- ----------------------------
DROP TABLE IF EXISTS `visit_report_info`;
CREATE TABLE `visit_report_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '主键ID',
  `report_date` datetime NOT NULL COMMENT '统计日期',
  `receiver_staff_id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '接待者ID',
  `receiver_staff_name` varchar(8) COLLATE utf8_bin NOT NULL COMMENT '接待者名称',
  `receiver_area` varchar(1) COLLATE utf8_bin NOT NULL COMMENT '接待者所属区域 0--沈阳 1--大连',
  `customer_counts` int(11) NOT NULL COMMENT '客户总数量',
  `new_counts` int(11) NOT NULL DEFAULT '0' COMMENT '空白客户数量',
  `repeat_counts` int(11) NOT NULL DEFAULT '0' COMMENT '重复登门客户数量',
  `repeat_orders` int(11) NOT NULL COMMENT '重复登门订单数',
  `repeat_amounts` int(11) NOT NULL COMMENT '重复登门订单数量',
  `roadshow_counts` int(11) NOT NULL DEFAULT '0' COMMENT '说明会客户数量',
  `roadshow_orders` int(11) NOT NULL COMMENT '说明会订单数量',
  `roadshow_amounts` int(11) NOT NULL COMMENT '说明会订单金额',
  `finish_order_counts` int(11) NOT NULL DEFAULT '0' COMMENT '成单客户数量',
  `finish_orders` int(11) NOT NULL COMMENT '成单客户订单数量',
  `finish_amounts` int(11) NOT NULL COMMENT '成单客户订单金额',
  `locked_counts` int(11) NOT NULL DEFAULT '0' COMMENT '锁定客户数量',
  `locked_orders` int(11) NOT NULL COMMENT '锁定客户订单数量',
  `locked_amounts` int(11) NOT NULL COMMENT '锁定客户订单金额',
  PRIMARY KEY (`id`),
  KEY `idx_report_date` (`report_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='接待统计表';

-- ----------------------------
-- Table structure for dept_performance_info
-- ----------------------------
DROP TABLE IF EXISTS `dept_performance_info`;
CREATE TABLE `dept_performance_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '主键ID',
  `employee_id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '员工ID',
  `performance` decimal(10,0) NOT NULL COMMENT '业绩',
  `order_amounts` int(11) NOT NULL COMMENT '件数',
  `new_customer_counts` int(11) NOT NULL COMMENT '新客户数量',
  `report_date` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`),
  KEY `idx_employee_id` (`employee_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='部门业绩统计表';

-- ----------------------------
-- Table structure for storehouse_operate_info
-- ----------------------------
DROP TABLE IF EXISTS `storehouse_operate_info`;
CREATE TABLE `storehouse_operate_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `order_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '订单ID',  
  `order_detail_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '订单详情ID',
  `good_sort_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商品分类ID',
  `good_sort_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '商品分类名称',
  `good_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商品ID',
  `good_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '商品类型（同good_info type）',
  `good_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `amount` int(11) DEFAULT NULL COMMENT '数量 如果退货数量为负',
  `operate_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '操作标志 0入库 1已库',
  `operate_date` datetime DEFAULT NULL COMMENT '更新日期',
  `operate_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='仓库操作详情表';

-- ----------------------------
-- Table structure for exchange_operate_info
-- ----------------------------
DROP TABLE IF EXISTS `exchange_operate_info`;
CREATE TABLE `exchange_operate_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL,
  `order_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '订单ID',
  `order_detail_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '订单详情ID',
  `good_sort_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商品分类ID',
  `good_sort_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '商品分类名称',
  `good_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商品ID',
  `good_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '商品类型（同good_info type）',
  `good_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `amount` int(11) DEFAULT NULL COMMENT '数量 如果退货数量为负',
  `operate_flag` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '操作标志 0未确认 1已确认',
  `operate_date` datetime DEFAULT NULL COMMENT '更新日期',
  `operate_id` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文交所操作详情表';


CREATE FUNCTION `rand_string`(n INT) RETURNS varchar(255) CHARSET latin1
BEGIN
    DECLARE chars_str varchar(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    DECLARE return_str varchar(255) DEFAULT '';
    DECLARE i INT DEFAULT 0;
    WHILE i < n DO
        SET return_str = concat(return_str,substring(chars_str , FLOOR(1 + RAND()*62 ),1));
        SET i = i +1;
    END WHILE;
    RETURN return_str;
END;

-- ----------------------------
-- Table structure for butt_perfor_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `butt_perfor_detail_info`;
CREATE TABLE `butt_perfor_detail_info` (
  `id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '主键ID',
  `phone_staff_id` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '客服ID',
  `phone_staff_name` varchar(8) COLLATE utf8_bin NOT NULL COMMENT '客服姓名',
  `phone_staff_group_name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '客服所属机构名称',
  `receive_staff_name` varchar(8) COLLATE utf8_bin NOT NULL COMMENT '接待姓名',
  `receive_finished_counts` int(11) DEFAULT NULL COMMENT '成单接待数',
  `out_orders_of_finished` int(11) DEFAULT NULL COMMENT '成单出单数',
  `out_order_rate_of_finished` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '成单出单率',
  `performance_of_finished` decimal(10,0) DEFAULT NULL COMMENT '成单业绩',
  `order_avg_of_finished` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '成单单均',
  `piece_avg_of_finished` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '成单件均',
  `receive_locked_counts` int(11) DEFAULT NULL COMMENT '锁定接待数',
  `out_orders_of_locked` int(11) DEFAULT NULL COMMENT '锁定出单数',
  `out_order_rate_of_locked` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '锁定出单率',
  `performance_of_locked` decimal(10,0) DEFAULT NULL COMMENT '锁定业绩',
  `order_avg_of_locked` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '锁定单均',
  `piece_avg_of_locked` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '锁定件均',
  `order_avg_of_goods_counts` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '单均产品件数',
  `report_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_id` (`id`) USING BTREE,
  KEY `idx_phone_staff_id` (`phone_staff_id`) USING BTREE,
  KEY `idx_phone_staff_name` (`phone_staff_name`) USING BTREE,
  KEY `idx_report_date` (`report_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='展厅客服对接业绩详情表';
