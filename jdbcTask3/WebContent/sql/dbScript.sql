DROP SCHEMA IF  EXISTS `captcha` ;
CREATE SCHEMA   `captcha` ;
USE `captcha` ;

CREATE TABLE IF NOT EXISTS `captcha`.`questions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `captcha`.`answers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `answer` VARCHAR(45) NOT NULL,
  `question_id` INT NOT NULL,
  `is_correct` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answers_questions_idx` (`question_id` ASC),
  CONSTRAINT `fk_answers_questions`
    FOREIGN KEY (`question_id`)
    REFERENCES `captcha`.`questions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `captcha`.`questions` ( `id`,`question`) VALUES (1,'cколько будет 2+2');
INSERT INTO `captcha`.`questions` (`id`,`question`) VALUES (2,'Выберите название животного');
INSERT INTO `captcha`.`questions` (`id`,`question`) VALUES (3,'Выберите марку автомобиля');

INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('1', '3', '1', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('2', '4', '1', '1');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('3', '6', '1', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('4', '2', '1', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('5', 'кот', '2', '1');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('6', 'мяч', '2', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('7', 'телевизор', '2', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('8', 'холодильник', '2', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('9', 'lg', '3', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('10', 'samsung', '3', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('11', 'луч', '3', '0');
INSERT INTO `captcha`.`answers` (`id`, `answer`, `question_id`, `is_correct`) VALUES ('12', 'geely', '3', '1');
