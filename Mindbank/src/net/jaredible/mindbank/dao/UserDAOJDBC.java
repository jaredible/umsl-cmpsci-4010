package net.jaredible.mindbank.dao;

import static net.jaredible.mindbank.dao.DAOUtil.preparedStatement;
import static net.jaredible.mindbank.dao.DAOUtil.toSqlDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.User;

public class UserDAOJDBC implements UserDAO {

	private static final String SQL_FIND_BY_ID = "SELECT * FROM User WHERE UserID = ?";
	private static final String SQL_FIND_BY_USERNAME = "SELECT * FROM User WHERE UserName = ?";
	private static final String SQL_FIND_BY_COOKIE_SELECTOR = "SELECT * FROM User WHERE CookieSelector = ?";
	private static final String SQL_LIST_ORDER_BY_USERNAME = "SELECT * FROM User ORDER BY UserName ASC";
	private static final String SQL_INSERT = "INSERT INTO User (Email, UserName, Name, Bio, StatusEmoji, StatusText, RegisteredTime, PassWordSalt, PassWordHash) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE User SET Name = ?, Bio = ?, StatusEmoji = ?, StatusText = ?, LastLoginTime = ?, CookieSecretKey = ?, CookieSelector = ?, HashedCookieValidator = ? WHERE UserID = ?";
	private static final String SQL_DELETE = "DELETE FROM User WHERE UserID = ?";
	private static final String SQL_EXIST_EMAIL = "SELECT UserID FROM User WHERE Email = ?";
	private static final String SQL_EXIST_USERNAME = "SELECT UserID FROM User WHERE UserName = ?";

	private DAOFactory daoFactory;

	UserDAOJDBC(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public User find(Long id) throws DAOException {
		return find(SQL_FIND_BY_ID, id);
	}

	@Override
	public User find(String userName) throws DAOException {
		return find(SQL_FIND_BY_USERNAME, userName);
	}

	@Override
	public User find(String cookieSelector, int dummy) throws DAOException {
		return find(SQL_FIND_BY_COOKIE_SELECTOR, cookieSelector);
	}

	private User find(String sql, Object... values) throws DAOException {
		User user = null;

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, sql, false, values); ResultSet resultSet = statement.executeQuery();) {
			if (resultSet.next()) {
				user = map(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public List<User> list() throws DAOException {
		List<User> users = new ArrayList<User>();

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_USERNAME); ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				users.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return users;
	}

	@Override
	public void create(User user) throws IllegalArgumentException, DAOException {
		if (user.getId() != null) {
			throw new IllegalArgumentException("User is already created, the user ID is not null.");
		}

		Object[] values = { user.getEmail(), user.getUserName(), user.getName(), user.getBio(), user.getStatusEmoji(), user.getStatusText(), toSqlDate(user.getRegisteredTime()), user.getPassWordSalt(), user.getPassWordHash() };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_INSERT, true, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Creating user failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setId(generatedKeys.getLong(1));
				} else {
					throw new DAOException("Creating user failed, no generated key obtained.");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void update(User user) throws IllegalArgumentException, DAOException {
		if (user.getId() == null) {
			throw new IllegalArgumentException("User is not created yet, the user ID is null.");
		}

		Object[] values = { user.getName(), user.getBio(), user.getStatusEmoji(), user.getStatusText(), toSqlDate(user.getLastLoginTime()), user.getCookieSecretKey(), user.getCookieSelector(), user.getHashedCookieValidator(), user.getId() };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_UPDATE, false, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Updating user failed, no rows affected.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(User user) throws DAOException {
		Object[] values = { user.getId() };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_DELETE, false, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Deleting user failed, no rows affected.");
			} else {
				user.setId(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean existEmail(String email) throws DAOException {
		Object[] values = { email };

		boolean exist = false;

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_EXIST_EMAIL, false, values); ResultSet resultSet = statement.executeQuery()) {
			exist = resultSet.next();
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return exist;
	}

	@Override
	public boolean existUserName(String userName) throws DAOException {
		Object[] values = { userName };

		boolean exist = false;

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_EXIST_USERNAME, false, values); ResultSet resultSet = statement.executeQuery()) {
			exist = resultSet.next();
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return exist;
	}

	private static User map(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong("UserID"));
		user.setEmail(resultSet.getString("Email"));
		user.setUserName(resultSet.getString("UserName"));
		user.setName(resultSet.getString("Name"));
		user.setBio(resultSet.getString("Bio"));
		user.setStatusEmoji(resultSet.getString("StatusEmoji"));
		user.setStatusText(resultSet.getString("StatusText"));
		user.setRegisteredTime(resultSet.getTimestamp("RegisteredTime"));
		user.setLastLoginTime(resultSet.getTimestamp("LastLoginTime"));
		user.setPassWordSalt(resultSet.getString("PassWordSalt"));
		user.setPassWordHash(resultSet.getString("PassWordHash"));
		user.setCookieSecretKey(resultSet.getString("CookieSecretKey"));
		user.setCookieSelector(resultSet.getString("CookieSelector"));
		user.setHashedCookieValidator(resultSet.getString("HashedCookieValidator"));
		return user;
	}

}
