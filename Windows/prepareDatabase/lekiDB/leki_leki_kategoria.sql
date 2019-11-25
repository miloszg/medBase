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
-- Table structure for table `leki_kategoria`
--

DROP TABLE IF EXISTS `leki_kategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `leki_kategoria` (
  `leki_id` int(11) NOT NULL,
  `kategoria_id` int(11) NOT NULL,
  PRIMARY KEY (`leki_id`,`kategoria_id`),
  KEY `kategoria_id` (`kategoria_id`),
  CONSTRAINT `leki_kategoria_ibfk_1` FOREIGN KEY (`leki_id`) REFERENCES `leki` (`id`) ON DELETE CASCADE,
  CONSTRAINT `leki_kategoria_ibfk_2` FOREIGN KEY (`kategoria_id`) REFERENCES `kategoria` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leki_kategoria`
--

LOCK TABLES `leki_kategoria` WRITE;
/*!40000 ALTER TABLE `leki_kategoria` DISABLE KEYS */;
INSERT INTO `leki_kategoria` VALUES (57,1),(58,1),(59,1),(60,1),(120,1),(128,1),(188,1),(189,1),(124,2),(160,2),(201,2),(23,3),(31,3),(32,3),(45,3),(46,3),(80,3),(147,3),(172,3),(199,3),(200,3),(208,3),(209,3),(41,4),(52,4),(53,4),(55,4),(92,4),(103,4),(104,4),(105,4),(106,4),(111,4),(155,4),(161,4),(164,4),(165,4),(169,4),(177,4),(178,4),(194,4),(180,5),(68,6),(69,6),(72,6),(77,6),(78,6),(84,6),(90,6),(183,6),(193,6),(4,7),(5,7),(6,7),(17,7),(18,7),(22,7),(27,7),(28,7),(29,7),(33,7),(34,7),(35,7),(48,7),(51,7),(65,7),(66,7),(73,7),(74,7),(75,7),(81,7),(86,7),(87,7),(88,7),(91,7),(93,7),(94,7),(95,7),(97,7),(99,7),(107,7),(109,7),(110,7),(130,7),(131,7),(132,7),(133,7),(134,7),(135,7),(136,7),(137,7),(138,7),(139,7),(140,7),(144,7),(148,7),(151,7),(152,7),(157,7),(159,7),(162,7),(163,7),(166,7),(167,7),(173,7),(174,7),(179,7),(184,7),(202,7),(204,7),(205,7),(206,7),(207,7),(9,8),(21,8),(30,8),(61,8),(98,8),(129,8),(14,9),(15,9),(16,9),(63,9),(64,9),(156,9),(158,9),(187,9),(203,9),(191,10),(191,11),(2,12),(8,12),(13,12),(19,12),(38,12),(43,12),(49,12),(50,12),(56,12),(63,12),(67,12),(71,12),(96,12),(118,12),(122,12),(123,12),(142,12),(143,12),(146,12),(149,12),(168,12),(170,12),(176,12),(181,12),(186,12),(190,12),(197,12),(198,12),(82,13),(85,14),(89,14),(127,14),(175,14),(195,14),(36,15),(39,15),(11,16),(12,16),(70,16),(113,16),(121,16),(1,17),(7,17),(38,17),(42,17),(3,18),(26,18),(47,18),(62,18),(79,18),(83,18),(125,18),(126,18),(145,18),(150,18),(171,18),(182,18),(196,18),(8,19),(20,19),(24,19),(25,19),(37,19),(40,19),(44,19),(54,19),(76,19),(101,19),(102,19),(112,19),(114,19),(115,19),(116,19),(117,19),(119,19),(141,19),(153,19),(161,19),(165,19),(182,19),(185,19),(192,19),(10,20),(108,20),(154,20),(210,20),(100,21);
/*!40000 ALTER TABLE `leki_kategoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-15 21:53:16
