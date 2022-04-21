--creating database
CREATE SCHEMA `electrogrid` ;

--power_consumption table
CREATE TABLE `electrogrid`.`power_consumption` (
  `Electricity_AccountNo` VARCHAR(12) NOT NULL,
  `CurrentReading` INT NOT NULL,
  `NoOfUnits` INT NOT NULL,
  `date` DATE NOT NULL,
  `monthYear` VARCHAR(45) NOT NULL,
  `type` VARCHAR(15) NOT NULL,
  `ReaderID` VARCHAR(45) NOT NULL,
  `userID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Electricity_AccountNo`));



ALTER TABLE `electrogrid`.`power_consumption` 
ADD COLUMN `recordID` VARCHAR(30) NOT NULL FIRST,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`recordID`);
;





  --power_capacity table

  CREATE TABLE `electrogrid`.`power_capacity` (
  `capacityID` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `noOfUnits` INT NOT NULL,
  `supplierID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`capacityID`));
