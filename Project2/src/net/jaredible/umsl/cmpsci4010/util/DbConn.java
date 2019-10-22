package net.jaredible.umsl.cmpsci4010.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConn {
	private static Connection dbConnection = null;

	public static Connection getConnection() {
		if (dbConnection != null) {
			return dbConnection;
		} else {
			try {
				InputStream is = DbConn.class.getClassLoader().getResourceAsStream("db.properties");
				Properties p = new Properties();
				if (p != null) {
					p.load(is);

					String dbDriver = p.getProperty("dbDriver");
					String connectionUrl = p.getProperty("connectionUrl");
					String username = p.getProperty("username");
					String password = p.getProperty("password");

					Class.forName(dbDriver).newInstance();
					dbConnection = DriverManager.getConnection(connectionUrl, username, password);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return dbConnection;
		}
	}
}
