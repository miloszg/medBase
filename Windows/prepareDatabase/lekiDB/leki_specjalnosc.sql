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
-- Table structure for table `specjalnosc`
--

DROP TABLE IF EXISTS `specjalnosc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `specjalnosc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nazwa` (`nazwa`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specjalnosc`
--

LOCK TABLES `specjalnosc` WRITE;
/*!40000 ALTER TABLE `specjalnosc` DISABLE KEYS */;
INSERT INTO `specjalnosc` VALUES (10,'Alergologia'),(14,'Angiologia'),(23,'Chirurgia ogolna'),(27,'Choroby pluc'),(11,'Choroby wewnetrzne'),(30,'Choroby zakazne i pasozytnicze'),(3,'Dermatologia i wenerologia'),(8,'Diabetologia'),(22,'Dietetyka'),(29,'Endokrynologia'),(4,'Gastroenterologia'),(2,'Ginekologia i poloznictwo'),(5,'Hematologia'),(9,'Homeopatia'),(13,'Immunologia kliniczna'),(12,'Kardiologia'),(32,'Medycyna rodzinna'),(28,'Medycyna sportowa'),(17,'Neurologia'),(20,'Okulistyka'),(16,'Onkologia kliniczna'),(1,'Ortopedia i traumatologia narzadu ruchu'),(31,'Otolaryngologia'),(15,'Pediatria'),(19,'Proktologia'),(25,'przeciwnowotworowe'),(21,'przyspiesza przemiany metaboliczne alkoholu'),(24,'Psychiatria'),(18,'Reumatologia'),(26,'Seksuologia'),(7,'Stomatologia ogolna'),(6,'Urologia');
/*!40000 ALTER TABLE `specjalnosc` ENABLE KEYS */;
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
