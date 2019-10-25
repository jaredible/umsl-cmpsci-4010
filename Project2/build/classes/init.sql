
-- CREATE TABLE IF NOT EXISTS test (id int);

CREATE TABLE IF NOT EXISTS user (
	id INT NOT NULL AUTO_INCREMENT,
	email VARCHAR (100) NOT NULL,
	displayName VARCHAR (100) NOT NULL,
	firstName VARCHAR (100) NOT NULL,
	lastName VARCHAR (100) NOT NULL,
	phone VARCHAR (20),
	password VARCHAR (100) NOT NULL,
	roleId INT NOT NULL,
	emailVerified BOOLEAN NOT NULL,
	phoneVerified BOOLEAN NOT NULL,
	registrationTimestamp TIMESTAMP NOT NULL,
	lastLoginTimestamp TIMESTAMP NOT NULL,
	PRIMARY KEY (id, email)
);
