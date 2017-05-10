ALTER TABLE `exercise`.`university`
DROP FOREIGN KEY `facultyId`;
ALTER TABLE `exercise`.`university`
DROP COLUMN `facultyId`,
DROP INDEX `facultyId_idx` ;
