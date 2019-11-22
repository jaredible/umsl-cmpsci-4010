package net.jaredible.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import net.jaredible.mindbank.model.Auth;
import net.jaredible.mindbank.util.DbConn;

public class AuthDAOImpl implements AuthDAO {

	private Connection connection;
	private PreparedStatement updateSelectorById;
	private PreparedStatement updateValidatorById;
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
		updateSelectorById = connection.prepareStatement("UPDATE Auth SET Selector = ? WHERE ID = ?;");
		updateValidatorById = connection.prepareStatement("UPDATE Auth SET Validator = ? WHERE ID = ?;");
		createWithToken = connection.prepareStatement("INSERT INTO Auth (ID, UserID, Selector, Validator) VALUES (?, ?, ?, ?);");
		getBySelector = connection.prepareStatement("SELECT * FROM Auth WHERE Selector = ?;");
		updateWithToken = connection.prepareStatement("UPDATE Auth SET Selector = ?, Validator = ? WHERE ID = ?;");
		deleteBySelector = connection.prepareStatement("DELETE FROM Auth WHERE ID = ?;");
	}

	@Override
	public void updateSelectorById(int id, String selector) {
		try {
			updateSelectorById.setString(1, selector);
			updateSelectorById.setInt(2, id);
			updateSelectorById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateValidatorById(int id, String validator) {
		try {
			updateValidatorById.setString(1, validator);
			updateValidatorById.setInt(2, id);
			updateValidatorById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createWithToken(Auth authToken) {
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
	public Auth getBySelector(String selector) {
		try {
			getBySelector.setString(1, selector);
			ResultSet rs = getBySelector.executeQuery();
			if (rs.next()) {
				Auth at = new Auth();
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
	public void updateWithToken(Auth authToken) {
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

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void closeConnections() {
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

		deleteBySelector = null;
		updateWithToken = null;
		createWithToken = null;
		getBySelector = null;
		connection = null;
	}

	protected void finalize() {
		closeConnections();
	}

	public static void main(String[] args) {
	}

}
