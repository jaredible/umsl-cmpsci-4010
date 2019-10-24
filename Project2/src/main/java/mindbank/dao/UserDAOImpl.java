package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.mindbank.model.User;
import main.java.mindbank.util.DbConn;

public class UserDAOImpl implements UserDAO {

	private Connection conn;
	private PreparedStatement checkExists;
	private PreparedStatement addUser;

	public UserDAOImpl() throws SQLException {
		conn = DbConn.openConn();
		checkExists = conn.prepareStatement("SELECT * FROM user WHERE email = ?");
		addUser = conn.prepareStatement("INSERT INTO user VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?)");
	}

	@Override
	public boolean checkExists(String email) {
		try {
			checkExists.setString(1, email);
			ResultSet rs = checkExists.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean login(String email, String password) {
		return false;
	}

	@Override
	public void addUser(User user) {
		try {
			checkExists.setString(1, email);
			ResultSet rs = checkExists.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int id) {
		return null;
	}

	@Override
	public User getUser(String email) {
		return null;
	}

	@Override
	public void updateUser(int id) {
	}

	@Override
	public void deleteUser(int id) {
	}

	protected void finalize() {
		try {
			checkExists.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
