package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.mindbank.model.Category;
import main.java.mindbank.util.CategoryList;
import main.java.mindbank.util.DbConn;

public class CategoryDAOImpl implements CategoryDAO {

	private Connection connection;
	private PreparedStatement getCategories;
	private PreparedStatement getCategoryById;

	public CategoryDAOImpl() throws SQLException {
		this(DbConn.openConn());
	}

	public CategoryDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;

		init();
	}

	private void init() throws SQLException {
		getCategories = connection.prepareStatement("SELECT * FROM Category;");
		getCategoryById = connection.prepareStatement("SELECT * FROM Category WHERE ID = ?;");
	}

	@Override
	public boolean getCategoryExistsById(int id) {
		return getCategory(id) != null;
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
				c.setDescription(rs.getString("Description"));
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
	public void updateCategory(Category categoy) {
	}

	@Override
	public void deleteCategory(int id) {
	}

	public Connection getConnection() {
		return connection;
	}

	protected void finalize() {
		try {
			if (!getCategoryById.isClosed()) {
				getCategoryById.close();
			}
			if (!getCategories.isClosed()) {
				getCategories.close();
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
