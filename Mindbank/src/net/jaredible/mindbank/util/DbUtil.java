package net.jaredible.mindbank.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbUtil {

	public static Connection getConnection() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/mindbank");
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
