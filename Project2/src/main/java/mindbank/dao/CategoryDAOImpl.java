package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import main.java.mindbank.model.Category;
import main.java.mindbank.util.CategoryList;
import main.java.mindbank.util.DbConn;

public class CategoryDAOImpl implements CategoryDAO {

	private Connection connection;

	// create
	private PreparedStatement addCategory;

	// read
	private PreparedStatement getCategories;
	private PreparedStatement getCategoryById;
	private PreparedStatement getCategoryExistsByName;

	// update

	// delete
	private PreparedStatement deleteCategoryById;

	public CategoryDAOImpl() throws SQLException {
		this(DbConn.openConn());
	}

	public CategoryDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;

		init();
	}

	private void init() throws SQLException {
		// create
		addCategory = connection.prepareStatement("INSERT INTO Category (ID, Name, Description) VALUES (?, ?, ?);");

		// read
		getCategories = connection.prepareStatement("SELECT * FROM Category ORDER BY Name ASC;");
		getCategoryById = connection.prepareStatement("SELECT * FROM Category WHERE ID = ?;");
		getCategoryExistsByName = connection.prepareStatement("SELECT * FROM Category WHERE Name = ?;");

		// update

		// delete
		deleteCategoryById = connection.prepareStatement("DELETE FROM Category WHERE ID = ?;");
	}

	@Override
	public boolean getCategoryExistsById(int id) {
		return getCategory(id) != null;
	}

	@Override
	public boolean getCategoryExistsByName(String name) {
		try {
			getCategoryExistsByName.setString(1, name);
			ResultSet rs = getCategoryExistsByName.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public CategoryList getCategories() {
		try {
			ResultSet rs = getCategories.executeQuery();
			CategoryList cl = new CategoryList();
			while (rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("ID"));
				c.setName(rs.getString("Name"));
				cl.add(c);
			}
			return cl;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addCategory(Category category) {
		try {
			addCategory.setNull(1, Types.INTEGER);
			addCategory.setString(2, category.getName());
			addCategory.setString(3, category.getDescription());
			addCategory.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category getCategory(int id) {
		try {
			getCategoryById.setInt(1, id);
			ResultSet rs = getCategoryById.executeQuery();
			if (rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("ID"));
				c.setName(rs.getString("Name"));
				c.setDescription(rs.getString("Description"));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void deleteCategoryById(int id) {
		try {
			deleteCategoryById.setInt(1, id);
			deleteCategoryById.executeUpdate();
		} catch (Exception e) {
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
			// create
			if (!addCategory.isClosed()) {
				addCategory.close();
			}

			// read
			if (!getCategories.isClosed()) {
				getCategories.close();
			}
			if (!getCategoryById.isClosed()) {
				getCategoryById.close();
			}
			if (!getCategoryExistsByName.isClosed()) {
				getCategoryExistsByName.close();
			}

			// update

			// delete
			if (!deleteCategoryById.isClosed()) {
				deleteCategoryById.close();
			}

			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void finalize() {
		closeConnections();
	}

	public static void main(String[] args) {
	}

}
