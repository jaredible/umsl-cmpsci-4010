package net.jaredible.mindbank.dao.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.Category;
import net.jaredible.mindbank.util.DbUtil;

public class CategoryDaoImpl implements CategoryDao {

	private Connection connection;
	private PreparedStatement getCategoryById;
	private PreparedStatement getCategoryByName;
	private PreparedStatement getAllCategories;
	private PreparedStatement addCategory;
	private PreparedStatement deleteCategoryById;

	@Override
	public Category getCategoryById(long id) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getCategoryById == null) {
				getCategoryById = connection.prepareStatement("SELECT * FROM Category WHERE ID = ?;");
			}

			getCategoryById.setLong(1, id);

			rs = getCategoryById.executeQuery();

			if (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("ID"));
				category.setName(rs.getString("Name"));
				category.setCreatedTime(rs.getTimestamp("CreatedTime"));
				category.setCreatedByUserId(rs.getInt("CreatedByUserID"));

				return category;
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
	public Category getCategoryByName(String name) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getCategoryByName == null) {
				getCategoryByName = connection.prepareStatement("SELECT * FROM Category WHERE Name = ?;");
			}

			getCategoryByName.setString(1, name);

			rs = getCategoryByName.executeQuery();

			if (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("ID"));
				category.setName(rs.getString("Name"));
				category.setCreatedTime(rs.getTimestamp("CreatedTime"));
				category.setCreatedByUserId(rs.getInt("CreatedByUserID"));

				return category;
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
	public List<Category> getAllCategories() {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getAllCategories == null) {
				getAllCategories = connection.prepareStatement("SELECT * FROM Category;");
			}

			rs = getAllCategories.executeQuery();

			List<Category> categories = new ArrayList<Category>();

			while (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("ID"));
				category.setName(rs.getString("Name"));
				category.setCreatedTime(rs.getTimestamp("CreatedTime"));
				category.setCreatedByUserId(rs.getInt("CreatedByUserID"));

				categories.add(category);
			}

			return categories;
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
	public long addCategory(Category category) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (addCategory == null) {
				addCategory = connection.prepareStatement("INSERT INTO Category (ID, Name, CreatedTime, CreatedByUserID) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			}

			addCategory.setNull(1, Types.INTEGER);
			addCategory.setString(2, category.getName());
			addCategory.setTimestamp(3, category.getCreatedTime());
			addCategory.setInt(4, category.getCreatedByUserId());

			int rowAffected = addCategory.executeUpdate();

			if (rowAffected == 1) {
				rs = addCategory.getGeneratedKeys();

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
	public int deleteCategoryById(long id) {
		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (deleteCategoryById == null) {
				deleteCategoryById = connection.prepareStatement("DELETE FROM Category WHERE ID = ?;");
			}

			deleteCategoryById.setLong(1, id);

			return deleteCategoryById.executeUpdate();
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
			if (getCategoryById != null && !getCategoryById.isClosed()) {
				getCategoryById.close();
			}
			if (getCategoryByName != null && !getCategoryByName.isClosed()) {
				getCategoryByName.close();
			}
			if (getAllCategories != null && !getAllCategories.isClosed()) {
				getAllCategories.close();
			}
			if (addCategory != null && !addCategory.isClosed()) {
				addCategory.close();
			}
			if (deleteCategoryById != null && !deleteCategoryById.isClosed()) {
				deleteCategoryById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
