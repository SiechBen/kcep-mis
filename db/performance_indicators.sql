
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
INSERT INTO `result_hierarchy` VALUES (1,'Goal: National food security improved',NULL,NULL),(2,'Objective 1: Increased production of targeted cereal staples (maize, sorghum, millet and associated pulses)',NULL,NULL),(3,'Objective 2: Increased income of smallholders in medium and high potential production areas',NULL,NULL),(4,'Outcome 1: Outcome 1: Productivity for maize, sorghum, finger millet and pulses increased',NULL,NULL),(5,'Output 1.1; Agricultural services/ inputs improved',NULL,NULL),(6,'Output 1.2; Capacity of farmers’ organizations to mobilize extension services, participate in local planning and value chains fora improved',NULL,NULL),(7,'Outcome 2; Post harvest practices and market linkages for targeted VCs improved',NULL,NULL),(8,'Output 2.1; Post-harvest management improved',NULL,NULL),(9,'Output 2.2; Market Access for participating smallholders improved',NULL,NULL),(10,'Outcome 3; Financial inclusion of beneficiaries improved',NULL,NULL),(11,'Output 3.1 : Use of financial tools and services by target groups increased',NULL,NULL),(12,'Output 3.2: Access to value chain financing improved',NULL,NULL);
/*!40000 ALTER TABLE `result_hierarchy` ENABLE KEYS */;
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

