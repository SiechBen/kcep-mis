-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: kcep_mis
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1

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
  `savings` decimal(16,2) DEFAULT NULL,
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
INSERT INTO `account` VALUES (1,'35453345',4334.00,1,'1234',6),(2,'23432424',4565.00,1,'324',12);
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
  `parent` smallint(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_activity_name_activity_name1` (`parent`),
  CONSTRAINT `fk_activity_name_activity_name1` FOREIGN KEY (`parent`) REFERENCES `activity_name` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_name`
--

LOCK TABLES `activity_name` WRITE;
/*!40000 ALTER TABLE `activity_name` DISABLE KEYS */;
INSERT INTO `activity_name` VALUES (1,'Community mobilization and selection of beneficiaries ',NULL),(2,'Identification and selection of agrodealers  (Eastern Region)',NULL),(3,'Capacity buiding of agrodealers',NULL),(4,'Training of extension service providers',NULL),(5,'Preparation of training and extension materials on selected technologies for targeted value chains',4),(6,'Training of Extension agents on new crop technologies',NULL),(7,'Training of extension agents on soil fertility management and diagnostics of nutrient deficiencies',NULL),(8,'Training of extension agents on extension climate smart good agricultural practices for targeted crops',NULL),(9,'Esablishment of field-trial demonstration plots',NULL),(10,'Establishment of demonstration plots at KALRO regional centres for filed trials',9),(11,'Establishment of demonstrations at plots at farmers\'  group level',NULL),(12,'Training of farmers on improved crop technologies',NULL),(13,'Training of community-level farmer organization and capacity building service providers ',NULL),(14,'Preparation of group organization and capacity building manuals',13),(15,'Training of service providers ',NULL),(16,'Farmer organization and capacity building',NULL),(17,'Farmer organization',16),(18,'Preparation of group action plans',NULL),(19,'Training and capacity building of groups',NULL),(20,'Linkages with inputs, services and output markets',NULL),(21,'Linkages to input markets',20),(22,'Linkages to service markets (Finance)',NULL),(23,'Linkages to output markets',NULL),(24,'Facilitatation of KCEP interface with groups',NULL),(25,'Training of farmers in harvest and post-harvest management',NULL),(26,'Preparation and procurement of training  materials',25),(27,'Training of trainers',NULL),(28,'Training of farmers',NULL),(29,'Formation of secondary farmer organizations',NULL),(30,'Establishment and operationalization of produce collection centres',NULL),(31,'Establishment of produce collection centres',30),(32,'Produce aggregation',NULL),(33,'Establisment and certification of produce warehousing facilities & systems',NULL),(34,'Establishment of warehousing facilities',33),(35,'Certification of warehouses',NULL),(36,'Warehouse operations',NULL),(37,'Set-up of WRS ',NULL),(38,'Building of business partnerships between lead buyers and farmer organizations',NULL),(39,'Identification of lead buyers',38),(40,'Mobilization and capacity building of farmers for sustained market engagement',NULL),(41,'Formalization of business partnerships (spot & forwards)',NULL),(42,'Value addition and processing',NULL),(43,'Investment promotion in value addition and processing',42),(44,'Support to farmer-owned value addition/processing ventures',NULL),(45,'Support to private sector-owned ventures',NULL),(46,'Spot improvements of access roads ',NULL),(47,'Enrollment of farmers and mobilization to raise required beneficiary contribution',NULL),(48,'Screening and enrolment of selected farmers',47),(49,'Savings mobilization to meet benficiary contribution requirements',NULL),(50,'Training of farmers on financial literacy',NULL),(51,'Accreditation and trainingof agrodealers',NULL),(52,'Enrollment and accreditation of Agrodealers',51),(53,'Financial literacy training of agrodealers',NULL),(54,'e-voucher Financing ',NULL),(55,'Identification of supply sources and valuation of e-voucher package',54),(56,'Coordination of inputs availability at agrodealer shops',NULL),(57,'e-voucher processing',NULL),(58,'e-financed inputs supply to farmers',NULL),(59,'Facilitating farmers\' acquisition of e-voucher inputs',NULL),(60,'Financial graduation (FG)of smallholder farmers',59),(61,'Design of financial graduation strategy and products',NULL),(62,'Close monitoring of FG process to ensure programme remains on track',NULL),(63,'Warehouse receipt financing ',NULL),(64,'Development and training on WRS financing',63),(65,'WRS financing',NULL),(66,'Value Chain Financing',NULL),(67,'Value Chain Financing of category 2 farmers',67),(68,'Agrodealer financing (no grant)',NULL),(69,'Financing of grain threshing/shelling service providers (no grant)',NULL),(70,'Financing storage, value addition and processing LLCs (venture - different debt:equity:grant ratios)',NULL),(71,'Programme operations & management',NULL),(72,'PCU investment and recurrent costs',71),(73,'Programme oversight',NULL),(74,'M&E and Knowledgement Management',NULL),(75,'Gender and social inclusion (GESI) study',74),(76,'Baseline/RIMS survey',NULL),(77,'M&E Reporting and KM training',NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annual_indicator`
--

LOCK TABLES `annual_indicator` WRITE;
/*!40000 ALTER TABLE `annual_indicator` DISABLE KEYS */;
/*!40000 ALTER TABLE `annual_indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `relative` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_category_category1` (`relative`),
  CONSTRAINT `fk_category_category1` FOREIGN KEY (`relative`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='e.g. farmer';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Farmers',NULL),(2,'Agro-dealers',NULL),(3,'Entrepreneurs',NULL),(4,'ToTs i.e. staff, lead farmers, NGO staff',NULL),(5,'AGMARK',NULL),(6,'Equity Group Foundation (EGF)',NULL),(7,'Equity Bank Limited (EBL)',NULL),(8,'County Staff',NULL),(9,'East African Grain Council (EAGC)',NULL),(10,'Technical Services Providers for Component 1',NULL),(11,'KALRO',NULL),(12,'Farmer Owned',NULL),(13,'Privately Owned',NULL),(14,'RIMS',NULL),(15,'NIMES',NULL),(16,'CIMES',NULL),(17,'Programme',NULL),(18,'GESI',NULL),(19,'Warehouse',NULL),(20,'Collection Centre',NULL),(21,'Cereal Productivity Enhancement',NULL),(22,'Post-Harvest Management and Market Linkages',NULL),(23,'Financial Services',NULL),(24,'Project Management',NULL),(25,'Effective Agricultural Services',21),(26,'Supporting demand of services and inputs (farmer organization and capacity building)',21),(27,'Post harvest management',22),(28,'Market Linkages and Value Addition',22),(29,'Financial inclusion',23),(30,'Value Chain financing',23),(31,'Outcome 1: Productivity for maize, white sorghum, finger millet and pulses increased',NULL),(32,'Outcome 2: Post-harvest practices and market linkages for targeted VCs improved',NULL),(33,'Outcome 3: Financial inclusion of beneficiaries improved',NULL),(34,'Output 1.1: Agricultural services/ inputs improved',31),(35,'Output 1.2: Capacity of farmers’ organizations to mobilize extension services, participate in local planning and value chains fora improved',31),(36,'Output 2.1: Post-harvest management improved',32),(37,'Output 2.2: Market Access for participating smallholders improved',32),(38,'Output 3.1 : Use of financial tools and services by target groups increased',33),(39,'Output 3.2: Access to value chain financing improved',33),(40,'1',NULL),(41,'2',NULL),(42,'3',NULL),(43,'4',NULL),(44,'5',NULL),(45,'100,000 smallholder farmers mobilized (40,000 category 1; 60,000 category 2)',NULL),(46,'300 agrodealers selected and their capacity built to provide e-voucher inputs ',NULL),(47,'1,440 Extension agents trained and equipped to train and provide extension services to smallholder farmers',NULL),(48,'4,140 demonstration plots established to conduct on-farm trials on released technologies and train farmers on improved agricultural practices - 150 at reaserch station; 3,990 at farmer-fields',NULL),(49,'100,000 farmers trained on improved technologies and practices',NULL),(50,'700 service providers trained on organization and capacity building of market oriented cereal-growers\' enterprises (MOCE)',NULL),(51,'40,000 category 1 farmers organized into cohesive market-oriented farmer groups (of an average of 25 farmers per group);  ',NULL),(52,'100,000 farmers (40,000 category 1; 60,000 category 2) organized in 4,000 groups linked to input, output and service markets',NULL),(53,'Effective coordination of extension services to supported farmers',NULL),(54,'100,000 smallholders farmers trained on improved harvest and post-harvest management of grains (40,000 category 1; 60,000 category 2)',NULL),(55,'40,000 category 1 farmers (already formed into groups under Comp 1) organized into 100 produce marketing groups',NULL),(56,'100 produce collection centres established and active',NULL),(57,'60 grain warehouse facilities certified and operational with WRS system',NULL),(58,'60 business partnerships developed between lead farmers and 100,000 farmers organized into secondary and apex farmer organizations; 90,000 MT of grain traded annually through structured market systems',NULL),(59,'20 ppp ventures established in value addition and processing',NULL),(60,'40,000 Smallholder farmers enrolled by Equity bank and raised required beneficiary contribution',NULL),(61,'40,000  smallholder farmers trained on financial literacy and improve their savings culture',NULL),(62,'300 agrodealers accredited as EBL agents with improved capacity to serve targeted farmers',NULL),(63,'40,000  accessed with e-voucher inputs financing',NULL),(64,'40,000 farmers graduate into financial sector',NULL),(65,'100,000 farmers access Warehouse Receipt System (WRS) financing',NULL),(66,'Access to Value chain financing by key players by 60,000 category 2 farmers; 300 agrodealers; service providers; and 80 PPP ventures in grain handling, value addition and processing',NULL),(67,'Effective programme management',NULL),(68,'Monitoring & Evaluation and Knowledgement instutionalized; Gender mainstreamed',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1,'Cereal Productivity Enhancement'),(3,'Financial Services'),(2,'Post-Harvest Management and Market Linkages'),(4,'Project Management');
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
  `serial_number` varchar(45) DEFAULT NULL,
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
  `yield` varchar(45) DEFAULT NULL,
  `date_done` date DEFAULT NULL,
  `quantity_harvested` double DEFAULT NULL,
  `family_consumption` double DEFAULT NULL,
  `quantity_sold` double DEFAULT NULL,
  `post_harvest_loss` double DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financial_year`
--

LOCK TABLES `financial_year` WRITE;
/*!40000 ALTER TABLE `financial_year` DISABLE KEYS */;
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
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `input_type`
--

LOCK TABLES `input_type` WRITE;
/*!40000 ALTER TABLE `input_type` DISABLE KEYS */;
INSERT INTO `input_type` VALUES (2,'Fertiliser'),(4,'Hermetic bags'),(1,'Seeds'),(3,'Tarpaulin');
/*!40000 ALTER TABLE `input_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `input_variety`
--

DROP TABLE IF EXISTS `input_variety`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `input_variety` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `variety` varchar(45) NOT NULL,
  `static_input` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `variety_UNIQUE` (`variety`),
  KEY `fk_input_variety_static_input1` (`static_input`),
  CONSTRAINT `fk_input_variety_static_input1` FOREIGN KEY (`static_input`) REFERENCES `static_input` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `input_variety`
--

LOCK TABLES `input_variety` WRITE;
/*!40000 ALTER TABLE `input_variety` DISABLE KEYS */;
INSERT INTO `input_variety` VALUES (1,'DAP',7),(2,'NPK',7),(3,'Hybrid 45',1),(4,'Katumani',1);
/*!40000 ALTER TABLE `input_variety` ENABLE KEYS */;
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
  `input_variety` int(10) unsigned DEFAULT NULL,
  `quantity` varchar(45) DEFAULT NULL,
  `farmer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_inputs_collection_person1` (`agro_dealer`),
  KEY `fk_inputs_collection_input_type1` (`input_type`),
  KEY `fk_inputs_collection_person2` (`farmer`),
  KEY `fk_inputs_collection_static_input1` (`static_input`),
  KEY `fk_inputs_collection_input_variety1` (`input_variety`),
  CONSTRAINT `fk_inputs_collection_input_type1` FOREIGN KEY (`input_type`) REFERENCES `input_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_input_variety1` FOREIGN KEY (`input_variety`) REFERENCES `input_variety` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
  `issuing_bank` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_loan_account1` (`account`),
  KEY `fk_loan_phenomenon1` (`issuing_bank`),
  CONSTRAINT `fk_loan_account1` FOREIGN KEY (`account`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_loan_phenomenon1` FOREIGN KEY (`issuing_bank`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `performance_indicator_type` int(10) unsigned NOT NULL DEFAULT '18',
  `result_hierarchy` smallint(3) unsigned NOT NULL,
  `description` varchar(400) NOT NULL,
  `baseline_date` date DEFAULT NULL,
  `baseline_value` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_performance_indicator_result_hierarchy1` (`result_hierarchy`),
  KEY `fk_performance_indicator_phenomenon1` (`performance_indicator_type`),
  CONSTRAINT `fk_performance_indicator_phenomenon1` FOREIGN KEY (`performance_indicator_type`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_performance_indicator_result_hierarchy1` FOREIGN KEY (`result_hierarchy`) REFERENCES `result_hierarchy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_indicator`
--

LOCK TABLES `performance_indicator` WRITE;
/*!40000 ALTER TABLE `performance_indicator` DISABLE KEYS */;
INSERT INTO `performance_indicator` VALUES (1,16,1,'a. No. of households uplifted from poverty Reduced poverty',NULL,NULL),(2,15,1,'c. Percentage reduction in the prevalence of child malnutrition',NULL,NULL),(3,15,1,'b. No. of households with improvement in assets ownership index (productive assets, bikes, radios, improving housing, tin roofs etc)',NULL,NULL),(4,15,1,'d. Reduction in HH experiencing one hungry season per year',NULL,NULL),(5,16,1,'e. Reduced hunger',NULL,NULL),(6,19,1,'f. Increased empowerment of women and youth in agriculture',NULL,NULL),(7,19,1,'g. Increased empowerment of women and youth in agriculture (WEAI 80%)',NULL,NULL),(8,18,2,'a. Percentage reduction of the national grain deficit',NULL,NULL),(9,15,2,'b. No. of targeted households using improved inputs voluntarily',NULL,NULL),(10,15,2,'c. No. of targeted small farmers in commercial contracts',NULL,NULL),(11,15,2,'d. No. of targeted smallholders engaged in financial services (i.e taking investment loans)',NULL,NULL),(12,15,2,'e. No. of targeted households bulking and selling grains at price 30% higher than farmgate',NULL,NULL),(13,19,2,'f. No. of M&E officers trained on gender mainstreaming and social inclusion',NULL,NULL),(14,19,2,'g. No. of women and youth empowered in decision making',NULL,NULL),(15,15,4,'a. Improved agricultural production',NULL,NULL),(16,18,4,'b. No. of HH adopting improved practices/packages ',NULL,NULL),(17,15,4,'c. No of farmers using purchased inputs',NULL,NULL),(18,17,4,'d. No. of farmers adopting modern techniques (Embu, Bungoma, Nakuru and Kitui)',NULL,NULL),(19,15,4,'e. No / Percentage of farmers reporting production/ improved yields',NULL,NULL),(20,15,4,'f. No of farmers adopting recommended technologies',NULL,NULL),(21,15,4,'g. No of groups functional after 3 years',NULL,NULL),(22,19,4,'h. Increased participation of women and youth in agriculture production',NULL,NULL),(23,18,5,'a. No. of subsistence farmers issued with input package',NULL,NULL),(24,18,5,'b. No. of agro-dealers trained',NULL,NULL),(25,15,5,'c. Staff of services providers trained',NULL,NULL),(26,15,5,'d. No. of people trained in crop production practices and technologies',NULL,NULL),(27,15,5,'e. No. of people accessing facilitated advisory services',NULL,NULL),(28,19,5,'f. No. of women and youth sensitized on decision making and access to key resources for production',NULL,NULL),(29,19,5,'g. No. of women and youth sensisitized on behavioural change, communication and sharing of household roles',NULL,NULL),(30,18,6,'a. No of farmer groups trained in organizational management',NULL,NULL),(31,15,6,'b. No. of crop production groups formed/ trained',NULL,NULL),(32,18,6,'c. No. of crop production groups formed',NULL,NULL),(33,15,6,'d. No. of formed crop production groups with women in leadership positions',NULL,NULL),(34,19,6,'e. No. of women and youth sensitized on leadership skills ',NULL,NULL),(35,19,6,'f. No. of women and youth sensitized on access to production inputs, extension and advisory services',NULL,NULL),(36,18,7,'a. No. of subsistence farmers adopting improved grain drying technologies ',NULL,NULL),(37,18,7,'b. No. of subsistence farmers adopting improved grain storage technologies ',NULL,NULL),(38,18,7,'c. No. of farmers using certified warehouses',NULL,NULL),(39,18,7,'d. Percentage reduction in post-harvest grain losses',NULL,NULL),(40,18,7,'e. No. of warehouses that have attained operational self-sufficiency Producers benefiting from improved access to markets',NULL,NULL),(41,15,7,'f. No. of functioning infrastructure after 3 years',NULL,NULL),(42,15,7,'g. No. of functioning storage & processing facilities after 3 years',NULL,NULL),(43,19,7,'i. Increased participation of women and youth in postharvest practices and marketing ',NULL,NULL),(44,18,8,'a. No. of subsistence farmers trained on post-harvest grain management',NULL,NULL),(45,15,8,'b. People trained on post-production and marketing',NULL,NULL),(46,18,8,'c. No. of warehouses certified',NULL,NULL),(47,18,8,'d. No. of collection centres rehabilitated',NULL,NULL),(48,15,8,'e. No. of storage facilities constructed/ rehabilitated',NULL,NULL),(49,15,8,'f. No. of processing facilities constructed/rehabilitated',NULL,NULL),(50,17,8,'g. No. of storage facilities constructed(Embu, Nakuru and Kitui)',NULL,NULL),(51,19,8,'h. No. of women and youth sensitized on adoption of time and energy saving technologies ',NULL,NULL),(52,18,9,'a. No. of HH beneficiaries trained in business partnership',NULL,NULL),(53,18,9,'b. No. of initiatives of commercial partnership implemented ',NULL,NULL),(54,18,9,'c. No. of access road improvement completed (spot road improvements)',NULL,NULL),(55,15,9,'d. Roads constructed/ rehabilitated',NULL,NULL),(56,19,9,'e. No. of women and youth sensitized on market access',NULL,NULL),(57,18,10,'a. No. of farmers producing grain and pulses have access to financial services',NULL,NULL),(58,15,10,'b. No. of HH using certified warehouse receipting system',NULL,NULL),(59,18,10,'c. No. / % increase in access to credit facilities (Bungoma, Kakamega and Nandi)',NULL,NULL),(60,15,10,'d. Improved access of the poor to financial services',NULL,NULL),(61,19,10,'g. Increased access to financial services by women and youth',NULL,NULL),(62,18,10,'e. Number and type of new products implemented by EBL',NULL,NULL),(63,15,10,'f. Improved performance of financial institutions',NULL,NULL),(64,18,11,'a. No. of vouchers used by target groups',NULL,NULL),(65,18,11,'b. No. of category 1 HH trained in financial literacy ',NULL,NULL),(66,15,11,'c. People trained on financial services  (male, female, youth)',NULL,NULL),(67,18,11,'d. No. of entrepreneurs trained in advanced financial training',NULL,NULL),(68,15,11,'e. Bank staff trained',NULL,NULL),(69,18,11,'f. No of farmers trained',NULL,NULL),(70,15,11,'g. Value of voluntary savings',NULL,NULL),(71,15,11,'h. Financial institutions participating in the Programme',NULL,NULL),(72,15,11,'i. No of active voluntary savers (RIMS1) (males, female, youth)',NULL,NULL),(73,15,11,'j. Number of active borrowers',NULL,NULL),(74,15,11,'k. Value of gross loan portfolio by Programme beneficiaries',NULL,NULL),(75,15,11,'l. Total amount of savings by target group(by gender)-target to be defined at start up',NULL,NULL),(76,19,11,'m. No. of women and youth sensitized on benefit sharing, off farm businesses and business skills',NULL,NULL),(77,19,11,'n. No. of women and youth sensitized on access to financial services',NULL,NULL),(78,18,12,'a. No. of value chain financing ventures completed',NULL,NULL),(79,18,12,'b. No. of forward contracts signed',NULL,NULL);
/*!40000 ALTER TABLE `performance_indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance_indicator_values`
--

DROP TABLE IF EXISTS `performance_indicator_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performance_indicator_values` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_year` smallint(4) DEFAULT NULL,
  `actual_value` double DEFAULT NULL,
  `expected_value` double DEFAULT NULL,
  `performance_indicator` smallint(3) unsigned NOT NULL,
  `purpose` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_performance_indicator_values_performance_indicator1` (`performance_indicator`),
  CONSTRAINT `fk_performance_indicator_values_performance_indicator1` FOREIGN KEY (`performance_indicator`) REFERENCES `performance_indicator` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_indicator_values`
--

LOCK TABLES `performance_indicator_values` WRITE;
/*!40000 ALTER TABLE `performance_indicator_values` DISABLE KEYS */;
INSERT INTO `performance_indicator_values` VALUES (1,2016,NULL,NULL,1,NULL),(2,2016,NULL,NULL,1,'Outcome report'),(3,2016,NULL,NULL,2,NULL),(4,2016,NULL,NULL,2,'Outcome report'),(5,2016,NULL,NULL,3,NULL),(6,2016,NULL,NULL,3,'Outcome report'),(7,2016,NULL,NULL,4,NULL),(8,2016,NULL,NULL,4,'Outcome report'),(9,2016,NULL,NULL,5,NULL),(10,2016,NULL,NULL,5,'Outcome report'),(11,2016,NULL,NULL,6,NULL),(12,2016,NULL,NULL,6,'Outcome report'),(13,2016,NULL,NULL,7,NULL),(14,2016,NULL,NULL,7,'Outcome report'),(15,2016,NULL,NULL,8,NULL),(16,2016,NULL,NULL,8,'Outcome report'),(17,2016,NULL,NULL,9,NULL),(18,2016,NULL,NULL,9,'Outcome report'),(19,2016,NULL,NULL,10,NULL),(20,2016,NULL,NULL,10,'Outcome report'),(21,2016,NULL,NULL,11,NULL),(22,2016,NULL,NULL,11,'Outcome report'),(23,2016,NULL,NULL,12,NULL),(24,2016,NULL,NULL,12,'Outcome report'),(25,2016,NULL,NULL,13,NULL),(26,2016,NULL,NULL,13,'Outcome report'),(27,2016,NULL,NULL,14,NULL),(28,2016,NULL,NULL,14,'Outcome report'),(29,2016,NULL,NULL,15,NULL),(30,2016,NULL,NULL,15,'Outcome report'),(31,2016,NULL,NULL,16,NULL),(32,2016,NULL,NULL,16,'Outcome report'),(33,2016,NULL,NULL,17,NULL),(34,2016,NULL,NULL,17,'Outcome report'),(35,2016,NULL,NULL,18,NULL),(36,2016,NULL,NULL,18,'Outcome report'),(37,2016,NULL,NULL,19,NULL),(38,2016,NULL,NULL,19,'Outcome report'),(39,2016,NULL,NULL,20,NULL),(40,2016,NULL,NULL,20,'Outcome report'),(41,2016,NULL,NULL,21,NULL),(42,2016,NULL,NULL,21,'Outcome report'),(43,2016,NULL,NULL,22,NULL),(44,2016,NULL,NULL,22,'Outcome report'),(45,2016,NULL,NULL,23,NULL),(46,2016,NULL,NULL,23,'Outcome report'),(47,2016,NULL,NULL,24,NULL),(48,2016,NULL,NULL,24,'Outcome report'),(49,2016,NULL,NULL,25,NULL),(50,2016,NULL,NULL,25,'Outcome report'),(51,2016,NULL,NULL,26,NULL),(52,2016,NULL,NULL,26,'Outcome report'),(53,2016,NULL,NULL,27,NULL),(54,2016,NULL,NULL,27,'Outcome report'),(55,2016,NULL,NULL,28,NULL),(56,2016,NULL,NULL,28,'Outcome report'),(57,2016,NULL,NULL,29,NULL),(58,2016,NULL,NULL,29,'Outcome report'),(59,2016,NULL,NULL,30,NULL),(60,2016,NULL,NULL,30,'Outcome report'),(61,2016,NULL,NULL,31,NULL),(62,2016,NULL,NULL,31,'Outcome report'),(63,2016,NULL,NULL,32,NULL),(64,2016,NULL,NULL,32,'Outcome report'),(65,2016,NULL,NULL,33,NULL),(66,2016,NULL,NULL,33,'Outcome report'),(67,2016,NULL,NULL,34,NULL),(68,2016,NULL,NULL,34,'Outcome report'),(69,2016,NULL,NULL,35,NULL),(70,2016,NULL,NULL,35,'Outcome report'),(71,2016,NULL,NULL,36,NULL),(72,2016,NULL,NULL,36,'Outcome report'),(73,2016,NULL,NULL,37,NULL),(74,2016,NULL,NULL,37,'Outcome report'),(75,2016,NULL,NULL,38,NULL),(76,2016,NULL,NULL,38,'Outcome report'),(77,2016,NULL,NULL,39,NULL),(78,2016,NULL,NULL,39,'Outcome report'),(79,2016,NULL,NULL,40,NULL),(80,2016,NULL,NULL,40,'Outcome report'),(81,2016,NULL,NULL,41,NULL),(82,2016,NULL,NULL,41,'Outcome report'),(83,2016,NULL,NULL,42,NULL),(84,2016,NULL,NULL,42,'Outcome report'),(85,2016,NULL,NULL,43,NULL),(86,2016,NULL,NULL,43,'Outcome report'),(87,2016,NULL,NULL,44,NULL),(88,2016,NULL,NULL,44,'Outcome report'),(89,2016,NULL,NULL,45,NULL),(90,2016,NULL,NULL,45,'Outcome report'),(91,2016,NULL,NULL,46,NULL),(92,2016,NULL,NULL,46,'Outcome report'),(93,2016,NULL,NULL,47,NULL),(94,2016,NULL,NULL,47,'Outcome report'),(95,2016,NULL,NULL,48,NULL),(96,2016,NULL,NULL,48,'Outcome report'),(97,2016,NULL,NULL,49,NULL),(98,2016,NULL,NULL,49,'Outcome report'),(99,2016,NULL,NULL,50,NULL),(100,2016,NULL,NULL,50,'Outcome report'),(101,2016,NULL,NULL,51,NULL),(102,2016,NULL,NULL,51,'Outcome report'),(103,2016,NULL,NULL,52,NULL),(104,2016,NULL,NULL,52,'Outcome report'),(105,2016,NULL,NULL,53,NULL),(106,2016,NULL,NULL,53,'Outcome report'),(107,2016,NULL,NULL,54,NULL),(108,2016,NULL,NULL,54,'Outcome report'),(109,2016,NULL,NULL,55,NULL),(110,2016,NULL,NULL,55,'Outcome report'),(111,2016,NULL,NULL,56,NULL),(112,2016,NULL,NULL,56,'Outcome report'),(113,2016,NULL,NULL,57,NULL),(114,2016,NULL,NULL,57,'Outcome report'),(115,2016,NULL,NULL,58,NULL),(116,2016,NULL,NULL,58,'Outcome report'),(117,2016,NULL,NULL,59,NULL),(118,2016,NULL,NULL,59,'Outcome report'),(119,2016,NULL,NULL,60,NULL),(120,2016,NULL,NULL,60,'Outcome report'),(121,2016,NULL,NULL,61,NULL),(122,2016,NULL,NULL,61,'Outcome report'),(123,2016,NULL,NULL,62,NULL),(124,2016,NULL,NULL,62,'Outcome report'),(125,2016,NULL,NULL,63,NULL),(126,2016,NULL,NULL,63,'Outcome report'),(127,2016,NULL,NULL,64,NULL),(128,2016,NULL,NULL,64,'Outcome report'),(129,2016,NULL,NULL,65,NULL),(130,2016,NULL,NULL,65,'Outcome report'),(131,2016,NULL,NULL,66,NULL),(132,2016,NULL,NULL,66,'Outcome report'),(133,2016,NULL,NULL,67,NULL),(134,2016,NULL,NULL,67,'Outcome report'),(135,2016,NULL,NULL,68,NULL),(136,2016,NULL,NULL,68,'Outcome report'),(137,2016,NULL,NULL,69,NULL),(138,2016,NULL,NULL,69,'Outcome report'),(139,2016,NULL,NULL,70,NULL),(140,2016,NULL,NULL,70,'Outcome report'),(141,2016,NULL,NULL,71,NULL),(142,2016,NULL,NULL,71,'Outcome report'),(143,2016,NULL,NULL,72,NULL),(144,2016,NULL,NULL,72,'Outcome report'),(145,2016,NULL,NULL,73,NULL),(146,2016,NULL,NULL,73,'Outcome report'),(147,2016,NULL,NULL,74,NULL),(148,2016,NULL,NULL,74,'Outcome report'),(149,2016,NULL,NULL,75,NULL),(150,2016,NULL,NULL,75,'Outcome report'),(151,2016,NULL,NULL,76,NULL),(152,2016,NULL,NULL,76,'Outcome report'),(153,2016,NULL,NULL,77,NULL),(154,2016,NULL,NULL,77,'Outcome report'),(155,2016,NULL,NULL,78,NULL),(156,2016,NULL,NULL,78,'Outcome report'),(157,2016,NULL,NULL,79,NULL),(158,2016,NULL,NULL,79,'Outcome report');
/*!40000 ALTER TABLE `performance_indicator_values` ENABLE KEYS */;
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
  `age` smallint(3) DEFAULT NULL,
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
-- Table structure for table `phenomenon`
--

DROP TABLE IF EXISTS `phenomenon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phenomenon` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` int(10) unsigned NOT NULL,
  `phenomenon_type` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_phenomenon_category1` (`category`),
  KEY `fk_phenomenon_phenomenon_type1` (`phenomenon_type`),
  CONSTRAINT `fk_phenomenon_category1` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_phenomenon_phenomenon_type1` FOREIGN KEY (`phenomenon_type`) REFERENCES `phenomenon_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phenomenon`
--

LOCK TABLES `phenomenon` WRITE;
/*!40000 ALTER TABLE `phenomenon` DISABLE KEYS */;
INSERT INTO `phenomenon` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,2),(6,6,2),(7,7,2),(8,8,2),(9,9,2),(10,10,2),(11,11,2),(12,12,3),(13,13,3),(14,7,6),(15,14,7),(16,15,7),(17,16,7),(18,17,7),(19,18,7),(20,19,8),(21,20,8),(22,21,9),(23,22,9),(24,23,9),(25,24,9),(26,25,10),(27,26,10),(28,27,10),(29,28,10),(30,29,10),(31,30,10),(32,31,11),(33,32,11),(34,33,11),(35,34,12),(36,35,12),(37,36,12),(38,37,12),(39,38,12),(40,39,12),(41,40,13),(42,41,13),(43,42,13),(44,43,13),(45,44,13),(46,45,14),(47,46,14),(48,47,14),(49,48,14),(50,49,14),(51,50,14),(52,51,14),(53,52,14),(54,53,14),(55,54,14),(56,55,14),(57,56,14),(58,57,14),(59,58,14),(60,59,14),(61,60,14),(62,61,14),(63,62,14),(64,63,14),(65,64,14),(66,65,14),(67,66,14),(68,67,14),(69,68,14);
/*!40000 ALTER TABLE `phenomenon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phenomenon_type`
--

DROP TABLE IF EXISTS `phenomenon_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phenomenon_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='e.g. category of trainees';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phenomenon_type`
--

LOCK TABLES `phenomenon_type` WRITE;
/*!40000 ALTER TABLE `phenomenon_type` DISABLE KEYS */;
INSERT INTO `phenomenon_type` VALUES (1,'Category of trainees'),(2,'Category of trainers'),(3,'Warehouse operator'),(4,'Procurement item'),(5,'GFSS code'),(6,'Bank'),(7,'Performance indicator type'),(8,'Warehouse type'),(9,'Component'),(10,'Sub-component'),(11,'Outcome'),(12,'Output'),(13,'Rating value'),(14,'Expected outcome');
/*!40000 ALTER TABLE `phenomenon_type` ENABLE KEYS */;
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
  `gfss_code` int(10) unsigned DEFAULT NULL,
  `date_purchased` date DEFAULT NULL,
  `serial_number` varchar(45) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `target_office` varchar(45) DEFAULT NULL,
  `county` smallint(5) unsigned DEFAULT NULL,
  `sub_county` smallint(5) unsigned DEFAULT NULL,
  `cost` decimal(16,2) DEFAULT NULL,
  `lpo_number` varchar(45) DEFAULT NULL,
  `invoice_or_receipt` varchar(300) DEFAULT NULL COMMENT 'Attachment\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_purchase_county1` (`county`),
  KEY `fk_procurement_phenomenon1` (`gfss_code`),
  KEY `fk_procurement_sub_county1` (`sub_county`),
  CONSTRAINT `fk_procurement_phenomenon1` FOREIGN KEY (`gfss_code`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_sub_county1` FOREIGN KEY (`sub_county`) REFERENCES `sub_county` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement_plan`
--

LOCK TABLES `procurement_plan` WRITE;
/*!40000 ALTER TABLE `procurement_plan` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement_plan_cs`
--

LOCK TABLES `procurement_plan_cs` WRITE;
/*!40000 ALTER TABLE `procurement_plan_cs` DISABLE KEYS */;
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
INSERT INTO `result_hierarchy` VALUES (1,'Goal: National food security improved',NULL,NULL),(2,'Programme objectives\nIncreased production of targeted cereal staples (maize, sorghum, millet and associated pulses)\nIncreased income of smallholders in medium and high potential production areas',NULL,NULL),(4,'Outcome 1: Productivity for maize, sorghum, finger millet and pulses increased',NULL,NULL),(5,'Output 1.1; Agricultural services/ inputs improved',NULL,NULL),(6,'Output 1.2; Capacity of farmers’ organizations to mobilize extension services, participate in local planning and value chains fora improved',NULL,NULL),(7,'Outcome 2; Post harvest practices and market linkages for targeted VCs improved',NULL,NULL),(8,'Output 2.1; Post-harvest management improved',NULL,NULL),(9,'Output 2.2; Market Access for participating smallholders improved',NULL,NULL),(10,'Outcome 3; Financial inclusion of beneficiaries improved',NULL,NULL),(11,'Output 3.1 : Use of financial tools and services by target groups increased',NULL,NULL),(12,'Output 3.2: Access to value chain financing improved',NULL,NULL);
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
  `name` varchar(45) NOT NULL,
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
  `financial_year` smallint(3) unsigned DEFAULT NULL,
  `annual_workplan_reference_code` varchar(20) DEFAULT NULL,
  `gfss_code` int(10) unsigned DEFAULT NULL,
  `expected_outcome` int(10) unsigned DEFAULT NULL,
  `component` smallint(2) unsigned DEFAULT NULL,
  `sub_component` smallint(2) unsigned DEFAULT NULL,
  `activity_name` smallint(2) unsigned DEFAULT NULL,
  `sub_activity_name` smallint(2) unsigned DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `measurement_unit` smallint(5) unsigned DEFAULT NULL,
  `unit_cost` decimal(16,2) DEFAULT NULL,
  `awpb_target` decimal(16,2) DEFAULT NULL,
  `programme_target` decimal(16,2) DEFAULT NULL,
  `totals` decimal(16,2) DEFAULT NULL,
  `response_pcu` smallint(2) unsigned DEFAULT NULL,
  `implementing_partner` smallint(2) unsigned DEFAULT NULL,
  `procurement_plan` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `value_achieved` decimal(16,2) DEFAULT NULL,
  `allocated_budget` decimal(16,2) DEFAULT NULL,
  `expenditure_category` smallint(2) unsigned DEFAULT NULL,
  `gok_percentage` double DEFAULT NULL,
  `ifad_loan_percentage` double DEFAULT NULL,
  `ifad_grant_percentage` double DEFAULT NULL,
  `beneficiaries_percentage` double DEFAULT NULL,
  `eu_percentage` double DEFAULT NULL,
  `financial_institution_percentage` double DEFAULT NULL,
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
  KEY `fk_sub_activity_phenomenon1` (`gfss_code`),
  KEY `fk_sub_activity_phenomenon2` (`expected_outcome`),
  CONSTRAINT `fk_activity_planning_activity1` FOREIGN KEY (`activity_name`) REFERENCES `activity_name` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_planning_expenditure_category1` FOREIGN KEY (`expenditure_category`) REFERENCES `expenditure_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_component1` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_sub_component1` FOREIGN KEY (`sub_component`) REFERENCES `sub_component` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_table11` FOREIGN KEY (`implementing_partner`) REFERENCES `implementing_partner` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_measurement_unit2` FOREIGN KEY (`measurement_unit`) REFERENCES `measurement_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon1` FOREIGN KEY (`gfss_code`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon2` FOREIGN KEY (`expected_outcome`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_response_pcu1` FOREIGN KEY (`response_pcu`) REFERENCES `response_pcu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_sub_activity_name2` FOREIGN KEY (`sub_activity_name`) REFERENCES `sub_activity_name` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_table11` FOREIGN KEY (`financial_year`) REFERENCES `financial_year` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_activity`
--

LOCK TABLES `sub_activity` WRITE;
/*!40000 ALTER TABLE `sub_activity` DISABLE KEYS */;
INSERT INTO `sub_activity` VALUES (1,NULL,'1.1.1.1',NULL,46,1,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,'1.1.1.2',NULL,46,1,1,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,NULL,'1.1.1.3',NULL,46,1,1,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,'1.1.1.4',NULL,46,1,1,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,'1.1.1.5',NULL,46,1,1,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,'1.1.1.6',NULL,46,1,1,NULL,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,'1.1.2.1',NULL,47,1,1,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,'1.1.2.2',NULL,47,1,1,NULL,8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,'1.1.2.3',NULL,47,1,1,NULL,9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,'1.1.2.4',NULL,47,1,1,NULL,10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,'1.1.3.1',NULL,47,1,1,NULL,11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,'1.1.3.2',NULL,47,1,1,NULL,12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,NULL,'1.1.3.3',NULL,47,1,1,NULL,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,NULL,'1.1.3.4',NULL,47,1,1,NULL,14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,NULL,'1.1.3.5',NULL,47,1,1,NULL,15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,NULL,'3.1.1.1.2',NULL,61,3,3,NULL,125,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,NULL,'1.1.4.1.1',NULL,48,1,1,NULL,16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,NULL,'1.1.4.1.2',NULL,48,1,1,NULL,17,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,NULL,'1.1.4.1.3',NULL,48,1,1,NULL,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,NULL,'1.1.4.1.4',NULL,48,1,1,NULL,19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,NULL,'1.1.4.1.5',NULL,48,1,1,NULL,20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,NULL,'1.1.4.1.6',NULL,48,1,1,NULL,21,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,NULL,'1.1.4.2.1',NULL,48,1,1,NULL,22,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,NULL,'1.1.4.2.2',NULL,48,1,1,NULL,23,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,NULL,'1.1.4.2.3',NULL,48,1,1,NULL,24,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,NULL,'1.1.4.3.1',NULL,48,1,1,NULL,25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,NULL,'1.1.4.3.2',NULL,48,1,1,NULL,26,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,NULL,'1.1.4.4.1',NULL,48,1,1,NULL,27,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,NULL,'1.1.4.4.2',NULL,48,1,1,NULL,28,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,NULL,'1.1.5.1.1',NULL,49,1,1,NULL,29,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,NULL,'1.1.5.1.2',NULL,49,1,1,NULL,30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,NULL,'1.1.5.1.3',NULL,49,1,1,NULL,31,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,NULL,'1.1.5.2.1',NULL,49,1,1,NULL,32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,NULL,'1.1.5.2.2',NULL,49,1,1,NULL,33,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,NULL,'1.1.5.2.3',NULL,49,1,1,NULL,34,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,NULL,'1.1.6.1',NULL,50,1,1,NULL,35,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,NULL,'1.1.6.2',NULL,50,1,1,NULL,36,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,NULL,'1.1.6.3',NULL,50,1,1,NULL,37,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,NULL,'1.1.6.4',NULL,50,1,1,NULL,38,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,NULL,'1.2.1.1.1',NULL,51,1,1,NULL,39,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,NULL,'1.2.1.1.2',NULL,51,1,1,NULL,40,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,NULL,'1.2.1.1.3',NULL,51,1,1,NULL,41,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,NULL,'1.2.1.2.1',NULL,51,1,1,NULL,42,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,NULL,'1.2.1.2.2',NULL,51,1,1,NULL,43,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,NULL,'1.2.1.2.3',NULL,51,1,1,NULL,44,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,NULL,'1.2.2.1.1',NULL,52,1,1,NULL,45,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,NULL,'1.2.2.1.2',NULL,52,1,1,NULL,46,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,NULL,'1.2.2.1.3',NULL,52,1,1,NULL,47,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,NULL,'1.2.2.2.1',NULL,52,1,NULL,NULL,48,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,NULL,'1.2.2.2.2',NULL,52,1,NULL,NULL,49,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,NULL,'1.2.2.3.1',NULL,52,1,1,NULL,50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,NULL,'1.2.2.3.2',NULL,52,1,1,NULL,51,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,NULL,'1.2.2.3.3',NULL,52,1,NULL,NULL,52,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,NULL,'1.2.2.3.4',NULL,52,1,1,NULL,53,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,NULL,'1.2.3.1.1',NULL,53,1,1,NULL,54,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,NULL,'1.2.3.1.2',NULL,53,1,1,NULL,55,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,NULL,'1.2.3.2.1',NULL,53,1,1,NULL,56,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,NULL,'1.2.3.2.2',NULL,53,1,1,NULL,57,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(59,NULL,'1.2.3.3.1',NULL,53,1,1,NULL,58,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60,NULL,'1.2.3.3.2',NULL,53,1,1,NULL,59,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,NULL,'1.2.4.1',NULL,54,1,1,NULL,60,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(62,NULL,'1.2.4.2',NULL,54,1,1,NULL,61,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(63,NULL,'2.1.1.1.1',NULL,55,2,2,NULL,62,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,NULL,'2.1.1.1.2',NULL,55,2,2,NULL,63,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,NULL,'2.1.1.1.3',NULL,55,2,2,NULL,64,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(66,NULL,'2.1.1.2.1',NULL,55,2,2,NULL,65,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(67,NULL,'2.1.1.2.2',NULL,55,2,2,NULL,66,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,NULL,'2.1.1.3.1',NULL,55,2,2,NULL,67,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,NULL,'2.1.1.3.2',NULL,55,2,2,NULL,68,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,NULL,'2.1.1.3.3',NULL,55,2,2,NULL,69,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,NULL,'2.1.1.3.4',NULL,55,2,2,NULL,70,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,NULL,'2.1.2.1',NULL,56,2,2,NULL,71,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,NULL,'2.1.2.2',NULL,56,2,2,NULL,72,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,NULL,'2.1.2.3',NULL,56,2,2,NULL,73,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,NULL,'2.1.2.4',NULL,56,2,2,NULL,74,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(76,NULL,'2.1.3.1.1',NULL,57,2,2,NULL,75,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,NULL,'2.1.3.1.2',NULL,57,2,2,NULL,76,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(78,NULL,'2.1.3.1.3',NULL,57,2,2,NULL,77,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,NULL,'2.1.3.2.1',NULL,57,2,2,NULL,78,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,NULL,'2.1.3.2.2',NULL,57,2,2,NULL,79,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,NULL,'2.1.3.2.3',NULL,57,2,2,NULL,80,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,NULL,'2.1.4.1.1',NULL,58,2,2,NULL,81,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,NULL,'2.1.4.1.2',NULL,58,2,2,NULL,82,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,NULL,'2.1.4.1.3',NULL,58,2,2,NULL,83,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,NULL,'2.1.4.1.4',NULL,58,2,2,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(86,NULL,'2.1.4.1.5',NULL,58,2,2,NULL,85,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(87,NULL,'2.1.4.2.1',NULL,58,2,2,NULL,86,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(88,NULL,'2.1.4.2.2',NULL,58,2,2,NULL,87,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(89,NULL,'2.1.4.2.3',NULL,58,2,2,NULL,88,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90,NULL,'2.1.4.2.4',NULL,58,2,NULL,NULL,89,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(91,NULL,'2.1.4.3.1',NULL,58,2,2,NULL,90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(92,NULL,'2.1.4.3.2',NULL,58,NULL,2,NULL,91,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(93,NULL,'2.1.4.3.3',NULL,58,2,2,NULL,92,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94,NULL,'2.1.4.4.1',NULL,58,2,2,NULL,93,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95,NULL,'2.1.4.4.2',NULL,58,2,2,NULL,94,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(96,NULL,'2.1.4.4.3',NULL,58,2,2,NULL,95,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(97,NULL,'2.1.4.4.4',NULL,58,2,2,NULL,96,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(98,NULL,'2.2.1.1.1',NULL,59,2,2,NULL,97,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99,NULL,'2.2.1.1.2',NULL,59,2,2,NULL,98,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(100,NULL,'2.2.1.1.3',NULL,59,2,2,NULL,99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(101,NULL,'2.2.1.1.4',NULL,59,2,2,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(102,NULL,'2.2.1.1.5',NULL,59,2,2,NULL,101,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(103,NULL,'2.2.1.2.1',NULL,59,2,2,NULL,102,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(104,NULL,'2.2.1.2.2',NULL,59,2,2,NULL,103,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(105,NULL,'2.2.1.2.3',NULL,59,2,2,NULL,104,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(106,NULL,'2.2.1.2.4',NULL,59,2,2,NULL,105,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(107,NULL,'2.2.1.3.1',NULL,59,2,2,NULL,106,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(108,NULL,'2.2.1.3.2',NULL,59,2,2,NULL,107,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(109,NULL,'2.2.1.3.3',NULL,59,2,2,NULL,108,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(110,NULL,'2.2.1.3.4',NULL,59,2,2,NULL,109,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(111,NULL,'2.2.2.1.1',NULL,60,2,2,NULL,110,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(112,NULL,'2.2.2.1.2',NULL,60,2,2,NULL,111,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(113,NULL,'2.2.2.1.3',NULL,60,2,2,NULL,112,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(114,NULL,'2.2.2.1.4',NULL,60,2,2,NULL,113,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(115,NULL,'2.2.2.2.1',NULL,60,2,2,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(116,NULL,'2.2.2.2.2',NULL,60,2,2,NULL,115,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(117,NULL,'2.2.2.2.3',NULL,60,2,2,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(118,NULL,'2.2.2.3.1',NULL,60,2,2,NULL,117,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(119,NULL,'2.2.2.3.2',NULL,60,2,2,NULL,118,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(120,NULL,'2.2.2.3.3',NULL,60,2,2,NULL,119,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(121,NULL,'2.2.3.1',NULL,60,2,2,NULL,120,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(122,NULL,'2.2.3.2',NULL,60,2,2,NULL,121,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(123,NULL,'2.2.3.3',NULL,60,2,2,NULL,122,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(124,NULL,'2.2.3.4',NULL,60,2,2,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(125,NULL,'3.1.1.1.1',NULL,61,3,3,NULL,124,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(126,NULL,'3.1.1.1.3',NULL,61,3,3,NULL,126,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(127,NULL,'3.1.1.2.1',NULL,61,3,NULL,NULL,127,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(128,NULL,'3.1.1.2.2',NULL,61,3,NULL,NULL,128,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(129,NULL,'3.1.1.2.3',NULL,61,3,NULL,NULL,129,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(130,NULL,'3.1.2.1',NULL,62,3,3,NULL,130,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(131,NULL,'3.1.2.2',NULL,62,3,3,NULL,131,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(132,NULL,'3.1.2.3',NULL,62,3,3,NULL,132,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(133,NULL,'3.1.2.4',NULL,62,3,3,NULL,133,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(134,NULL,'3.1.2.5',NULL,62,3,3,NULL,134,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(135,NULL,'3.1.3.1.1',NULL,63,3,3,NULL,135,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(136,NULL,'3.1.3.1.2',NULL,63,3,3,NULL,136,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(137,NULL,'3.1.3.1.3',NULL,63,3,3,NULL,137,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(138,NULL,'3.1.3.1.4',NULL,63,3,3,NULL,138,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(139,NULL,'3.1.3.2.1',NULL,63,3,3,NULL,139,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(140,NULL,'3.1.3.2.2',NULL,63,3,3,NULL,140,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(141,NULL,'3.1.3.2.3',NULL,63,3,3,NULL,141,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(142,NULL,'3.1.4.1.1',NULL,64,3,3,NULL,142,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(143,NULL,'3.1.4.1.2',NULL,64,3,3,NULL,143,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(144,NULL,'3.1.4.1.3',NULL,64,3,3,NULL,144,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(145,NULL,'3.1.4.2.1',NULL,64,3,3,NULL,145,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(146,NULL,'3.1.4.2.2',NULL,64,3,3,NULL,146,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(147,NULL,'3.1.4.3.1',NULL,64,3,3,NULL,147,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(148,NULL,'3.1.4.3.2',NULL,64,3,3,NULL,148,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(149,NULL,'3.1.4.3.3',NULL,64,3,3,NULL,149,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(150,NULL,'3.1.4.4.1',NULL,64,3,3,NULL,150,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(151,NULL,'3.1.4.4.2',NULL,64,3,3,NULL,151,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(152,NULL,'3.1.4.4.3',NULL,64,3,3,NULL,152,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(153,NULL,'3.1.4.5.1',NULL,64,3,3,NULL,153,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(154,NULL,'3.1.4.5.2',NULL,64,3,3,NULL,154,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(155,NULL,'3.1.5.1.1',NULL,65,3,NULL,NULL,155,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(156,NULL,'3.1.5.1.2',NULL,65,3,NULL,NULL,156,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(157,NULL,'3.1.5.1.3',NULL,65,3,NULL,NULL,157,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(158,NULL,'3.1.5.2.1',NULL,65,3,3,NULL,158,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(159,NULL,'3.1.5.2.2',NULL,65,3,3,NULL,159,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(160,NULL,'3.1.5.2.3',NULL,65,3,3,NULL,160,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(161,NULL,'3.1.5.2.4',NULL,65,3,3,NULL,161,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(162,NULL,'3.2.1.1.1',NULL,66,3,3,NULL,162,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(163,NULL,'3.2.1.1.2',NULL,66,3,3,NULL,163,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(164,NULL,'3.2.1.1.3',NULL,66,3,3,NULL,164,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(165,NULL,'3.2.1.1.4',NULL,66,3,3,NULL,165,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(166,NULL,'3.2.1.2.1',NULL,66,3,3,NULL,166,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(167,NULL,'3.2.1.2.2',NULL,66,3,3,NULL,167,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(168,NULL,'3.2.1.2.3',NULL,66,3,3,NULL,168,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(169,NULL,'3.2.2.1.1',NULL,67,3,3,NULL,169,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(170,NULL,'3.2.2.1.2',NULL,67,3,3,NULL,170,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(171,NULL,'3.2.2.1.3',NULL,67,3,3,NULL,171,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(172,NULL,'3.2.2.1.4',NULL,67,3,3,NULL,172,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(173,NULL,'3.2.2.2.1',NULL,67,3,3,NULL,173,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(174,NULL,'3.2.2.2.2',NULL,67,3,3,NULL,174,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(175,NULL,'3.2.2.2.3',NULL,67,3,3,NULL,175,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(176,NULL,'3.2.2.3.1',NULL,67,3,3,NULL,176,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(177,NULL,'3.2.2.3.2',NULL,67,3,3,NULL,177,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(178,NULL,'3.2.2.3.3',NULL,67,3,3,NULL,178,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(179,NULL,'3.2.2.3.4',NULL,67,3,3,NULL,179,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(180,NULL,'3.2.2.4.1',NULL,67,3,3,NULL,180,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(181,NULL,'3.2.2.4.2',NULL,67,3,3,NULL,181,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(182,NULL,'3.2.2.4.3',NULL,67,3,3,NULL,182,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(183,NULL,'3.2.2.4.4',NULL,67,3,3,NULL,183,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(184,NULL,'3.2.2.4.5',NULL,67,3,3,NULL,184,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(185,NULL,'3.2.2.4.6',NULL,68,3,3,NULL,185,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(186,NULL,'4.1.1.1',NULL,68,4,NULL,NULL,186,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(187,NULL,'4.1.1.2',NULL,68,4,NULL,NULL,187,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(188,NULL,'4.1.1.3',NULL,68,4,NULL,NULL,188,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(189,NULL,'4.1.1.4',NULL,68,4,NULL,NULL,189,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(190,NULL,'4.1.1.5',NULL,68,4,NULL,NULL,190,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(191,NULL,'4.1.1.6',NULL,68,4,NULL,NULL,191,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(192,NULL,'4.1.1.7',NULL,68,4,NULL,NULL,192,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(193,NULL,'4.1.1.8',NULL,68,4,NULL,NULL,193,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(194,NULL,'4.1.1.9',NULL,68,4,NULL,NULL,194,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(195,NULL,'4.1.1.10',NULL,68,4,NULL,NULL,195,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(196,NULL,'4.1.1.11',NULL,68,4,NULL,NULL,196,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(197,NULL,'4.1.1.12',NULL,68,4,NULL,NULL,197,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(198,NULL,'4.1.1.13',NULL,68,4,NULL,NULL,198,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(199,NULL,'4.1.2.1',NULL,68,4,NULL,NULL,199,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(200,NULL,'4.1.2.2',NULL,68,4,NULL,NULL,200,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(201,NULL,'4.1.2.3',NULL,68,4,NULL,NULL,201,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(202,NULL,'4.1.2.4',NULL,68,4,NULL,NULL,202,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(203,NULL,'4.1.2.5',NULL,68,4,NULL,NULL,203,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(204,NULL,'4.2.1.1',NULL,69,4,NULL,NULL,204,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(205,NULL,'4.2.1.2',NULL,69,4,NULL,NULL,205,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(206,NULL,'4.2.2.1',NULL,69,4,NULL,NULL,206,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(207,NULL,'4.2.2.2',NULL,69,4,NULL,NULL,207,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(208,NULL,'4.2.2.3',NULL,69,4,NULL,NULL,208,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(209,NULL,'4.2.3.1',NULL,69,4,NULL,NULL,209,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(210,NULL,'4.2.3.2',NULL,69,4,NULL,NULL,210,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(211,NULL,'4.2.3.3',NULL,69,4,NULL,NULL,211,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(212,NULL,'4.2.3.4',NULL,69,4,NULL,NULL,212,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(213,NULL,'4.2.3.5',NULL,69,4,NULL,NULL,213,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(214,NULL,'4.2.3.6',NULL,69,4,NULL,NULL,214,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(215,NULL,'4.2.3.7',NULL,69,4,NULL,NULL,215,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
  KEY `fk_sub_activity_description_activity1` (`activity_name`),
  CONSTRAINT `fk_sub_activity_description_activity1` FOREIGN KEY (`activity_name`) REFERENCES `activity_name` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_activity_name`
--

LOCK TABLES `sub_activity_name` WRITE;
/*!40000 ALTER TABLE `sub_activity_name` DISABLE KEYS */;
INSERT INTO `sub_activity_name` VALUES (1,'Sub-county stakeholder forum sensitization meetings in Eastern region (6 sub-counties)',1),(2,'Community mobilization & awareness campaigns in Eastern and Westen region',1),(3,'Calls for beneficiaries applications for e-voucher scheme',1),(4,'Screening & Selection of beneficiaries for e-voucher ',1),(5,'Signing beneficiaries\' commitments with PCU',1),(6,'Selection of category 2 farmers and farmer groups ',1),(7,'Calls for expression of application for agrodealers\' participation in e-voucher scheme',2),(8,'Application & Pre-selection of Agro-dealers by Sub-county committees',2),(9,'Review and final selection of Agrodealers by EBL',2),(10,'Discussion and signing of Agrodealers\' implementation commitments with PCU',2),(11,'linkage of agro-dealers to bulk suppliers',3),(12,'Technical backstoping support for acquisition of required inputs',3),(13,'Development & customization of training Materials for agro-dealers',3),(14,'Production of training Materials',3),(15,'Training of selected agrodealers ',3),(16,'Preparation of materials on adapted crop technologies (KALRO)',5),(17,'Soil nutrition and fertilizer application materials (IPNI)',5),(18,'Preparation of materails on crop protection',5),(19,'Materials on climate smart agriculture',5),(20,'Compilation into a joint publication',5),(21,'Printing of extension materials',5),(22,'Identification and selection of extension agents',6),(23,'Training of trainers - Western & Eastern regions',6),(24,'Follow-up on trainees',6),(25,'Identification and selection of trainees',7),(26,'Training of trainers',7),(27,'Identification of extension agents to be trained',8),(28,'Training of trainers',8),(29,'Identification and selection of sites',10),(30,'Establishment of demonstration plots ',10),(31,'Ongoing management of demonstration plots ',10),(32,'Identification and selection of demo plot locations at the ward level (host farmers)',11),(33,'Establishment of farmer-level demo plots',11),(34,'Ongoing management and feedback on technology adoption by farmers',11),(35,'Group level training of farmers',12),(36,'Farmer field days',12),(37,'Farmer exchange visits',12),(38,'Trade fairs in each sub-county for farmer exposure to best practices',12),(39,'Rapid training needs assessment - trainers; farmer groups',14),(40,'Preparation of customized training of trainer manuals',14),(41,'Printing of training materials',14),(42,'Identification and selection of trainers',15),(43,'Training of service providers ',15),(44,'Follow-up on trainees',15),(45,'Support to group self selection, formulation of group constitutions & election of leaders',17),(46,'Support to group registration with social services',17),(47,'Group accreditation to CGA',17),(48,'Participatory review of group status & needs',18),(49,'Participatory rapid preperation of group action plans',18),(50,'Training on organization & management',19),(51,'Training joint action in procurement & marketing',19),(52,'Facilitation of selected farmers for mutual learning visits',19),(53,'Follow-up after mutual learning visits',19),(54,'Support to farmers in quantification of inputs requirements and access points',21),(55,'Sensitization meetings to link farmer groups to agrodealers',21),(56,'Support to farmers in review of service requirements',22),(57,'Facilitation of meeting between farmers and service providers (including Financial services)',22),(58,'Support collective action in produce bulking',23),(59,'Facilitation of farmer to buyers (identified under Comp 2)',23),(60,'Ongoing field coordination',24),(61,'Facilitation of M&E activities',24),(62,'Preparation of training materials and hand-outs',26),(63,'Printing of training materials',26),(64,'Procurement of training demonstration kits',26),(65,'Identification and selection of trainers',27),(66,'Training of trainers',27),(67,'Mobilization of farmers for training, including selection of demonstration sites',28),(68,'Training of farmers',28),(69,'Practical demonstration',28),(70,'Follow-up on utilization of demonstration equipment and e-voucher post-harvest package',28),(71,'Farmer group mobilization to form aggregation groups',29),(72,'Support in group establishment and registration',29),(73,'Support in group action plan preparation',29),(74,'Capacity building training of secondary groups',29),(75,'Support to farmer identification & hire of produce collection stores ',31),(76,'Supply of basic equipment to farmer stores ',31),(77,'Farmer training on collection centre management',31),(78,'Support in farmer planning for produce collection',32),(79,'Technical support in management of stores',32),(80,'Technical support in produce disposal from stores',32),(81,'Identification and selection of facilities for support',34),(82,'Identification and selection of Civil works vendors',34),(83,'Construction of farmer-owned facilities',34),(84,'Refurbishment of public-owned facilities (Refurbishing and certification of existing warehouses)',34),(85,'Supply of equipment to supported warehouses',34),(86,'Establishment of warehouse management structures',35),(87,'Training of warehouse staff and management',35),(88,'Certification of warehouses',35),(89,'Certification of exisiting privately-owned storage facilities',35),(90,'Support in preparation of warehouse operations plan',36),(91,'Technical backstopping support in warehouse operations',36),(92,'Warehouse operational support up to financial sustainability ',36),(93,'Technical support in establishment of WRS ',37),(94,'Sensitization and training of farmers on WRS',37),(95,'Training of warehouse operators on WRS',37),(96,'Support to Comp 3 in establishment of WRS financing',37),(97,'Inventory of lead buyers',39),(98,'Engagement with potential buyers to identify those interested in building partnerships',39),(99,'Agreement with lead buyers on working modalities',39),(100,'Preparation of summary sheets on each buyer requirements for communication to farmers',39),(101,'Ongoing engagement with buyers',39),(102,'Mobilization of farmers for meetings with buyers',40),(103,'Sensitization of farmers on buyer requirements',40),(104,'Farmer organization training on marketing',40),(105,'Ongoing technical support to farmer organizations towards fulfillment of their market obligations',40),(106,'Technical support in trade negotiations',41),(107,'Follow-up coordination with both parties to ensure progress towards contract fuilfillment ',41),(108,'Technical support in produce aggregation & sales',41),(109,'Technical support in payments management for building trust',41),(110,'Elaboration and structuring of PPP model',43),(111,'Packaging of investment business cases (opportunities)',43),(112,'Investment opportunity campaigns',43),(113,'Identification and selection of investments for support',43),(114,'Technical support in feasibility study',44),(115,'Linkages to financing (Comp 3)',44),(116,'Technical support in start-up & management',44),(117,'Technical backstopping in feasibility study',45),(118,'Linkage to financing (Comp 3)',45),(119,'Linkage to farmers for materials supply',45),(120,'Identification of areas for spot improvement',46),(121,'Identification and selection of civil works vendors',46),(122,'Spot improvement civil works',46),(123,'Oversight of civil works',46),(124,'Review of list of selected farmers',48),(125,'Sensitization of selected farmers on account opening procedures',48),(126,'Enrolment of the selected farmers - issued with cards',48),(127,'Support to farmer groups\' establishment of savings mobilization structures & plans (table banking etc)',49),(128,'Ongoing savings mobilization by farmers',49),(129,'Review of farmer savings to trigger release of e-voucher',49),(130,'Mobilization of farmers for training',50),(131,'Training of farmers in Western Kenya (maize zone)',50),(132,'Training of farmers in Eastern (Sorghum/millet zone) ',50),(133,'Follow-up on trained farmers to strengthen adoption',50),(134,'Ongoing coordination',50),(135,'Review and selection of agrodealers in Eastern',52),(136,'Sensitization and training of selected agrodealers',52),(137,'Enrolment of the selected agrodealers',52),(138,'Installation of POS devices and training on operation',52),(139,'Mobilization of participating agrodealers',53),(140,'Financial literacy training',53),(141,'Follow-up to encourage adoption of practices',53),(142,'Consultation with e-voucher supply chain players for bulk supply',55),(143,'Sub-county specific valuation of e-voucher technical package with agrodealers',55),(144,'Preparation and approval of e-voucher values for selected farmers',55),(145,'Coordination of farmers to place orders with agro-dealers',56),(146,'Support to agrodealers in stock projections & supply',56),(147,'Sumission of approved e-voucher list to EBL',57),(148,'Review and processing of e-vouchers into farmer accounts',57),(149,'Ongoing coordination with agro-dealers & PCU for smooth accessibility of e-funds',57),(150,'Farmer mobilization for inputs collection & use',58),(151,'Coordination of any issues arising for expeditious resolution ',58),(152,'Close monitoring to minimize moral hazards',58),(153,'Voucher value for Maize, sorghum & millet for year 1',59),(154,'GoK contribution for maize, sorghum & millet voucher value for year 1',59),(155,'Study on adapted financial products for smallholder farmers',61),(156,'Design of adapted financial products for cereal farmers',61),(157,'Testing of adapted financial products',61),(158,'Establishment of criteria for financial graduation',62),(159,'Establishment of M&E system for FG',62),(160,'FG measurement of participating farmers',62),(161,'Ongoing FG monitoring and evaluation',62),(162,'Development of WRS financing products',64),(163,'Development & customization of WRS financing platform',64),(164,'Training of staff and warehouse operators on WRS financing',64),(165,'Mobilization and training of farmers on WRS financing',64),(166,'Coordination of farmer financing requests',65),(167,'Processing and disbursements into farmer accounts ',65),(168,'Ongoing monitoring for feedback on improvements',65),(169,'Formulation of financing window for category 2 farmers',67),(170,'Mobilization of farmers for account opening and preparations for financing eligibility',67),(171,'Support to farmers application for financing',67),(172,'Processing and disbursements of finance ',67),(173,'Mobilization of agrodealers to utilize financing window developed in 2015',68),(174,'Opening of financing facilities for agrodealers',68),(175,'Ongoing support for appropriate utilization of facility',68),(176,'Establishment of financing window for service providers',69),(177,'Mobilization of SPs for account opening and preparations for eligibility',69),(178,'SP financing',69),(179,'Follow-up support & monitoring',69),(180,'Establishment of financing window for PPP ventures',70),(181,'Sensitization of potential applicants on application process',70),(182,'Review of proposals (in coordination with Comp 2)',70),(183,'Disbursements',70),(184,'Follow-up for successful operations and repayments',70),(185,'Supporting the establishment of LLC linked to each storage facility by legal advisor',70),(186,'Payment of utilities - Security & interner services',72),(187,'Programme branding',72),(188,'Payment for insurance for vehicles',72),(189,'Maintaince of vehicles',72),(190,'Group medical & accidents insurance covers',72),(191,'Payment for Publications ',72),(192,'Payment of PCU Staff salaries ',72),(193,'Office supplies and consumables',72),(194,'Procurement of other office equipments & accessories',72),(195,'Procurement of vehicles',72),(196,'Communication items',72),(197,'Payments for PCU staff Trainings',72),(198,'Payment of Professional body membership',72),(199,'Programme Steering Committee Meeting',73),(200,'County Programme Coordinating Comettee (CPCC) meetings  ',73),(201,'Quarterly Sub County stakeholder meetings',73),(202,'Coordination meetings and workshops',73),(203,'Attending IFAD regional implementation workshops',73),(204,'Gender and social inclusion (GESI) study',75),(205,'GESI workshop',75),(206,'Preparation of survey methodology & work plan ',76),(207,'Baseline/RIMS  survey',76),(208,'Conducting stakeholder workshop',76),(209,'M&E Annual review meeting',77),(210,'M&E quarterly field follow-up visits',77),(211,'Implementing partners AWPB Preparation workshops',77),(212,'M&E quarterly planning and review Meetings',77),(213,'Trainings of PCU & County staff on KM',77),(214,'Participate in IFAD Supervision Mission',77),(215,'Preparation of consolidated programme reports',77);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_component`
--

LOCK TABLES `sub_component` WRITE;
/*!40000 ALTER TABLE `sub_component` DISABLE KEYS */;
INSERT INTO `sub_component` VALUES (1,'Effective Agricultural Services',1),(2,'Supporting demand of services and inputs (farmer organization and capacity building)',1),(3,'Post harvest management',2),(4,'Market Linkages and Value Addition',2),(5,'Financial Inclusion',3),(6,'Value Chain financing',3);
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
  `module` smallint(5) unsigned DEFAULT NULL,
  `trainer` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_topic_topic1` (`module`),
  KEY `fk_topic_phenomenon1` (`trainer`),
  CONSTRAINT `fk_topic_phenomenon1` FOREIGN KEY (`trainer`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_topic_topic1` FOREIGN KEY (`module`) REFERENCES `topic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'Managing working capital',NULL,NULL),(2,'Managing stock ',NULL,NULL),(3,'Costing and Pricing',NULL,NULL),(4,'Selling and Marketing ',NULL,NULL),(5,'Basic Business Record keeping',NULL,NULL),(6,'Seed Management ',NULL,NULL),(7,'Fertilizer and Soil health management',NULL,NULL),(8,'Crop protection products',NULL,NULL),(9,'Market and markets requirements',NULL,NULL),(10,'Marketing',NULL,NULL),(11,'Financial Services',NULL,NULL),(12,'Budgeting',NULL,NULL),(13,'Savings',NULL,NULL),(14,'Debt Management',NULL,NULL),(15,'Farmer organizational development',NULL,NULL),(16,'Ecological Requirement',NULL,NULL),(17,'Crop and crop varieties',NULL,NULL),(18,'Crop Management',NULL,NULL),(19,'Pests and disease control',NULL,NULL),(20,'Post-harvest handling techniques',NULL,NULL),(21,'Soil types and characteristics',16,NULL),(22,'Rainfall',16,NULL),(23,'Soil PH',16,NULL),(24,'Temperature',16,NULL),(25,'Crop varieties',17,NULL),(26,'Choice of varieties',17,NULL),(27,'Land preparation',18,NULL),(28,'Planting',18,NULL),(29,'Fertilizers types and application',18,NULL),(30,'Nutrient deficiency',18,NULL),(31,'Weeding',18,NULL),(32,'Capping',18,NULL),(33,'Thinning',18,NULL),(34,'Common pest ',19,NULL),(35,'Common disease',19,NULL),(36,'Signs and symptoms',19,NULL),(37,'Cultural and chemical controls',19,NULL),(38,'Signs of maturity',20,NULL),(39,'Harvesting method',20,NULL),(40,'Harvesting equipment',20,NULL),(41,'Drying',20,NULL),(42,'Post-harvest pest and disease and control methods',20,NULL),(43,'Packaging and storage',20,NULL),(44,'Utilization and processing',21,NULL),(45,'Value addition',21,NULL),(46,'Processing equipment',21,NULL);
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
  `training` int(10) unsigned NOT NULL,
  `phenomenon` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_trainer_training1` (`training`),
  KEY `fk_trainer_phenomenon1` (`phenomenon`),
  CONSTRAINT `fk_trainer_phenomenon1` FOREIGN KEY (`phenomenon`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
  `category_of_trainees` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_training_location1` (`venue`),
  KEY `fk_training_training_topic1` (`topic`),
  KEY `fk_training_phenomenon1` (`category_of_trainees`),
  CONSTRAINT `fk_training_location1` FOREIGN KEY (`venue`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_training_phenomenon1` FOREIGN KEY (`category_of_trainees`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
  `warehouse_type` int(10) unsigned NOT NULL,
  `warehouse_operator` int(10) unsigned DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `units` smallint(5) unsigned DEFAULT NULL,
  `offers_wrs` tinyint(1) DEFAULT NULL,
  `certified` tinyint(1) DEFAULT NULL,
  `location` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_warehouse_units1` (`units`),
  KEY `fk_warehouse_location1` (`location`),
  KEY `fk_warehouse_phenomenon1` (`warehouse_operator`),
  KEY `fk_warehouse_phenomenon2` (`warehouse_type`),
  CONSTRAINT `fk_warehouse_location1` FOREIGN KEY (`location`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_phenomenon1` FOREIGN KEY (`warehouse_operator`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_phenomenon2` FOREIGN KEY (`warehouse_type`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_units1` FOREIGN KEY (`units`) REFERENCES `measurement_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `warehouse_operation`
--

DROP TABLE IF EXISTS `warehouse_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse_operation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `warehouse` int(10) unsigned NOT NULL,
  `quantity_brought` double DEFAULT NULL,
  `produce_type_brought` int(10) unsigned DEFAULT NULL,
  `selling_date` date DEFAULT NULL,
  `quantity_sold` double DEFAULT NULL,
  `produce_type_sold` int(10) unsigned DEFAULT NULL,
  `selling_price` decimal(18,2) DEFAULT NULL,
  `buyer` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_warehouse_operation_static_input1` (`produce_type_brought`),
  KEY `fk_warehouse_operation_static_input2` (`produce_type_sold`),
  KEY `fk_warehouse_operation_warehouse1` (`warehouse`),
  CONSTRAINT `fk_warehouse_operation_static_input1` FOREIGN KEY (`produce_type_brought`) REFERENCES `static_input` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_operation_static_input2` FOREIGN KEY (`produce_type_sold`) REFERENCES `static_input` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_operation_warehouse1` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse_operation`
--

LOCK TABLES `warehouse_operation` WRITE;
/*!40000 ALTER TABLE `warehouse_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse_operation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-05  9:35:59
