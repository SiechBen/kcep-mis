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
-- Table `component`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `component` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `component` (
  `id` SMALLINT(2) UNSIGNED NOT NULL AUTO_INCREMENT,
  `component` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `component` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `component_UNIQUE` ON `component` (`component` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sub_component`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sub_component` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sub_component` (
  `id` SMALLINT(2) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sub_component` VARCHAR(200) NOT NULL,
  `component` SMALLINT(2) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sub_component_component1`
    FOREIGN KEY (`component`)
    REFERENCES `component` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `sub_component` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `sub_component_UNIQUE` ON `sub_component` (`sub_component` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `activity_name`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_name` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `activity_name` (
  `id` SMALLINT(2) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `parent` SMALLINT(2) UNSIGNED NULL,
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
  `id` SMALLINT(2) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `activity_name` SMALLINT(2) UNSIGNED NOT NULL,
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
  `component` SMALLINT(2) UNSIGNED NULL,
  `sub_component` SMALLINT(2) UNSIGNED NULL,
  `activity_name` SMALLINT(2) UNSIGNED NULL,
  `sub_activity_name` SMALLINT(2) UNSIGNED NULL,
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
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_programme_component1`
    FOREIGN KEY (`component`)
    REFERENCES `component` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_programme_sub_component1`
    FOREIGN KEY (`sub_component`)
    REFERENCES `sub_component` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
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
  `ebl_branch` SMALLINT UNSIGNED NOT NULL,
  `sol_id` VARCHAR(45) NULL,
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
  `quantity` VARCHAR(45) NULL,
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
  `sub_component` SMALLINT(2) UNSIGNED NULL,
  `component` SMALLINT(2) UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_indicator_hierarchy_sub_component1`
    FOREIGN KEY (`sub_component`)
    REFERENCES `sub_component` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_indicator_hierarchy_component1`
    FOREIGN KEY (`component`)
    REFERENCES `component` (`id`)
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
  `quarter` SMALLINT(1) NOT NULL,
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
INSERT INTO `person_role` (`id`, `person_role`) VALUES (6, 'Regional Coordinator');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (7, 'National Officer');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (8, 'KALRO Officer');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (9, 'System Admin');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (10, 'Equity');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (11, 'Warehouse Operator');
INSERT INTO `person_role` (`id`, `person_role`) VALUES (12, 'AGMARK Officer');

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
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (6, 'Equity Group Foundation (EGF)', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (7, 'Equity Bank Limited (EBL)', NULL);
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
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (18, 'GESI', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (19, 'Warehouse', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (20, 'Collection Centre', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (21, 'Cereal Productivity Enhancement', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (22, 'Post-Harvest Management and Market Linkages', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (23, 'Financial Services', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (24, 'Project Management', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (25, 'Effective Agricultural Services', 21);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (26, 'Supporting demand of services and inputs (farmer organization and capacity building)', 21);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (27, 'Post harvest management', 22);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (28, 'Market Linkages and Value Addition', 22);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (29, 'Financial inclusion', 23);
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
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (89, 'CGA', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (90, 'CGA/SP2', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (91, 'SP2', NULL);
INSERT INTO `category` (`id`, `name`, `relative`) VALUES (92, 'IPNI', NULL);
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

COMMIT;


-- -----------------------------------------------------
-- Data for table `phenomenon`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (1, 1, 1);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (2, 2, 1);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (3, 3, 1);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (4, 4, 1);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (5, 5, 2);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (6, 6, 2);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (7, 7, 2);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (8, 8, 2);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (9, 9, 2);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (10, 10, 2);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (11, 11, 2);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (12, 12, 3);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (13, 13, 3);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (14, 7, 6);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (15, 14, 7);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (16, 15, 7);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (17, 16, 7);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (18, 17, 7);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (19, 18, 7);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (20, 19, 8);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (21, 20, 8);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (22, 21, 9);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (23, 22, 9);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (24, 23, 9);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (25, 24, 9);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (26, 25, 10);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (27, 26, 10);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (28, 27, 10);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (29, 28, 10);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (30, 29, 10);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (31, 30, 10);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (32, 31, 11);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (33, 32, 11);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (34, 33, 11);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (35, 34, 12);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (36, 35, 12);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (37, 36, 12);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (38, 37, 12);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (39, 38, 12);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (40, 39, 12);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (41, 40, 13);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (42, 41, 13);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (43, 42, 13);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (44, 43, 13);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (45, 44, 13);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (46, 45, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (47, 46, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (48, 47, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (49, 48, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (50, 49, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (51, 50, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (52, 51, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (53, 52, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (54, 53, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (55, 54, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (56, 55, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (57, 56, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (58, 57, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (59, 58, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (60, 59, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (61, 60, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (62, 61, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (63, 62, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (64, 63, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (65, 64, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (66, 65, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (67, 66, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (68, 67, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (69, 68, 14);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (70, 69, 1);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (71, 70, 1);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (72, 71, 1);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (73, 72, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (74, 73, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (75, 74, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (76, 75, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (77, 76, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (78, 77, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (79, 78, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (80, 79, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (81, 80, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (82, 81, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (83, 82, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (84, 83, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (85, 84, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (86, 85, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (87, 86, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (88, 87, 15);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (89, 88, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (90, 89, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (91, 90, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (92, 91, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (93, 92, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (94, 93, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (95, 94, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (96, 95, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (97, 96, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (98, 97, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (99, 98, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (100, 99, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (101, 5, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (102, 11, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (103, 151, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (104, 152, 16);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (105, 100, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (106, 101, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (107, 102, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (108, 103, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (109, 104, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (110, 105, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (111, 106, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (112, 107, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (113, 108, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (114, 109, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (115, 110, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (116, 111, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (117, 112, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (118, 113, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (119, 114, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (120, 115, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (121, 116, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (122, 117, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (123, 118, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (124, 119, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (125, 120, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (126, 121, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (127, 122, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (128, 123, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (129, 124, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (130, 125, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (131, 126, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (132, 127, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (133, 128, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (134, 129, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (135, 130, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (136, 131, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (137, 132, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (138, 133, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (139, 134, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (140, 135, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (141, 136, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (142, 137, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (143, 138, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (144, 139, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (145, 140, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (146, 141, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (147, 142, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (148, 143, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (149, 144, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (150, 145, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (151, 146, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (152, 147, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (153, 148, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (154, 149, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (155, 150, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (156, 153, 17);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (157, 154, 18);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (158, 155, 18);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (159, 156, 19);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (160, 157, 19);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (161, 158, 19);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (162, 159, 19);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (163, 160, 19);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (164, 161, 19);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (165, 162, 19);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (166, 163, 19);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (167, 164, 20);
INSERT INTO `phenomenon` (`id`, `category`, `phenomenon_type`) VALUES (168, 165, 20);

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
-- Data for table `component`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `component` (`id`, `component`) VALUES (1, 'Cereal Productivity Enhancement');
INSERT INTO `component` (`id`, `component`) VALUES (2, 'Post-Harvest Management and Market Linkages');
INSERT INTO `component` (`id`, `component`) VALUES (3, 'Financial Services');
INSERT INTO `component` (`id`, `component`) VALUES (4, 'Project Management');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sub_component`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `sub_component` (`id`, `sub_component`, `component`) VALUES (1, 'Effective Agricultural Services', 1);
INSERT INTO `sub_component` (`id`, `sub_component`, `component`) VALUES (2, 'Supporting demand of services and inputs (farmer organization and capacity building)', 1);
INSERT INTO `sub_component` (`id`, `sub_component`, `component`) VALUES (3, 'Post harvest management', 2);
INSERT INTO `sub_component` (`id`, `sub_component`, `component`) VALUES (4, 'Market Linkages and Value Addition', 2);
INSERT INTO `sub_component` (`id`, `sub_component`, `component`) VALUES (5, 'Financial Inclusion', 3);
INSERT INTO `sub_component` (`id`, `sub_component`, `component`) VALUES (6, 'Value Chain financing', 3);

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
INSERT INTO `financial_year` (`id`, `financial_year`, `current_year`) VALUES (1, '2016', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sub_activity`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (1, 1, '1.1.1.1', NULL, 46, 105, 1, 1.1, NULL, 1, NULL, NULL, 6, 255, 6, 16, 1350, 73, 89, 'No', 'Posters', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (2, 1, '1.1.1.2', NULL, 46, 105, 1, 1.1, NULL, 2, NULL, NULL, 5, 100, 261, 261, 26100, 73, 90, 'No', 'Meetings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (3, 1, '1.1.1.3', NULL, 46, 105, 1, 1.1, NULL, 3, NULL, NULL, 5, 0.55, 3480, 3480, 1914, 73, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (4, 1, '1.1.1.4', NULL, 46, 105, 1, 1.1, NULL, 4, NULL, NULL, 5, 1350, 16, 16, 21600, 73, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (5, 1, '1.1.1.5', NULL, 46, 105, 1, 1.1, NULL, 5, NULL, NULL, 5, 40, 87, 87, 3480, 73, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (6, 1, '1.1.1.6', NULL, 46, 105, 1, 1.1, NULL, 6, NULL, NULL, 4, 0.55, 3480, 3480, 1914, 73, 89, 'No', 'Posters', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (7, 1, '1.1.2.1', NULL, 47, 106, 1, 1.1, NULL, 7, NULL, NULL, 4, 0.55, 3480, 3480, 1914, 73, 89, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (8, 1, '1.1.2.2', NULL, 47, 106, 1, 1.1, NULL, 8, NULL, NULL, 5, 225, 16, 16, 3600, 73, 89, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (9, 1, '1.1.2.3', NULL, 47, 106, 1, 1.1, NULL, 9, NULL, NULL, 4, 0, 150, 150, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (10, 1, '1.1.2.4', NULL, 47, 106, 1, 1.1, NULL, 10, NULL, NULL, 4, 40, 16, 16, 640, 75, 90, 'No', 'Sensitizations', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (11, 1, '1.1.3.1', NULL, 47, 107, 1, 1.1, NULL, 11, NULL, NULL, 19, 0, 300, 300, NULL, 76, 101, 'No', NULL, NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (12, 1, '1.1.3.2', NULL, 47, 107, 1, 1.1, NULL, 12, NULL, NULL, 9, 2300, 1, 2, 2300, 76, 101, 'No', 'Field visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (13, 1, '1.1.3.3', NULL, 47, 107, 1, 1.1, NULL, 13, NULL, NULL, 4, 0, 1, 1, 0, 75, 101, 'No', 'Deskwork', NULL, NULL, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (14, 1, '1.1.3.4', NULL, 47, 107, 1, 1.1, NULL, 14, NULL, NULL, 6, 7440, 1, 1, 7440, 75, 101, 'No', 'Printing', NULL, NULL, 161, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (15, 1, '1.1.3.5', NULL, 47, 107, 1, 1.1, NULL, 15, NULL, NULL, 6, 1306, 1, 1, 1306, 75, 101, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (16, 1, '1.1.4.1.1', NULL, 48, 108, 1, 1.1, NULL, 16, NULL, NULL, 4, 0, 1, 1, 0, 75, 102, 'No', 'Deskwork', NULL, NULL, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (17, 1, '1.1.4.1.2', NULL, 48, 108, 1, 1.1, NULL, 17, NULL, NULL, 4, 0, 1, 1, 0, 75, 93, 'No', 'Deskwork', NULL, NULL, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (18, 1, '1.1.4.1.3', NULL, 48, 108, 1, 1.1, NULL, 18, NULL, NULL, 4, 0, 1, 1, 0, 75, 101, 'No', 'Deskwork', NULL, NULL, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (19, 1, '1.1.4.1.4', NULL, 48, 108, 1, 1.1, NULL, 19, NULL, NULL, 4, 0, 1, 1, 0, 75, 94, 'No', 'Deskwork', NULL, NULL, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (20, 1, '1.1.4.1.5', NULL, 48, 108, 1, 1.1, NULL, 20, NULL, NULL, 6, 5440, 1, 1, 5440, 75, 102, 'No', 'Meetings', NULL, NULL, 161, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (21, 1, '1.1.4.1.6', NULL, 48, 108, 1, 1.1, NULL, 21, NULL, NULL, 6, 10.64, 1440, 2160, 17560, 75, 102, 'No', 'Printing', NULL, NULL, 161, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (22, 1, '1.1.4.2.1', NULL, 48, 109, 1, 1.1, NULL, 22, NULL, NULL, 10, 0, 1, 1, 0, 75, 102, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (23, 1, '1.1.4.2.2', NULL, 48, 109, 1, 1.1, NULL, 23, NULL, NULL, 6, 7427, 9, 21, 66840, 75, 102, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (24, 1, '1.1.4.2.3', NULL, 48, 109, 1, 1.1, NULL, 24, NULL, NULL, 4, 135, 16, 16, 2160, 81, 102, 'No', 'Field visits', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (25, 1, '1.1.4.3.1', NULL, 48, 109, 1, 1.1, NULL, 25, NULL, NULL, 10, NULL, 1, 1, NULL, 77, 93, 'No', 'Deskwork', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (26, 1, '1.1.4.3.2', NULL, 48, 109, 1, 1.1, NULL, 26, NULL, NULL, 4, 7667, 3, 3, 23000, 77, 93, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (27, 1, '1.1.4.4.1', NULL, 48, 109, 1, 1.1, NULL, 27, NULL, NULL, 10, 0, 1, 1, NULL, 76, 90, 'No', 'Deskwork', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (28, 1, '1.1.4.4.2', NULL, 48, 109, 1, 1.1, NULL, 28, NULL, NULL, 7, 7667, 3, 3, 23000, 76, 102, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (29, 1, '1.1.5.1.1', NULL, 49, 110, 1, 1.1, NULL, 29, NULL, NULL, 8, 0, 100, 150, 0, 77, 102, 'No', 'Deskwork', NULL, NULL, 159, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (30, 1, '1.1.5.1.2', NULL, 49, 110, 1, 1.1, NULL, 30, NULL, NULL, 24, 770, 100, 150, 47000, 77, 102, 'No', 'Demos', NULL, NULL, 159, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (31, 1, '1.1.5.1.3', NULL, 49, 110, 1, 1.1, NULL, 31, NULL, NULL, 7, 300, 100, 150, 30000, 77, 102, 'No', 'Crop management', NULL, NULL, 159, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (32, 1, '1.1.5.2.1', NULL, 49, 111, 1, 1.1, NULL, 32, NULL, NULL, 8, 20, 2329, 3990, 46420, 77, 90, 'No', 'Trainings', NULL, NULL, 159, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (33, 1, '1.1.5.2.2', NULL, 49, 111, 1, 1.1, NULL, 33, NULL, NULL, 8, 90, 2329, 3990, 66740, 77, 90, 'No', 'Trainings', NULL, NULL, 159, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (34, 1, '1.1.5.2.3', NULL, 49, 111, 1, 1.1, NULL, 34, NULL, NULL, 11, 40, 2329, 3990, 92840, 77, 90, 'No', 'Field visits', NULL, NULL, 159, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (35, 1, '1.1.6.1', NULL, 50, 112, 1, 1.1, NULL, 35, NULL, NULL, 4, 103, 300, 600, 31000, 77, 90, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (36, 1, '1.1.6.2', NULL, 50, 112, 1, 1.1, NULL, 36, NULL, NULL, 4, 1840, 25, 35, 46000, 77, 90, 'No', 'Trainings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (37, 1, '1.1.6.3', NULL, 50, 112, 1, 1.1, NULL, 37, NULL, NULL, 4, 250, 20, 27, 5000, 77, 90, 'No', 'Exchange visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (38, 1, '1.1.6.4', NULL, 50, 112, 1, 1.1, NULL, 38, NULL, NULL, 4, 2925, 40, 60, 117000, 77, 90, 'No', 'Exhibitions', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (39, 1, '1.2.1.1.1', NULL, 51, 113, 1, 1.2, NULL, 39, NULL, NULL, 4, 120, 16, 16, 1920, 76, 90, 'No', 'Survey', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (40, 1, '1.2.1.1.2', NULL, 51, 113, 1, 1.2, NULL, 40, NULL, NULL, 4, 0, 1, 1, NULL, 76, 90, 'No', 'Deskwork', NULL, NULL, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (41, 1, '1.2.1.1.3', NULL, 51, 113, 1, 1.2, NULL, 41, NULL, NULL, 4, 14, 350, 700, 5000, 76, 90, 'No', 'Trainings', NULL, NULL, 161, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (42, 1, '1.2.1.2.1', NULL, 51, 114, 1, 1.2, NULL, 42, NULL, NULL, 4, 0, 1, 1, NULL, 76, 90, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (43, 1, '1.2.1.2.2', NULL, 51, 114, 1, 1.2, NULL, 43, NULL, NULL, 5, 43, 350, 700, 15050, 76, 90, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (44, 1, '1.2.1.2.3', NULL, 51, 114, 1, 1.2, NULL, 44, NULL, NULL, 5, 65, 16, 16, 1040, 76, 90, 'No', 'Follow ups', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (45, 1, '1.2.2.1.1', NULL, 52, 115, 1, 1.2, NULL, 45, NULL, NULL, 5, 10, 1092, 4000, 10920, 77, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (46, 1, '1.2.2.1.2', NULL, 52, 115, 1, 1.2, NULL, 46, NULL, NULL, 4, 0, 1092, 4000, 0, 77, 90, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (47, 1, '1.2.2.1.3', NULL, 52, 115, 1, 1.2, NULL, 47, NULL, NULL, 5, 0, 1092, 4000, 0, 77, 90, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (50, 1, '1.2.2.3.1', NULL, 52, 115, 1, 1.2, NULL, 50, NULL, NULL, 5, 103, 300, 600, 31000, 76, 90, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (51, 1, '1.2.2.3.2', NULL, 52, 115, 1, 1.2, NULL, 51, NULL, NULL, 6, 10, 692, 1600, 6920, 76, 101, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (53, 1, '1.2.2.3.4', NULL, 52, 115, 1, 1.2, NULL, 53, NULL, NULL, 4, 10, 12, 24, 120, 76, 90, 'No', 'Follow ups', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (54, 1, '1.2.3.1.1', NULL, 53, 116, 1, 1.2, NULL, 54, NULL, NULL, 6, 20, 2492, 4000, 5000, 76, 101, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (55, 1, '1.2.3.1.2', NULL, 53, 116, 1, 1.2, NULL, 55, NULL, NULL, 4, 250, 20, 32, 5000, 76, 90, 'No', 'Meetings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (56, 1, '1.2.3.2.1', NULL, 53, 116, 1, 1.2, NULL, 56, NULL, NULL, 5, 10, 2492, 4000, 24920, 76, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (57, 1, '1.2.3.2.2', NULL, 53, 116, 1, 1.2, NULL, 57, NULL, NULL, 5, 10, 2492, 4000, 24920, 76, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (58, 1, '1.2.3.3.1', NULL, 53, 116, 1, 1.2, NULL, 58, NULL, NULL, 5, 10, 2492, 4000, 24920, 76, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (59, 1, '1.2.3.3.2', NULL, 53, 116, 1, 1.2, NULL, 59, NULL, NULL, 5, 10, 43, 100, 430, 76, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (60, 1, '1.2.4.1', NULL, 54, 117, 1, 1.2, NULL, 60, NULL, NULL, 6, 20317, 12, 48, 234800, 78, 90, 'No', 'Operational cost', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (61, 1, '1.2.4.2', NULL, 54, 117, 1, 1.2, NULL, 61, NULL, NULL, 16, 30, 2492, 4000, 74760, 79, 90, 'No', 'Follow ups', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (62, 1, '2.1.1.1.1', NULL, 55, 118, 2, 2.1, NULL, 62, NULL, NULL, 4, 0, 1, 1, 0, 75, 90, 'No', 'Deskwork', NULL, NULL, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (63, 1, '2.1.1.1.2', NULL, 55, 118, 2, 2.1, NULL, 63, NULL, NULL, 4, 0, 1, 1, 0, 75, 92, 'No', 'Deskwork', NULL, NULL, 161, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (64, 1, '2.1.1.1.3', NULL, 55, 118, 2, 2.1, NULL, 64, NULL, NULL, 15, 423, 346, 800, 146190, 80, 102, 'Yes', 'Procurement process to be done by PCU', NULL, NULL, 161, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (65, 1, '2.1.1.2.1', NULL, 55, 119, 2, 2.1, NULL, 65, NULL, NULL, 4, 0, 1, 1, 0, 75, 92, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (66, 1, '2.1.1.2.2', NULL, 55, 119, 2, 2.1, NULL, 66, NULL, NULL, 10, 7667, 3, 3, 23000, 75, 102, 'No', 'Meetings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (67, 1, '2.1.1.3.1', NULL, 55, 120, 2, 2.1, NULL, 67, NULL, NULL, 4, 0, 1, 1, 0, NULL, 102, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (68, 1, '2.1.1.3.2', NULL, 55, 120, 2, 2.1, NULL, 68, NULL, NULL, 6, 688, 346, 800, 237880, 75, 103, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (69, 1, '2.1.1.3.3', NULL, 55, 120, 2, 2.1, NULL, 69, NULL, NULL, 6, 90, 346, 800, 31000, 75, 92, 'No', 'Trainings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (70, 1, '2.1.1.3.4', NULL, 55, 120, 2, 2.1, NULL, 70, NULL, NULL, 4, 10, 346, 800, 3460, 81, 92, 'No', 'Field visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (71, 1, '2.1.2.1', NULL, 56, 121, 2, 2.1, NULL, 71, NULL, NULL, 5, NULL, 346, 800, 0, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (72, 1, '2.1.2.2', NULL, 56, 121, 2, 2.1, NULL, 72, NULL, NULL, 5, 10, 346, 800, 3460, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (73, 1, '2.1.2.3', NULL, 56, 121, 2, 2.1, NULL, 73, NULL, NULL, 5, 20, 346, 800, 6920, 76, 103, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (74, 1, '2.1.2.4', NULL, 56, 121, 2, 2.1, NULL, 74, NULL, NULL, 5, 20, 346, 800, 6920, 76, 103, 'No', 'Meetings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (75, 1, '2.1.3.1.1', NULL, 57, 122, 2, 2.1, NULL, 75, NULL, NULL, 5, 10, 43, 100, 430, 82, 103, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (76, 1, '2.1.3.1.2', NULL, 57, 122, 2, 2.1, NULL, 76, NULL, NULL, 6, 1385, 43, 100, 59555, 83, 92, 'No', 'Store visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (77, 1, '2.1.3.1.3', NULL, 57, 122, 2, 2.1, NULL, 77, NULL, NULL, 5, 110, 43, 100, 4730, 76, 92, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (78, 1, '2.1.3.2.1', NULL, 57, 123, 2, 2.1, NULL, 78, NULL, NULL, 5, 110, 43, 100, 4730, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (79, 1, '2.1.3.2.2', NULL, 57, 123, 2, 2.1, NULL, 79, NULL, NULL, 5, 110, 43, 100, 4730, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (80, 1, '2.1.3.2.3', NULL, 57, 123, 2, 2.1, NULL, 80, NULL, NULL, 5, 10, 43, 100, 430, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (81, 1, '2.1.4.1.1', NULL, 58, 124, 2, 2.1, NULL, 81, NULL, NULL, 5, 1350, 16, 16, 21600, 82, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (82, 1, '2.1.4.1.2', NULL, 58, 124, 2, 2.1, NULL, 82, NULL, NULL, 5, 3000, 4, 6, 3000, 83, 92, 'Yes', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (83, 1, '2.1.4.1.3', NULL, 58, 124, 2, 2.1, NULL, 83, NULL, NULL, 12, 266667, 4, 6, 249867, 82, 92, 'No', 'Civil works', NULL, NULL, 159, NULL, NULL, NULL, 15, 85, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (84, 1, '2.1.4.1.4', NULL, 58, 124, 2, 2.1, NULL, 84, NULL, NULL, 12, 12000, 13, 19, 155000, 82, 92, 'Yes', 'Civil works', NULL, NULL, 159, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (85, 1, '2.1.4.1.5', NULL, 58, 124, 2, 2.1, NULL, 85, NULL, NULL, 12, 2589, 23, 35, 59555, 82, 92, 'Yes', 'Equipment', NULL, NULL, 161, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (86, 1, '2.1.4.2.1', NULL, 58, 125, 2, 2.1, NULL, 86, NULL, NULL, 5, 50, 23, 60, 1150, 82, 92, 'No', 'Meetings', NULL, NULL, 159, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (87, 1, '2.1.4.2.2', NULL, 58, 125, 2, 2.1, NULL, 87, NULL, NULL, 11, 50, 46, 120, 2300, 82, 92, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (88, 1, '2.1.4.2.3', NULL, 58, 125, 2, 2.1, NULL, 88, NULL, NULL, 13, 988, 17, 25, 16800, 83, 92, 'No', 'Certification', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (90, 1, '2.1.4.3.1', NULL, 58, 126, 2, 2.1, NULL, 90, NULL, NULL, 5, 50, 23, 60, 1150, 75, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (91, 1, '2.1.4.3.2', NULL, 58, 126, NULL, 2.1, NULL, 91, NULL, NULL, 5, 40, 23, 60, 920, 75, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (92, 1, '2.1.4.3.3', NULL, 58, 126, 2, 2.1, NULL, 92, NULL, NULL, 6, 89000, 1, 4, 89000, 75, 92, 'No', 'Operation cost', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (93, 1, '2.1.4.4.1', NULL, 58, 127, 2, 2.1, NULL, 93, NULL, NULL, 5, 50, 23, 60, 1150, 75, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (94, 1, '2.1.4.4.2', NULL, 58, 127, 2, 2.1, NULL, 94, NULL, NULL, 5, 430, 23, 60, 9890, 75, 92, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (95, 1, '2.1.4.4.3', NULL, 58, 127, 2, 2.1, NULL, 95, NULL, NULL, 5, 50, 46, 120, 2300, 75, 92, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (96, 1, '2.1.4.4.4', NULL, 58, 127, 2, 2.1, NULL, 96, NULL, NULL, 5, 50, 23, 60, 1150, 74, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (97, 1, '2.2.1.1.1', NULL, 59, 128, 2, 2.2, NULL, 97, NULL, NULL, 4, 0, 1, 1, 0, 75, 92, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (98, 1, '2.2.1.1.2', NULL, 59, 128, 2, 2.2, NULL, 98, NULL, NULL, 5, 660, 25, 60, 16500, 75, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (99, 1, '2.2.1.1.3', NULL, 59, 128, 2, 2.2, NULL, 99, NULL, NULL, 5, 330, 25, 60, 8250, 75, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (100, 1, '2.2.1.1.4', NULL, 59, 128, 2, 2.2, NULL, 100, NULL, NULL, 4, 0, 1, 1, NULL, 75, 92, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (101, 1, '2.2.1.1.5', NULL, 59, 128, 2, 2.2, NULL, 101, NULL, NULL, 4, 170, 25, 60, 4250, 75, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (102, 1, '2.2.1.2.1', NULL, 59, 129, 2, 2.2, NULL, 102, NULL, NULL, 4, 10, 2292, 4000, 22920, 76, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (103, 1, '2.2.1.2.2', NULL, 59, 129, 2, 2.2, NULL, 103, NULL, NULL, 5, 10, 2292, 4000, 2500, 76, 103, 'No', 'Meetings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (104, 1, '2.2.1.2.3', NULL, 59, 129, 2, 2.2, NULL, 104, NULL, NULL, 5, 60, 2292, 4000, 137520, 76, 103, 'No', 'Meetings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (105, 1, '2.2.1.2.4', NULL, 59, 130, 2, 2.2, NULL, 105, NULL, NULL, 5, 10, 25, 60, 2500, 76, 103, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (106, 1, '2.2.1.3.1', NULL, 59, 130, 2, 2.2, NULL, 106, NULL, NULL, 5, 330, 25, 60, 8250, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (107, 1, '2.2.1.3.2', NULL, 59, 130, 2, 2.2, NULL, 107, NULL, NULL, 5, 30, 25, 60, 750, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (108, 1, '2.2.1.3.3', NULL, 59, 130, 2, 2.2, NULL, 108, NULL, NULL, 5, 208, 25, 60, 5190, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (109, 1, '2.2.1.3.4', NULL, 59, 130, 2, 2.2, NULL, 109, NULL, NULL, 5, 0, 25, 60, 0, 76, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (110, 1, '2.2.2.1.1', NULL, 60, 131, 2, 2.2, NULL, 110, NULL, NULL, 4, 0, 1, 1, 0, 75, 92, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (111, 1, '2.2.2.1.2', NULL, 60, 131, 2, 2.2, NULL, 111, NULL, NULL, 4, 0, 1, 1, 0, 75, 92, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (112, 1, '2.2.2.1.3', NULL, 60, 131, 2, 2.2, NULL, 112, NULL, NULL, 5, 10, 87, 87, 870, 75, 103, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (113, 1, '2.2.2.1.4', NULL, 60, 131, 2, 2.2, NULL, 113, NULL, NULL, 5, 225, 16, 16, 3600, 75, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (114, 1, '2.2.2.2.1', NULL, 60, 131, 2, 2.2, NULL, 114, NULL, NULL, 17, 2000, 11, 11, 8000, 75, 92, 'No', 'Study', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (115, 1, '2.2.2.2.2', NULL, 60, 131, 2, 2.2, NULL, 115, NULL, NULL, 4, 0, 11, 11, 0, 75, 92, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (116, 1, '2.2.2.2.3', NULL, 60, 131, 2, 2.2, NULL, 116, NULL, NULL, 6, 20250, 11, 11, 81000, 75, 92, 'No', 'Operational cost', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (117, 1, '2.2.2.3.1', NULL, 60, 131, 2, 2.2, NULL, 117, NULL, NULL, 4, 0, 9, 9, 0, 75, 92, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (118, 1, '2.2.2.3.2', NULL, 60, 131, 2, 2.2, NULL, 118, NULL, NULL, 4, 0, 9, 9, 0, 75, 92, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (119, 1, '2.2.2.3.3', NULL, 60, 131, 2, 2.2, NULL, 119, NULL, NULL, 5, 0, 87, 87, 0, 75, 92, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (120, 1, '2.2.3.1', NULL, 60, 132, 2, 2.2, NULL, 120, NULL, NULL, 5, 0, 0, 20, 0, 82, 92, NULL, 'Meetings', NULL, NULL, 159, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (121, 1, '2.2.3.2', NULL, 60, 132, 2, 2.2, NULL, 121, NULL, NULL, 5, 0, 0, 20, 0, 82, 92, NULL, 'Meetings', NULL, NULL, 159, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (122, 1, '2.2.3.3', NULL, 60, 132, 2, 2.2, NULL, 122, NULL, NULL, 18, 0, 0, 20, 0, 82, 92, NULL, 'Civil works', NULL, NULL, 159, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (123, 1, '2.2.3.4', NULL, 60, 132, 2, 2.2, NULL, 123, NULL, NULL, 4, 0, 0, 20, 0, 82, 92, 'No', 'Meetings', NULL, NULL, 159, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (124, 1, '3.1.1.1.1', NULL, 61, 133, 3, 3.1, NULL, 124, NULL, NULL, 4, 0, 26300, 40000, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (125, 1, '3.1.1.1.2', NULL, 61, 133, 3, 3.1, NULL, 125, NULL, NULL, 6, 17, 22300, 40000, 379100, 74, 91, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (126, 1, '3.1.1.1.3', NULL, 61, 133, 3, 3.1, NULL, 126, NULL, NULL, 6, 1, 22300, 40000, 22300, 74, 91, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (130, 1, '3.1.2.1', NULL, 62, 135, 3, 3.1, NULL, 130, NULL, NULL, 4, NULL, 27300, 40000, NULL, 74, 104, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (131, 1, '3.1.2.2', NULL, 62, 135, 3, 3.1, NULL, 131, NULL, NULL, 11, 10, 15000, 40000, 150000, 74, 104, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (132, 1, '3.1.2.3', NULL, 62, 135, 3, 3.1, NULL, 132, NULL, NULL, 11, 10, 12300, 40000, 123000, 74, 104, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (133, 1, '3.1.2.4', NULL, 62, 135, 3, 3.1, NULL, 133, NULL, NULL, 4, NULL, 1092, 1600, NULL, 74, 104, 'No', 'Field visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (134, 1, '3.1.2.5', NULL, 62, 135, 3, 3.1, NULL, 134, NULL, NULL, 4, NULL, 1, 1, NULL, 74, 104, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (135, 1, '3.1.3.1.1', NULL, 63, 136, 3, 3.1, NULL, 135, NULL, NULL, 4, 0, 150, 300, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (136, 1, '3.1.3.1.2', NULL, 63, 136, 3, 3.1, NULL, 136, NULL, NULL, 6, 17, 150, 300, 2250, 74, 91, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (137, 1, '3.1.3.1.3', NULL, 63, 136, 3, 3.1, NULL, 137, NULL, NULL, 6, 9, 150, 300, 1350, 74, 91, 'No', 'Trainings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (138, 1, '3.1.3.1.4', NULL, 63, 136, 3, 3.1, NULL, 138, NULL, NULL, 4, NULL, 150, 300, NULL, 74, 91, 'No', 'Deskwork', NULL, NULL, 163, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (139, 1, '3.1.3.2.1', NULL, 63, 137, 3, 3.1, NULL, 139, NULL, NULL, 4, NULL, 300, 300, NULL, 74, 101, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (140, 1, '3.1.3.2.2', NULL, 63, 137, 3, 3.1, NULL, 140, NULL, NULL, 5, NULL, 300, 300, NULL, 74, 91, 'No', 'Meetings', NULL, NULL, 163, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (141, 1, '3.1.3.2.3', NULL, 63, 137, 3, 3.1, NULL, 141, NULL, NULL, 24, NULL, 300, 300, NULL, 74, 101, 'No', 'Visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (142, 1, '3.1.4.1.1', NULL, 64, 138, 3, 3.1, NULL, 142, NULL, NULL, 6, 2538, 8, 8, 20304, 76, 96, 'No', 'Meetings', NULL, NULL, 162, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (143, 1, '3.1.4.1.2', NULL, 64, 138, 3, 3.1, NULL, 143, NULL, NULL, 4, 1265, 16, 16, 20240, 74, 95, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (144, 1, '3.1.4.1.3', NULL, 64, 138, 3, 3.1, NULL, 144, NULL, NULL, 6, NULL, 1, 1, NULL, 74, 101, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (145, 1, '3.1.4.2.1', NULL, 64, 139, 3, 3.1, NULL, 145, NULL, NULL, 5, NULL, 1, 1, NULL, 76, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (146, 1, '3.1.4.2.2', NULL, 64, 139, 3, 3.1, NULL, 146, NULL, NULL, 5, 1265, 16, 16, 20240, 74, 101, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (147, 1, '3.1.4.3.1', NULL, 64, 140, 3, 3.1, NULL, 147, NULL, NULL, 4, NULL, 27300, 40000, NULL, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (148, 1, '3.1.4.3.2', NULL, 64, 140, 3, 3.1, NULL, 148, NULL, NULL, 4, NULL, 27300, 40000, NULL, 78, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (149, 1, '3.1.4.3.3', NULL, 64, 140, 3, 3.1, NULL, 149, NULL, NULL, 4, NULL, 1, 1, NULL, 78, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (150, 1, '3.1.4.4.1', NULL, 64, 141, 3, 3.1, NULL, 150, NULL, NULL, 5, NULL, 1, 1, NULL, 74, 91, 'No', NULL, NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (151, 1, '3.1.4.4.2', NULL, 64, 141, 3, 3.1, NULL, 151, NULL, NULL, 4, NULL, 1, 1, NULL, 74, 91, 'No', NULL, NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (152, 1, '3.1.4.4.3', NULL, 64, 141, 3, 3.1, NULL, 152, NULL, NULL, 5, 20, 1092, 1600, 21840, 79, 97, 'No', 'Group visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (153, 1, '3.1.4.5.1', NULL, 64, 142, 3, 3.1, NULL, 153, NULL, NULL, 6, 117, 17300, 40000, 2027579, 74, 91, 'No', 'Inputs value', NULL, NULL, 166, NULL, NULL, NULL, 10, 90, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (154, 1, '3.1.4.5.2', NULL, 64, 142, 3, 3.1, NULL, 154, NULL, NULL, 6, 20, 17300, 40000, 337833, 74, 91, 'No', 'Inputs value', NULL, NULL, 164, 100, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (158, 1, '3.1.5.2.1', NULL, 65, 144, 3, 3.1, NULL, 158, NULL, NULL, 4, 0, 1, 1, NULL, 84, 91, 'No', 'Deskwork', NULL, NULL, 162, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (159, 1, '3.1.5.2.2', NULL, 65, 144, 3, 3.1, NULL, 159, NULL, NULL, 4, 1040, 1, 1, 1040, 79, 91, 'No', 'Deskwork', NULL, NULL, 162, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (160, 1, '3.1.5.2.3', NULL, 65, 144, 3, 3.1, NULL, 160, NULL, NULL, 4, 40, 692, 1600, 27680, 79, 91, 'No', 'Data collection', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (161, 1, '3.1.5.2.4', NULL, 65, 144, 3, 3.1, NULL, 161, NULL, NULL, 4, 0, 1, 1, NULL, 79, 91, 'No', 'Reporting', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (162, 1, '3.2.1.1.1', NULL, 66, 145, 3, 3.2, NULL, 162, NULL, NULL, 4, NULL, 1, 1, NULL, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (163, 1, '3.2.1.1.2', NULL, 66, 145, 3, 3.2, NULL, 163, NULL, NULL, 4, NULL, 1, 1, NULL, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (164, 1, '3.2.1.1.3', NULL, 66, 145, 3, 3.2, NULL, 164, NULL, NULL, 11, 40, 46, 120, 1840, 74, 91, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (165, 1, '3.2.1.1.4', NULL, 66, 145, 3, 3.2, NULL, 165, NULL, NULL, 11, 430, 23, 60, 9890, 74, 91, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (166, 1, '3.2.1.2.1', NULL, 66, 146, 3, 3.2, NULL, 166, NULL, NULL, 4, 0, 1, 1, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (167, 1, '3.2.1.2.2', NULL, 66, 146, 3, 3.2, NULL, 167, NULL, NULL, 4, 0, 1, 1, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (168, 1, '3.2.1.2.3', NULL, 66, 146, 3, 3.2, NULL, 168, NULL, NULL, 5, 40, 23, 60, 920, 81, 91, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (169, 1, '3.2.2.1.1', NULL, 67, 147, 3, 3.2, NULL, 169, NULL, NULL, 4, 0, 1, 1, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (170, 1, '3.2.2.1.2', NULL, 67, 147, 3, 3.2, NULL, 170, NULL, NULL, 4, 0, 1, 1, 0, 74, 97, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (171, 1, '3.2.2.1.3', NULL, 67, 147, 3, 3.2, NULL, 171, NULL, NULL, 5, 10, 1092, 1600, 10920, 74, 97, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (172, 1, '3.2.2.1.4', NULL, 67, 147, 3, 3.2, NULL, 172, NULL, NULL, 5, 0, 1, 1, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (173, 1, '3.2.2.2.1', NULL, 67, 148, 3, 3.2, NULL, 173, NULL, NULL, 5, 0, 1, 1, 0, 74, 98, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (174, 1, '3.2.2.2.2', NULL, 67, 148, 3, 3.2, NULL, 174, NULL, NULL, 4, 0, 1, 1, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (175, 1, '3.2.2.2.3', NULL, 67, 148, 3, 3.2, NULL, 175, NULL, NULL, 5, 225, 16, 16, 3600, 74, 99, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (176, 1, '3.2.2.3.1', NULL, 67, 149, 3, 3.2, NULL, 176, NULL, NULL, 4, 0, 1, 1, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (177, 1, '3.2.2.3.2', NULL, 67, 149, 3, 3.2, NULL, 177, NULL, NULL, 5, 670, 118, 200, 79080, 74, 97, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (178, 1, '3.2.2.3.3', NULL, 67, 149, 3, 3.2, NULL, 178, NULL, NULL, 21, 0, 118, 200, 0, 74, 91, 'No', 'Funds', NULL, NULL, 164, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (179, 1, '3.2.2.3.4', NULL, 67, 149, 3, 3.2, NULL, 179, NULL, NULL, 22, 40, 23, 60, 920, 81, 91, 'No', 'Field visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (180, 1, '3.2.2.4.1', NULL, 67, 150, 3, 3.2, NULL, 180, NULL, NULL, 4, 0, 1, 1, 0, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (181, 1, '3.2.2.4.2', NULL, 67, 150, 3, 3.2, NULL, 181, NULL, NULL, 5, 120, 16, 16, 1920, 74, 91, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (182, 1, '3.2.2.4.3', NULL, 67, 150, 3, 3.2, NULL, 182, NULL, NULL, 4, 0, 1, 1, 0, 85, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (183, 1, '3.2.2.4.4', NULL, 67, 150, 3, 3.2, NULL, 183, NULL, NULL, 6, 33560, 20, 80, 671200, 74, 91, 'No', 'Farms', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (184, 1, '3.2.2.4.5', NULL, 67, 150, 3, 3.2, NULL, 184, NULL, NULL, 24, 10, 20, 80, 200, 81, 91, 'No', 'Field visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (185, 1, '3.2.2.4.6', NULL, 68, 151, 3, 3.2, NULL, 185, NULL, NULL, 4, 11670, 3, 9, 35000, 74, 91, 'No', 'Financial support', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (186, 1, '4.1.1.1', NULL, 68, 156, 4, NULL, NULL, 186, NULL, NULL, 6, 1125, 12, 48, 13500, 78, NULL, 'Yes', 'Procurement', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (187, 1, '4.1.1.2', NULL, 68, 156, 4, NULL, NULL, 187, NULL, NULL, 6, 15000, 1, 1, 15000, 78, NULL, 'No', 'Branding', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (188, 1, '4.1.1.3', NULL, 68, 156, 4, NULL, NULL, 188, NULL, NULL, 6, 13000, 1, 1, 13000, 78, NULL, 'No', 'Operational cost', NULL, NULL, 160, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (189, 1, '4.1.1.4', NULL, 68, 156, 4, NULL, NULL, 189, NULL, NULL, 6, 8010, 1, 1, 8010, 78, NULL, 'No', 'Operational cost', NULL, NULL, 160, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (190, 1, '4.1.1.5', NULL, 68, 156, 4, NULL, NULL, 190, NULL, NULL, 6, 50000, 1, 1, 50000, 78, NULL, 'No', 'Insurance covers', NULL, NULL, 165, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (191, 1, '4.1.1.6', NULL, 68, 156, 4, NULL, NULL, 191, NULL, NULL, 6, 5000, 1, 1, 5000, 78, NULL, 'No', 'Publications', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (192, 1, '4.1.1.7', NULL, 68, 156, 4, NULL, NULL, 192, NULL, NULL, 23, 97681, 12, 48, 1172172, 78, NULL, 'No', 'Operational cost', NULL, NULL, 165, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (193, 1, '4.1.1.8', NULL, 68, 156, 4, NULL, NULL, 193, NULL, NULL, 6, 15000, 1, 1, 15000, 78, NULL, 'Yes', 'Procurement', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (194, 1, '4.1.1.9', NULL, 68, 156, 4, NULL, NULL, 194, NULL, NULL, 6, 6200, 1, 1, 6200, 78, NULL, 'Yes', 'Procurement', NULL, NULL, 161, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (195, 1, '4.1.1.10', NULL, 68, 156, 4, NULL, NULL, 195, NULL, NULL, 6, 74613, 4, 7, 298450, 78, NULL, 'Yes', 'Procurement', NULL, NULL, 160, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (196, 1, '4.1.1.11', NULL, 68, 156, 4, NULL, NULL, 196, NULL, NULL, 6, 8640, 1, 1, 8640, 78, NULL, 'Yes', 'Procurement', NULL, NULL, 161, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (197, 1, '4.1.1.12', NULL, 68, 156, 4, NULL, NULL, 197, NULL, NULL, 6, 4000, 4, 4, 1600, 78, NULL, 'No', 'Trainings', NULL, NULL, 165, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (198, 1, '4.1.1.13', NULL, 68, 156, 4, NULL, NULL, 198, NULL, NULL, 6, 3000, 12, 48, 3600, 78, NULL, 'No', 'Fees', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (199, 1, '4.1.2.1', NULL, 68, 152, 4, NULL, NULL, 199, NULL, NULL, 5, 7000, 2, 8, 4000, 86, NULL, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (200, 1, '4.1.2.2', NULL, 68, 152, 4, NULL, NULL, 200, NULL, NULL, 5, 797, 16, 48, 1275, 86, NULL, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (201, 1, '4.1.2.3', NULL, 68, 152, 4, NULL, NULL, 201, NULL, NULL, 5, 564, 64, 208, 36096, 76, NULL, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (202, 1, '4.1.2.4', NULL, 68, 152, 4, NULL, NULL, 202, NULL, NULL, 10, 255, 72, 234, 18376, 76, NULL, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (203, 1, '4.1.2.5', NULL, 68, 152, 4, NULL, NULL, 203, NULL, NULL, 10, 19230, 1, 4, 19230, 87, NULL, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (204, 1, '4.2.1.1', NULL, 69, 153, 4, NULL, NULL, 204, NULL, NULL, 6, 44000, 1, 4, 44000, 87, 100, 'No', 'Study', NULL, NULL, 162, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (205, 1, '4.2.1.2', NULL, 69, 153, 4, NULL, NULL, 205, NULL, NULL, 4, 5000, 1, 1, 5000, 87, NULL, 'No', 'Meetings', NULL, NULL, 162, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (206, 1, '4.2.2.1', NULL, 69, 154, 4, NULL, NULL, 206, NULL, NULL, 5, 0, 1, 1, 0, 79, NULL, NULL, 'Deskwork', NULL, NULL, 162, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (207, 1, '4.2.2.2', NULL, 69, 154, 4, NULL, NULL, 207, NULL, NULL, 6, 34266, 1, 1, 34266, 79, 100, 'Yes', 'Survey', NULL, NULL, 162, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (208, 1, '4.2.2.3', NULL, 69, 154, 4, NULL, NULL, 208, NULL, NULL, 10, 5734, 1, 1, 5737, 79, NULL, 'No', 'Workshops', NULL, NULL, 162, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (209, 1, '4.2.3.1', NULL, 69, 155, 4, NULL, NULL, 209, NULL, NULL, 4, 20000, 1, 4, 20000, 79, NULL, 'No', 'Workshops', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (210, 1, '4.2.3.2', NULL, 69, 155, 4, NULL, NULL, 210, NULL, NULL, 4, 1190, 8, 26, 9520, 75, NULL, 'No', 'Study', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (211, 1, '4.2.3.3', NULL, 69, 155, 4, NULL, NULL, 211, NULL, NULL, 4, 22710, 1, 3, 22710, 87, NULL, 'No', 'Trainings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (212, 1, '4.2.3.4', NULL, 69, 155, 4, NULL, NULL, 212, NULL, NULL, 4, 4750, 4, 13, 19000, 79, NULL, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (213, 1, '4.2.3.5', NULL, 69, 155, 4, NULL, NULL, 213, NULL, NULL, 4, 3845, 1, 1, 3845, 87, NULL, 'No', 'Trainings', NULL, NULL, 163, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (214, 1, '4.2.3.6', NULL, 69, 155, 4, NULL, NULL, 214, NULL, NULL, 6, 15501, 1, 4, 15501, 78, NULL, 'No', 'Field visits and meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (215, 1, '4.2.3.7', NULL, 69, 155, 4, NULL, NULL, 215, NULL, NULL, 4, NULL, 4, 13, NULL, 79, NULL, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (48, 1, '1.2.2.2.1', NULL, 52, 115, 1, NULL, NULL, 48, NULL, NULL, 5, 10, 1092, 4000, 10920, 77, 90, 'No', 'Trainings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (49, 1, '1.2.2.2.2', NULL, 52, 115, 1, NULL, NULL, 49, NULL, NULL, 4, 10, 1092, 4000, 10920, 77, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (52, 1, '1.2.2.3.3', NULL, 52, 115, 1, NULL, NULL, 52, NULL, NULL, 4, 500, 12, 24, 5880, 76, 90, 'No', 'Visits', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (89, 1, '2.1.4.2.4', NULL, 58, 125, 2, NULL, NULL, 89, NULL, NULL, 13, 988, 6, 10, 5928, 75, 92, 'No', 'Certification', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (127, 1, '3.1.1.2.1', NULL, 61, 134, 3, NULL, NULL, 127, NULL, NULL, 5, 10, 1092, 1600, 1092, 74, 90, 'No', 'Meetings', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, 100, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (128, 1, '3.1.1.2.2', NULL, 61, 134, 3, NULL, NULL, 128, NULL, NULL, 5, NULL, 1, 1, NULL, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (129, 1, '3.1.1.2.3', NULL, 61, 134, 3, NULL, NULL, 129, NULL, NULL, 20, NULL, 1, 1, NULL, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (155, 1, '3.1.5.1.1', NULL, 65, 143, 3, NULL, NULL, 155, NULL, NULL, 17, 35000, 1, 1, 35000, 74, 91, 'No', 'Study', NULL, NULL, 166, NULL, NULL, NULL, NULL, 100, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (156, 1, '3.1.5.1.2', NULL, 65, 143, 3, NULL, NULL, 156, NULL, NULL, 4, 0, 1, 1, NULL, 74, 91, 'No', 'Deskwork', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sub_activity` (`id`, `financial_year`, `annual_workplan_reference_code`, `gfss_code`, `expected_outcome`, `annual_indicator`, `component`, `sub_component`, `activity_name`, `sub_activity_name`, `start_date`, `end_date`, `measurement_unit`, `unit_cost`, `awpb_target`, `programme_target`, `totals`, `response_pcu`, `implementing_partner`, `procurement_plan`, `description`, `value_achieved`, `allocated_budget`, `expenditure_category`, `gok_percentage`, `ifad_loan_percentage`, `ifad_grant_percentage`, `beneficiaries_percentage`, `eu_percentage`, `financial_institution_percentage`, `county`) VALUES (157, 1, '3.1.5.1.3', NULL, 65, 143, 3, NULL, NULL, 157, NULL, NULL, 4, 0, 1, 1, NULL, 74, 91, 'No', 'Field test', NULL, NULL, 166, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (1, 'Goal: National food security improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (2, 'Programme objectives\nIncreased production of targeted cereal staples (maize, sorghum, millet and associated pulses)\nIncreased income of smallholders in medium and high potential production areas', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (4, 'Outcome 1: Productivity for maize, sorghum, finger millet and pulses increased', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (5, 'Output 1.1; Agricultural services/ inputs improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (6, 'Output 1.2; Capacity of farmers’ organizations to mobilize extension services, participate in local planning and value chains fora improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (7, 'Outcome 2; Post harvest practices and market linkages for targeted VCs improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (8, 'Output 2.1; Post-harvest management improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (9, 'Output 2.2; Market Access for participating smallholders improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (10, 'Outcome 3; Financial inclusion of beneficiaries improved', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (11, 'Output 3.1 : Use of financial tools and services by target groups increased', NULL, NULL);
INSERT INTO `result_hierarchy` (`id`, `description`, `sub_component`, `component`) VALUES (12, 'Output 3.2: Access to value chain financing improved', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `performance_indicator`
-- -----------------------------------------------------
START TRANSACTION;
USE `kcep_mis`;
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (1, 16, 1, 'a. No. of households uplifted from poverty Reduced poverty', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (2, 15, 1, 'c. Percentage reduction in the prevalence of child malnutrition', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (3, 15, 1, 'b. No. of households with improvement in assets ownership index (productive assets, bikes, radios, improving housing, tin roofs etc)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (4, 15, 1, 'd. Reduction in HH experiencing one hungry season per year', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (5, 16, 1, 'e. Reduced hunger', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (6, 19, 1, 'f. Increased empowerment of women and youth in agriculture', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (7, 19, 1, 'g. Increased empowerment of women and youth in agriculture (WEAI 80%)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (8, 18, 2, 'a. Percentage reduction of the national grain deficit', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (9, 15, 2, 'b. No. of targeted households using improved inputs voluntarily', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (10, 15, 2, 'c. No. of targeted small farmers in commercial contracts', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (11, 15, 2, 'd. No. of targeted smallholders engaged in financial services (i.e taking investment loans)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (12, 15, 2, 'e. No. of targeted households bulking and selling grains at price 30% higher than farmgate', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (13, 19, 2, 'f. No. of M&E officers trained on gender mainstreaming and social inclusion', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (14, 19, 2, 'g. No. of women and youth empowered in decision making', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (15, 15, 4, 'a. Improved agricultural production', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (16, 18, 4, 'b. No. of HH adopting improved practices/packages ', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (17, 15, 4, 'c. No of farmers using purchased inputs', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (18, 17, 4, 'd. No. of farmers adopting modern techniques (Embu, Bungoma, Nakuru and Kitui)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (19, 15, 4, 'e. No / Percentage of farmers reporting production/ improved yields', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (20, 15, 4, 'f. No of farmers adopting recommended technologies', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (21, 15, 4, 'g. No of groups functional after 3 years', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (22, 19, 4, 'h. Increased participation of women and youth in agriculture production', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (23, 18, 5, 'a. No. of subsistence farmers issued with input package', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (24, 18, 5, 'b. No. of agro-dealers trained', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (25, 15, 5, 'c. Staff of services providers trained', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (26, 15, 5, 'd. No. of people trained in crop production practices and technologies', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (27, 15, 5, 'e. No. of people accessing facilitated advisory services', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (28, 19, 5, 'f. No. of women and youth sensitized on decision making and access to key resources for production', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (29, 19, 5, 'g. No. of women and youth sensisitized on behavioural change, communication and sharing of household roles', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (30, 18, 6, 'a. No of farmer groups trained in organizational management', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (31, 15, 6, 'b. No. of crop production groups formed/ trained', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (32, 18, 6, 'c. No. of crop production groups formed', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (33, 15, 6, 'd. No. of formed crop production groups with women in leadership positions', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (34, 19, 6, 'e. No. of women and youth sensitized on leadership skills ', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (35, 19, 6, 'f. No. of women and youth sensitized on access to production inputs, extension and advisory services', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (36, 18, 7, 'a. No. of subsistence farmers adopting improved grain drying technologies ', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (37, 18, 7, 'b. No. of subsistence farmers adopting improved grain storage technologies ', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (38, 18, 7, 'c. No. of farmers using certified warehouses', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (39, 18, 7, 'd. Percentage reduction in post-harvest grain losses', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (40, 18, 7, 'e. No. of warehouses that have attained operational self-sufficiency', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (41, 15, 7, 'f. No. of functioning infrastructure after 3 years', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (42, 15, 7, 'g. No. of functioning storage & processing facilities after 3 years', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (43, 19, 7, 'i. Increased participation of women and youth in postharvest practices and marketing ', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (44, 18, 8, 'a. No. of subsistence farmers trained on post-harvest grain management', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (45, 15, 8, 'b. People trained on post-production and marketing', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (46, 18, 8, 'c. No. of warehouses certified', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (47, 18, 8, 'd. No. of collection centres rehabilitated', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (48, 15, 8, 'e. No. of storage facilities constructed/ rehabilitated', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (49, 15, 8, 'f. No. of processing facilities constructed/rehabilitated', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (50, 17, 8, 'g. No. of storage facilities constructed(Embu, Nakuru and Kitui)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (51, 19, 8, 'h. No. of women and youth sensitized on adoption of time and energy saving technologies ', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (52, 18, 9, 'a. No. of HH beneficiaries trained in business partnership', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (53, 18, 9, 'b. No. of initiatives of commercial partnership implemented ', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (54, 18, 9, 'c. No. of access road improvement completed (spot road improvements)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (55, 15, 9, 'd. Roads constructed/ rehabilitated', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (56, 19, 9, 'e. No. of women and youth sensitized on market access', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (57, 18, 10, 'a. No. of farmers producing grain and pulses have access to financial services', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (58, 15, 10, 'b. No. of HH using certified warehouse receipting system', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (59, 18, 10, 'c. No. / % increase in access to credit facilities (Bungoma, Kakamega and Nandi)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (60, 15, 10, 'd. Improved access of the poor to financial services', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (61, 19, 10, 'g. Increased access to financial services by women and youth', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (62, 18, 10, 'e. Number and type of new products implemented by EBL', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (63, 15, 10, 'f. Improved performance of financial institutions', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (64, 18, 11, 'a. No. of vouchers used by target groups', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (65, 18, 11, 'b. No. of category 1 HH trained in financial literacy ', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (66, 15, 11, 'c. People trained on financial services  (male, female, youth)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (67, 18, 11, 'd. No. of entrepreneurs trained in advanced financial training', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (68, 15, 11, 'e. Bank staff trained', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (69, 18, 11, 'f. No of farmers trained', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (70, 15, 11, 'g. Value of voluntary savings', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (71, 15, 11, 'h. Financial institutions participating in the Programme', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (72, 15, 11, 'i. No of active voluntary savers (RIMS1) (males, female, youth)', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (73, 15, 11, 'j. Number of active borrowers', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (74, 15, 11, 'k. Value of gross loan portfolio by Programme beneficiaries', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (75, 15, 11, 'l. Total amount of savings by target group(by gender)-target to be defined at start up', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (76, 19, 11, 'm. No. of women and youth sensitized on benefit sharing, off farm businesses and business skills', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (77, 19, 11, 'n. No. of women and youth sensitized on access to financial services', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (78, 18, 12, 'a. No. of value chain financing ventures completed', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (79, 18, 12, 'b. No. of forward contracts signed', NULL, NULL);
INSERT INTO `performance_indicator` (`id`, `performance_indicator_type`, `result_hierarchy`, `description`, `baseline_date`, `baseline_value`) VALUES (80, 18, 7, 'f. Producers benefiting from improved access to markets', NULL, NULL);

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

