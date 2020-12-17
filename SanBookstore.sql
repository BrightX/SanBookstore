/**
 * 作者： 徐亮亮
 * 学号： 1810120118
 * 作品名：“三味书屋”图书线上销售系统
 */

-- ----------------------------
-- 创建数据库
-- ----------------------------
DROP DATABASE IF EXISTS SanBookstore;
CREATE DATABASE SanBookstore charset utf8mb4;

-- ----------------------------
-- 创建数据表
-- ----------------------------

-- 用户表
CREATE TABLE SanBookstore.user (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    password VARCHAR(64) NOT NULL COMMENT '密码',
    balance DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '账户余额',
    is_merchant TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否是商家',
    is_superuser TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否是管理员',
    UNIQUE INDEX `uk_username` (username) COMMENT '用户名'
) COMMENT '用户表';

-- 商铺表
CREATE TABLE SanBookstore.shop (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    name VARCHAR(50) NOT NULL COMMENT '商铺名',
    send_address TEXT NULL COMMENT '发货地址',
    balance DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '账户收益',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    user_id INT UNSIGNED NOT NULL COMMENT '用户id'
) COMMENT '商铺表';

-- 商品表
CREATE TABLE SanBookstore.book (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    name VARCHAR(50) NOT NULL COMMENT '商品名称',
    shop_id INT UNSIGNED COMMENT '商铺id',
    shop_name VARCHAR(50) NOT NULL COMMENT '商铺名',
    info TEXT NULL COMMENT '商品信息',
    price DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '商品价格',
    image VARCHAR(255) NULL COMMENT '商品图片',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '上架时间',
    inventory INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '库存量'
) COMMENT '商品表';

-- 订单表
CREATE TABLE SanBookstore.order (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    order_number VARCHAR(64) NOT NULL COMMENT '订单号',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    user_id INT UNSIGNED COMMENT '用户id',
    shop_name VARCHAR(50) NOT NULL COMMENT '商铺名',
    shop_id INT UNSIGNED COMMENT '商铺id',
    book_name VARCHAR(50) NOT NULL COMMENT '商品名称',
    book_id INT UNSIGNED COMMENT '商品id',
    price DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '商品价格',
    image VARCHAR(255) NULL COMMENT '商品图片',
    send_address TEXT NULL COMMENT '发货地址',
    receive_address TEXT NULL COMMENT '收货地址',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
    status VARCHAR(10) NULL DEFAULT '待支付' COMMENT '订单状态',
    payment_amount DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '付款金额',
    purchase_quantity INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品数量'
) COMMENT '订单表';

-- 购物车表
CREATE TABLE SanBookstore.shopcart (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    book_id INT UNSIGNED COMMENT '商品id',
    user_id INT UNSIGNED COMMENT '用户id',
    purchase_quantity INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品数量'
) COMMENT '购物车表';
