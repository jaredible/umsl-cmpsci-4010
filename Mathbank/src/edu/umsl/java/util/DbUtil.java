package edu.umsl.java.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil {

	private static String driver;
	private static String url;
	private static String name;
	private static String options;
	private static String user;
	private static String password;
	private static boolean propsLoaded = false;

	private static void loadProps() {
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			Properties props = new Properties();

			props.load(is);
			is.close();

			driver = props.getProperty("db.driver");
			url = props.getProperty("db.url");
			name = props.getProperty("db.name");
			options = props.getProperty("db.options");
			user = props.getProperty("db.user");
			password = props.getProperty("db.password");

			propsLoaded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection openConnection() {
		if (!propsLoaded) {
			loadProps();
		}

		Connection conn = null;

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(getConnectionString(), user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	private static String getConnectionString() {
		if (!propsLoaded) {
			loadProps();
		}

		return url + name + options;
	}

}
