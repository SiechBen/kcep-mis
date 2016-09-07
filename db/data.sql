
--
-- Dumping data for table `ebl_branch`
--

LOCK TABLES `ebl_branch` WRITE;
/*!40000 ALTER TABLE `ebl_branch` DISABLE KEYS */;
INSERT INTO `ebl_branch` VALUES (1,'Nakuru Kenyatta Avenue');
/*!40000 ALTER TABLE `ebl_branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'0701404084','siele.bernard@gmail.com','203 Bomet'),(2,'0701404084','agrodealer@gmail.com','203 Bomet'),(3,'0701404086','countyofficer@gmail.com','203 Bomet'),(4,'0701404087','equity@gmail.com','203 Bomet'),(5,'0701404088','farmer@gmail.com','203 Bomet'),(6,'0701404089','kalroofficer@gmail.com','203 Bomet'),(7,'0701404081','nationalofficer@gmail.com','202 Bomet'),(8,'0701404082','regionalcoordinator@gmail.com','203 Bomet'),(9,'0701404080','subcounty@gmail.com','203 Bomet'),(10,'0701404041','warehouseoperator@gmail.com','203 Bomet'),(11,'0701404086','wao@gmail.com','203 Bomet'),(12,'0725287162','ss@anynomous.ac.ke','203 Muminji');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,5,8,7,35.8035000,0.2983000,NULL,NULL),(3,1,1,1,NULL,NULL,NULL,NULL),(4,1,15,19,NULL,NULL,NULL,NULL),(5,1,1,1,NULL,NULL,NULL,NULL),(6,1,1,6,NULL,NULL,NULL,NULL),(7,1,1,7,NULL,NULL,NULL,NULL),(8,1,1,7,NULL,NULL,NULL,NULL),(9,1,9,1,NULL,NULL,NULL,NULL),(10,1,4,5,NULL,NULL,NULL,NULL),(11,6,7,6,NULL,NULL,NULL,NULL),(12,3,3,6,NULL,NULL,NULL,NULL),(13,3,3,3,NULL,NULL,NULL,NULL),(14,1,1,48,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Ben Siech',2,'29820457','1993-06-19',NULL,'Millet growing',NULL,NULL,NULL,1,1,NULL,0),(3,'Ben Siech',2,'29820458','1993-06-22',NULL,'Millet growing',NULL,NULL,NULL,12,3,NULL,0),(4,'Ben Siech',2,'29820459','1993-06-22',NULL,'Millet growing',NULL,NULL,NULL,3,4,NULL,0),(5,'Ben Siech',2,'29820460','1993-06-22',NULL,'Millet growing',NULL,NULL,NULL,4,5,NULL,0),(6,'Ben Siech',2,'29820461','1993-06-22',NULL,'',NULL,NULL,NULL,5,6,2,0),(7,'Ben Siech',2,'29820463','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,6,7,NULL,0),(8,'Ben Siech',2,'29820451','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,7,8,NULL,0),(9,'Ben Siech',1,'29820452','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,8,9,NULL,0),(10,'Ben Siech',2,'29820453','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,9,10,NULL,0),(11,'Ben Siech',2,'29820455','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,10,11,NULL,0),(12,'Ben Siech',2,'29820456','2016-06-22',NULL,'Millet growing',NULL,NULL,NULL,11,12,NULL,0),(14,'Bwana mkubwa',1,'29820420','2016-06-29',NULL,'Large scale millet growing',NULL,NULL,NULL,14,2,NULL,0);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,1,'siele.bernard@gmail.com','1aca6dca73cc19c35147c3c7d2707ca78dd2495c7075c4b41912c140c4b39c00',9),(2,3,'agrodealer@gmail.com','35e8f79d9e51f0a3970f9cadc210ddec20d377af1e6a6d09c2f4eff3a9efa0d8',2),(3,4,'countyofficer@gmail.com','86ba3fc5f3c904d88523fe117921e46ce21f000b71386471d43367e6e86193fe',5),(4,5,'equity@gmail.com','3e0abfe6dc7124b22dc288240ab281552628179686a89fa3f09b8fd839f2453a',10),(5,6,'farmer@gmail.com','ba2a06effc4ee674b193ba47e22af92dda8e5c83b6657bb1d105deb6f2b4fa5f',1),(6,7,'kalroofficer@gmail.com','b163088866b02553aac34836f7699c1dcb5ccba391edd8efbdc21f664cefd0e7',8),(7,8,'nationalofficer@gmail.com','150a83b58026957b23ad46de7e69aa9de4e406cb6e10b7e92e940ad224687ace',7),(8,9,'regionalcoordinator@gmail.com','1abfa2598a1c1e5e722af1b81f8594330531d916f84cd18b804b8efabb431b21',6),(9,10,'subcounty@gmail.com','8d5e355759e9bbaeb101db1129e489b4e0acce93f38e6ea61d5c416bf872c924',4),(10,11,'warehouseoperator@gmail.com','f671352bd741b3226ed0126d2bc06d2e2ca485ee4ab0dbe8c3ab16a9b4a9b71b',11),(11,12,'wao@gmail.com','59c094d3f594d8900c5db0d90574d9f65c779b4101a02a8cb6d7f1c8586f1af5',3),(12,14,'ss@anynomous.ac.ke','b34e62121d7076d6deb48917adac921b9007084c6133849867ea1c72c7fcaf24',1);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'35453345',4334,1,'1234',6),(2,'23432424',4565,1,'324',12);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
