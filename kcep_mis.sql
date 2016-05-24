-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema kcep_mis
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `kcep_mis` ;

-- -----------------------------------------------------
-- Schema kcep_mis
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kcep_mis` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `kcep_mis` ;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL,
  `county` VARCHAR(45) NULL,
  `sub_county` VARCHAR(45) NULL,
  `ward` VARCHAR(45) NULL,
  `longitude` DECIMAL(10,7) NULL,
  `latitude` DECIMAL(9,7) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `contact` (
  `id` INT NOT NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `postal_address` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `person_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person_role` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `person_role` (
  `id` INT NOT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `group` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `group` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `person` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `national_id` VARCHAR(20) NULL,
  `gender` TINYINT(1) NULL,
  `location_id` INT NOT NULL,
  `contact_id` INT NOT NULL,
  `business_name` VARCHAR(45) NULL,
  `person_role_id` INT NOT NULL,
  `group_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `person` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `measurement_unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `measurement_unit` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `measurement_unit` (
  `id` INT NOT NULL,
  `unit` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `warehouse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `warehouse` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `warehouse` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `person_id` INT UNSIGNED NOT NULL COMMENT 'Person id is the operator\n',
  `capacity` INT NULL,
  `units_id` INT NOT NULL,
  `wrs` TINYINT(1) NULL,
  `certified` TINYINT(1) NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `warehouse` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `static_input`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `static_input` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `static_input` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `static_input` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `input_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `input_type` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `input_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `static_input_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `input_type` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `e_voucher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_voucher` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `e_voucher` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `amount` VARCHAR(45) NULL,
  `input_type_id` INT UNSIGNED NULL,
  `person_id` INT UNSIGNED NOT NULL,
  `date_redeemed` DATE NULL,
  `photo` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `e_voucher` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `equipment` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `equipment` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `equipment_number` VARCHAR(45) NULL,
  `warehouse_id` INT UNSIGNED NOT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `training`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `training` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `training` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_date` DATE NULL,
  `end_date` VARCHAR(45) NULL,
  `person_id` INT UNSIGNED NOT NULL,
  `topic` VARCHAR(45) NULL,
  `venue` VARCHAR(45) NULL,
  `number_of_trainees` VARCHAR(45) NULL,
  `attendance` VARCHAR(200) NULL COMMENT 'Location of attachment\n',
  `person_role_id` INT NOT NULL COMMENT 'Category of trainees eg farmers',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `training` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `contract`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contract` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `contract` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `programme`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `programme` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `programme` (
  `id` INT NOT NULL,
  `activity` TEXT NULL,
  `start_period` VARCHAR(45) NULL,
  `end_period` VARCHAR(45) NULL,
  `unit` VARCHAR(45) NULL,
  `awp_target` VARCHAR(45) NULL,
  `programme_target` VARCHAR(45) NULL,
  `value_achieved` VARCHAR(45) NULL,
  `requested_budget` VARCHAR(45) NULL,
  `actual_expenditure` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `purchase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `purchase` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `purchase` (
  `id` INT NOT NULL,
  `item` VARCHAR(45) NULL,
  `date_purchased` VARCHAR(45) NULL,
  `serial_number` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `target_office` VARCHAR(45) NULL,
  `county` VARCHAR(45) NULL,
  `sub_county` VARCHAR(45) NULL,
  `cost` VARCHAR(45) NULL,
  `lpo_number` VARCHAR(45) NULL,
  `invoice` VARCHAR(300) NULL COMMENT 'Attachment\n',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `person_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `person_role` (`id`, `role`) VALUES (1, 'Farmer');
INSERT INTO `person_role` (`id`, `role`) VALUES (2, 'Agro-dealer');
INSERT INTO `person_role` (`id`, `role`) VALUES (3, 'WAO (Ward Extension Officer)');
INSERT INTO `person_role` (`id`, `role`) VALUES (4, 'Sub-county Officer');
INSERT INTO `person_role` (`id`, `role`) VALUES (5, 'County Officer');
INSERT INTO `person_role` (`id`, `role`) VALUES (6, 'Regional Coordinator');
INSERT INTO `person_role` (`id`, `role`) VALUES (7, 'National Office');

COMMIT;


-- -----------------------------------------------------
-- Data for table `measurement_unit`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `measurement_unit` (`id`, `unit`) VALUES (1, 'Kgs');
INSERT INTO `measurement_unit` (`id`, `unit`) VALUES (2, 'Tonnes');
INSERT INTO `measurement_unit` (`id`, `unit`) VALUES (3, 'Bags');

COMMIT;


-- -----------------------------------------------------
-- Data for table `static_input`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `static_input` (`id`, `name`) VALUES (1, 'Maize');
INSERT INTO `static_input` (`id`, `name`) VALUES (2, 'Beans');
INSERT INTO `static_input` (`id`, `name`) VALUES (3, 'Pigeon peas');
INSERT INTO `static_input` (`id`, `name`) VALUES (4, 'Green grams');
INSERT INTO `static_input` (`id`, `name`) VALUES (5, 'Sorghum');
INSERT INTO `static_input` (`id`, `name`) VALUES (6, 'Millet');
INSERT INTO `static_input` (`id`, `name`) VALUES (7, 'Planting');
INSERT INTO `static_input` (`id`, `name`) VALUES (8, 'Top-dressing');

COMMIT;


-- -----------------------------------------------------
-- Data for table `input_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `input_type` (`id`, `type`, `static_input_id`) VALUES (1, 'Seeds', NULL);
INSERT INTO `input_type` (`id`, `type`, `static_input_id`) VALUES (2, 'Fertiliser', NULL);
INSERT INTO `input_type` (`id`, `type`, `static_input_id`) VALUES (3, 'Tarpaulin', NULL);
INSERT INTO `input_type` (`id`, `type`, `static_input_id`) VALUES (4, 'Hermetic bags', NULL);

COMMIT;


