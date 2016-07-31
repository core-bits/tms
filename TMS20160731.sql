CREATE DATABASE  IF NOT EXISTS `tms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tms`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: tms
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banks`
--

DROP TABLE IF EXISTS `banks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banks` (
  `id` varchar(9) NOT NULL,
  `name` varchar(100) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banks`
--

LOCK TABLES `banks` WRITE;
/*!40000 ALTER TABLE `banks` DISABLE KEYS */;
/*!40000 ALTER TABLE `banks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buisness_unit`
--

DROP TABLE IF EXISTS `buisness_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buisness_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buisness_unit_code` varchar(45) DEFAULT NULL,
  `buisness_unit_name` varchar(65) DEFAULT NULL,
  `buisness_unit_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `buisness_unit_name_UNIQUE` (`buisness_unit_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buisness_unit`
--

LOCK TABLES `buisness_unit` WRITE;
/*!40000 ALTER TABLE `buisness_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `buisness_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_code` varchar(45) DEFAULT NULL,
  `department_name` varchar(45) NOT NULL,
  `department_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `department_name_UNIQUE` (`department_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Various deparment in Ericsson participating in the co-operative society ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_allocation_guidelines`
--

DROP TABLE IF EXISTS `loan_allocation_guidelines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan_allocation_guidelines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loan_name` varchar(65) DEFAULT NULL,
  `maximum_amount` decimal(19,2) DEFAULT NULL,
  `minimum_amount` decimal(19,2) DEFAULT NULL,
  `maximum_tenure` varchar(45) DEFAULT NULL,
  `minimum_tenure` varchar(45) DEFAULT NULL,
  `interest_rate` int(9) DEFAULT NULL COMMENT 'The rate is in percentage. This will be required if a guidelines rate will be graduated ',
  `loan_type` int(9) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `loan_guideline_idx` (`loan_type`),
  CONSTRAINT `loan_guideline` FOREIGN KEY (`loan_type`) REFERENCES `loan_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_allocation_guidelines`
--

LOCK TABLES `loan_allocation_guidelines` WRITE;
/*!40000 ALTER TABLE `loan_allocation_guidelines` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan_allocation_guidelines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_type`
--

DROP TABLE IF EXISTS `loan_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loan_name` varchar(45) DEFAULT NULL,
  `loan_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loan_name_UNIQUE` (`loan_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_type`
--

LOCK TABLES `loan_type` WRITE;
/*!40000 ALTER TABLE `loan_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(45) DEFAULT NULL,
  `member_name` varchar(100) DEFAULT NULL,
  `member_address` varchar(200) DEFAULT NULL,
  `sex` varchar(7) DEFAULT NULL,
  `buisness_unit` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `bank` varchar(50) DEFAULT NULL,
  `account_number` varchar(20) DEFAULT NULL,
  `mobile_number` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `marital_status` varchar(15) DEFAULT NULL,
  `witness_name` varchar(100) DEFAULT NULL,
  `witness_residential_address` varchar(200) DEFAULT NULL,
  `witness_signature` blob,
  `witness_approval_date` datetime DEFAULT NULL,
  `next_of_kin` varchar(100) DEFAULT NULL,
  `relationship_to_nok` varchar(45) DEFAULT NULL,
  `phone_of_nok` varchar(20) DEFAULT NULL,
  `address_of_nok` varchar(200) DEFAULT NULL,
  `referral_name` varchar(100) DEFAULT NULL,
  `undertaking_date` datetime DEFAULT NULL,
  `undertaking_name` varchar(100) DEFAULT NULL,
  `authority_to_deduct_amount` decimal(19,2) DEFAULT NULL,
  `authority_to_deduct_effective_date` date DEFAULT NULL,
  `applicant_signature` blob,
  `application_date` datetime DEFAULT NULL,
  `secretary_signature` blob,
  `secretary_approval_date` datetime DEFAULT NULL,
  `secretary_approval_status` tinyint(4) DEFAULT NULL,
  `president_signature` blob,
  `president_approval_date` datetime DEFAULT NULL,
  `president_approval_status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id_UNIQUE` (`member_id`),
  KEY `member_department_idx` (`department`),
  KEY `member_bu_idx` (`buisness_unit`),
  KEY `member_bank_idx` (`bank`),
  CONSTRAINT `member_bu` FOREIGN KEY (`buisness_unit`) REFERENCES `buisness_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `member_department` FOREIGN KEY (`department`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  `role_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `user_login_id` varchar(45) DEFAULT NULL,
  `user_login_password` varchar(45) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_status` tinyint(1) DEFAULT NULL,
  `first_login_status` tinyint(1) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `date_assigned` datetime DEFAULT NULL,
  `assigned_by` varchar(45) DEFAULT NULL,
  `date_authorised` datetime DEFAULT NULL,
  `authorised_by` varchar(45) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `authorise_status` varchar(2) DEFAULT NULL,
  `reason` varchar(130) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_constraint` (`user_id`),
  KEY `role_constraint` (`role_id`),
  CONSTRAINT `role_constraint` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `user_constraint` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-31 15:04:31
