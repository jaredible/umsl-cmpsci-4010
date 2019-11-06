
DROP DATABASE IF EXISTS mindbank;

CREATE DATABASE mindbank;

USE mindbank;

CREATE TABLE IF NOT EXISTS Role (
	ID INT NOT NULL,
	Name VARCHAR (10) NOT NULL,
	Description VARCHAR (100),
	PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS User (
	ID INT NOT NULL AUTO_INCREMENT,
	RoleID INT NOT NULL,
	Email VARCHAR (50) NOT NULL UNIQUE,
	UserName VARCHAR (20) UNIQUE,
	Name VARCHAR (100),
	Bio VARCHAR (420),
	PhoneNumber VARCHAR (20),
	PasswordHash TEXT NOT NULL,
	EmailVerified BOOLEAN,
	PhoneNumberVerified BOOLEAN,
	RegistrationTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	LoginTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (RoleID) REFERENCES Role(ID)
);

CREATE TABLE IF NOT EXISTS Category (
	ID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR (30) NOT NULL,
	Description VARCHAR (100),
	PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS Problem (
	ID INT NOT NULL AUTO_INCREMENT,
	CategoryID INT NOT NULL,
	Title VARCHAR (20) NOT NULL,
	Content TEXT NOT NULL,
	Edited BOOLEAN NOT NULL,
	CreatedByUserID INT NOT NULL,
	CreatedTimestamp TIMESTAMP NOT NULL,
	PRIMARY KEY (ID, CategoryID),
	FOREIGN KEY (CategoryID) REFERENCES Category(ID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(ID)
);

CREATE TABLE IF NOT EXISTS Auth (
	ID INT NOT NULL AUTO_INCREMENT,
	UserID INT NOT NULL,
	Selector VARCHAR (12) NOT NULL,
	Validator VARCHAR (64) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (UserID) REFERENCES User(ID)
);

INSERT INTO Role (ID, Name, Description) VALUES (0, "DEFAULT", "");
INSERT INTO Role (ID, Name, Description) VALUES (1, "ADMIN", "");

INSERT INTO User (ID, RoleID, Email, UserName, Name, Bio, PhoneNumber, PasswordHash, EmailVerified, PhoneNumberVerified, RegistrationTimestamp, LoginTimestamp) 
VALUES (NULL, 0, "jared@jaredible.net", "Jaredible", "Jared Diehl", "This is me!", "3146291836", "443697d7c5bd42e70e34c9254d9951e17f09e523f8ff969cd347f8af156da13e", FALSE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO User (ID, RoleID, Email, UserName, Name, Bio, PhoneNumber, PasswordHash, EmailVerified, PhoneNumberVerified, RegistrationTimestamp, LoginTimestamp) 
VALUES (NULL, 1, "admin@admin.com", "admin", NULL, NULL, NULL, "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Category (ID, Name, Description) VALUES (NULL, "AI", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Machine Learning", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Web Development", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Essay", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Proposal", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Technical Writing", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Algebra", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Geometry", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Trigonometry", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Classical Mechanics", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Relativistic Mechanics", "");
INSERT INTO Category (ID, Name, Description) VALUES (NULL, "Quantum Mechanics", "");

INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 6, "Proposal", "This is a test.", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 7, "Simple Equation", "$$ 2+2=4 $$", FALSE, 1, CURRENT_TIMESTAMP);
