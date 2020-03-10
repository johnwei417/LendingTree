-- MySQL dump 10.13  Distrib 8.0.19, for osx10.15 (x86_64)
--
-- Host: localhost    Database: loan
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `bank_id` int NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,'Chase Bank'),(2,'Bank Of America'),(3,'Citi Bank'),(4,'PNC Bank');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `cus_id` int NOT NULL AUTO_INCREMENT,
  `cus_salary` bigint DEFAULT NULL,
  PRIMARY KEY (`cus_id`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (35,20000),(36,15000),(37,500000);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `dept_id` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'ADMIN'),(2,'PICKUP'),(3,'VERIFICATION'),(4,'LEGAL');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (49,'1035 boyce rd'),(46,'208 main st'),(47,'1035 boyce rd');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees_departments`
--

DROP TABLE IF EXISTS `employees_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees_departments` (
  `dept_id` int DEFAULT NULL,
  `emp_id` int NOT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `FKiwal78dmujd6x35tj4pmi6mui` (`dept_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees_departments`
--

LOCK TABLES `employees_departments` WRITE;
/*!40000 ALTER TABLE `employees_departments` DISABLE KEYS */;
INSERT INTO `employees_departments` VALUES (3,47),(2,46),(4,49);
/*!40000 ALTER TABLE `employees_departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan` (
  `loan_id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`loan_id`)
) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
INSERT INTO `loan` VALUES (53,20000),(54,40000),(59,40000),(60,9000),(58,4900),(57,20000),(56,20000),(55,49000),(61,5000);
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans_banks`
--

DROP TABLE IF EXISTS `loans_banks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans_banks` (
  `bank_id` int DEFAULT NULL,
  `loan_id` int NOT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `FKqsqo27uemd726a6998ngn7ddl` (`bank_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans_banks`
--

LOCK TABLES `loans_banks` WRITE;
/*!40000 ALTER TABLE `loans_banks` DISABLE KEYS */;
INSERT INTO `loans_banks` VALUES (2,53),(1,54),(2,55),(4,56),(3,57),(2,58),(1,59),(4,60),(1,61);
/*!40000 ALTER TABLE `loans_banks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans_customers`
--

DROP TABLE IF EXISTS `loans_customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans_customers` (
  `loan_id` int NOT NULL,
  `cus_id` int NOT NULL,
  KEY `FKmx9isyq8kfq10i0lfxoe3uhui` (`cus_id`),
  KEY `FKqnk1ffu7o71944fr8083v03sc` (`loan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans_customers`
--

LOCK TABLES `loans_customers` WRITE;
/*!40000 ALTER TABLE `loans_customers` DISABLE KEYS */;
INSERT INTO `loans_customers` VALUES (53,35),(54,35),(55,35),(56,35),(57,35),(58,36),(59,36),(60,36),(61,37);
/*!40000 ALTER TABLE `loans_customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans_departments`
--

DROP TABLE IF EXISTS `loans_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans_departments` (
  `loan_id` int NOT NULL,
  `dept_id` int NOT NULL,
  KEY `FKdsntotttehi4sfmsc4t5wyxow` (`dept_id`),
  KEY `FK7doe03t7wxclgogn9ubpe0gi2` (`loan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans_departments`
--

LOCK TABLES `loans_departments` WRITE;
/*!40000 ALTER TABLE `loans_departments` DISABLE KEYS */;
INSERT INTO `loans_departments` VALUES (53,1),(54,2),(55,1),(56,1),(57,2),(58,1),(59,1),(60,3),(61,1);
/*!40000 ALTER TABLE `loans_departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans_loanstatus`
--

DROP TABLE IF EXISTS `loans_loanstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans_loanstatus` (
  `loanstatus_id` int DEFAULT NULL,
  `loan_id` int NOT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `FKl1p6rsn5mq3omakfgh4ly9p2i` (`loanstatus_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans_loanstatus`
--

LOCK TABLES `loans_loanstatus` WRITE;
/*!40000 ALTER TABLE `loans_loanstatus` DISABLE KEYS */;
INSERT INTO `loans_loanstatus` VALUES (3,53),(3,54),(3,55),(3,56),(3,57),(3,58),(3,59),(3,60),(1,61);
/*!40000 ALTER TABLE `loans_loanstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans_loantype`
--

DROP TABLE IF EXISTS `loans_loantype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans_loantype` (
  `loantype_id` int DEFAULT NULL,
  `loan_id` int NOT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `FKotcavxavxavw936qt3ygk6q2t` (`loantype_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans_loantype`
--

LOCK TABLES `loans_loantype` WRITE;
/*!40000 ALTER TABLE `loans_loantype` DISABLE KEYS */;
INSERT INTO `loans_loantype` VALUES (2,53),(1,54),(1,55),(3,56),(4,57),(3,58),(1,59),(4,60),(2,61);
/*!40000 ALTER TABLE `loans_loantype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loanstatus`
--

DROP TABLE IF EXISTS `loanstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loanstatus` (
  `loanstatus_id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`loanstatus_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loanstatus`
--

