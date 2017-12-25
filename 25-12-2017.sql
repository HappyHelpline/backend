/*
SQLyog Community v12.4.3 (64 bit)
MySQL - 5.7.19-log : Database - happyhelpline
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`happyhelpline` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `happyhelpline`;

/*Table structure for table `appointment_sechdule` */

DROP TABLE IF EXISTS `appointment_sechdule`;

CREATE TABLE `appointment_sechdule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appoint_id` varchar(50) DEFAULT 'NA',
  `volunteer_id` varchar(50) DEFAULT 'NA',
  `client_id` varchar(50) DEFAULT 'NA',
  `status` varchar(50) DEFAULT 'NA',
  `schedule_date` varchar(50) DEFAULT 'NA',
  `description` varchar(50) DEFAULT 'NA',
  `trndate` varchar(50) DEFAULT 'NA',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `id` (`appoint_id`,`volunteer_id`,`client_id`,`trndate`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `register_client` */

DROP TABLE IF EXISTS `register_client`;

CREATE TABLE `register_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL DEFAULT 'NA',
  `password` varchar(100) NOT NULL DEFAULT 'NA',
  `contact_no` varchar(12) DEFAULT 'NA',
  `email_add` varchar(150) DEFAULT 'NA',
  `trndate` varchar(50) DEFAULT 'NA',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `username` (`username`),
  FULLTEXT KEY `trndate` (`trndate`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `register_volunteer` */

DROP TABLE IF EXISTS `register_volunteer`;

CREATE TABLE `register_volunteer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(150) DEFAULT 'NA',
  `password` varchar(150) DEFAULT 'NA',
  `first_name` varchar(150) DEFAULT 'NA',
  `last_name` varchar(150) DEFAULT 'NA',
  `gender_type` varchar(6) DEFAULT 'NA',
  `contact_no` varchar(12) DEFAULT 'NA',
  `alternate_no` varchar(12) DEFAULT 'NA',
  `email_add` varchar(150) DEFAULT 'NA',
  `Address` varchar(300) DEFAULT 'NA',
  `state` varchar(50) DEFAULT 'NA',
  `country` varchar(50) DEFAULT 'NA',
  `professional` varchar(200) DEFAULT 'NA',
  `about_you` varchar(1000) DEFAULT 'NA',
  `language` varchar(100) DEFAULT 'NA',
  `whyjoinus` varchar(1000) DEFAULT 'NA',
  `schedule_time` varchar(500) DEFAULT 'NA',
  `trndate` varchar(50) DEFAULT 'NA',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `username` (`username`),
  FULLTEXT KEY `trndate` (`schedule_time`,`trndate`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
