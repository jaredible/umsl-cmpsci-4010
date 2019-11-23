
DROP DATABASE IF EXISTS mindbank;

CREATE DATABASE mindbank;

USE mindbank;

CREATE TABLE User (
	ID INT NOT NULL AUTO_INCREMENT,
	Email VARCHAR (50) NOT NULL UNIQUE,
	UserName VARCHAR (20) NOT NULL UNIQUE,
	Name VARCHAR (20),
	Bio VARCHAR (420),
	ProfileImage BLOB,
	RegisteredTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	LastLoginTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	EmailVerified BOOLEAN NOT NULL DEFAULT FALSE,
	PasswordSalt TEXT NOT NULL,
	PasswordHash TEXT NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE AuthToken (
	ID INT NOT NULL AUTO_INCREMENT,
	UserID INT NOT NULL,
	Selector VARCHAR (12) NOT NULL,
	Validator VARCHAR (64) NOT NULL,
	CreatedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (ID),
	FOREIGN KEY (UserID) REFERENCES User(ID)
);

CREATE TABLE Comment (
	ID INT NOT NULL AUTO_INCREMENT,
	Content VARCHAR (1000) NOT NULL,
	CreatedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CreatedByUserID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(ID)
);

CREATE TABLE Tag (
	ID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR (20) NOT NULL UNIQUE,
	CreatedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CreatedByUserID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(ID)
);

CREATE TABLE Category (
	ID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR (20) NOT NULL UNIQUE,
	CreatedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CreatedByUserID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(ID)
);

CREATE TABLE Problem (
	ID INT NOT NULL AUTO_INCREMENT,
	Title VARCHAR (50) NOT NULL,
	Content TEXT NOT NULL,
	Edited BOOLEAN DEFAULT FALSE NOT NULL,
	CreatedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	LastEditedTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CreatedByUserID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(ID)
);

CREATE TABLE ProblemCategory (
	ID INT NOT NULL AUTO_INCREMENT,
	ProblemID INT NOT NULL,
	CategoryID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (ProblemID) REFERENCES Problem(ID),
	FOREIGN KEY (CategoryID) REFERENCES Category(ID)
);

CREATE TABLE ProblemTag (
	ID INT NOT NULL AUTO_INCREMENT,
	ProblemID INT NOT NULL,
	TagID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (ProblemID) REFERENCES Problem(ID),
	FOREIGN KEY (TagID) REFERENCES Tag(ID)
);

CREATE TABLE ProblemComment (
	ID INT NOT NULL AUTO_INCREMENT,
	ProblemID INT NOT NULL,
	CommentID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (ProblemID) REFERENCES Problem(ID),
	FOREIGN KEY (CommentID) REFERENCES Comment(ID)
);
