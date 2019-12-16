package net.jaredible.mindbank.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

class DataSourceDAOFactory extends DAOFactory {

	private DataSource dataSource;

	DataSourceDAOFactory(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
