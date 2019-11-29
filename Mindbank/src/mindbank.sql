
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
	StatusEmoji VARCHAR (50),
	StatusText VARCHAR (50),
	RegisteredTime DATETIME DEFAULT NOW(),
	LastLoginTime DATETIME NOT NULL DEFAULT NOW(),
	EmailVerified BOOLEAN NOT NULL DEFAULT FALSE,
	PasswordSalt VARCHAR (11) NOT NULL,
	PasswordHash VARCHAR (128) NOT NULL,
	CookieSecretKey VARCHAR (11),
	CookieSelector VARCHAR (12),
	HashedCookieValidator VARCHAR (128),
	PRIMARY KEY (ID)
);

CREATE TABLE Tag (
	ID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR (20) NOT NULL UNIQUE,
	CreatedTime DATETIME NOT NULL DEFAULT NOW(),
	CreatedByUserID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(ID)
);

CREATE TABLE Category (
	ID INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR (20) NOT NULL UNIQUE,
	CreatedTime DATETIME NOT NULL DEFAULT NOW(),
	CreatedByUserID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(ID)
);

CREATE TABLE Problem (
	ID INT NOT NULL AUTO_INCREMENT,
	Title VARCHAR (50) NOT NULL UNIQUE,
	Content TEXT NOT NULL,
	CreatedTime DATETIME NOT NULL DEFAULT NOW(),
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

INSERT INTO User (ID, Email, UserName, Name, Bio, ProfileImage, RegisteredTime, LastLoginTime, EmailVerified, PasswordSalt, PasswordHash) VALUES
(NULL, "jared@jaredible.net", "Jaredible", "Jared D", "I solve Rubik's Cubes!", NULL, "2019-11-23 21:52:26", "2019-11-23 21:52:26", TRUE, "[B@396502b9", "e3d51346952c09d8fca12fc2011cff8a79031c9dfb56cafdf147a8cd85d6a2f1050efa26bbde4c2bc27ffa941a775121ea79e1a58a9f2e266e4b1876db10e5be"),
(NULL, "ian.g@jaredible.net", "TL", "Ian G", NULL, NULL, "2019-11-23 21:52:24", "2019-11-23 21:52:24", TRUE, "[B@4f876338", "303b70f6c3b8b3213aad1d2a6082d2a878a891b00a6f1aeb6d06d5bbc931e94ee3698a3d5c7344cf148fc3b9bde7ae47a6c2d8961852c10c2e8c0ca252427e47"),
(NULL, "chris.c@jaredible.net", "Xephorium", "Chris C", NULL, NULL, "2019-11-23 21:52:25", "2019-11-23 21:52:25", TRUE, "[B@758e9b0a", "8155ea427a38983982675470591f251ad5594c12a58e63551178deddb0e8df9c880798424ca381c5b2bcd6c740336a78ff6e7487a3eb366721d4858c3183661b"),
(NULL, "allison.f@jaredible.net", "LunaRose", "Allison F", NULL, NULL, "2019-11-23 21:52:27", "2019-11-23 21:52:27", TRUE, "[B@2674be74", "240a612253b8791cbbf4b433a468a11246436262c993e94c9c67c2f247000817cb1bcaf72e504c95e00ef76ef850b7debbe2d0423b40eafcfe8126accdd5b1cd"),
(NULL, "duc.n@jaredible.net", "Zegster", "Duc N", NULL, NULL, "2019-11-23 21:52:28", "2019-11-23 21:52:28", TRUE, "[B@d0ada33", "6479580592707215b8c71b4e87d6e0d3638e0b7d2dfad8c8ede03d138a35b4c0efd92aa24bed9344a4313bc7e10d0fffd3754d3bf81bcf93c4fe0a01d35b5145"),
(NULL, "vasyl.o@jaredible.net", "Celestial", "Vasyl O", NULL, NULL, "2019-11-23 21:52:29", "2019-11-23 21:52:29", TRUE, "[B@35b7bc2b", "98100ae6cd62c2ff6312f3304b976036c57d8217b853ed151ea7344f00b15f10a6ce25f2eab48ba40a2f564eef530f7b2047ce35ae771a0dca9b2c4c4113ba50"),
(NULL, "tarik.m@jaredible.net", "Tarik", "Tarik M", NULL, NULL, "2019-11-23 21:52:30", "2019-11-23 21:52:30", TRUE, "[B@dd5cfcc", "039f9e3c42493b4b9b8a675e49fe97f3e8304bd0b28ca27e0b62ae7fff2de662287b51a9bc9743ea4019c158a504e68ca7b2fde46faf5718ce2c686bbcd176bb");

INSERT INTO Tag (ID, Name, CreatedTime, CreatedByUserID) VALUES
(NULL, "UMSL", "2019-11-23 21:52:24", 1),
(NULL, "MATH", "2019-11-23 21:52:25", 1),
(NULL, "CMPSCI", "2019-11-23 21:52:26", 1),
(NULL, "3000", "2019-11-23 21:52:27", 1),
(NULL, "4250", "2019-11-23 21:52:28", 1),
(NULL, "4010", "2019-11-23 21:52:29", 1),
(NULL, "Quiz", "2019-11-23 21:52:30", 1);

INSERT INTO Category (ID, Name, CreatedTime, CreatedByUserID) VALUES
(NULL, "UMSL", "2019-11-23 21:52:24", 1),
(NULL, "MATH", "2019-11-23 21:52:25", 1),
(NULL, "CMPSCI", "2019-11-23 21:52:26", 1),
(NULL, "3000", "2019-11-23 21:52:27", 1),
(NULL, "4250", "2019-11-23 21:52:28", 1),
(NULL, "4010", "2019-11-23 21:52:29", 1),
(NULL, "Quiz", "2019-11-23 21:52:30", 1);

INSERT INTO Problem (ID, Title, Content, CreatedTime, CreatedByUserID) VALUES
(NULL, "UMSL CMPSCI 4010 Quiz 3 Question 1", "This is the first question.", "2019-11-23 21:52:24", 1),
(NULL, "UMSL CMPSCI 4010 Quiz 3 Question 2", "This is the second question.", "2019-11-23 21:52:25", 1),
(NULL, "UMSL CMPSCI 4010 Quiz 3 Question 3", "This is the third question.", "2019-11-23 21:52:26", 1),
(NULL, "UMSL CMPSCI 4010 Quiz 3 Question 4", "This is the fourth question.", "2019-11-23 21:52:27", 1),
(NULL, "UMSL CMPSCI 4010 Quiz 3 Question 5", "This is the fifth question.", "2019-11-23 21:52:28", 1);

INSERT INTO ProblemCategory (ID, ProblemID, CategoryID) VALUES
(NULL, 1, 1),
(NULL, 1, 3),
(NULL, 1, 6),
(NULL, 1, 7),
(NULL, 2, 1),
(NULL, 2, 3),
(NULL, 2, 6),
(NULL, 2, 7),
(NULL, 3, 1),
(NULL, 3, 3),
(NULL, 3, 6),
(NULL, 3, 7),
(NULL, 4, 1),
(NULL, 4, 3),
(NULL, 4, 6),
(NULL, 4, 7),
(NULL, 5, 1),
(NULL, 5, 3),
(NULL, 5, 6),
(NULL, 5, 7);

INSERT INTO ProblemTag (ID, ProblemID, TagID) VALUES
(NULL, 1, 1),
(NULL, 1, 3),
(NULL, 1, 6),
(NULL, 1, 7),
(NULL, 2, 1),
(NULL, 2, 3),
(NULL, 2, 6),
(NULL, 2, 7),
(NULL, 3, 1),
(NULL, 3, 3),
(NULL, 3, 6),
(NULL, 3, 7),
(NULL, 4, 1),
(NULL, 4, 3),
(NULL, 4, 6),
(NULL, 4, 7),
(NULL, 5, 1),
(NULL, 5, 3),
(NULL, 5, 6),
(NULL, 5, 7);
