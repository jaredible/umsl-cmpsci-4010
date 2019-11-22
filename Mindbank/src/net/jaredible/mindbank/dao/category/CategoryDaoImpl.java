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
	private PreparedStatement getAllCategories;
	private PreparedStatement addCategory;
	private PreparedStatement deleteCategoryById;
	private PreparedStatement getCategoryExistsByName;

	public CategoryDaoImpl() throws Exception {
		connection = DbUtil.openConnection();
		getCategoryById = connection.prepareStatement("SELECT * FROM Category WHERE ID = ?;");
		getAllCategories = connection.prepareStatement("SELECT * FROM Category;");
		addCategory = connection.prepareStatement("INSERT INTO Category (ID, Name, CreatedTime, CreatedByUserID) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		deleteCategoryById = connection.prepareStatement("DELETE FROM Category WHERE ID = ?;");
		getCategoryExistsByName = connection.prepareStatement("SELECT * FROM Category WHERE Name = ?;");
	}

	@Override
	public Category getCategoryById(long id) {
		ResultSet rs = null;

		try {
			getCategoryById.setLong(1, id);

			rs = getCategoryById.executeQuery();

			if (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("ID"));
				category.setName(rs.getString("Title"));
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
		try {
			ResultSet rs = getAllCategories.executeQuery();

			List<Category> categories = new ArrayList<Category>();

			while (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("ID"));
				category.setName(rs.getString("Title"));
				category.setCreatedTime(rs.getTimestamp("CreatedTime"));
				category.setCreatedByUserId(rs.getInt("CreatedByUserID"));

				categories.add(category);
			}

			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int addCategory(Category category) {
		ResultSet rs = null;

		try {
			addCategory.setNull(1, Types.INTEGER);
			addCategory.setString(2, category.getName());
			addCategory.setTimestamp(3, category.getCreatedTime());
			addCategory.setInt(4, category.getCreatedByUserId());

			int rowAffected = addCategory.executeUpdate();

			if (rowAffected == 1) {
				rs = addCategory.getGeneratedKeys();

				if (rs.next()) {
					return rs.getInt(1);
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
			deleteCategoryById.setLong(1, id);

			return deleteCategoryById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public boolean getCategoryExistsByName(String name) {
		try {
			getCategoryExistsByName.setString(1, name);

			ResultSet rs = getCategoryExistsByName.executeQuery();

			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
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
			if (getAllCategories != null && !getAllCategories.isClosed()) {
				getAllCategories.close();
			}
			if (addCategory != null && !addCategory.isClosed()) {
				addCategory.close();
			}
			if (deleteCategoryById != null && !deleteCategoryById.isClosed()) {
				deleteCategoryById.close();
			}
			if (getCategoryExistsByName != null && !getCategoryExistsByName.isClosed()) {
				getCategoryExistsByName.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
