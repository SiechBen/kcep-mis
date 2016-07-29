-- MySQL dump 10.13  Distrib 5.7.13, for Linux (x86_64)
--
-- Host: localhost    Database: kcep_mis
-- ------------------------------------------------------
-- Server version	5.7.13-0ubuntu0.16.04.2

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
  `ebl_branch` smallint(5) unsigned NOT NULL,
  `sol_id` varchar(45) DEFAULT NULL,
  `farmer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_account_details_person1` (`farmer`),
  KEY `fk_account_ebl_branch1` (`ebl_branch`),
  CONSTRAINT `fk_account_details_person1` FOREIGN KEY (`farmer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_ebl_branch1` FOREIGN KEY (`ebl_branch`) REFERENCES `ebl_branch` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'35453345',1,'1234',6),(2,'23432424',1,'324',12);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_name`
--

DROP TABLE IF EXISTS `activity_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_name` (
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_name`
--

LOCK TABLES `activity_name` WRITE;
/*!40000 ALTER TABLE `activity_name` DISABLE KEYS */;
INSERT INTO `activity_name` VALUES (1,'Community mobilization and selection of beneficiaries'),(2,'Identification and selection of agrodealers(Eastern Region)');
/*!40000 ALTER TABLE `activity_name` ENABLE KEYS */;
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
-- Table structure for table `annual_indicator`
--

DROP TABLE IF EXISTS `annual_indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `annual_indicator` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `performance_indicator` smallint(3) unsigned NOT NULL,
  `sub_activity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_performance_indicator_has_sub_activity_performance_indicat1` (`performance_indicator`),
  KEY `fk_performance_indicator_has_sub_activity_sub_activity1` (`sub_activity`),
  CONSTRAINT `fk_performance_indicator_has_sub_activity_performance_indicat1` FOREIGN KEY (`performance_indicator`) REFERENCES `performance_indicator` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_performance_indicator_has_sub_activity_sub_activity1` FOREIGN KEY (`sub_activity`) REFERENCES `sub_activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annual_indicator`
--

LOCK TABLES `annual_indicator` WRITE;
/*!40000 ALTER TABLE `annual_indicator` DISABLE KEYS */;
INSERT INTO `annual_indicator` VALUES (1,1,1),(2,2,1),(3,69,2),(4,66,2),(5,1,3),(6,2,3),(7,3,3);
/*!40000 ALTER TABLE `annual_indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `component` (
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
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
  `email` varchar(150) DEFAULT NULL,
  `postal_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'0701404084','siele.bernard@gmail.com','203 Bomet'),(2,'0701404084','agrodealer@gmail.com','203 Bomet'),(3,'0701404086','countyofficer@gmail.com','203 Bomet'),(4,'0701404087','equity@gmail.com','203 Bomet'),(5,'0701404088','farmer@gmail.com','203 Bomet'),(6,'0701404089','kalroofficer@gmail.com','203 Bomet'),(7,'0701404081','nationalofficer@gmail.com','202 Bomet'),(8,'0701404082','regionalcoordinator@gmail.com','203 Bomet'),(9,'0701404080','subcounty@gmail.com','203 Bomet'),(10,'0701404041','warehouseoperator@gmail.com','203 Bomet'),(11,'0701404086','wao@gmail.com','203 Bomet'),(12,'0725287162','ss@anynomous.ac.ke','203 Muminji');
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
  `region` smallint(2) unsigned NOT NULL,
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
-- Table structure for table `divisional_location`
--

DROP TABLE IF EXISTS `divisional_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `divisional_location` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `divisional_location`
--

LOCK TABLES `divisional_location` WRITE;
/*!40000 ALTER TABLE `divisional_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `divisional_location` ENABLE KEYS */;
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
-- Table structure for table `ebl_branch`
--

DROP TABLE IF EXISTS `ebl_branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ebl_branch` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ebl_branch`
--

LOCK TABLES `ebl_branch` WRITE;
/*!40000 ALTER TABLE `ebl_branch` DISABLE KEYS */;
INSERT INTO `ebl_branch` VALUES (1,'Nakuru Kenyatta Avenue');
/*!40000 ALTER TABLE `ebl_branch` ENABLE KEYS */;
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
-- Table structure for table `expenditure_category`
--

DROP TABLE IF EXISTS `expenditure_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expenditure_category` (
  `id` smallint(2) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenditure_category`
