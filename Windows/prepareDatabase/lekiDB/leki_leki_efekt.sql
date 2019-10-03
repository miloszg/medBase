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
-- Table structure for table `leki_efekt`
--

DROP TABLE IF EXISTS `leki_efekt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `leki_efekt` (
  `leki_id` int(11) NOT NULL,
  `efekt_id` int(11) NOT NULL,
  PRIMARY KEY (`leki_id`,`efekt_id`),
  KEY `efekt_id` (`efekt_id`),
  CONSTRAINT `leki_efekt_ibfk_1` FOREIGN KEY (`leki_id`) REFERENCES `leki` (`id`) ON DELETE CASCADE,
  CONSTRAINT `leki_efekt_ibfk_2` FOREIGN KEY (`efekt_id`) REFERENCES `efekt` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leki_efekt`
--

LOCK TABLES `leki_efekt` WRITE;
/*!40000 ALTER TABLE `leki_efekt` DISABLE KEYS */;
INSERT INTO `leki_efekt` VALUES (103,1),(99,2),(53,3),(10,4),(141,5),(145,5),(171,5),(68,6),(72,6),(184,7),(169,8),(119,9),(153,10),(210,11),(135,12),(179,12),(107,13),(108,13),(109,13),(110,13),(18,14),(184,14),(8,15),(16,16),(97,17),(153,18),(131,19),(132,19),(133,19),(18,20),(204,20),(53,21),(54,21),(161,21),(56,22),(74,23),(20,24),(33,25),(73,26),(153,27),(121,28),(161,28),(127,29),(38,30),(68,31),(69,31),(72,31),(159,32),(26,33),(61,34),(37,35),(108,36),(130,36),(208,37),(40,38),(98,38),(129,38),(207,39),(31,40),(32,40),(134,41),(165,42),(142,43),(208,43),(166,44),(167,44),(52,45),(2,46),(38,46),(49,46),(50,46),(57,46),(58,46),(59,46),(60,46),(91,46),(96,46),(118,46),(187,47),(66,48),(164,49),(36,50),(39,50),(33,51),(61,52),(151,53),(124,54),(174,55),(168,56),(149,57),(85,58),(23,59),(124,60),(70,61),(52,62),(184,63),(204,63),(199,64),(200,64),(156,65),(117,66),(184,67),(130,68),(56,69),(155,69),(207,70),(147,71),(103,72),(104,72),(105,72),(106,72),(183,73),(5,74),(36,75),(39,75),(2,76),(38,76),(49,76),(50,76),(75,77),(24,78),(25,78),(62,78),(150,78),(28,79),(130,80),(66,81),(89,82),(47,83),(118,84),(23,85),(98,86),(144,87),(13,88),(19,88),(43,88),(149,88),(186,88),(81,89),(173,89),(203,90),(65,91),(209,92),(182,93),(65,94),(41,95),(55,95),(56,95),(92,95),(177,95),(178,95),(191,96),(57,97),(77,98),(78,98),(3,99),(61,100),(24,101),(25,101),(91,101),(98,101),(182,101),(194,102),(1,103),(42,103),(80,104),(112,105),(111,106),(9,107),(190,108),(122,109),(123,109),(64,110),(18,111),(184,111),(33,112),(35,112),(99,113),(10,114),(175,115),(40,116),(76,116),(48,117),(19,118),(43,118),(71,118),(180,119),(196,120),(170,121),(81,122),(142,122),(143,122),(63,123),(114,124),(115,124),(116,124),(117,124),(27,125),(154,126),(47,127),(171,127),(45,128),(46,128),(147,128),(200,128),(101,129),(128,130),(94,131),(131,131),(132,131),(133,131),(134,131),(202,131),(101,132),(119,133),(120,134),(114,135),(115,135),(116,135),(101,136),(114,137),(115,137),(116,137),(117,137),(141,138),(196,139),(112,140),(168,141),(176,141),(186,141),(54,142),(185,143),(51,144),(127,145),(140,146),(172,147),(11,148),(12,148),(188,149),(189,149),(14,150),(67,151),(146,151),(30,152),(93,153),(34,154),(75,155),(111,156),(125,157),(137,158),(205,158),(194,159),(24,160),(25,160),(138,161),(148,162),(194,163),(122,164),(17,165),(195,166),(47,167),(139,168),(113,169),(207,170),(83,171),(84,171),(121,171),(95,172),(157,173),(53,174),(54,174),(164,174),(86,175),(87,175),(88,175),(140,175),(10,176),(22,176),(29,176),(24,177),(25,177),(26,177),(38,177),(57,177),(58,177),(59,177),(60,177),(129,177),(182,177),(201,177),(14,178),(15,179),(192,180),(122,181),(123,181),(143,181),(186,181),(125,182),(63,183),(35,184),(21,185),(79,185),(126,185),(141,185),(160,185),(183,185),(102,186),(190,187),(4,188),(5,188),(6,188),(206,188),(207,188),(165,189),(193,190),(152,191),(67,192),(190,193),(7,194),(9,195),(136,196),(162,196),(163,196),(44,197),(181,198),(90,199),(128,200),(197,200),(158,201),(198,202),(64,203),(203,204),(147,205);
/*!40000 ALTER TABLE `leki_efekt` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-15 21:53:15