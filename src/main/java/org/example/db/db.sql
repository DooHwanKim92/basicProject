DROP DATABASE IF EXISTS `proj1`;
CREATE DATABASE `proj1`;

USE `proj1`;

CREATE TABLE `member`(
id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
deptId int UNSIGNED NOT NULL,
stateId int UNSIGNED NOT NULL DEFAULT '6',
state char(100),
name char(100) NOT NULL,
userId char(100) NOT NULL,
password char(100) not null,
regDate datetime NOT NULL,
`position` char(100) NOT NULL,
email char(100) NOT NULL,
birthDate char(100) NOT NULL,
createdDate datetime NOT NULL DEFAULT NOW(),
modifiedDate datetime NOT NULL DEFAULT NOW(),
workStrartTime datetime DEFAULT NOW(),
workEndTime datetime) DEFAULT NOW();

CREATE TABLE `dept` (
	`id`	int unsigned not null primary key auto_increment,
	`deptName`	char(100) not null,
	`createdDate`	datetime not null,
	`modifiedDate`	datetime
);

CREATE TABLE `state` (
	`id`	int unsigned not null primary key auto_increment,
	`state`	char(100) not null,
	`createdDate`	datetime,
	`modifiedDate`	datetime
);