--

LOCK TABLES `expenditure_category` WRITE;
/*!40000 ALTER TABLE `expenditure_category` DISABLE KEYS */;
INSERT INTO `expenditure_category` VALUES (4,'Consultancies'),(3,'Equipments & Materials'),(6,'Grants & Subsidies'),(8,'Operating Costs'),(7,'Salaries & Allowances'),(5,'Trainings'),(2,'Vehicles'),(1,'Works');
/*!40000 ALTER TABLE `expenditure_category` ENABLE KEYS */;
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
-- Table structure for table `farm_activity`
--

DROP TABLE IF EXISTS `farm_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `farm_activity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `yield` double DEFAULT NULL,
  `date_done` date DEFAULT NULL,
  `quantity_sold` double DEFAULT NULL,
  `quantity_harvested` double DEFAULT NULL,
  `average_selling_price` decimal(16,2) DEFAULT NULL,
  `farmer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_farm_activity_person1` (`farmer`),
  CONSTRAINT `fk_farm_activity_person1` FOREIGN KEY (`farmer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `financial_year`
--

DROP TABLE IF EXISTS `financial_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `financial_year` (
  `id` smallint(3) unsigned NOT NULL AUTO_INCREMENT,
  `financial_year` varchar(45) NOT NULL,
  `current_year` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `financial_year_UNIQUE` (`financial_year`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financial_year`
--

LOCK TABLES `financial_year` WRITE;
/*!40000 ALTER TABLE `financial_year` DISABLE KEYS */;
INSERT INTO `financial_year` VALUES (1,'2015-2016',0),(2,'2016-2017',1),(3,'2017-2018',0),(4,'2019-2020',0);
/*!40000 ALTER TABLE `financial_year` ENABLE KEYS */;
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
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `input_type`
--

LOCK TABLES `input_type` WRITE;
/*!40000 ALTER TABLE `input_type` DISABLE KEYS */;
INSERT INTO `input_type` VALUES (1,'Seeds'),(2,'Fertiliser'),(3,'Tarpaulin'),(4,'Hermetic bags');
/*!40000 ALTER TABLE `input_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inputs_collection`
--

DROP TABLE IF EXISTS `inputs_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inputs_collection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date_collected` date DEFAULT NULL,
  `agro_dealer` int(10) unsigned NOT NULL,
  `input_type` smallint(5) unsigned DEFAULT NULL,
  `static_input` int(10) unsigned DEFAULT NULL,
  `quantity` varchar(45) DEFAULT NULL,
  `farmer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_inputs_collection_person1` (`agro_dealer`),
  KEY `fk_inputs_collection_input_type1` (`input_type`),
  KEY `fk_inputs_collection_person2` (`farmer`),
  KEY `fk_inputs_collection_static_input1` (`static_input`),
  CONSTRAINT `fk_inputs_collection_input_type1` FOREIGN KEY (`input_type`) REFERENCES `input_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_person1` FOREIGN KEY (`agro_dealer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_person2` FOREIGN KEY (`farmer`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_static_input1` FOREIGN KEY (`static_input`) REFERENCES `static_input` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `amount` decimal(16,2) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `account` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_loan_account1` (`account`),
  CONSTRAINT `fk_loan_account1` FOREIGN KEY (`account`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `sub_county` smallint(5) unsigned DEFAULT NULL,
  `ward` smallint(5) unsigned DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(9,7) DEFAULT NULL,
  `village` mediumint(8) unsigned DEFAULT NULL,
  `divisional_location` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_location_county1` (`county`),
  KEY `fk_location_sub_county1` (`sub_county`),
  KEY `fk_location_ward1` (`ward`),
  KEY `fk_location_village1` (`village`),
  KEY `fk_location_divisional_location1` (`divisional_location`),
  CONSTRAINT `fk_location_county1` FOREIGN KEY (`county`) REFERENCES `county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_divisional_location1` FOREIGN KEY (`divisional_location`) REFERENCES `divisional_location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_sub_county1` FOREIGN KEY (`sub_county`) REFERENCES `sub_county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_village1` FOREIGN KEY (`village`) REFERENCES `village` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_ward1` FOREIGN KEY (`ward`) REFERENCES `ward` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,5,8,7,35.8035000,0.2983000,NULL,NULL),(3,1,1,1,NULL,NULL,NULL,NULL),(4,1,15,19,NULL,NULL,NULL,NULL),(5,1,1,1,NULL,NULL,NULL,NULL),(6,1,1,6,NULL,NULL,NULL,NULL),(7,1,1,7,NULL,NULL,NULL,NULL),(8,1,1,7,NULL,NULL,NULL,NULL),(9,1,9,1,NULL,NULL,NULL,NULL),(10,1,4,5,NULL,NULL,NULL,NULL),(11,6,7,6,NULL,NULL,NULL,NULL),(12,3,3,6,NULL,NULL,NULL,NULL),(13,3,3,3,NULL,NULL,NULL,NULL),(14,1,1,48,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measurement_unit`
--

DROP TABLE IF EXISTS `measurement_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measurement_unit` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `unit` varchar(45) NOT NULL,
  `symbol` varchar(20) DEFAULT NULL,
  `use` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `unit_UNIQUE` (`unit`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measurement_unit`
--

LOCK TABLES `measurement_unit` WRITE;
/*!40000 ALTER TABLE `measurement_unit` DISABLE KEYS */;
INSERT INTO `measurement_unit` VALUES (1,'Kilograms','Kg',NULL),(2,'Tonnes','T',NULL),(3,'90 kg Bags',NULL,NULL),(4,'Number',NULL,'Programme'),(5,'Meeting',NULL,'Programme'),(6,'Lumpsum',NULL,'Programme'),(7,'Sites',NULL,NULL),(8,'Plots',NULL,NULL),(9,'Field visits',NULL,NULL),(10,'Trainings',NULL,NULL),(11,'Workshops',NULL,NULL),(12,'Warehouses',NULL,NULL),(13,'Certifications',NULL,NULL),(14,'Meetings','',NULL),(15,'Kits','',NULL),(16,'Group visits',NULL,NULL),(17,'Studies',NULL,NULL),(18,'Works',NULL,NULL);
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
  `id` smallint(3) unsigned NOT NULL AUTO_INCREMENT,
  `performance_indicator_type` smallint(2) unsigned DEFAULT NULL,
  `result_hierarchy` smallint(3) unsigned NOT NULL,
  `description` varchar(400) NOT NULL,
  `baseline_date` date DEFAULT NULL,
  `baseline_value` double DEFAULT NULL,
  `year_of_use` smallint(6) DEFAULT NULL,
  `actual_value` double DEFAULT NULL,
  `expected_value` double DEFAULT NULL,
  `ratio` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_performance_indicator_performance_indicator_type1` (`performance_indicator_type`),
  KEY `fk_performance_indicator_result_hierarchy1` (`result_hierarchy`),
  CONSTRAINT `fk_performance_indicator_performance_indicator_type1` FOREIGN KEY (`performance_indicator_type`) REFERENCES `performance_indicator_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_performance_indicator_result_hierarchy1` FOREIGN KEY (`result_hierarchy`) REFERENCES `result_hierarchy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_indicator`
--

LOCK TABLES `performance_indicator` WRITE;
/*!40000 ALTER TABLE `performance_indicator` DISABLE KEYS */;
INSERT INTO `performance_indicator` VALUES (1,NULL,1,'a. Uplifting of at least 32,000 households from poverty',NULL,NULL,NULL,NULL,NULL,NULL),(2,2,1,'b. Reduced poverty (NIMES)',NULL,NULL,NULL,NULL,NULL,NULL),(3,1,1,'c. Number of households with improvement in assets ownership index (RIMS3)',NULL,NULL,NULL,NULL,NULL,NULL),(4,1,1,'d. Percentage reduction in the prevalence of child malnutrition (RIMS3)1',NULL,NULL,NULL,NULL,NULL,NULL),(5,1,1,'e. HH experiencing one hungry season per year (RIMS3)2',NULL,NULL,NULL,NULL,NULL,NULL),(6,2,1,'f. Reduced hunger (NIMES)3',NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,2,'a. National grain deficit reduced by 41,000T/ 10% national deficit',NULL,NULL,NULL,NULL,NULL,NULL),(13,1,2,'b. Number targeted households using improved inputs voluntarily (min 32,000HH), (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(14,1,2,'c.  Number targeted small farmers in commercial contracts (min 55,000), (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(15,1,2,'d. Number targeted smallholders engaged in financial services (i.e taking investment loans), RIMS2',NULL,NULL,NULL,NULL,NULL,NULL),(16,1,2,'e. Number of targeted households bulking and selling grains at price 30% higher than farmgate (min 80,000), (RIMS 2)',NULL,NULL,NULL,NULL,NULL,NULL),(17,NULL,2,'f. Number of women and youth empowered',NULL,NULL,NULL,NULL,NULL,NULL),(25,1,4,'a. Improved agricultural production (RIMS2)\nTargets: \nMaize: 0.9 to 1.8 MT/acre\nSorghum;0.6 to 1.2 MT/acre\nMillet; 0.7 to 1.3 MT/acre\nPulses; 0.3 to 0.6 MT/acre',NULL,NULL,NULL,NULL,NULL,NULL),(26,NULL,4,'b. Improved practices/packages adopted by 62,000 HH(32,000 subsistence, 30,000 advanced, 50% women)',NULL,NULL,NULL,NULL,NULL,NULL),(27,1,4,'c. No of farmers using purchased inputs (RIMS 2)',NULL,NULL,NULL,NULL,NULL,NULL),(28,3,4,'d. No. of farmers adopting modern techniques (Embu, Bungoma, Nakuru and Kitui) (CIMES)',NULL,NULL,NULL,NULL,NULL,NULL),(29,1,4,'e. No / Percentage of farmers reporting production/ improved yields (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(30,1,4,'f. No of farmers adopting recommended technologies (RIMS 2)',NULL,NULL,NULL,NULL,NULL,NULL),(31,1,4,'g. No of groups functional after 3 years (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(32,NULL,5,'a. 40,000 HH subsistence farmers issued with input package',NULL,NULL,NULL,NULL,NULL,NULL),(33,NULL,5,'b. 300 agro-dealers trained',NULL,NULL,NULL,NULL,NULL,NULL),(34,1,5,'c. Staff of services providers trained (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(35,1,5,'d. No. of people trained in crop production practices and technologies (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(36,1,5,'e. No. of people accessing facilitated advisory services (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(40,NULL,6,'a. 1,333 farmer groups in organizational management trained',NULL,NULL,NULL,NULL,NULL,NULL),(41,1,6,'b. No. of crop production groups formed/ trained (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(42,NULL,6,'c. No. of crop production groups formed',NULL,NULL,NULL,NULL,NULL,NULL),(43,1,6,'d. No. of formed crop production groups with women in leadership positions (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(44,NULL,7,'a. Improved grain drying technologies adopted by 32,000 subsistence HH farmers',NULL,NULL,NULL,NULL,NULL,NULL),(45,NULL,7,'b. Improved grain storage technologies adopted by 32,000 subsistence HH farmers',NULL,NULL,NULL,NULL,NULL,NULL),(46,NULL,7,'c. Certified warehouses used by 80,000 HH (32,000 subsistence and 48,000 advanced)',NULL,NULL,NULL,NULL,NULL,NULL),(47,NULL,7,'d. Post-harvest grain losses reduced from 25 to 5% for 80,000 (32,000 subsistence and 48,000 advanced)',NULL,NULL,NULL,NULL,NULL,NULL),(48,NULL,7,'e. Operational self-sufficiency attained for 60 warehouses',NULL,NULL,NULL,NULL,NULL,NULL),(49,1,7,'f. Producers benefiting from improved access to markets (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(50,1,7,'g. No. of functioning infrastructure after 3 years (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(51,1,7,'h. No. of functioning storage & processing facilities after 3 years (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(52,NULL,8,'a. 40,000 subsistence farmers trained on post-harvest grain management',NULL,NULL,NULL,NULL,NULL,NULL),(53,1,8,'b. People trained on post-production and marketing (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(54,NULL,8,'c. 60 warehouses certified',NULL,NULL,NULL,NULL,NULL,NULL),(55,NULL,8,'d. 100 collection centres rehabilitated',NULL,NULL,NULL,NULL,NULL,NULL),(56,1,8,'e. No. or storage facilities constructed/ rehabilitated (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(57,1,8,'f. No. of processing facilities constructed/rehabilitated (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(58,3,8,'g. No. of storage facilities constructed(Embu, Nakuru and Kitui) (CIMES)',NULL,NULL,NULL,NULL,NULL,NULL),(59,NULL,9,'a. 100,000 HH beneficiaries trained in business partnership',NULL,NULL,NULL,NULL,NULL,NULL),(60,NULL,9,'b. 60 initiatives of commercial partnership implemented ',NULL,NULL,NULL,NULL,NULL,NULL),(61,NULL,9,'c. 20 access road improvement completed (spot road improvements)',NULL,NULL,NULL,NULL,NULL,NULL),(62,1,9,'d. Roads constructed/ rehabilitated (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(63,NULL,10,'a. 95,000 farmers producing grain and pulses have access to financial services',NULL,NULL,NULL,NULL,NULL,NULL),(64,1,10,'b. Improved access of the poor to financial services (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(65,NULL,10,'c. Number and type of new products implemented by EBL1',NULL,NULL,NULL,NULL,NULL,NULL),(66,1,10,'d. Improved performance of financial institutions (RIMS2)',NULL,NULL,NULL,NULL,NULL,NULL),(67,NULL,11,'a. 40,000 vouchers used by target groups',NULL,NULL,NULL,NULL,NULL,NULL),(68,NULL,11,'b. 62,500 HH using certified warehouse receipting system',NULL,NULL,NULL,NULL,NULL,NULL),(69,NULL,11,'c. 40,000 category 1 HH trained in financial literacy',NULL,NULL,NULL,NULL,NULL,NULL),(70,1,11,'d. People trained on financial services  (male, female, youth) (RIMS 1)',NULL,NULL,NULL,NULL,NULL,NULL),(71,NULL,11,'e. 200 entrepreneurs trained in advanced financial training',NULL,NULL,NULL,NULL,NULL,NULL),(72,1,11,'f. Bank staff trained (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(73,NULL,11,'g. No of farmers trained',NULL,NULL,NULL,NULL,NULL,NULL),(74,1,11,'h. Value of voluntary savings (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(75,1,11,'i. financial institutions participating in the project (RIMS 1)',NULL,NULL,NULL,NULL,NULL,NULL),(76,1,11,'j. No of active voluntary savers (RIMS1) (males, female, youth)',NULL,NULL,NULL,NULL,NULL,NULL),(77,1,11,'k. Number of active borrowers (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(78,1,11,'l. Value of gross loan portfolio by project beneficiaries (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(79,3,11,'m. No. / % increase in access to credit facilities (Bungoma, Kakamega and Nandi) (CIMES)',NULL,NULL,NULL,NULL,NULL,NULL),(80,1,11,'n. Total amount of savings by target group(by gender)-target to be defined at start up (RIMS1)',NULL,NULL,NULL,NULL,NULL,NULL),(81,NULL,12,'a. 100 value chain financing ventures completed',NULL,NULL,NULL,NULL,NULL,NULL),(82,NULL,12,'b. 80 forward contracts signed',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `performance_indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance_indicator_type`
--

DROP TABLE IF EXISTS `performance_indicator_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performance_indicator_type` (
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_indicator_type`
--

LOCK TABLES `performance_indicator_type` WRITE;
/*!40000 ALTER TABLE `performance_indicator_type` DISABLE KEYS */;
INSERT INTO `performance_indicator_type` VALUES (1,'RIMS'),(2,'NIMES'),(3,'CIMES');
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
  `age` varchar(45) DEFAULT NULL,
  `business_name` varchar(45) DEFAULT NULL,
  `farmer_group` int(10) unsigned DEFAULT NULL,
  `farmer_sub_group` int(10) unsigned DEFAULT NULL,
  `designation_in_group` int(10) unsigned DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  `contact` int(10) unsigned DEFAULT NULL,
  `plot_size` double DEFAULT NULL,
  `approved` tinyint(1) DEFAULT '0',
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Ben Siech',2,'29820457','1993-06-19',NULL,'Millet growing',NULL,NULL,NULL,1,1,NULL,0),(3,'Ben Siech',2,'29820458','1993-06-22',NULL,'Millet growing',NULL,NULL,NULL,12,3,NULL,0),(4,'Ben Siech',2,'29820459','1993-06-22',NULL,'Millet growing',NULL,NULL,NULL,3,4,NULL,0),(5,'Ben Siech',2,'29820460','1993-06-22',NULL,'Millet growing',NULL,NULL,NULL,4,5,NULL,0),(6,'Ben Siech',2,'29820461','1993-06-22',NULL,'',NULL,NULL,NULL,5,6,2,0),(7,'Ben Siech',2,'29820463','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,6,7,NULL,0),(8,'Ben Siech',2,'29820451','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,7,8,NULL,0),(9,'Ben Siech',1,'29820452','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,8,9,NULL,0),(10,'Ben Siech',2,'29820453','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,9,10,NULL,0),(11,'Ben Siech',2,'29820455','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,10,11,NULL,0),(12,'Ben Siech',2,'29820456','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,11,12,NULL,0),(14,'Bwana mkubwa',1,'29820420','2016-06-29',NULL,'Large scale millet growing',NULL,NULL,NULL,14,2,NULL,0);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_role`
--

DROP TABLE IF EXISTS `person_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_role` (
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
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
INSERT INTO `plan_vs_actual` VALUES (1,'Plan'),(2,'Actual'),(3,'Updated');
/*!40000 ALTER TABLE `plan_vs_actual` ENABLE KEYS */;
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
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
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
-- Table structure for table `response_pcu`
--

DROP TABLE IF EXISTS `response_pcu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `response_pcu` (
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response_pcu`
--

LOCK TABLES `response_pcu` WRITE;
/*!40000 ALTER TABLE `response_pcu` DISABLE KEYS */;
INSERT INTO `response_pcu` VALUES (1,'PC'),(2,'PO'),(3,'FSS'),(4,'VCDS'),(5,'PME'),(6,'CMTGO');
/*!40000 ALTER TABLE `response_pcu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_hierarchy`
--

DROP TABLE IF EXISTS `result_hierarchy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_hierarchy` (
  `id` smallint(3) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(400) DEFAULT NULL,
  `sub_component` smallint(2) unsigned DEFAULT NULL,
  `component` smallint(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_indicator_hierarchy_sub_component1` (`sub_component`),
  KEY `fk_indicator_hierarchy_component1` (`component`),
  CONSTRAINT `fk_indicator_hierarchy_component1` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_indicator_hierarchy_sub_component1` FOREIGN KEY (`sub_component`) REFERENCES `sub_component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_hierarchy`
--

LOCK TABLES `result_hierarchy` WRITE;
/*!40000 ALTER TABLE `result_hierarchy` DISABLE KEYS */;
INSERT INTO `result_hierarchy` VALUES (1,'Goal: National food security improved',NULL,NULL),(2,'Objective 1: Increased production of targeted cereal staples (maize, sorghum, millet and associated pulses)',NULL,NULL),(3,'Objective 2: Increased income of smallholders in medium and high potential production areas',NULL,NULL),(4,'Outcome 1: Outcome 1: Productivity for maize, sorghum, finger millet and pulses increased',NULL,NULL),(5,'Output 1.1; Agricultural services/ inputs improved',NULL,NULL),(6,'Output 1.2; Capacity of farmers’ organizations to mobilize extension services, participate in local planning and value chains fora improved',NULL,NULL),(7,'Outcome 2; Post harvest practices and market linkages for targeted VCs improved',NULL,NULL),(8,'Output 2.1; Post-harvest management improved',NULL,NULL),(9,'Output 2.2; Market Access for participating smallholders improved',NULL,NULL),(10,'Outcome 3; Financial inclusion of beneficiaries improved',NULL,NULL),(11,'Output 3.1 : Use of financial tools and services by target groups increased',NULL,NULL),(12,'Output 3.2: Access to value chain financing improved',NULL,NULL);
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
  `input_type` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_static_input_input_type1` (`input_type`),
  CONSTRAINT `fk_static_input_input_type1` FOREIGN KEY (`input_type`) REFERENCES `input_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `static_input`
--

LOCK TABLES `static_input` WRITE;
/*!40000 ALTER TABLE `static_input` DISABLE KEYS */;
INSERT INTO `static_input` VALUES (1,'Maize',1),(2,'Beans',1),(3,'Pigeon peas',1),(4,'Green grams',1),(5,'Sorghum',1),(6,'Millet',1),(7,'Planting',2),(8,'Top-dressing',2),(9,'Post-harvest',2);
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
  `expected_outcome` varchar(200) DEFAULT NULL,
  `annual_workplan_reference_code` varchar(20) DEFAULT NULL,
  `component` smallint(2) unsigned NOT NULL,
  `sub_component` smallint(2) unsigned DEFAULT NULL,
  `activity_name` smallint(2) unsigned NOT NULL,
  `sub_activity_name` smallint(2) unsigned NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `measurement_unit` smallint(5) unsigned NOT NULL,
  `unit_cost` decimal(16,2) DEFAULT NULL,
  `awpb_target` decimal(16,2) DEFAULT NULL,
  `programme_target` decimal(16,2) DEFAULT NULL,
  `totals` decimal(16,2) DEFAULT NULL,
  `response_pcu` smallint(2) unsigned NOT NULL,
  `implementing_partner` smallint(2) unsigned DEFAULT NULL,
  `procurement_plan` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `value_achieved` decimal(16,2) DEFAULT NULL,
  `allocated_budget` decimal(16,2) DEFAULT NULL,
  `expenditure_category` smallint(2) unsigned NOT NULL,
  `gok_percentage` double DEFAULT NULL,
  `ifad_loan_percentage` double DEFAULT NULL,
  `ifad_grant_percentage` double DEFAULT NULL,
  `beneficiaries_percentage` double DEFAULT NULL,
  `eu_percentage` double DEFAULT NULL,
  `financial_institution_percentage` double DEFAULT NULL,
  `financial_year` smallint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_programme_component1` (`component`),
  KEY `fk_programme_sub_component1` (`sub_component`),
  KEY `fk_programme_table11` (`implementing_partner`),
  KEY `fk_activity_planning_activity1` (`activity_name`),
  KEY `fk_activity_planning_expenditure_category1` (`expenditure_category`),
  KEY `fk_sub_activity_measurement_unit2` (`measurement_unit`),
  KEY `fk_sub_activity_sub_activity_name2` (`sub_activity_name`),
  KEY `fk_sub_activity_response_pcu1` (`response_pcu`),
  KEY `fk_sub_activity_table11` (`financial_year`),
  CONSTRAINT `fk_activity_planning_activity1` FOREIGN KEY (`activity_name`) REFERENCES `activity_name` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_planning_expenditure_category1` FOREIGN KEY (`expenditure_category`) REFERENCES `expenditure_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_component1` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_sub_component1` FOREIGN KEY (`sub_component`) REFERENCES `sub_component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_table11` FOREIGN KEY (`implementing_partner`) REFERENCES `implementing_partner` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_measurement_unit2` FOREIGN KEY (`measurement_unit`) REFERENCES `measurement_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_response_pcu1` FOREIGN KEY (`response_pcu`) REFERENCES `response_pcu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_sub_activity_name2` FOREIGN KEY (`sub_activity_name`) REFERENCES `sub_activity_name` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_table11` FOREIGN KEY (`financial_year`) REFERENCES `financial_year` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_activity`
--

LOCK TABLES `sub_activity` WRITE;
/*!40000 ALTER TABLE `sub_activity` DISABLE KEYS */;
INSERT INTO `sub_activity` VALUES (1,NULL,'21312',1,2,1,1,'2016-07-28','2016-07-28',2,98.00,98.00,890.00,987.00,1,1,'Yes','098',98765.00,96789.00,4,8,7,7,8,6,7,2),(2,'100000 smallholder farmers mobilized(40000 category 1; 60000 category 2)','1.1.1.4',1,2,1,1,'2016-07-28','2016-07-28',6,9876.00,34567890.00,56879.00,909876.00,1,1,'Yes','dfvbhjkl;y',567890.00,998765.00,4,45,7,9,45,5,8,2),(3,'60 grain warehouse facilities certified and operational with WRS system','2.1.3.1',3,2,1,2,'2016-07-29','2016-07-29',4,45000.00,60000.00,78000.00,56000.00,1,1,'Yes','Meetings',98000.00,78000.00,8,11,11,11,11,11,12,2);
/*!40000 ALTER TABLE `sub_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_activity_name`
--

DROP TABLE IF EXISTS `sub_activity_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_activity_name` (
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `activity_name` smallint(2) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_sub_activity_description_activity1` (`activity_name`),
  CONSTRAINT `fk_sub_activity_description_activity1` FOREIGN KEY (`activity_name`) REFERENCES `activity_name` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_activity_name`
--

LOCK TABLES `sub_activity_name` WRITE;
/*!40000 ALTER TABLE `sub_activity_name` DISABLE KEYS */;
INSERT INTO `sub_activity_name` VALUES (1,'Screening and selection of beneficiaries for e-voucher',1),(2,'Selection of category 2 farmers and farmer groups',1),(3,'Calls for expression of application for agrodealers\' participation in e-voucher scheme',2),(4,'Discussion and signing of agrodealers implementation commitments with PCU',2);
/*!40000 ALTER TABLE `sub_activity_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_component`
--

DROP TABLE IF EXISTS `sub_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_component` (
  `id` smallint(2) unsigned NOT NULL AUTO_INCREMENT,
  `sub_component` varchar(200) NOT NULL,
  `component` smallint(2) unsigned NOT NULL,
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
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
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
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `topic` varchar(200) NOT NULL,
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
  `topic` smallint(5) unsigned DEFAULT NULL,
  `venue` int(10) unsigned DEFAULT NULL,
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
  `person_role` smallint(2) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_account_person1` (`person`),
  KEY `fk_user_account_person_role1` (`person_role`),
  CONSTRAINT `fk_user_account_person1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_account_person_role1` FOREIGN KEY (`person_role`) REFERENCES `person_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,1,'siele.bernard@gmail.com','1aca6dca73cc19c35147c3c7d2707ca78dd2495c7075c4b41912c140c4b39c00',9),(2,3,'agrodealer@gmail.com','35e8f79d9e51f0a3970f9cadc210ddec20d377af1e6a6d09c2f4eff3a9efa0d8',2),(3,4,'countyofficer@gmail.com','86ba3fc5f3c904d88523fe117921e46ce21f000b71386471d43367e6e86193fe',5),(4,5,'equity@gmail.com','3e0abfe6dc7124b22dc288240ab281552628179686a89fa3f09b8fd839f2453a',10),(5,6,'farmer@gmail.com','ba2a06effc4ee674b193ba47e22af92dda8e5c83b6657bb1d105deb6f2b4fa5f',1),(6,7,'kalroofficer@gmail.com','b163088866b02553aac34836f7699c1dcb5ccba391edd8efbdc21f664cefd0e7',8),(7,8,'nationalofficer@gmail.com','150a83b58026957b23ad46de7e69aa9de4e406cb6e10b7e92e940ad224687ace',7),(8,9,'regionalcoordinator@gmail.com','1abfa2598a1c1e5e722af1b81f8594330531d916f84cd18b804b8efabb431b21',6),(9,10,'subcounty@gmail.com','8d5e355759e9bbaeb101db1129e489b4e0acce93f38e6ea61d5c416bf872c924',4),(10,11,'warehouseoperator@gmail.com','f671352bd741b3226ed0126d2bc06d2e2ca485ee4ab0dbe8c3ab16a9b4a9b71b',11),(11,12,'wao@gmail.com','59c094d3f594d8900c5db0d90574d9f65c779b4101a02a8cb6d7f1c8586f1af5',3),(12,14,'ss@anynomous.ac.ke','b34e62121d7076d6deb48917adac921b9007084c6133849867ea1c72c7fcaf24',1);
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
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
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
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `sub_county` smallint(5) unsigned NOT NULL,
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

-- Dump completed on 2016-07-29  6:24:43
