package edu.umsl.java.dao.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.umsl.java.model.Category;
import edu.umsl.java.util.DbUtil;

public class CategoryDaoImpl implements CategoryDao {

	private Connection connection;
	private PreparedStatement addCategory;
	private PreparedStatement getCategories;
	private PreparedStatement getCategoryIdExists;
	private PreparedStatement getNameExists;
	private PreparedStatement getCategoryById;
	private PreparedStatement updateCategory;
	private PreparedStatement deleteCategoryById;

	public CategoryDaoImpl() throws Exception {
		connection = DbUtil.openConn();
		addCategory = connection.prepareStatement("INSERT INTO Category (ID, Name, Description, CreatedTime, Edited, TrackingID) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		getCategories = connection.prepareStatement("SELECT * FROM Category ORDER BY CreatedTime DESC;");
		getCategoryIdExists = connection.prepareStatement("SELECT * FROM Category WHERE ID = ?;");
		getNameExists = connection.prepareStatement("SELECT * FROM Category WHERE Name = ?;");
		getCategoryById = connection.prepareStatement("SELECT * FROM Category WHERE ID = ?;");
		updateCategory = connection.prepareStatement("UPDATE Category SET Name = ?, Description = ?, Edited = TRUE, TrackingID = ? WHERE ID = ?;");
		deleteCategoryById = connection.prepareStatement("DELETE FROM Category WHERE ID = ?;");
	}

	@Override
	public int addCategory(Category category) {
		ResultSet rs = null;
		int resultId = 0;

		try {
			addCategory.setNull(1, Types.INTEGER);
			addCategory.setString(2, category.getName());
			addCategory.setString(3, category.getDescription());
			addCategory.setTimestamp(4, category.getCreatedTime());
			addCategory.setBoolean(5, category.isEdited());
			addCategory.setInt(6, category.getTrackingId());
			int rowAffected = addCategory.executeUpdate();
			if (rowAffected == 1) {
				rs = addCategory.getGeneratedKeys();
				if (rs.next()) {
					resultId = rs.getInt(1);
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

		return resultId;
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
				category.setEdited(rs.getBoolean("Edited"));
				categories.add(category);
			}
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean getCategoryIdExists(int id) {
		try {
			getCategoryIdExists.setInt(1, id);
			ResultSet rs = getCategoryIdExists.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean getNameExists(String name) {
		ResultSet rs = null;

		try {
			getNameExists.setString(1, name);
			rs = getNameExists.executeQuery();
			if (rs.next()) {
				return true;
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

		return false;
	}

	@Override
	public Category getCategoryById(int id) {
		ResultSet rs = null;

		try {
			getCategoryById.setInt(1, id);
			rs = getCategoryById.executeQuery();
			if (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("ID"));
				category.setName(rs.getString("Name"));
				category.setDescription(rs.getString("Description"));
				category.setCreatedTime(rs.getTimestamp("CreatedTime"));
				category.setEdited(rs.getBoolean("Edited"));
				category.setTrackingId(rs.getInt("TrackingID"));
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
	public void updateCategory(Category category) {
		try {
			updateCategory.setString(1, category.getName());
			updateCategory.setString(2, category.getDescription());
			updateCategory.setInt(3, category.getTrackingId());
			updateCategory.setInt(4, category.getId());
			updateCategory.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	protected void finalize() {
		try {
			if (addCategory != null && !addCategory.isClosed()) {
				addCategory.close();
			}
			if (getCategories != null && !getCategories.isClosed()) {
				getCategories.close();
			}
			if (getCategoryIdExists != null && !getCategoryIdExists.isClosed()) {
				getCategoryIdExists.close();
			}
			if (getNameExists != null && !getNameExists.isClosed()) {
				getNameExists.close();
			}
			if (getCategoryById != null && !getCategoryById.isClosed()) {
				getCategoryById.close();
			}
			if (updateCategory != null && !updateCategory.isClosed()) {
				updateCategory.close();
			}
			if (deleteCategoryById != null && !deleteCategoryById.isClosed()) {
				deleteCategoryById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
