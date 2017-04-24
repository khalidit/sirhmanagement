-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sirh_database
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sirh_database` ;

-- -----------------------------------------------------
-- Schema sirh_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sirh_database` DEFAULT CHARACTER SET utf8 ;
USE `sirh_database` ;

-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `country` ;

CREATE TABLE IF NOT EXISTS `country` (
  `countryId` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`countryId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `name_UNIQUE` ON `country` (`name` ASC);


-- -----------------------------------------------------
-- Table `zipcode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `zipcode` ;

CREATE TABLE IF NOT EXISTS `zipcode` (
  `zipCodeId` INT(11) NOT NULL AUTO_INCREMENT,
  `zip` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`zipCodeId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `zipCodeId_UNIQUE` ON `zipcode` (`zipCodeId` ASC);

CREATE UNIQUE INDEX `zipcode_UNIQUE` ON `zipcode` (`zipcode` ASC);


-- -----------------------------------------------------
-- Table `city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `city` ;

CREATE TABLE IF NOT EXISTS `city` (
  `cityId` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `countryId` INT(11) NOT NULL,
  `zipCodeId` INT(11) NOT NULL,
  PRIMARY KEY (`cityId`),
  CONSTRAINT `FK_CITY_COUNTRY`
    FOREIGN KEY (`countryId`)
    REFERENCES `country` (`countryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CITY_ZIP_CODE`
    FOREIGN KEY (`zipCodeId`)
    REFERENCES `zipcode` (`zipCodeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `cityId_UNIQUE` ON `city` (`cityId` ASC);

CREATE UNIQUE INDEX `name_UNIQUE` ON `city` (`name` ASC);

CREATE INDEX `countryId_IDX` ON `city` (`countryId` ASC);

CREATE INDEX `zipCodeId_IDX` ON `city` (`zipCodeId` ASC);


-- -----------------------------------------------------
-- Table `adress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `adress` ;

CREATE TABLE IF NOT EXISTS `adress` (
  `adressId` INT(11) NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(300) NOT NULL,
  `cityId` INT(11) NOT NULL,
  PRIMARY KEY (`adressId`),
  CONSTRAINT `FK_ADRESS_CITY`
    FOREIGN KEY (`cityId`)
    REFERENCES `city` (`cityId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `cityId_IDX` ON `adress` (`cityId` ASC);


-- -----------------------------------------------------
-- Table `client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `client` ;

CREATE TABLE IF NOT EXISTS `client` (
  `clientId` INT(11) NOT NULL AUTO_INCREMENT,
  `clientName` VARCHAR(100) NOT NULL,
  `clientSiret` VARCHAR(100) NOT NULL,
  `adressId` INT(11) NOT NULL,
  PRIMARY KEY (`clientId`),
  CONSTRAINT `FK_CLIENT_ADRESS`
    FOREIGN KEY (`adressId`)
    REFERENCES `adress` (`adressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `clientID_UNIQUE` ON `client` (`clientId` ASC);

CREATE UNIQUE INDEX `clientName_UNIQUE` ON `client` (`clientName` ASC);

CREATE UNIQUE INDEX `clientSiret_UNIQUE` ON `client` (`clientSiret` ASC);

CREATE INDEX `adressId_IDX` ON `client` (`adressId` ASC);


-- -----------------------------------------------------
-- Table `client_project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `client_project` ;

CREATE TABLE IF NOT EXISTS `client_project` (
  `clientProjectId` INT(11) NOT NULL AUTO_INCREMENT,
  `clientId` INT(11) NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NULL DEFAULT NULL,
  `salesRevenue` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`clientProjectId`),
  CONSTRAINT `FK_CLIENT_PROJECT_CLIENT`
    FOREIGN KEY (`clientId`)
    REFERENCES `client` (`clientId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Liste des projets par client';

CREATE UNIQUE INDEX `clientProjectId_UNIQUE` ON `client_project` (`clientProjectId` ASC);

CREATE INDEX `clientId_IDX` ON `client_project` (`clientId` ASC);


-- -----------------------------------------------------
-- Table `civility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `civility` ;

CREATE TABLE IF NOT EXISTS `civility` (
  `civilityId` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`civilityId`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `name_UNIQUE` ON `civility` (`name` ASC);


-- -----------------------------------------------------
-- Table `family_situation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `family_situation` ;

CREATE TABLE IF NOT EXISTS `family_situation` (
  `familySituationId` INT(11) NOT NULL AUTO_INCREMENT,
  `situation` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`familySituationId`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `situation_UNIQUE` ON `family_situation` (`situation` ASC);


-- -----------------------------------------------------
-- Table `collaborator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `collaborator` ;

CREATE TABLE IF NOT EXISTS `collaborator` (
  `collaboratorId` INT(11) NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `lastName` VARCHAR(45) NULL DEFAULT NULL,
  `civilityId` INT(11) NOT NULL,
  `email` VARCHAR(60) NULL DEFAULT NULL,
  `birthDate` DATE NOT NULL,
  `famillySituationId` INT(11) NOT NULL,
  `adressId` INT(11) NOT NULL,
  `arivalDate` DATE NOT NULL COMMENT 'Date d\'arrivée / d\'engagement',
  PRIMARY KEY (`collaboratorId`),
  CONSTRAINT `FK_COLLABORATOR_ADRESS`
    FOREIGN KEY (`adressId`)
    REFERENCES `adress` (`adressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COLLABORATOR_CIVILITY`
    FOREIGN KEY (`civilityId`)
    REFERENCES `civility` (`civilityId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COLLABORATOR_FAMILY_SITUATION`
    FOREIGN KEY (`famillySituationId`)
    REFERENCES `family_situation` (`familySituationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `collaboratorId_UNIQUE` ON `collaborator` (`collaboratorId` ASC);

CREATE INDEX `adressId_IDX` ON `collaborator` (`adressId` ASC);

CREATE INDEX `civilityId_IDX` ON `collaborator` (`civilityId` ASC);

CREATE INDEX `situationFamilyId_IDX` ON `collaborator` (`famillySituationId` ASC);


-- -----------------------------------------------------
-- Table `collaborator_project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `collaborator_project` ;

CREATE TABLE IF NOT EXISTS `collaborator_project` (
  `collaboratorProjectId` INT(11) NOT NULL AUTO_INCREMENT,
  `clientProjectId` INT(11) NOT NULL,
  `collaboratorId` INT(11) NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`collaboratorProjectId`),
  CONSTRAINT `FK_COLLABORATOR_PROJECT_CLIENT_PROJECT`
    FOREIGN KEY (`clientProjectId`)
    REFERENCES `client_project` (`clientProjectId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COLLABORATOR_PROJECT_COLLABORATOR`
    FOREIGN KEY (`collaboratorId`)
    REFERENCES `collaborator` (`collaboratorId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Liste des collaborateurs par projet';

CREATE INDEX `collaboratorId_IDX` ON `collaborator_project` (`collaboratorId` ASC);

CREATE INDEX `clientProjectId_IDX` ON `collaborator_project` (`clientProjectId` ASC);


-- -----------------------------------------------------
-- Table `activity_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_report` ;

CREATE TABLE IF NOT EXISTS `activity_report` (
  `activityReportId` INT(11) NOT NULL AUTO_INCREMENT,
  `collaboratorProjectId` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `isFullDay` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`activityReportId`),
  CONSTRAINT `FK_ACTIVITY_REPORT_COLLABORATOR_PROJECT`
    FOREIGN KEY (`collaboratorProjectId`)
    REFERENCES `collaborator_project` (`collaboratorProjectId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Table des CRA (Compte Rendu d\'Activité) par mission et par collaborateur';

CREATE UNIQUE INDEX `activityReportId_UNIQUE` ON `activity_report` (`activityReportId` ASC);

CREATE INDEX `collaboratorProjectId_IDX` ON `activity_report` (`collaboratorProjectId` ASC);


-- -----------------------------------------------------
-- Table `holiday_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `holiday_type` ;

CREATE TABLE IF NOT EXISTS `holiday_type` (
  `holidayTypeId` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `defaultQuantity` MEDIUMINT(2) NOT NULL,
  PRIMARY KEY (`holidayTypeId`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8
COMMENT = 'Types de congé avec leur solde';

CREATE UNIQUE INDEX `holidayTypeId_UNIQUE` ON `holiday_type` (`holidayTypeId` ASC);

CREATE UNIQUE INDEX `name_UNIQUE` ON `holiday_type` (`name` ASC);


-- -----------------------------------------------------
-- Table `holiday_balance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `holiday_balance` ;

CREATE TABLE IF NOT EXISTS `holiday_balance` (
  `holidayBalanceId` INT(11) NOT NULL AUTO_INCREMENT,
  `holidayTypeId` INT(11) NOT NULL,
  `collaboratorId` INT(11) NOT NULL,
  `balanceQuantity` SMALLINT(2) NOT NULL,
  PRIMARY KEY (`holidayBalanceId`),
  CONSTRAINT `FK_HOLIDAY_BALANCE_COLLABORATOR`
    FOREIGN KEY (`collaboratorId`)
    REFERENCES `collaborator` (`collaboratorId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HOLIDAY_BALANCE_HOLIDAY_TYPE`
    FOREIGN KEY (`holidayTypeId`)
    REFERENCES `holiday_type` (`holidayTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Solde des congés par collaborateur et par type de congé';

CREATE UNIQUE INDEX `holidayBalanceId_UNIQUE` ON `holiday_balance` (`holidayBalanceId` ASC);

CREATE INDEX `holidayTypeId_IDX` ON `holiday_balance` (`holidayTypeId` ASC);

CREATE INDEX `collaboratorId_IDX` ON `holiday_balance` (`collaboratorId` ASC);


-- -----------------------------------------------------
-- Table `holiday_period`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `holiday_period` ;

CREATE TABLE IF NOT EXISTS `holiday_period` (
  `holidayPeriodId` INT(11) NOT NULL AUTO_INCREMENT,
  `periodName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`holidayPeriodId`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COMMENT = 'Période du congé (Matin ou après midi)';

CREATE UNIQUE INDEX `holidayPeriodId_UNIQUE` ON `holiday_period` (`holidayPeriodId` ASC);


-- -----------------------------------------------------
-- Table `holiday_request_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `holiday_request_status` ;

CREATE TABLE IF NOT EXISTS `holiday_request_status` (
  `holidayRequestStatusId` INT(11) NOT NULL AUTO_INCREMENT,
  `statusName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`holidayRequestStatusId`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COMMENT = 'Liste des états du workflow de la validation des congés';

CREATE UNIQUE INDEX `holidayRequestStatusId_UNIQUE` ON `holiday_request_status` (`holidayRequestStatusId` ASC);

CREATE UNIQUE INDEX `statusName_UNIQUE` ON `holiday_request_status` (`statusName` ASC);


-- -----------------------------------------------------
-- Table `holiday_request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `holiday_request` ;

CREATE TABLE IF NOT EXISTS `holiday_request` (
  `holidayRequestId` INT(11) NOT NULL AUTO_INCREMENT,
  `collaboratorId` INT(11) NOT NULL,
  `holidayBalanceId` INT(11) NOT NULL,
  `startDate` DATETIME NOT NULL,
  `startPeriodId` INT(11) NOT NULL,
  `endDate` DATETIME NOT NULL,
  `endPeriodId` INT(11) NOT NULL,
  `holidayRequestStatusId` INT(11) NOT NULL,
  PRIMARY KEY (`holidayRequestId`),
  CONSTRAINT `FK_HOLIDAY_REQUEST_COLLABORATOR`
    FOREIGN KEY (`collaboratorId`)
    REFERENCES `collaborator` (`collaboratorId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HOLIDAY_REQUEST_HOLIDAY_BALANCE`
    FOREIGN KEY (`holidayBalanceId`)
    REFERENCES `holiday_balance` (`holidayBalanceId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HOLIDAY_REQUEST_HOLIDAY_PERIOD_END`
    FOREIGN KEY (`endPeriodId`)
    REFERENCES `holiday_period` (`holidayPeriodId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HOLIDAY_REQUEST_HOLIDAY_PERIOD_START`
    FOREIGN KEY (`startPeriodId`)
    REFERENCES `holiday_period` (`holidayPeriodId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HOLIDAY_REQUEST_HOLIDAY_REQUEST_STATUS`
    FOREIGN KEY (`holidayRequestStatusId`)
    REFERENCES `holiday_request_status` (`holidayRequestStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Table de toutes les demandes de congés';

CREATE UNIQUE INDEX `collaboratorHolidayId_UNIQUE` ON `holiday_request` (`holidayRequestId` ASC);

CREATE INDEX `holidayRequestStatusId_IDX` ON `holiday_request` (`holidayRequestStatusId` ASC);

CREATE INDEX `collaboratorId_IDX` ON `holiday_request` (`collaboratorId` ASC);

CREATE INDEX `holidayBalanceId_IDX` ON `holiday_request` (`holidayBalanceId` ASC);

CREATE INDEX `startPeriodId_IDX` ON `holiday_request` (`startPeriodId` ASC);

CREATE INDEX `endPeriodId_IDX` ON `holiday_request` (`endPeriodId` ASC);


-- -----------------------------------------------------
-- Table `public_holiday_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `public_holiday_category` ;

CREATE TABLE IF NOT EXISTS `public_holiday_category` (
  `publicHolidayCategoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`publicHolidayCategoryId`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8
COMMENT = 'Liste des catégories des jours fériés';

CREATE UNIQUE INDEX `publicHolidayCategoryId_UNIQUE` ON `public_holiday_category` (`publicHolidayCategoryId` ASC);


-- -----------------------------------------------------
-- Table `public_holiday`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `public_holiday` ;

CREATE TABLE IF NOT EXISTS `public_holiday` (
  `publicHolidayId` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `publicHolidayCategoryId` INT(11) NOT NULL,
  PRIMARY KEY (`publicHolidayId`),
  CONSTRAINT `FK_PUBLIC_HOLIDAY_PUBLIC_HOLIDAY_CATEGORY`
    FOREIGN KEY (`publicHolidayCategoryId`)
    REFERENCES `public_holiday_category` (`publicHolidayCategoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Liste des jours fériés de l\'année';

CREATE UNIQUE INDEX `publicHolidayId_UNIQUE` ON `public_holiday` (`publicHolidayId` ASC);

CREATE INDEX `publicHolidayCategoryId_IDX` ON `public_holiday` (`publicHolidayCategoryId` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Data for table `civility`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `civility` (`civilityId`, `name`) VALUES (DEFAULT, 'Monsieur');
INSERT INTO `civility` (`civilityId`, `name`) VALUES (DEFAULT, 'Madame');
INSERT INTO `civility` (`civilityId`, `name`) VALUES (DEFAULT, 'Mademoiselle');

COMMIT;


-- -----------------------------------------------------
-- Data for table `family_situation`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `family_situation` (`familySituationId`, `situation`) VALUES (DEFAULT, 'Célibataire');
INSERT INTO `family_situation` (`familySituationId`, `situation`) VALUES (DEFAULT, 'Marié(e)');
INSERT INTO `family_situation` (`familySituationId`, `situation`) VALUES (DEFAULT, 'Divorcé(e)');
INSERT INTO `family_situation` (`familySituationId`, `situation`) VALUES (DEFAULT, 'Pacé(e)');
INSERT INTO `family_situation` (`familySituationId`, `situation`) VALUES (DEFAULT, 'Veuf(ve)');

COMMIT;


-- -----------------------------------------------------
-- Data for table `holiday_type`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé payé', 22);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'RTT Salarié', 2);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'RTT Société', 1);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé maternité', 1);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé paternité', 11);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé sabbatique', 1);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé pour raisons familiales', 1);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé de reclassement', 1);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé d\'adoption', 1);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé de longue maladie', 1);
INSERT INTO `holiday_type` (`holidayTypeId`, `name`, `defaultQuantity`) VALUES (DEFAULT, 'Congé pour engagement associatif', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `holiday_period`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `holiday_period` (`holidayPeriodId`, `periodName`) VALUES (DEFAULT, 'Morning');
INSERT INTO `holiday_period` (`holidayPeriodId`, `periodName`) VALUES (DEFAULT, 'Afternoon');

COMMIT;


-- -----------------------------------------------------
-- Data for table `holiday_request_status`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `holiday_request_status` (`holidayRequestStatusId`, `statusName`) VALUES (DEFAULT, 'Created');
INSERT INTO `holiday_request_status` (`holidayRequestStatusId`, `statusName`) VALUES (DEFAULT, 'Submited');
INSERT INTO `holiday_request_status` (`holidayRequestStatusId`, `statusName`) VALUES (DEFAULT, 'Canceled');
INSERT INTO `holiday_request_status` (`holidayRequestStatusId`, `statusName`) VALUES (DEFAULT, 'Validated');
INSERT INTO `holiday_request_status` (`holidayRequestStatusId`, `statusName`) VALUES (DEFAULT, 'Refused');

COMMIT;


-- -----------------------------------------------------
-- Data for table `public_holiday_category`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Jour de l\'an');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Lundi de Pâques');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Fête du Travail');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, '8 Mai 1945');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Jeudi de l\'Ascension');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Lundi de Pentecôte');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Fête Nationale');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Assomption');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'La Toussaint');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Armistice');
INSERT INTO `public_holiday_category` (`publicHolidayCategoryId`, `category`) VALUES (DEFAULT, 'Noël');

COMMIT;

