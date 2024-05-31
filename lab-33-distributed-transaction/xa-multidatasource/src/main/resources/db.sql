-- 1: 创建数据库
create database abc;
-- 2： 创建表
CREATE TABLE user (
`id` int NOT NULL,
`name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
`money` double(255,0) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- 3： 添加数据
insert into user values(1,'zhangsan',2000);


-- 1： 创建数据库
create database icbc;
-- 2： 创建表
CREATE TABLE user (
    `id` int NOT NULL,
    `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `money` double(255,0) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- 3： 添加数据
insert into user values(1,'lisi',2000);