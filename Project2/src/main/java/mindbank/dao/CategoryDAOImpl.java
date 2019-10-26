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

	public CategoryDAOImpl() throws SQLException {
		connection = DbConn.openConn();
		getCategories = connection.prepareStatement("SELECT * FROM Category;");
	}

	public CategoryDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;
		getCategories = connection.prepareStatement("SELECT * FROM Category;");
	}

	@Override
	public CategoryList getCategories() {
		try {
			ResultSet rs = getCategories.executeQuery();
			CategoryList cl = new CategoryList();
			while (rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("ID"));
				c.setSubjectId(rs.getInt("SubjectID"));
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
			getCategories.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

}
