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
-- Table structure for table `kategoria`
--

DROP TABLE IF EXISTS `kategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `kategoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nazwa` (`nazwa`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategoria`
--

LOCK TABLES `kategoria` WRITE;
/*!40000 ALTER TABLE `kategoria` DISABLE KEYS */;
INSERT INTO `kategoria` VALUES (18,'Dermatologia'),(20,'Dietetyczny srodek spozywczy specjalnego przeznaczenia medycznego'),(3,'Homeopatyczne'),(15,'Hormonalne (bez hormonow plciowych)'),(17,'Krew i uklad krwiotworczy'),(21,'Medycyna rodzinna'),(2,'Narzady wzroku i sluchu'),(13,'Onkologia kliniczna'),(12,'Osrodkowy uklad nerwowy'),(11,'owadobojcze i repelenty'),(16,'Przeciwnowotworowe i immunomodulujace'),(10,'Przeciwpasozytnicze'),(19,'Przewod pokarmowy i metabolizm'),(5,'Rozne'),(6,'Stosowane w zakazeniach (przeciwinfekcyjne)'),(7,'Suplementy diety'),(1,'Uklad miesniowo-szkieletowy'),(9,'Uklad moczowo-plciowy i hormony plciowe'),(8,'Uklad oddechowy'),(4,'Uklad sercowo-naczyniowy'),(14,'Wyroby medyczne');
/*!40000 ALTER TABLE `kategoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-15 21:53:18
