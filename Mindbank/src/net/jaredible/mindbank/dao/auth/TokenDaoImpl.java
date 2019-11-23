package net.jaredible.mindbank.dao.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import net.jaredible.mindbank.model.AuthToken;
import net.jaredible.mindbank.util.DbUtil;

public class TokenDaoImpl implements TokenDao {

	private Connection connection;
	private PreparedStatement getTokenBySelector;
	private PreparedStatement addToken;
	private PreparedStatement deleteTokenById;

	@Override
	public AuthToken getTokenBySelector(String selector) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getTokenBySelector == null) {
				getTokenBySelector = connection.prepareStatement("SELECT * FROM Token WHERE Selector = ?;");
			}

			getTokenBySelector.setString(1, selector);

			rs = getTokenBySelector.executeQuery();

			if (rs.next()) {
				AuthToken token = new AuthToken();

				token.setId(rs.getInt("ID"));
				token.setUserId(rs.getInt("UserID"));
				token.setSelector(rs.getString("Selector"));
				token.setValidator(rs.getString("Validator"));
				token.setCreatedTime(rs.getTimestamp("CreatedTime"));

				return token;
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
	public long addToken(AuthToken token) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (addToken == null) {
				addToken = connection.prepareStatement("INSERT INTO Token (ID, UserID, Selector, Validator, CreatedTime) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			}

			addToken.setNull(1, Types.INTEGER);
			addToken.setInt(2, token.getUserId());
			addToken.setString(3, token.getSelector());
			addToken.setString(4, token.getValidator());
			addToken.setTimestamp(5, token.getCreatedTime());

			int rowAffected = addToken.executeUpdate();

			if (rowAffected == 1) {
				rs = addToken.getGeneratedKeys();

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
	public int deleteTokenById(long id) {
		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (deleteTokenById == null) {
				deleteTokenById = connection.prepareStatement("DELETE FROM Token WHERE ID = ?;");
			}

			deleteTokenById.setLong(1, id);

			return deleteTokenById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	protected void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (getTokenBySelector != null && !getTokenBySelector.isClosed()) {
				getTokenBySelector.close();
			}
			if (addToken != null && !addToken.isClosed()) {
				addToken.close();
			}
			if (deleteTokenById != null && !deleteTokenById.isClosed()) {
				deleteTokenById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
