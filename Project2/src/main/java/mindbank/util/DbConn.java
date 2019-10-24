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
			initDb();
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

	private static void initDb() throws SQLException {
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
					File f = new File(DbConn.class.getClassLoader().getResource("init.sql").getFile());
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
						}
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

	private static boolean getDbExists() throws SQLException {
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
			initDb();
			System.out.println("" + getDbExists());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
