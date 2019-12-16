package net.jaredible.mindbank.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class DAOFactory {

	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USERNAME = "username";
	private static final String PROPERTY_PASSWORD = "password";

	abstract Connection getConnection() throws SQLException;

	public UserDAO getUserDAO() {
		return new UserDAOJDBC(this);
	}

	public ProblemDAO getProblemDAO() {
		return new ProblemDAOJDBC(this);
	}

	public CategoryDAO getCategoryDAO() {
		return new CategoryDAOJDBC(this);
	}

	public TagDAO getTagDAO() {
		return new TagDAOJDBC(this);
	}

	public static DAOFactory getInstance(String name) throws DAOConfigurationException {
		if (name == null) {
			throw new DAOConfigurationException("Database name is null.");
		}

		DAOProperties properties = new DAOProperties(name);
		String url = properties.getProperty(PROPERTY_URL, true);
		String driverClassName = properties.getProperty(PROPERTY_DRIVER, false);
		String password = properties.getProperty(PROPERTY_PASSWORD, false);
		String username = properties.getProperty(PROPERTY_USERNAME, password != null);
		DAOFactory instance;

		if (driverClassName != null) {
			try {
				Class.forName(driverClassName);
			} catch (ClassNotFoundException e) {
				throw new DAOConfigurationException("Driver class '" + driverClassName + "' is missing in classpath.", e);
			}
			instance = new DriverManagerDAOFactory(url, username, password);
		} else {
			DataSource dataSource;
			try {
				dataSource = (DataSource) new InitialContext().lookup(url);
			} catch (NamingException e) {
				throw new DAOConfigurationException("DataSource '" + url + "' is missing in JNDI.", e);
			}
			if (username != null) {
				instance = new DataSourceWithLoginDAOFactory(dataSource, username, password);
			} else {
				instance = new DataSourceDAOFactory(dataSource);
			}
		}

		return instance;
	}

}
