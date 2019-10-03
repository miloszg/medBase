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
-- Table structure for table `postac`
--

DROP TABLE IF EXISTS `postac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `postac` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nazwa` (`nazwa`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postac`
--

LOCK TABLES `postac` WRITE;
/*!40000 ALTER TABLE `postac` DISABLE KEYS */;
INSERT INTO `postac` VALUES (17,'aerozol'),(16,'aerozol do nosa'),(44,'czopki doodbytnicze'),(19,'drazetki'),(42,'granulat do przygotowania zawiesiny doustnej'),(22,'granulki homeopatyczne'),(47,'iniekcje'),(5,'kapsulki'),(37,'kapsulki o przedluzonym uwalnianiu'),(18,'kapsulki twarde'),(14,'kostki'),(10,'krem'),(45,'krople'),(4,'krople do oczu'),(27,'liofilizat doustny'),(21,'masc'),(15,'masc do nosa'),(26,'pastylki do ssania'),(7,'plastry'),(28,'plyn'),(43,'plyn do plukania jamy ustnej'),(3,'plyn do stosowania na skore'),(8,'plyn doustny'),(31,'proszek do inhalacji'),(33,'proszek do sporzadzania zawiesiny doustnej'),(38,'proszek i rozpuszczalnik do sporzadzania roztworu'),(20,'proszek i zawiesina do przygotowania zawiesiny do wstrzykiwan'),(11,'roztwor do stosowania podjezykowego'),(24,'roztwor do wstrzykiwan'),(2,'roztwor do wstrzykiwan domiesniowych'),(25,'roztwor do wstrzykiwan dostawowych'),(34,'roztwor doustny'),(32,'syrop'),(9,'system transdermalny'),(13,'tabletki'),(1,'tabletki do ssania'),(40,'tabletki do zucia'),(6,'tabletki dojelitowe'),(36,'tabletki drazowane'),(23,'tabletki musujace'),(46,'tabletki o przedluzonym uwalnianiu'),(41,'tabletki o zmodyfikowanym uwalnianiu'),(30,'tabletki podjezykowe'),(35,'tabletki powlekane'),(39,'zawiesina do wstrzykiwan'),(12,'zel'),(29,'zelki');
/*!40000 ALTER TABLE `postac` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-15 21:53:17
