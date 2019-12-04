package net.jaredible.mindbank.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.user.User;
import net.jaredible.mindbank.util.DbUtil;

public class UserDaoImpl implements UserDao {

	private Connection connection;
	private PreparedStatement getUserById;
	private PreparedStatement getUserByEmail;
	private PreparedStatement getUserByUserName;
	private PreparedStatement getUserByCookieSelector;
	private PreparedStatement getAllUsers;
	private PreparedStatement addUser;
	private PreparedStatement updateUser;
	private PreparedStatement deleteUserById;

	@Override
	public User getUserById(long id) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getUserById == null) {
				getUserById = connection.prepareStatement("SELECT * FROM User WHERE ID = ?;");
			}

			getUserById.setLong(1, id);

			rs = getUserById.executeQuery();

			if (rs.next()) {
				return extractUserFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getUserByEmail == null) {
				getUserByEmail = connection.prepareStatement("SELECT * FROM User WHERE Email = ?;");
			}

			getUserByEmail.setString(1, email);

			rs = getUserByEmail.executeQuery();

			if (rs.next()) {
				return extractUserFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public User getUserByUserName(String userName) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getUserByUserName == null) {
				getUserByUserName = connection.prepareStatement("SELECT * FROM User WHERE UserName = ?;");
			}

			getUserByUserName.setString(1, userName);

			rs = getUserByUserName.executeQuery();

			if (rs.next()) {
				return extractUserFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public User getUserByCookieSelector(String cookieSelector) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getUserByCookieSelector == null) {
				getUserByCookieSelector = connection.prepareStatement("SELECT * FROM User WHERE CookieSelector = ?;");
			}

			getUserByCookieSelector.setString(1, cookieSelector);

			rs = getUserByCookieSelector.executeQuery();

			if (rs.next()) {
				return extractUserFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public List<User> getAllUsers() {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getAllUsers == null) {
				getAllUsers = connection.prepareStatement("SELECT * FROM User;");
			}

			rs = getAllUsers.executeQuery();

			List<User> users = new ArrayList<User>();

			while (rs.next()) {
				users.add(extractUserFromResultSet(rs));
			}

			return users;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public long addUser(User user) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (addUser == null) {
				addUser = connection.prepareStatement("INSERT INTO User (ID, Email, UserName, Name, Bio, ProfileImage, StatusEmoji, StatusText, RegisteredTime, LastLoginTime, EmailVerified, PasswordSalt, PasswordHash, CookieSecretKey, CookieSelector, HashedCookieValidator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			}

			addUser.setNull(1, Types.INTEGER);
			addUser.setString(2, user.getEmail());
			addUser.setString(3, user.getUserName());
			addUser.setString(4, user.getName());
			addUser.setString(5, user.getBio());
			addUser.setBlob(6, user.getProfileImage());
			addUser.setString(7, user.getStatusEmoji());
			addUser.setString(8, user.getStatusText());
			addUser.setTimestamp(9, user.getRegisteredTime());
			addUser.setTimestamp(10, user.getLastLoginTime());
			addUser.setBoolean(11, user.isEmailVerified());
			addUser.setString(12, user.getPasswordSalt());
			addUser.setString(13, user.getPasswordHash());
			updateUser.setString(14, user.getCookieSecretKey());
			updateUser.setString(15, user.getCookieSelector());
			updateUser.setString(16, user.getHashedCookieValidator());

			int rowAffected = addUser.executeUpdate();

			if (rowAffected == 1) {
				rs = addUser.getGeneratedKeys();

				if (rs.next()) {
					return rs.getLong(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return -1;
	}

	@Override
	public int updateUser(User user) {
		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (updateUser == null) {
				updateUser = connection.prepareStatement("UPDATE User SET ID = ?, Email = ?, UserName = ?, Name = ?, Bio = ?, ProfileImage = ?, StatusEmoji = ?, StatusText = ?, RegisteredTime = ?, LastLoginTime = ?, EmailVerified = ?, PasswordSalt = ?, PasswordHash = ?, CookieSecretKey = ?, CookieSelector = ?, HashedCookieValidator = ? WHERE ID = ?;");
			}

			updateUser.setLong(1, user.getId());
			updateUser.setString(2, user.getEmail());
			updateUser.setString(3, user.getUserName());
			updateUser.setString(4, user.getName());
			updateUser.setString(5, user.getBio());
			updateUser.setBlob(6, user.getProfileImage());
			updateUser.setString(7, user.getStatusEmoji());
			updateUser.setString(8, user.getStatusText());
			updateUser.setTimestamp(9, user.getRegisteredTime());
			updateUser.setTimestamp(10, user.getLastLoginTime());
			updateUser.setBoolean(11, user.isEmailVerified());
			updateUser.setString(12, user.getPasswordSalt());
			updateUser.setString(13, user.getPasswordHash());
			updateUser.setString(14, user.getCookieSecretKey());
			updateUser.setString(15, user.getCookieSelector());
			updateUser.setString(16, user.getHashedCookieValidator());

			updateUser.setLong(17, user.getId());

			return updateUser.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public int deleteUserById(long id) {
		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (deleteUserById == null) {
				deleteUserById = connection.prepareStatement("DELETE FROM User WHERE ID = ?;");
			}

			deleteUserById.setLong(1, id);

			return deleteUserById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	private User extractUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt("ID"));
		user.setEmail(rs.getString("Email"));
		user.setUserName(rs.getString("UserName"));
		user.setName(rs.getString("Name"));
		user.setBio(rs.getString("Bio"));
		user.setProfileImage(rs.getBlob("ProfileImage"));
		user.setStatusEmoji(rs.getString("StatusEmoji"));
		user.setStatusText(rs.getString("StatusText"));
		user.setRegisteredTime(rs.getTimestamp("RegisteredTime"));
		user.setLastLoginTime(rs.getTimestamp("LastLoginTime"));
		user.setEmailVerified(rs.getBoolean("EmailVerified"));
		user.setPasswordSalt(rs.getString("PasswordSalt"));
		user.setPasswordHash(rs.getString("PasswordHash"));
		user.setCookieSecretKey(rs.getString("CookieSecretKey"));
		user.setCookieSelector(rs.getString("CookieSelector"));
		user.setHashedCookieValidator(rs.getString("HashedCookieValidator"));

		return user;
	}

	@Override
	protected void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (getUserById != null && !getUserById.isClosed()) {
				getUserById.close();
			}
			if (getUserByEmail != null && !getUserByEmail.isClosed()) {
				getUserByEmail.close();
			}
			if (getUserByUserName != null && !getUserByUserName.isClosed()) {
				getUserByUserName.close();
			}
			if (getUserByCookieSelector != null && !getUserByCookieSelector.isClosed()) {
				getUserByCookieSelector.close();
			}
			if (getAllUsers != null && !getAllUsers.isClosed()) {
				getAllUsers.close();
			}
			if (addUser != null && !addUser.isClosed()) {
				addUser.close();
			}
			if (updateUser != null && !updateUser.isClosed()) {
				updateUser.close();
			}
			if (deleteUserById != null && !deleteUserById.isClosed()) {
				deleteUserById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
