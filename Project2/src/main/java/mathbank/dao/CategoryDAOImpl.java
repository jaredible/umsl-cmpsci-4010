package main.java.mathbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.mathbank.model.Category;
import main.java.mathbank.model.Subject;
import main.java.mathbank.util.DbConn;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public List<Category> getList() {
		List<Category> result = new ArrayList<Category>();

		Connection conn = DbConn.openConn();
		String sql = "SELECT * FROM category";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				result.add(new Category(id, name, description));
			}

			DbConn.closeConn(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Category> getList(Subject subject) {
		List<Category> result = new ArrayList<Category>();

		Connection conn = DbConn.openConn();
		String sql = "SELECT * FROM category";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				result.add(new Category(id, name, description));
			}

			DbConn.closeConn(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void addCategory(Category category) {
		Connection conn = DbConn.openConn();
		String sql = "INSERT INTO category VALUE(?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, category.getId());
			ps.setString(2, category.getName());
			ps.setString(3, category.getDescription());
			ps.executeUpdate();
			DbConn.closeConn(conn, ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category getCategory(int id) {
		Category result = new Category();

		Connection conn = DbConn.openConn();
		String sql = "SELECT * FROM category WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				result.setId(id);
				result.setName(name);
				result.setDescription(description);
			}

			DbConn.closeConn(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void updateCategory(Category categoy) {
		Connection conn = DbConn.openConn();
		String sql = "UPDATE category SET name = ?, description = ? WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoy.getName());
			ps.setString(2, categoy.getDescription());
			ps.setInt(3, categoy.getId());
			ps.executeUpdate();
			DbConn.closeConn(conn, ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCategory(int id) {
		Connection conn = DbConn.openConn();
		String sql = "DELETE FROM category WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			DbConn.closeConn(conn, ps, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CategoryDAOImpl dao = new CategoryDAOImpl();
		Category c = new Category(6, "Test1", "Test2");
		System.out.println("id: " + c.getId());
		System.out.println("name: " + c.getName());
		System.out.println("description: " + c.getDescription());
		dao.deleteCategory(c.getId());
		dao.addCategory(c);
		c.setName("Test3");
		c.setDescription("Test4");
		dao.updateCategory(c);
		Category cc = dao.getCategory(6);
		System.out.println("id: " + cc.getId());
		System.out.println("name: " + cc.getName());
		System.out.println("description: " + cc.getDescription());
	}

}
