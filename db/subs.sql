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
-- Table `farmer_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmer_group` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `farmer_group` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `trained` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `farmer_group` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `region` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `region` (
  `id` SMALLINT(2) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `region` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `county` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `county` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `region` SMALLINT(2) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_county_region2`
    FOREIGN KEY (`region`)
    REFERENCES `region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `county` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `name_UNIQUE` ON `county` (`name` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sub_county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sub_county` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sub_county` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `county` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sub_county_county1`
    FOREIGN KEY (`county`)
    REFERENCES `county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `sub_county` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ward`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ward` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ward` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `sub_county` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_ward_sub_county1`
    FOREIGN KEY (`sub_county`)
    REFERENCES `sub_county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `ward` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `village`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `village` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `village` (
  `id` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `village` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `divisional_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `divisional_location` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `divisional_location` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `divisional_location` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `location` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `region` SMALLINT(2) UNSIGNED NULL,
  `county` SMALLINT UNSIGNED NULL,
  `sub_county` SMALLINT UNSIGNED NULL,
  `ward` SMALLINT UNSIGNED NULL,
  `longitude` DECIMAL(10,7) NULL,
  `latitude` DECIMAL(9,7) NULL,
  `village` MEDIUMINT UNSIGNED NULL,
  `divisional_location` SMALLINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_location_county1`
    FOREIGN KEY (`county`)
    REFERENCES `county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_sub_county1`
    FOREIGN KEY (`sub_county`)
    REFERENCES `sub_county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_ward1`
    FOREIGN KEY (`ward`)
    REFERENCES `ward` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_village1`
    FOREIGN KEY (`village`)
    REFERENCES `village` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_divisional_location1`
    FOREIGN KEY (`divisional_location`)
    REFERENCES `divisional_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_region1`
    FOREIGN KEY (`region`)
    REFERENCES `region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `location` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `contact` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(150) NULL,
  `postal_address` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `contact` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sex`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sex` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sex` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sex` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `sex` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `designation_in_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `designation_in_group` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `designation_in_group` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `designation` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `designation_in_group` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `designation_UNIQUE` ON `designation_in_group` (`designation` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `farmer_sub_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmer_sub_group` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `farmer_sub_group` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `farmer_group` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_farmer_sub_group_farmer_group1`
    FOREIGN KEY (`farmer_group`)
    REFERENCES `farmer_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `farmer_sub_group` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `person` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `sex` SMALLINT UNSIGNED NULL,
  `national_id` VARCHAR(20) NULL,
  `year_of_birth` SMALLINT(4) NULL,
  `age` SMALLINT(3) NULL,
  `business_name` VARCHAR(45) NULL,
  `farmer_group` INT UNSIGNED NULL,
  `farmer_sub_group` INT UNSIGNED NULL,
  `designation_in_group` INT UNSIGNED NULL,
  `location` INT UNSIGNED NULL,
  `contact` INT UNSIGNED NULL,
  `plot_size` DOUBLE NULL,
  `approved` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_person_farmer_group1`
    FOREIGN KEY (`farmer_group`)
    REFERENCES `farmer_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_location1`
    FOREIGN KEY (`location`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_contact2`
    FOREIGN KEY (`contact`)
    REFERENCES `contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_sex1`
    FOREIGN KEY (`sex`)
    REFERENCES `sex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_designation_in_group1`
    FOREIGN KEY (`designation_in_group`)
    REFERENCES `designation_in_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_farmer_sub_group1`
    FOREIGN KEY (`farmer_sub_group`)
    REFERENCES `farmer_sub_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `person` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `person_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person_role` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `person_role` (
  `id` SMALLINT(2) UNSIGNED NOT NULL AUTO_INCREMENT,
  `person_role` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `person_role` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `person_role_UNIQUE` ON `person_role` (`person_role` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `measurement_unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `measurement_unit` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `measurement_unit` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `unit` VARCHAR(45) NOT NULL,
  `symbol` VARCHAR(20) NULL,
  `use` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `measurement_unit` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `unit_UNIQUE` ON `measurement_unit` (`unit` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `category` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  `relative` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_category_category1`
    FOREIGN KEY (`relative`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'e.g. farmer';

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `category` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `phenomenon_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phenomenon_type` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `phenomenon_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'e.g. category of trainees';

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `phenomenon_type` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `phenomenon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phenomenon` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `phenomenon` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `category` INT UNSIGNED NOT NULL,
  `phenomenon_type` INT UNSIGNED NOT NULL,
  `tied_value` DECIMAL(16,2) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_phenomenon_category1`
    FOREIGN KEY (`category`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_phenomenon_phenomenon_type1`
    FOREIGN KEY (`phenomenon_type`)
    REFERENCES `phenomenon_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `phenomenon` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `warehouse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `warehouse` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `warehouse` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `warehouse_type` INT UNSIGNED NOT NULL,
  `warehouse_operator` INT UNSIGNED NULL,
  `capacity` INT NULL,
  `units` SMALLINT UNSIGNED NULL,
  `offers_wrs` TINYINT(1) NULL,
  `certified` TINYINT(1) NULL,
  `location` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_warehouse_units1`
    FOREIGN KEY (`units`)
    REFERENCES `measurement_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_location1`
    FOREIGN KEY (`location`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_phenomenon1`
    FOREIGN KEY (`warehouse_operator`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_phenomenon2`
    FOREIGN KEY (`warehouse_type`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `warehouse` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `input_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `input_type` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `input_type` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `input_type` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `type_UNIQUE` ON `input_type` (`type` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `e_voucher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_voucher` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `e_voucher` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `amount` VARCHAR(45) NULL,
  `input_type` SMALLINT UNSIGNED NULL,
  `person` INT UNSIGNED NULL,
  `date_redeemed` DATE NULL,
  `inputs_logbook_page` TEXT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_input_input_type1`
    FOREIGN KEY (`input_type`)
    REFERENCES `input_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_input_person1`
    FOREIGN KEY (`person`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `e_voucher` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `static_input`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `static_input` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `static_input` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `input_type` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_static_input_input_type1`
    FOREIGN KEY (`input_type`)
    REFERENCES `input_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `static_input` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `name_UNIQUE` ON `static_input` (`name` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `equipment` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `equipment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `serial_number` VARCHAR(45) NULL,
  `total_count` INT NULL COMMENT 'The total number of equipment of a type',
  `warehouse` INT UNSIGNED NOT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_equipment_warehouse1`
    FOREIGN KEY (`warehouse`)
    REFERENCES `warehouse` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `equipment` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `topic` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `topic` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `topic` VARCHAR(200) NOT NULL,
  `module` SMALLINT UNSIGNED NULL,
  `trainer` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_topic_topic1`
    FOREIGN KEY (`module`)
    REFERENCES `topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_topic_phenomenon1`
    FOREIGN KEY (`trainer`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `topic` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `training`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `training` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `training` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `topic` SMALLINT UNSIGNED NULL,
  `venue` INT UNSIGNED NULL,
  `number_of_trainees` INT NULL,
  `attendance_sheet` TEXT NULL COMMENT 'Location of attachment\n',
  `category_of_trainees` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_training_location1`
    FOREIGN KEY (`venue`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_training_training_topic1`
    FOREIGN KEY (`topic`)
    REFERENCES `topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_training_phenomenon1`
    FOREIGN KEY (`category_of_trainees`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `contract` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `activity_name`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_name` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `activity_name` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `parent` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_activity_name_activity_name1`
    FOREIGN KEY (`parent`)
    REFERENCES `activity_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `activity_name` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `name_UNIQUE` ON `activity_name` (`name` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sub_activity_name`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sub_activity_name` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sub_activity_name` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `activity_name` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sub_activity_description_activity1`
    FOREIGN KEY (`activity_name`)
    REFERENCES `activity_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `sub_activity_name` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `financial_year`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `financial_year` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `financial_year` (
  `id` SMALLINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `financial_year` VARCHAR(45) NOT NULL,
  `current_year` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `financial_year` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `financial_year_UNIQUE` ON `financial_year` (`financial_year` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sub_activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sub_activity` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sub_activity` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `financial_year` SMALLINT(3) UNSIGNED NULL,
  `annual_workplan_reference_code` VARCHAR(20) NULL,
  `gfss_code` INT UNSIGNED NULL,
  `expected_outcome` INT UNSIGNED NULL,
  `annual_indicator` INT UNSIGNED NULL,
  `component` INT UNSIGNED NULL,
  `sub_component` INT UNSIGNED NULL,
  `activity_name` INT UNSIGNED NULL,
  `sub_activity_name` INT UNSIGNED NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `measurement_unit` SMALLINT UNSIGNED NULL,
  `unit_cost` DECIMAL(16,2) NULL,
  `awpb_target` DECIMAL(16,2) NULL,
  `programme_target` DECIMAL(16,2) NULL,
  `totals` DECIMAL(16,2) NULL,
  `response_pcu` INT UNSIGNED NULL,
  `implementing_partner` INT UNSIGNED NULL,
  `procurement_plan` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `value_achieved` DECIMAL(16,2) NULL,
  `allocated_budget` DECIMAL(16,2) NULL,
  `expenditure_category` INT UNSIGNED NULL,
  `gok_percentage` DOUBLE NULL,
  `ifad_loan_percentage` DOUBLE NULL,
  `ifad_grant_percentage` DOUBLE NULL,
  `beneficiaries_percentage` DOUBLE NULL,
  `eu_percentage` DOUBLE NULL,
  `financial_institution_percentage` DOUBLE NULL,
  `county` SMALLINT UNSIGNED NULL,
  `region` SMALLINT(2) UNSIGNED NULL,
  `awpb_owner` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_activity_planning_activity1`
    FOREIGN KEY (`activity_name`)
    REFERENCES `activity_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_measurement_unit2`
    FOREIGN KEY (`measurement_unit`)
    REFERENCES `measurement_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_sub_activity_name2`
    FOREIGN KEY (`sub_activity_name`)
    REFERENCES `sub_activity_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_table11`
    FOREIGN KEY (`financial_year`)
    REFERENCES `financial_year` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon1`
    FOREIGN KEY (`gfss_code`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon2`
    FOREIGN KEY (`expected_outcome`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon3`
    FOREIGN KEY (`implementing_partner`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon4`
    FOREIGN KEY (`response_pcu`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon5`
    FOREIGN KEY (`annual_indicator`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon6`
    FOREIGN KEY (`expenditure_category`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_county1`
    FOREIGN KEY (`county`)
    REFERENCES `county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_region1`
    FOREIGN KEY (`region`)
    REFERENCES `region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon7`
    FOREIGN KEY (`component`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon8`
    FOREIGN KEY (`sub_component`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_activity_phenomenon9`
    FOREIGN KEY (`awpb_owner`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `sub_activity` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `procurement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `procurement` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `procurement` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `item` VARCHAR(45) NULL,
  `gfss_code` INT UNSIGNED NULL,
  `date_purchased` DATE NULL,
  `serial_number` VARCHAR(45) NULL,
  `description` VARCHAR(400) NULL,
  `target_office` VARCHAR(45) NULL,
  `county` SMALLINT UNSIGNED NULL,
  `sub_county` SMALLINT UNSIGNED NULL,
  `cost` DECIMAL(16,2) NULL,
  `lpo_number` VARCHAR(45) NULL,
  `invoice_or_receipt` VARCHAR(300) NULL COMMENT 'Attachment\n',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_purchase_county1`
    FOREIGN KEY (`county`)
    REFERENCES `county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_phenomenon1`
    FOREIGN KEY (`gfss_code`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_sub_county1`
    FOREIGN KEY (`sub_county`)
    REFERENCES `sub_county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `procurement` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sampled_farmer_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sampled_farmer_data` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sampled_farmer_data` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ward_extension_officer` INT UNSIGNED NOT NULL,
  `season` SMALLINT NULL,
  `productivity_per_crop_per_farmer` VARCHAR(100) NULL,
  `post_harvest_losses` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sampled_farmer_data_person1`
    FOREIGN KEY (`ward_extension_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `sampled_farmer_data` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `number_description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `number_description` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `number_description` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `number_description` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `age_bracket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `age_bracket` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `age_bracket` (
  `id` SMALLINT UNSIGNED NOT NULL,
  `bracket` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `age_bracket` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `bracket_UNIQUE` ON `age_bracket` (`bracket` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `number_of_farmers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `number_of_farmers` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `number_of_farmers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number_description` INT UNSIGNED NOT NULL,
  `age_bracket` SMALLINT UNSIGNED NULL,
  `sex` SMALLINT UNSIGNED NULL,
  `number` INT NULL,
  `sampled_farmer_data` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_number_of_farmers_sex1`
    FOREIGN KEY (`sex`)
    REFERENCES `sex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disaggregated_sampled_farmer_data1`
    FOREIGN KEY (`sampled_farmer_data`)
    REFERENCES `sampled_farmer_data` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_number_of_farmers_number_description1`
    FOREIGN KEY (`number_description`)
    REFERENCES `number_description` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_number_of_farmers_age_bracket1`
    FOREIGN KEY (`age_bracket`)
    REFERENCES `age_bracket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `number_of_farmers` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `extension_and_field_visit_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `extension_and_field_visit_data` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `extension_and_field_visit_data` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number_of_people_seeking_or_offered_advisory_services` INT NULL,
  `nature_of_advisory_services` VARCHAR(400) NULL,
  `number_of_field_visits_conducted` INT NULL,
  `number_of_farmers_visited` INT NULL,
  `field_visits_ward_locations` VARCHAR(400) NULL,
  `ward_extension_officer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_extension_and_field_visit_data_person1`
    FOREIGN KEY (`ward_extension_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `extension_and_field_visit_data` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_account` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user_account` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `person` INT UNSIGNED NOT NULL,
  `username` VARCHAR(150) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `person_role` SMALLINT(2) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_account_person1`
    FOREIGN KEY (`person`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_account_person_role1`
    FOREIGN KEY (`person_role`)
    REFERENCES `person_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `user_account` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `username_UNIQUE` ON `user_account` (`username` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `farmer_group_record`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmer_group_record` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `farmer_group_record` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `farmer_group` INT UNSIGNED NOT NULL,
  `date_of_inputs_collection` DATE NULL,
  `first_weeding_done` TINYINT(1) NULL,
  `second_weeding_done` TINYINT(1) NULL,
  `quantity_harvested_from_kcep_farm` INT NULL COMMENT '90 kg bags',
  `quantity_sold_from_kcep_farm` INT NULL,
  `average_selling_price_per_bag` DECIMAL NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_farmer_group_record_farmer_group1`
    FOREIGN KEY (`farmer_group`)
    REFERENCES `farmer_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `farmer_group_record` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `technology`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `technology` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `technology` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number_of_studies_conducted` SMALLINT NULL,
  `name` VARCHAR(45) NULL,
  `type_purpose` VARCHAR(400) NULL,
  `target_sub_counties` TEXT NULL,
  `kalro_officer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_technology_person1`
    FOREIGN KEY (`kalro_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `technology` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `technology_target_county`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `technology_target_county` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `technology_target_county` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `technology` INT UNSIGNED NOT NULL,
  `county` SMALLINT UNSIGNED NOT NULL,
  `kalro_officer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_technology_target_county_technology1`
    FOREIGN KEY (`technology`)
    REFERENCES `technology` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_technology_target_county_county1`
    FOREIGN KEY (`county`)
    REFERENCES `county` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_technology_target_county_person1`
    FOREIGN KEY (`kalro_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `technology_target_county` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `extension_material_and_guideline`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `extension_material_and_guideline` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `extension_material_and_guideline` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `kalro_officer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_extension_material_and_guideline_person1`
    FOREIGN KEY (`kalro_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `extension_material_and_guideline` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `soil_fertility_package`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `soil_fertility_package` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `soil_fertility_package` (
  `id` INT NOT NULL,
  `no_of_sampling_and_analysis_activities` SMALLINT NULL,
  `no_of_packages_developed` SMALLINT NULL,
  `target_locations` TEXT NULL,
  `no_of_meetings_for_e_voucher_definition` SMALLINT NULL,
  `kalro_officer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_soil_fertility_package_person1`
    FOREIGN KEY (`kalro_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `on_farm_trials_and_demonstrations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `on_farm_trials_and_demonstrations` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `on_farm_trials_and_demonstrations` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `no_of_on_farm_trials` SMALLINT NULL,
  `no_of_demonstrations` SMALLINT NULL,
  `target_locations` TEXT NULL,
  `attendance_sheet` TEXT NULL,
  `kalro_officer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_on_farm_trials_and_demonstrations_person1`
    FOREIGN KEY (`kalro_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `on_farm_trials_and_demonstrations` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `validation_workshops`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `validation_workshops` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `validation_workshops` (
  `id` INT NOT NULL,
  `no_in_western_region` INT NULL,
  `no_in_eastern_region` INT NULL,
  `attendance_sheet` TEXT NULL,
  `kalro_officer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_validation_workshops_person1`
    FOREIGN KEY (`kalro_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dissemination_of_results`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dissemination_of_results` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dissemination_of_results` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `no_of_result_set_in_western_region` INT NULL,
  `no_of_result_set_in_eastern_region` INT NULL,
  `kalro_officer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_dissemination_of_results_person1`
    FOREIGN KEY (`kalro_officer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `dissemination_of_results` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `warehouse_operation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `warehouse_operation` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `warehouse_operation` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `warehouse` INT UNSIGNED NOT NULL,
  `quantity_brought` DOUBLE NULL,
  `produce_type_brought` INT UNSIGNED NULL,
  `selling_date` DATE NULL,
  `quantity_sold` DOUBLE NULL,
  `produce_type_sold` INT UNSIGNED NULL,
  `selling_price` DECIMAL(18,2) NULL,
  `buyer` VARCHAR(120) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_warehouse_operation_static_input1`
    FOREIGN KEY (`produce_type_brought`)
    REFERENCES `static_input` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_operation_static_input2`
    FOREIGN KEY (`produce_type_sold`)
    REFERENCES `static_input` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_warehouse_operation_warehouse1`
    FOREIGN KEY (`warehouse`)
    REFERENCES `warehouse` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `warehouse_operation` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `trainer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trainer` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `trainer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `training` INT UNSIGNED NOT NULL,
  `phenomenon` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_trainer_training1`
    FOREIGN KEY (`training`)
    REFERENCES `training` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trainer_phenomenon1`
    FOREIGN KEY (`phenomenon`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `trainer` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `feedback` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `feedback` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `message` TEXT NOT NULL,
  `time_posted` DATETIME NOT NULL,
  `attachment` TEXT NULL,
  `submitter` INT UNSIGNED NULL,
  `feedback_type` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_feedback_person1`
    FOREIGN KEY (`submitter`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_feedback_phenomenon1`
    FOREIGN KEY (`feedback_type`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `feedback` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `trainee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trainee` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `trainee` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `training` INT UNSIGNED NOT NULL,
  `person` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_table1_training2`
    FOREIGN KEY (`training`)
    REFERENCES `training` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_person1`
    FOREIGN KEY (`person`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `trainee` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ebl_branch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebl_branch` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ebl_branch` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `ebl_branch` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `name_UNIQUE` ON `ebl_branch` (`name` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `account` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `account` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_number` VARCHAR(45) NULL,
  `savings` DECIMAL(16,2) NULL,
  `ebl_branch` SMALLINT UNSIGNED NULL,
  `sol_id` SMALLINT(2) NULL,
  `farmer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_account_details_person1`
    FOREIGN KEY (`farmer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_ebl_branch1`
    FOREIGN KEY (`ebl_branch`)
    REFERENCES `ebl_branch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `account` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `farm_activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farm_activity` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `farm_activity` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `yield` VARCHAR(45) NULL,
  `date_done` DATE NULL,
  `quantity_harvested` DOUBLE NULL,
  `family_consumption` DOUBLE NULL,
  `quantity_sold` DOUBLE NULL,
  `post_harvest_loss` DOUBLE NULL,
  `average_selling_price` DECIMAL(16,2) NULL,
  `farmer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_farm_activity_person1`
    FOREIGN KEY (`farmer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `farm_activity` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `loan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loan` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `loan` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(16,2) NULL,
  `type` VARCHAR(45) NULL,
  `account` INT UNSIGNED NOT NULL,
  `issuing_bank` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_loan_account1`
    FOREIGN KEY (`account`)
    REFERENCES `account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_loan_phenomenon1`
    FOREIGN KEY (`issuing_bank`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `loan` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `input_variety`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `input_variety` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `input_variety` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `variety` VARCHAR(45) NOT NULL,
  `static_input` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_input_variety_static_input1`
    FOREIGN KEY (`static_input`)
    REFERENCES `static_input` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `input_variety` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `variety_UNIQUE` ON `input_variety` (`variety` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `inputs_collection`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inputs_collection` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `inputs_collection` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date_collected` DATE NULL,
  `agro_dealer` INT UNSIGNED NOT NULL,
  `input_type` SMALLINT UNSIGNED NULL,
  `static_input` INT UNSIGNED NULL,
  `input_variety` INT UNSIGNED NULL,
  `quantity` SMALLINT NULL,
  `farmer` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_inputs_collection_person1`
    FOREIGN KEY (`agro_dealer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_input_type1`
    FOREIGN KEY (`input_type`)
    REFERENCES `input_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_person2`
    FOREIGN KEY (`farmer`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_static_input1`
    FOREIGN KEY (`static_input`)
    REFERENCES `static_input` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inputs_collection_input_variety1`
    FOREIGN KEY (`input_variety`)
    REFERENCES `input_variety` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `inputs_collection` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `result_hierarchy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `result_hierarchy` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `result_hierarchy` (
  `id` SMALLINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(400) NULL,
  `component` INT UNSIGNED NULL,
  `sub_component` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_result_hierarchy_phenomenon1`
    FOREIGN KEY (`component`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_result_hierarchy_phenomenon2`
    FOREIGN KEY (`sub_component`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `result_hierarchy` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `performance_indicator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `performance_indicator` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `performance_indicator` (
  `id` SMALLINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `performance_indicator_type` INT UNSIGNED NOT NULL DEFAULT 18,
  `result_hierarchy` SMALLINT(3) UNSIGNED NOT NULL,
  `description` VARCHAR(400) NOT NULL,
  `baseline_date` DATE NULL,
  `baseline_value` DOUBLE NULL,
  `appraisal_target` DOUBLE NULL,
  `measurement_unit` SMALLINT UNSIGNED NULL,
  `core` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_performance_indicator_result_hierarchy1`
    FOREIGN KEY (`result_hierarchy`)
    REFERENCES `result_hierarchy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_performance_indicator_phenomenon1`
    FOREIGN KEY (`performance_indicator_type`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_performance_indicator_measurement_unit1`
    FOREIGN KEY (`measurement_unit`)
    REFERENCES `measurement_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `performance_indicator` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `procurement_method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `procurement_method` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `procurement_method` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `method` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `procurement_method` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `procurement_plan_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `procurement_plan_type` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `procurement_plan_type` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `procurement_plan_type` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `plan_vs_actual`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plan_vs_actual` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `plan_vs_actual` (
  `id` SMALLINT UNSIGNED NOT NULL,
  `choice` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `plan_vs_actual` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ifad_prior_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ifad_prior_review` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ifad_prior_review` (
  `id` SMALLINT NOT NULL,
  `choice` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `procurement_plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `procurement_plan` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `procurement_plan` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `procurement_plan_type` SMALLINT UNSIGNED NOT NULL,
  `description` VARCHAR(400) NULL,
  `ifad_prior_review` SMALLINT NOT NULL,
  `plan_vs_actual` SMALLINT UNSIGNED NOT NULL,
  `cost` DECIMAL(16,2) NULL,
  `procurement_method` SMALLINT UNSIGNED NOT NULL,
  `complete_bd` DATE NULL COMMENT 'Bidding Document',
  `approval_by_ifad1` DATE NULL,
  `approval_by_sda` DATE NULL,
  `issue_bd` DATE NULL COMMENT 'Bidding Document',
  `receive_bids` DATE NULL,
  `evaluate_bids` DATE NULL,
  `approval_by_ifad2` DATE NULL,
  `award` DATE NULL,
  `approval_by_sda_or_ag` DATE NULL,
  `sign_contract` DATE NULL,
  `commence_contract` DATE NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_procurement_plan_procurement_method1`
    FOREIGN KEY (`procurement_method`)
    REFERENCES `procurement_method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_procurement_plan_category1`
    FOREIGN KEY (`procurement_plan_type`)
    REFERENCES `procurement_plan_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_plan_vs_actual1`
    FOREIGN KEY (`plan_vs_actual`)
    REFERENCES `plan_vs_actual` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_ifad_prior_review1`
    FOREIGN KEY (`ifad_prior_review`)
    REFERENCES `ifad_prior_review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `procurement_plan` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `procurement_plan_cs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `procurement_plan_cs` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `procurement_plan_cs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `procurement_plan_type` SMALLINT UNSIGNED NOT NULL,
  `description` VARCHAR(400) NULL,
  `ifad_prior_review` SMALLINT NOT NULL,
  `plan_vs_actual` SMALLINT UNSIGNED NOT NULL,
  `cost` DECIMAL(16,2) NULL,
  `procurement_method` SMALLINT UNSIGNED NOT NULL,
  `submit_tor` DATE NULL,
  `complete_reoi` DATE NULL,
  `complete_bd` DATE NULL COMMENT 'Bidding Document',
  `approval_by_ifad1` DATE NULL,
  `issue_reoi` DATE NULL,
  `receive_eois` DATE NULL,
  `establish_short_list` DATE NULL,
  `complete_rfp` DATE NULL,
  `approval_by_ifad2` DATE NULL,
  `approval_by_sda` DATE NULL,
  `issue_rfp` DATE NULL COMMENT 'Bidding Document',
  `receive_proposals` DATE NULL,
  `evaluate_technical_proposals` DATE NULL,
  `approval_by_ifad3` DATE NULL,
  `negotiate` DATE NULL,
  `approval_by_ifad4` DATE NULL,
  `award` DATE NULL,
  `approval_by_sda_or_ag` DATE NULL,
  `sign_contract` DATE NULL,
  `commence_contract` DATE NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_procurement_plan_procurement_method10`
    FOREIGN KEY (`procurement_method`)
    REFERENCES `procurement_method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_procurement_plan_category10`
    FOREIGN KEY (`procurement_plan_type`)
    REFERENCES `procurement_plan_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_ncs_plan_vs_actual1`
    FOREIGN KEY (`plan_vs_actual`)
    REFERENCES `plan_vs_actual` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procurement_plan_ncs_ifad_prior_review1`
    FOREIGN KEY (`ifad_prior_review`)
    REFERENCES `ifad_prior_review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `procurement_plan_cs` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `performance_indicator_values`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `performance_indicator_values` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `performance_indicator_values` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `project_year` SMALLINT(4) NULL,
  `actual_value` DOUBLE NULL,
  `expected_value` DOUBLE NULL,
  `performance_indicator` SMALLINT(3) UNSIGNED NOT NULL,
  `purpose` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_performance_indicator_values_performance_indicator1`
    FOREIGN KEY (`performance_indicator`)
    REFERENCES `performance_indicator` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `performance_indicator_values` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `goal_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `goal_report` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `goal_report` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NULL,
  `baseline` VARCHAR(45) NULL,
  `mid_term_review` VARCHAR(45) NULL,
  `impact_assessment` INT NULL,
  `end_term_evaluation` VARCHAR(45) NULL,
  `target` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `goal_report` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `activity_progress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_progress` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `activity_progress` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `value_achieved_or_expense` DECIMAL(16,2) NULL,
  `target_or_budget` DECIMAL(16,2) NULL,
  `sub_activity` INT UNSIGNED NOT NULL,
  `progress_type` INT UNSIGNED NOT NULL,
  `quarter` SMALLINT(1) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_activity_progress_sub_activity1`
    FOREIGN KEY (`sub_activity`)
    REFERENCES `sub_activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_progress_phenomenon1`
    FOREIGN KEY (`progress_type`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `activity_progress` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `uploaded_file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `uploaded_file` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `uploaded_file` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `purpose` INT UNSIGNED NOT NULL,
  `name` TEXT NOT NULL,
  `first_row` SMALLINT(2) NULL,
  `uploader` INT UNSIGNED NOT NULL,
  `populated` TINYINT(1) NULL,
  `time_uploaded` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_table1_phenomenon1`
    FOREIGN KEY (`purpose`)
    REFERENCES `phenomenon` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_uploaded_file_person1`
    FOREIGN KEY (`uploader`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `uploaded_file` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `activity_progress_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_progress_comment` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `activity_progress_comment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment` TEXT NULL,
  `sub_activity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_activity_progress_comment_sub_activity1`
    FOREIGN KEY (`sub_activity`)
    REFERENCES `sub_activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `activity_progress_comment` (`id` ASC);

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `region`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `region` (`id`, `name`) VALUES (1, 'Eastern');
INSERT INTO `region` (`id`, `name`) VALUES (2, 'Western');

COMMIT;


-- -----------------------------------------------------
-- Data for table `county`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `county` (`id`, `name`, `region`) VALUES (1, 'Bungoma', 2);
INSERT INTO `county` (`id`, `name`, `region`) VALUES (2, 'Embu', 1);
INSERT INTO `county` (`id`, `name`, `region`) VALUES (3, 'Kakamega', 2);
INSERT INTO `county` (`id`, `name`, `region`) VALUES (4, 'Kitui', 1);
INSERT INTO `county` (`id`, `name`, `region`) VALUES (5, 'Nakuru', 2);
INSERT INTO `county` (`id`, `name`, `region`) VALUES (6, 'Nandi', 2);
INSERT INTO `county` (`id`, `name`, `region`) VALUES (7, 'Tharaka Nithi', 1);
INSERT INTO `county` (`id`, `name`, `region`) VALUES (8, 'Transzoia', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sub_county`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (1, 'Mbeere North', 2);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (2, 'Mbeere South', 2);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (3, 'Tharaka North', 7);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (4, 'Tharaka South', 7);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (5, 'Mwingi Central', 4);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (6, 'Mwingi North', 4);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (7, 'Njoro', 5);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (8, 'Molo', 5);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (9, 'Chesumei', 6);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (10, 'Mosop', 6);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (11, 'Likuyani', 3);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (12, 'Lugari', 3);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (13, 'Kwanza', 8);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (14, 'Cheranganyi', 8);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (15, 'Sirisia', 1);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (16, 'Tongaren', 1);
INSERT INTO `sub_county` (`id`, `name`, `county`) VALUES (17, 'Emgwen', 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ward`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (1, 'Mauche', 7);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (2, 'Kihingo', 7);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (3, 'Nesuit', 7);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (4, 'Lare', 7);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (5, 'Njoro', 7);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (6, 'Mau Narok', 7);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (7, 'Elburgon', 8);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (8, 'Turi', 8);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (9, 'Mariashoni', 8);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (10, 'Molo', 8);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (11, 'Ngechek/Lelmoko', 9);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (12, 'Kaptel/Kamoiywo', 9);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (13, 'Chemundu', 9);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (14, 'Kosikai', 9);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (15, 'Kiptuiya', 9);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (16, 'Kabisaga', 10);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (17, 'Chepterwai', 10);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (18, 'Kurgung/Surungai', 10);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (19, 'Kabiyet', 10);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (20, 'Ndalat', 10);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (21, 'Sangaloi/Kebolonik', 10);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (22, 'Sinoko', 11);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (23, 'Nzoia', 11);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (24, 'Likuyani', 11);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (25, 'Sango', 11);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (26, 'Kongoni', 11);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (27, 'Lugari', 12);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (28, 'Lumakanda', 12);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (29, 'Muatuma', 12);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (30, 'Chekalini', 12);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (31, 'Lwandeti/Chevaywa', 12);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (32, 'Kwanza', 13);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (33, 'Keiyo', 13);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (34, 'Bidii', 13);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (35, 'Kapomboi', 13);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (36, 'Motosiet', 14);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (37, 'Cherangani Suwerwa', 14);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (38, 'Malakisi', 15);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (39, 'Lwandanyi', 15);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (40, 'Namwela', 15);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (41, 'Soysambu', 16);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (42, 'Milima', 16);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (43, 'Mbakalo', 16);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (44, 'Naitiri', 16);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (45, 'Ndalu', 16);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (46, 'Kiminini/Torangen', 16);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (47, 'Nthawa', 1);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (48, 'Muminji', 1);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (49, 'Evurore', 1);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (50, 'Mwea', 2);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (51, 'Makima', 2);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (52, 'Mavuria', 2);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (53, 'Mbeti South', 2);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (54, 'Kiambere', 2);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (55, 'Mukothima', 3);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (56, 'Gatunga', 3);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (57, 'Marimanti', 4);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (58, 'Nkondi', 4);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (59, 'Ciakariga', 4);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (60, 'Mwingi Central', 5);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (61, 'Waita', 5);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (62, 'Kivou', 5);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (63, 'Nguni', 5);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (64, 'Nuu', 5);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (65, 'Mui', 5);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (66, 'Muumoni', 6);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (67, 'Tseikuru', 6);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (68, 'Ngomeni', 6);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (69, 'Kyuso', 6);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (70, 'Tharaka', 6);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (71, 'Kipkaren', 10);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (72, 'Kapsabet', 17);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (73, 'Kilibwoni', 17);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (74, 'Chepkumia', 17);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (75, 'Kapkangani', 17);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (76, 'Chepsiro/Kiptoror', 14);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (77, 'Sitatunga', 14);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (78, 'Kaplamai', 14);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (79, 'Sinyerere', 14);
INSERT INTO `ward` (`id`, `name`, `sub_county`) VALUES (80, 'Makutano', 14);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sex`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `sex` (`id`, `sex`) VALUES (1, 'Female');
INSERT INTO `sex` (`id`, `sex`) VALUES (2, 'Male');

COMMIT;


-- -----------------------------------------------------
-- Data for table `designation_in_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `designation_in_group` (`id`, `designation`) VALUES (1, 'Chair');
INSERT INTO `designation_in_group` (`id`, `designation`) VALUES (2, 'Secretary');
INSERT INTO `designation_in_group` (`id`, `designation`) VALUES (3, 'Treasurer');
INSERT INTO `designation_in_group` (`id`, `designation`) VALUES (4, 'Normal Member');

COMMIT;


-- -----------------------------------------------------
-- Data for table `person_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `person_role` (`id`, `person_role`) VALUES (1, 'Farmer');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (2, 'Agro-dealer');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (3, 'WAO (Ward Extension Officer)');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (4, 'Sub-county Officer');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (5, 'County Officer');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (6, 'PCU Regional Staff');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (7, 'Programme Coordinator');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (8, 'KALRO Officer');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (9, 'System Admin');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (10, 'Equity');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (11, 'Warehouse Operator');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (12, 'AGMARK Officer');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (13, 'Senior Programme Coordinator');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (14, 'PCU Staff');

COMMIT;


-- -----------------------------------------------------
-- Data for table `measurement_unit`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (1, 'Kilograms', 'Kg', NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (2, 'Tonnes', 'T', NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (3, '90 kg Bags', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (4, 'Number', NULL, 'Programme');
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (5, 'Meeting', NULL, 'Programme');
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (6, 'Lumpsum', NULL, 'Programme');
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (7, 'Sites', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (8, 'Plots', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (9, 'Field visits', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (11, 'Workshops', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (10, 'Trainings', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (12, 'Warehouses', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (13, 'Certifications', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (14, 'Meetings', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (15, 'Kits', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (16, 'Group visits', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (17, 'Studies', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (18, 'Works', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (19, 'Survey', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (20, 'Review', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (21, 'Funds', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (22, 'Follow ups', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (23, 'P/M', NULL, NULL);
INSERT INTO `measurement_unit` (`id`, `unit`, `symbol`, `use`) VALUES (24, 'Visits', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (1, 'Farmers', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (2, 'Agro-dealers', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (3, 'Entrepreneurs', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (4, 'ToTs - lead staff', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (5, 'AGMARK', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (171, 'Equity', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (7, 'Equity Bank Limited (EBL)', 171);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (8, 'County Staff', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (9, 'East African Grain Council (EAGC)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (10, 'Technical Services Providers for Component 1', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (12, 'Farmer Owned', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (13, 'Privately Owned', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (11, 'KALRO', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (14, 'RIMS', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (15, 'NIMES', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (16, 'CIMES', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (17, 'Programme', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (18, 'WEAI', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (19, 'Warehouse', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (20, 'Collection Centre', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (21, 'Cereal Productivity Enhancement', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (22, 'Post-Harvest Management and Market Linkages', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (24, 'Programme Management', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (25, 'Effective Agricultural Services', 21);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (26, 'Supporting demand of services and inputs (farmer organization and capacity building)', 21);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (27, 'Post harvest management', 22);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (28, 'Market Linkages and Value Addition', 22);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (23, 'Financial Services', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (30, 'Value Chain financing', 23);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (31, 'Outcome 1: Productivity for maize, white sorghum, finger millet and pulses increased', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (32, 'Outcome 2: Post-harvest practices and market linkages for targeted VCs improved', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (33, 'Outcome 3: Financial inclusion of beneficiaries improved', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (34, 'Output 1.1: Agricultural services/ inputs improved', 31);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (35, 'Output 1.2: Capacity of farmers’ organizations to mobilize extension services, participate in local planning and value chains fora improved', 31);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (36, 'Output 2.1: Post-harvest management improved', 32);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (37, 'Output 2.2: Market Access for participating smallholders improved', 32);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (38, 'Output 3.1 : Use of financial tools and services by target groups increased', 33);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (39, 'Output 3.2: Access to value chain financing improved', 33);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (40, '1', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (41, '2', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (42, '3', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (43, '4', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (44, '5', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (45, '100,000 smallholder farmers mobilized (40,000 category 1; 60,000 category 2)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (46, '300 agrodealers selected and their capacity built to provide e-voucher inputs ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (47, '1,440 Extension agents trained and equipped to train and provide extension services to smallholder farmers', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (48, '4,140 demonstration plots established to conduct on-farm trials on released technologies and train farmers on improved agricultural practices - 150 at reaserch station; 3,990 at farmer-fields', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (49, '100,000 farmers trained on improved technologies and practices', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (50, '700 service providers trained on organization and capacity building of market oriented cereal-growers\' enterprises (MOCE)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (51, '40,000 category 1 farmers organized into cohesive market-oriented farmer groups (of an average of 25 farmers per group);  ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (52, '100,000 farmers (40,000 category 1; 60,000 category 2) organized in 4,000 groups linked to input, output and service markets', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (53, 'Effective coordination of extension services to supported farmers', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (54, '100,000 smallholders farmers trained on improved harvest and post-harvest management of grains (40,000 category 1; 60,000 category 2)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (55, '40,000 category 1 farmers (already formed into groups under Comp 1) organized into 100 produce marketing groups', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (56, '100 produce collection centres established and active', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (57, '60 grain warehouse facilities certified and operational with WRS system', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (58, '60 business partnerships developed between lead farmers and 100,000 farmers organized into secondary and apex farmer organizations; 90,000 MT of grain traded annually through structured market systems', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (59, '20 ppp ventures established in value addition and processing', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (60, '40,000 Smallholder farmers enrolled by Equity bank and raised required beneficiary contribution', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (61, '40,000  smallholder farmers trained on financial literacy and improve their savings culture', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (62, '300 agrodealers accredited as EBL agents with improved capacity to serve targeted farmers', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (63, '40,000  accessed with e-voucher inputs financing', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (64, '40,000 farmers graduate into financial sector', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (65, '100,000 farmers access Warehouse Receipt System (WRS) financing', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (66, 'Access to Value chain financing by key players by 60,000 category 2 farmers; 300 agrodealers; service providers; and 80 PPP ventures in grain handling, value addition and processing', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (67, 'Effective programme management', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (68, 'Monitoring & Evaluation and Knowledgement instutionalized; Gender mainstreamed', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (69, 'ToTs - Bank staff', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (70, 'ToTs - SCAOs/WAOs/FEWs', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (71, 'ToTs - Agro-dealers', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (72, 'Agribusiness officers', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (73, 'FSS', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (74, 'VCDS', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (75, 'Agribusiness', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (76, 'Agronomists', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (77, 'FAM', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (78, 'PME', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (79, 'PROC', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (80, 'M&E', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (81, 'PCU engineer', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (82, 'PO/PCU engineer', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (83, 'FSS/PCU engineer', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (84, 'FSS/PME', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (85, 'FSS/VCDS', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (86, 'PC', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (87, 'SME', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (88, 'Sub-county', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (90, 'CGA/SP2', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (91, 'SP2', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (93, 'CGA/FAO', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (94, 'AGMARK/County', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (95, 'AGMARK/SP2', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (96, 'CGA/EBL', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (97, 'AGMARK/CGA', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (98, 'AGMARK/EBL', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (99, 'Consulting firm', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (100, '27,300 category 1 farmers (15,000 maize; 7,500 sorghum and 4,800 millet) mobilized and selected for e-voucher programme:\n\n35,000 category 2 farmers mobilized and selected for programme participation', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (101, '150 agrodealers in Eastern region identifed and selected ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (102, '300 agrodealers trained, accredited and with adequate stocks for provision of e-voucher inputs to participating farmers (Both Eastern & Western region)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (103, 'Extension training manuals enough for 1,440 trainers; and extension materials to reach  100,000 farmers  prepared and printed', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (104, '1,440 Extension agents trained as trainers                                                                         (692 Public extension agents & CGA trained on soil fertility management & diagnostic & new crop technologies and climate smart good agricultural practices\n748 non-governmental stakeholders and lead farmers trained on climate smart good agricultural practices)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (105, '100 demonstration plots  established at regional research stations for on-farm trials of new technologies and training of extension agents (including lead farmers)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (106, '2,329 demonstration plots etsbalished at farmer group/lead farmer level', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (107, '27,300 category1 farmers trained intensively on climate-smart good agricultural practices;    35,000 category 2 farmers sensitized on improved farming practices (through field days) ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (108, 'Customized cereal growers farmer organization & capacity building manuals prepared', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (109, '350 service providers trained', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (110, '1,092 cereal growers groups registered with a membership of 27,300 farmers', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (111, '62,300 farmers (27,300 category 1; 35,000 category 2) linked to input, output and service markets', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (112, 'On going field coordination', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (113, 'Training manuals and hand-outs/fliers enough for 57,300 printed; demonstration equipment procured', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (114, '346 Post-harvest management trainers trained', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (115, '57,300 farmers (17,300 category 1 and 40,000 category 2) trained on improved post harvest management techniques; 433 farmer groups issued with post harvest equipments', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (116, '17,300 farmers organized into 346 secondary farmer organiztions', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (117, '43 grain collection centres established and equiped ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (118, '15,000 MT of grain collected for marketing through established bulking centres', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (119, '17 warehouses established 4 newly constructed; 13 refurbished)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (120, '35 warehouses certified', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (121, '15,000 MT of grain traded through established  centres', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (122, 'Warehouse Receipts System established for all supported warehouses', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (123, 'Lead buyers for all targeted commodities identified (maize; sorghum, millet, pulses)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (124, '57,300 farmers (17,300 category 1 and 40,000 category 2) mobilized, trained and coached on market linkages', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (125, '25 Buyer-producer business partnerships established; 15,000 MT of grain traded', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (126, '20 Investment partners identified; 4 PPP ventures initiated', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (127, '20 spots for improvement identified; civil works initiated for 4 spots', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (128, '27,300 smallholder farmers enrolled by EBL in Western region and Eastern issued with debit cards ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (129, '27,300 farmers raise 10% contribution required to trigger e-voucher release', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (130, '27,300 trained on financial literacy', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (131, '300 agrodealers accredited as agents with POS devices installed', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (132, '300 agrodealers trained on Financial literacy', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (133, 'e-voucher value for 27,300 selected maize, sorghum and millet farmers defined', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (134, '300 accredited agrodealers have sufficient stocks to service farmer requirements ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (135, 'e-voucher value for 27,300 farmers is credited into farmers accounts', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (136, '27,300 farmers collect 100% of e-financed inputs ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (137, 'Selected farmers facilitated with e-voucher inputs', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (138, 'Appropriate financial products for smallholders developed and tested', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (139, 'System for monitoring financial graduation of farmers set; FG rating of participating 27,300 farmers established', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (140, 'WRS system developed; staff, operators and farmers trained', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (141, '5,000 farmers receive WRS financing', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (142, '10,000 category 2 smallholder farmers receive financing', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (143, '300 accredited agrodealers with access to finance from EBL', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (144, '200 Service providers in shelling/thresshing services banking with Equity and accessing finance', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (145, '20 PPP ventures (10 storage facilities; 10 value addition and processing) financed ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (146, '6 LLC  linked to storage storage facility established', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (147, 'Programme planning and oversight ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (148, 'GESI study conducted and GESI workshop held', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (149, 'Baseline/RIMS survey conducted and workshop held ', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (150, 'Programe quarterly reports ready in time and PCU staff trained on KM', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (151, 'CGA/SP2', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (152, 'CGF', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (153, 'Smooth PCU operations', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (154, 'Feedback', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (155, 'Success story', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (156, 'Works', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (157, 'Vehicles', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (158, 'Equipments & Materials', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (159, 'Consultancies', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (160, 'Trainings', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (161, 'Grants & Subsidies', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (162, 'Salaries & Allowances', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (163, 'Operating Costs', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (164, 'Physical', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (165, 'Financial', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (166, 'Farmers Excel File', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (167, 'Agro-dealers Excel File', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (89, 'PCU', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (168, 'Preparatory', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (29, 'Financial inclusion', 23);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (169, 'Physical appraisal', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (170, 'Financial appraisal', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (6, 'Equity Group Foundation (EGF)', 171);

COMMIT;


-- -----------------------------------------------------
-- Data for table `phenomenon_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (1, 'Category of trainees');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (2, 'Category of trainers');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (3, 'Warehouse operator');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (4, 'Procurement item');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (5, 'GFSS code');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (6, 'Bank');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (7, 'Performance indicator type');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (8, 'Warehouse type');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (9, 'Component');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (10, 'Sub-component');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (11, 'Outcome');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (12, 'Output');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (13, 'Rating value');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (14, 'Expected outcome');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (15, 'Response PCU');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (16, 'Implementing partner');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (17, 'Annual indicator');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (18, 'Feedback type');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (19, 'Expenditure category');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (20, 'Progress type');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (21, 'Uploaded file purpose');
INSERT INTO `phenomenon_type` (`id`, `name`) VALUES (22, 'AWPB owner');

COMMIT;


-- -----------------------------------------------------
-- Data for table `phenomenon`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (1, 1, 1, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (2, 2, 1, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (3, 3, 1, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (4, 4, 1, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (5, 5, 2, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (6, 6, 2, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (7, 7, 2, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (8, 8, 2, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (9, 9, 2, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (10, 10, 2, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (11, 11, 2, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (12, 12, 3, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (13, 13, 3, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (14, 7, 6, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (15, 14, 7, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (16, 15, 7, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (17, 16, 7, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (18, 17, 7, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (19, 18, 7, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (20, 19, 8, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (21, 20, 8, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (22, 21, 9, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (23, 22, 9, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (24, 23, 9, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (25, 24, 9, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (26, 25, 10, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (27, 26, 10, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (28, 27, 10, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (29, 28, 10, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (30, 29, 10, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (31, 30, 10, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (32, 31, 11, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (33, 32, 11, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (34, 33, 11, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (35, 34, 12, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (36, 35, 12, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (37, 36, 12, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (38, 37, 12, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (39, 38, 12, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (40, 39, 12, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (41, 40, 13, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (42, 41, 13, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (43, 42, 13, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (44, 43, 13, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (45, 44, 13, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (46, 45, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (47, 46, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (48, 47, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (49, 48, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (50, 49, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (51, 50, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (52, 51, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (53, 52, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (54, 53, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (55, 54, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (56, 55, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (57, 56, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (58, 57, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (59, 58, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (60, 59, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (61, 60, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (62, 61, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (63, 62, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (64, 63, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (65, 64, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (66, 65, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (67, 66, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (68, 67, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (69, 68, 14, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (70, 69, 1, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (71, 70, 1, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (72, 71, 1, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (73, 72, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (74, 73, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (75, 74, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (76, 75, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (77, 76, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (78, 77, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (79, 78, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (80, 79, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (81, 80, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (82, 81, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (83, 82, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (84, 83, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (85, 84, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (86, 85, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (87, 86, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (88, 87, 15, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (89, 88, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (91, 90, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (92, 91, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (93, 11, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (94, 93, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (95, 94, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (96, 95, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (97, 96, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (98, 97, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (99, 98, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (100, 99, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (101, 5, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (102, 11, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (103, 151, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (104, 152, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (105, 100, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (106, 101, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (107, 102, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (108, 103, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (109, 104, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (110, 105, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (111, 106, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (112, 107, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (113, 108, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (114, 109, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (115, 110, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (116, 111, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (117, 112, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (118, 113, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (119, 114, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (120, 115, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (121, 116, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (122, 117, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (123, 118, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (124, 119, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (125, 120, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (126, 121, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (127, 122, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (128, 123, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (129, 124, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (130, 125, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (131, 126, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (132, 127, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (133, 128, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (134, 129, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (135, 130, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (136, 131, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (137, 132, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (138, 133, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (139, 134, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (140, 135, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (141, 136, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (142, 137, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (143, 138, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (144, 139, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (145, 140, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (146, 141, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (147, 142, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (148, 143, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (149, 144, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (150, 145, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (151, 146, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (152, 147, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (153, 148, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (154, 149, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (155, 150, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (156, 153, 17, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (157, 154, 18, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (158, 155, 18, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (159, 156, 19, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (160, 157, 19, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (161, 158, 19, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (162, 159, 19, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (163, 160, 19, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (164, 161, 19, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (165, 162, 19, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (166, 163, 19, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (167, 164, 20, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (168, 165, 20, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (169, 166, 21, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (170, 167, 21, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (90, 89, 16, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (172, 168, 9, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (173, 169, 20, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (174, 170, 20, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (175, 11, 22, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (176, 5, 22, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (177, 171, 22, NULL);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`, `tied_value`) VALUES (178, 89, 22, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `input_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `input_type` (`id`, `type`) VALUES (1, 'Seeds');
INSERT INTO `input_type` (`id`, `type`) VALUES (2, 'Fertiliser');
INSERT INTO `input_type` (`id`, `type`) VALUES (3, 'Tarpaulin');
INSERT INTO `input_type` (`id`, `type`) VALUES (4, 'Hermetic bags');

COMMIT;


-- -----------------------------------------------------
-- Data for table `static_input`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (1, 'Maize', 1);
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (2, 'Beans', 1);
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (3, 'Pigeon peas', 1);
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (4, 'Green grams', 1);
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (5, 'Sorghum', 1);
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (6, 'Millet', 1);
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (7, 'Planting', 2);
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (8, 'Top-dressing', 2);
INSERT INTO `static_input` (`id`, `name`, `input_type`) VALUES (9, 'Post-harvest', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (1, 'Managing working capital', NULL, 5);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (2, 'Managing stock ', NULL, 5);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (3, 'Costing and Pricing', NULL, 5);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (4, 'Selling and Marketing ', NULL, 5);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (5, 'Basic Business Record keeping', NULL, 5);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (6, 'Seed Management ', NULL, 5);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (7, 'Fertilizer and Soil health management', NULL, 5);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (8, 'Crop protection products', NULL, 5);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (9, 'Market and markets requirements', NULL, 11);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (10, 'Marketing', NULL, 11);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (11, 'Financial Services', NULL, 6);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (12, 'Budgeting', NULL, 6);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (13, 'Savings', NULL, 6);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (14, 'Debt Management', NULL, 6);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (15, 'Farmer organizational development', NULL, 6);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (16, 'Ecological Requirement', NULL, 11);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (17, 'Crop and crop varieties', NULL, 11);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (18, 'Crop Management', NULL, 11);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (19, 'Pests and disease control', NULL, 11);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (20, 'Post-harvest handling techniques', NULL, 11);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (21, 'Soil types and characteristics', 16, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (22, 'Rainfall', 16, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (23, 'Soil PH', 16, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (24, 'Temperature', 16, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (25, 'Crop varieties', 17, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (26, 'Choice of varieties', 17, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (27, 'Land preparation', 18, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (28, 'Planting', 18, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (29, 'Fertilizers types and application', 18, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (30, 'Nutrient deficiency', 18, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (31, 'Weeding', 18, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (32, 'Capping', 18, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (33, 'Thinning', 18, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (34, 'Common pest ', 19, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (35, 'Common disease', 19, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (36, 'Signs and symptoms', 19, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (37, 'Cultural and chemical controls', 19, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (38, 'Signs of maturity', 20, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (39, 'Harvesting method', 20, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (40, 'Harvesting equipment', 20, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (41, 'Drying', 20, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (42, 'Post-harvest pest and disease and control methods', 20, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (43, 'Packaging and storage', 20, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (44, 'Utilization and processing', 21, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (45, 'Value addition', 21, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (46, 'Processing equipment', 21, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (47, 'Business partnerships', NULL, 6);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (48, 'Gender mainstream  and social inclusion(M&E Offices)', NULL, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (49, 'Decision making and access to key resources for production', NULL, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (50, 'Behavioural change, communication and sharing of household roles', NULL, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (51, 'Leadership skills', NULL, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (52, 'Access to product inputs, extension services', NULL, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (53, 'Adoption of time and energy-saving technologies', NULL, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (54, 'Market access', NULL, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (55, 'Benefit sharing, off-farm businesses and business skills', NULL, NULL);
INSERT INTO `topic` (`id`, `topic`, `module`, `trainer`) VALUES (56, 'Access to financial services', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_name`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (1, 'Community mobilization and selection of beneficiaries ', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (2, 'Identification and selection of agrodealers  (Eastern Region)', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (3, 'Capacity buiding of agrodealers', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (4, 'Training of extension service providers', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (5, 'Preparation of training and extension materials on selected technologies for targeted value chains', 4);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (6, 'Training of Extension agents on new crop technologies', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (7, 'Training of extension agents on soil fertility management and diagnostics of nutrient deficiencies', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (8, 'Training of extension agents on extension climate smart good agricultural practices for targeted crops', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (9, 'Esablishment of field-trial demonstration plots', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (10, 'Establishment of demonstration plots at KALRO regional centres for filed trials', 9);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (11, 'Establishment of demonstrations at plots at farmers\'  group level', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (13, 'Training of community-level farmer organization and capacity building service providers ', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (14, 'Preparation of group organization and capacity building manuals', 13);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (15, 'Training of service providers ', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (16, 'Farmer organization and capacity building', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (17, 'Farmer organization', 16);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (18, 'Preparation of group action plans', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (19, 'Training and capacity building of groups', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (20, 'Linkages with inputs, services and output markets', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (21, 'Linkages to input markets', 20);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (22, 'Linkages to service markets (Finance)', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (23, 'Linkages to output markets', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (24, 'Facilitatation of KCEP interface with groups', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (25, 'Training of farmers in harvest and post-harvest management', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (26, 'Preparation and procurement of training  materials', 25);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (27, 'Training of trainers', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (28, 'Training of farmers', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (29, 'Formation of secondary farmer organizations', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (30, 'Establishment and operationalization of produce collection centres', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (31, 'Establishment of produce collection centres', 30);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (32, 'Produce aggregation', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (33, 'Establisment and certification of produce warehousing facilities & systems', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (34, 'Establishment of warehousing facilities', 33);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (35, 'Certification of warehouses', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (36, 'Warehouse operations', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (37, 'Set-up of WRS ', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (38, 'Building of business partnerships between lead buyers and farmer organizations', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (39, 'Identification of lead buyers', 38);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (40, 'Mobilization and capacity building of farmers for sustained market engagement', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (41, 'Formalization of business partnerships (spot & forwards)', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (42, 'Value addition and processing', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (43, 'Investment promotion in value addition and processing', 42);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (44, 'Support to farmer-owned value addition/processing ventures', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (45, 'Support to private sector-owned ventures', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (46, 'Spot improvements of access roads ', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (47, 'Enrollment of farmers and mobilization to raise required beneficiary contribution', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (48, 'Screening and enrolment of selected farmers', 47);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (49, 'Savings mobilization to meet benficiary contribution requirements', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (50, 'Training of farmers on financial literacy', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (51, 'Accreditation and trainingof agrodealers', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (52, 'Enrollment and accreditation of Agrodealers', 51);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (53, 'Financial literacy training of agrodealers', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (54, 'e-voucher Financing ', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (55, 'Identification of supply sources and valuation of e-voucher package', 54);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (56, 'Coordination of inputs availability at agrodealer shops', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (57, 'e-voucher processing', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (58, 'e-financed inputs supply to farmers', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (59, 'Facilitating farmers\' acquisition of e-voucher inputs', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (60, 'Financial graduation (FG)of smallholder farmers', 59);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (61, 'Design of financial graduation strategy and products', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (62, 'Close monitoring of FG process to ensure programme remains on track', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (63, 'Warehouse receipt financing ', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (64, 'Development and training on WRS financing', 63);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (65, 'WRS financing', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (66, 'Value Chain Financing', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (67, 'Value Chain Financing of category 2 farmers', 67);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (68, 'Agrodealer financing (no grant)', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (69, 'Financing of grain threshing/shelling service providers (no grant)', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (70, 'Financing storage, value addition and processing LLCs (venture - different debt:equity:grant ratios)', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (71, 'Programme operations & management', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (72, 'PCU investment and recurrent costs', 71);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (73, 'Programme oversight', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (74, 'M&E and Knowledgement Management', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (75, 'Gender and social inclusion (GESI) study', 74);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (76, 'Baseline/RIMS survey', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (77, 'M&E Reporting and KM training', NULL);
INSERT INTO `activity_name` (`id`, `name`, `parent`) VALUES (12, 'Training of farmers on improved crop technologies', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sub_activity_name`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (1, 'Sub-county stakeholder forum sensitization meetings in Eastern region (6 sub-counties)', 1);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (2, 'Community mobilization & awareness campaigns in Eastern and Westen region', 1);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (3, 'Calls for beneficiaries applications for e-voucher scheme', 1);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (4, 'Screening & Selection of beneficiaries for e-voucher ', 1);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (5, 'Signing beneficiaries\' commitments with PCU', 1);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (6, 'Selection of category 2 farmers and farmer groups ', 1);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (7, 'Calls for expression of application for agrodealers\' participation in e-voucher scheme', 2);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (8, 'Application & Pre-selection of Agro-dealers by Sub-county committees', 2);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (9, 'Review and final selection of Agrodealers by EBL', 2);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (10, 'Discussion and signing of Agrodealers\' implementation commitments with PCU', 2);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (11, 'linkage of agro-dealers to bulk suppliers', 3);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (12, 'Technical backstoping support for acquisition of required inputs', 3);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (13, 'Development & customization of training Materials for agro-dealers', 3);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (14, 'Production of training Materials', 3);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (15, 'Training of selected agrodealers ', 3);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (16, 'Preparation of materials on adapted crop technologies (KALRO)', 5);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (17, 'Soil nutrition and fertilizer application materials (IPNI)', 5);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (18, 'Preparation of materails on crop protection', 5);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (19, 'Materials on climate smart agriculture', 5);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (20, 'Compilation into a joint publication', 5);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (21, 'Printing of extension materials', 5);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (22, 'Identification and selection of extension agents', 6);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (23, 'Training of trainers - Western & Eastern regions', 6);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (24, 'Follow-up on trainees', 6);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (25, 'Identification and selection of trainees', 7);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (26, 'Training of trainers', 7);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (27, 'Identification of extension agents to be trained', 8);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (28, 'Training of trainers', 8);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (29, 'Identification and selection of sites', 10);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (30, 'Establishment of demonstration plots ', 10);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (31, 'Ongoing management of demonstration plots ', 10);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (32, 'Identification and selection of demo plot locations at the ward level (host farmers)', 11);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (33, 'Establishment of farmer-level demo plots', 11);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (34, 'Ongoing management and feedback on technology adoption by farmers', 11);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (35, 'Group level training of farmers', 12);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (36, 'Farmer field days', 12);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (37, 'Farmer exchange visits', 12);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (38, 'Trade fairs in each sub-county for farmer exposure to best practices', 12);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (39, 'Rapid training needs assessment - trainers; farmer groups', 14);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (40, 'Preparation of customized training of trainer manuals', 14);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (41, 'Printing of training materials', 14);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (42, 'Identification and selection of trainers', 15);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (43, 'Training of service providers ', 15);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (44, 'Follow-up on trainees', 15);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (45, 'Support to group self selection, formulation of group constitutions & election of leaders', 17);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (46, 'Support to group registration with social services', 17);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (47, 'Group accreditation to CGA', 17);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (48, 'Participatory review of group status & needs', 18);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (49, 'Participatory rapid preperation of group action plans', 18);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (50, 'Training on organization & management', 19);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (51, 'Training joint action in procurement & marketing', 19);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (52, 'Facilitation of selected farmers for mutual learning visits', 19);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (53, 'Follow-up after mutual learning visits', 19);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (54, 'Support to farmers in quantification of inputs requirements and access points', 21);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (55, 'Sensitization meetings to link farmer groups to agrodealers', 21);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (56, 'Support to farmers in review of service requirements', 22);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (57, 'Facilitation of meeting between farmers and service providers (including Financial services)', 22);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (58, 'Support collective action in produce bulking', 23);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (59, 'Facilitation of farmer to buyers (identified under Comp 2)', 23);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (60, 'Ongoing field coordination', 24);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (61, 'Facilitation of M&E activities', 24);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (62, 'Preparation of training materials and hand-outs', 26);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (63, 'Printing of training materials', 26);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (64, 'Procurement of training demonstration kits', 26);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (65, 'Identification and selection of trainers', 27);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (66, 'Training of trainers', 27);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (67, 'Mobilization of farmers for training, including selection of demonstration sites', 28);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (68, 'Training of farmers', 28);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (69, 'Practical demonstration', 28);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (71, 'Farmer group mobilization to form aggregation groups', 29);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (72, 'Support in group establishment and registration', 29);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (73, 'Support in group action plan preparation', 29);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (74, 'Capacity building training of secondary groups', 29);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (75, 'Support to farmer identification & hire of produce collection stores ', 31);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (76, 'Supply of basic equipment to farmer stores ', 31);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (77, 'Farmer training on collection centre management', 31);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (78, 'Support in farmer planning for produce collection', 32);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (79, 'Technical support in management of stores', 32);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (80, 'Technical support in produce disposal from stores', 32);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (81, 'Identification and selection of facilities for support', 34);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (82, 'Identification and selection of Civil works vendors', 34);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (83, 'Construction of farmer-owned facilities', 34);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (84, 'Refurbishment of public-owned facilities (Refurbishing and certification of existing warehouses)', 34);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (85, 'Supply of equipment to supported warehouses', 34);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (86, 'Establishment of warehouse management structures', 35);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (87, 'Training of warehouse staff and management', 35);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (88, 'Certification of warehouses', 35);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (89, 'Certification of exisiting privately-owned storage facilities', 35);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (90, 'Support in preparation of warehouse operations plan', 36);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (91, 'Technical backstopping support in warehouse operations', 36);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (92, 'Warehouse operational support up to financial sustainability ', 36);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (93, 'Technical support in establishment of WRS ', 37);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (94, 'Sensitization and training of farmers on WRS', 37);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (95, 'Training of warehouse operators on WRS', 37);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (96, 'Support to Comp 3 in establishment of WRS financing', 37);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (97, 'Inventory of lead buyers', 39);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (98, 'Engagement with potential buyers to identify those interested in building partnerships', 39);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (99, 'Agreement with lead buyers on working modalities', 39);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (100, 'Preparation of summary sheets on each buyer requirements for communication to farmers', 39);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (101, 'Ongoing engagement with buyers', 39);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (102, 'Mobilization of farmers for meetings with buyers', 40);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (103, 'Sensitization of farmers on buyer requirements', 40);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (104, 'Farmer organization training on marketing', 40);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (105, 'Ongoing technical support to farmer organizations towards fulfillment of their market obligations', 40);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (106, 'Technical support in trade negotiations', 41);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (107, 'Follow-up coordination with both parties to ensure progress towards contract fuilfillment ', 41);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (108, 'Technical support in produce aggregation & sales', 41);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (109, 'Technical support in payments management for building trust', 41);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (114, 'Technical support in feasibility study', 44);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (115, 'Linkages to financing (Comp 3)', 44);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (120, 'Identification of areas for spot improvement', 46);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (121, 'Identification and selection of civil works vendors', 46);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (122, 'Spot improvement civil works', 46);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (123, 'Oversight of civil works', 46);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (124, 'Review of list of selected farmers', 48);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (125, 'Sensitization of selected farmers on account opening procedures', 48);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (126, 'Enrolment of the selected farmers - issued with cards', 48);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (127, 'Support to farmer groups\' establishment of savings mobilization structures & plans (table banking etc)', 49);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (128, 'Ongoing savings mobilization by farmers', 49);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (129, 'Review of farmer savings to trigger release of e-voucher', 49);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (130, 'Mobilization of farmers for training', 50);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (131, 'Training of farmers in Western Kenya (maize zone)', 50);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (132, 'Training of farmers in Eastern (Sorghum/millet zone) ', 50);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (133, 'Follow-up on trained farmers to strengthen adoption', 50);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (135, 'Review and selection of agrodealers in Eastern', 52);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (136, 'Sensitization and training of selected agrodealers', 52);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (137, 'Enrolment of the selected agrodealers', 52);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (138, 'Installation of POS devices and training on operation', 52);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (139, 'Mobilization of participating agrodealers', 53);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (140, 'Financial literacy training', 53);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (141, 'Follow-up to encourage adoption of practices', 53);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (142, 'Consultation with e-voucher supply chain players for bulk supply', 55);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (143, 'Sub-county specific valuation of e-voucher technical package with agrodealers', 55);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (144, 'Preparation and approval of e-voucher values for selected farmers', 55);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (145, 'Coordination of farmers to place orders with agro-dealers', 56);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (146, 'Support to agrodealers in stock projections & supply', 56);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (147, 'Sumission of approved e-voucher list to EBL', 57);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (148, 'Review and processing of e-vouchers into farmer accounts', 57);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (149, 'Ongoing coordination with agro-dealers & PCU for smooth accessibility of e-funds', 57);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (150, 'Farmer mobilization for inputs collection & use', 58);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (151, 'Coordination of any issues arising for expeditious resolution ', 58);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (152, 'Close monitoring to minimize moral hazards', 58);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (153, 'Voucher value for Maize, sorghum & millet for year 1', 59);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (154, 'GoK contribution for maize, sorghum & millet voucher value for year 1', 59);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (155, 'Study on adapted financial products for smallholder farmers', 61);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (156, 'Design of adapted financial products for cereal farmers', 61);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (157, 'Testing of adapted financial products', 61);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (158, 'Establishment of criteria for financial graduation', 62);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (159, 'Establishment of M&E system for FG', 62);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (160, 'FG measurement of participating farmers', 62);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (161, 'Ongoing FG monitoring and evaluation', 62);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (162, 'Development of WRS financing products', 64);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (163, 'Development & customization of WRS financing platform', 64);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (164, 'Training of staff and warehouse operators on WRS financing', 64);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (165, 'Mobilization and training of farmers on WRS financing', 64);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (166, 'Coordination of farmer financing requests', 65);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (167, 'Processing and disbursements into farmer accounts ', 65);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (168, 'Ongoing monitoring for feedback on improvements', 65);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (169, 'Formulation of financing window for category 2 farmers', 67);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (170, 'Mobilization of farmers for account opening and preparations for financing eligibility', 67);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (171, 'Support to farmers application for financing', 67);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (172, 'Processing and disbursements of finance ', 67);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (173, 'Mobilization of agrodealers to utilize financing window developed in 2015', 68);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (174, 'Opening of financing facilities for agrodealers', 68);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (175, 'Ongoing support for appropriate utilization of facility', 68);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (176, 'Establishment of financing window for service providers', 69);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (177, 'Mobilization of SPs for account opening and preparations for eligibility', 69);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (178, 'SP financing', 69);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (179, 'Follow-up support & monitoring', 69);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (180, 'Establishment of financing window for PPP ventures', 70);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (181, 'Sensitization of potential applicants on application process', 70);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (182, 'Review of proposals (in coordination with Comp 2)', 70);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (183, 'Disbursements', 70);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (184, 'Follow-up for successful operations and repayments', 70);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (185, 'Supporting the establishment of LLC linked to each storage facility by legal advisor', 70);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (186, 'Payment of utilities - Security & interner services', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (187, 'Programme branding', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (188, 'Payment for insurance for vehicles', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (189, 'Maintaince of vehicles', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (190, 'Group medical & accidents insurance covers', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (191, 'Payment for Publications ', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (192, 'Payment of PCU Staff salaries ', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (193, 'Office supplies and consumables', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (194, 'Procurement of other office equipments & accessories', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (195, 'Procurement of vehicles', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (196, 'Communication items', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (197, 'Payments for PCU staff Trainings', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (198, 'Payment of Professional body membership', 72);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (199, 'Programme Steering Committee Meeting', 73);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (200, 'County Programme Coordinating Comettee (CPCC) meetings  ', 73);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (201, 'Quarterly Sub County stakeholder meetings', 73);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (202, 'Coordination meetings and workshops', 73);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (203, 'Attending IFAD regional implementation workshops', 73);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (204, 'Gender and social inclusion (GESI) study', 75);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (205, 'GESI workshop', 75);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (206, 'Preparation of survey methodology & work plan ', 76);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (207, 'Baseline/RIMS  survey', 76);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (208, 'Conducting stakeholder workshop', 76);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (209, 'M&E Annual review meeting', 77);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (210, 'M&E quarterly field follow-up visits', 77);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (211, 'Implementing partners AWPB Preparation workshops', 77);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (212, 'M&E quarterly planning and review Meetings', 77);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (213, 'Trainings of PCU & County staff on KM', 77);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (214, 'Participate in IFAD Supervision Mission', 77);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (215, 'Preparation of consolidated programme reports', 77);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (70, 'Follow-up on utilization of demonstration equipment and e-voucher post-harvest package', 28);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (110, 'Elaboration and structuring of PPP model', 43);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (111, 'Packaging of investment business cases (opportunities)', 43);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (112, 'Investment opportunity campaigns', 43);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (113, 'Identification and selection of investments for support', 43);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (116, 'Technical support in start-up & management', 44);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (134, 'Ongoing coordination', 50);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (117, 'Technical backstopping in feasibility study', 45);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (118, 'Linkage to financing (Comp 3)', 45);
INSERT INTO `sub_activity_name` (`id`, `name`, `activity_name`) VALUES (119, 'Linkage to farmers for materials supply', 45);

COMMIT;


-- -----------------------------------------------------
-- Data for table `financial_year`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `financial_year` (`id`, `financial_year`, `current_year`) VALUES (1, '2015-2016', 0);
INSERT INTO `financial_year` (`id`, `financial_year`, `current_year`) VALUES (2, '2016-2017', 0);
INSERT INTO `financial_year` (`id`, `financial_year`, `current_year`) VALUES (3, '2017-2018', 1);
INSERT INTO `financial_year` (`id`, `financial_year`, `current_year`) VALUES (4, '2018-2019', 0);
INSERT INTO `financial_year` (`id`, `financial_year`, `current_year`) VALUES (5, '2019-2020', 0);
INSERT INTO `financial_year` (`id`, `financial_year`, `current_year`) VALUES (6, '2020-2021', 0);
INSERT INTO `financial_year` (`id`, `financial_year`, `current_year`) VALUES (7, '2021-2022', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `number_description`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `number_description` (`id`, `description`) VALUES (1, 'Who were sampled');
INSERT INTO `number_description` (`id`, `description`) VALUES (2, 'Who used inputs correctly');
INSERT INTO `number_description` (`id`, `description`) VALUES (3, 'Who have adopted GAPs');
INSERT INTO `number_description` (`id`, `description`) VALUES (4, 'Who have adopted post-harvest management technologies');

COMMIT;


-- -----------------------------------------------------
-- Data for table `input_variety`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `input_variety` (`id`, `variety`, `static_input`) VALUES (1, 'DAP', 7);
INSERT INTO `input_variety` (`id`, `variety`, `static_input`) VALUES (2, 'NPK', 7);
INSERT INTO `input_variety` (`id`, `variety`, `static_input`) VALUES (3, 'Hybrid 45', 1);
INSERT INTO `input_variety` (`id`, `variety`, `static_input`) VALUES (4, 'Katumani', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `result_hierarchy`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (1, 'Goal: National food security improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (2, 'Programme objectives\nIncreased production of targeted cereal staples (maize, sorghum, millet and associated pulses)\nIncreased income of smallholders in medium and high potential production areas', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (4, 'Outcome 1: Productivity for maize, sorghum, bulrush millet and pulses increased', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (5, 'Output 1.1; Agricultural services/ inputs improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (6, 'Output 1.2; Capacity of farmers’ organizations to mobilize extension services, participate in local planning and value chains fora improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (7, 'Outcome 2; Post harvest practices and market linkages for targeted VCs improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (8, 'Output 2.1; Post-harvest management improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (9, 'Output 2.2; Market Access for participating smallholders improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (10, 'Outcome 3; Financial inclusion of beneficiaries improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (11, 'Output 3.1 : Use of financial tools and services by target groups increased', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `component`, `sub_component`) VALUES (12, 'Output 3.2: Access to value chain financing improved', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `performance_indicator`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (1, 16, 1, 'a. Uplifting of at least 32,000 households from poverty (NIMES)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (2, 15, 1, 'b. No. of households with improvement in assets ownership index (productive assets, bikes, radios, improving housing, tin roofs etc) (RIMS3)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (3, 15, 1, 'c. Percentage reduction in the prevalence of child malnutrition (RIMS3)1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (4, 15, 1, 'd. Reduction in households experiencing one hungry season per year (RIMS3)1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (5, 16, 1, 'e. Reduced hunger (NIMES)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (6, 19, 1, 'f. Increased empowerment of women and youth in agriculture (WEAI 80%) (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (8, 18, 2, 'a. National grain deficit reduced by 41,000T / 10% national deficit', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (9, 15, 2, 'b. No. of targeted households using improved inputs voluntarily (min 32,000 HH), (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (10, 15, 2, 'c. No. of targeted smallholder farmers in commercial contracts, (min 55,000), (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (11, 15, 2, 'd. No. of targeted smallholders engaged in financial services (i.e taking investment loans), RIMS2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (12, 15, 2, 'e. No. of targeted households bulking and selling grains at price 30% higher than farmgate, (RIMS 2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (13, 19, 2, 'f. No. of Programme M&E officers trained on gender mainstreaming and social inclusion (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (14, 19, 2, 'g. No. of women and youth empowered in decision making (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (15, 15, 4, 'a. Improved agricultural production (RIMS2)\nTargets:\ni. Maize; 0.9 to 1.8 MT/acre\nii. Sorghum; 0.6 to 1.2 MT/acre\niii. Bush millet; 0.7 to 1.3 MT/acre\niv. Pulses; 0.3 to 0.6 MT/acre', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (16, 18, 4, 'b. Improved practices / packages adopted by 62,000 HH (32,000 subsistence, 30,000 advanced, 50% women) ', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (17, 15, 4, 'c. No of farmers using purchased inputs (RIMS 2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (18, 17, 4, 'd. No. of farmers adopting modern techniques (Embu, Bungoma, Nakuru and Kitui) (CIMES)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (19, 15, 4, 'e. No / Percentage of farmers reporting improved production/ improved yields (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (20, 15, 4, 'f. No of farmers adopting recommended technologies (RIMS 2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (21, 15, 4, 'g. No of groups functional after 3 years (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (22, 19, 4, 'ncreased participation of women and youth in agriculture production (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (23, 18, 5, 'a. 40,000 HH subsistence farmers issued with input package', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (24, 18, 5, 'b. 300 agro-dealers trained', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (25, 15, 5, 'c. Staff of services providers trained (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (26, 15, 5, 'd. No. of people trained in crop production practices and technologies (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (27, 15, 5, 'e. No. of people accessing facilitated advisory services (RIMS1) ', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (28, 19, 5, 'f. No. of women and youth sensitized on decision making and access to key resources for production (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (30, 18, 6, 'a. 1,333 farmer groups trained in organizational management', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (31, 15, 6, 'b. No. of crop production groups formed/ trained (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (32, 18, 6, 'c. No. of crop production groups formed', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (33, 15, 6, 'd. No. of formed crop production groups with women in leadership positions (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (34, 19, 6, 'e. No. of women and youth sensitized on leadership skills (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (35, 19, 6, 'f. No. of women and youth sensitized on access to production inputs, extension and advisory services (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (36, 18, 7, 'mproved grain drying technologies adopted by 32,000 subsistence farmers', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (37, 18, 7, 'b. Improved grain storage technologies adopted by 32,000 subsistence farmers', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (38, 18, 7, 'c. Certified warehouses used by 80,000 HH (32,000 subsistence and 48,000 advanced)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (39, 18, 7, 'd. Post-harvest grain losses reduced from 25 to 5% for 80,000 HH (32,000 subsistence and 48,000 advanced)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (40, 18, 7, 'e. Operational self-sufficiency attained for 60 warehouses', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (41, 15, 7, 'f. Producers benefiting from improved access to markets (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (42, 15, 7, 'g. No. of functioning infrastructure after 3 years (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (43, 19, 7, 'h. No. of functioning storage & processing facilities after 3 years (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (44, 18, 8, 'a. 40,000 subsistence farmers trained on post-harvest grain management ', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (45, 15, 8, 'b. People trained on post-production and marketing (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (46, 18, 8, 'c. 60 warehouses certified', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (47, 18, 8, 'd. 100 collection centres rehabilitated', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (48, 15, 8, 'e. No. of storage facilities constructed/ rehabilitated (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (49, 15, 8, 'f. No. of processing facilities constructed/rehabilitated (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (50, 17, 8, 'g. No. of storage facilities constructed (Embu, Nakuru and Kitui) (CIMES)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (52, 18, 9, 'a. 100,000 HH beneficiaries trained in business partnership', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (53, 18, 9, 'b. 60 initiatives of commercial partnership implemented', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (54, 18, 9, 'c. 20 spot improvement of access roads completed', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (55, 15, 9, 'd. Roads constructed/ rehabilitated (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (56, 19, 9, 'e. No. of women and youth sensitized on market access (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (57, 18, 10, 'a. 95,000 farmers producing grain and pulses having access to financial services', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (58, 15, 10, 'b. 62,500 HH using certified warehouse receipting system', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (59, 18, 10, 'c. Percentage increase in access to credit facilities (Bungoma, Kakamega and Nandi) (CIMES)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (60, 15, 10, 'd. Improved access of the poor to financial services (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (61, 19, 10, 'g. Increased access to financial services by women and youth (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (62, 18, 10, 'e. Number and type of new products implemented by EBL1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (63, 15, 10, 'f. Improved performance of financial institutions (RIMS2)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (64, 18, 11, 'a. 40,000 vouchers used by target groups', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (65, 18, 11, 'b. 40,000 category 1 HH trained in financial literacy', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (66, 15, 11, 'c. People trained on financial services  (male, female, youth) (RIMS 1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (67, 18, 11, 'd. 200 entrepreneurs trained in advanced financial training', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (68, 15, 11, 'e. Bank staff trained (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (69, 18, 11, 'f. No of farmers trained', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (70, 15, 11, 'g. Value of voluntary savings (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (71, 15, 11, 'h. Financial institutions participating in the Programme (RIMS 1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (72, 15, 11, 'i. No of active voluntary savers (RIMS1) (males, female, youth)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (73, 15, 11, 'j. Number of active borrowers (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (74, 15, 11, 'k. Value of gross loan portfolio by Programme beneficiaries (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (75, 15, 11, 'l. Total amount of savings by target group(by gender)-target to be defined at start up (RIMS1)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (76, 19, 11, 'm. No. of women and youth sensitized on benefit sharing, off farm businesses and business skills (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (77, 19, 11, 'n. No. of women and youth sensitized on access to financial services (WEAI)', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (78, 18, 12, 'a. 100 value chain financing ventures completed', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (79, 18, 12, 'b. 80 forward contracts signed', NULL, NULL, NULL, NULL, 1);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`, `appraisal_target`, `measurement_unit`, `core`) VALUES (81, 18, 7, 'i. Increased participation of women and youth in postharvest practices and marketing (WEAI)', NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `procurement_method`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `procurement_method` (`id`, `method`) VALUES (1, 'LCS');
INSERT INTO `procurement_method` (`id`, `method`) VALUES (2, 'QCBS');
INSERT INTO `procurement_method` (`id`, `method`) VALUES (3, 'ICB/UNOPS');
INSERT INTO `procurement_method` (`id`, `method`) VALUES (4, 'NS');
INSERT INTO `procurement_method` (`id`, `method`) VALUES (5, 'NCB');
INSERT INTO `procurement_method` (`id`, `method`) VALUES (6, 'NCB/UNOPS');
INSERT INTO `procurement_method` (`id`, `method`) VALUES (7, 'DS');

COMMIT;


-- -----------------------------------------------------
-- Data for table `procurement_plan_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `procurement_plan_type` (`id`, `type`) VALUES (1, 'Goods');
INSERT INTO `procurement_plan_type` (`id`, `type`) VALUES (2, 'Non-Consulting Services(NCS)');
INSERT INTO `procurement_plan_type` (`id`, `type`) VALUES (3, 'Consulting Services');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plan_vs_actual`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `plan_vs_actual` (`id`, `choice`) VALUES (1, 'Plan');
INSERT INTO `plan_vs_actual` (`id`, `choice`) VALUES (2, 'Actual');
INSERT INTO `plan_vs_actual` (`id`, `choice`) VALUES (3, 'Updated');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ifad_prior_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `ifad_prior_review` (`id`, `choice`) VALUES (1, 'Yes');
INSERT INTO `ifad_prior_review` (`id`, `choice`) VALUES (2, 'No');

COMMIT;

