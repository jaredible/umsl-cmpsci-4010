package net.jaredible.umsl.cmpsci4010.util;

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

	public static Connection openConn() {
		if (!propsLoaded) {
			loadProps();
		}

		Connection conn = null;

		try {
			createDatabase();
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + name, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConn(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
			stmt.close();
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
