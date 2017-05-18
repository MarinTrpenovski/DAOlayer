-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: exercise
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  `parentIndex` int(11) DEFAULT NULL,
  `numericalmapping` varchar(300) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `universityId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `universityId_idx` (`universityId`),
  CONSTRAINT `universityId` FOREIGN KEY (`universityId`) REFERENCES `university` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,NULL,0,NULL,NULL,'Technical','Partizanska',1),(2,NULL,0,NULL,NULL,'Art','Teodosij Gologanov',2),(3,NULL,0,NULL,NULL,'Medicine','Vodnjanska',2),(4,1,1,NULL,NULL,'Elektro','ilindenska',1),(5,1,1,NULL,NULL,'Masinski','Ilindenska',1),(6,1,1,NULL,NULL,'Prirodno-matematicki','mavrovka',1),(7,6,2,NULL,NULL,'Matematicki','Mavrovka',1),(8,6,2,NULL,NULL,'hemiski','Mavrovka',1),(9,2,1,NULL,NULL,'Muzicka Akademija','Ruzveltova',3),(10,2,1,NULL,NULL,'Likovna Akademija','Aminta treti',3),(11,3,1,NULL,NULL,'Medicinski Fakultet','Vodnjanska',2),(12,3,1,NULL,NULL,'Stomatoloski Fakultet','Vodnjanska',2);
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-18 16:32:06
