package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import main.java.mindbank.model.Problem;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.ProblemList;

public class ProblemDAOImpl implements ProblemDAO {

	private Connection connection;
	private PreparedStatement getProblemsWithLimit;
	private PreparedStatement getProblemsByCategoryIdWithLimit;
	private PreparedStatement addProblem;
	private PreparedStatement getProblemById;
	private PreparedStatement deleteProblemById;

	public ProblemDAOImpl() throws SQLException {
		this(DbConn.openConn());
	}

	public ProblemDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;

		init();
	}

	private void init() throws SQLException {
		getProblemsWithLimit = connection.prepareStatement("SELECT Problem.*, User.UserName FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID ORDER BY Problem.CreatedTimestamp DESC LIMIT ?, ?;");
		getProblemsByCategoryIdWithLimit = connection.prepareStatement("SELECT Problem.*, User.UserName FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID WHERE Problem.CategoryID = ? ORDER BY Problem.CreatedTimestamp DESC LIMIT ?, ?;");
		addProblem = connection.prepareStatement("INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (?, ?, ?, ?, ?, ?, ?);");
		getProblemById = connection.prepareStatement("SELECT Problem.*, User.UserName FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID WHERE Problem.ID = ?;");
		deleteProblemById = connection.prepareStatement("DELETE FROM Problem WHERE ID = ?;");
	}

	@Override
	public ProblemList getProblemsWithLimit(int offset, int rowCount) {
		try {
			getProblemsWithLimit.setInt(1, offset);
			getProblemsWithLimit.setInt(2, rowCount);
			ResultSet rs = getProblemsWithLimit.executeQuery();
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ProblemList getProblemsByCategoryIdWithLimit(int categoryId, int offset, int rowCount) {
		try {
			getProblemsByCategoryIdWithLimit.setInt(1, categoryId);
			getProblemsByCategoryIdWithLimit.setInt(2, offset);
			getProblemsByCategoryIdWithLimit.setInt(3, rowCount);
			ResultSet rs = getProblemsByCategoryIdWithLimit.executeQuery();
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addProblem(Problem problem) {
		try {
			addProblem.setNull(1, Types.INTEGER);
			addProblem.setInt(2, problem.getCategoryId());
			addProblem.setString(3, problem.getTitle());
			addProblem.setString(4, problem.getContent());
			addProblem.setBoolean(5, problem.isEdited());
			addProblem.setInt(6, problem.getCreatedByUserId());
			addProblem.setTimestamp(7, problem.getCreatedTimestamp());
			addProblem.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Problem getProblemById(int id) {
		try {
			getProblemById.setInt(1, id);
			ResultSet rs = getProblemById.executeQuery();
			if (rs.next()) {
				Problem p = new Problem();
				p.setId(rs.getInt("ID"));
				p.setCategoryId(rs.getInt("CategoryId"));
				p.setTitle(rs.getString("Title"));
				p.setContent(rs.getString("Content"));
				p.setEdited(rs.getBoolean("Edited"));
				p.setCreatedByUserId(rs.getInt("CreatedByUserID"));
				p.setCreatedTimestamp(rs.getTimestamp("CreatedTimestamp"));
				return p;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void deleteProblemById(int id) {
		try {
			deleteProblemById.setInt(1, id);
			deleteProblemById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void finalize() {
		try {
			if (!getProblemsWithLimit.isClosed()) {
				getProblemsWithLimit.close();
			}
			if (!getProblemsByCategoryIdWithLimit.isClosed()) {
				getProblemsByCategoryIdWithLimit.close();
			}
			if (!addProblem.isClosed()) {
				addProblem.close();
			}
			if (!getProblemById.isClosed()) {
				getProblemById.close();
			}
			if (!deleteProblemById.isClosed()) {
				deleteProblemById.close();
			}
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static void main(String[] args) {
	}

}
