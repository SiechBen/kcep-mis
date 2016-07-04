-- MySQL dump 10.13  Distrib 5.7.12, for Linux (x86_64)
--
-- Host: localhost    Database: kcep_mis
-- ------------------------------------------------------
-- Server version	5.7.12-0ubuntu1.1

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account_number` varchar(45) DEFAULT NULL,
  `ebl_branch` varchar(45) DEFAULT NULL,
  `sol_id` varchar(45) DEFAULT NULL,
  `farmer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_account_details_person1` (`farmer`),
  CONSTRAINT `fk_account_details_person1` FOREIGN KEY (`farmer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `age_bracket`
--

DROP TABLE IF EXISTS `age_bracket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `age_bracket` (
  `id` smallint(5) unsigned NOT NULL,
  `bracket` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `bracket_UNIQUE` (`bracket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `age_bracket`
--

LOCK TABLES `age_bracket` WRITE;
/*!40000 ALTER TABLE `age_bracket` DISABLE KEYS */;
/*!40000 ALTER TABLE `age_bracket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `component` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `component` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `component_UNIQUE` (`component`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1,'Cereal Productivity Enhancement'),(3,'Financial Services'),(2,'Post-Harvest Management and Market Linkages');
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `postal_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'0701404084','siele.bernard@gmail.com','203 Bomet'),(3,'0701404084','agrodealer@gmail.com','203 Bomet'),(4,'0701404086','countyofficer@gmail.com','203 Bomet'),(5,'0701404087','equity@gmail.com','203 Bomet'),(6,'0701404088','farmer@gmail.com','203 Bomet'),(7,'0701404089','kalroofficer@gmail.com','203 Bomet'),(8,'0701404081','nationalofficer@gmail.com','202 Bomet'),(9,'0701404082','regionalcoordinator@gmail.com','203 Bomet'),(10,'0701404080','subcounty@gmail.com','203 Bomet'),(11,'0701404041','warehouseoperator@gmail.com','203 Bomet'),(12,'0701404086','wao@gmail.com','203 Bomet'),(13,'0725287162','ss@anynomous.ac.ke','203 Muminji'),(14,'0727080918','henrykchirchir@gmail.com',''),(15,'0728254281','enockkkorir@gmail.com',''),(16,'0726365000','leahjterer@gmail.com',''),(17,'0724333448','koilelstores@gmail.com',''),(18,'0712444630','bentallam@gmail.com',''),(19,'0723144351','marthakiay@gmail.com',''),(20,'0721571079','willyrop@gmail.com',''),(21,'0720402148','henryrop@gmail.com',''),(22,'0702954085','judycherop@gmail.com',''),(23,'0720792007','janekiprotich@gmail.com',''),(24,'0721943674','dorcaschepkwony@gmail.com',''),(25,'0726253218','dinahcherotich@gmail.com',''),(26,'0721893081','johnkemboi@gmail.com',''),(27,'0720837777','petermutai@gmail.com',''),(28,'0717153900','christophersawe@gmail.com',''),(29,'0718801292','francismuniu15@gmail.com',NULL),(30,'0724968759','cherotichgladys55@gmail.com',NULL),(31,'0728386627','eliudenzianosenelua85@gmail.com',NULL),(32,'0723377384','josephngaah06@gmail.com',NULL),(33,'0721779275','timothysachita26@gmail.com',NULL),(34,'0721616191','christineshiamala66@gmail.com',NULL),(35,'0721757470','juliusmaloba07@gmail.com',NULL),(36,'0723250815','samsonisieko27@gmail.com',NULL),(37,'0706814194','peterwaswawakhisi47@gmail.com',NULL),(38,'07228 8905','benardbulimo67@gmail.com',NULL),(39,'0711174756','bensonlusabilukonzo97@gmail.com',NULL),(40,'0723514029','edgarlubanga28@gmail.com',NULL),(41,'0722463817','musanamai38@gmail.com',NULL),(42,'0707449630','bernardoondeng58@gmail.com',NULL),(43,'0722403758','hellenkabiru68@gmail.com',NULL),(44,'0707675152','piusnamungu98@gmail.com',NULL),(45,'0721123422','anjelinebwalei19@gmail.com',NULL),(46,'0722146780','anthonywesonga29@gmail.com',NULL),(47,'0721381754','teresakabiru49@gmail.com',NULL),(48,'0717627680','petersonobusuru69@gmail.com',NULL),(49,'0715824348','rebeccakemei99@gmail.com',NULL),(50,'0705322261','erickisiangani10@gmail.com',NULL),(51,'0721717502','josephkisingu20@gmail.com',NULL),(52,'0721359760','sichanginandeyeka40@gmail.com',NULL),(53,'0721903751','alicelumwaji60@gmail.com',NULL),(54,'0722217293','beatricekhavele90@gmail.com',NULL),(55,'0726570070','essendisimon11@gmail.com',NULL),(56,'0725348523','patrickmunialo41@gmail.com',NULL),(57,'0726291499','judithalegwa51@gmail.com',NULL),(58,'0721295425','philipkirwasang71@gmail.com',NULL),(59,'0725365246','joshuakiplimolaboso91@gmail.com',NULL),(60,'0722605381','williamk.kosgei12@gmail.com',NULL),(61,'0720439311','juliuskimuagorsailel22@gmail.com',NULL),(62,'0720430070','wilsonkiboitchemwok42@gmail.com',NULL),(63,'0722493123','petermuttai62@gmail.com',NULL),(64,'0722922168','peterkutiitumo82@gmail.com',NULL),(65,'0722967750','bismillahiadanbatelo03@gmail.com',NULL),(66,'0720469481','michaelkiplagatoleoikop23@gmail.com',NULL),(67,'0723501674','emilyjeronobarngetung43@gmail.com',NULL),(68,'0724610822','nathanksang53@gmail.com',NULL),(69,'0722665531','tamboche73@gmail.com',NULL),(70,'0728332251','simonchepsiror24@gmail.com',NULL),(71,'0717125343','calebk.lagat54@gmail.com',NULL),(72,'07226655311','tanykinaholdingsltd74@gmail.com',NULL),(73,'0721279682','sharonn.kasili05@gmail.com',NULL),(74,'0726582856','fredmabonga25@gmail.com',NULL),(75,'0720746248','wakwetu45@gmail.com',NULL),(76,'0729437702','lusiawasukamaina55@gmail.com',NULL),(77,'0712950741','kennethnamwok75@gmail.com',NULL),(78,'07129507411','francisokechotieno95@gmail.com',NULL),(79,'07002105011','eliakimw.simiyu06@gmail.com',NULL),(80,'0717678756','kiraiagrovet26@gmail.com',NULL),(81,'0714134113','mauricej.manyasi36@gmail.com',NULL),(82,'0710872605','elizabethwekesa46@gmail.com',NULL),(83,'0712516488','walayostores76@gmail.com',NULL),(84,'0711376374','mosessimiyu96@gmail.com',NULL),(85,'0727847991','jamesotieno17@gmail.com',NULL),(86,'0703236392','marynekesabarasa37@gmail.com',NULL),(87,'0711699029','joselynemakhanu47@gmail.com',NULL),(88,'0711326915','edwardwekesa77@gmail.com',NULL),(89,'0715665941','peternajoli08@gmail.com',NULL),(90,'0716440120','rosemanyonge18@gmail.com',NULL),(91,'0722936681','martinwetungu38@gmail.com',NULL),(92,'0721316405','jafredwanyonyi48@gmail.com',NULL),(93,'0727245870','victormasibo58@gmail.com',NULL),(94,'0720391446','mildredmagalomba78@gmail.com',NULL),(95,'0723684715','elizabethmusungu98@gmail.com',NULL),(96,'0724390434','peterm.barasa09@gmail.com',NULL),(97,'0720581492','jameswanyonyi39@gmail.com',NULL),(98,'0725298249','leonardwalubengo59@gmail.com',NULL),(99,'0714443999','moseskiptanui79@gmail.com',NULL),(100,'0722909070','dorcasgoren99@gmail.com',NULL),(101,'0701894699','simonndegwamwangi10@gmail.com',NULL),(102,'070022888','thomask.talaam50@gmail.com',NULL),(103,'0721744521','josephrotich70@gmail.com',NULL),(104,'0722619790','jonathancheruiyot90@gmail.com',NULL),(105,'0713359926','shemcharan11@gmail.com',NULL),(106,'0722745304','shadrackterer21@gmail.com',NULL),(107,'0721571085','kenfap41@gmail.com',NULL),(108,'0729087368','philemonk.meli71@gmail.com',NULL),(109,'07290873688','stanleyolesianga02@gmail.com',NULL),(110,'0721543442','paulmaina42@gmail.com',NULL),(111,'0724983689','milkawangari72@gmail.com',NULL),(112,'0725241834','kariukiesther23@gmail.com',NULL),(113,'0735700166','charlesisinga63@gmail.com',NULL),(114,'0722911042','josephmaina24@gmail.com',NULL),(115,'072276902','johnngariya64@gmail.com',NULL),(116,'0723454614','francisletting94@gmail.com',NULL),(117,'0726223479','erastusopicho05@gmail.com',NULL),(118,'0728266128','aggreywaliaula25@gmail.com',NULL),(119,'0727430101','benardmasanja45@gmail.com',NULL),(120,'0711556823','kenaffreubenkirwa65@gmail.com',NULL),(121,'0724407298','petermwangi95@gmail.com',NULL),(122,'0721863498','josephnzomo06@gmail.com',NULL),(123,'0721744540','josephndungu26@gmail.com',NULL),(124,'0710417801','shemkakaimatolo36@gmail.com',NULL),(125,'0720718621','peterkimathimbae46@gmail.com',NULL),(126,'0715212004','peterkibe66@gmail.com',NULL),(127,'0727782893','jacksonkibetrutto86@gmail.com',NULL),(128,'0712974661','mosessimiyumunialo07@gmail.com',NULL),(129,'0727675481','philemonkipchumba27@gmail.com',NULL),(130,'0729578276','stanleykipkorirrotich47@gmail.com',NULL),(131,'0724401110','elizabethengendothuo67@gmail.com',NULL),(132,'0723549067','stephenkirungu97@gmail.com',NULL),(133,'0724398606','seraphirejullyoulepu08@gmail.com',NULL),(134,'0729760232','josephmurathi28@gmail.com',NULL),(135,'0722256973','charleskamauchege48@gmail.com',NULL),(136,'0727022397','gerishonkamau68@gmail.com',NULL),(137,'0729070016','annwambuikimani98@gmail.com',NULL),(138,'0722319008','franciskahiu09@gmail.com',NULL),(139,'0723114255','desmondkiplangatchumo29@gmail.com',NULL),(140,'0700210501','charlesbiwott39@gmail.com',NULL),(141,'0722360185','geoffreyirangi59@gmail.com',NULL),(142,'0728158374','g.kngaruiya79@gmail.com',NULL),(143,'0721525849','marywanjohi99@gmail.com',NULL),(144,'0722692425','marykahingo10@gmail.com',NULL),(145,'0727548284','marykahingo40@gmail.com',NULL),(146,'0701047219','janemuthonimburu60@gmail.com',NULL),(147,'072887091','patrickmugo80@gmail.com',NULL),(148,'0722251390','immanuelsalaonslempaa01@gmail.com',NULL),(149,'0722950921','gladwellwaithera21@gmail.com',NULL),(150,'0722748253','lempaaraphaelk.31@gmail.com',NULL),(151,'0721371809','zacheosmonani41@gmail.com',NULL),(152,'0711576445','marykemunto61@gmail.com',NULL),(153,'0727713457','annwanjiruwakaba91@gmail.com',NULL),(154,'0726741739','stephenkiptoolelei12@gmail.com',NULL),(155,'0725165760','bensonkariuki32@gmail.com',NULL),(156,'0724344736','johnn.mwangi52@gmail.com',NULL);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `county`
--

DROP TABLE IF EXISTS `county`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `county` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `region` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_county_region2` (`region`),
  CONSTRAINT `fk_county_region2` FOREIGN KEY (`region`) REFERENCES `region` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `county`
--

LOCK TABLES `county` WRITE;
/*!40000 ALTER TABLE `county` DISABLE KEYS */;
INSERT INTO `county` VALUES (1,'Bungoma',2),(2,'Embu',1),(3,'Kakamega',2),(4,'Kitui',1),(5,'Nakuru',2),(6,'Nandi',2),(7,'Tharaka Nithi',1),(8,'Transzoia',2);
/*!40000 ALTER TABLE `county` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation_in_group`
--

DROP TABLE IF EXISTS `designation_in_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation_in_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `designation` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `designation_UNIQUE` (`designation`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation_in_group`
--

LOCK TABLES `designation_in_group` WRITE;
/*!40000 ALTER TABLE `designation_in_group` DISABLE KEYS */;
INSERT INTO `designation_in_group` VALUES (1,'Chair'),(4,'Normal Member'),(2,'Secretary'),(3,'Treasurer');
/*!40000 ALTER TABLE `designation_in_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dissemination_of_results`
--

DROP TABLE IF EXISTS `dissemination_of_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dissemination_of_results` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_of_result_set_in_western_region` int(11) DEFAULT NULL,
  `no_of_result_set_in_eastern_region` int(11) DEFAULT NULL,
  `kalro_officer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_dissemination_of_results_person1` (`kalro_officer`),
  CONSTRAINT `fk_dissemination_of_results_person1` FOREIGN KEY (`kalro_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dissemination_of_results`
--

LOCK TABLES `dissemination_of_results` WRITE;
/*!40000 ALTER TABLE `dissemination_of_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `dissemination_of_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_voucher`
--

DROP TABLE IF EXISTS `e_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `e_voucher` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `amount` varchar(45) DEFAULT NULL,
  `input_type` smallint(5) unsigned DEFAULT NULL,
  `person` int(10) unsigned DEFAULT NULL,
  `date_redeemed` date DEFAULT NULL,
  `inputs_logbook_page` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_input_input_type1` (`input_type`),
  KEY `fk_input_person1` (`person`),
  CONSTRAINT `fk_input_input_type1` FOREIGN KEY (`input_type`) REFERENCES `input_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_input_person1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_voucher`
--

LOCK TABLES `e_voucher` WRITE;
/*!40000 ALTER TABLE `e_voucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `e_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `total_count` int(11) DEFAULT NULL COMMENT 'The total number of equipment of a type',
  `warehouse` int(10) unsigned NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_equipment_warehouse1` (`warehouse`),
  CONSTRAINT `fk_equipment_warehouse1` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extension_and_field_visit_data`
--

DROP TABLE IF EXISTS `extension_and_field_visit_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extension_and_field_visit_data` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number_of_people_seeking_or_offered_advisory_services` int(11) DEFAULT NULL,
  `nature_of_advisory_services` varchar(400) DEFAULT NULL,
  `number_of_field_visits_conducted` int(11) DEFAULT NULL,
  `number_of_farmers_visited` int(11) DEFAULT NULL,
  `field_visits_ward_locations` varchar(400) DEFAULT NULL,
  `ward_extension_officer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_extension_and_field_visit_data_person1` (`ward_extension_officer`),
  CONSTRAINT `fk_extension_and_field_visit_data_person1` FOREIGN KEY (`ward_extension_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extension_and_field_visit_data`
--

LOCK TABLES `extension_and_field_visit_data` WRITE;
/*!40000 ALTER TABLE `extension_and_field_visit_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `extension_and_field_visit_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extension_material_and_guideline`
--

DROP TABLE IF EXISTS `extension_material_and_guideline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extension_material_and_guideline` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `kalro_officer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_extension_material_and_guideline_person1` (`kalro_officer`),
  CONSTRAINT `fk_extension_material_and_guideline_person1` FOREIGN KEY (`kalro_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extension_material_and_guideline`
--

LOCK TABLES `extension_material_and_guideline` WRITE;
/*!40000 ALTER TABLE `extension_material_and_guideline` DISABLE KEYS */;
/*!40000 ALTER TABLE `extension_material_and_guideline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farm`
--

DROP TABLE IF EXISTS `farm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farm` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `location` int(10) unsigned NOT NULL,
  `plot_size` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_farm_location1` (`location`),
  CONSTRAINT `fk_farm_location1` FOREIGN KEY (`location`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farm`
--

LOCK TABLES `farm` WRITE;
/*!40000 ALTER TABLE `farm` DISABLE KEYS */;
/*!40000 ALTER TABLE `farm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farm_activity`
--

DROP TABLE IF EXISTS `farm_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farm_activity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `farm` int(10) unsigned NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `yield` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `quantity_sold` double DEFAULT NULL,
  `quantity_harvested` double DEFAULT NULL,
  `average_selling_price_per` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_farm_activity_farm1` (`farm`),
  CONSTRAINT `fk_farm_activity_farm1` FOREIGN KEY (`farm`) REFERENCES `farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farm_activity`
--

LOCK TABLES `farm_activity` WRITE;
/*!40000 ALTER TABLE `farm_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `farm_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmer_group`
--

DROP TABLE IF EXISTS `farmer_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farmer_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmer_group`
--

LOCK TABLES `farmer_group` WRITE;
/*!40000 ALTER TABLE `farmer_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `farmer_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmer_group_record`
--

DROP TABLE IF EXISTS `farmer_group_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farmer_group_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `farmer_group` int(10) unsigned NOT NULL,
  `date_of_inputs_collection` date DEFAULT NULL,
  `first_weeding_done` tinyint(1) DEFAULT NULL,
  `second_weeding_done` tinyint(1) DEFAULT NULL,
  `quantity_harvested_from_kcep_farm` int(11) DEFAULT NULL COMMENT '90 kg bags',
  `quantity_sold_from_kcep_farm` int(11) DEFAULT NULL,
  `average_selling_price_per_bag` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_farmer_group_record_farmer_group1` (`farmer_group`),
  CONSTRAINT `fk_farmer_group_record_farmer_group1` FOREIGN KEY (`farmer_group`) REFERENCES `farmer_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmer_group_record`
--

LOCK TABLES `farmer_group_record` WRITE;
/*!40000 ALTER TABLE `farmer_group_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `farmer_group_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `farmer_sub_group`
--

DROP TABLE IF EXISTS `farmer_sub_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farmer_sub_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `farmer_group` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_farmer_sub_group_farmer_group1` (`farmer_group`),
  CONSTRAINT `fk_farmer_sub_group_farmer_group1` FOREIGN KEY (`farmer_group`) REFERENCES `farmer_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmer_sub_group`
--

LOCK TABLES `farmer_sub_group` WRITE;
/*!40000 ALTER TABLE `farmer_sub_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `farmer_sub_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `time_posted` datetime NOT NULL,
  `farmer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_feedback_person1` (`farmer`),
  CONSTRAINT `fk_feedback_person1` FOREIGN KEY (`farmer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ifad_prior_review`
--

DROP TABLE IF EXISTS `ifad_prior_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ifad_prior_review` (
  `id` smallint(6) NOT NULL,
  `choice` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ifad_prior_review`
--

LOCK TABLES `ifad_prior_review` WRITE;
/*!40000 ALTER TABLE `ifad_prior_review` DISABLE KEYS */;
INSERT INTO `ifad_prior_review` VALUES (1,'Yes'),(2,'No');
/*!40000 ALTER TABLE `ifad_prior_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `implementing_partner`
--

DROP TABLE IF EXISTS `implementing_partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `implementing_partner` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `person_role` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_table1_person_role1` (`person_role`),
  CONSTRAINT `fk_table1_person_role1` FOREIGN KEY (`person_role`) REFERENCES `person_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `implementing_partner`
--

LOCK TABLES `implementing_partner` WRITE;
/*!40000 ALTER TABLE `implementing_partner` DISABLE KEYS */;
INSERT INTO `implementing_partner` VALUES (1,8),(2,10),(3,12);
/*!40000 ALTER TABLE `implementing_partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `input_type`
--

DROP TABLE IF EXISTS `input_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `input_type` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `static_input` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_input_type_static_input1` (`static_input`),
  CONSTRAINT `fk_input_type_static_input1` FOREIGN KEY (`static_input`) REFERENCES `static_input` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `input_type`
--

LOCK TABLES `input_type` WRITE;
/*!40000 ALTER TABLE `input_type` DISABLE KEYS */;
INSERT INTO `input_type` VALUES (1,'Seeds',NULL),(2,'Fertiliser',NULL),(3,'Tarpaulin',NULL),(4,'Hermetic bags',NULL);
/*!40000 ALTER TABLE `input_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inputs_collection`
--

DROP TABLE IF EXISTS `inputs_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inputs_collection` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `agro_dealer` int(10) unsigned NOT NULL,
  `input_type` smallint(5) unsigned NOT NULL,
  `quantity` varchar(45) DEFAULT NULL,
  `farmer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_inputs_collection_person1` (`agro_dealer`),
  KEY `fk_inputs_collection_input_type1` (`input_type`),
  KEY `fk_inputs_collection_person2` (`farmer`),
  CONSTRAINT `fk_inputs_collection_input_type1` FOREIGN KEY (`input_type`) REFERENCES `input_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_person1` FOREIGN KEY (`agro_dealer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_person2` FOREIGN KEY (`farmer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inputs_collection`
--

LOCK TABLES `inputs_collection` WRITE;
/*!40000 ALTER TABLE `inputs_collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `inputs_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,0) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `person` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_loan_person1` (`person`),
  CONSTRAINT `fk_loan_person1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `county` smallint(5) unsigned DEFAULT NULL,
  `sub_county` int(10) unsigned DEFAULT NULL,
  `ward` int(10) unsigned DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(9,7) DEFAULT NULL,
  `village` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_location_county1` (`county`),
  KEY `fk_location_sub_county1` (`sub_county`),
  KEY `fk_location_ward1` (`ward`),
  KEY `fk_location_village1` (`village`),
  CONSTRAINT `fk_location_county1` FOREIGN KEY (`county`) REFERENCES `county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_sub_county1` FOREIGN KEY (`sub_county`) REFERENCES `sub_county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_village1` FOREIGN KEY (`village`) REFERENCES `village` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_ward1` FOREIGN KEY (`ward`) REFERENCES `ward` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,5,8,7,35.8035000,0.2983000,NULL),(3,1,1,1,NULL,NULL,NULL),(4,1,15,19,NULL,NULL,NULL),(5,1,1,1,NULL,NULL,NULL),(6,1,1,6,NULL,NULL,NULL),(7,1,1,7,NULL,NULL,NULL),(8,1,1,7,NULL,NULL,NULL),(9,1,9,1,NULL,NULL,NULL),(10,1,4,5,NULL,NULL,NULL),(11,6,7,6,NULL,NULL,NULL),(12,3,3,6,NULL,NULL,NULL),(13,3,3,3,NULL,NULL,NULL),(14,1,1,48,NULL,NULL,NULL),(15,1,1,NULL,NULL,NULL,NULL),(16,1,1,NULL,NULL,NULL,NULL),(17,1,9,11,NULL,NULL,NULL),(18,1,9,11,NULL,NULL,NULL),(19,1,9,11,NULL,NULL,NULL),(20,1,9,11,NULL,NULL,NULL),(21,1,9,12,NULL,NULL,NULL),(22,1,9,12,NULL,NULL,NULL),(23,1,9,14,NULL,NULL,NULL),(24,1,9,14,NULL,NULL,NULL),(25,1,9,15,NULL,NULL,NULL),(26,1,9,15,NULL,NULL,NULL),(27,1,9,13,NULL,NULL,NULL),(28,1,9,13,NULL,NULL,NULL),(29,1,9,13,NULL,NULL,NULL),(30,1,9,13,NULL,NULL,NULL),(31,1,9,13,NULL,NULL,NULL),(32,6,9,12,NULL,NULL,NULL),(33,3,12,30,NULL,NULL,NULL),(34,3,12,30,NULL,NULL,NULL),(35,3,12,28,NULL,NULL,NULL),(36,3,12,28,NULL,NULL,NULL),(37,3,12,27,NULL,NULL,NULL),(38,3,12,27,NULL,NULL,NULL),(39,3,12,27,NULL,NULL,NULL),(40,3,12,27,NULL,NULL,NULL),(41,3,12,28,NULL,NULL,NULL),(42,3,12,30,NULL,NULL,NULL),(43,3,12,28,NULL,NULL,NULL),(44,3,12,30,NULL,NULL,NULL),(45,3,12,27,NULL,NULL,NULL),(46,3,11,22,NULL,NULL,NULL),(47,3,11,26,NULL,NULL,NULL),(48,3,11,26,NULL,NULL,NULL),(49,3,11,26,NULL,NULL,NULL),(50,3,11,24,NULL,NULL,NULL),(51,3,11,23,NULL,NULL,NULL),(52,3,11,26,NULL,NULL,NULL),(53,3,11,22,NULL,NULL,NULL),(54,3,11,26,NULL,NULL,NULL),(55,3,11,25,NULL,NULL,NULL),(56,3,11,24,NULL,NULL,NULL),(57,3,11,25,NULL,NULL,NULL),(58,3,11,24,NULL,NULL,NULL),(59,3,11,22,NULL,NULL,NULL),(60,3,11,23,NULL,NULL,NULL),(61,6,10,16,NULL,NULL,NULL),(62,6,10,16,NULL,NULL,NULL),(63,6,10,16,NULL,NULL,NULL),(64,6,10,16,NULL,NULL,NULL),(65,6,10,17,NULL,NULL,NULL),(66,6,10,17,NULL,NULL,NULL),(67,6,10,17,NULL,NULL,NULL),(68,6,10,19,NULL,NULL,NULL),(69,6,10,19,NULL,NULL,NULL),(70,6,10,19,NULL,NULL,NULL),(71,6,10,19,NULL,NULL,NULL),(72,6,10,18,NULL,NULL,NULL),(73,6,10,18,NULL,NULL,NULL),(74,6,10,18,NULL,NULL,NULL),(75,6,10,71,NULL,NULL,NULL),(76,1,15,38,NULL,NULL,NULL),(77,1,15,39,NULL,NULL,NULL),(78,1,15,38,NULL,NULL,NULL),(79,1,15,39,NULL,NULL,NULL),(80,1,15,38,NULL,NULL,NULL),(81,1,15,38,NULL,NULL,NULL),(82,1,15,38,NULL,NULL,NULL),(83,1,15,40,NULL,NULL,NULL),(84,1,15,39,NULL,NULL,NULL),(85,1,15,38,NULL,NULL,NULL),(86,1,15,38,NULL,NULL,NULL),(87,1,16,41,NULL,NULL,NULL),(88,1,16,41,NULL,NULL,NULL),(89,1,16,41,NULL,NULL,NULL),(90,1,16,42,NULL,NULL,NULL),(91,1,16,42,NULL,NULL,NULL),(92,1,16,42,NULL,NULL,NULL),(93,1,16,42,NULL,NULL,NULL),(94,1,16,41,NULL,NULL,NULL),(95,1,16,44,NULL,NULL,NULL),(96,1,16,43,NULL,NULL,NULL),(97,1,16,44,NULL,NULL,NULL),(98,1,16,44,NULL,NULL,NULL),(99,1,16,45,NULL,NULL,NULL),(100,1,16,46,NULL,NULL,NULL),(101,1,16,46,NULL,NULL,NULL),(102,8,14,36,NULL,NULL,NULL),(103,8,14,36,NULL,NULL,NULL),(104,8,14,36,NULL,NULL,NULL),(105,8,14,76,NULL,NULL,NULL),(106,8,14,76,NULL,NULL,NULL),(107,8,14,37,NULL,NULL,NULL),(108,8,14,37,NULL,NULL,NULL),(109,8,14,37,NULL,NULL,NULL),(110,8,14,78,NULL,NULL,NULL),(111,8,14,78,NULL,NULL,NULL),(112,8,14,80,NULL,NULL,NULL),(113,8,14,80,NULL,NULL,NULL),(114,8,14,80,NULL,NULL,NULL),(115,8,14,79,NULL,NULL,NULL),(116,8,14,79,NULL,NULL,NULL),(117,8,13,32,NULL,NULL,NULL),(118,8,13,35,NULL,NULL,NULL),(119,8,13,32,NULL,NULL,NULL),(120,8,13,32,NULL,NULL,NULL),(121,8,13,35,NULL,NULL,NULL),(122,8,13,34,NULL,NULL,NULL),(123,8,13,34,NULL,NULL,NULL),(124,8,13,35,NULL,NULL,NULL),(125,8,13,34,NULL,NULL,NULL),(126,8,13,32,NULL,NULL,NULL),(127,8,13,33,NULL,NULL,NULL),(128,8,13,34,NULL,NULL,NULL),(129,8,13,32,NULL,NULL,NULL),(130,8,13,32,NULL,NULL,NULL),(131,8,13,32,NULL,NULL,NULL),(132,5,7,1,NULL,NULL,NULL),(133,5,7,1,NULL,NULL,NULL),(134,5,7,5,NULL,NULL,NULL),(135,5,7,5,NULL,NULL,NULL),(136,5,7,2,NULL,NULL,NULL),(137,5,7,2,NULL,NULL,NULL),(138,5,7,6,NULL,NULL,NULL),(139,5,7,6,NULL,NULL,NULL),(140,5,7,6,NULL,NULL,NULL),(141,5,7,4,NULL,NULL,NULL),(142,5,7,3,NULL,NULL,NULL),(143,5,7,3,NULL,NULL,NULL),(144,5,7,5,NULL,NULL,NULL),(145,5,8,7,NULL,NULL,NULL),(146,5,8,7,NULL,NULL,NULL),(147,5,8,7,NULL,NULL,NULL),(148,5,8,7,NULL,NULL,NULL),(149,5,8,8,NULL,NULL,NULL),(150,5,8,8,NULL,NULL,NULL),(151,5,8,8,NULL,NULL,NULL),(152,5,8,10,NULL,NULL,NULL),(153,5,8,10,NULL,NULL,NULL),(154,5,8,10,NULL,NULL,NULL),(155,5,8,10,NULL,NULL,NULL),(156,5,8,7,NULL,NULL,NULL),(157,5,8,9,NULL,NULL,NULL),(158,5,8,10,NULL,NULL,NULL),(159,5,8,7,NULL,NULL,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measurement_unit`
--

DROP TABLE IF EXISTS `measurement_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measurement_unit` (
  `id` smallint(5) unsigned NOT NULL,
  `unit` varchar(45) NOT NULL,
  `symbol` varchar(20) DEFAULT NULL,
  `use` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `unit_UNIQUE` (`unit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measurement_unit`
--

LOCK TABLES `measurement_unit` WRITE;
/*!40000 ALTER TABLE `measurement_unit` DISABLE KEYS */;
INSERT INTO `measurement_unit` VALUES (1,'Kilograms','Kg',NULL),(2,'Tonnes','T',NULL),(3,'90 kg Bags',NULL,NULL),(4,'Number',NULL,'Programme'),(5,'Meeting',NULL,'Programme'),(6,'Lumpsum',NULL,'Programme');
/*!40000 ALTER TABLE `measurement_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `number_description`
--

DROP TABLE IF EXISTS `number_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `number_description` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `number_description`
--

LOCK TABLES `number_description` WRITE;
/*!40000 ALTER TABLE `number_description` DISABLE KEYS */;
INSERT INTO `number_description` VALUES (1,'Who were sampled'),(2,'Who used inputs correctly'),(3,'Who have adopted GAPs'),(4,'Who have adopted post-harvest management technologies');
/*!40000 ALTER TABLE `number_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `number_of_farmers`
--

DROP TABLE IF EXISTS `number_of_farmers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `number_of_farmers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number_description` int(10) unsigned NOT NULL,
  `age_bracket` smallint(5) unsigned DEFAULT NULL,
  `sex` smallint(5) unsigned DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `sampled_farmer_data` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_number_of_farmers_sex1` (`sex`),
  KEY `fk_disaggregated_sampled_farmer_data1` (`sampled_farmer_data`),
  KEY `fk_number_of_farmers_number_description1` (`number_description`),
  KEY `fk_number_of_farmers_age_bracket1` (`age_bracket`),
  CONSTRAINT `fk_disaggregated_sampled_farmer_data1` FOREIGN KEY (`sampled_farmer_data`) REFERENCES `sampled_farmer_data` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_number_of_farmers_age_bracket1` FOREIGN KEY (`age_bracket`) REFERENCES `age_bracket` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_number_of_farmers_number_description1` FOREIGN KEY (`number_description`) REFERENCES `number_description` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_number_of_farmers_sex1` FOREIGN KEY (`sex`) REFERENCES `sex` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `number_of_farmers`
--

LOCK TABLES `number_of_farmers` WRITE;
/*!40000 ALTER TABLE `number_of_farmers` DISABLE KEYS */;
/*!40000 ALTER TABLE `number_of_farmers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `on_farm_trials_and_demonstrations`
--

DROP TABLE IF EXISTS `on_farm_trials_and_demonstrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `on_farm_trials_and_demonstrations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `no_of_on_farm_trials` smallint(6) DEFAULT NULL,
  `no_of_demonstrations` smallint(6) DEFAULT NULL,
  `target_locations` text,
  `attendance_sheet` text,
  `kalro_officer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_on_farm_trials_and_demonstrations_person1` (`kalro_officer`),
  CONSTRAINT `fk_on_farm_trials_and_demonstrations_person1` FOREIGN KEY (`kalro_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `on_farm_trials_and_demonstrations`
--

LOCK TABLES `on_farm_trials_and_demonstrations` WRITE;
/*!40000 ALTER TABLE `on_farm_trials_and_demonstrations` DISABLE KEYS */;
/*!40000 ALTER TABLE `on_farm_trials_and_demonstrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance_indicator`
--

DROP TABLE IF EXISTS `performance_indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performance_indicator` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `performance_indicator_type` int(11) DEFAULT NULL,
  `result_hierarchy` int(11) NOT NULL,
  `description` varchar(400) NOT NULL,
  `baseline_date` date DEFAULT NULL,
  `baseline_value` double DEFAULT NULL,
  `year_of_use` smallint(6) DEFAULT NULL,
  `actual_value` double DEFAULT NULL,
  `expected_value` double DEFAULT NULL,
  `ratio` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_indicator_indicator_type1` (`performance_indicator_type`),
  KEY `fk_indicator_indicator_hierarchy1` (`result_hierarchy`),
  CONSTRAINT `fk_indicator_indicator_hierarchy1` FOREIGN KEY (`result_hierarchy`) REFERENCES `result_hierarchy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_indicator_indicator_type1` FOREIGN KEY (`performance_indicator_type`) REFERENCES `performance_indicator_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_indicator`
--

LOCK TABLES `performance_indicator` WRITE;
/*!40000 ALTER TABLE `performance_indicator` DISABLE KEYS */;
INSERT INTO `performance_indicator` VALUES (1,NULL,1,'f. Reduced hunger (NIMES)1','2016-07-03',2345,NULL,123,232,53.02),(2,NULL,1,'f. Reduced hunger (NIMES)1','2016-07-03',2345,NULL,123,232,53.02),(3,NULL,1,'f. Reduced hunger (NIMES)1','2016-07-03',2345,NULL,123,232,53.02),(4,NULL,1,'b. Number targeted households using improved inputs voluntarily (min 32,000HH), (RIMS2)','2016-01-01',12345,NULL,2345,3245,72.27),(5,NULL,2,'f. Number of women and youth empowered','2016-01-01',76776,NULL,3456,4546,76.02),(6,NULL,1,'a. 40,000 HH subsistence farmers issued with input package','2016-07-04',9876,2016,78,79,98.73),(7,NULL,3,'c. No. of crop production groups formed','2016-07-04',90987,2016,987,9876,9.99),(8,NULL,1,'b. Improved grain storage technologies adopted by 32,000 subsistence HH farmers','2016-07-06',8765,2017,987,999,98.8);
/*!40000 ALTER TABLE `performance_indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance_indicator_type`
--

DROP TABLE IF EXISTS `performance_indicator_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performance_indicator_type` (
  `id` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_indicator_type`
--

LOCK TABLES `performance_indicator_type` WRITE;
/*!40000 ALTER TABLE `performance_indicator_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `performance_indicator_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `sex` smallint(5) unsigned DEFAULT NULL,
  `national_id` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `business_name` varchar(45) DEFAULT NULL,
  `farmer_group` int(10) unsigned DEFAULT NULL,
  `farmer_sub_group` int(10) unsigned DEFAULT NULL,
  `designation_in_group` int(10) unsigned DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  `contact` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `national_id_UNIQUE` (`national_id`),
  KEY `fk_person_farmer_group1` (`farmer_group`),
  KEY `fk_person_location1` (`location`),
  KEY `fk_person_contact2` (`contact`),
  KEY `fk_person_sex1` (`sex`),
  KEY `fk_person_designation_in_group1` (`designation_in_group`),
  KEY `fk_person_farmer_sub_group1` (`farmer_sub_group`),
  CONSTRAINT `fk_person_contact2` FOREIGN KEY (`contact`) REFERENCES `contact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_designation_in_group1` FOREIGN KEY (`designation_in_group`) REFERENCES `designation_in_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_farmer_group1` FOREIGN KEY (`farmer_group`) REFERENCES `farmer_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_farmer_sub_group1` FOREIGN KEY (`farmer_sub_group`) REFERENCES `farmer_sub_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_location1` FOREIGN KEY (`location`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_sex1` FOREIGN KEY (`sex`) REFERENCES `sex` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Ben Siech',2,'29820457','1993-06-19','Millet growing',NULL,NULL,NULL,1,1),(3,'Ben Siech',2,'29820458','1993-06-22','Millet growing',NULL,NULL,NULL,12,3),(4,'Ben Siech',2,'29820459','1993-06-22','Millet growing',NULL,NULL,NULL,3,4),(5,'Ben Siech',2,'29820460','1993-06-22','Millet growing',NULL,NULL,NULL,4,5),(6,'Ben Siech',2,'29820461','1993-06-22','',NULL,NULL,NULL,5,6),(7,'Ben Siech',2,'29820463','2016-06-22','Millet growing',NULL,NULL,NULL,6,7),(8,'Ben Siech',2,'29820451','2016-06-22','Millet growing',NULL,NULL,NULL,7,8),(9,'Ben Siech',1,'29820452','2016-06-22','Millet growing',NULL,NULL,NULL,8,9),(10,'Ben Siech',2,'29820453','2016-06-22','Millet growing',NULL,NULL,NULL,9,10),(11,'Ben Siech',2,'29820455','2016-06-22','Millet growing',NULL,NULL,NULL,10,11),(12,'Ben Siech',2,'29820456','2016-06-22','Millet growing',NULL,NULL,NULL,11,12),(13,'Bwana mkubwa',1,'29820420','2016-06-29','Large scale millet growing',NULL,NULL,NULL,14,13),(14,'Henry K Chirchir',2,'0727080918','2016-06-30','Asai Farners Stores',NULL,NULL,NULL,17,14),(15,'Enock K Korir',2,'0728254281','2016-06-30','Lelchego Agro-vet',NULL,NULL,NULL,18,15),(16,'Leah J Keter',1,'0726365000','2016-06-30','Kmoiywa Stores',NULL,NULL,NULL,19,16),(17,'',1,'0724333448','2016-06-30','Koilel Stores',NULL,NULL,NULL,20,17),(18,'Ben Tallam',2,'0712444630','2016-06-30','Kabiyet Dairies Agro-vet',NULL,NULL,NULL,21,18),(19,'Martha Kiay',1,'0723144351','2016-06-30','Sonoiyat Women Group',NULL,NULL,NULL,22,19),(20,'Willy Rop',2,'0721571079','2016-06-30','Chamtany Farmers Centre',NULL,NULL,NULL,23,20),(21,'Henry Rono',2,'0720402148','2016-06-30','Naigaba Agro-vet',NULL,NULL,NULL,24,21),(22,'Judy Cherop',1,'0702954085','2016-06-30','Kaon Enterprises',NULL,NULL,NULL,25,22),(23,'Jane Kiprotich',1,'0720792007','2016-06-30','Taunet Agro-vet',NULL,NULL,NULL,26,23),(24,'Dorcas Chepkwony',1,'0721943674','2016-06-30','Baraton Farm Care',NULL,NULL,NULL,27,24),(25,'Dinah Cherotich',1,'0726253218','2016-06-30','Kamuny Agro-vet',NULL,NULL,NULL,28,25),(26,'John Kemboi',2,'0721893081','2016-06-30','Baraton Agricultural',NULL,NULL,NULL,29,26),(27,'Peter Mutai',2,'0720837777','2016-06-30','Lessos Agrovet',NULL,NULL,NULL,30,27),(28,'Christopher Sawe',2,'0717153900','2016-06-30','Tarakwa Agrochemicals',NULL,NULL,NULL,31,28),(29,'Francis Muniu',2,'0718801292',NULL,'Millenium Mega Choice',NULL,NULL,NULL,32,29),(30,'Cherotich Gladys',1,'0724968759',NULL,'Western Agrovet',NULL,NULL,NULL,33,30),(31,'Eliud Enziano Senelua',2,'0728386627',NULL,'God Provides Agrovet',NULL,NULL,NULL,34,31),(32,'Joseph Ngaah',2,'0723377384',NULL,'KENAFF',NULL,NULL,NULL,35,32),(33,'Timothy Sachita',2,'0721779275',NULL,'Elgon View',NULL,NULL,NULL,36,33),(34,'Christine Shiamala',1,'0721616191',NULL,'Lugari Stores',NULL,NULL,NULL,37,34),(35,'Julius Maloba',2,'0721757470',NULL,'Nyota Agrovet',NULL,NULL,NULL,38,35),(36,'Samson Isieko',2,'0723250815',NULL,'Somalo Enterprise Maranda',NULL,NULL,NULL,39,36),(37,'Peter Waswa Wakhisi',2,'0706814194',NULL,'Chango Traders',NULL,NULL,NULL,40,37),(38,'Benard Bulimo',2,'07228 8905',NULL,'Baraka Traders',NULL,NULL,NULL,41,38),(39,'Benson Lusabi Lukonzo',2,'0711174756',NULL,'Joy Enterprises',NULL,NULL,NULL,42,39),(40,'Edgar Lubanga',2,'0723514029',NULL,'Mwamba Agro Supply',NULL,NULL,NULL,43,40),(41,'Musa Namai',2,'0722463817',NULL,'Savana Agro Vet',NULL,NULL,NULL,44,41),(42,'Bernard O Ondeng',2,'0707449630',NULL,'Jaseme',NULL,NULL,NULL,45,42),(43,'Hellen Kabiru',1,'0722403758',NULL,'Rift Agrovet',NULL,NULL,NULL,46,43),(44,'Pius Namungu',2,'0707675152',NULL,'Kongoni Farm Care Agrovet',NULL,NULL,NULL,47,44),(45,'Anjeline Bwalei',1,'0721123422',NULL,'Berur Agrochemical',NULL,NULL,NULL,48,45),(46,'Anthony Wesonga',2,'0722146780',NULL,'Nangili Veterinery Centre',NULL,NULL,NULL,49,46),(47,'Teresa Kabiru',1,'0721381754',NULL,'Kimilili Agrovet',NULL,NULL,NULL,50,47),(48,'Peterson Obusuru',2,'0717627680',NULL,'Hillcare Agrovet',NULL,NULL,NULL,51,48),(49,'Rebecca Kemei',1,'0715824348',NULL,'Mois bridge Dairy Sacco',NULL,NULL,NULL,52,49),(50,'Eric Kisiangani',2,'0705322261',NULL,'Destiny Veterinary Centre',NULL,NULL,NULL,53,50),(51,'Joseph Kisingu',2,'0721717502',NULL,'Vetline Supplies',NULL,NULL,NULL,54,51),(52,'Sichangi Nandeyeka',2,'0721359760',NULL,'Sango Veterinary Centre',NULL,NULL,NULL,55,52),(53,'Alice Lumwaji',1,'0721903751',NULL,'Akhalu Enterprise',NULL,NULL,NULL,56,53),(54,'Beatrice Khavele',1,'0722217293',NULL,'Bimamu Stores',NULL,NULL,NULL,57,54),(55,'Essendi Simon',2,'0726570070',NULL,'Soy Acres Stores',NULL,NULL,NULL,58,55),(56,'Patrick Munialo',2,'0725348523',NULL,'Agrochemicals Nzoia',NULL,NULL,NULL,59,56),(57,'Judith Alegwa',1,'0726291499',NULL,'Hill Care',NULL,NULL,NULL,60,57),(58,'Philip Kirwa Sang',2,'0721295425',NULL,'Kirwa and Kirwa General shop',NULL,NULL,NULL,61,58),(59,'Joshua Kiplimo Laboso',2,'0725365246',NULL,'Cheptil Agro-Vet',NULL,NULL,NULL,62,59),(60,'William K. Kosgei',2,'0722605381',NULL,'Swara shop',NULL,NULL,NULL,63,60),(61,'Julius Kimuagor Sailel',2,'0720439311',NULL,'Kipyegon Mkulima shop',NULL,NULL,NULL,64,61),(62,'Wilson Kiboit Chemwok',2,'0720430070',NULL,'Kimgoror Investment',NULL,NULL,NULL,65,62),(63,'Peter Muttai',2,'0722493123',NULL,'Abrastel Enterprises',NULL,NULL,NULL,66,63),(64,'Peter KutiItumo',2,'0722922168',NULL,'Batumo Contractors Limited',NULL,NULL,NULL,67,64),(65,'Bismillahi Adan Batelo',2,'0722967750',NULL,'Bismillahi shop',NULL,NULL,NULL,68,65),(66,'Michael Kiplagat Oleoikop',2,'0720469481',NULL,'Kaiboi Multipurpose store',NULL,NULL,NULL,69,66),(67,'Emily Jerono Barngetung',1,'0723501674',NULL,'Chamtany Farm Inputs',NULL,NULL,NULL,70,67),(68,'Nathan K Sang',2,'0724610822',NULL,'Relax Kaiboi',NULL,NULL,NULL,71,68),(69,'Tamboche',1,'0722665531',NULL,'Tanykina Dairy Plant Ltd (Tamboche)',NULL,NULL,NULL,72,69),(70,'Simon Chepsiror',2,'0728332251',NULL,'Kurkung Kaptich Agrovet',NULL,NULL,NULL,73,70),(71,'Caleb K. Lagat',2,'0717125343',NULL,'Naet Kaptich Enterprise',NULL,NULL,NULL,74,71),(72,'Tanykina Holdings Ltd',1,'07226655311',NULL,'Tanykina Dairy Plant Ltd',NULL,NULL,NULL,75,72),(73,'Sharon N. Kasili',1,'0721279682',NULL,'Chenjeni Stores',NULL,NULL,NULL,76,73),(74,'Fred Mabonga',2,'0726582856',NULL,'Midlands',NULL,NULL,NULL,77,74),(75,'Wakwetu',1,'0720746248',NULL,'Wakwetu',NULL,NULL,NULL,78,75),(76,'Lusia Wasuka Maina',1,'0729437702',NULL,'Wilgrace',NULL,NULL,NULL,79,76),(77,'Kenneth Namwok',2,'0712950741',NULL,'Upper Hill',NULL,NULL,NULL,80,77),(78,'Francis Okech Otieno',2,'07129507411',NULL,'Riverside Agrovet',NULL,NULL,NULL,81,78),(79,'Eliakim W. Simiyu',2,'07002105011',NULL,'Elyland General Stop',NULL,NULL,NULL,82,79),(80,'Kirai Agrovet',1,'0717678756',NULL,'Kirai Agrovet',NULL,NULL,NULL,83,80),(81,'Maurice J. Manyasi',1,'0714134113',NULL,'Namubila General shop',NULL,NULL,NULL,84,81),(82,'Elizabeth Wekesa',1,'0710872605',NULL,'Vision Agrovet',NULL,NULL,NULL,85,82),(83,'Walayo Stores',1,'0712516488',NULL,'Walayo Stores',NULL,NULL,NULL,86,83),(84,'Moses Simiyu',2,'0711376374',NULL,'Narati Stores',NULL,NULL,NULL,87,84),(85,'James Otieno',2,'0727847991',NULL,'Brigadier Stores',NULL,NULL,NULL,88,85),(86,'Mary Nekesa Barasa',1,'0703236392',NULL,'Bunyala Stores',NULL,NULL,NULL,89,86),(87,'Joselyne Makhanu',1,'0711699029',NULL,'Naitiri Dairy',NULL,NULL,NULL,90,87),(88,'Edward Wekesa',2,'0711326915',NULL,'Blue Band Stores',NULL,NULL,NULL,91,88),(89,'Peter Najoli',2,'0715665941',NULL,'Naitiri Farmers Agrovet',NULL,NULL,NULL,92,89),(90,'Rose Manyonge',1,'0716440120',NULL,'BavesAgrovet',NULL,NULL,NULL,93,90),(91,'Martin Wetungu',2,'0722936681',NULL,'Amani Agrovet',NULL,NULL,NULL,94,91),(92,'Jafred Wanyonyi',2,'0721316405',NULL,'Naitiri General Stores',NULL,NULL,NULL,95,92),(93,'Victor Masibo',2,'0727245870',NULL,'Kadogo Cheapest',NULL,NULL,NULL,96,93),(94,'Mildred M Agalomba',1,'0720391446',NULL,'Chambiti Hardware',NULL,NULL,NULL,97,94),(95,'Elizabeth Musungu',1,'0723684715',NULL,'JembMukulima Stores',NULL,NULL,NULL,98,95),(96,'Peter M. Barasa',2,'0724390434',NULL,'Pema Agrovet',NULL,NULL,NULL,99,96),(97,'James Wanyonyi',2,'0720581492',NULL,'Sweet Point',NULL,NULL,NULL,100,97),(98,'Leonard Walubengo',2,'0725298249',NULL,'Vision Agrovet',NULL,NULL,NULL,101,98),(99,'Moses Kiptanui',2,'0714443999',NULL,'Tulin Stores',NULL,NULL,NULL,102,99),(100,'Dorcas Goren',1,'0722909070',NULL,'Grand Rabana',NULL,NULL,NULL,103,100),(101,'Simon Ndegwa Mwangi',2,'0701894699',NULL,'Sibanga farmers Gen Merchant',NULL,NULL,NULL,104,101),(102,'Thomas K.Talaam',2,'070022888',NULL,'Talaamis General store',NULL,NULL,NULL,105,102),(103,'Joseph Rotich',2,'0721744521',NULL,'Sambich General Stores',NULL,NULL,NULL,106,103),(104,'Jonathan Cheruiyot',2,'0722619790',NULL,'Kamur Agro Stores',NULL,NULL,NULL,107,104),(105,'Shem Charan',2,'0713359926',NULL,'Sheum Shop',NULL,NULL,NULL,108,105),(106,'Shadrack Terer',2,'0722745304',NULL,'Kachibora Agrostores',NULL,NULL,NULL,109,106),(107,'Kenfap',2,'0721571085',NULL,'Kenfap',NULL,NULL,NULL,110,107),(108,'Philemon K. Meli',2,'0729087368',NULL,'Teldon Investments',NULL,NULL,NULL,111,108),(109,'Stanley Ole sianga',2,'07290873688',NULL,'Cherangani Vet Centre',NULL,NULL,NULL,112,109),(110,'Paul Maina',2,'0721543442',NULL,'Ebenezer',NULL,NULL,NULL,113,110),(111,'Milka Wangari',1,'0724983689',NULL,'Joy Farmers',NULL,NULL,NULL,114,111),(112,'Kariuki Esther',1,'0725241834',NULL,'Benez Agrovet',NULL,NULL,NULL,115,112),(113,'Charles Isinga',2,'0735700166',NULL,'Bora Bora Agrovet',NULL,NULL,NULL,116,113),(114,'Joseph Maina',2,'0722911042',NULL,'Sunlight Agrovet',NULL,NULL,NULL,117,114),(115,'John Ngariya',2,'072276902',NULL,'Failoi hardware',NULL,NULL,NULL,118,115),(116,'Francis Letting',2,'0723454614',NULL,'Fralett General Suppliers',NULL,NULL,NULL,119,116),(117,'Erastus Opicho',2,'0726223479',NULL,'Elhoim Agrovet',NULL,NULL,NULL,120,117),(118,'Aggrey Waliaula',2,'0728266128',NULL,'Nice Stores',NULL,NULL,NULL,121,118),(119,'Benard Masanja',2,'0727430101',NULL,'Itete Trading Co. limited',NULL,NULL,NULL,122,119),(120,'KENAFF Reuben Kirwa',2,'0711556823',NULL,'KENFAP (KENAFF)-',NULL,NULL,NULL,123,120),(121,'Peter Mwangi',2,'0724407298',NULL,'Kobos Agrovet and Hardware',NULL,NULL,NULL,124,121),(122,'Joseph Nzomo',2,'0721863498',NULL,'Mazop enterprises limited',NULL,NULL,NULL,125,122),(123,'Joseph Ndungu',2,'0721744540',NULL,'Joyka Investment',NULL,NULL,NULL,126,123),(124,'Shem Kakai Matolo',2,'0710417801',NULL,'Kakai Kwanza / KK SHA Agrovet',NULL,NULL,NULL,127,124),(125,'Peter Kimathi Mbae',2,'0720718621',NULL,'Kilimo Leo stores',NULL,NULL,NULL,128,125),(126,'Peter Kibe',2,'0715212004',NULL,'Ebenezar Agro-vet',NULL,NULL,NULL,129,126),(127,'Jackson Kibet Rutto',2,'0727782893',NULL,'Kilimo Agro vet',NULL,NULL,NULL,130,127),(128,'Moses Simiyu Munialo',2,'0712974661',NULL,'YASOLO Agrovet',NULL,NULL,NULL,131,128),(129,'Philemon Kipchumba',2,'0727675481',NULL,'Teret Farmers Agrovet',NULL,NULL,NULL,132,129),(130,'Stanley Kipkorir Rotich',2,'0729578276',NULL,'Sosiana Agrovet',NULL,NULL,NULL,133,130),(131,'Elizabethe Ngendo Thuo',1,'0724401110',NULL,'Shamba Njoro Inputs',NULL,NULL,NULL,134,131),(132,'Stephen Kirungu',2,'0723549067',NULL,'Nakuru Njoro Animal feed',NULL,NULL,NULL,135,132),(133,'Seraphire Jully Oulepu',1,'0724398606',NULL,'Captan Farm Inputs Store',NULL,NULL,NULL,136,133),(134,'Joseph Murathi',2,'0729760232',NULL,'Wise Farmers Agrovet',NULL,NULL,NULL,137,134),(135,'Charles Kamau Chege',2,'0722256973',NULL,'Kawa Agro dealer',NULL,NULL,NULL,138,135),(136,'Gerishon Kamau',2,'0727022397',NULL,'Tumaini',NULL,NULL,NULL,139,136),(137,'Ann wambui Kimani',1,'0729070016',NULL,'Kwa Ann (Farmers pride) Mwisho wa lami',NULL,NULL,NULL,140,137),(138,'Francis Kahiu',2,'0722319008',NULL,'Topfarm Agrovet',NULL,NULL,NULL,141,138),(139,'Desmond Kiplangat Chumo',2,'0723114255',NULL,'Nessuit farmers Agrovet',NULL,NULL,NULL,142,139),(140,'Charles Biwott',2,'0700210501',NULL,'Nessuit Agrovet',NULL,NULL,NULL,143,140),(141,'Geoffrey Irangi',2,'0722360185',NULL,'Mwanainchi Farm Inputs',NULL,NULL,NULL,144,141),(142,'G.K Ngaruiya',2,'0728158374',NULL,'One Kenya Agrovet',NULL,NULL,NULL,145,142),(143,'Mary Wanjohi',1,'0721525849',NULL,'Ole-Subukia Animal Feeds',NULL,NULL,NULL,146,143),(144,'Mary Kahingo',1,'0722692425',NULL,'Tetu Agrovet',NULL,NULL,NULL,147,144),(145,'Mary Kahingo',1,'0727548284',NULL,'Mawa Agrovet',NULL,NULL,NULL,148,145),(146,'Jane Muthoni Mburu',1,'0701047219',NULL,'Modern Shamba supplies',NULL,NULL,NULL,149,146),(147,'Patrick Mugo',2,'072887091',NULL,'Highway Agrovet',NULL,NULL,NULL,150,147),(148,'Immanuel Salaons Lempaa',2,'0722251390',NULL,'Universal Shamba Supplies',NULL,NULL,NULL,151,148),(149,'Gladwell Waithera',1,'0722950921',NULL,'Anicrop world co. ltd',NULL,NULL,NULL,152,149),(150,'Lempaa Raphael K.',2,'0722748253',NULL,'Shamba Inputs supplies',NULL,NULL,NULL,153,150),(151,'Zacheos Monani',2,'0721371809',NULL,'Moche Agrovet',NULL,NULL,NULL,154,151),(152,'Mary Kemunto',1,'0711576445',NULL,'Hybrid seed Centre',NULL,NULL,NULL,155,152),(153,'Ann Wanjiru Wakaba',1,'0727713457',NULL,'Universal United investment',NULL,NULL,NULL,156,153),(154,'Stephen Kiptoo Lelei',2,'0726741739',NULL,'MalekiAgrovet',NULL,NULL,NULL,157,154),(155,'Benson Kariuki',2,'0725165760',NULL,'NdimuAgrovet',NULL,NULL,NULL,158,155),(156,'John N. Mwangi',2,'0724344736',NULL,'Millenium Agrovet',NULL,NULL,NULL,159,156);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_role`
--

DROP TABLE IF EXISTS `person_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_role` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `person_role` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `person_role_UNIQUE` (`person_role`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_role`
--

LOCK TABLES `person_role` WRITE;
/*!40000 ALTER TABLE `person_role` DISABLE KEYS */;
INSERT INTO `person_role` VALUES (12,'Agmark'),(2,'Agro-dealer'),(5,'County Officer'),(10,'Equity'),(1,'Farmer'),(8,'KALRO Officer'),(7,'National Officer'),(6,'Regional Coordinator'),(4,'Sub-county Officer'),(9,'System Admin'),(3,'WAO (Ward Extension Officer)'),(11,'Warehouse Operator');
/*!40000 ALTER TABLE `person_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_vs_actual`
--

DROP TABLE IF EXISTS `plan_vs_actual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan_vs_actual` (
  `id` smallint(5) unsigned NOT NULL,
  `choice` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_vs_actual`
--

LOCK TABLES `plan_vs_actual` WRITE;
/*!40000 ALTER TABLE `plan_vs_actual` DISABLE KEYS */;
INSERT INTO `plan_vs_actual` VALUES (1,'Plan'),(2,'Updated');
/*!40000 ALTER TABLE `plan_vs_actual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planning`
--

DROP TABLE IF EXISTS `planning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planning` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `annual_workplan_reference_code` varchar(45) DEFAULT NULL,
  `activity` int(10) unsigned NOT NULL,
  `awpb_target` decimal(16,2) DEFAULT NULL,
  `programme_target` decimal(16,2) DEFAULT NULL,
  `value_achieved` decimal(16,2) DEFAULT NULL,
  `allocated_budget` decimal(16,2) DEFAULT NULL,
  `component` smallint(5) unsigned NOT NULL,
  `sub_component` smallint(5) unsigned DEFAULT NULL,
  `implementing_partner` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_programme_component1` (`component`),
  KEY `fk_programme_sub_component1` (`sub_component`),
  KEY `fk_programme_table11` (`implementing_partner`),
  KEY `fk_programme_activity1` (`activity`),
  CONSTRAINT `fk_programme_activity1` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_component1` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_sub_component1` FOREIGN KEY (`sub_component`) REFERENCES `sub_component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_table11` FOREIGN KEY (`implementing_partner`) REFERENCES `implementing_partner` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planning`
--

LOCK TABLES `planning` WRITE;
/*!40000 ALTER TABLE `planning` DISABLE KEYS */;
/*!40000 ALTER TABLE `planning` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procurement`
--

DROP TABLE IF EXISTS `procurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procurement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item` varchar(45) DEFAULT NULL,
  `date_purchased` varchar(45) DEFAULT NULL,
  `serial_number` varchar(45) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `target_office` varchar(45) DEFAULT NULL,
  `county` smallint(5) unsigned DEFAULT NULL,
  `sub_county` varchar(45) DEFAULT NULL,
  `cost` decimal(16,2) DEFAULT NULL,
  `lpo_number` varchar(45) DEFAULT NULL,
  `invoice_or_receipt` varchar(300) DEFAULT NULL COMMENT 'Attachment\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_purchase_county1` (`county`),
  CONSTRAINT `fk_purchase_county1` FOREIGN KEY (`county`) REFERENCES `county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement`
--

LOCK TABLES `procurement` WRITE;
/*!40000 ALTER TABLE `procurement` DISABLE KEYS */;
/*!40000 ALTER TABLE `procurement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procurement_method`
--

DROP TABLE IF EXISTS `procurement_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procurement_method` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `method` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement_method`
--

LOCK TABLES `procurement_method` WRITE;
/*!40000 ALTER TABLE `procurement_method` DISABLE KEYS */;
INSERT INTO `procurement_method` VALUES (1,'LCS'),(2,'QCBS'),(3,'ICB/UNOPS'),(4,'NS'),(5,'NCB'),(6,'NCB/UNOPS'),(7,'DS');
/*!40000 ALTER TABLE `procurement_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procurement_plan`
--

DROP TABLE IF EXISTS `procurement_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procurement_plan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `procurement_plan_type` smallint(5) unsigned NOT NULL,
  `description` varchar(400) DEFAULT NULL,
  `ifad_prior_review` smallint(6) NOT NULL,
  `plan_vs_actual` smallint(5) unsigned NOT NULL,
  `cost` decimal(16,2) DEFAULT NULL,
  `procurement_method` smallint(5) unsigned NOT NULL,
  `complete_bd` date DEFAULT NULL COMMENT 'Bidding Document',
  `approval_by_ifad1` date DEFAULT NULL,
  `approval_by_sda` date DEFAULT NULL,
  `issue_bd` date DEFAULT NULL COMMENT 'Bidding Document',
  `receive_bids` date DEFAULT NULL,
  `evaluate_bids` date DEFAULT NULL,
  `approval_by_ifad2` date DEFAULT NULL,
  `award` date DEFAULT NULL,
  `approval_by_sda_or_ag` date DEFAULT NULL,
  `sign_contract` date DEFAULT NULL,
  `commence_contract` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_procurement_plan_procurement_method1` (`procurement_method`),
  KEY `fk_procurement_plan_procurement_plan_category1` (`procurement_plan_type`),
  KEY `fk_procurement_plan_plan_vs_actual1` (`plan_vs_actual`),
  KEY `fk_procurement_plan_ifad_prior_review1` (`ifad_prior_review`),
  CONSTRAINT `fk_procurement_plan_ifad_prior_review1` FOREIGN KEY (`ifad_prior_review`) REFERENCES `ifad_prior_review` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_plan_vs_actual1` FOREIGN KEY (`plan_vs_actual`) REFERENCES `plan_vs_actual` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_procurement_method1` FOREIGN KEY (`procurement_method`) REFERENCES `procurement_method` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_procurement_plan_category1` FOREIGN KEY (`procurement_plan_type`) REFERENCES `procurement_plan_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement_plan`
--

LOCK TABLES `procurement_plan` WRITE;
/*!40000 ALTER TABLE `procurement_plan` DISABLE KEYS */;
INSERT INTO `procurement_plan` VALUES (1,1,'undefined',1,1,2143244.00,1,'2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02',NULL,'2016-07-02','2016-07-02',NULL,'2016-07-02','2016-07-02'),(2,1,'Selection of sub-counties',1,1,987654.00,1,'2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02',NULL,'2016-07-02','2016-07-02'),(3,1,'Baseline survey',1,1,9876543333.00,1,'2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02'),(4,1,'GESI strategy',1,1,980887654.00,1,'2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02');
/*!40000 ALTER TABLE `procurement_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procurement_plan_cs`
--

DROP TABLE IF EXISTS `procurement_plan_cs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procurement_plan_cs` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `procurement_plan_type` smallint(5) unsigned NOT NULL,
  `description` varchar(400) DEFAULT NULL,
  `ifad_prior_review` smallint(6) NOT NULL,
  `plan_vs_actual` smallint(5) unsigned NOT NULL,
  `cost` decimal(16,2) DEFAULT NULL,
  `procurement_method` smallint(5) unsigned NOT NULL,
  `submit_tor` date DEFAULT NULL,
  `complete_reoi` date DEFAULT NULL,
  `complete_bd` date DEFAULT NULL COMMENT 'Bidding Document',
  `approval_by_ifad1` date DEFAULT NULL,
  `issue_reoi` date DEFAULT NULL,
  `receive_eois` date DEFAULT NULL,
  `establish_short_list` date DEFAULT NULL,
  `complete_rfp` date DEFAULT NULL,
  `approval_by_ifad2` date DEFAULT NULL,
  `approval_by_sda` date DEFAULT NULL,
  `issue_rfp` date DEFAULT NULL COMMENT 'Bidding Document',
  `receive_proposals` date DEFAULT NULL,
  `evaluate_technical_proposals` date DEFAULT NULL,
  `approval_by_ifad3` date DEFAULT NULL,
  `negotiate` date DEFAULT NULL,
  `approval_by_ifad4` date DEFAULT NULL,
  `award` date DEFAULT NULL,
  `approval_by_sda_or_ag` date DEFAULT NULL,
  `sign_contract` date DEFAULT NULL,
  `commence_contract` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_procurement_plan_procurement_method10` (`procurement_method`),
  KEY `fk_procurement_plan_procurement_plan_category10` (`procurement_plan_type`),
  KEY `fk_procurement_plan_ncs_plan_vs_actual1` (`plan_vs_actual`),
  KEY `fk_procurement_plan_ncs_ifad_prior_review1` (`ifad_prior_review`),
  CONSTRAINT `fk_procurement_plan_ncs_ifad_prior_review1` FOREIGN KEY (`ifad_prior_review`) REFERENCES `ifad_prior_review` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_ncs_plan_vs_actual1` FOREIGN KEY (`plan_vs_actual`) REFERENCES `plan_vs_actual` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_procurement_method10` FOREIGN KEY (`procurement_method`) REFERENCES `procurement_method` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_procurement_plan_category10` FOREIGN KEY (`procurement_plan_type`) REFERENCES `procurement_plan_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement_plan_cs`
--

LOCK TABLES `procurement_plan_cs` WRITE;
/*!40000 ALTER TABLE `procurement_plan_cs` DISABLE KEYS */;
INSERT INTO `procurement_plan_cs` VALUES (1,1,'undefined',1,1,23456.00,1,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03',NULL,NULL,NULL,'2016-07-03',NULL,NULL,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03'),(2,1,'Baseline survey',1,1,234567.00,1,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03'),(3,3,'GESI strategy',2,2,98765.00,1,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03'),(4,1,'Development of Crop Insurance Products',1,1,67586.00,1,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03',NULL,'2016-07-03');
/*!40000 ALTER TABLE `procurement_plan_cs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procurement_plan_type`
--

DROP TABLE IF EXISTS `procurement_plan_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procurement_plan_type` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement_plan_type`
--

LOCK TABLES `procurement_plan_type` WRITE;
/*!40000 ALTER TABLE `procurement_plan_type` DISABLE KEYS */;
INSERT INTO `procurement_plan_type` VALUES (1,'Goods'),(2,'Non-Consulting Services(NCS)'),(3,'Consulting Services');
/*!40000 ALTER TABLE `procurement_plan_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'Eastern'),(2,'Western');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_hierarchy`
--

DROP TABLE IF EXISTS `result_hierarchy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_hierarchy` (
  `id` int(11) NOT NULL,
  `description` varchar(400) DEFAULT NULL,
  `sub_component` smallint(5) unsigned DEFAULT NULL,
  `component` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_indicator_hierarchy_sub_component1` (`sub_component`),
  KEY `fk_indicator_hierarchy_component1` (`component`),
  CONSTRAINT `fk_indicator_hierarchy_component1` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_indicator_hierarchy_sub_component1` FOREIGN KEY (`sub_component`) REFERENCES `sub_component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_hierarchy`
--

LOCK TABLES `result_hierarchy` WRITE;
/*!40000 ALTER TABLE `result_hierarchy` DISABLE KEYS */;
INSERT INTO `result_hierarchy` VALUES (1,'Goal: National food security improved',NULL,NULL),(2,'Increased production of targeted cereal staples (maize, sorghum, millet and associated pulses)',NULL,NULL),(3,'Increased income of smallholders in medium and high potential production areas',NULL,NULL);
/*!40000 ALTER TABLE `result_hierarchy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sampled_farmer_data`
--

DROP TABLE IF EXISTS `sampled_farmer_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sampled_farmer_data` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ward_extension_officer` int(10) unsigned NOT NULL,
  `season` smallint(6) DEFAULT NULL,
  `productivity_per_crop_per_farmer` varchar(100) DEFAULT NULL,
  `post_harvest_losses` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_sampled_farmer_data_person1` (`ward_extension_officer`),
  CONSTRAINT `fk_sampled_farmer_data_person1` FOREIGN KEY (`ward_extension_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sampled_farmer_data`
--

LOCK TABLES `sampled_farmer_data` WRITE;
/*!40000 ALTER TABLE `sampled_farmer_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `sampled_farmer_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sex`
--

DROP TABLE IF EXISTS `sex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sex` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `sex` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sex`
--

LOCK TABLES `sex` WRITE;
/*!40000 ALTER TABLE `sex` DISABLE KEYS */;
INSERT INTO `sex` VALUES (1,'Female'),(2,'Male');
/*!40000 ALTER TABLE `sex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soil_fertility_package`
--

DROP TABLE IF EXISTS `soil_fertility_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soil_fertility_package` (
  `id` int(11) NOT NULL,
  `no_of_sampling_and_analysis_activities` smallint(6) DEFAULT NULL,
  `no_of_packages_developed` smallint(6) DEFAULT NULL,
  `target_locations` text,
  `no_of_meetings_for_e_voucher_definition` smallint(6) DEFAULT NULL,
  `kalro_officer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_soil_fertility_package_person1` (`kalro_officer`),
  CONSTRAINT `fk_soil_fertility_package_person1` FOREIGN KEY (`kalro_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soil_fertility_package`
--

LOCK TABLES `soil_fertility_package` WRITE;
/*!40000 ALTER TABLE `soil_fertility_package` DISABLE KEYS */;
/*!40000 ALTER TABLE `soil_fertility_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `static_input`
--

DROP TABLE IF EXISTS `static_input`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `static_input` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `static_input`
--

LOCK TABLES `static_input` WRITE;
/*!40000 ALTER TABLE `static_input` DISABLE KEYS */;
INSERT INTO `static_input` VALUES (2,'Beans'),(4,'Green grams'),(1,'Maize'),(6,'Millet'),(3,'Pigeon peas'),(7,'Planting'),(9,'Post-harvest'),(5,'Sorghum'),(8,'Top-dressing');
/*!40000 ALTER TABLE `static_input` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_activity`
--

DROP TABLE IF EXISTS `sub_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_activity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `activity` int(10) unsigned NOT NULL,
  `measurement_unit` smallint(5) unsigned NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `actual_expenditure` decimal(16,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_sub_activity_activity1` (`activity`),
  KEY `fk_sub_activity_measurement_unit1` (`measurement_unit`),
  CONSTRAINT `fk_sub_activity_activity1` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_measurement_unit1` FOREIGN KEY (`measurement_unit`) REFERENCES `measurement_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_activity`
--

LOCK TABLES `sub_activity` WRITE;
/*!40000 ALTER TABLE `sub_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_component`
--

DROP TABLE IF EXISTS `sub_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_component` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `sub_component` varchar(200) NOT NULL,
  `component` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `sub_component_UNIQUE` (`sub_component`),
  KEY `fk_sub_component_component1` (`component`),
  CONSTRAINT `fk_sub_component_component1` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_component`
--

LOCK TABLES `sub_component` WRITE;
/*!40000 ALTER TABLE `sub_component` DISABLE KEYS */;
INSERT INTO `sub_component` VALUES (1,'Including appropriate technical packages, advisory services and input supply- to increase the\nquantity and quality of production',1),(2,'The strengthening of farmers‟ organizations',1);
/*!40000 ALTER TABLE `sub_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_county`
--

DROP TABLE IF EXISTS `sub_county`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_county` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `county` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_sub_county_county1` (`county`),
  CONSTRAINT `fk_sub_county_county1` FOREIGN KEY (`county`) REFERENCES `county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_county`
--

LOCK TABLES `sub_county` WRITE;
/*!40000 ALTER TABLE `sub_county` DISABLE KEYS */;
INSERT INTO `sub_county` VALUES (1,'Mbeere North',2),(2,'Mbeere South',2),(3,'Tharaka North',7),(4,'Tharaka South',7),(5,'Mwingi Central',4),(6,'Mwingi North',4),(7,'Njoro',5),(8,'Molo',5),(9,'Chesumei',6),(10,'Mosop',6),(11,'Likuyani',3),(12,'Lugari',3),(13,'Kwanza',8),(14,'Cheranganyi',8),(15,'Sirisia',1),(16,'Tongaren',1),(17,'Emgwen',6);
/*!40000 ALTER TABLE `sub_county` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technology`
--

DROP TABLE IF EXISTS `technology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technology` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number_of_studies_conducted` smallint(6) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type_purpose` varchar(400) DEFAULT NULL,
  `target_sub_counties` text,
  `kalro_officer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_technology_person1` (`kalro_officer`),
  CONSTRAINT `fk_technology_person1` FOREIGN KEY (`kalro_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technology`
--

LOCK TABLES `technology` WRITE;
/*!40000 ALTER TABLE `technology` DISABLE KEYS */;
/*!40000 ALTER TABLE `technology` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technology_target_county`
--

DROP TABLE IF EXISTS `technology_target_county`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technology_target_county` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `technology` int(10) unsigned NOT NULL,
  `county` smallint(5) unsigned NOT NULL,
  `kalro_officer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_technology_target_county_technology1` (`technology`),
  KEY `fk_technology_target_county_county1` (`county`),
  KEY `fk_technology_target_county_person1` (`kalro_officer`),
  CONSTRAINT `fk_technology_target_county_county1` FOREIGN KEY (`county`) REFERENCES `county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_technology_target_county_person1` FOREIGN KEY (`kalro_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_technology_target_county_technology1` FOREIGN KEY (`technology`) REFERENCES `technology` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technology_target_county`
--

LOCK TABLES `technology_target_county` WRITE;
/*!40000 ALTER TABLE `technology_target_county` DISABLE KEYS */;
/*!40000 ALTER TABLE `technology_target_county` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` smallint(5) unsigned NOT NULL,
  `topic` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainee`
--

DROP TABLE IF EXISTS `trainee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainee` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `training` int(10) unsigned NOT NULL,
  `person` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_table1_training2` (`training`),
  KEY `fk_table1_person1` (`person`),
  CONSTRAINT `fk_table1_person1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_training2` FOREIGN KEY (`training`) REFERENCES `training` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainee`
--

LOCK TABLES `trainee` WRITE;
/*!40000 ALTER TABLE `trainee` DISABLE KEYS */;
/*!40000 ALTER TABLE `trainee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `person` int(10) unsigned NOT NULL,
  `training` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_trainer_person1` (`person`),
  KEY `fk_trainer_training1` (`training`),
  CONSTRAINT `fk_trainer_person1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trainer_training1` FOREIGN KEY (`training`) REFERENCES `training` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer`
--

LOCK TABLES `trainer` WRITE;
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `topic` smallint(5) unsigned NOT NULL,
  `venue` int(10) unsigned NOT NULL,
  `number_of_trainees` int(11) DEFAULT NULL,
  `attendance_sheet` varchar(200) DEFAULT NULL COMMENT 'Location of attachment\n',
  `category_of_trainees` smallint(5) unsigned DEFAULT NULL COMMENT 'Category of trainees eg farmers',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_training_person_role1` (`category_of_trainees`),
  KEY `fk_training_location1` (`venue`),
  KEY `fk_training_training_topic1` (`topic`),
  CONSTRAINT `fk_training_location1` FOREIGN KEY (`venue`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_training_person_role1` FOREIGN KEY (`category_of_trainees`) REFERENCES `person_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_training_training_topic1` FOREIGN KEY (`topic`) REFERENCES `topic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training`
--

LOCK TABLES `training` WRITE;
/*!40000 ALTER TABLE `training` DISABLE KEYS */;
/*!40000 ALTER TABLE `training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `person` int(10) unsigned NOT NULL,
  `username` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `person_role` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_account_person1` (`person`),
  KEY `fk_user_account_person_role1` (`person_role`),
  CONSTRAINT `fk_user_account_person1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_account_person_role1` FOREIGN KEY (`person_role`) REFERENCES `person_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,1,'siele.bernard@gmail.com','1aca6dca73cc19c35147c3c7d2707ca78dd2495c7075c4b41912c140c4b39c00',9),(2,3,'agrodealer@gmail.com','35e8f79d9e51f0a3970f9cadc210ddec20d377af1e6a6d09c2f4eff3a9efa0d8',2),(3,4,'countyofficer@gmail.com','86ba3fc5f3c904d88523fe117921e46ce21f000b71386471d43367e6e86193fe',5),(4,5,'equity@gmail.com','3e0abfe6dc7124b22dc288240ab281552628179686a89fa3f09b8fd839f2453a',10),(5,6,'farmer@gmail.com','ba2a06effc4ee674b193ba47e22af92dda8e5c83b6657bb1d105deb6f2b4fa5f',1),(6,7,'kalroofficer@gmail.com','b163088866b02553aac34836f7699c1dcb5ccba391edd8efbdc21f664cefd0e7',8),(7,8,'nationalofficer@gmail.com','150a83b58026957b23ad46de7e69aa9de4e406cb6e10b7e92e940ad224687ace',7),(8,9,'regionalcoordinator@gmail.com','1abfa2598a1c1e5e722af1b81f8594330531d916f84cd18b804b8efabb431b21',6),(9,10,'subcounty@gmail.com','8d5e355759e9bbaeb101db1129e489b4e0acce93f38e6ea61d5c416bf872c924',4),(10,11,'warehouseoperator@gmail.com','f671352bd741b3226ed0126d2bc06d2e2ca485ee4ab0dbe8c3ab16a9b4a9b71b',11),(11,12,'wao@gmail.com','59c094d3f594d8900c5db0d90574d9f65c779b4101a02a8cb6d7f1c8586f1af5',3),(12,13,'ss@anynomous.ac.ke','b34e62121d7076d6deb48917adac921b9007084c6133849867ea1c72c7fcaf24',1),(13,14,'henrykchirchir@gmail.com','89ab1d1be52943d35bdbd858bc72c72430859085f6b59a6edc1089e73e32fe12',2),(14,15,'enockkkorir@gmail.com','35a3214b3f7475479c47a9e899a33b3aef8234e99c6c4c096f0a08746a257428',2),(15,16,'leahjterer@gmail.com','84302700142d4adaf528b818be8c0c0593f677953c2a2fb8b8d475d0dc01bdf8',2),(16,17,'koilelstores@gmail.com','9b990469e69d3c775ed90f7479689de4dc5b2f4f9acb6524dd8946fb70868776',2),(17,18,'bentallam@gmail.com','a0ee3d40a581f45ba46a74946ed13c1ae7a2d5f911e9fedc4e1cb35f863eb42e',2),(18,19,'marthakiay@gmail.com','b4cda9b6f54a317b660f18793b6fecbc5ff78b29623b8f1088e16bd8197d3b2b',2),(19,20,'willyrop@gmail.com','ece185256df7dfa96dde23cec764fb1c0dffb21401ccb3584ffcb34de6cb1565',2),(20,21,'henryrop@gmail.com','004d6a8c16f1dd6f6c2ded5017b2626f6f136a89b29a8ca8ae4e1dad56e2f393',12),(21,22,'judycherop@gmail.com','37f58d876a0940a0f66a495ad1b1ecc01272ed460299543b7145856f79466f80',2),(22,23,'janekiprotich@gmail.com','839829050a82b9692501058ea055bfdb16fe4d22df094ccb62a673800adc5f64',2),(23,24,'dorcaschepkwony@gmail.com','d17bc3ec3eed56f05f01a5929c38263df24afb6bd1ca6ef4a98b4ff4b2f98f32',2),(24,25,'dinahcherotich@gmail.com','6f999f8e1d27a4ccc34a09847a7b67cd4179942eeb2528c09908aa3cd34dd31a',12),(25,26,'johnkemboi@gmail.com','cc73f996704dfb924629a6fe4a1707fff68dee5e44cbbbdfb87f4c7eedb20150',12),(26,27,'petermutai@gmail.com','0d2ca99b7d29607ab48d95ce5fd65336c92ec054f07152c29e93fc07999784ca',2),(27,28,'christophersawe@gmail.com','3bd1cbd855cb2f0f23101a19b955a778618f00245a288b0f514e96231b7b7f53',2),(28,29,'francismuniu15@gmail.com','d82eac596b25eb62883c61e242debd5764c731355502f57590f1d8df87175d9d',2),(29,30,'cherotichgladys55@gmail.com','af11dee7988c6bc6cffcc2d3ee421cc1f0c6d8395a94aa2251ff8e2e7afe3eea',2),(30,31,'eliudenzianosenelua85@gmail.com','ba85f54d4ed2e4c9e9c79bc71c9854cb4004c30d0e5cd600e87f658d4767e09c',2),(31,32,'josephngaah06@gmail.com','9f8fda809570fb14b26a650dfda3d89d53db3ce23cef896946db92a78ad7d99e',2),(32,33,'timothysachita26@gmail.com','f93c7fb6ab224d71771c57a91f9a11c67d4c57daeea39a39805610b5e3f6598d',2),(33,34,'christineshiamala66@gmail.com','448bd11b18481103fef792d79fdd1f73e72b703d197f9075ff77a0d40bcebcaf',2),(34,35,'juliusmaloba07@gmail.com','1ac7b389dab08e1d16d836aa06067517afb885e33f32ec088a56807ec2f3de3a',2),(35,36,'samsonisieko27@gmail.com','82a8aa5885ad5b1d7c78f6df85ddbf191c52a89415d97a4a2c5a7d4da27f50d6',2),(36,37,'peterwaswawakhisi47@gmail.com','8a8a753f16b72ba8b0cc8302b09683b7841d2606be43404ed7dceb1255f0aa50',2),(37,38,'benardbulimo67@gmail.com','80d29ebd94cb1692ecc153f0ad32fbe57623508e44230f28500ab6fb01b78519',2),(38,39,'bensonlusabilukonzo97@gmail.com','361a9675e16a7a2e99607b939a62d91632dfdc41920744453802678a771ad701',2),(39,40,'edgarlubanga28@gmail.com','16c0fd65d738b007df3b7f6503576f8c947f79e6b223cdefb950af5888485cf5',2),(40,41,'musanamai38@gmail.com','0765f7a52ffa45b071853edb4d60daf85266e1ed591c73dfa61614c842b8fefb',2),(41,42,'bernardoondeng58@gmail.com','039e4730a1d14bccfe420e397a7a96b10c69190ef1350445da677fb4ffbbca51',2),(42,43,'hellenkabiru68@gmail.com','b2fc7b0bbc5e86e2698085990ad0d55c28967ce9ed3097d6302e9edde35afbdd',2),(43,44,'piusnamungu98@gmail.com','294c18255bd27a3905182d8b39e2eebf3d5ad2bd56e3a913982cc1e0a5600845',2),(44,45,'anjelinebwalei19@gmail.com','1ded8c40c41687a8c872c930d06a9739562afabd9a07032c335c3dc5179d2a4d',2),(45,46,'anthonywesonga29@gmail.com','44e2862bea7d699d7a281db9a75ed45a618c20617e44bda54e591962bccff21d',2),(46,47,'teresakabiru49@gmail.com','945e769ecbc755c0f757d841674fde743b9df53406e8349362545e6502d6d49f',2),(47,48,'petersonobusuru69@gmail.com','3a3d3dca3ffed513cd077d6e044cd44f142af64ad6e3fd86dea017fbd4f5ec03',2),(48,49,'rebeccakemei99@gmail.com','ca31bc41817575ded4532c91bde51923e78abdf821c038f22742492856997aed',2),(49,50,'erickisiangani10@gmail.com','5b7b1974474a465493a3511524a08d933734c68c254f6bf25fb3c367987c7250',2),(50,51,'josephkisingu20@gmail.com','10e9f81e333c89d1ec4cd4c5358794d50460617ee5c990b29b824a8b746c9db9',2),(51,52,'sichanginandeyeka40@gmail.com','11cafc91cce651491cde6a2358edbd9ba2d894690a056e4396f0b79d076e5eb8',2),(52,53,'alicelumwaji60@gmail.com','3a4a22cdbb0fbdb0b0b837f978e6c3662c9506cfb40fa046b4c5ed8fc4488813',2),(53,54,'beatricekhavele90@gmail.com','c3f6dbae0685684cbfb42a6e2d121fcbb39d0e8b54c62b0b4c61a2a7fa7fe26e',2),(54,55,'essendisimon11@gmail.com','08336a1dd3bb26d078a97ca5b7a878198ff933b303e0c1f2af52e9efb55fe9ac',2),(55,56,'patrickmunialo41@gmail.com','52576d01628130b6c9dba31474240e5d4e8bf2d819fe9e52b7539e39525c5695',2),(56,57,'judithalegwa51@gmail.com','6fab1aee7de32ef40acfd381d61e6cc9ae9f613b69047693d53fafe0fe6e5471',2),(57,58,'philipkirwasang71@gmail.com','523dc27eabeefe6eea3711e0bb869b86dba6b8e25de339d71495da22cb5db17a',2),(58,59,'joshuakiplimolaboso91@gmail.com','e7f9c806f2aa9c16a6a92c9977155c97b56c51bb93bdf72fefc1aad3a468842f',2),(59,60,'williamk.kosgei12@gmail.com','3aeb0401fa87130af0c07db2e2e440f09f89cc6b1374a14e5ac6552d15bc1231',2),(60,61,'juliuskimuagorsailel22@gmail.com','be2a8164316b29a4719e825998dfdf79293f37e537acc198405108d71242c581',2),(61,62,'wilsonkiboitchemwok42@gmail.com','ac8db66aa8db88f7a90e143adeafc1afd0e218bbc0837a86e59929c383e4f464',2),(62,63,'petermuttai62@gmail.com','fad0b80c7463e6a3818034af9a0102b52ba6aa5f31f612467b34ff040f09bc32',2),(63,64,'peterkutiitumo82@gmail.com','dfa6d722a84c85ee845235fc55e34426c4bab1e9c248b00e7787a81169da27eb',2),(64,65,'bismillahiadanbatelo03@gmail.com','461678c1b452a2affeeb629d298ca4ff739c8f564305b6ffdbdb9fd75819329f',2),(65,66,'michaelkiplagatoleoikop23@gmail.com','eea774923648a0935c365317532ab3117eacfddd08fae34351edd70a678157ff',2),(66,67,'emilyjeronobarngetung43@gmail.com','7a65c707d89c98a22fa067559d6366f09f17eb66242c323cc22b494c54150447',2),(67,68,'nathanksang53@gmail.com','f89080e2c8d6fb65c93502886cece9a6fcd7bf93813d60e786f8e1e90e0fd7e6',2),(68,69,'tamboche73@gmail.com','e21d5f87770c45fe18cadb4f22df7c637eb5e086d96aeb6e9237b672b9eaca26',2),(69,70,'simonchepsiror24@gmail.com','3c07bf65685cf9542a5e854d487b358f1121ddf386c1ef1058accecd9135ee01',2),(70,71,'calebk.lagat54@gmail.com','de72df30140da281929564c0ee2eef35d457f187a0ecebedc7df1faf1df82c26',2),(71,72,'tanykinaholdingsltd74@gmail.com','0713a64c69a93115f648455b2b1ea03e5f95809c9e7a56fb15dbfa5ea7ae1318',2),(72,73,'sharonn.kasili05@gmail.com','43b294daa03931b8718081cc1bcca1b74cb2fab02039e43bbd785abfae85793c',2),(73,74,'fredmabonga25@gmail.com','beeecbe05f85539fc1095d063c6e3d98a76408fb93ee54b2cee43b3a916cab0a',2),(74,75,'wakwetu45@gmail.com','c73726a8dd9f00189d6dca8b25756ca27f5ff056dac8e213b235732a83aae09d',2),(75,76,'lusiawasukamaina55@gmail.com','b72042d624556e2f1ff635f52599054bf77fccd7384466dd91e31f6438257ff7',2),(76,77,'kennethnamwok75@gmail.com','e3c09a389d870df9ba7e3d1f4eeeb9ebbd0a456c5cbc82badde3e65fbe5fe492',2),(77,78,'francisokechotieno95@gmail.com','5d42358b152e54a4149fbf8d34348b562c7eadc5223e10833f033af3368aa64e',2),(78,79,'eliakimw.simiyu06@gmail.com','cff500412edeb7873a6f8cdaccd868188f8181534fb07b324f8e331352624f6c',2),(79,80,'kiraiagrovet26@gmail.com','26ae3e399637ccd3931ba0c21777d40e2d2ad7b5242add15563299f0fa618ba6',2),(80,81,'mauricej.manyasi36@gmail.com','2bd232b5b82a751b2e50d9f8e8ea6c0fbe7da53bb7d5ddab0f30538fc5b6c448',2),(81,82,'elizabethwekesa46@gmail.com','6594689f138407eea6ff581c27a09afbe5bd3bc0255d986578e9799ad417b732',2),(82,83,'walayostores76@gmail.com','0ea89c1dd8f7d8045ccad95647d2f44eb2d8c96872f74e634c0a81aebbb7db77',2),(83,84,'mosessimiyu96@gmail.com','33070515d21507dba01e9b585560032d0b17e5bb61c567de633e8dd75c1071e7',2),(84,85,'jamesotieno17@gmail.com','cb17cc338d8d5a87e703e1cf216c6e682881c0d71b3c3e54246ab916644f6679',2),(85,86,'marynekesabarasa37@gmail.com','a56a4c651c9994b2d550c83ddcf575471e0503da1efa2cd4ebdb7eb975a1c5d5',2),(86,87,'joselynemakhanu47@gmail.com','4cdea6742019c984c5045cc931354fb859e2ddff6c973920b43769fbe5ac37cd',2),(87,88,'edwardwekesa77@gmail.com','49f613ffb42d62b195365ae2ed90bde6a1f94cef6e15b83f6a7c21c71c80155b',2),(88,89,'peternajoli08@gmail.com','9072169aa07a9d167ab79dc76aebc36a2679f973ed8f36a9f6517444eb1b8b5c',2),(89,90,'rosemanyonge18@gmail.com','b2fa6ec395b3150f050207f98ab05663539f73f7746cdceafd9190923f7b4595',2),(90,91,'martinwetungu38@gmail.com','c981ffd37503e2ce4e916f7a3674ad6346cf6a267f4e325b6cf063fb0732a7ba',2),(91,92,'jafredwanyonyi48@gmail.com','e4221cead2975bf02645c232eab47c16239910649ad2c1419d9d916544eb3483',2),(92,93,'victormasibo58@gmail.com','235e59e940db53201b411be28e98f3ac7cda6c95112df10adb7ce5d13785754f',2),(93,94,'mildredmagalomba78@gmail.com','d1c54311fe37479a8ac52f4b37e1729194c0406ccc5b4ded4d32158a0b233bc4',2),(94,95,'elizabethmusungu98@gmail.com','6219066a47668df48eef233421e7cb23fce206f8eb37ad9a9105a2cd771c8056',2),(95,96,'peterm.barasa09@gmail.com','87f2e94828d3697aabc648e8b2d2932c063b11d4cf4a421fa0e5d5b3b896adfc',2),(96,97,'jameswanyonyi39@gmail.com','95ad571dc21327ba0a5b10e30ff2bc4d092920b46e3c9231629c82e91ba8e7a1',2),(97,98,'leonardwalubengo59@gmail.com','720fefda0179401b68d6301d936a54af96f4e8004c23f4a5f1d9312ef816b4d3',2),(98,99,'moseskiptanui79@gmail.com','98de6d87c88ce6d56c5bcca3e6f6846dc49dd3ea7652cb0719bcc50fe8f316a2',2),(99,100,'dorcasgoren99@gmail.com','2a959468a424884517cb62468af553ac11b8242d6fa682f3e9b2ca6805e03c13',2),(100,101,'simonndegwamwangi10@gmail.com','e6c021df152ffb07041c0c44dfcc0b95fbfd494954bbd3e0f77dca7a97b66817',2),(101,102,'thomask.talaam50@gmail.com','2cf0c897e98f99d94a51530ef661d6c9c6d8500297ce43ec21a71ce1d86b09a7',2),(102,103,'josephrotich70@gmail.com','498a5c18a5d9bb521b34306df69f5474a045ea65f62da4b5d0a7794be77aa4ac',2),(103,104,'jonathancheruiyot90@gmail.com','6f34c9bfb2c96520773f2a297b9b01f3578471708dfce55aea82ab5781dc6b1c',2),(104,105,'shemcharan11@gmail.com','cd8caa4a967afe9913870415b6ac39cc45b2355dcb6abfad41bc8b91c9cf0071',2),(105,106,'shadrackterer21@gmail.com','582cdf338ba03dda37ba14f81bd0fe66f62a3c23bc817dba43ea148f61da60ea',2),(106,107,'kenfap41@gmail.com','f0eaf26477cb35979e8e25cd06093715c0bb17ca2dc3d35f44d07b701f2a96e5',2),(107,108,'philemonk.meli71@gmail.com','fd051372a4eaebfdcc68e42077acc33b70670b83a0e82b748f44343b6477caeb',2),(108,109,'stanleyolesianga02@gmail.com','feca194d21ee5af5a4ff3c870ec7670c89fbf8ade682e30708c5d7151f3cddea',2),(109,110,'paulmaina42@gmail.com','64d34e531b4af5ae79819595968b6aec51ab837b01748dee9e2277d55b03ab6f',2),(110,111,'milkawangari72@gmail.com','4dcb1f228edfa541476666452e8ead979c83d69a7b5f39a31bf011422dbaa093',2),(111,112,'kariukiesther23@gmail.com','81bfea5af4960bec6190c7d78d75c92a565b0b0ed01513dc52c97f2782201565',2),(112,113,'charlesisinga63@gmail.com','4803e644fb5f87b603087afb65720b3296898ad7188a6511283887e9b2618721',2),(113,114,'josephmaina24@gmail.com','902eb47e9be11265b45aeff8383d65ff956a666b9bdbf2edfa4f1f900c858098',2),(114,115,'johnngariya64@gmail.com','6e0479f2201a7f7a5b90b977b6e4087e4060daa3457d739185b39324d584c0c1',2),(115,116,'francisletting94@gmail.com','d46c85c92811dd11259eacf987665f928f2ea920bbde0c636f09ecf4b60b4f69',2),(116,117,'erastusopicho05@gmail.com','f423a754d207664e617cdde22c4d09ec48cab5a82935c3487986c46cf5540ab3',2),(117,118,'aggreywaliaula25@gmail.com','1ad35c12debeef3b442066db25b69f2d74f4462aa529a9c9f588b64ef0a7ef29',2),(118,119,'benardmasanja45@gmail.com','a0e7b6004b60f247626b642cf7b94a6b41359edd45f55c5ba19043be6eeef562',2),(119,120,'kenaffreubenkirwa65@gmail.com','2d382b35c815945349cfbfcfc59eb0f949cb0d79180ab868e1e9dc93c0334018',2),(120,121,'petermwangi95@gmail.com','6eb1a326db4fad1a6a779666fd4919c4a4b4864b4eefc3c0fe4c9475179b2ba2',2),(121,122,'josephnzomo06@gmail.com','b37bd2514fbba01735258348b9a81c716be6611f7fbf7623f60cf3d3ed466915',2),(122,123,'josephndungu26@gmail.com','7cc04d092ff30910f6fae5b2f17af0bf07ba50f7ea0058fa6a74d4288f7c7083',2),(123,124,'shemkakaimatolo36@gmail.com','edf6bc1b6c6a9ef1224c786264561533b696c12c7f136ebe668b6dc563eb98b7',2),(124,125,'peterkimathimbae46@gmail.com','67543459d078bd4b7af1fb0eeb87e474cbaf2ca67f5727750073fe6f0e31b099',2),(125,126,'peterkibe66@gmail.com','f328a23a28d0785e7c24e3b5f005b9598346d5a1847dfacf57425e2827720144',2),(126,127,'jacksonkibetrutto86@gmail.com','9063999b8f98c543fe06f2c2fd25137bb184ea479dc654fd363438f25361da3b',2),(127,128,'mosessimiyumunialo07@gmail.com','396701b2e708eb9652ae5414a614e536f1aa76070cfb5b7e0d3ea85f9e35cded',2),(128,129,'philemonkipchumba27@gmail.com','baa837136093a6bb5330a63d2fad513edcdd3e0b58a4e9e95e0d31dc4f40bd0d',2),(129,130,'stanleykipkorirrotich47@gmail.com','da59dd904d4c9758f9f943b1d2c3919ec84a2a332f4879edc59dea957631d51f',2),(130,131,'elizabethengendothuo67@gmail.com','53aeffbd3b972bb0b3d97c7dc712d19ec217430abacc4456c0dea5fea8c1d582',2),(131,132,'stephenkirungu97@gmail.com','3e13854834c119a89418c30de89d7c9f657a99ba626031548815fcca50b6f3bf',2),(132,133,'seraphirejullyoulepu08@gmail.com','9b382db3cb8b070a1b54e646ce6dfa441a0d1d95124269393d9ad044e3759921',2),(133,134,'josephmurathi28@gmail.com','5c8f6f282a6f43191cd55823a1b8522aaf8433893714778e8eeb139653adc599',2),(134,135,'charleskamauchege48@gmail.com','8ec38d4e77aab5f302fc37cf2ea1e47b3f8da28b10bc60333751f8d4ec21aab8',2),(135,136,'gerishonkamau68@gmail.com','226fb129f719011e2963be98ea27f6831197ebf6c75a06278a7d63342bae4c26',2),(136,137,'annwambuikimani98@gmail.com','880f1bfe3f08940ad3ac503bdcbc10ffd39d16984832defd1e4a0b67b9480d3e',2),(137,138,'franciskahiu09@gmail.com','dfe984a8f65dce708c23ccd9b6677a7b0ab01b0041b8b22182b31a8ad930dd75',2),(138,139,'desmondkiplangatchumo29@gmail.com','d2397d03fcf4eabd29f07730acf75f7d386c58f11df058bd8ab5116dd89a2690',2),(139,140,'charlesbiwott39@gmail.com','9db8b5ed56b189184989382780bd4483c52a3462b508428e628ebf6af53efd4c',2),(140,141,'geoffreyirangi59@gmail.com','2bca645e90b8fc9920b2bb5db590d97a58da9c83f33bc460871fcd89211f39c8',2),(141,142,'g.kngaruiya79@gmail.com','e297144514e4f2a0a16a410829791dbe65e3b016ac20a2498f92b94076101812',2),(142,143,'marywanjohi99@gmail.com','0a5610b0d11195efb0df19cc08cdc72269573ca8fa26dc539cc8410e0b96de55',2),(143,144,'marykahingo10@gmail.com','76da2c461a6bca04967e4ee6ee04de6a558370c25fd83ad26d72b8fa15e1adac',2),(144,145,'marykahingo40@gmail.com','1a56d56adcbcc6806a1babd3472d85361b28276439587bc3216fc18fab8488f1',2),(145,146,'janemuthonimburu60@gmail.com','16d1533b572cb690e3a7c9379567d14c9475a50af15677152361bc2f85869408',2),(146,147,'patrickmugo80@gmail.com','5627ac0759b7d65fddabd4d00e3f7d84000b16d3cde77dbca937cbf8c586fbb7',2),(147,148,'immanuelsalaonslempaa01@gmail.com','9274bb658eb6553123fc689be5a8a1109fc0ae95fb2b2e2e8d4229f11f2e4874',2),(148,149,'gladwellwaithera21@gmail.com','5de067bcec64ea22a3575238e58521ffd95c9ffc37b6d96d8cfbf30ad6ee9596',2),(149,150,'lempaaraphaelk.31@gmail.com','adca637f7eb5fc805a8264e3eec886a1b0f7696aae30dcfb6484be28b7d710a9',2),(150,151,'zacheosmonani41@gmail.com','c85000ae1aac893f741d5bf545f754a4bce4ec640d30d4c9b08d17f16fbf9d93',2),(151,152,'marykemunto61@gmail.com','a6ce4a853c5a94b2f3622165918e17876eb7e289a5c6daff4cf436359a54a990',2),(152,153,'annwanjiruwakaba91@gmail.com','b1f22540031cccd1ad94a201b2c74a9529726002e70f648bf6551232260b1b35',2),(153,154,'stephenkiptoolelei12@gmail.com','26936656736d01c3bf1e3066ad7134b632b9d160aab6ce3a7462bc55e62f0b82',2),(154,155,'bensonkariuki32@gmail.com','3fbbad949dc4e07eab9ac71e148c92d2bea1ee25832ccd5f94dba1bbc85b2b31',2),(155,156,'johnn.mwangi52@gmail.com','d8dbe0cff31854473361cba0e418277c655328226b21170a7376e3808f68a7a9',2);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validation_workshops`
--

DROP TABLE IF EXISTS `validation_workshops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `validation_workshops` (
  `id` int(11) NOT NULL,
  `no_in_western_region` int(11) DEFAULT NULL,
  `no_in_eastern_region` int(11) DEFAULT NULL,
  `attendance_sheet` text,
  `kalro_officer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_validation_workshops_person1` (`kalro_officer`),
  CONSTRAINT `fk_validation_workshops_person1` FOREIGN KEY (`kalro_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validation_workshops`
--

LOCK TABLES `validation_workshops` WRITE;
/*!40000 ALTER TABLE `validation_workshops` DISABLE KEYS */;
/*!40000 ALTER TABLE `validation_workshops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `village`
--

DROP TABLE IF EXISTS `village`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `village` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `ward` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_village_ward1` (`ward`),
  CONSTRAINT `fk_village_ward1` FOREIGN KEY (`ward`) REFERENCES `ward` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `village`
--

LOCK TABLES `village` WRITE;
/*!40000 ALTER TABLE `village` DISABLE KEYS */;
/*!40000 ALTER TABLE `village` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ward` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `sub_county` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_ward_sub_county1` (`sub_county`),
  CONSTRAINT `fk_ward_sub_county1` FOREIGN KEY (`sub_county`) REFERENCES `sub_county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
INSERT INTO `ward` VALUES (1,'Mauche',7),(2,'Kihingo',7),(3,'Nesuit',7),(4,'Lare',7),(5,'Njoro',7),(6,'Mau Narok',7),(7,'Elburgon',8),(8,'Turi',8),(9,'Mariashoni',8),(10,'Molo',8),(11,'Ngechek/Lelmoko',9),(12,'Kaptel/Kamoiywo',9),(13,'Chemundu',9),(14,'Kosikai',9),(15,'Kiptuiya',9),(16,'Kabisaga',10),(17,'Chepterwai',10),(18,'Kurgung/Surungai',10),(19,'Kabiyet',10),(20,'Ndalat',10),(21,'Sangaloi/Kebolonik',10),(22,'Sinoko',11),(23,'Nzoia',11),(24,'Likuyani',11),(25,'Sango',11),(26,'Kongoni',11),(27,'Lugari',12),(28,'Lumakanda',12),(29,'Muatuma',12),(30,'Chekalini',12),(31,'Lwandeti/Chevaywa',12),(32,'Kwanza',13),(33,'Keiyo',13),(34,'Bidii',13),(35,'Kapomboi',13),(36,'Motosiet',14),(37,'Cherangani Suwerwa',14),(38,'Malakisi',15),(39,'Lwandanyi',15),(40,'Namwela',15),(41,'Soysambu',16),(42,'Milima',16),(43,'Mbakalo',16),(44,'Naitiri',16),(45,'Ndalu',16),(46,'Kiminini/Torangen',16),(47,'Nthawa',1),(48,'Muminji',1),(49,'Evurore',1),(50,'Mwea',2),(51,'Makima',2),(52,'Mavuria',2),(53,'Mbeti South',2),(54,'Kiambere',2),(55,'Mukothima',3),(56,'Gatunga',3),(57,'Marimanti',4),(58,'Nkondi',4),(59,'Ciakariga',4),(60,'Mwingi Central',5),(61,'Waita',5),(62,'Kivou',5),(63,'Nguni',5),(64,'Nuu',5),(65,'Mui',5),(66,'Muumoni',6),(67,'Tseikuru',6),(68,'Ngomeni',6),(69,'Kyuso',6),(70,'Tharaka',6),(71,'Kipkaren',10),(72,'Kapsabet',17),(73,'Kilibwoni',17),(74,'Chepkumia',17),(75,'Kapkangani',17),(76,'Chepsiro/Kiptoror',14),(77,'Sitatunga',14),(78,'Kaplamai',14),(79,'Sinyerere',14),(80,'Makutano',14);
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `warehouse_type` smallint(5) unsigned NOT NULL DEFAULT '1',
  `warehouse_operator` int(10) unsigned DEFAULT NULL COMMENT 'Person id is the operator\n',
  `capacity` int(11) DEFAULT NULL,
  `units` smallint(5) unsigned DEFAULT NULL,
  `offers_wrs` tinyint(1) DEFAULT NULL,
  `certified` tinyint(1) DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `warehouse_operator_UNIQUE` (`warehouse_operator`),
  KEY `fk_warehouse_units1` (`units`),
  KEY `fk_warehouse_location1` (`location`),
  KEY `fk_warehouse_warehouse_type1` (`warehouse_type`),
  CONSTRAINT `fk_warehouse_location1` FOREIGN KEY (`location`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_person1` FOREIGN KEY (`warehouse_operator`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_units1` FOREIGN KEY (`units`) REFERENCES `measurement_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_warehouse_type1` FOREIGN KEY (`warehouse_type`) REFERENCES `warehouse_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse_type`
--

DROP TABLE IF EXISTS `warehouse_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse_type` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse_type`
--

LOCK TABLES `warehouse_type` WRITE;
/*!40000 ALTER TABLE `warehouse_type` DISABLE KEYS */;
INSERT INTO `warehouse_type` VALUES (1,'Warehouse'),(2,'Collection Centre');
/*!40000 ALTER TABLE `warehouse_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-04  9:19:40
