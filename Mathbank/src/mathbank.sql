
DROP DATABASE IF EXISTS mathbank;

CREATE DATABASE mathbank;

USE mathbank;

CREATE TABLE Tracking (
	ID INT NOT NULL AUTO_INCREMENT,
	TrackingType INT NOT NULL,
	IP VARCHAR (15) NOT NULL,
	UserAgent TEXT NOT NULL,
	CreatedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PreviousTrackingID INT DEFAULT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE Category (
    ID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR (20) NOT NULL UNIQUE,
    Description VARCHAR (420) NOT NULL,
	CreatedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Edited BOOLEAN DEFAULT FALSE,
	TrackingID INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (TrackingID) REFERENCES Tracking(ID)
);

CREATE TABLE Problem (
    ID INT NOT NULL AUTO_INCREMENT,
    CategoryID INT NOT NULL,
    Title VARCHAR (50) NOT NULL UNIQUE,
    Content TEXT NOT NULL,
	CreatedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Edited BOOLEAN DEFAULT FALSE,
	PasswordHash TEXT,
	ViewCount INT DEFAULT 0,
	TrackingID INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (CategoryID) REFERENCES Category(ID),
    FOREIGN KEY (TrackingID) REFERENCES Tracking(ID)
);

INSERT INTO Tracking (ID, TrackingType, IP, UserAgent, CreatedTime) VALUES
(NULL, 0, "test", "test", "2019-11-11 11:11:11");

INSERT INTO Category (ID, Name, Description, CreatedTime, Edited, TrackingID) VALUES
(NULL, "Test1", "Test1", "2019-11-11 11:11:11", FALSE, 1),
(NULL, "Test2", "Test2", "2019-11-11 11:11:12", TRUE, 1),
(NULL, "Test3", "Test3", "2019-11-11 11:11:13", FALSE, 1);

INSERT INTO Problem (ID, CategoryID, Title, Content, CreatedTime, Edited, TrackingID) VALUES
(NULL, 1, "Test1", "Test1", "2019-11-11 11:11:11", TRUE, 1),
(NULL, 2, "Test2", "Test2", "2019-11-11 11:11:12", FALSE, 1),
(NULL, 3, "Test3", "Test3", "2019-11-11 11:11:13", TRUE, 1);
