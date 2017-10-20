-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema LegoHouse
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `LegoHouse` ;

-- -----------------------------------------------------
-- Schema LegoHouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LegoHouse` DEFAULT CHARACTER SET utf8 ;
USE `LegoHouse` ;

-- -----------------------------------------------------
-- Table `LegoHouse`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LegoHouse`.`User` (
  `id` INT NOT NULL auto_increment,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('customer', 'employee') NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LegoHouse`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LegoHouse`.`Order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `length` INT NOT NULL,
  `width` INT NOT NULL,
  `layers` INT NOT NULL,
  `orderStatus` ENUM('PROCESSING', 'SENT') NOT NULL DEFAULT 'PROCESSING',
  `user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `LegoHouse`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- --------------------------------------------------------
-- ADD DUMMY INFO
-- --------------------------------------------------------
INSERT INTO User(email, password, role)
VALUES ('admin', 'Password1', 'employee');

INSERT INTO User(email, password)
VALUES ('customer','Password1');

-- -------------------------------------------------------- 
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
