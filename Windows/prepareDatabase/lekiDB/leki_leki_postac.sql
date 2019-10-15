CREATE DATABASE  IF NOT EXISTS `leki` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `leki`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: leki
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `leki_postac`
--

DROP TABLE IF EXISTS `leki_postac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `leki_postac` (
  `leki_id` int(11) NOT NULL,
  `postac_id` int(11) NOT NULL,
  PRIMARY KEY (`leki_id`,`postac_id`),
  KEY `postac_id` (`postac_id`),
  CONSTRAINT `leki_postac_ibfk_1` FOREIGN KEY (`leki_id`) REFERENCES `leki` (`id`) ON DELETE CASCADE,
  CONSTRAINT `leki_postac_ibfk_2` FOREIGN KEY (`postac_id`) REFERENCES `postac` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leki_postac`
--

LOCK TABLES `leki_postac` WRITE;
/*!40000 ALTER TABLE `leki_postac` DISABLE KEYS */;
INSERT INTO `leki_postac` VALUES (5,1),(76,1),(132,1),(139,1),(58,2),(90,2),(196,3),(85,4),(124,4),(160,4),(201,4),(13,5),(17,5),(18,5),(22,5),(29,5),(48,5),(61,5),(65,5),(66,5),(86,5),(87,5),(88,5),(93,5),(94,5),(96,5),(97,5),(101,5),(102,5),(103,5),(104,5),(107,5),(108,5),(109,5),(110,5),(112,5),(125,5),(130,5),(132,5),(134,5),(135,5),(136,5),(137,5),(138,5),(140,5),(143,5),(146,5),(148,5),(152,5),(153,5),(154,5),(159,5),(162,5),(173,5),(174,5),(184,5),(194,5),(210,5),(20,6),(197,7),(70,8),(123,8),(181,8),(170,9),(26,10),(79,10),(83,10),(150,10),(180,11),(16,12),(25,12),(57,12),(59,12),(60,12),(128,12),(141,12),(164,12),(1,13),(2,13),(4,13),(7,13),(8,13),(14,13),(23,13),(27,13),(28,13),(34,13),(38,13),(45,13),(49,13),(52,13),(54,13),(55,13),(62,13),(63,13),(70,13),(71,13),(73,13),(80,13),(81,13),(95,13),(115,13),(117,13),(120,13),(131,13),(133,13),(144,13),(145,13),(151,13),(156,13),(157,13),(158,13),(167,13),(175,13),(176,13),(188,13),(189,13),(190,13),(191,13),(194,13),(198,13),(205,13),(74,14),(21,15),(98,16),(195,17),(185,18),(51,19),(142,19),(149,19),(193,20),(47,21),(79,21),(126,21),(46,22),(49,23),(99,23),(161,23),(166,23),(9,24),(31,24),(36,24),(39,24),(42,24),(113,24),(186,24),(89,25),(6,26),(91,26),(10,27),(3,28),(56,28),(75,28),(147,28),(171,28),(182,28),(202,28),(204,29),(206,29),(207,29),(200,30),(208,30),(129,31),(30,32),(40,32),(118,32),(121,32),(122,32),(61,33),(168,34),(11,35),(12,35),(15,35),(19,35),(33,35),(35,35),(41,35),(43,35),(53,35),(67,35),(68,35),(72,35),(84,35),(92,35),(105,35),(106,35),(114,35),(119,35),(155,35),(163,35),(164,35),(165,35),(168,35),(169,35),(177,35),(178,35),(179,35),(183,35),(186,35),(192,35),(64,36),(187,37),(44,38),(77,39),(78,39),(37,40),(172,40),(203,40),(72,41),(111,41),(69,42),(72,42),(24,43),(50,44),(32,45),(200,45),(209,45),(116,46),(127,47),(199,47);
/*!40000 ALTER TABLE `leki_postac` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-15 21:53:19