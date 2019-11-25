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
-- Table structure for table `efekt`
--

DROP TABLE IF EXISTS `efekt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `efekt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nazwa` (`nazwa`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `efekt`
--

LOCK TABLES `efekt` WRITE;
/*!40000 ALTER TABLE `efekt` DISABLE KEYS */;
INSERT INTO `efekt` VALUES (94,'afrodyzjak'),(201,'androgenne'),(6,'antybiotyk'),(167,'antyseptyczne'),(185,'bakteriobojcze'),(31,'bakteriostatyczne'),(129,'bialek i cukrow'),(156,'cytoprotekcyjne'),(138,'grzybobojcze'),(194,'hamuje agregacje plytek krwi'),(150,'hamuje dzialanie androgenow'),(65,'hamuje owulacje'),(24,'hamuje wydzielanie kwasu solnego w zoladku'),(183,'hamuje wydzielanie prolaktyny'),(139,'hamuje wzrost brodawek'),(169,'immunosupresyjne'),(120,'keratolityczne'),(155,'korzystnie wplywa na flore bakteryjna jelit'),(125,'korzystnie wplywa na poziom cholesterolu'),(40,'korzystnie wplywa na zaburzenia krazenia wiencowego'),(57,'lagodnie uspokajajaco'),(128,'lagodzi objawy choroby lokomocyjnej'),(198,'lagodzi stany depresyjne'),(71,'leczenie wspomagajace chorob skory i blon sluzowych'),(26,'lekko przeczyszczajace'),(51,'magnez i cynk'),(200,'miejscowe przeciwbolowe'),(130,'miejscowe przeciwzapalne'),(163,'moczopedne'),(109,'nasenne'),(58,'nawilzajace'),(182,'normalizuje odnowe i roznicowanie sie komorek naskorka'),(95,'obniza cisnienie tetnicze krwi'),(54,'obniza cisnienie wewnatrzgalkowe'),(72,'obniza stezenie cholesterolu i trojglicerydow we krwi'),(174,'ochronnie na naczynia krwionosne'),(106,'ochronnie na serce'),(5,'odkazajace'),(178,'oslabia poped plciowy'),(159,'oszczedza jony potasu'),(85,'pobudza procesy przemiany materii'),(115,'pobudza wydzielanie sliny'),(100,'pobudza wydzielanie sluzu'),(28,'podnosi odpornosc organizmu'),(162,'pomaga usunac przykry zapach z ust'),(92,'poprawia funkcje watroby w stanach zastoinowych'),(142,'poprawia napiecie scian naczyn zylnych'),(29,'poprawia ruchomosc stawu'),(52,'poprawia transport sluzowo-rzeskowy'),(202,'poprawia ukrwienie blednika'),(22,'poprawia ukrwienie serca'),(82,'poprawia wlasciwosci smarujace plynu maziowego'),(121,'poprawia zdolnosci poznawcze'),(110,'powoduje selektywny skurcz lub rozkurcz naczyn'),(114,'preparat probiotyczny'),(75,'przeciw osteoporozie'),(38,'przeciwalergiczne'),(8,'przeciwarytmiczne'),(101,'przeciwbakteryjne'),(143,'przeciwbiegunkowe'),(46,'przeciwbolowe'),(88,'przeciwdepresyjne'),(76,'przeciwgoraczkowe'),(78,'przeciwgrzybicze'),(56,'przeciwko schizofrenii'),(84,'przeciwko uzaleznieniu od heroiny i morfiny'),(199,'przeciwko zakazeniom wywolanym przez Haemophilus influenzae typu b'),(118,'przeciwlekowe'),(192,'przeciwmigrenowe'),(148,'przeciwnowotworowe'),(97,'przeciwobrzekowe'),(151,'przeciwpadaczkowe'),(123,'przeciwparkinsonowskie'),(73,'przeciwpierwotniakowe'),(141,'przeciwpsychotyczne'),(96,'przeciwrobacze'),(83,'przeciwswiadowe'),(171,'przeciwwirusowe'),(105,'przeciwwzdeciowe'),(103,'przeciwzakrzepowe'),(177,'przeciwzapalne'),(180,'przeczyszczajace'),(41,'przyspiesza procesy  przemiany materii'),(126,'przywraca odpowiednie pH pochwy'),(160,'regenerujace'),(45,'reguluje prace serca'),(77,'reguluje wyproznienia'),(152,'rozkurcza miesnie gladkie drog oddechowych'),(47,'rozkurcza miesnie gladkie pecherza moczowego'),(140,'rozkurcza miesnie gladkie przewodu pokarmowego'),(164,'rozkurczajace'),(10,'rozpuszcza kamienie zolciowe'),(107,'rozrzedza zalegajaca wydzieline'),(69,'rozszerza naczynia krwionosne'),(60,'rozszerza zrenice'),(93,'sciagajace'),(102,'sodopednie'),(37,'stabilizujaco na uklad nerwowy'),(119,'szczepionka odczulajaca'),(197,'uczestniczy w przemianach weglowodanow'),(86,'udraznia przewody nosowe'),(195,'ulatwia odkrztuszanie'),(193,'ulatwia odzwyczajenie sie od palenia tytoniu'),(90,'ulatwia osiagniecie erekcji'),(144,'ulatwia trawienie'),(122,'ulatwia zasypianie'),(98,'uodparnia przeciw odkleszczowemu zapaleniu mozgu'),(42,'uodparniajace'),(181,'uspokajajace'),(166,'usuwa kurzajki'),(99,'usuwa odciski i zgrubiala skore'),(14,'uzupelnia  diete w kwas gamma-linolenowy'),(17,'uzupelnia codzienna diete w potas'),(39,'uzupelnia codzienna diete w witamine B12'),(172,'uzupelnia diete w beta-karoten'),(74,'uzupelnia diete w bioflawonoidy cytrusowe'),(4,'uzupelnia diete w fruktooligosacharydy'),(111,'uzupelnia diete w kwas alfa-liponowy'),(170,'uzupelnia diete w kwas foliowy'),(158,'uzupelnia diete w kwasy tluszczowe omega-3'),(153,'uzupelnia diete w L-karnityne'),(161,'uzupelnia diete w niezbedne nienasycone kwasy tluszczowe'),(176,'uzupelnia diete w probiotyczne bakterie'),(63,'uzupelnia diete w selen'),(11,'uzupelnia diete w skladnik  wplywajacy na prawidlowa agregacje krwi'),(12,'uzupelnia diete w skladnik poprawiajace sprawnosc stawow'),(32,'uzupelnia diete w skladnik wspomagajace prawidlowe widzenie'),(173,'uzupelnia diete w skladniki korzystne dla wzroku'),(23,'uzupelnia diete w skladniki korzystnie wplywajace na  prace jelit'),(48,'uzupelnia diete w skladniki korzystnie wplywajace na libido'),(175,'uzupelnia diete w skladniki korzystnie wplywajace na nawilzenie skory'),(36,'uzupelnia diete w skladniki korzystnie wplywajace na uklad krazenia'),(89,'uzupelnia diete w skladniki lagodzace napiecie nerwowe'),(87,'uzupelnia diete w skladniki ograniczajace nadmierne wydzielanie potu'),(2,'uzupelnia diete w skladniki pobudzajace organizm'),(117,'uzupelnia diete w skladniki pobudzajace uklad odpornosciowy'),(146,'uzupelnia diete w skladniki spowalniajace procesy starzenia  skory'),(80,'uzupelnia diete w skladniki spowalniajace procesy starzenia organizmu'),(68,'uzupelnia diete w skladniki ulatwiajace usuwanie wolnych rodnikow'),(55,'uzupelnia diete w skladniki ulatwiajace zasypianie'),(131,'uzupelnia diete w skladniki wspomagajace odchudzanie'),(196,'uzupelnia diete w skladniki wspomagajace odpornosc'),(191,'uzupelnia diete w skladniki wspomagajace odpornosc drog moczowych'),(79,'uzupelnia diete w skladniki wspomagajace pamiec i koncentracje'),(53,'uzupelnia diete w skladniki wspomagajace trawienie'),(13,'uzupelnia diete w skladniki wspomagajace utrzymanie prawidlowego poziomu cholesterolu'),(19,'uzupelnia diete w skladniki wspomagajace wydolnosc fizyczna organizmu'),(113,'uzupelnia diete w skladniki wzmacniajace organizm'),(112,'uzupelnia diete w wapn'),(154,'uzupelnia diete w wapn i magnez'),(188,'uzupelnia diete w witamine C'),(44,'uzupelnia diete w witamine C i rutyne'),(25,'uzupelnia diete w witamine D3'),(67,'uzupelnia diete w witamine E'),(20,'uzupelnia diete w witaminy'),(165,'uzupelnia diete w witaminy i mineraly'),(7,'uzupelnia diete w witaminy z grupy B'),(70,'uzupelnia diete w zelazo'),(9,'uzupelnia niedobor choliny i metioniny'),(35,'uzupelnia niedobor wapnia i witaminy D3'),(116,'uzupelnia niedobory  wapnia'),(15,'uzupelnia niedobory potasu'),(133,'uzupelnia niedobory witamin'),(91,'wspomaga aktywnosc seksualna u kobiet'),(81,'wspomaga aktywnosc seksualna u mezczyzn'),(145,'wspomaga procesy naprawcze w stawach'),(184,'wspomaga regulacje gospodarki wapniowo-fosforanowej'),(104,'wspomaga terapie odchudzajaca'),(186,'wspomaga trawienie'),(132,'wspomaga trawienie tluszczow'),(136,'wspomaga zewnatrzwydzielnicza funkcje trzustki'),(205,'wspomagajaco przy  zaburzeniach zoladkowo-jelitowych'),(108,'wykrztusne'),(189,'wzmacnia naczynia krwionosne'),(168,'wzmacnia uklad kostny'),(61,'wzmacniajace odpornosc organizmu'),(27,'zapobiega powstawaniu kamieni zolciowych'),(190,'zapobieganie wsciekliznie'),(127,'zluszczajace'),(187,'zmienia smak oraz zapach dymu tytoniowego'),(179,'zmniejsza dolegliwosci zwiazane z przerostem gruczolu krokowego'),(30,'zmniejsza krzepliwosc krwi'),(59,'zmniejsza lagodny przerost ukladu chlonnego'),(34,'zmniejsza lepkosc sluzu'),(149,'zmniejsza napiecie miesni'),(134,'zmniejsza napiecie miesni szkieletowych'),(43,'zmniejsza napiecie nerwowe'),(64,'zmniejsza nasilenie zawrotow glowy i szumow usznych'),(33,'zmniejsza obrzek'),(21,'zmniejsza przepuszczalnosc naczyn krwionosnych'),(157,'zmniejsza rogowacenie'),(50,'zmniejsza stezenie wapnia we krwi'),(137,'zmniejsza wchlanianie glukozy w jelitach'),(124,'zmniejsza wytwarzanie glukozy w watrobie'),(18,'zolciotworcze'),(147,'zwieksza  wytrzymalosc fizyczna organizmu'),(49,'zwieksza elastycznosc naczyn'),(3,'zwieksza napiecie naczyn zylnych'),(204,'zwieksza naplyw krwi do pracia'),(203,'zwieksza sile i czestosc skurczow macicy'),(62,'zwieksza sile skurczu miesnia sercowego'),(1,'zwieksza stezenie  dobrego  cholesterolu (HDL)'),(16,'zwieksza stezenie testosteronu'),(135,'zwieksza wrazliwosc komorek na insuline'),(66,'zwieksza wrazliwosc tkanek na insuline');
/*!40000 ALTER TABLE `efekt` ENABLE KEYS */;
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
