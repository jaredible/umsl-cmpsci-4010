package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.umsl.java.model.Problem;
import edu.umsl.java.util.DbConn;

public class ProblemDaoImpl implements ProblemDao {

	private Connection connection;
	private PreparedStatement addProblem;
	private PreparedStatement getProblems;
	private PreparedStatement getProblemsByCategoryId;
	private PreparedStatement getProblemIdExists;
	private PreparedStatement getTitleExists;
	private PreparedStatement getProblemById;
	private PreparedStatement updateProblem;

	public ProblemDaoImpl() throws Exception {
		connection = DbConn.openConn();
		addProblem = connection.prepareStatement("INSERT INTO Problem (ID, CategoryID, Title, Content, CreatedTime, TrackingId) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		getProblems = connection.prepareStatement("SELECT * FROM Problem ORDER BY CreatedTime DESC;");
		getProblemsByCategoryId = connection.prepareStatement("SELECT * FROM Problem WHERE CategoryID = ? ORDER BY CreatedTime DESC;");
		getProblemIdExists = connection.prepareStatement("SELECT * FROM Problem WHERE ID = ?;");
		getTitleExists = connection.prepareStatement("SELECT * FROM Problem WHERE Title = ?;");
		getProblemById = connection.prepareStatement("SELECT * FROM Problem WHERE ID = ?;");
		updateProblem = connection.prepareStatement("UPDATE Problem SET Title = ?, CategoryID = ?, Content = ?, Edited = TRUE, TrackingID = ? WHERE ID = ?;");
	}

	@Override
	public int addProblem(Problem problem) {
		ResultSet rs = null;
		int resultId = 0;

		try {
			addProblem.setNull(1, Types.INTEGER);
			addProblem.setInt(2, problem.getCategoryId());
			addProblem.setString(3, problem.getTitle());
			addProblem.setString(4, problem.getContent());
			addProblem.setTimestamp(5, problem.getCreatedTime());
			addProblem.setInt(6, problem.getTrackingId());
			int rowAffected = addProblem.executeUpdate();
			if (rowAffected == 1) {
				rs = addProblem.getGeneratedKeys();
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
	public List<Problem> getProblems() {
		ResultSet rs = null;

		try {
			rs = getProblems.executeQuery();
			List<Problem> problems = new ArrayList<Problem>();
			while (rs.next()) {
				Problem problem = new Problem();
				problem.setId(rs.getInt("ID"));
				problem.setCategoryId(rs.getInt("CategoryID"));
				problem.setTitle(rs.getString("Title"));
				problem.setContent(rs.getString("Content"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problem.setEdited(rs.getBoolean("Edited"));
				problems.add(problem);
			}
			return problems;
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
	public List<Problem> getProblemsByCategoryId(int categoryId) {
		ResultSet rs = null;

		try {
			getProblemsByCategoryId.setInt(1, categoryId);
			rs = getProblemsByCategoryId.executeQuery();
			List<Problem> problems = new ArrayList<Problem>();
			while (rs.next()) {
				Problem problem = new Problem();
				problem.setId(rs.getInt("ID"));
				problem.setCategoryId(rs.getInt("CategoryID"));
				problem.setTitle(rs.getString("Title"));
				problem.setContent(rs.getString("Content"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problem.setEdited(rs.getBoolean("Edited"));
				problems.add(problem);
			}
			return problems;
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
	public boolean getProblemIdExists(int id) {
		ResultSet rs = null;

		try {
			getProblemIdExists.setInt(1, id);
			rs = getProblemIdExists.executeQuery();
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
	public boolean getTitleExists(String title) {
		ResultSet rs = null;

		try {
			getTitleExists.setString(1, title);
			rs = getTitleExists.executeQuery();
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
	public Problem getProblemById(int id) {
		ResultSet rs = null;

		try {
			getProblemById.setInt(1, id);
			rs = getProblemById.executeQuery();
			if (rs.next()) {
				Problem problem = new Problem();
				problem.setId(rs.getInt("ID"));
				problem.setCategoryId(rs.getInt("CategoryID"));
				problem.setTitle(rs.getString("Title"));
				problem.setContent(rs.getString("Content"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problem.setEdited(rs.getBoolean("Edited"));
				problem.setTrackingId(rs.getInt("TrackingID"));
				return problem;
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
	public void updateProblem(Problem problem) {
		try {
			updateProblem.setString(1, problem.getTitle());
			updateProblem.setInt(2, problem.getCategoryId());
			updateProblem.setString(3, problem.getContent());
			updateProblem.setInt(4, problem.getTrackingId());
			updateProblem.setInt(5, problem.getId());
			updateProblem.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void finalize() {
		try {
			if (addProblem != null && !addProblem.isClosed()) {
				addProblem.close();
			}
			if (getProblems != null && !getProblems.isClosed()) {
				getProblems.close();
			}
			if (getProblemsByCategoryId != null && !getProblemsByCategoryId.isClosed()) {
				getProblemsByCategoryId.close();
			}
			if (getProblemIdExists != null && !getProblemIdExists.isClosed()) {
				getProblemIdExists.close();
			}
			if (getTitleExists != null && !getTitleExists.isClosed()) {
				getTitleExists.close();
			}
			if (getProblemById != null && !getProblemById.isClosed()) {
				getProblemById.close();
			}
			if (updateProblem != null && !updateProblem.isClosed()) {
				updateProblem.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
