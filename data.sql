/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  siech
 * Created: Jun 27, 2016
 */

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (3,'0701404084','agrodealer@gmail.com','203 Bomet'),(4,'0701404086','countyofficer@gmail.com','203 Bomet'),(5,'0701404087','equity@gmail.com','203 Bomet'),(6,'0701404088','farmer@gmail.com','203 Bomet'),(7,'0701404089','kalroofficer@gmail.com','203 Bomet'),(8,'0701404081','nationalofficer@gmail.com','202 Bomet'),(9,'0701404082','regionalcoordinator@gmail.com','203 Bomet'),(10,'0701404080','subcounty@gmail.com','203 Bomet'),(11,'0701404041','warehouseoperator@gmail.com','203 Bomet'),(12,'0701404086','wao@gmail.com','203 Bomet');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (3,1,1,1,NULL,NULL),(4,1,15,19,NULL,NULL),(5,1,1,1,NULL,NULL),(6,1,1,6,NULL,NULL),(7,1,1,7,NULL,NULL),(8,1,1,7,NULL,NULL),(9,1,1,1,NULL,NULL),(10,1,4,5,NULL,NULL),(11,6,7,6,NULL,NULL),(12,3,3,6,NULL,NULL),(13,3,3,3,NULL,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (3,'Ben Siech',2,'29820458','1993-06-22','Millet growing',NULL,NULL,NULL,12,3),(4,'Ben Siech',2,'29820459','1993-06-22','Millet growing',NULL,NULL,NULL,3,4),(5,'Ben Siech',2,'29820460','1993-06-22','Millet growing',NULL,NULL,NULL,4,5),(6,'Ben Siech',2,'29820461','1993-06-22','',NULL,NULL,NULL,5,6),(7,'Ben Siech',2,'29820463','2016-06-22','Millet growing',NULL,NULL,NULL,6,7),(8,'Ben Siech',2,'29820451','2016-06-22','Millet growing',NULL,NULL,NULL,7,8),(9,'Ben Siech',1,'29820452','2016-06-22','Millet growing',NULL,NULL,NULL,8,9),(10,'Ben Siech',2,'29820453','2016-06-22','Millet growing',NULL,NULL,NULL,9,10),(11,'Ben Siech',2,'29820455','2016-06-22','Millet growing',NULL,NULL,NULL,10,11),(12,'Ben Siech',2,'29820456','2016-06-22','Millet growing',NULL,NULL,NULL,11,12);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `programme`
--

LOCK TABLES `programme` WRITE;
/*!40000 ALTER TABLE `programme` DISABLE KEYS */;
INSERT INTO `programme` VALUES (1,'KCEP implementation','06/24/2016','06/24/2016',NULL,'20000','23000','1200000','100000','91000',1,NULL,1),(2,'KCEP optimization','06/24/2016','06/24/2016',NULL,'500000','2500000','2200000','410000','412000',3,NULL,3),(3,'KCEP actualization','06/24/2016','06/24/2016',6,'600000','450000','35000000','2000000','2100000',2,NULL,2),(4,'KCEP appropriation','06/24/2016','06/24/2016',5,'200000','500000','700000','150000','165000',1,2,3);
/*!40000 ALTER TABLE `programme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (2,3,'agrodealer@gmail.com','35e8f79d9e51f0a3970f9cadc210ddec20d377af1e6a6d09c2f4eff3a9efa0d8',2),(3,4,'countyofficer@gmail.com','86ba3fc5f3c904d88523fe117921e46ce21f000b71386471d43367e6e86193fe',5),(4,5,'equity@gmail.com','3e0abfe6dc7124b22dc288240ab281552628179686a89fa3f09b8fd839f2453a',10),(5,6,'farmer@gmail.com','ba2a06effc4ee674b193ba47e22af92dda8e5c83b6657bb1d105deb6f2b4fa5f',1),(6,7,'kalroofficer@gmail.com','b163088866b02553aac34836f7699c1dcb5ccba391edd8efbdc21f664cefd0e7',8),(7,8,'nationalofficer@gmail.com','150a83b58026957b23ad46de7e69aa9de4e406cb6e10b7e92e940ad224687ace',7),(8,9,'regionalcoordinator@gmail.com','1abfa2598a1c1e5e722af1b81f8594330531d916f84cd18b804b8efabb431b21',6),(9,10,'subcounty@gmail.com','8d5e355759e9bbaeb101db1129e489b4e0acce93f38e6ea61d5c416bf872c924',4),(10,11,'warehouseoperator@gmail.com','f671352bd741b3226ed0126d2bc06d2e2ca485ee4ab0dbe8c3ab16a9b4a9b71b',11),(11,12,'wao@gmail.com','59c094d3f594d8900c5db0d90574d9f65c779b4101a02a8cb6d7f1c8586f1af5',3);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