LOCK TABLES `loanstatus` WRITE;
/*!40000 ALTER TABLE `loanstatus` DISABLE KEYS */;
INSERT INTO `loanstatus` VALUES (1,'APPROVED'),(2,'REJECTED'),(3,'PENDING');
/*!40000 ALTER TABLE `loanstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loantype`
--

DROP TABLE IF EXISTS `loantype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loantype` (
  `loantype_id` int NOT NULL AUTO_INCREMENT,
  `loantype` varchar(255) DEFAULT NULL,
  `loan_loan_id` int DEFAULT NULL,
  PRIMARY KEY (`loantype_id`),
  KEY `FKnp0yh1py25wyg80d93h0kpmij` (`loan_loan_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loantype`
--

LOCK TABLES `loantype` WRITE;
/*!40000 ALTER TABLE `loantype` DISABLE KEYS */;
INSERT INTO `loantype` VALUES (1,'Business',NULL),(2,'Home',NULL),(3,'Education',NULL),(4,'Personal',NULL);
/*!40000 ALTER TABLE `loantype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loantypes_banks`
--

DROP TABLE IF EXISTS `loantypes_banks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loantypes_banks` (
  `loantype_id` int NOT NULL,
  `bank_id` int NOT NULL,
  KEY `FKt8exoe4yumtkbykvjym9jnqgi` (`bank_id`),
  KEY `FK38gqnwswt0it0oj209p1ol20k` (`loantype_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loantypes_banks`
--

LOCK TABLES `loantypes_banks` WRITE;
/*!40000 ALTER TABLE `loantypes_banks` DISABLE KEYS */;
/*!40000 ALTER TABLE `loantypes_banks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_EMPLOYEE'),(3,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (95,NULL,'2020-03-09 16:01:10','hnk5876@wmich.edu','moath','moath','$2a$10$9DGPrgFJtnsdsrYLF/p7fel5QIRHqipENKjOo5yluf6gFzmYiITHa','269213123',NULL,NULL,NULL,'2020-03-09 16:03:03','moath'),(68,NULL,'2020-03-02 12:17:04','hwei970417@gmail.com','admin','admin','$2a$10$RbQ1aNQV.rZog.FxwjXtZOWkgLZ2EFLvD2pODmy46uXFM16mIEjSW','2692676860',NULL,NULL,NULL,'2020-03-02 18:24:07','admin'),(85,NULL,'2020-03-09 17:02:52','hwei970417@gmail.com','Xiaoming','Zhang','$2a$10$jCuX0qKCdYBAQjGFoA2t4ORsfAqz25wFCgKYCQ1W36zc1yM9iNlKa','2692676860',NULL,NULL,NULL,'2020-03-09 17:02:52','xiaoming'),(86,NULL,'2020-03-09 17:03:38','johnwei970417@gmail.com','XiaoHong','Li','$2a$10$LfDU1hcaVZrWo4m8qXt4Au.fkf1HeWG72T1h.iJ4c9h1M4gQaxOZK','2682762883',NULL,NULL,NULL,'2020-03-09 17:03:38','xiaohong'),(92,NULL,'2020-03-09 12:27:40','hwei970417@gmail.com','Honglin','Wei','$2a$10$KdwodisI6LjfM1CoZZ/FUeUi3/H.2oVnRjqg5vt48uC4V7lAcQoqO','2692676860',NULL,NULL,NULL,'2020-03-09 12:27:40','honglin'),(93,NULL,'2020-03-09 12:29:57','johnwei970417@gmail.com','Xiaozi','Wang','$2a$10$i5fEKIWAeW9rMMhOIfn2Ou0pkJQ5PXDJNkhnORLBEcIfm61xA.RaO','2692766883',NULL,NULL,NULL,'2020-03-09 12:29:57','xiaozi'),(94,NULL,'2020-03-09 12:31:03','johnwei970417@gmail.com','Xiaoqian','Bi','$2a$10$9v/pwstDVAw7/ZpPmW9AmOieoMy2NIPbQAVapRSb8ga8cVPgIG6Ce','2692676860',NULL,NULL,NULL,'2020-03-09 12:31:03','xiaoqian');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_customers`
--

DROP TABLE IF EXISTS `users_customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_customers` (
  `cus_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKhavn2kxoswbqrdbk0k4w2xfgj` (`cus_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_customers`
--

LOCK TABLES `users_customers` WRITE;
/*!40000 ALTER TABLE `users_customers` DISABLE KEYS */;
INSERT INTO `users_customers` VALUES (35,92),(36,94),(37,95);
/*!40000 ALTER TABLE `users_customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_employees`
--

DROP TABLE IF EXISTS `users_employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_employees` (
  `emp_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKredyjoh3yo0w87kxl5vwxpe2b` (`emp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_employees`
--

LOCK TABLES `users_employees` WRITE;
/*!40000 ALTER TABLE `users_employees` DISABLE KEYS */;
INSERT INTO `users_employees` VALUES (47,86),(46,85),(49,93);
/*!40000 ALTER TABLE `users_employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (68,1),(85,2),(86,2),(92,3),(93,2),(94,3),(95,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-09 18:46:13
