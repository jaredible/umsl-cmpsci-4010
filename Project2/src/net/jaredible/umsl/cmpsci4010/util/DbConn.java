package net.jaredible.umsl.cmpsci4010.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConn {

	private static String driver;
	private static String url;
	private static String name;
	private static String user;
	private static String password;
	private static boolean propsLoaded = false;
	private static Connection connection = null;

	private static void loadProps() {
		try {
			InputStream is = DbConn.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();

			prop.load(is);

			driver = prop.getProperty("db.driver");
			url = prop.getProperty("db.url");
			name = prop.getProperty("db.name");
			user = prop.getProperty("db.user");
			password = prop.getProperty("db.password");

			propsLoaded = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			if (!propsLoaded) {
				loadProps();
			}

			try {
				createDatabase();
				Class.forName(driver).newInstance();
				connection = DriverManager.getConnection(url + "/" + name, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return connection;
		}
	}

	private static void createDatabase() throws SQLException {
		if (!propsLoaded) {
			loadProps();
		}

		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stmt = conn.createStatement();

		try {
			stmt.execute("CREATE DATABASE IF NOT EXISTS " + name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public static void main(String[] args) {
		try {
			createDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
