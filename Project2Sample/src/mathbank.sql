
DROP DATABASE IF EXISTS mathbank;

CREATE DATABASE mathbank;

USE mathbank;


CREATE TABLE Category (
    ID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR (20) NOT NULL UNIQUE,
    Description VARCHAR (420) NOT NULL,
	CreatedTime TIMESTAMP NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE Problem (
    ID INT NOT NULL AUTO_INCREMENT,
    CategoryID INT NOT NULL,
    Title VARCHAR (50) NOT NULL UNIQUE,
    Content TEXT NOT NULL,
	CreatedTime TIMESTAMP NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (CategoryID) REFERENCES Category(ID)
);

INSERT INTO Category (ID, Name, Description, CreatedTime) VALUES
(NULL, "Test1", "Test1", "2019-11-11 11:11:11"),
(NULL, "Test2", "Test2", "2019-11-11 11:11:12"),
(NULL, "Test3", "Test3", "2019-11-11 11:11:13");

INSERT INTO Problem (ID, CategoryID, Title, Content, CreatedTime) VALUES
(NULL, 1, "Test1", "Test1", "2019-11-11 11:11:11"),
(NULL, 2, "Test2", "Test2", "2019-11-11 11:11:12"),
(NULL, 3, "Test3", "Test3", "2019-11-11 11:11:13");