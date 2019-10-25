package main.java.mindbank.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	private static DbConn instance;

	public static DbConn getInstance() {
		if (instance == null) {
			instance = new DbConn();
		}

		return instance;
	}

	private void loadProps() {
		boolean test = true;
		if (test) { // TODO
			driver = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://192.168.64.3:3306/";
			name = "mindbank";
			user = "admin";
			password = "";
			propsLoaded = true;
			return;
		}

		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();

			System.out.println("" + (is == null));

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

	public Connection openConn() {
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

	public void initDb() throws SQLException {
		if (initialized) {
			return;
		}

		if (!propsLoaded) {
			loadProps();
		}

		Connection conn = null;
		Statement stmt = null;

		try {
			System.out.println(url);
			System.out.println(user);
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				stmt = conn.createStatement();
				if (stmt != null) {
					stmt.execute("CREATE DATABASE IF NOT EXISTS " + name);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}

		try {
			conn = DriverManager.getConnection(getDbUrl(), user, password);
			if (conn != null) {
				stmt = conn.createStatement();
				if (stmt != null) {
					File f = new File(Thread.currentThread().getContextClassLoader().getResource("init.sql").getFile());
					if (f != null) {
						FileReader fr = new FileReader(f);
						if (fr != null) {
							BufferedReader br = new BufferedReader(fr);
							if (br != null) {
								String result = "";
								String line;
								while ((line = br.readLine()) != null) {
									result += line + "\n";
								}
								stmt.execute(result);
							}
							br.close();
						}
						fr.close();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}

		initialized = true;
	}

	public boolean getDbExists() throws SQLException {
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

	public String getDbUrl() {
		if (!propsLoaded) {
			loadProps();
		}

		return url + name;
	}

	public static void main(String[] args) {
		try {
			DbConn db = getInstance();
			Connection conn = db.openConn();
			db.initDb();
			System.out.println("" + db.getDbExists());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
