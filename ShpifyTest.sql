-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: shopify_test
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `province` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `zip` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (1,'123 King adward','ON','Canada','K1E 6T5','613-123-4567',1),(2,'34 Rue St-Dominiqu','QC','Canada','K2E 6K5','514-123-8569',2),(3,'99 Main ave.','ON','Canada','K6E 9T5','613-123-9568',3),(4,'190 Lees Ave','ON','Canada','K1S 5L5','819-318-2791',4),(7,'190 Lees Ave 212','ON','Canada','K1S 5L5','17600364180',10),(8,'81 James St','ON','Canada','K1L 5M2','17600364180',11),(9,'800 King Edward','ON','Canada','K1L 5M2','6132267789',12),(10,'800 King Edward','ON','CA','K1K N1N','6135627777',13),(11,'190 Lees Ave','ON','Canada','K1S 5L5','6135627777',14),(15,'200 LEES AVE','ON','Canada','K1S 5L6','8193182792',20);
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOK`
--

DROP TABLE IF EXISTS `BOOK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BOOK` (
  `bookid` varchar(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `price` int(10) unsigned NOT NULL,
  `author` varchar(100) NOT NULL,
  `categoryid` int(11) NOT NULL,
  `imgURL` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `publisher_year` int(11) DEFAULT NULL,
  `inventory` int(11) NOT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOK`
--

