
--
-- Dumping data for table `result_hierarchy`
--

LOCK TABLES `result_hierarchy` WRITE;
/*!40000 ALTER TABLE `result_hierarchy` DISABLE KEYS */;
INSERT INTO `result_hierarchy` VALUES 
(1,'Goal: National food security improved',NULL,NULL),
(2,'Objective 1: Increased production of targeted cereal staples (maize, sorghum, millet and associated pulses)',NULL,NULL),
(3,'Objective 2: Increased income of smallholders in medium and high potential production areas',NULL,NULL),
(4,'Outcome 1: Outcome 1: Productivity for maize, sorghum, finger millet and pulses increased',NULL,NULL),
(5,'Output 1.1; Agricultural services/ inputs improved',NULL,NULL),
(6,'Output 1.2; Capacity of farmers’ organizations to mobilize extension services, participate in local planning and value chains fora improved',NULL,NULL),
(7,'Outcome 2; Post harvest practices and market linkages for targeted VCs improved',NULL,NULL),
(8,'Output 2.1; Post-harvest management improved',NULL,NULL),
(9,'Output 2.2; Market Access for participating smallholders improved',NULL,NULL),
(10,'Outcome 3; Financial inclusion of beneficiaries improved',NULL,NULL),
(11,'Output 3.1 : Use of financial tools and services by target groups increased',NULL,NULL),
(12,'Output 3.2: Access to value chain financing improved',NULL,NULL);
/*!40000 ALTER TABLE `result_hierarchy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `performance_indicator`
--

LOCK TABLES `performance_indicator` WRITE;
/*!40000 ALTER TABLE `performance_indicator` DISABLE KEYS */;
INSERT INTO `performance_indicator` VALUES 
(1,18,1,'a. Uplifting of at least 32,000 households from poverty',NULL,NULL,NULL,NULL,NULL,NULL),
(2,16,1,'b. Reduced poverty',NULL,NULL,NULL,NULL,NULL,NULL),
(3,15,1,'c. Number of households with improvement in assets ownership index',NULL,NULL,NULL,NULL,NULL,NULL),
(4,15,1,'d. Percentage reduction in the prevalence of child malnutrition',NULL,NULL,NULL,NULL,NULL,NULL),
(5,15,1,'e. HH experiencing one hungry season per year',NULL,NULL,NULL,NULL,NULL,NULL),
(6,16,1,'f. Reduced hunger',NULL,NULL,NULL,NULL,NULL,NULL),
(12,18,2,'a. National grain deficit reduced by 41,000T/ 10% national deficit',NULL,NULL,NULL,NULL,NULL,NULL),
(13,15,2,'b. Number targeted households using improved inputs voluntarily (min 32,000HH)',NULL,NULL,NULL,NULL,NULL,NULL),
(14,15,2,'c.  Number targeted small farmers in commercial contracts (min 55,000),',NULL,NULL,NULL,NULL,NULL,NULL),
(15,15,2,'d. Number targeted smallholders engaged in financial services (i.e taking investment loans), RIMS2',NULL,NULL,NULL,NULL,NULL,NULL),
(16,15,2,'e. Number of targeted households bulking and selling grains at price 30% higher than farmgate (min 80,000),',NULL,NULL,NULL,NULL,NULL,NULL),
(17,18,2,'f. Number of women and youth empowered',NULL,NULL,NULL,NULL,NULL,NULL),
(25,15,4,'a. Improved agricultural production (RIMS2)\nTargets: \nMaize: 0.9 to 1.8 MT/acre\nSorghum;0.6 to 1.2 MT/acre\nMillet; 0.7 to 1.3 MT/acre\nPulses; 0.3 to 0.6 MT/acre',NULL,NULL,NULL,NULL,NULL,NULL),
(26,18,4,'b. Improved practices/packages adopted by 62,000 HH(32,000 subsistence, 30,000 advanced, 50% women)',NULL,NULL,NULL,NULL,NULL,NULL),
(27,15,4,'c. No of farmers using purchased inputs',NULL,NULL,NULL,NULL,NULL,NULL),
(28,17,4,'d. No. of farmers adopting modern techniques (Embu, Bungoma, Nakuru and Kitui)',NULL,NULL,NULL,NULL,NULL,NULL),
(29,15,4,'e. No / Percentage of farmers reporting production/ improved yields',NULL,NULL,NULL,NULL,NULL,NULL),
(30,15,4,'f. No of farmers adopting recommended technologies',NULL,NULL,NULL,NULL,NULL,NULL),
(31,15,4,'g. No of groups functional after 3 years',NULL,NULL,NULL,NULL,NULL,NULL),
(32,18,5,'a. 40,000 HH subsistence farmers issued with input package',NULL,NULL,NULL,NULL,NULL,NULL),
(33,18,5,'b. 300 agro-dealers trained',NULL,NULL,NULL,NULL,NULL,NULL),
(34,15,5,'c. Staff of services providers trained',NULL,NULL,NULL,NULL,NULL,NULL),
(35,15,5,'d. No. of people trained in crop production practices and technologies',NULL,NULL,NULL,NULL,NULL,NULL),
(36,15,5,'e. No. of people accessing facilitated advisory services',NULL,NULL,NULL,NULL,NULL,NULL),
(40,18,6,'a. 1,333 farmer groups in organizational management trained',NULL,NULL,NULL,NULL,NULL,NULL),
(41,15,6,'b. No. of crop production groups formed/ trained',NULL,NULL,NULL,NULL,NULL,NULL),
(42,18,6,'c. No. of crop production groups formed',NULL,NULL,NULL,NULL,NULL,NULL),
(43,15,6,'d. No. of formed crop production groups with women in leadership positions',NULL,NULL,NULL,NULL,NULL,NULL),
(44,18,7,'a. Improved grain drying technologies adopted by 32,000 subsistence HH farmers',NULL,NULL,NULL,NULL,NULL,NULL),
(45,18,7,'b. Improved grain storage technologies adopted by 32,000 subsistence HH farmers',NULL,NULL,NULL,NULL,NULL,NULL),
(46,18,7,'c. Certified warehouses used by 80,000 HH (32,000 subsistence and 48,000 advanced)',NULL,NULL,NULL,NULL,NULL,NULL),
(47,18,7,'d. Post-harvest grain losses reduced from 25 to 5% for 80,000 (32,000 subsistence and 48,000 advanced)',NULL,NULL,NULL,NULL,NULL,NULL),
(48,18,7,'e. Operational self-sufficiency attained for 60 warehouses',NULL,NULL,NULL,NULL,NULL,NULL),
(49,15,7,'f. Producers benefiting from improved access to markets',NULL,NULL,NULL,NULL,NULL,NULL),
(50,15,7,'g. No. of functioning infrastructure after 3 years',NULL,NULL,NULL,NULL,NULL,NULL),
(51,15,7,'h. No. of functioning storage & processing facilities after 3 years',NULL,NULL,NULL,NULL,NULL,NULL),
(52,18,8,'a. 40,000 subsistence farmers trained on post-harvest grain management',NULL,NULL,NULL,NULL,NULL,NULL),
(53,15,8,'b. People trained on post-production and marketing',NULL,NULL,NULL,NULL,NULL,NULL),
(54,18,8,'c. 60 warehouses certified',NULL,NULL,NULL,NULL,NULL,NULL),
(55,18,8,'d. 100 collection centres rehabilitated',NULL,NULL,NULL,NULL,NULL,NULL),
(56,15,8,'e. No. or storage facilities constructed/ rehabilitated',NULL,NULL,NULL,NULL,NULL,NULL),
(57,15,8,'f. No. of processing facilities constructed/rehabilitated',NULL,NULL,NULL,NULL,NULL,NULL),
(58,17,8,'g. No. of storage facilities constructed(Embu, Nakuru and Kitui)',NULL,NULL,NULL,NULL,NULL,NULL),
(59,18,9,'a. 100,000 HH beneficiaries trained in business partnership',NULL,NULL,NULL,NULL,NULL,NULL),
(60,18,9,'b. 60 initiatives of commercial partnership implemented ',NULL,NULL,NULL,NULL,NULL,NULL),
(61,18,9,'c. 20 access road improvement completed (spot road improvements)',NULL,NULL,NULL,NULL,NULL,NULL),
(62,15,9,'d. Roads constructed/ rehabilitated',NULL,NULL,NULL,NULL,NULL,NULL),
(63,18,10,'a. 95,000 farmers producing grain and pulses have access to financial services',NULL,NULL,NULL,NULL,NULL,NULL),
(64,15,10,'b. Improved access of the poor to financial services',NULL,NULL,NULL,NULL,NULL,NULL),
(65,18,10,'c. Number and type of new products implemented by EBL1',NULL,NULL,NULL,NULL,NULL,NULL),
(66,15,10,'d. Improved performance of financial institutions',NULL,NULL,NULL,NULL,NULL,NULL),
(67,18,11,'a. 40,000 vouchers used by target groups',NULL,NULL,NULL,NULL,NULL,NULL),
(68,18,11,'b. 62,500 HH using certified warehouse receipting system',NULL,NULL,NULL,NULL,NULL,NULL),
(69,18,11,'c. 40,000 category 1 HH trained in financial literacy',NULL,NULL,NULL,NULL,NULL,NULL),
(70,15,11,'d. People trained on financial services  (male, female, youth)',NULL,NULL,NULL,NULL,NULL,NULL),
(71,18,11,'e. 200 entrepreneurs trained in advanced financial training',NULL,NULL,NULL,NULL,NULL,NULL),
(72,15,11,'f. Bank staff trained',NULL,NULL,NULL,NULL,NULL,NULL),
(73,18,11,'g. No of farmers trained',NULL,NULL,NULL,NULL,NULL,NULL),
(74,15,11,'h. Value of voluntary savings',NULL,NULL,NULL,NULL,NULL,NULL),
(75,15,11,'i. financial institutions participating in the project',NULL,NULL,NULL,NULL,NULL,NULL),
(76,15,11,'j. No of active voluntary savers (RIMS1) (males, female, youth)',NULL,NULL,NULL,NULL,NULL,NULL),
(77,15,11,'k. Number of active borrowers',NULL,NULL,NULL,NULL,NULL,NULL),
(78,15,11,'l. Value of gross loan portfolio by project beneficiaries',NULL,NULL,NULL,NULL,NULL,NULL),
(79,17,11,'m. No. / % increase in access to credit facilities (Bungoma, Kakamega and Nandi)',NULL,NULL,NULL,NULL,NULL,NULL),
(80,15,11,'n. Total amount of savings by target group(by gender)-target to be defined at start up',NULL,NULL,NULL,NULL,NULL,NULL),
(81,18,12,'a. 100 value chain financing ventures completed',NULL,NULL,NULL,NULL,NULL,NULL),
(82,18,12,'b. 80 forward contracts signed',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `performance_indicator` ENABLE KEYS */;
UNLOCK TABLES;

