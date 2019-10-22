package net.jaredible.umsl.cmpsci4010.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import net.jaredible.umsl.cmpsci4010.model.Category;
import net.jaredible.umsl.cmpsci4010.util.DbConn;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public void addCategory(Category category) {
		Connection c = DbConn.getConnection();
		String sql = "INSERT INTO category VALUE(?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ps.setInt(1, category.getId());
			ps.setString(1, category.getName());
			ps.setString(1, category.getDescription());
			ps.executeUpdate();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getCategory(int id) {
		return 0;
	}

	@Override
	public List<Category> getList() {
		return null;
	}

}
