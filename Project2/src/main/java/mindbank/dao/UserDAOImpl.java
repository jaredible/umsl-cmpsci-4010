package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
		getEmailExists = conn.prepareStatement("SELECT * FROM User WHERE Email = ?;");
		isValidCredentials = conn.prepareStatement("SELECT * FROM User WHERE Email = ? AND PasswordHash = ?;");
		addUser = conn.prepareStatement("INSERT INTO User (ID, RoleID, Email, UserName, FirstName, LastName, PhoneNumber, PasswordHash, EmailVerified, PhoneNumberVerified, RegistrationTimestamp, LoginTimestamp) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		setLogin = conn.prepareStatement("UPDATE User SET LoginTimestamp = ? WHERE Email = ?;");
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
			setLogin.setTimestamp(1, user.getLoginTimestamp());
			setLogin.setString(2, user.getEmail());
			setLogin.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {
		try {
			addUser.setNull(1, Types.INTEGER);
			addUser.setInt(2, user.getRoleId());
			addUser.setString(3, user.getEmail());
			addUser.setString(4, user.getUserName());
			addUser.setString(5, user.getFirstName());
			addUser.setString(6, user.getLastName());
			addUser.setString(7, user.getPhoneNumber());
			addUser.setString(8, user.getPasswordHash());
			addUser.setBoolean(9, user.isEmailVerified());
			addUser.setBoolean(10, user.isPhoneNumberVerified());
			addUser.setTimestamp(11, user.getRegistrationTimestamp());
			addUser.setTimestamp(12, user.getLoginTimestamp());
			addUser.execute();
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
			setLogin.close();
			addUser.close();
			isValidCredentials.close();
			getEmailExists.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
