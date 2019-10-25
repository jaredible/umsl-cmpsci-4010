
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
