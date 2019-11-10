
DROP DATABASE IF EXISTS mindbank;

CREATE DATABASE mindbank;

USE mindbank;

CREATE TABLE Role (
	ID INT NOT NULL,
	Name VARCHAR (10) NOT NULL,
	Description VARCHAR (100),
	PRIMARY KEY (ID)
);

CREATE TABLE User (
	ID INT NOT NULL AUTO_INCREMENT,
	RoleID INT NOT NULL,
	Email VARCHAR (30) NOT NULL UNIQUE,
	UserName VARCHAR (12) UNIQUE,
	Name VARCHAR (20),
	Bio VARCHAR (420),
	ProfileImage BLOB,
	PhoneNumber VARCHAR (15),
	PasswordHash TEXT NOT NULL,
	EmailVerified BOOLEAN,
	PhoneNumberVerified BOOLEAN,
	RegistrationTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	LoginTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (RoleID) REFERENCES Role(ID)
);

CREATE TABLE Category (
	ID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR (30) NOT NULL,
	Description VARCHAR (100),
	PRIMARY KEY (ID)
);

CREATE TABLE Problem (
	ID INT NOT NULL AUTO_INCREMENT,
	CategoryID INT NOT NULL,
	Title VARCHAR (100) NOT NULL,
	Content TEXT NOT NULL,
	Edited BOOLEAN DEFAULT FALSE NOT NULL,
	CreatedByUserID INT NOT NULL,
	CreatedTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (ID, CategoryID),
	FOREIGN KEY (CategoryID) REFERENCES Category(ID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(ID)
);

CREATE TABLE Auth (
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
VALUES (0, 1, "admin@admin.com", "admin", NULL, NULL, NULL, "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", TRUE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO User (ID, RoleID, Email, UserName, Name, Bio, PhoneNumber, PasswordHash, EmailVerified, PhoneNumberVerified, RegistrationTimestamp, LoginTimestamp) 
VALUES (NULL, 0, "jared@jaredible.net", "Jaredible", "Jared Diehl", "This is me!", "3146291836", "443697d7c5bd42e70e34c9254d9951e17f09e523f8ff969cd347f8af156da13e", FALSE, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

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
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 9, "This is going to be a very long title some day!", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 6, "Proposal", "This is a test.", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 7, "Simple Equation", "$$ 2+2=4 $$", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 9, "This is going to be a very long title some day!", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 6, "Proposal", "This is a test.", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 7, "Simple Equation", "$$ 2+2=4 $$", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 9, "This is going to be a very long title some day!", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 6, "Proposal", "This is a test.", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 7, "Simple Equation", "$$ 2+2=4 $$", FALSE, 1, CURRENT_TIMESTAMP);
INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (NULL, 9, "This is going to be a very long title some day!", "$$\sum_{n=1}^\infty \frac{1}{n^2} \to
  \textstyle \sum_{n=1}^\infty \frac{1}{n^2} \to
  \displaystyle \sum_{n=1}^\infty \frac{1}{n^2}$$", FALSE, 1, CURRENT_TIMESTAMP);
