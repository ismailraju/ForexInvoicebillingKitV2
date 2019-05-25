-- MySQL dump 10.16  Distrib 10.1.38-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: forexinvoice
-- ------------------------------------------------------
-- Server version	10.1.38-MariaDB

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
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `branch_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ifsc_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,'HDFC','hdfc_branch','IFSC-hdfc'),(2,'ICICI','icici_branch','IFSC-icici'),(3,'AXIS','axis_branch','IFSC-axis'),(4,'CITI','citi_branch','IFSC-citi'),(5,'KVB','KVB_branch','IFSC-KVB'),(6,'HSBC','HSBC_branch','IFSC-HSBC'),(7,'RBC','RBC_branch','IFSC-RBC'),(8,'SCOTIABANK','Scotiabank_branch','IFSC-Scotiabank');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `fax` varchar(255) COLLATE utf8_bin NOT NULL,
  `telephone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `web_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Saiesan','+1 4435345345','+1 5435345345','Steel Avenue,India','www.example.com','example@gmail.com');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'India'),(2,'Indonesia'),(3,'Pakistan'),(4,'Bangladesh'),(5,'Japan'),(6,'Philippines'),(7,'Vietnam'),(8,'Turkey'),(9,'Iran'),(10,'Thailand'),(11,'Myanmar'),(12,'South Korea'),(13,'Iraq'),(14,'Afghanistan'),(15,'Saudi Arabia'),(16,'Uzbekistan'),(17,'Malaysia'),(18,'Nepal'),(19,'Yemen'),(20,'North Korea'),(21,'Sri Lanka'),(22,'Kazakhstan'),(23,'Syria'),(24,'Cambodia'),(25,'Jordan'),(26,'Azerbaijan'),(27,'United Arab Emirates'),(28,'Tajikistan'),(29,'Israel'),(30,'Laos'),(31,'Kyrgyzstan'),(32,'Lebanon'),(33,'Turkmenistan'),(34,'Singapore'),(35,'State of Palestine'),(36,'Oman'),(37,'Kuwait'),(38,'Georgia'),(39,'Mongolia'),(40,'Armenia'),(41,'Qatar'),(42,'Bahrain'),(43,'Timor-Leste'),(44,'Cyprus'),(45,'Bhutan'),(46,'Maldives'),(47,'Brunei');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `short_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sign` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `country` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'Afghan afghani','AFN','€','Afghanistan'),(2,'Armenian dram','AMD','€','Armenia'),(3,'Azerbaijan manat','AZN','€','Azerbaijan'),(4,'Bahraini dinar','BHD','€','Bahrain'),(5,'Bangladeshi taka','BDT','€','Bangladesh'),(6,'Bhutanese ngultrum','BTN','€','Bhutan'),(7,'United States dollar','USD','€','British Indian Ocean Territory (UK)'),(8,'Brunei dollar','BND','€','Brunei'),(9,'Cambodian riel','KHR','€','Cambodia'),(10,'Chinese Yuan Renminbi','CNY','€','China'),(11,'Australian dollar','AUD','€','Christmas Island (Australia)'),(12,'Australian dollar','AUD','€','Cocos (Keeling) Islands (Australia)'),(13,'European euro','EUR','€','Cyprus'),(14,'Georgian lari','GEL','€','Georgia'),(15,'Hong Kong dollar','HKD','€','Hong Kong (China)'),(16,'Indian rupee','INR','€','India'),(17,'Indonesian rupiah','IDR','€','Indonesia'),(18,'Iranian rial','IRR','€','Iran'),(19,'Iraqi dinar','IQD','€','Iraq'),(20,'Israeli new shekel','ILS','€','Israel'),(21,'Japanese yen','JPY','€','Japan'),(22,'Jordanian dinar','JOD','€','Jordan'),(23,'Kazakhstani tenge','KZT','€','Kazakhstan'),(24,'Kuwaiti dinar','KWD','€','Kuwait'),(25,'Kyrgyzstani som','KGS','€','Kyrgyzstan'),(26,'Lao kip','LAK','€','Laos'),(27,'Lebanese pound','LBP','€','Lebanon'),(28,'Macanese pataca','MOP','€','Macau (China)'),(29,'Malaysian ringgit','MYR','€','Malaysia'),(30,'Maldivian rufiyaa','MVR','€','Maldives'),(31,'Mongolian tugrik','MNT','€','Mongolia'),(32,'Myanmar kyat','MMK','€','Myanmar (formerly Burma)'),(33,'Nepalese rupee','NPR','€','Nepal'),(34,'North Korean won','KPW','€','North Korea'),(35,'Omani rial','OMR','€','Oman'),(36,'Pakistani rupee','PKR','€','Pakistan'),(37,'Israeli new shekel','ILS','€','Palestine'),(38,'Philippine peso','PHP','€','Philippines'),(39,'Qatari riyal','QAR','€','Qatar'),(40,'Russian ruble','RUB','€','Russia'),(41,'Saudi Arabian riyal','SAR','€','Saudi Arabia'),(42,'Singapore dollar','SGD','€','Singapore'),(43,'South Korean won','KRW','€','South Korea'),(44,'Sri Lankan rupee','LKR','€','Sri Lanka'),(45,'Syrian pound','SYP','€','Syria'),(46,'New Taiwan dollar','TWD','€','Taiwan'),(47,'Tajikistani somoni','TJS','€','Tajikistan'),(48,'Thai baht','THB','€','Thailand'),(49,'United States dollar','USD','€','Timor-Leste'),(50,'Turkish lira','TRY','€','Turkey'),(51,'Turkmen manat','TMT','€','Turkmenistan'),(52,'UAE dirham','AED','€','United Arab Emirates'),(53,'Uzbekistani som','UZS','€','Uzbekistan'),(54,'Vietnamese dong','VND','€','Vietnam'),(55,'Yemeni rial','YER','€','Yemen');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ful_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `telephone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_expiry_date` date DEFAULT NULL,
  `issue_place` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `purpose` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `occupation` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Ismail Hossain Raju','2019-05-07','01674985665','India','CG-1000','2019-05-22','Ontorio','Family expence','Genaral worker'),(2,'raju11','2019-05-07','045436346','fffffffffffffffff','23432543rdgsdg','2019-05-09','dsgdsgsdg','fdhstureu','rureueru'),(3,'raju11','2019-05-07','045436346','fffffffffffffffff','23432543rdgsdg','2019-05-09','dsgdsgsdg','fdhstureu','rureueru'),(4,'raju11','2019-05-07','045436346','fffffffffffffffff','23432543rdgsdg','2019-05-09','dsgdsgsdg','fdhstureu','rureueru'),(5,'raju','2019-05-09','32523532','dsgsdhds','h3253tgsdh','2019-05-08','dshdfh','fdhfdhfd','hfhdfhfdh'),(6,'raju','2019-05-09','32523532','dsgsdhds','h3253tgsdh','2019-05-08','dshdfh','fdhfdhfd','hfhdfhfdh'),(7,'dffd','2019-05-01','fsds','sd','fds','2019-05-08','dsds','d','ds'),(8,'ewfew','2019-05-09','fwef','ewfe','wfwe','2019-05-14','fwefwe','fwe','f'),(9,'ewfew','2019-05-09','fwef','ewfe','wfwe','2019-05-14','fwefwe','fwe','f'),(10,'ewfew','2019-05-09','fwef','ewfe','wfwe','2019-05-14','fwefwe','fwe','f'),(11,'sfds','2019-05-01','dfd','fdfd','fd','2019-05-15','fdf','dfd','fdf'),(12,'sdfsd','2019-05-08','fsd','fds','f','2019-05-22','fsdf','fsdf','dsf'),(13,'dsfdsgdsg','2019-05-10','dgd','gds','ds','2019-05-16','dsgds','gdsg','dsgdsg');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipient`
