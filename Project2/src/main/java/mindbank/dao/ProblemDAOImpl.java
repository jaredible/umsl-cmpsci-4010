package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.mindbank.model.Category;
import main.java.mindbank.model.Problem;
import main.java.mindbank.util.CategoryList;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.ProblemList;

public class ProblemDAOImpl implements ProblemDAO {

	private Connection connection;
	private PreparedStatement getProblems;

	public ProblemDAOImpl() throws SQLException {
		connection = DbConn.openConn();
		getProblems = connection.prepareStatement("SELECT * FROM Problem");
	}

	public ProblemDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;
		getProblems = connection.prepareStatement("SELECT Problem.*, User.UserName FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID ORDER BY Problem.CreatedTimestamp DESC");
	}

	@Override
	public ProblemList getProblems() {
		try {
			ResultSet rs = getProblems.executeQuery();
			ProblemList pl = new ProblemList();
			while (rs.next()) {
				Problem p = new Problem();
				p.setId(rs.getInt("ID"));
				p.setCategoryId(rs.getInt("CategoryID"));
				p.setTitle(rs.getString("Title"));
				p.setContent(rs.getString("Content"));
				p.setEdited(rs.getBoolean("Edited"));
				p.setCreatedByUserId(rs.getInt("CreatedByUserID"));
				p.setCreatedTimestamp(rs.getTimestamp("CreatedTimestamp"));
				pl.add(p);
			}
			return pl;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addProblem(Problem problem) {
	}

	@Override
	public Problem getProblem(int id) {
		return null;
	}

	@Override
	public void updateProblem(Problem problem) {
	}

	@Override
	public void deleteProblem(int id) {
	}

	protected void finalize() {
		try {
			if (!getProblems.isClosed()) {
				getProblems.close();
			}
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static void main(String[] args) {
	}

}
