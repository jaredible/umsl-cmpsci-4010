package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.mindbank.model.User;
import main.java.mindbank.util.DbConn;

public class UserDAOImpl implements UserDAO {

	private Connection conn;
	private PreparedStatement getEmailExists;
	private PreparedStatement isValidCredentials;
	private PreparedStatement addUser;
	private PreparedStatement setLogin;

	public UserDAOImpl() throws SQLException {
		conn = DbConn.openConn();
		getEmailExists = conn.prepareStatement("SELECT * FROM user WHERE email = ?");
		isValidCredentials = conn.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
		addUser = conn.prepareStatement("INSERT INTO user VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		setLogin = conn.prepareStatement("UPDATE user SET lastLoginTimestamp = ? WHERE email = ?");
	}

	@Override
	public boolean getEmailExists(String email) {
		try {
			getEmailExists.setString(1, email);
			ResultSet rs = getEmailExists.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean isValidCredentials(String email, String password) {
		try {
			isValidCredentials.setString(1, email);
			isValidCredentials.setString(2, password);
			ResultSet rs = isValidCredentials.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void setLogin(User user) {
		try {
			setLogin.setTimestamp(1, user.getLastLoginTimestamp());
			setLogin.setString(2, user.getEmail());
			setLogin.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {
		try {
			addUser.setInt(1, user.getId());
			addUser.setString(2, user.getFirstname());
			addUser.setString(3, user.getLastname());
			addUser.setString(4, user.getDisplayName());
			addUser.setString(5, user.getEmail());
			addUser.setInt(6, user.getPhoneNumber());
			addUser.setString(7, user.getPassword());
			addUser.setInt(8, user.getRoleId());
			addUser.setBoolean(9, user.isEmailVerified());
			addUser.setBoolean(10, user.isPhoneVerified());
			addUser.setTimestamp(11, user.getRegistrationTimestamp());
			addUser.setTimestamp(12, user.getLastLoginTimestamp());
			addUser.executeUpdate();
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
	public void deleteUser(int id) {
	}

	protected void finalize() {
		try {
			addUser.close();
			isValidCredentials.close();
			getEmailExists.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
