SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `MEG` DEFAULT CHARACTER SET Latin1 COLLATE = latin1_swedish_ci;
USE `MEG` ;

-- -----------------------------------------------------
-- Table `MEG`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEG`.`Estado` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `sigla` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEG`.`Secao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEG`.`Secao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEG`.`SalarioMinimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEG`.`SalarioMinimo`(
	`ano` INT NOT NULL,
	`valor` FLOAT NOT NULL,
	PRIMARY KEY (`ano`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `MEG`.`Descricao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEG`.`Descricao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `MEG`.`Noticias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEG`.`Noticias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `noticia` VARCHAR(200) NOT NULL,
  `imagem` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `MEG`.`Quadro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEG`.`Quadro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor` FLOAT NOT NULL,
  `ano` SMALLINT(6) NULL,
  `estado_id` INT NOT NULL,
  `secao_id` INT NOT NULL,
  `descricao_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Quadro_Estado_idx` (`estado_id` ASC),
  INDEX `fk_Quadro_Secao_idx` (`secao_id` ASC),
  INDEX `fk_Quadro_Descricao_idx` (`descricao_id` ASC),
  CONSTRAINT `fk_Quadro_Estado`
    FOREIGN KEY (`estado_id`)
    REFERENCES `MEG`.`Estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Quadro_Secao`
    FOREIGN KEY (`secao_id`)
    REFERENCES `MEG`.`Secao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Quadro_Descricao`
    FOREIGN KEY (`descricao_id`)
    REFERENCES `MEG`.`Descricao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
