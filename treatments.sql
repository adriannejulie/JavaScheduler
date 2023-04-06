/* W23 Project Example Database
   2023 Barcomb
 
 Each time this file is executed, it will reset the database to the original state defined below.
 
 */
 
 

DROP DATABASE IF EXISTS EWR;
CREATE DATABASE EWR; 
USE EWR;

DROP USER IF EXISTS 'oop'@'localhost';
CREATE USER 'oop'@'localhost' identified with mysql_native_password BY 'password';
GRANT ALL ON *.* TO 'oop'@'localhost';

DROP TABLE IF EXISTS ANIMALS;
CREATE TABLE ANIMALS (
	AnimalID		int not null AUTO_INCREMENT,
        AnimalNickname		varchar(25),
	AnimalSpecies		varchar(25),
	primary key (AnimalID)
);

INSERT INTO ANIMALS (AnimalID, AnimalNickname, AnimalSpecies) VALUES
(1, 'Loner', 'coyote'),
(2, 'Biter', 'coyote'),
(3, 'Bitter', 'coyote'),
(4, 'Pencil', 'coyote'),
(5, 'Eraser', 'coyote'),
(6, 'Annie, Oliver and Mowgli', 'fox'),
(7, 'Slinky', 'fox'),
(8, 'Spike', 'porcupine'),
(9, 'Javelin', 'porcupine'),
(10, 'Gatekeeper', 'porcupine'),
(11, 'Sunshine', 'porcupine'),
(12, 'Shadow', 'porcupine'),
(13, 'Boots', 'coyote'),
(14, 'Spin', 'coyote'),
(15, 'Spot', 'coyote');

DROP TABLE IF EXISTS TASKS;
CREATE TABLE TASKS (
	TaskID			int not null AUTO_INCREMENT,
	Description		varchar(50),
        Duration                int,
        MaxWindow               int,
	primary key (TaskID)
);

INSERT INTO TASKS (TaskID, Description, Duration, MaxWindow) VALUES
(1, 'Kit feeding', 30, 2),
(2, 'Rebandage leg wound', 20, 1),
(3, 'Apply burn ointment back', 10, 3),
(4, 'Administer antibiotics', 5, 1),
(5, 'Flush neck wound', 25, 1),
(6, 'Give fluid injection', 10, 1),
(7, 'Give vitamin injection', 5, 5),
(8, 'Mange treatment', 15, 4),
(9, 'Eyedrops', 25, 1),
(10, 'Inspect broken leg', 5, 2);

DROP TABLE IF EXISTS TREATMENTS;
CREATE TABLE TREATMENTS (
      	TreatmentID	int not null AUTO_INCREMENT,
	AnimalID	int not null,
	TaskID		int not null,
	StartHour	int not null,
	primary key (TreatmentID)
);

INSERT INTO TREATMENTS (AnimalID, TaskID, StartHour) VALUES
(6, 1, 0),
(6, 1, 2),
(6, 1, 4),
(6, 1, 6),
(6, 1, 8),
(6, 1, 10),
(6, 1, 12),
(6, 1, 14),
(6, 1, 16),
(6, 1, 18),
(6, 1, 20),
(6, 1, 22),
(1, 9, 22),
(2, 10, 13),
(2, 9, 13),
(3, 7, 13),
(4, 7, 13),
(5, 7, 13),
(7, 2, 19),
(8, 5, 6),
(8, 4, 6),
(8, 4, 18),
(9, 9, 22),
(10, 7, 23),
(11, 8, 23),
(12, 3, 15),
(13, 6, 22),
(14, 10, 13),
(14, 8, 13),
(15, 8, 13);
