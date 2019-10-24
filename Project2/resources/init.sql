
-- CREATE TABLE IF NOT EXISTS test (id int);

CREATE TABLE IF NOT EXISTS user (
	id INT NOT NULL,
	email VARCHAR (20) NOT NULL,
	username VARCHAR (20) NOT NULL,
	firstname VARCHAR (20) NOT NULL,
	lastname VARCHAR (20) NOT NULL,
	phone INT,
	password VARCHAR (20) NOT NULL,
	PRIMARY KEY (id, email, username)
);

CREATE TABLE IF NOT EXISTS role (
	id INT NOT NULL,
	name VARCHAR (20) NOT NULL,
	description VARCHAR (20)
);