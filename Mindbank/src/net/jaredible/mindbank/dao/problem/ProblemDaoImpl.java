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
	private PreparedStatement getProblemsByFields;
	private PreparedStatement addProblem;
	private PreparedStatement updateProblem;
	private PreparedStatement deleteProblemById;

	@Override
	public Problem getProblemById(long id) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getProblemById == null) {
				getProblemById = connection.prepareStatement("SELECT * FROM Problem WHERE ID = ?;");
			}

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
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getAllProblems == null) {
				getAllProblems = connection.prepareStatement("SELECT * FROM Problem;");
			}

			rs = getAllProblems.executeQuery();

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
	public List<Problem> getProblemsByFields(String titleLike, String categoryIdsRegex, String tagIdsRegex, String contentLike, String dateCreatedStart, String dateCreatedEnd, String userIdsRegex) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getProblemsByFields == null) {
				getProblemsByFields = connection.prepareStatement("SELECT Problem.* FROM Problem LEFT OUTER JOIN ProblemCategory ON ProblemCategory.ProblemID = Problem.ID LEFT OUTER JOIN ProblemTag ON ProblemTag.ProblemID = Problem.ID WHERE Problem.Title LIKE ? AND ProblemCategory.CategoryID REGEXP ? AND ProblemTag.TagID REGEXP ? AND Problem.Content LIKE ? AND Problem.CreatedTime BETWEEN ? AND ? AND Problem.CreatedByUserID REGEXP ? GROUP BY Problem.ID;");
			}

			getProblemsByFields.setString(1, "%" + titleLike + "%");
			getProblemsByFields.setString(2, categoryIdsRegex);
			getProblemsByFields.setString(3, tagIdsRegex);
			getProblemsByFields.setString(4, "%" + contentLike + "%");
			getProblemsByFields.setString(5, dateCreatedStart);
			getProblemsByFields.setString(6, dateCreatedEnd);
			getProblemsByFields.setString(7, userIdsRegex);

			System.out.println(getProblemsByFields.toString());

			rs = getProblemsByFields.executeQuery();

			List<Problem> problems = new ArrayList<Problem>();

			while (rs.next()) {
				Problem problem = new Problem();

				problem.setId(rs.getInt("Problem.ID"));
				problem.setTitle(rs.getString("Problem.Title"));
				problem.setContent(rs.getString("Problem.Content"));
				problem.setEdited(rs.getBoolean("Problem.Edited"));
				problem.setCreatedTime(rs.getTimestamp("Problem.CreatedTime"));
				problem.setLastEditedTime(rs.getTimestamp("Problem.LastEditedTime"));
				problem.setCreatedByUserId(rs.getInt("Problem.CreatedByUserId"));

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
	public long addProblem(Problem problem) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (addProblem == null) {
				addProblem = connection.prepareStatement("INSERT INTO Problem (ID, Title, Content, Edited, CreatedTime, LastEditedTime, CreatedByUserID) VALUES (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			}

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

	@Override
	public int updateProblem(Problem problem) {
		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (updateProblem == null) {
				updateProblem = connection.prepareStatement("UPDATE Problem SET ID = ?, Title = ?, Content = ?, Edited = ?, CreatedTime = ?, LastEditedTime = ?, CreatedByUserID = ? WHERE ID = ?;");
			}

			updateProblem.setLong(1, problem.getId());
			updateProblem.setString(2, problem.getTitle());
			updateProblem.setString(3, problem.getContent());
			updateProblem.setBoolean(4, problem.isEdited());
			updateProblem.setTimestamp(5, problem.getCreatedTime());
			updateProblem.setTimestamp(6, problem.getLastEditedTime());
			updateProblem.setInt(7, problem.getCreatedByUserId());

			updateProblem.setLong(8, problem.getId());

			return updateProblem.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public int deleteProblemById(long id) {
		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (deleteProblemById == null) {
				deleteProblemById = connection.prepareStatement("DELETE FROM Problem WHERE ID = ?;");
			}

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
