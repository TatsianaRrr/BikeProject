-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bikedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bikedb
-- -----------------------------------------------------
DROP schema if exists `bikedb`;
CREATE SCHEMA IF NOT EXISTS `bikedb` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `bikedb` ;

-- -----------------------------------------------------
-- Table `bikedb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bikedb`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `userRole` ENUM('user', 'admin') NOT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8 COLLATE utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `bikedb`.`bike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bikedb`.`bike` (
  `idbike` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `year` INT NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `price` VARCHAR(100) NOT NULL,
  `image` VARCHAR(100) NOT NULL,
  `ordered` VARCHAR(45) NOT NULL DEFAULT '2',
  `deleted` VARCHAR(45) NOT NULL DEFAULT '2',
  PRIMARY KEY (`idbike`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8 COLLATE utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `bikedb`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bikedb`.`order` (
  `idorder` INT(10) NOT NULL AUTO_INCREMENT,
  `user_iduser` INT NOT NULL,
  `bike_idbike` INT NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idorder`),
  INDEX `fk_user_has_bike_bike1_idx` (`bike_idbike` ASC),
  INDEX `fk_user_has_bike_user_idx` (`user_iduser` ASC),
  INDEX `idx_bike_idbike_user_iduser` (`bike_idbike` ASC, `user_iduser` ASC),
  CONSTRAINT `fk_user_has_bike_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `bikedb`.`user` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_has_bike_bike1`
    FOREIGN KEY (`bike_idbike`)
    REFERENCES `bikedb`.`bike` (`idbike`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8 COLLATE utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `bikedb`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `bikedb`;
INSERT INTO `bikedb`.`user` (`iduser`, `login`, `password`, `name`, `email`, `userRole`) VALUES (1, '1', '1', '1', '1', 'user');
INSERT INTO `bikedb`.`user` (`iduser`, `login`, `password`, `name`, `email`, `userRole`) VALUES (2, '2', '2', '2', '2', 'admin');
INSERT INTO `bikedb`.`user` (`iduser`, `login`, `password`, `name`, `email`, `userRole`) VALUES (3, '3', '3', '3', '3', 'admin');
INSERT INTO `bikedb`.`user` (`iduser`, `login`, `password`, `name`, `email`, `userRole`) VALUES (4, '4', '4', '4', '4', 'admin');
INSERT INTO `bikedb`.`user` (`iduser`, `login`, `password`, `name`, `email`, `userRole`) VALUES (5, '5', '5', '5', '5', 'admin');
INSERT INTO `bikedb`.`user` (`iduser`, `login`, `password`, `name`, `email`, `userRole`) VALUES (6, '6', '6', '6', '6', 'admin');
INSERT INTO `bikedb`.`user` (`iduser`, `login`, `password`, `name`, `email`, `userRole`) VALUES (7, '7', '7', '7', '7', 'admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bikedb`.`bike`
-- -----------------------------------------------------
START TRANSACTION;
USE `bikedb`;
INSERT INTO `bikedb`.`bike` (`idbike`, `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted`)
VALUES (1, 'Aist', '2020', 'Aist Magic', 5,  'image1.jpg', false, false);
INSERT INTO `bikedb`.`bike` (`idbike`, `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted`)
VALUES (2, 'Favorit', '2021', 'Favorit Neo 12', 3,  'image2.jpg', false, false);
INSERT INTO `bikedb`.`bike` (`idbike`, `name`, `year`, `description`, `price`, `image`, `ordered`, `deleted`)
VALUES (3, 'Stels', '2019', 'Stels Navigator 310 Lady 28 V020', 4,  'image3jpg', false, false);
COMMIT;


-- -----------------------------------------------------
-- Data for table `bikedb`.`order`
-- -----------------------------------------------------
START TRANSACTION;
USE `bikedb`;
INSERT INTO `bikedb`.`order` (`idorder`, `user_iduser`, `bike_idbike`, `description`) VALUES (1, 1, 1, '1');
INSERT INTO `bikedb`.`order` (`idorder`, `user_iduser`, `bike_idbike`, `description`) VALUES (2, 1, 2, '1');
INSERT INTO `bikedb`.`order` (`idorder`, `user_iduser`, `bike_idbike`, `description`) VALUES (3, 2, 1, '1');

COMMIT;

