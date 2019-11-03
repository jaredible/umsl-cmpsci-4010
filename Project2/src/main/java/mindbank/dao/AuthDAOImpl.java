package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import main.java.mindbank.model.AuthToken;
import main.java.mindbank.util.DbConn;

public class AuthDAOImpl implements AuthDAO {

	private Connection connection;
	private PreparedStatement getBySelector;
	private PreparedStatement createWithToken;
	private PreparedStatement updateWithToken;
	private PreparedStatement deleteBySelector;

	public AuthDAOImpl() throws SQLException {
		this(DbConn.openConn());
	}

	public AuthDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;

		init();
	}

	private void init() throws SQLException {
		createWithToken = connection.prepareStatement("INSERT INTO Auth (ID, UserID, Selector, Validator) VALUES (?, ?, ?, ?)");
		getBySelector = connection.prepareStatement("SELECT * FROM Auth WHERE Selector = ?");
		updateWithToken = connection.prepareStatement("UPDATE Auth SET Selector = ?, Validator = ? WHERE ID = ?");
		deleteBySelector = connection.prepareStatement("DELETE FROM Auth WHERE ID = ?");
	}

	@Override
	public void createWithToken(AuthToken authToken) {
		try {
			createWithToken.setNull(1, Types.INTEGER);
			createWithToken.setInt(2, authToken.getUserId());
			createWithToken.setString(3, authToken.getSelector());
			createWithToken.setString(4, authToken.getValidator());
			createWithToken.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AuthToken getBySelector(String selector) {
		try {
			getBySelector.setString(1, selector);
			ResultSet rs = getBySelector.executeQuery();
			if (rs.next()) {
				AuthToken at = new AuthToken();
				at.setId(rs.getInt("ID"));
				at.setUserId(rs.getInt("UserID"));
				at.setSelector(rs.getString("Selector"));
				at.setValidator(rs.getString("Validator"));
				return at;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateWithToken(AuthToken authToken) {
		try {
			updateWithToken.setString(1, authToken.getSelector());
			updateWithToken.setString(2, authToken.getValidator());
			updateWithToken.setInt(3, authToken.getId());
			updateWithToken.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(int tokenId) {
		try {
			deleteBySelector.setInt(1, tokenId);
			deleteBySelector.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void finalize() {
		try {
			if (!deleteBySelector.isClosed()) {
				deleteBySelector.close();
			}
			if (!updateWithToken.isClosed()) {
				updateWithToken.close();
			}
			if (!createWithToken.isClosed()) {
				createWithToken.close();
			}
			if (!getBySelector.isClosed()) {
				getBySelector.close();
			}
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static void main(String[] args) {
	}

}