LOCK TABLES `BOOK` WRITE;
/*!40000 ALTER TABLE `BOOK` DISABLE KEYS */;
INSERT INTO `BOOK` VALUES ('1118008189','HTML AND CSS: DESIGN AND BUILD WEBSITES',1599,'Jon Duckett',1,'../images/bk3.png','BBA',2013,5),('1187189032','The Haunted Mansion (Widescreen) (Bilingual)',1221,'Micheal Lee',3,'../images/bk9.png','CVB',2012,5),('1323508820','CLEAN CODE: A HANDBOOK OF AGILE SOFTWARE',1599,'Robert C. Martin',1,'../images/bk1.png','AAB',2011,5),('1407869656','MONSTER MANUAL',2000,'Wizards Rpg Team',2,'../images/bk2.png','ABB',2012,5),('1476795924','LEADERSHIP: IN TURBULENT TIMES',2000,'Doris Kearns Goodwin',3,'../images/bk4.png','ABC',2014,5),('1501175513','FEAR: TRUMP IN THE WHITE HOUSE',2000,'Bob Woodward',4,'../images/bk5.png','VAV',2015,5),('1627794247','ROBIN: INTRODUCTION OF LITTLE GENIUS',1599,'Dave Itzkoff',4,'../images/bk6.png','ABCC',2016,5),('1627794258','BookTest: INTRODUCTION OF HELLO WORLD',1000,'WEST J',1,'../images/bk7.png','BCAA',2017,5),('1627794334','TEST: BOOK OF LEARNING PYTHON WORLD',1300,'KARBI JACK',2,'../images/bk8.png','ABC',2010,5);
/*!40000 ALTER TABLE `BOOK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES (1,'Computers'),(2,'Entertainment'),(3,'HIstory'),(4,'Biography and Memoir');
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Counts`
--

DROP TABLE IF EXISTS `Counts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Counts` (
  `id` int(11) NOT NULL,
  `counts` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Counts`
--

LOCK TABLES `Counts` WRITE;
/*!40000 ALTER TABLE `Counts` DISABLE KEYS */;
INSERT INTO `Counts` VALUES (1,22);
/*!40000 ALTER TABLE `Counts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderBook`
--

DROP TABLE IF EXISTS `OrderBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `OrderBook` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `bookid` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderBook`
--

LOCK TABLES `OrderBook` WRITE;
/*!40000 ALTER TABLE `OrderBook` DISABLE KEYS */;
INSERT INTO `OrderBook` VALUES (7,2,'1501175513',3),(8,2,'1323508820',2),(9,3,'1407869656',1),(10,3,'1407869656',1),(11,3,'1323508820',2),(12,3,'1323508820',3),(13,5,'1501175513',2),(14,6,'1476795924',10),(15,6,'1407869656',5),(16,6,'1323508820',5),(17,7,'1323508820',2),(18,8,'1323508820',2),(19,10,'1323508820',1),(20,11,'1323508820',10),(21,12,'1407869656',5),(22,13,'1501175513',4),(23,14,'1627794258',3),(30,19,'1407869656',1),(31,20,'1501175513',1),(32,21,'1627794334',2),(33,21,'1627794258',8),(34,22,'1407869656',1),(35,23,'1118008189',1),(36,24,'1501175513',1),(37,24,'1476795924',2),(38,25,'1407869656',1),(39,0,'1627794247',8),(40,0,'1501175513',2),(41,27,'1118008189',1),(42,0,'1323508820',2),(43,29,'1501175513',2),(44,0,'1627794247',2),(45,0,'1627794258',3),(46,0,'1627794334',1),(47,33,'1627794334',1),(48,34,'1323508820',3),(49,35,'1118008189',5),(50,35,'1187189032',3);
/*!40000 ALTER TABLE `OrderBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `generationtime` datetime NOT NULL,
  `totalprice` float NOT NULL,
  `addressid` int(11) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `shipping` float NOT NULL,
  `tax` float NOT NULL,
  `aftertaxprice` float NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (2,1,'2018-10-24 00:09:44',9198,1,'Failed',5,1195.74,10398.7,5),(3,4,'2018-10-25 15:30:27',11995,4,'Processing',5,1559.35,13559.3,7),(4,1,'2018-10-25 15:35:36',0,1,'Processing',5,0,5,0),(5,4,'2018-10-25 15:43:20',4000,4,'Processing',5,520,4525,2),(6,4,'2018-10-25 16:46:08',37995,4,'Processing',5,4939.35,42939.4,20),(7,4,'2018-10-25 19:59:06',3198,4,'Processing',5,415.74,3618.74,2),(8,4,'2018-10-25 19:59:07',3198,4,'Processing',5,415.74,3618.74,2),(9,4,'2018-10-25 19:59:09',0,4,'Processing',5,0,5,0),(10,4,'2018-10-25 20:08:04',1599,4,'Success',5,207.87,1811.87,1),(11,1,'2018-10-25 20:11:54',15990,1,'Success',5,2078.7,18073.7,10),(12,1,'2018-10-25 20:12:38',10000,1,'Success',5,1300,11305,5),(13,1,'2018-10-25 20:13:08',8000,1,'Success',5,1040,9045,4),(14,1,'2018-10-25 20:14:03',3,1,'Failed',5,0.39,8.39,3),(15,1,'2018-10-28 20:57:37',4041,1,'Processing',5,525.33,4571.33,3),(19,4,'2018-10-28 21:21:08',2000,4,'Failed',5,260,2265,1),(20,4,'2018-10-28 21:23:10',2000,4,'Success',5,260,2265,1),(21,2,'2018-10-29 00:14:33',10600,2,'Success',8,848,11456,10),(22,1,'2018-12-04 01:08:35',2000,1,'Processing',5,260,2265,1),(23,1,'2018-12-04 01:22:37',1599,1,'Processing',5,207.87,1811.87,1),(24,1,'2018-12-04 01:23:23',6000,1,'Success',5,780,6785,3),(25,1,'2018-12-31 04:27:13',2000,1,'Success',5,260,2265,1),(26,20,'2018-12-31 20:53:55',16792,15,'Processing',5,2182.96,18980,10),(27,20,'2018-12-31 21:01:37',1599,15,'Processing',5,207.87,1811.87,1),(28,20,'2018-12-31 21:03:15',3198,15,'Processing',5,415.74,3618.74,2),(29,1,'2018-12-31 21:10:21',4000,1,'Processing',5,520,4525,2),(30,1,'2018-12-31 21:13:29',3198,1,'Processing',5,415.74,3618.74,2),(31,1,'2018-12-31 21:16:15',3000,1,'Processing',5,390,3395,3),(32,1,'2018-12-31 21:18:45',1300,1,'Processing',5,169,1474,1),(33,1,'2018-12-31 21:23:24',1300,1,'Processing',5,169,1474,1),(34,1,'2018-12-31 21:34:56',4797,1,'Failed',5,623.61,5425.61,3),(35,1,'2019-01-15 22:23:57',11658,1,'Success',5,1515.54,13178.5,8);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShoppingCart`
--

DROP TABLE IF EXISTS `ShoppingCart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ShoppingCart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `bookid` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid_idx` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShoppingCart`
--

LOCK TABLES `ShoppingCart` WRITE;
/*!40000 ALTER TABLE `ShoppingCart` DISABLE KEYS */;
/*!40000 ALTER TABLE `ShoppingCart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'test@test.com','123456','Ftest','Ltest'),(2,'test2@test.com','123456','Ftest2','Ltest2'),(3,'test3@test.com','654321','Ftest3','Ltest3'),(4,'weiw4180@gmail.com','123456','Wei','Wei'),(10,'weiw4180@163.com','123123','Wei','Wei'),(11,'weiw4180@hotmail.com','123000','Alex','Wei'),(12,'a597217837@hotmail.com','123000','Nancy','Chen'),(13,'haha@hoho.com','123456','haha','hoho'),(14,'123@123456.com','123456','123','123'),(20,'APItest@test.com','apiapi','API_first','API_last'),(21,'NewAPItest@test.com','apiapi','API_first','API_last');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-15 19:41:05
