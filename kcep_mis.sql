-- MySQL dump 10.13  Distrib 5.7.12, for Linux (x86_64)
--
-- Host: localhost    Database: kcep_mis
-- ------------------------------------------------------
-- Server version	5.7.12-0ubuntu1

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
-- Table structure for table `collection_centre`
--

DROP TABLE IF EXISTS `collection_centre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collection_centre` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `operational` tinyint(1) DEFAULT NULL,
  `ward_extension_officer` int(10) unsigned NOT NULL,
  `location` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_collection_centre_person1` (`ward_extension_officer`),
  KEY `fk_collection_centre_location1` (`location`),
  CONSTRAINT `fk_collection_centre_location1` FOREIGN KEY (`location`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_collection_centre_person1` FOREIGN KEY (`ward_extension_officer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='The total number of collection centres should be reported by the Ward Extension Officer(WAO). \nTherefore, this total count shall be calculated by the system.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection_centre`
--

LOCK TABLES `collection_centre` WRITE;
/*!40000 ALTER TABLE `collection_centre` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection_centre` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'0701404084',NULL,'203  Bomet'),(2,'0701404085','siele.bernard@gmail.com','203'),(3,'','',''),(4,'0701404083','siele.bernard@gmail.com','203 Eldoret'),(5,'0701404083','siele.bernarrd@gmail.com','203 Eldoret'),(6,'0701404012','siele.bernard@gmaol.com','203 Marsabit');
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
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `county`
--

LOCK TABLES `county` WRITE;
/*!40000 ALTER TABLE `county` DISABLE KEYS */;
INSERT INTO `county` VALUES (1,'Baringo'),(2,'Bomet'),(3,'Bungoma'),(4,'Busia'),(5,'Elgeyo/Marakwet'),(6,'Embu'),(7,'Garissa'),(8,'Homa Bay'),(9,'Isiolo'),(10,'Kajiado'),(11,'Kakamega'),(12,'Kericho'),(13,'Kiambu'),(14,'Kilifi'),(15,'Kirinyaga'),(16,'Kisii'),(17,'Kisumu'),(18,'Kitui'),(19,'Kwale'),(20,'Laikipia'),(21,'Lamu'),(22,'Machakos'),(23,'Makueni'),(24,'Mandera'),(25,'Marsabit'),(26,'Meru'),(27,'Migori'),(28,'Mombasa'),(29,'Muranga'),(30,'Nairobi'),(31,'Nakuru'),(32,'Nandi'),(33,'Narok'),(34,'Nyamira'),(35,'Nyandarua'),(36,'Nyeri'),(37,'Samburu'),(38,'Siaya'),(39,'Taita Taveta'),(40,'Tana River'),(41,'Tharaka-Nithi'),(42,'Trans Nzoia'),(43,'Turkana'),(44,'Uasin Gishu'),(45,'Vihiga'),(46,'Wajir'),(47,'West Pokot');
/*!40000 ALTER TABLE `county` ENABLE KEYS */;
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
  `input_type` int(10) unsigned DEFAULT NULL,
  `person` int(10) unsigned DEFAULT NULL,
  `date_redeemed` date DEFAULT NULL,
  `inputs_logbook_page` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_input_input_type1` (`input_type`),
  KEY `fk_input_person1` (`person`),
  CONSTRAINT `fk_input_input_type1` FOREIGN KEY (`input_type`) REFERENCES `input_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_input_person1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_voucher`
--

LOCK TABLES `e_voucher` WRITE;
/*!40000 ALTER TABLE `e_voucher` DISABLE KEYS */;
INSERT INTO `e_voucher` VALUES (1,'20000',2,3,'2016-06-14','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/checkbox.isSelected.png'),(2,'20000',2,3,'2016-06-14','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/checkbox.isSelected.png'),(3,'10000',3,6,'2016-06-13','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/Menu_001.png'),(4,'',1,1,NULL,NULL),(5,'5000',4,6,'2016-06-14','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/what age brackets.png'),(6,'5000',4,6,'2016-06-14','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/what age brackets.png'),(7,'',1,1,NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/null'),(8,'5000000',4,5,'2016-06-14','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/ethcable03.gif'),(9,'',1,1,NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/null'),(10,'',1,1,NULL,NULL),(11,'',1,1,NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/null'),(12,'',1,1,NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/null'),(13,'',1,1,NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/null'),(14,'685320',1,1,'2016-06-14','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/ethcable568b.gif'),(15,'85444',3,3,'2016-06-14','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/eVoucher/inputs_logbook_pages/DownloadFileServlet.java');
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
-- Table structure for table `input_type`
--

DROP TABLE IF EXISTS `input_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `input_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
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
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `county` int(10) unsigned DEFAULT NULL,
  `sub_county` varchar(45) DEFAULT NULL,
  `ward` varchar(45) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(9,7) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_location_county1` (`county`),
  CONSTRAINT `fk_location_county1` FOREIGN KEY (`county`) REFERENCES `county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,2,'Bomet central','Ndarawetta',NULL,NULL),(2,30,'','Madaraka',NULL,NULL),(3,1,'','',NULL,NULL),(4,32,'Eldoret','',NULL,NULL),(5,32,'Eldoret','',NULL,NULL),(6,25,'Mandera','Mandera',NULL,NULL),(7,1,'','',NULL,NULL),(8,1,'','',NULL,NULL),(9,1,'','',NULL,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measurement_unit`
--

DROP TABLE IF EXISTS `measurement_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measurement_unit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `unit` varchar(45) NOT NULL,
  `symbol` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `unit_UNIQUE` (`unit`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measurement_unit`
--

LOCK TABLES `measurement_unit` WRITE;
/*!40000 ALTER TABLE `measurement_unit` DISABLE KEYS */;
INSERT INTO `measurement_unit` VALUES (1,'Kilograms','Kg'),(2,'Tonnes','T'),(3,'Bags',NULL);
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
  `location` int(10) unsigned DEFAULT NULL,
  `contact` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `national_id_UNIQUE` (`national_id`),
  KEY `fk_person_farmer_group1` (`farmer_group`),
  KEY `fk_person_location1` (`location`),
  KEY `fk_person_contact2` (`contact`),
  KEY `fk_person_sex1` (`sex`),
  CONSTRAINT `fk_person_contact2` FOREIGN KEY (`contact`) REFERENCES `contact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_farmer_group1` FOREIGN KEY (`farmer_group`) REFERENCES `farmer_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_location1` FOREIGN KEY (`location`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_sex1` FOREIGN KEY (`sex`) REFERENCES `sex` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Ben',2,'29820457',NULL,'Millet growin',NULL,1,1),(2,'Ben10',2,'29820458','2016-06-09','Millet growing',NULL,2,2),(3,'Ben11',1,'',NULL,'',NULL,3,3),(4,'Ben12',2,'29820451','2016-06-09','Millet growing',NULL,4,4),(5,'Ben12',2,'29820412','2016-06-09','Millet growing',NULL,5,5),(6,'Ben13',2,'29820111','2016-06-09','Millet growinh',NULL,6,6);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_role`
--

DROP TABLE IF EXISTS `person_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `person_role` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `person_role_UNIQUE` (`person_role`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_role`
--

LOCK TABLES `person_role` WRITE;
/*!40000 ALTER TABLE `person_role` DISABLE KEYS */;
INSERT INTO `person_role` VALUES (2,'Agro-dealer'),(5,'County Officer'),(1,'Farmer'),(8,'KALRO Officer'),(7,'National Office'),(6,'Regional Coordinator'),(4,'Sub-county Officer'),(9,'System Admin'),(3,'WAO (Ward Extension Officer)');
/*!40000 ALTER TABLE `person_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programme`
--

DROP TABLE IF EXISTS `programme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programme` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `activity` text,
  `start_period` varchar(45) DEFAULT NULL,
  `end_period` varchar(45) DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `awp_target` varchar(45) DEFAULT NULL,
  `programme_target` varchar(45) DEFAULT NULL,
  `value_achieved` varchar(45) DEFAULT NULL,
  `requested_budget` varchar(45) DEFAULT NULL,
  `actual_expenditure` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programme`
--

LOCK TABLES `programme` WRITE;
/*!40000 ALTER TABLE `programme` DISABLE KEYS */;
INSERT INTO `programme` VALUES (1,'','','','','','','','',''),(2,'','','','','','','','','');
/*!40000 ALTER TABLE `programme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item` varchar(45) DEFAULT NULL,
  `date_purchased` varchar(45) DEFAULT NULL,
  `serial_number` varchar(45) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `target_office` varchar(45) DEFAULT NULL,
  `county` int(10) unsigned DEFAULT NULL,
  `sub_county` varchar(45) DEFAULT NULL,
  `cost` varchar(45) DEFAULT NULL,
  `lpo_number` varchar(45) DEFAULT NULL,
  `invoice_and_receipts` varchar(300) DEFAULT NULL COMMENT 'Attachment\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_purchase_county1` (`county`),
  CONSTRAINT `fk_purchase_county1` FOREIGN KEY (`county`) REFERENCES `county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,'Sprinkler','06/12/2016','23509784','Has a capacity of 20 litres','Kwale county office',19,'','5000','322434q23','cmd basic commands.java'),(2,'Scarecrow','06/12/2016','21423421','','Isiolo county office',9,'','1200','564565','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/Viportal.xlsx'),(3,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(4,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(5,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(6,'','','','','',1,'','','',NULL),(7,'','','','','',1,'','','',NULL),(8,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(9,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(10,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(11,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(12,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(13,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(14,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(15,'','','','','',1,'','','','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/null'),(16,'','','','','',1,'','','',NULL),(17,'','','','','',1,'','','',NULL),(18,'','','','','',1,'','','',NULL),(19,'','','','','',1,'','','',NULL),(20,'','','','','',1,'','','',NULL),(21,'','','','','',1,'','','',NULL),(22,'','','','','',1,'','','',NULL),(23,'','','','','',1,'','','',NULL),(24,'','','','','',1,'','','',NULL),(25,'Scarecrow','06/12/2016','','','',1,'','1200','',NULL),(26,'Scarecrow','06/12/2016','21423421','','Isiolo county office',9,'','1200','564565',NULL),(27,'Scarecrow','06/12/2016','21423421','','Isiolo county office',9,'','1200','564565','/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis//documents/purchase/invoice_and_receipts/IMG_5042.jpg'),(28,'','','','','',1,'','','',NULL);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `static_input`
--

LOCK TABLES `static_input` WRITE;
/*!40000 ALTER TABLE `static_input` DISABLE KEYS */;
INSERT INTO `static_input` VALUES (2,'Beans'),(4,'Green grams'),(1,'Maize'),(6,'Millet'),(3,'Pigeon peas'),(7,'Planting'),(5,'Sorghum'),(8,'Top-dressing');
/*!40000 ALTER TABLE `static_input` ENABLE KEYS */;
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
  `trainer` int(10) unsigned DEFAULT NULL,
  `topic` varchar(200) DEFAULT NULL,
  `venue` varchar(200) DEFAULT NULL,
  `number_of_trainees` int(11) DEFAULT NULL,
  `attendance_sheet` varchar(200) DEFAULT NULL COMMENT 'Location of attachment\n',
  `category_of_trainees` int(10) unsigned DEFAULT NULL COMMENT 'Category of trainees eg farmers',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_training_person1` (`trainer`),
  KEY `fk_training_person_role1` (`category_of_trainees`),
  CONSTRAINT `fk_training_person1` FOREIGN KEY (`trainer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_training_person_role1` FOREIGN KEY (`category_of_trainees`) REFERENCES `person_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training`
--

LOCK TABLES `training` WRITE;
/*!40000 ALTER TABLE `training` DISABLE KEYS */;
INSERT INTO `training` VALUES (1,'2016-06-09','2016-06-09',1,'Technology','Class',20,'uon.JPG',1),(2,'2016-06-10','2016-06-10',4,'How to spray crops','Weka warehouse',30,'Christina Shusho - Ee Bwana Umenichunguza Lyrics.pdf',1),(3,'2016-06-11','2016-06-11',2,'How to weed maize','Samburu central kcep farm',17,'docs/attendance_sheets/Christina Shusho - Ee Bwana Umenichunguza Lyrics.pdf/Christina Shusho - Ee Bwana Umenichunguza Lyrics.pdf',1),(4,'2016-06-11','2016-06-11',1,'How to weed maize','Bomet central kcep farm',49,'docs/attendance_sheets/Payment .ods',1),(5,NULL,NULL,1,'','',NULL,'docs/training/attendance_sheets/',2),(6,NULL,NULL,1,'','',NULL,'docs/training/attendance_sheets/',2),(7,NULL,NULL,1,'','',NULL,'docs/training/attendance_sheets/',2),(8,NULL,NULL,1,'','',NULL,NULL,2),(9,NULL,NULL,1,'','',NULL,NULL,2),(10,NULL,NULL,1,'','',NULL,NULL,2),(11,NULL,NULL,1,'','',NULL,NULL,2),(12,NULL,NULL,1,'','',NULL,NULL,2),(13,NULL,NULL,1,'','',NULL,NULL,2),(14,NULL,NULL,1,'','',NULL,NULL,2),(15,NULL,NULL,1,'','',NULL,NULL,2),(16,NULL,NULL,1,'','',NULL,NULL,2),(17,'2016-06-11','2016-06-11',4,'How to weed maize','Nandi central kcep farm',21,NULL,1),(18,'2016-06-11','2016-06-11',4,'How to weed maize','Nandi central kcep farm',21,NULL,1),(19,'2016-06-11','2016-06-11',4,'How to weed maize','Nandi central kcep farm',21,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/training/attendance_sheets/pom.xml',1),(20,'2016-06-11','2016-06-11',4,'How to weed maize','Nandi central kcep farm',21,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/training/attendance_sheets/kcep_mis.sql',1),(21,'2016-06-11','2016-06-11',4,'How to weed maize','Nandi central kcep farm',21,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/training/attendance_sheets/cmd.bat',1),(22,'2016-06-12','2016-06-12',6,'How to harvest millet','Samburu county kcep farm',97,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/training/attendance_sheets/Info.txt',1),(23,NULL,NULL,1,'','',NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/training/attendance_sheets/null',2),(24,NULL,NULL,1,'','',NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/training/attendance_sheets/null',2),(25,NULL,NULL,1,'','',NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/training/attendance_sheets/null',2),(26,NULL,NULL,1,'','',NULL,'/home/siech/Workshop/Java/Projects/kcep-mis/target/kcep-mis/documents/training/attendance_sheets/null',2);
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
  `person_role` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_account_person1` (`person`),
  KEY `fk_user_account_person_role1` (`person_role`),
  CONSTRAINT `fk_user_account_person1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_account_person_role1` FOREIGN KEY (`person_role`) REFERENCES `person_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,6,'siele.bernard@gmaol.com','185a7fa3f99c9d116bc992a24d5b592b06c7e406e06054b0ea6d475faa909762',9);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
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
  `warehouse_operator` int(10) unsigned DEFAULT NULL COMMENT 'Person id is the operator\n',
  `capacity` int(11) DEFAULT NULL,
  `units` int(10) unsigned DEFAULT NULL,
  `offers_wrs` tinyint(1) DEFAULT NULL,
  `certified` tinyint(1) DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `warehouse_operator_UNIQUE` (`warehouse_operator`),
  KEY `fk_warehouse_units1` (`units`),
  KEY `fk_warehouse_location1` (`location`),
  CONSTRAINT `fk_warehouse_location1` FOREIGN KEY (`location`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_person1` FOREIGN KEY (`warehouse_operator`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_units1` FOREIGN KEY (`units`) REFERENCES `measurement_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'',1,NULL,1,1,1,7),(2,'',4,NULL,1,1,1,8),(3,'',5,NULL,1,1,1,9);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-14 20:16:02
