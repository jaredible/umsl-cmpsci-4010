
CREATE USER 'mindbank'@'%' IDENTIFIED BY 'mindbank';
GRANT ALL ON mindbank.* TO 'mindbank'@'%' IDENTIFIED BY 'mindbank';

DROP DATABASE IF EXISTS mindbank;
CREATE DATABASE mindbank;
USE mindbank;

CREATE TABLE mindbank.User (
	UserID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	Email VARCHAR(50) NOT NULL UNIQUE,
	UserName VARCHAR(20) NOT NULL UNIQUE,
	Name VARCHAR(30),
	Bio VARCHAR(420),
	StatusEmoji VARCHAR(30),
	StatusText VARCHAR (30),
	RegisteredTime DATETIME NOT NULL,
	LastLoginTime DATETIME,
	PassWordSalt VARCHAR(11) NOT NULL,
	PassWordHash VARCHAR(128) NOT NULL,
	CookieSecretKey VARCHAR(11),
	CookieSelector VARCHAR(12),
	HashedCookieValidator VARCHAR(128),
	PRIMARY KEY (UserID)
);

CREATE TABLE mindbank.Tag (
	TagID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	Name VARCHAR(20) NOT NULL UNIQUE,
	CreatedTime DATETIME NOT NULL,
	CreatedByUserID BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (TagID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(UserID)
);

CREATE TABLE mindbank.Category (
	CategoryID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	Name VARCHAR(20) NOT NULL UNIQUE,
	CreatedTime DATETIME NOT NULL,
	CreatedByUserID BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (CategoryID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(UserID)
);

CREATE TABLE mindbank.Problem (
	ProblemID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	Title VARCHAR(100) NOT NULL UNIQUE,
	Content VARCHAR(10000) NOT NULL,
	CreatedTime DATETIME NOT NULL,
	CreatedByUserID BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (ProblemID),
	FOREIGN KEY (CreatedByUserID) REFERENCES User(UserID)
);

CREATE TABLE mindbank.ProblemCategory (
	ProblemCategoryID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	ProblemID BIGINT UNSIGNED NOT NULL,
	CategoryID BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (ProblemCategoryID, ProblemID, CategoryID),
	FOREIGN KEY (ProblemID) REFERENCES Problem(ProblemID),
	FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE mindbank.ProblemTag (
	ProblemTagID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	ProblemID BIGINT UNSIGNED NOT NULL,
	TagID BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (ProblemTagID, ProblemID, TagID),
	FOREIGN KEY (ProblemID) REFERENCES Problem(ProblemID),
	FOREIGN KEY (TagID) REFERENCES Tag(TagID)
);

INSERT INTO User (UserID, Email, UserName, Name, Bio, StatusEmoji, StatusText, RegisteredTime, LastLoginTime, PasswordSalt, PasswordHash) VALUES
(NULL, "jared@jaredible.net", "Jaredible", "Jared D", "I solve Rubik's cubes!", "zany_face", "Just studying", "2019-11-23 21:52:26", "2019-11-23 21:52:26", "[B@396502b9", "e3d51346952c09d8fca12fc2011cff8a79031c9dfb56cafdf147a8cd85d6a2f1050efa26bbde4c2bc27ffa941a775121ea79e1a58a9f2e266e4b1876db10e5be");
INSERT INTO User (UserID, Email, UserName, Name, Bio, RegisteredTime, LastLoginTime, PasswordSalt, PasswordHash) VALUES
(NULL, "ian.g@jaredible.net", "TL", "Ian G", NULL, "2019-11-23 21:52:24", "2019-11-23 21:52:24", "[B@4f876338", "303b70f6c3b8b3213aad1d2a6082d2a878a891b00a6f1aeb6d06d5bbc931e94ee3698a3d5c7344cf148fc3b9bde7ae47a6c2d8961852c10c2e8c0ca252427e47"),
(NULL, "chris.c@jaredible.net", "Xephorium", "Chris C", NULL, "2019-11-23 21:52:25", "2019-11-23 21:52:25", "[B@758e9b0a", "8155ea427a38983982675470591f251ad5594c12a58e63551178deddb0e8df9c880798424ca381c5b2bcd6c740336a78ff6e7487a3eb366721d4858c3183661b"),
(NULL, "allison.f@jaredible.net", "LunaRose", "Allison F", NULL, "2019-11-23 21:52:27", "2019-11-23 21:52:27", "[B@2674be74", "240a612253b8791cbbf4b433a468a11246436262c993e94c9c67c2f247000817cb1bcaf72e504c95e00ef76ef850b7debbe2d0423b40eafcfe8126accdd5b1cd"),
(NULL, "duc.n@jaredible.net", "Zegster", "Duc N", NULL, "2019-11-23 21:52:28", "2019-11-23 21:52:28", "[B@d0ada33", "6479580592707215b8c71b4e87d6e0d3638e0b7d2dfad8c8ede03d138a35b4c0efd92aa24bed9344a4313bc7e10d0fffd3754d3bf81bcf93c4fe0a01d35b5145"),
(NULL, "vasyl.o@jaredible.net", "Celestial", "Vasyl O", NULL, "2019-11-23 21:52:29", "2019-11-23 21:52:29", "[B@35b7bc2b", "98100ae6cd62c2ff6312f3304b976036c57d8217b853ed151ea7344f00b15f10a6ce25f2eab48ba40a2f564eef530f7b2047ce35ae771a0dca9b2c4c4113ba50"),
(NULL, "tarik.m@jaredible.net", "Tarik", "Tarik M", NULL, "2019-11-23 21:52:30", "2019-11-23 21:52:30", "[B@dd5cfcc", "039f9e3c42493b4b9b8a675e49fe97f3e8304bd0b28ca27e0b62ae7fff2de662287b51a9bc9743ea4019c158a504e68ca7b2fde46faf5718ce2c686bbcd176bb"),
(NULL, "wenjie.h@jaredible.net", "DrHe", "Wenjie He", NULL, "2019-11-23 21:52:31", "2019-11-23 21:52:31", "[B@52338a4f", "70739ae7f0916748c8aec3e4cf724b4ef0a281a41e252fd86f66c52b373c8ffbe0e04aa2dd128019bb9800ea0d6391d40781e377c69708fecb86dd2e32306519");

INSERT INTO Tag (TagID, Name, CreatedTime, CreatedByUserID) VALUES
(NULL, "UMSL", "2019-11-23 21:52:24", 1),
(NULL, "MATH", "2019-11-23 21:52:25", 1),
(NULL, "CMPSCI", "2019-11-23 21:52:26", 1),
(NULL, "3000", "2019-11-23 21:52:27", 1),
(NULL, "4250", "2019-11-23 21:52:28", 1),
(NULL, "4010", "2019-11-23 21:52:29", 1),
(NULL, "Quiz", "2019-11-23 21:52:30", 1);

INSERT INTO Category (CategoryID, Name, CreatedTime, CreatedByUserID) VALUES
(NULL, "UMSL", "2019-11-23 21:52:24", 1),
(NULL, "MATH", "2019-11-23 21:52:25", 1),
(NULL, "CMPSCI", "2019-11-23 21:52:26", 1),
(NULL, "3000", "2019-11-23 21:52:27", 1),
(NULL, "4250", "2019-11-23 21:52:28", 1),
(NULL, "4010", "2019-11-23 21:52:29", 1),
(NULL, "Quiz", "2019-11-23 21:52:30", 1);

INSERT INTO Problem (ProblemID, Title, Content, CreatedTime, CreatedByUserID) VALUES
(NULL, "UMSL CMPSCI 4010 Quiz 4 Question 1", "In a Java web application, in order to configure a filter chain, we have to use the file web.xml.", "2019-11-23 21:52:24", 1),
(NULL, "UMSL CMPSCI 4010 Quiz 4 Question 2", "A filter can only intercept requests for Java files (servlets and JSPs). It cannot intercept any request for non-Java files, such as HTML, PDF, etc.", "2019-11-23 21:52:25", 1),
(NULL, "UMSL CMPSCI 4010 Quiz 4 Question 3", "In a Java web application, it is not allowed to make two users share the same database connection object.", "2019-11-23 21:52:26", 1),
(NULL, "UMSL CMPSCI 4010 Quiz 4 Question 4", "The following gives the order of the database connection efficiency from the least to the most efficient: servlet scope connection, method scope connection, connection pool.", "2019-11-23 21:52:27", 1),
(NULL, "UMSL CMPSCI 4010 Quiz 4 Question 5", "To configure a connection pool in a Java web application in Tomcat, the configuration file META-INF/context.xml is used.", "2019-11-23 21:52:28", 1),
(NULL, 'Testing', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \'Content here, content here\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \'lorem ipsum\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\r\n$$\\sum_{n=1}^\\infty \\frac{1}{n^2} \\to\r\n  \\textstyle \\sum_{n=1}^\\infty \\frac{1}{n^2} \\to\r\n  \\displaystyle \\sum_{n=1}^\\infty \\frac{1}{n^2}$$\r\nThis is just a test.\r\n$$\r\n    \\begin{matrix}\r\n    1 & x & x^2 \\\\\r\n    1 & y & y^2 \\\\\r\n    1 & z & z^2 \\\\\r\n    \\end{matrix}\r\n$$', '2019-11-23 21:52:29', 8);

INSERT INTO ProblemCategory (ProblemCategoryID, ProblemID, CategoryID) VALUES
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
(NULL, 4, 7);

INSERT INTO ProblemTag (ProblemTagID, ProblemID, TagID) VALUES
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
(NULL, 4, 7);
