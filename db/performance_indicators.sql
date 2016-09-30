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
-- Table structure for table `performance_indicator`
--

DROP TABLE IF EXISTS `performance_indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performance_indicator` (
  `id` smallint(3) unsigned NOT NULL AUTO_INCREMENT,
  `performance_indicator_type` int(10) unsigned DEFAULT '18',
  `result_hierarchy` smallint(3) unsigned DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `baseline_date` date DEFAULT NULL,
  `baseline_value` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_performance_indicator_phenomenon1` (`performance_indicator_type`),
  KEY `fk_performance_indicator_result_hierarchy1` (`result_hierarchy`),
  CONSTRAINT `fk_performance_indicator_phenomenon1` FOREIGN KEY (`performance_indicator_type`) REFERENCES `phenomenon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_performance_indicator_result_hierarchy1` FOREIGN KEY (`result_hierarchy`) REFERENCES `result_hierarchy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-29 15:33:33
