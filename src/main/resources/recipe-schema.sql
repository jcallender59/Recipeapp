DROP TABLE IF EXISTS recipes CASCADE;
CREATE TABLE `recipes` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL,
  `author` VARCHAR(100) NULL,
  `ingredients` VARCHAR(100) NULL,
PRIMARY KEY (`id`));
