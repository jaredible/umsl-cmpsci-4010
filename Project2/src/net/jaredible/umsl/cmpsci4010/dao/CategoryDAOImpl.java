package net.jaredible.umsl.cmpsci4010.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.umsl.cmpsci4010.model.Category;
import net.jaredible.umsl.cmpsci4010.util.DbConn;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public void addCategory(Category category) {
		Connection c = DbConn.getConnection();
		String sql = "INSERT INTO test VALUE(?, ?, ?)";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, category.getId());
			ps.setString(2, category.getName());
			ps.setString(3, category.getDescription());
			ps.executeUpdate();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category getCategory(int categoryId) {
		Category result = new Category();

		Connection c = DbConn.getConnection();
		String sql = "SELECT * FROM category WHERE id='" + categoryId + "'"; // TODO
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				result = new Category(id, name, description); // TODO
			}
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Category> getList() {
		List<Category> result = new ArrayList<Category>();

		Connection c = DbConn.getConnection();
		String sql = "SELECT * FROM category";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				result.add(new Category(id, name, description));
			}
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static void main(String[] args) {
		CategoryDAOImpl dao = new CategoryDAOImpl();
		Category c = new Category(8, "Test1", "Test2");
		dao.addCategory(c);
	}

}
