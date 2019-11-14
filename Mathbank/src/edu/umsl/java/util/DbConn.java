package edu.umsl.java.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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
		} catch (Exception e) {
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
			conn = DriverManager.getConnection(getDbUrl(), user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static String getDbUrl() {
		if (!propsLoaded) {
			loadProps();
		}

		return url + name;
	}

	public static void main(String[] args) {
	}

}
