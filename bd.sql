-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema AppBanda
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AppBanda
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AppBanda` DEFAULT CHARACTER SET utf8 ;
USE `AppBanda` ;

-- -----------------------------------------------------
-- Table `AppBanda`.`Banda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AppBanda`.`Banda` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` MEDIUMTEXT NOT NULL,
  `nomeCidade` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AppBanda`.`Cd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AppBanda`.`Cd` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(2000) NOT NULL,
  `duracao` VARCHAR(11) NOT NULL,
  `link` VARCHAR(2000) NOT NULL,
  `ano` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 59
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AppBanda`.`BandaCd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AppBanda`.`BandaCd` (
  `Banda_id` INT(11) NOT NULL,
  `Cd_id` INT(11) NOT NULL,
  PRIMARY KEY (`Banda_id`, `Cd_id`),
  INDEX `fk_Banda_has_Cd_Cd1_idx` (`Cd_id` ASC),
  INDEX `fk_Banda_has_Cd_Banda1_idx` (`Banda_id` ASC),
  CONSTRAINT `fk_Banda_has_Cd_Banda1`
    FOREIGN KEY (`Banda_id`)
    REFERENCES `AppBanda`.`Banda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Banda_has_Cd_Cd1`
    FOREIGN KEY (`Cd_id`)
    REFERENCES `AppBanda`.`Cd` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AppBanda`.`Dvd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AppBanda`.`Dvd` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` MEDIUMTEXT NOT NULL,
  `duracao` DOUBLE NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `ano` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AppBanda`.`BandaDvd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AppBanda`.`BandaDvd` (
  `Banda_id` INT(11) NOT NULL,
  `Dvd_id` INT(11) NOT NULL,
  PRIMARY KEY (`Banda_id`, `Dvd_id`),
  INDEX `fk_Banda_has_Dvd_Dvd1_idx` (`Dvd_id` ASC),
  INDEX `fk_Banda_has_Dvd_Banda1_idx` (`Banda_id` ASC),
  CONSTRAINT `fk_Banda_has_Dvd_Banda1`
    FOREIGN KEY (`Banda_id`)
    REFERENCES `AppBanda`.`Banda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Banda_has_Dvd_Dvd1`
    FOREIGN KEY (`Dvd_id`)
    REFERENCES `AppBanda`.`Dvd` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AppBanda`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AppBanda`.`Usuario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `senha` VARCHAR(200) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AppBanda`.`UsuarioCd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AppBanda`.`UsuarioCd` (
  `Usuario_id` INT(11) NOT NULL,
  `Cd_id` INT(11) NOT NULL,
  `nota` INT(11) NOT NULL,
  PRIMARY KEY (`Usuario_id`, `Cd_id`),
  INDEX `fk_Usuario_has_Cd_Cd1_idx` (`Cd_id` ASC),
  INDEX `fk_Usuario_has_Cd_Usuario1_idx` (`Usuario_id` ASC),
  CONSTRAINT `fk_Usuario_has_Cd_Cd1`
    FOREIGN KEY (`Cd_id`)
    REFERENCES `AppBanda`.`Cd` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Cd_Usuario1`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `AppBanda`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `AppBanda`.`UsuarioDvd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AppBanda`.`UsuarioDvd` (
  `Usuario_id` INT(11) NOT NULL,
  `Dvd_id` INT(11) NOT NULL,
  `nota` INT(11) NOT NULL,
  PRIMARY KEY (`Usuario_id`, `Dvd_id`),
  INDEX `fk_Usuario_has_Dvd_Dvd1_idx` (`Dvd_id` ASC),
  INDEX `fk_Usuario_has_Dvd_Usuario_idx` (`Usuario_id` ASC),
  CONSTRAINT `fk_Usuario_has_Dvd_Dvd1`
    FOREIGN KEY (`Dvd_id`)
    REFERENCES `AppBanda`.`Dvd` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Dvd_Usuario`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `AppBanda`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
