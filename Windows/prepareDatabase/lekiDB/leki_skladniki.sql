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
-- Table structure for table `skladniki`
--

DROP TABLE IF EXISTS `skladniki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `skladniki` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nazwa` (`nazwa`)
) ENGINE=InnoDB AUTO_INCREMENT=343 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skladniki`
--

LOCK TABLES `skladniki` WRITE;
/*!40000 ALTER TABLE `skladniki` DISABLE KEYS */;
INSERT INTO `skladniki` VALUES (220,'Absinthium D1'),(298,'acenokumarol'),(266,'acetylocysteina'),(22,'acitretin'),(236,'acyklowir'),(267,'alantoina'),(200,'Alchemilla vulgaris D2'),(331,'Aloe D4'),(166,'Ambra grisea D6'),(264,'aminokwasowy chelat chromu'),(223,'aminokwasowy chelat magnezu'),(219,'amylaza'),(77,'Anamirta cocculus D4'),(27,'anastrozol'),(270,'Antimonium crudum D2'),(67,'antygen wirusa kleszczowego zapalenia mozgu'),(202,'Arnica D8'),(221,'Arnica montana 9CH'),(186,'Asa foetida D4'),(57,'Bacopa monniera'),(83,'bacytracyna'),(247,'Badiaga D3'),(215,'Barium carbonicum D6'),(41,'Baryta carbonica D4'),(161,'beta karoten'),(243,'beta-karoten'),(74,'Bifidobacterium'),(291,'Bifidobacterium bifidum'),(108,'bioflawonoidy cytrusowe'),(127,'bromfenak'),(140,'bromokryptyna'),(42,'budezonid'),(70,'Calcarea acetica D2'),(90,'Carduus marianus D2'),(259,'Cerium oxalicum D4'),(205,'chelat aminokwasowy cynku Albion'),(91,'chelat aminokwasowy manganu Albion'),(143,'chelat aminokwasowy miedzi Albion'),(1,'chelat aminokwasowy selenu Albion'),(24,'Chelidonium D3'),(340,'chlorek chromu'),(94,'chlorek cynku'),(20,'chlorek potasu'),(146,'chlorowodorek  lerkanidypiny'),(191,'chlorowodorek dezipraminy'),(147,'chlorowodorek fenylefryny'),(332,'chlorowodorek metadonu'),(257,'chlorowodorek metforminy'),(289,'chrom'),(244,'ciprofibrat'),(273,'Cocculus D6'),(201,'Cocculus indicus 4 CH'),(198,'Conium D4'),(144,'Conium maculatum D3'),(248,'Crataegus D1'),(66,'Crataegus D2'),(59,'cynk'),(18,'cyprofloksacyna'),(319,'cytrynian alweryny'),(307,'cytrynian choliny'),(68,'cytrynian cynku'),(46,'cytrynian magnezu'),(142,'cytrynian nikametanu'),(185,'czaber ogrodowy'),(71,'dichlorowodorek betahistyny'),(11,'dichlorowodorek trimetazydyny'),(317,'digoksyna'),(167,'dimetyloeter'),(136,'diosmina'),(122,'dwuzasadowy fosforan  wapnia'),(61,'ekonazol'),(37,'ekstrakt Garcinia cambogia'),(178,'ekstrakt z czerwonego ryzu'),(218,'ekstrakt z czosnku'),(69,'ekstrakt z dzikiej rozy'),(335,'ekstrakt z gorzkiej pomaranczy'),(322,'ekstrakt z guarany'),(128,'ekstrakt z imbiru'),(43,'ekstrakt z indygo'),(227,'ekstrakt z jezowki purpurowej'),(49,'ekstrakt z klaczy ostryzu dlugiego'),(194,'ekstrakt z kory wierzby bialej'),(285,'ekstrakt z korzenia kozlka'),(197,'ekstrakt z korzenia tarczycy bajkalskiej'),(73,'ekstrakt z kwiatostanu lipy'),(162,'ekstrakt z kwiatu rumianku'),(217,'ekstrakt z lisci karczocha'),(33,'ekstrakt z lisci melisy'),(327,'ekstrakt z lisci miety pieprzowej'),(124,'ekstrakt z lisci milorzebu japonskiego'),(336,'ekstrakt z melisy'),(260,'ekstrakt z owocu borowki'),(203,'ekstrakt z pedow owsa'),(274,'ekstrakt z pestek winogron'),(173,'ekstrakt z pokrzywy zwyczajnej'),(109,'ekstrakt z szyszek chmielu'),(316,'ekstrakt z ziela skrzypu'),(96,'ekstrakt z zielonej herbaty'),(159,'ekstrakt z zywotnika zachodniego'),(265,'erdosteina'),(323,'erytromycyna'),(60,'etosuksymid'),(2,'eukaliptus'),(35,'Euspongia officinalis D3'),(117,'fenofibrat'),(84,'figi'),(54,'finasteryd'),(294,'fitosterole'),(15,'fluoksetyna'),(286,'fluorouracyl'),(276,'folacyna'),(176,'Fruitflow'),(149,'fruktooligosacharydy'),(204,'Fucus vesiculosus D2'),(278,'fumaran zelaza'),(226,'ginkgo biloba'),(75,'gliceryna'),(250,'Glonoinum D5'),(44,'Glonoinum D6'),(4,'glukonian miedzi'),(341,'glukonolaktobionian wapnia'),(284,'guma guar'),(85,'gwajafenezyna'),(89,'hialuronian sodu'),(189,'hydrochlorotiazyd'),(320,'hydrolizat bialek kazeinowych'),(30,'inaktywowany wirus wscieklizny'),(180,'inozyna pranobeks'),(158,'kalcytonina lososiowa'),(209,'Kalium carbonicum D3'),(7,'Kalium carbonicum D5'),(196,'Kalium iodatum D4'),(261,'klarytromycyna'),(170,'klobazam'),(163,'koenzym Q10'),(53,'kofeina'),(150,'kokarboksylaza'),(81,'kolagen'),(38,'kompleks skladnikow mineralnych z mleka'),(305,'kompleks witaminy C i bioflawonoidow cytrusowych PureWay-C'),(228,'koncentrat z daktyli'),(52,'krwawnik pospolity'),(230,'krzemionka'),(280,'krzemionka  z pedow bambusa'),(237,'kwas acetylosalicylowy'),(337,'kwas alfa-liponowy'),(139,'kwas cytrynowy'),(115,'kwas dokozaheksaenowy'),(333,'kwas eikozapantaenowy'),(105,'kwas foliowy'),(246,'kwas fusydowy'),(268,'kwas hialuronowy'),(126,'kwas jablkowy'),(72,'kwas mlekowy'),(134,'kwas pantotenowy'),(210,'kwas salicylowy'),(254,'kwas ursodeoksycholowy'),(165,'kwasy Omega-3'),(277,'kwasy Omega-6'),(23,'kwasy tluszczowe omega-3'),(87,'kwetiapina'),(104,'kwiat hibiskusa'),(114,'L- leucyna'),(224,'L-arginina'),(111,'L-cystyna'),(252,'L-karnityna'),(110,'L-ornityna'),(107,'L-tauryna'),(6,'Lachesis mutus D8'),(292,'Lactobacillus acidophilus'),(211,'Lactobacillus delbrueckii ssp. bulgaricus'),(28,'Lactobacillus Fermentum 57A'),(312,'Lactobacillus gasseri 57C'),(183,'Lactobacillus plantarum 57B'),(157,'Lactobacillus rhamnosus GG'),(314,'laktobionian wapnia'),(329,'laktoza'),(97,'len zwyczajny'),(288,'lewomepromazyna'),(269,'lidokaina'),(50,'likopen'),(256,'losartan potasu'),(133,'luteina'),(318,'Lycopodium D4'),(63,'Lycopus virginicus D4'),(325,'magnez'),(13,'maleinian flupirtyny'),(34,'maleinian pirylaminy'),(125,'mangan'),(309,'mebendazol'),(98,'medroksyprogesteron'),(116,'mentol'),(279,'mesterolon'),(171,'metanosulfonian dihydroergokrystyny'),(301,'metionina'),(245,'metokarbamol'),(45,'metotreksat'),(78,'metylosulfonylometan'),(16,'miedz'),(251,'mieta pieprzowa'),(3,'mniszek lekarski'),(56,'monakolina K'),(297,'mupirocyna'),(12,'N-acetylowodoroasparaginian potasu'),(263,'nadtlenek wodoru'),(190,'nalewka z lisci szalwi'),(258,'naproksen'),(123,'naturalne enzymy trawienne'),(338,'neomycyna'),(26,'nikotynamid'),(212,'Nitroglycerinum D3'),(231,'Nux vomica 4 CH'),(342,'Nux vomica D4'),(101,'octan cyproteronu'),(99,'octan wapnia'),(239,'olej rybi'),(102,'olej z kryla'),(182,'olej z nasion ogorecznika'),(240,'olej z nasion wiesiolka'),(330,'olej z ogorecznika'),(8,'olej z watroby dorsza'),(310,'olej z watroby rekina'),(64,'olejek anyzowy'),(36,'olejek mietowy'),(153,'paczki dzikiej rozy'),(315,'pankreatyna'),(129,'pantoprazol'),(207,'pantotenian wapnia'),(174,'paracetamol'),(311,'Paris quadrifolia D4'),(222,'pasta figowa'),(14,'Petroleum D5'),(39,'Petroleum rectificatum 4 CH'),(271,'Petroleum rectificatum D8'),(100,'Phosphor D6'),(62,'plucnica islandzka'),(141,'polikosanole z trzciny cukrowej'),(151,'poziomka pospolita'),(80,'propafenon'),(241,'proteaza'),(188,'pulpa z tamaryndowca'),(148,'Pulsatilla pratensis D4'),(238,'puree z daktyli'),(293,'rabarbar'),(132,'racekadotryl'),(290,'risperidon'),(175,'rozmaryn lekarski'),(118,'rutozyd'),(55,'rutyna'),(137,'rywastygmina'),(164,'selen'),(95,'selenian sodu'),(233,'Selenicereus grandiflorus D1'),(120,'Selenicereus grandiflorus D3'),(326,'siarczan cynku'),(300,'siarczan dihydralazyny'),(253,'siarczan glukozaminy'),(154,'siarczan polimyksyny B'),(299,'siarka stracona'),(302,'sildenafil'),(321,'smola weglowa'),(181,'sod'),(47,'sol Seignetta'),(172,'sol sodowa diklofenaku'),(152,'sol sodowa heparyny'),(92,'sol sodowa kwasu hialuronowego'),(283,'Spigelia anthelmia D3'),(5,'Spigelia D5'),(206,'spironolakton'),(113,'sproszkowany owoc aceroli'),(187,'sproszkowany owoc czarnej porzeczki'),(234,'sproszkowany owoc dzikiej rozy'),(229,'Streptococcus thermophilus'),(214,'suchy wyciag z borowki czernicy'),(249,'symetykon'),(339,'syrop zbozowy'),(138,'szczepionka przeciw H. influenzae typ b'),(255,'Tabacum 4CH'),(324,'tamandrynowiec'),(51,'Taraxacum D2'),(193,'teofilina'),(275,'terbinafina'),(131,'testosteron'),(156,'Thuja occidentalis D6'),(313,'Thyroidinum D8'),(21,'tianeptyna'),(306,'tinidazol'),(328,'tlenek cynku'),(272,'tlenek magnezu'),(199,'tolterodyny octan'),(9,'topiramat'),(155,'trastuzumab'),(168,'trokserutyna'),(282,'tyzanidyna'),(88,'walsartan'),(145,'wapn'),(93,'weglan wapnia'),(334,'winian ergotaminy'),(31,'winian L-karnityny'),(304,'witamina A'),(119,'witamina B1 (tiamina)'),(216,'witamina B12 (cyjanokobalamina)'),(295,'witamina B2 (ryboflawina)'),(235,'witamina B5 (kwas pantotenowy)'),(287,'witamina B6 (pirydoksyna)'),(10,'witamina C (kwas askorbinowy)'),(213,'witamina D (cholekalcyferol)'),(40,'witamina D3 (cholekalcyferol)'),(169,'witamina E (tokoferol)'),(82,'witamina H (biotyna)'),(242,'witamina PP (niacyna)'),(29,'wlokna akacji'),(25,'wodoronadtlenek mocznika'),(130,'wyciag olejowy z nasion pietruszki'),(48,'wyciag plynny z korzenia kozlka'),(308,'wyciag plynny z owocow glogu'),(192,'wyciag suchy z lisci senesu'),(58,'wyciag z chmielu'),(195,'wyciag z czarnego bzu'),(262,'wyciag z guarany'),(17,'wyciag z karczocha zwyczajnego'),(225,'wyciag z korzenia kozlka lekarskiego'),(232,'wyciag z lisci melisy'),(296,'wyciag z lisci miety pieprzowej'),(281,'wyciag z liscia melisy'),(303,'wyciag z liscia szalwi'),(32,'wyciag z miety pieprzowej'),(65,'wyciag z ostryzu dlugiego'),(112,'wyciag z owocow zurawiny wielkoowocowej'),(208,'wyciag z pieciornika'),(19,'wyciag z rzodkwi zwyczajnej'),(76,'wyciag z ziela dziurawca'),(177,'wyciag z ziela melisy'),(86,'wyciag z ziela pokrzywy'),(106,'wyciagi alergenowe lub ich mieszanki'),(79,'yerba mate'),(135,'Yucca D2'),(184,'zeaksantyna'),(121,'zen-szen'),(103,'zielona herbata'),(179,'Zincum sarcolacticum 6 DH'),(160,'[');
/*!40000 ALTER TABLE `skladniki` ENABLE KEYS */;
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