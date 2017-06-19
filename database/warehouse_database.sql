CREATE SCHEMA IF NOT EXISTS `warehouse` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `warehouse`;

CREATE TABLE IF NOT EXISTS `warehouse`.`customer` (
	`id_customer` INT NOT NULL AUTO_INCREMENT,
	`customer_name` VARCHAR(50) NOT NULL,
	`contact_person` VARCHAR(50) NOT NULL,
	`address` VARCHAR(50) NULL,
	`city` VARCHAR(50) NOT NULL,
	`post_code` INT NOT NULL,
	`country` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id_customer`)
);

CREATE TABLE IF NOT EXISTS `warehouse`.`employee` (
	`id_employee` INT NOT NULL AUTO_INCREMENT,
	`last_name` VARCHAR(50) NOT NULL,
	`first_name` VARCHAR(50) NOT NULL,
	`birth_date` DATE NULL,
	PRIMARY KEY (`id_employee`)
);

CREATE TABLE IF NOT EXISTS `warehouse`.`supplier` (
	`id_supplier` INT NOT NULL AUTO_INCREMENT,
	`supplier_name` VARCHAR(50) NOT NULL,
	`contact_person` VARCHAR(50) NOT NULL,
	`address` VARCHAR(50) NULL,
	`city` VARCHAR(50) NOT NULL,
	`post_code` INT NOT NULL,
	`country` VARCHAR(50) NOT NULL,
	`phone` VARCHAR(12),
	PRIMARY KEY (`id_supplier`)
);

CREATE TABLE IF NOT EXISTS `warehouse`.`shipper` (
	`id_shipper` INT NOT NULL AUTO_INCREMENT,
	`shipper_name` VARCHAR(50) NOT NULL,
	`phone` VARCHAR(12),
	PRIMARY KEY (`id_shipper`)
);

CREATE TABLE IF NOT EXISTS `warehouse`.`product` (
	`id_product` INT NOT NULL AUTO_INCREMENT,
	`product_name` VARCHAR(50) NOT NULL,
	`fk_supplier` INT NULL,
	`product_category` VARCHAR(50) NOT NULL,
	`price_per_unit` DOUBLE,
	PRIMARY KEY (`id_product`),
	CONSTRAINT `fk_product_supplier` FOREIGN KEY (`fk_supplier`) REFERENCES `warehouse`.`supplier` (`id_supplier`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `warehouse`.`orders` (
	`id_order` INT NOT NULL AUTO_INCREMENT,
	`order_date` DATE NULL,
	`fk_customer` INT NULL,
	`fk_employee` INT NULL,
	`fk_shipper` INT NULL,
	PRIMARY KEY (`id_order`),
	CONSTRAINT `fk_order_customer` FOREIGN KEY (`fk_customer`) REFERENCES `warehouse`.`customer` (`id_customer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `fk_order_employee` FOREIGN KEY (`fk_employee`) REFERENCES `warehouse`.`employee` (`id_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `fk_order_shipper` FOREIGN KEY (`fk_shipper`) REFERENCES `warehouse`.`shipper` (`id_shipper`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `warehouse`.`order_details` (
	`id_order_details` INT NOT NULL AUTO_INCREMENT,
	`fk_order` INT NULL,
	`fk_product` INT NULL,
	`quantity` INT NULL,
	PRIMARY KEY (`id_order_details`),
	CONSTRAINT `fk_order_details_order` FOREIGN KEY (`fk_order`) REFERENCES `warehouse`.`orders` (`id_order`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `fk_order_details_product` FOREIGN KEY (`fk_product`) REFERENCES `warehouse`.`product` (`id_product`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

