
DROP DATABASE IF EXISTS mindbank;

CREATE DATABASE mindbank;

USE mindbank;

CREATE TABLE IF NOT EXISTS Role (
	ID INT NOT NULL,
	Name VARCHAR (10) NOT NULL,
	Description VARCHAR (100),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS User (
	ID INT NOT NULL AUTO_INCREMENT,
	RoleID INT NOT NULL,
	Email VARCHAR (50) NOT NULL,
	UserName VARCHAR (20),
	FirstName VARCHAR (20) NOT NULL,
	LastName VARCHAR (20) NOT NULL,
	PhoneNumber VARCHAR (20),
	PasswordHash TEXT NOT NULL,
	EmailVerified BOOLEAN,
	PhoneNumberVerified BOOLEAN,
	RegistrationTimestamp TIMESTAMP NOT NULL,
	LoginTimestamp TIMESTAMP NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (RoleID) REFERENCES Role(ID)
);

CREATE TABLE IF NOT EXISTS Subject (
	ID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(20) NOT NULL,
	Description VARCHAR (100),
	PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS Category (
	ID INT NOT NULL AUTO_INCREMENT,
	SubjectID INT NOT NULL,
	Name VARCHAR (30) NOT NULL,
	Description VARCHAR (100),
	PRIMARY KEY (ID, SubjectID),
	FOREIGN KEY (SubjectID) REFERENCES Subject(ID)
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

INSERT INTO Role (ID, Name, Description) VALUES (0, "DEFAULT", "");
INSERT INTO Role (ID, Name, Description) VALUES (1, "USER", "");
INSERT INTO Role (ID, Name, Description) VALUES (2, "MOD", "");
INSERT INTO Role (ID, Name, Description) VALUES (3, "ADMIN", "");

INSERT INTO User (ID, RoleID, Email, UserName, FirstName, LastName, PhoneNumber, PasswordHash, EmailVerified, PhoneNumberVerified, RegistrationTimestamp, LoginTimestamp) 
VALUES (0, 3, "jared@jaredible.net", "Jaredible", "Jared", "Diehl", "3146291836", "Testing2000!", FALSE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Subject (ID, Name, Description) VALUES (NULL, "Computer Science", "");
INSERT INTO Subject (ID, Name, Description) VALUES (NULL, "English", "");
INSERT INTO Subject (ID, Name, Description) VALUES (NULL, "Math", "");
INSERT INTO Subject (ID, Name, Description) VALUES (NULL, "Physics", "");

INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 1, "AI", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 1, "Machine Learning", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 1, "Web Development", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 2, "Essay", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 2, "Proposal", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 2, "Technical Writing", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 3, "Algebra", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 3, "Geometry", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 3, "Trigonometry", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 4, "Classical Mechanics", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 4, "Relativistic Mechanics", "");
INSERT INTO Category (ID, SubjectID, Name, Description) VALUES (NULL, 4, "Quantum Mechanics", "");

INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 6, "Proposal", "This is a test.", FALSE, 1, CURRENT_TIMESTAMP);
