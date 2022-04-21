--User Table

CREATE TABLE `electrogrid`.`user` (
  `userID` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `accountNumber` INT NOT NULL,
  `address` VARCHAR(100) NULL,
  `NIC` VARCHAR(20) NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`userID`));

ALTER TABLE user
ADD COLUMN reset_code VARCHAR(45) NOT NULL;


--Breakdown Table

CREATE TABLE `electrogrid`.`breakdown` (
  `breakdownID` INT NOT NULL AUTO_INCREMENT,
  `region` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `user` INT NOT NULL,
  PRIMARY KEY (`breakdownID`),
  INDEX `user_idx` (`user` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`user`)
    REFERENCES `electrogrid`.`user` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE breakdown
ADD COLUMN date VARCHAR(45);

ALTER TABLE breakdown
ADD COLUMN status INT;