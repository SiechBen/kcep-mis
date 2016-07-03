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
INSERT INTO `contact` VALUES (1,'0701404084','siele.bernard@gmail.com','203 Bomet'),(3,'0701404084','agrodealer@gmail.com','203 Bomet'),(4,'0701404086','countyofficer@gmail.com','203 Bomet'),(5,'0701404087','equity@gmail.com','203 Bomet'),(6,'0701404088','farmer@gmail.com','203 Bomet'),(7,'0701404089','kalroofficer@gmail.com','203 Bomet'),(8,'0701404081','nationalofficer@gmail.com','202 Bomet'),(9,'0701404082','regionalcoordinator@gmail.com','203 Bomet'),(10,'0701404080','subcounty@gmail.com','203 Bomet'),(11,'0701404041','warehouseoperator@gmail.com','203 Bomet'),(12,'0701404086','wao@gmail.com','203 Bomet'),(13,'0725287162','ss@anynomous.ac.ke','203 Muminji'),(14,'0727080918','henrykchirchir@gmail.com',''),(15,'0728254281','enockkkorir@gmail.com',''),(16,'0726365000','leahjterer@gmail.com',''),(17,'0724333448','koilelstores@gmail.com',''),(18,'0712444630','bentallam@gmail.com',''),(19,'0723144351','marthakiay@gmail.com',''),(20,'0721571079','willyrop@gmail.com',''),(21,'0720402148','henryrop@gmail.com',''),(22,'0702954085','judycherop@gmail.com',''),(23,'0720792007','janekiprotich@gmail.com',''),(24,'0721943674','dorcaschepkwony@gmail.com',''),(25,'0726253218','dinahcherotich@gmail.com',''),(26,'0721893081','johnkemboi@gmail.com',''),(27,'0720837777','petermutai@gmail.com',''),(28,'0717153900','christophersawe@gmail.com','');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,5,8,7,35.8035000,0.2983000,NULL),(3,1,1,1,NULL,NULL,NULL),(4,1,15,19,NULL,NULL,NULL),(5,1,1,1,NULL,NULL,NULL),(6,1,1,6,NULL,NULL,NULL),(7,1,1,7,NULL,NULL,NULL),(8,1,1,7,NULL,NULL,NULL),(9,1,9,1,NULL,NULL,NULL),(10,1,4,5,NULL,NULL,NULL),(11,6,7,6,NULL,NULL,NULL),(12,3,3,6,NULL,NULL,NULL),(13,3,3,3,NULL,NULL,NULL),(14,1,1,48,NULL,NULL,NULL),(15,1,1,NULL,NULL,NULL,NULL),(16,1,1,NULL,NULL,NULL,NULL),(17,1,9,11,NULL,NULL,NULL),(18,1,9,11,NULL,NULL,NULL),(19,1,9,11,NULL,NULL,NULL),(20,1,9,11,NULL,NULL,NULL),(21,1,9,12,NULL,NULL,NULL),(22,1,9,12,NULL,NULL,NULL),(23,1,9,14,NULL,NULL,NULL),(24,1,9,14,NULL,NULL,NULL),(25,1,9,15,NULL,NULL,NULL),(26,1,9,15,NULL,NULL,NULL),(27,1,9,13,NULL,NULL,NULL),(28,1,9,13,NULL,NULL,NULL),(29,1,9,13,NULL,NULL,NULL),(30,1,9,13,NULL,NULL,NULL),(31,1,9,13,NULL,NULL,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Ben Siech',2,'29820457','1993-06-19','Millet growing',NULL,NULL,NULL,1,1),(3,'Ben Siech',2,'29820458','1993-06-22','Millet growing',NULL,NULL,NULL,12,3),(4,'Ben Siech',2,'29820459','1993-06-22','Millet growing',NULL,NULL,NULL,3,4),(5,'Ben Siech',2,'29820460','1993-06-22','Millet growing',NULL,NULL,NULL,4,5),(6,'Ben Siech',2,'29820461','1993-06-22','',NULL,NULL,NULL,5,6),(7,'Ben Siech',2,'29820463','2016-06-22','Millet growing',NULL,NULL,NULL,6,7),(8,'Ben Siech',2,'29820451','2016-06-22','Millet growing',NULL,NULL,NULL,7,8),(9,'Ben Siech',1,'29820452','2016-06-22','Millet growing',NULL,NULL,NULL,8,9),(10,'Ben Siech',2,'29820453','2016-06-22','Millet growing',NULL,NULL,NULL,9,10),(11,'Ben Siech',2,'29820455','2016-06-22','Millet growing',NULL,NULL,NULL,10,11),(12,'Ben Siech',2,'29820456','2016-06-22','Millet growing',NULL,NULL,NULL,11,12),(13,'Bwana mkubwa',1,'29820420','2016-06-29','Large scale millet growing',NULL,NULL,NULL,14,13),(14,'Henry K Chirchir',2,'0727080918','2016-06-30','Asai Farners Stores',NULL,NULL,NULL,17,14),(15,'Enock K Korir',2,'0728254281','2016-06-30','Lelchego Agro-vet',NULL,NULL,NULL,18,15),(16,'Leah J Keter',1,'0726365000','2016-06-30','Kmoiywa Stores',NULL,NULL,NULL,19,16),(17,'',1,'0724333448','2016-06-30','Koilel Stores',NULL,NULL,NULL,20,17),(18,'Ben Tallam',2,'0712444630','2016-06-30','Kabiyet Dairies Agro-vet',NULL,NULL,NULL,21,18),(19,'Martha Kiay',1,'0723144351','2016-06-30','Sonoiyat Women Group',NULL,NULL,NULL,22,19),(20,'Willy Rop',2,'0721571079','2016-06-30','Chamtany Farmers Centre',NULL,NULL,NULL,23,20),(21,'Henry Rono',2,'0720402148','2016-06-30','Naigaba Agro-vet',NULL,NULL,NULL,24,21),(22,'Judy Cherop',1,'0702954085','2016-06-30','Kaon Enterprises',NULL,NULL,NULL,25,22),(23,'Jane Kiprotich',1,'0720792007','2016-06-30','Taunet Agro-vet',NULL,NULL,NULL,26,23),(24,'Dorcas Chepkwony',1,'0721943674','2016-06-30','Baraton Farm Care',NULL,NULL,NULL,27,24),(25,'Dinah Cherotich',1,'0726253218','2016-06-30','Kamuny Agro-vet',NULL,NULL,NULL,28,25),(26,'John Kemboi',2,'0721893081','2016-06-30','Baraton Agricultural',NULL,NULL,NULL,29,26),(27,'Peter Mutai',2,'0720837777','2016-06-30','Lessos Agrovet',NULL,NULL,NULL,30,27),(28,'Christopher Sawe',2,'0717153900','2016-06-30','Tarakwa Agrochemicals',NULL,NULL,NULL,31,28);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,1,'siele.bernard@gmail.com','1aca6dca73cc19c35147c3c7d2707ca78dd2495c7075c4b41912c140c4b39c00',9),(2,3,'agrodealer@gmail.com','35e8f79d9e51f0a3970f9cadc210ddec20d377af1e6a6d09c2f4eff3a9efa0d8',2),(3,4,'countyofficer@gmail.com','86ba3fc5f3c904d88523fe117921e46ce21f000b71386471d43367e6e86193fe',5),(4,5,'equity@gmail.com','3e0abfe6dc7124b22dc288240ab281552628179686a89fa3f09b8fd839f2453a',10),(5,6,'farmer@gmail.com','ba2a06effc4ee674b193ba47e22af92dda8e5c83b6657bb1d105deb6f2b4fa5f',1),(6,7,'kalroofficer@gmail.com','b163088866b02553aac34836f7699c1dcb5ccba391edd8efbdc21f664cefd0e7',8),(7,8,'nationalofficer@gmail.com','150a83b58026957b23ad46de7e69aa9de4e406cb6e10b7e92e940ad224687ace',7),(8,9,'regionalcoordinator@gmail.com','1abfa2598a1c1e5e722af1b81f8594330531d916f84cd18b804b8efabb431b21',6),(9,10,'subcounty@gmail.com','8d5e355759e9bbaeb101db1129e489b4e0acce93f38e6ea61d5c416bf872c924',4),(10,11,'warehouseoperator@gmail.com','f671352bd741b3226ed0126d2bc06d2e2ca485ee4ab0dbe8c3ab16a9b4a9b71b',11),(11,12,'wao@gmail.com','59c094d3f594d8900c5db0d90574d9f65c779b4101a02a8cb6d7f1c8586f1af5',3),(12,13,'ss@anynomous.ac.ke','b34e62121d7076d6deb48917adac921b9007084c6133849867ea1c72c7fcaf24',1),(13,14,'henrykchirchir@gmail.com','89ab1d1be52943d35bdbd858bc72c72430859085f6b59a6edc1089e73e32fe12',2),(14,15,'enockkkorir@gmail.com','35a3214b3f7475479c47a9e899a33b3aef8234e99c6c4c096f0a08746a257428',2),(15,16,'leahjterer@gmail.com','84302700142d4adaf528b818be8c0c0593f677953c2a2fb8b8d475d0dc01bdf8',2),(16,17,'koilelstores@gmail.com','9b990469e69d3c775ed90f7479689de4dc5b2f4f9acb6524dd8946fb70868776',2),(17,18,'bentallam@gmail.com','a0ee3d40a581f45ba46a74946ed13c1ae7a2d5f911e9fedc4e1cb35f863eb42e',2),(18,19,'marthakiay@gmail.com','b4cda9b6f54a317b660f18793b6fecbc5ff78b29623b8f1088e16bd8197d3b2b',2),(19,20,'willyrop@gmail.com','ece185256df7dfa96dde23cec764fb1c0dffb21401ccb3584ffcb34de6cb1565',2),(20,21,'henryrop@gmail.com','004d6a8c16f1dd6f6c2ded5017b2626f6f136a89b29a8ca8ae4e1dad56e2f393',12),(21,22,'judycherop@gmail.com','37f58d876a0940a0f66a495ad1b1ecc01272ed460299543b7145856f79466f80',2),(22,23,'janekiprotich@gmail.com','839829050a82b9692501058ea055bfdb16fe4d22df094ccb62a673800adc5f64',2),(23,24,'dorcaschepkwony@gmail.com','d17bc3ec3eed56f05f01a5929c38263df24afb6bd1ca6ef4a98b4ff4b2f98f32',2),(24,25,'dinahcherotich@gmail.com','6f999f8e1d27a4ccc34a09847a7b67cd4179942eeb2528c09908aa3cd34dd31a',12),(25,26,'johnkemboi@gmail.com','cc73f996704dfb924629a6fe4a1707fff68dee5e44cbbbdfb87f4c7eedb20150',12),(26,27,'petermutai@gmail.com','0d2ca99b7d29607ab48d95ce5fd65336c92ec054f07152c29e93fc07999784ca',2),(27,28,'christophersawe@gmail.com','3bd1cbd855cb2f0f23101a19b955a778618f00245a288b0f514e96231b7b7f53',2);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `procurement_plan`
--

LOCK TABLES `procurement_plan` WRITE;
/*!40000 ALTER TABLE `procurement_plan` DISABLE KEYS */;
INSERT INTO `procurement_plan` VALUES (1,1,'undefined',1,1,2143244.00,1,'2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02',NULL,'2016-07-02','2016-07-02',NULL,'2016-07-02','2016-07-02'),(2,1,'Selection of sub-counties',1,1,987654.00,1,'2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02',NULL,'2016-07-02','2016-07-02'),(3,1,'Baseline survey',1,1,9876543333.00,1,'2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02'),(4,1,'GESI strategy',1,1,980887654.00,1,'2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02','2016-07-02');
/*!40000 ALTER TABLE `procurement_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `procurement_plan_cs`
--

LOCK TABLES `procurement_plan_cs` WRITE;
/*!40000 ALTER TABLE `procurement_plan_cs` DISABLE KEYS */;
INSERT INTO `procurement_plan_cs` VALUES (1,1,'undefined',1,1,23456.00,1,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03',NULL,NULL,NULL,'2016-07-03',NULL,NULL,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03'),(2,1,'Baseline survey',1,1,234567.00,1,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03'),(3,3,'GESI strategy',2,2,98765.00,1,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03'),(4,1,'Development of Crop Insurance Products',1,1,67586.00,1,NULL,'2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03','2016-07-03',NULL,'2016-07-03');
/*!40000 ALTER TABLE `procurement_plan_cs` ENABLE KEYS */;
UNLOCK TABLES;
