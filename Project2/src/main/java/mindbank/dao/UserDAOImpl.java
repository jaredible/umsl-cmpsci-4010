package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import main.java.mindbank.model.User;
import main.java.mindbank.util.DbConn;

public class UserDAOImpl implements UserDAO {

	private Connection connection;
	private PreparedStatement getEmailExists;
	private PreparedStatement isValidCredentials;
	private PreparedStatement setLogin;
	private PreparedStatement addUser;
	private PreparedStatement getUserById;
	private PreparedStatement getUserByEmail;
	private PreparedStatement updateUser;
	private PreparedStatement deleteUserById;

	public UserDAOImpl() throws SQLException {
		connection = DbConn.openConn();

		init();
	}

	public UserDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;

		init();
	}

	private void init() throws SQLException {
		getEmailExists = connection.prepareStatement("SELECT * FROM User WHERE Email = ?;");
		isValidCredentials = connection.prepareStatement("SELECT * FROM User WHERE Email = ? AND PasswordHash = ?;");
		setLogin = connection.prepareStatement("UPDATE User SET LoginTimestamp = CURRENT_TIMESTAMP WHERE ID = ?;");
		addUser = connection.prepareStatement("INSERT INTO User (ID, RoleID, Email, UserName, FirstName, LastName, PhoneNumber, PasswordHash, EmailVerified, PhoneNumberVerified, RegistrationTimestamp, LoginTimestamp) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		getUserById = connection.prepareStatement("SELECT * FROM User WHERE ID = ?;");
		getUserByEmail = connection.prepareStatement("SELECT * FROM User WHERE Email = ?;");
		updateUser = connection.prepareStatement("UPDATE User SET ... WHERE ID = ?");
		deleteUserById = connection.prepareStatement("DELETE FROM User WHERE ID = ?;");
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
	public void setLoginById(int id) {
		try {
			setLogin.setInt(1, id);
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
	public User getUserById(int id) {
		try {
			getUserById.setInt(1, id);
			ResultSet rs = getUserById.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setRoleId(rs.getInt("RoleID"));
				user.setEmail(rs.getString("Email"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setPasswordHash(rs.getString("PasswordHash"));
				user.setEmailVerified(rs.getBoolean("EmailVerified"));
				user.setPhoneNumberVerified(rs.getBoolean("PhoneNumberVerified"));
				user.setRegistrationTimestamp(rs.getTimestamp("RegistrationTimestamp"));
				user.setLoginTimestamp(rs.getTimestamp("LoginTimestamp"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		try {
			getUserByEmail.setString(1, email);
			ResultSet rs = getUserByEmail.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setRoleId(rs.getInt("RoleID"));
				user.setEmail(rs.getString("Email"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setPasswordHash(rs.getString("PasswordHash"));
				user.setEmailVerified(rs.getBoolean("EmailVerified"));
				user.setPhoneNumberVerified(rs.getBoolean("PhoneNumberVerified"));
				user.setRegistrationTimestamp(rs.getTimestamp("RegistrationTimestamp"));
				user.setLoginTimestamp(rs.getTimestamp("LoginTimestamp"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateUser(User user) {

	}

	@Override
	public void deleteUser(int id) {
		try {
			deleteUserById.setInt(1, id);
			deleteUserById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	protected void finalize() {
		try {
			if (!deleteUserById.isClosed()) {
				deleteUserById.close();
			}
			if (!getUserByEmail.isClosed()) {
				getUserByEmail.close();
			}
			if (!getUserById.isClosed()) {
				getUserById.close();
			}
			if (!setLogin.isClosed()) {
				setLogin.close();
			}
			if (!addUser.isClosed()) {
				addUser.close();
			}
			if (!isValidCredentials.isClosed()) {
				isValidCredentials.close();
			}
			if (!getEmailExists.isClosed()) {
				getEmailExists.close();
			}
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

}
