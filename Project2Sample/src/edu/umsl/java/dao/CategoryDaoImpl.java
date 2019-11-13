package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.umsl.java.model.Category;
import edu.umsl.java.util.DbConn;

public class CategoryDaoImpl implements CategoryDao {

	private Connection connection;
	private PreparedStatement addCategory;
	private PreparedStatement getCategories;

	public CategoryDaoImpl() throws Exception {
		connection = DbConn.openConn();
		addCategory = connection.prepareStatement("INSERT INTO Category (ID, Name, Description, CreatedTime) VALUES (?, ?, ?, ?);");
		getCategories = connection.prepareStatement("SELECT * FROM Category;");
	}

	@Override
	public void addCategory(Category category) {
		try {
			addCategory.setNull(1, Types.INTEGER);
			addCategory.setString(2, category.getName());
			addCategory.setString(3, category.getDescription());
			addCategory.setTimestamp(4, category.getCreatedTime());
			addCategory.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Category> getCategories() {
		try {
			ResultSet rs = getCategories.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("ID"));
				category.setName(rs.getString("Name"));
				category.setDescription(rs.getString("Description"));
				category.setCreatedTime(rs.getTimestamp("CreatedTime"));
				categories.add(category);
			}
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
