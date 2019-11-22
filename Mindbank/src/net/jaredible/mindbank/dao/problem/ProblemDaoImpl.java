package net.jaredible.mindbank.dao.problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.util.DbUtil;

public class ProblemDaoImpl implements ProblemDao {

	private Connection connection;
	private PreparedStatement getProblemById;
	private PreparedStatement getAllProblems;
	private PreparedStatement addProblem;
	private PreparedStatement updateProblem;
	private PreparedStatement deleteProblemById;

	public ProblemDaoImpl() throws Exception {
		connection = DbUtil.openConnection();
		getProblemById = connection.prepareStatement("SELECT * FROM Problem WHERE ID = ?;");
		getAllProblems = connection.prepareStatement("SELECT * FROM Problem;");
		addProblem = connection.prepareStatement("INSERT INTO Problem (ID, Title, Content, Edited, CreatedTime, LastEditedTime, CreatedByUserID) VALUES (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		updateProblem = connection.prepareStatement("UPDATE Problem SET Title = ?, Content = ?, Edited = ?, CreatedTime = ?, LastEditedTime = ?, CreatedByUserID = ? WHERE ID = ?;");
		deleteProblemById = connection.prepareStatement("DELETE FROM Problem WHERE ID = ?;");
	}

	@Override
	public Problem getProblemById(long id) {
		ResultSet rs = null;

		try {
			getProblemById.setLong(1, id);

			rs = getProblemById.executeQuery();

			if (rs.next()) {
				Problem problem = new Problem();

				problem.setId(rs.getInt("ID"));
				problem.setTitle(rs.getString("Title"));
				problem.setContent(rs.getString("Content"));
				problem.setEdited(rs.getBoolean("Edited"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problem.setLastEditedTime(rs.getTimestamp("LastEditedTime"));
				problem.setCreatedByUserId(rs.getInt("CreatedByUserID"));

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
	public List<Problem> getAllProblems() {
		try {
			ResultSet rs = getAllProblems.executeQuery();

			List<Problem> problems = new ArrayList<Problem>();

			while (rs.next()) {
				Problem problem = new Problem();

				problem.setId(rs.getInt("ID"));
				problem.setTitle(rs.getString("Title"));
				problem.setContent(rs.getString("Content"));
				problem.setEdited(rs.getBoolean("Edited"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problem.setLastEditedTime(rs.getTimestamp("LastEditedTime"));
				problem.setCreatedByUserId(rs.getInt("CreatedByUserId"));

				problems.add(problem);
			}

			return problems;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int addProblem(Problem problem) {
		ResultSet rs = null;

		try {
			addProblem.setNull(1, Types.INTEGER);
			addProblem.setString(2, problem.getTitle());
			addProblem.setString(3, problem.getContent());
			addProblem.setBoolean(4, problem.isEdited());
			addProblem.setTimestamp(5, problem.getCreatedTime());
			addProblem.setTimestamp(6, problem.getLastEditedTime());
			addProblem.setInt(7, problem.getCreatedByUserId());

			int rowAffected = addProblem.executeUpdate();

			if (rowAffected == 1) {
				rs = addProblem.getGeneratedKeys();

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
	public int updateProblem(Problem problem, int[] params) {
		try {
			for (int param : params) {
				switch (param) {
				case Problem.TITLE:
					updateProblem.setString(1, problem.getTitle());
					break;
				case Problem.CONTENT:
					updateProblem.setString(2, problem.getContent());
					break;
				case Problem.EDITED:
					updateProblem.setBoolean(3, problem.isEdited());
					break;
				case Problem.CREATED_TIME:
					updateProblem.setTimestamp(4, problem.getCreatedTime());
					break;
				case Problem.LAST_EDITED_TIME:
					updateProblem.setTimestamp(5, problem.getLastEditedTime());
					break;
				case Problem.CREATED_BY_USER_ID:
					updateProblem.setInt(6, problem.getCreatedByUserId());
					break;
				default:
					throw new Exception("Unknown parameter!");
				}
			}

			updateProblem.setLong(7, problem.getId());

			return updateProblem.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public int deleteProblemById(long id) {
		try {
			deleteProblemById.setLong(1, id);

			return deleteProblemById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public boolean getProblemExistsById(long id) {
		return getProblemById(id) != null;
	}

	@Override
	protected void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (getProblemById != null && !getProblemById.isClosed()) {
				getProblemById.close();
			}
			if (getAllProblems != null && !getAllProblems.isClosed()) {
				getAllProblems.close();
			}
			if (addProblem != null && !addProblem.isClosed()) {
				addProblem.close();
			}
			if (updateProblem != null && !updateProblem.isClosed()) {
				updateProblem.close();
			}
			if (deleteProblemById != null && !deleteProblemById.isClosed()) {
				deleteProblemById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
