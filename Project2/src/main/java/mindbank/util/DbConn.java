package main.java.mindbank.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	private static boolean initialized = false;

	private static void loadProps() {
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();

			prop.load(is);
			is.close();

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

	public static Connection openConn() {
		if (!propsLoaded) {
			loadProps();
		}

		Connection conn = null;

		try {
			Class.forName(driver).newInstance();
			initDb();
			conn = DriverManager.getConnection(getDbUrl(), user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void initDb() throws SQLException {
		if (initialized) {
			return;
		}

		if (!propsLoaded) {
			loadProps();
		}

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				stmt = conn.createStatement();
				if (stmt != null) {
					stmt.execute("CREATE DATABASE IF NOT EXISTS " + name + ";");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}

		initialized = true;
	}

	public static boolean getDbExists() throws SQLException {
		if (!propsLoaded) {
			loadProps();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				ResultSet rs = conn.getMetaData().getCatalogs();
				while (rs.next()) {
					if (name.equals(rs.getString(1))) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return false;
	}

	public static String getDbUrl() {
		if (!propsLoaded) {
			loadProps();
		}

		return url + name;
	}

	public static void main(String[] args) {
		try {
			Connection conn = openConn();
			initDb();
			System.out.println("" + getDbExists());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
