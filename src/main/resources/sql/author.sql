CREATE TABLE `vhas`.`author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `web_id` INT NULL,
  `uid` VARCHAR(255) NULL,
  `name` VARCHAR(255) NULL,
  `gender` VARCHAR(255) NULL,
  `age` INT NULL,
  `url` VARCHAR(255) NULL,
  `level` VARCHAR(255) NULL,
  `fans` INT NULL,
  `attention` INT NULL,
  `works` INT NULL,
  `birthday` DATETIME NULL,
  `regtime` DATETIME NULL,
  `lasttime` DATETIME NULL,
  `uptime` DATETIME NULL,
  `slogan` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `img` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
  DEFAULT CHARSET = utf8mb4;

  CREATE TABLE `vhas`.`authorupdate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `web_id` INT NULL,
  `uid` VARCHAR(255) NULL,
  `name` VARCHAR(255) NULL,
  `gender` VARCHAR(255) NULL,
  `age` INT NULL,
  `url` VARCHAR(255) NULL,
  `level` VARCHAR(255) NULL,
  `fans` INT NULL,
  `attention` INT NULL,
  `works` INT NULL,
  `birthday` DATETIME NULL,
  `regtime` DATETIME NULL,
  `lasttime` DATETIME NULL,
  `uptime` DATETIME NULL,
  `slogan` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `img` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
  DEFAULT CHARSET = utf8mb4;
