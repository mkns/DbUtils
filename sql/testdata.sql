DROP TABLE IF EXISTS Person;
CREATE TABLE `Person` (
  `name` varchar(255) default NULL,
  `age` int(11) default NULL
);

INSERT INTO `Person` VALUES ('John Doe',25);
INSERT INTO `Person` VALUES ('Bilbo Baggins',26);
INSERT INTO `Person` VALUES ('John Doe',29);

DROP TABLE IF EXISTS vehicle;
CREATE TABLE `vehicle` (
  `id` varchar(64) default NULL,
  `make` varchar(32) default NULL,
  `model` varchar(64) default NULL,
  `colour` varchar(16) default NULL
);

INSERT INTO `vehicle` VALUES ('','Renault','Grand Scenic','');