--

DROP TABLE IF EXISTS `recipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ful_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `dob` date NOT NULL,
  `telephone` varchar(20) COLLATE utf8_bin NOT NULL,
  `Address` varchar(255) COLLATE utf8_bin NOT NULL,
  `id_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `received_method` varchar(255) COLLATE utf8_bin NOT NULL,
  `bank` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bank` (`bank`),
  CONSTRAINT `bank_fk` FOREIGN KEY (`bank`) REFERENCES `bank` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipient`
--

LOCK TABLES `recipient` WRITE;
/*!40000 ALTER TABLE `recipient` DISABLE KEYS */;
INSERT INTO `recipient` VALUES (1,'Reeta','2019-05-07','01674984545','India','00000000000000','Bank Receipt',1),(2,'uhsdhdsh','2019-05-22','435435345435','dgdshfd','325gdgdfh','fgfdhfdhfd',1),(3,'uhsdhdsh','2019-05-22','435435345435','dgdshfd','325gdgdfh','fgfdhfdhfd',1),(4,'uhsdhdsh','2019-05-22','435435345435','dgdshfd','325gdgdfh','fgfdhfdhfd',1),(5,'rani','2019-05-01','24232423','dhgdfhgfdh','3254gdgdsgdsg','cash',1),(6,'rani','2019-05-01','24232423','dhgdfhgfdh','3254gdgdsgdsg','cash',1),(7,'fdssd','2019-05-08','dds','f','sdd','s',2),(8,'few','2019-05-07','f','ewfwe','few','fwef',5),(9,'few','2019-05-07','f','ewfwe','few','fwef',5),(10,'few','2019-05-07','f','ewfwe','few','fwef',5),(11,'df','2019-05-08','dsf','dsf','dsfds','fdsf',2),(12,'dsf','2019-05-08','dfsd','fdsf','fdsf','fsd',5),(13,'dsgdsg','2019-05-07','gd','dg','ds','gdsg',2);
/*!40000 ALTER TABLE `recipient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `amount_send` decimal(10,2) NOT NULL,
  `send_currency` int(11) NOT NULL,
  `commission` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `country` int(11) NOT NULL,
  `exchange_rate` decimal(10,2) NOT NULL,
  `amount_receive` decimal(10,2) NOT NULL,
  `receive_currency` int(11) NOT NULL,
  `payment_method` varchar(255) COLLATE utf8_bin NOT NULL,
  `customer` int(11) NOT NULL,
  `recipient` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `send_currency_fk` (`send_currency`),
  KEY `receive_currency_fk` (`receive_currency`),
  KEY `country_fk` (`country`),
  KEY `customer` (`customer`),
  KEY `amount_receive` (`amount_receive`),
  KEY `recipient` (`recipient`),
  CONSTRAINT `Customer_fk` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `country_fk` FOREIGN KEY (`country`) REFERENCES `country` (`id`),
  CONSTRAINT `receive_currency_fk` FOREIGN KEY (`receive_currency`) REFERENCES `currency` (`id`),
  CONSTRAINT `recipient` FOREIGN KEY (`recipient`) REFERENCES `recipient` (`id`),
  CONSTRAINT `send_currency_fk` FOREIGN KEY (`send_currency`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'I-000001',250.00,5,10.00,260.00,21,135.00,33812.00,44,'Cash',1,1),(2,NULL,500.00,3,60.00,560.00,1,2.50,8000.00,2,'cash',2,2),(3,NULL,500.00,3,60.00,560.00,1,2.50,8000.00,2,'cash',3,3),(4,NULL,500.00,3,60.00,560.00,1,2.50,8000.00,2,'cash',4,4),(5,NULL,5000.00,2,20.00,5020.00,1,2.30,6000.00,2,'cash',5,5),(6,NULL,5000.00,2,20.00,5020.00,1,2.30,6000.00,2,'cash',6,6),(7,NULL,2.00,1,3.00,4.00,1,5.00,6.00,2,'asfsa',7,7),(8,NULL,757.00,2,7575.00,7575.00,1,757.00,57.00,2,'575',8,8),(9,NULL,757.00,2,7575.00,7575.00,1,757.00,57.00,2,'575',9,9),(10,NULL,757.00,2,7575.00,7575.00,1,757.00,57.00,2,'575',10,10),(11,NULL,54.00,2,54.00,45.00,3,544.00,54.00,4,'45',11,11),(12,NULL,4545.00,4,54545.00,454.00,2,2.00,21.00,2,'23jvg',12,12),(13,NULL,44.00,3,221.00,121.00,2,121.00,211.00,1,'22',13,13);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-26  1:19:32
