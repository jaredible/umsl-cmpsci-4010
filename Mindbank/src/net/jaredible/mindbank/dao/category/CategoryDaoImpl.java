package net.jaredible.mindbank.dao.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.category.CategoryModel;
import net.jaredible.mindbank.util.DbUtil;

public class CategoryDaoImpl implements CategoryDao {

	private Connection connection;
	private PreparedStatement getCategoryById;
	private PreparedStatement getCategoryByName;
	private PreparedStatement getAllCategories;
	private PreparedStatement getCategoriesByProblemId;
	private PreparedStatement addCategory;

	@Override
	public CategoryModel getCategoryById(long id) {
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
				return extractCategoryFromResultSet(rs);
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
	public CategoryModel getCategoryByName(String name) {
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
				return extractCategoryFromResultSet(rs);
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
	public List<CategoryModel> getAllCategories() {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getAllCategories == null) {
				getAllCategories = connection.prepareStatement("SELECT * FROM Category;");
			}

			rs = getAllCategories.executeQuery();

			List<CategoryModel> categories = new ArrayList<CategoryModel>();

			while (rs.next()) {
				categories.add(extractCategoryFromResultSet(rs));
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
	public List<CategoryModel> getCategoriesByProblemId(long id) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getCategoriesByProblemId == null) {
				getCategoriesByProblemId = connection.prepareStatement("SELECT Category.* FROM ProblemCategory LEFT OUTER JOIN Category ON Category.ID = ProblemCategory.CategoryID WHERE ProblemCategory.ProblemID = ?;");
			}

			getCategoriesByProblemId.setLong(1, id);

			rs = getCategoriesByProblemId.executeQuery();

			List<CategoryModel> categories = new ArrayList<CategoryModel>();

			while (rs.next()) {
				categories.add(extractCategoryFromResultSet(rs));
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
	public long addCategory(CategoryModel category) {
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
			addCategory.setLong(4, category.getCreatedByUserId());

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

	private CategoryModel extractCategoryFromResultSet(ResultSet rs) throws SQLException {
		CategoryModel category = new CategoryModel();

		category.setId(rs.getInt("ID"));
		category.setName(rs.getString("Name"));
		category.setCreatedTime(rs.getTimestamp("CreatedTime"));
		category.setCreatedByUserId(rs.getInt("CreatedByUserID"));

		return category;
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
			if (getCategoriesByProblemId != null && !getCategoriesByProblemId.isClosed()) {
				getCategoriesByProblemId.close();
			}
			if (addCategory != null && !addCategory.isClosed()) {
				addCategory.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
