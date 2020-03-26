-- 1.创建数据库
create schema if not exists `market` default character set utf8mb4 collate utf8mb4_bin;


-- -----------------------------------------------------
-- Table `business`.`merchant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `market`.`merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL COMMENT '商户名',
  `cellphone` VARCHAR(36) NOT NULL COMMENT '部门id',
  `ctime` timestamp NULL COMMENT '开始时间',
  `mtime` timestamp NULL COMMENT '最近一次更新时间',
  `deleted` TINYINT(1) DEFAULT 0 COMMENT '',
  `version` BIGINT(20) UNSIGNED NULL COMMENT '',
  PRIMARY KEY (`id`) COMMENT '')
  ENGINE = InnoDB
  COMMENT = '商户基本信息表';

-- -----------------------------------------------------
-- Table `market`.`merchant_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `market`.`merchant_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(36) NOT NULL COMMENT '商户用户名',
  `password` VARCHAR(64) NOT NULL COMMENT '商户登录密码',
  `merchant_id` bigint(20)  NOT NULL COMMENT '商户id',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '用户类型 1:管理员 2:收银员',
  `ctime` timestamp NULL COMMENT '开始时间',
  `mtime` timestamp NULL COMMENT '最近一次更新时间',
  `deleted` TINYINT(1) DEFAULT '0' COMMENT '',
  `version` BIGINT(20) UNSIGNED NULL COMMENT '',
  PRIMARY KEY (`id`) COMMENT '',
  KEY `idx_merchant_id` (`merchant_id`) USING BTREE,
  UNIQUE INDEX `username` (`username`))
  ENGINE = InnoDB
  COMMENT = '商户账户表';





-- -----------------------------------------------------
-- Table `market`.`merchant_consumer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `market`.`merchant_consumer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL COMMENT '会员名称',
  `cellphone` VARCHAR(36) NOT NULL COMMENT '会员手机号',
  `email` VARCHAR(36) NULL COMMENT '会员email',
  `wechat` VARCHAR(36) NULL COMMENT '微信',
  `merchant_id` bigint(20) NOT NULL COMMENT '商户id',
  `ctime` timestamp NULL COMMENT '开始时间',
  `mtime` timestamp NULL COMMENT '最近一次更新时间',
  `operator_create` VARCHAR(36) NULL COMMENT '创建人',
  `operator_update` VARCHAR(36) NULL COMMENT '最后一次修改操作人',
  `deleted` TINYINT(1) DEFAULT '0' COMMENT '',
  `version` BIGINT(20) UNSIGNED NULL COMMENT '',
  PRIMARY KEY (`id`) COMMENT '',
  KEY `idx_merchant_id` (`merchant_id`) USING BTREE)
  ENGINE = InnoDB
  COMMENT = '商户会员表';


-- -----------------------------------------------------
-- `market`.`transaction`
-- -----------------------------------------------------
create table if not exists `market`.`transaction`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) not null comment '订单号',
  `name` varchar(128) not null comment '流水描述',
  `remark` varchar(128) null comment '流水备注',
  `original_amount` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '原始金额',
  `pay_amount` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '实付金额',
  `discount` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '优惠金额',
  `merchant_id` bigint(20)  NOT NULL COMMENT '商户id',
  `consumer_id` bigint(20)  NOT NULL COMMENT '会员id',
  `type` int(2) NOT NULL DEFAULT '1' COMMENT '流水类型 1:支付 2:退款 3:部分退款',
  `status` int(10) unsigned null default null comment '流水状态 1000:待支付 1001:支付中 1002:支付成功 1003:支付失败 1004:支付异常 1005:退款成功 1006:退款失败 1007:退款异常',
  `operator_create` VARCHAR(36) NULL COMMENT '创建人',
  `operator_update` VARCHAR(36) NULL COMMENT '最后一次修改操作人',
  `ctime` timestamp NULL COMMENT '开始时间',
  `mtime` timestamp NULL COMMENT '最近一次更新时间',
  `deleted` tinyint(1) default '0' comment '',
  primary key (`id`) comment '',
  index `order_id` (`order_id` asc) comment ''
)engine=InnoDB
  comment='交易流水表';

-- -----------------------------------------------------
-- `market`.`order`
-- -----------------------------------------------------
create table if not exists `market`.`order`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(32) not null comment '订单号',
  `name` varchar(128) not null comment '流水描述',
  `remark` varchar(128) null comment '流水备注',
  `original_amount` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '原始金额',
  `pay_amount` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '实付金额',
  `discount` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '优惠金额',
  `merchant_id` bigint(20)  NOT NULL COMMENT '商户id',
  `consumer_id` bigint(20)  NOT NULL COMMENT '会员id',
  `type` int(2) NOT NULL DEFAULT '1' COMMENT '流水类型 1:支付 2:退款 3:部分退款',
  `status` int(10) unsigned null default null comment '订单状态 0:待支付 1:支付中 2:支付成功 3:支付失败 4:支付异常 5:退款成功 6:退款失败 7:退款异常',
  `operator_create` VARCHAR(36) NULL COMMENT '创建人',
  `operator_update` VARCHAR(36) NULL COMMENT '最后一次修改操作人',
  `ctime` timestamp NULL COMMENT '开始时间',
  `mtime` timestamp NULL COMMENT '最近一次更新时间',
  `deleted` tinyint(1) default '0' comment '',
  primary key (`id`) comment '',
  unique index `idx_sn` (`sn` asc) comment ''
)engine=InnoDB
  comment='交易流水表';


-- -----------------------------------------------------
-- Table `market`.`product`
-- -----------------------------------------------------
create table if not exists `market`.`merchant_product`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) not null comment '商品名称',
  `priority` int(4) NOT NULL DEFAULT '0' COMMENT '商品优先级',
  `price` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '商品价格',
  `original_price` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '原价',
  `count` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `image` text COLLATE utf8mb4_bin,
  `context` text COLLATE utf8mb4_bin,
  `status` int(10) unsigned null default null comment '商品状态 0:待发布 1:审核中 2:审核通过 3:审核不通过',
  `merchant_id` bigint(20)  NOT NULL COMMENT '商户id',
  `industry_id` bigint(20)  NOT NULL COMMENT '商品类型id',
  `operator_create` VARCHAR(36) NULL COMMENT '创建人',
  `operator_update` VARCHAR(36) NULL COMMENT '最后一次修改操作人',
  `ctime` timestamp NULL COMMENT '开始时间',
  `mtime` timestamp NULL COMMENT '最近一次更新时间',
  `deleted` tinyint(1) default '0' comment '',
  primary key (`id`) comment ''
)engine=InnoDB
  comment='交易流水表';


CREATE TABLE `market`.`industry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level1` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '一级类型',
  `level2` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '二级类型',
  `level3` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '三级类型',
  `level4` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '四级类型',
  `depth` int(2) NOT NULL COMMENT '深度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品类型表';


--
-- 计数器建表语句
--

CREATE TABLE `business`.`table_name_sn_prefix_12345` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `stub` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stub` (`stub`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
