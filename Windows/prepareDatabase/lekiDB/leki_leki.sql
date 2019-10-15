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
-- Table structure for table `leki`
--

DROP TABLE IF EXISTS `leki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `leki` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(128) NOT NULL,
  `info` text,
  `dawkowanie` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nazwa` (`nazwa`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leki`
--

LOCK TABLES `leki` WRITE;
/*!40000 ALTER TABLE `leki` DISABLE KEYS */;
INSERT INTO `leki` VALUES (1,'Acenocumarol WZF  - Acenocoumarolum',NULL,NULL),(2,'Acenol  - Paracetamolum',NULL,NULL),(3,'Acerin  - Acidum lacticum, Acidum salicylicum',NULL,NULL),(4,'Acerola',NULL,NULL),(5,'Acerola plus',NULL,NULL),(6,'Acerola plus czarny bez',NULL,NULL),(7,'Acesan  - Acidum acetylsalicylicum',NULL,NULL),(8,'Acespargin  - Kalii acetylaspartas',NULL,NULL),(9,'Acetylcysteine Sandoz (ACC)  - Acetylcysteinum',NULL,NULL),(10,'Acidolac',NULL,NULL),(11,'Anastrozol-ratiopharm  - Anastrozolum',NULL,NULL),(12,'Anastrozole Accord  - Anastrozolum',NULL,NULL),(13,'Andepin  - Fluoxetinum',NULL,NULL),(14,'Androcur  - Cyproteroni acetas',NULL,NULL),(15,'Androster  - Finasteridum',NULL,NULL),(16,'Androtop  - Testosteronum',NULL,NULL),(17,'AndroVit',NULL,NULL),(18,'Anedox',NULL,NULL),(19,'Aneptinex  - Tianeptinum',NULL,NULL),(20,'Anesteloc  - Pantoprazolum',NULL,NULL),(21,'Bactroban (masc do nosa)  - Mupirocinum',NULL,NULL),(22,'Bactum',NULL,NULL),(23,'Badiaga complexe Nr 47',NULL,NULL),(24,'Baikadent (plyn)  - Scutellariae baicalensis radix extractum',NULL,NULL),(25,'Baikadent (zel)  - Scutellariae baicalensis radix extractum',NULL,NULL),(26,'Baikaderm  - Scutellariae baicalensis radicis extractum,  Allantoinum',NULL,NULL),(27,'Bakol Forte',NULL,NULL),(28,'Bakozin',NULL,NULL),(29,'Baktimax',NULL,NULL),(30,'Baladex  - Theophyllinum, Guaifenesinum',NULL,NULL),(31,'Cactus compositum S (iniekcje)',NULL,NULL),(32,'Cactus Compositum S (krople)',NULL,NULL),(33,'Calcenato',NULL,NULL),(34,'Calci Strong+Magnez',NULL,NULL),(35,'Calcifos',NULL,NULL),(36,'Calcihexal  - Calcitoninum salmonis',NULL,NULL),(37,'Calcilac  - Calcii carbonas, Colecalciferolum',NULL,NULL),(38,'Calcipiryna  - Acidum acetylsalicylicum, Calcii carbonas',NULL,NULL),(39,'Calcitonin  - Calcitoninum salmonis',NULL,NULL),(40,'Calcium (syrop)  - Calcii glubionas, Calcii lactobionas',NULL,NULL),(41,'Co-Valsacor  - Valsartanum, Hydrochlorothiazidum',NULL,NULL),(42,'Coaparin  - Heparinum natricum',NULL,NULL),(43,'Coaxil  - Tianeptinum',NULL,NULL),(44,'Cocarboxylasum  - Cocarboxylasum',NULL,NULL),(45,'Cocculine',NULL,NULL),(46,'Cocculus Dagomed 21 choroba lokomocyjna',NULL,NULL),(47,'Cocois  - Pix litanthracis, Acidum salicylicum, Sulfur praecipitatum',NULL,NULL),(48,'Cod Liver Oil',NULL,NULL),(49,'Codipar  - Paracetamolum',NULL,NULL),(50,'Codipar  (czopki)  - Paracetamolum',NULL,NULL),(51,'DigeZym',NULL,NULL),(52,'Digoxin  - Digoxinum',NULL,NULL),(53,'DIH  - Diosminum',NULL,NULL),(54,'DIH Max  - Diosminum',NULL,NULL),(55,'Dihydralazinum  - Dihydralazini sulphas',NULL,NULL),(56,'Dihydroergotoxinum aethanosulfonicum  - Dihydroergotoxini edisilas',NULL,NULL),(57,'Diklofenak Lgo  - Diclofenacum natricum',NULL,NULL),(58,'Diklonat P (iniekcje)  - Diclofenacum natricum',NULL,NULL),(59,'Diklonat P (zel)  - Diclofenacum natricum',NULL,NULL),(60,'Dikloziaja  - Diclofenacum natricum',NULL,NULL),(61,'Erdomed  - Erdosteinum',NULL,NULL),(62,'Erfin  - Terbinafinum',NULL,NULL),(63,'Ergolaktyna  - Bromocriptinum',NULL,NULL),(64,'Ergotaminum tartaricum  - Ergotamini tartras',NULL,NULL),(65,'Erotic dla kobiet',NULL,NULL),(66,'Erotic dla mezczyzn',NULL,NULL),(67,'Erudan  - Topiramatum',NULL,NULL),(68,'Erythromycinum TZF (Erythromycinum)  - Erythromycinum',NULL,NULL),(69,'Erytromycinum pro suspensione  - Erythromycinum',NULL,NULL),(70,'Esberitox N  - Preparat zlozony',NULL,NULL),(71,'Frisium  - Clobazamum',NULL,NULL),(72,'Fromilid  - Clarithromycinum',NULL,NULL),(73,'Fructolax',NULL,NULL),(74,'Fructolax (kostki)',NULL,NULL),(75,'Fructolax (plyn)',NULL,NULL),(76,'FrutiCal 200  - Calcii carbonas',NULL,NULL),(77,'FSME-IMMUN  - Vaccinum encephalitidis ixodibus advectae inactivatum',NULL,NULL),(78,'FSME-IMMUN Junior  - Vaccinum encephalitidis ioxodibus advectae inactivatum',NULL,NULL),(79,'Fucidin (krem, masc)  - Acidum fusidicum',NULL,NULL),(80,'Fucus Complexe Nr 111',NULL,NULL),(81,'Herbsen',NULL,NULL),(82,'Herceptin',NULL,NULL),(83,'Herpex  - Aciclovirum',NULL,NULL),(84,'Heviran  - Aciclovirum',NULL,NULL),(85,'Hialeye',NULL,NULL),(86,'Hialu-Femin',NULL,NULL),(87,'Hialucell 4900',NULL,NULL),(88,'Hialuskin 4900',NULL,NULL),(89,'Hialux',NULL,NULL),(90,'HiB Titer  - Vaccinum haemophili influenzae B',NULL,NULL),(91,'Karmelki Extra Starka',NULL,NULL),(92,'Karnidin  - Lercanidipini hydrochloridum',NULL,NULL),(93,'Karnifort',NULL,NULL),(94,'Karnityna Ambio',NULL,NULL),(95,'Karovit',NULL,NULL),(96,'Katadolon  - Flupirtini maleas',NULL,NULL),(97,'Katelin+',NULL,NULL),(98,'Kato Nasal Spray  - Pyrlamini maleas, Phenylephrini hydrochloridum',NULL,NULL),(99,'KC Energy maxsss',NULL,NULL),(100,'KC Super AntyKac',NULL,NULL),(101,'Lipancrea 16000  - Pancreatinum',NULL,NULL),(102,'Lipancrea 8000  - Pancreatinum',NULL,NULL),(103,'Lipanor  - Ciprofibratum',NULL,NULL),(104,'Lipanthyl  - Fenofibratum',NULL,NULL),(105,'Lipanthyl NT 145  - Fenofibratum',NULL,NULL),(106,'Lipanthyl Supra  - Fenofibratum',NULL,NULL),(107,'LipiForma',NULL,NULL),(108,'LipiForma Plus',NULL,NULL),(109,'Lipiminor',NULL,NULL),(110,'Lipobon',NULL,NULL),(111,'Metazydyna MR  - Trimetazidini dihydrochloridum',NULL,NULL),(112,'Meteospasmyl  - Alverini citras, Simeticonum',NULL,NULL),(113,'Metex  - Methotrexatum',NULL,NULL),(114,'Metfogamma  - Metformini hydrochloridum',NULL,NULL),(115,'Metformax  - Metformini hydrochloridum',NULL,NULL),(116,'Metformax SR  - Metformini hydrochloridum',NULL,NULL),(117,'Metformin  - Metformini hydrochloridum',NULL,NULL),(118,'Methadone hydrochloride  - Methadonum',NULL,NULL),(119,'Methiovit  - Choline, L-Methionine, Vitamins',NULL,NULL),(120,'Methocarbamol  - Methocarbamolum',NULL,NULL),(121,'Neosine syrop  - Inosinum pranobexum',NULL,NULL),(122,'Neospasmina  - Crataegus sp., Valeriana officinalis',NULL,NULL),(123,'Neospasmol  - Crataegus sp., Valeriana officinalis',NULL,NULL),(124,'Neosynephrin-POS 10%  - Phenylephrini hydrochloridum',NULL,NULL),(125,'Neotigason  - Acitretinum',NULL,NULL),(126,'Neotopic  - Bacitracinum, Neomycinum,Polymyxinum B',NULL,NULL),(127,'NeoVisc',NULL,NULL),(128,'Neoxen  - Naproxenum',NULL,NULL),(129,'Neplit Easyhaler  - Budesonidum',NULL,NULL),(130,'Neptune Krill Oil',NULL,NULL),(131,'Olimp L-karnityna',NULL,NULL),(132,'Olimp L-karnityna Forte Plus',NULL,NULL),(133,'Olimp L-karnityna Plus',NULL,NULL),(134,'Olimp Lady Term',NULL,NULL),(135,'Olimp MSM forte',NULL,NULL),(136,'Olimp Odpormax',NULL,NULL),(137,'Olimp Omega 3',NULL,NULL),(138,'Olimp Omega 3 Gold',NULL,NULL),(139,'Olimp Osteoblock',NULL,NULL),(140,'Olimp Perfect Skin',NULL,NULL),(141,'Peroxyl Dental Gel 1%  - Hydrogenii peroxidum',NULL,NULL),(142,'Persen  - Valerianae radix, Melissae folium,  Menthae herba',NULL,NULL),(143,'Persen forte  - Valerianae radix, Melissae folium, Menthae herba',NULL,NULL),(144,'Perspi Block',NULL,NULL),(145,'Pertlenon  - Urea peroxydata',NULL,NULL),(146,'Petinimid  - Ethosuximidum',NULL,NULL),(147,'Petroleum D5',NULL,NULL),(148,'Petroselin',NULL,NULL),(149,'Petylyl  - Desipramini hydrochloridum',NULL,NULL),(150,'Pevaryl  - Econazoli nitras',NULL,NULL),(151,'ProTrawin Optimal',NULL,NULL),(152,'ProUro',NULL,NULL),(153,'Proursan  - Acidum ursodeoxycholicum',NULL,NULL),(154,'ProVag',NULL,NULL),(155,'Provasan  - Nicametati citras',NULL,NULL),(156,'Provera  - Medroxyprogesteronum',NULL,NULL),(157,'Providin',NULL,NULL),(158,'Proviron  - Mesterolonum',NULL,NULL),(159,'Prowzrok',NULL,NULL),(160,'Proxacin (krople do oczu)  - Ciprofloxacinum',NULL,NULL),(161,'Rutokal C  - Acidum ascorbicum, Calcium, Rutosidum',NULL,NULL),(162,'Rutokal C plus',NULL,NULL),(163,'Rutoscorbovit',NULL,NULL),(164,'Rutoven  - Troxerutinum',NULL,NULL),(165,'Rutovit  C  - Acidum ascorbicum, Rutosidum',NULL,NULL),(166,'Rutyna C Forte',NULL,NULL),(167,'Rutyna-C',NULL,NULL),(168,'Ryspolit  - Risperidonum',NULL,NULL),(169,'Rytmonorm  - Propafenonum',NULL,NULL),(170,'Rywastygmina Apotex  - Rivastigminum',NULL,NULL),(171,'Spirytus salicylowy  - Acidum salicylicum',NULL,NULL),(172,'Sportenine',NULL,NULL),(173,'Spox',NULL,NULL),(174,'Spox Noc',NULL,NULL),(175,'SST - Tabletki pobudzajace wydzielanie sliny',NULL,NULL),(176,'Stadaquel  - Quetiapinum',NULL,NULL),(177,'Stadazar  - Losartanum kalicum',NULL,NULL),(178,'Stadazar HCT  - Losartanum kalicum, Hydrochlorothiazidum',NULL,NULL),(179,'Staflexis',NULL,NULL),(180,'Staloral  - Preparat zlozony',NULL,NULL),(181,'Tinctura Hyperici  - Hipericum perforatum',NULL,NULL),(182,'Tinctura Salviae  - Salvia officinalis',NULL,NULL),(183,'Tinidazolum  - Tinidazolum',NULL,NULL),(184,'Tiolip',NULL,NULL),(185,'Tiorfan  - Racecadotrilum',NULL,NULL),(186,'Tisercin  - Levomepromazinum',NULL,NULL),(187,'Titlodine  - Tolterodini tartras',NULL,NULL),(188,'Tizanidine Arrow  - Tizanidinum',NULL,NULL),(189,'Tizanor  - Tizanidinum',NULL,NULL),(190,'Tobacoff  - Anisi oleum, Mentha piperita, Paulinia cupana, Potentilla tormentilla',NULL,NULL),(191,'Vermox  - Mebendazolum',NULL,NULL),(192,'Verolax  - Sennae folium extractum siccum',NULL,NULL),(193,'Verorab  - Vaccinum rabiei ex cellulis ad usum humanum',NULL,NULL),(194,'Verospiron  - Spironolactonum',NULL,NULL),(195,'Verrukil',NULL,NULL),(196,'Verrumal  - Fluorouracilum, Acidum salicylicum',NULL,NULL),(197,'Versatis  - Lidocainum',NULL,NULL),(198,'Vertigen  - Betahistini dihydrochloridum',NULL,NULL),(199,'Vertigoheel (iniekcje)',NULL,NULL),(200,'Vertigoheel (tabletki, krople)',NULL,NULL),(201,'Yellox  - Brompfenacum',NULL,NULL),(202,'YerbaSlim',NULL,NULL),(203,'Yextor  - Sildenafilum',NULL,NULL),(204,'Yomi Multiwitamina',NULL,NULL),(205,'Yomi Omega-3',NULL,NULL),(206,'Yomi Witamina C odpornosc',NULL,NULL),(207,'Yomi Zelazo',NULL,NULL),(208,'Ypsiloheel N',NULL,NULL),(209,'Yucca Complexe Nr 110',NULL,NULL),(210,'Zaax',NULL,NULL);
/*!40000 ALTER TABLE `leki` ENABLE KEYS */;
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