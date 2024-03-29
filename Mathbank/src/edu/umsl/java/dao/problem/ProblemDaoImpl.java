package edu.umsl.java.dao.problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.umsl.java.model.Problem;
import edu.umsl.java.util.DbUtil;

public class ProblemDaoImpl implements ProblemDao {

	private Connection connection;
	private PreparedStatement addProblem;
	private PreparedStatement getProblems;
	private PreparedStatement getProblemsByCategoryId;
	private PreparedStatement getProblemsByTagNames;
	private PreparedStatement getProblemsByCategoryIdOrTagNames;
	private PreparedStatement getProblemIdExists;
	private PreparedStatement getTitleExists;
	private PreparedStatement getProblemById;
	private PreparedStatement updateProblem;
	private PreparedStatement incrementViewCountById;
	private PreparedStatement deleteProblemById;
	private PreparedStatement deleteProblemTagById;

	public ProblemDaoImpl() throws Exception {
		connection = DbUtil.openConnection();
		addProblem = connection.prepareStatement("INSERT INTO Problem (ID, CategoryID, Title, Content, PasswordHash, CreatedTime, LastEditTime, TrackingID) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		getProblems = connection.prepareStatement("SELECT * FROM Problem ORDER BY CreatedTime DESC;");
		getProblemsByCategoryId = connection.prepareStatement("SELECT * FROM Problem WHERE CategoryID = ? ORDER BY CreatedTime DESC;");
		getProblemsByTagNames = connection.prepareStatement("SELECT * FROM Problem LEFT OUTER JOIN ProblemTag ON ProblemTag.ProblemID = Problem.ID LEFT OUTER JOIN Tag ON Tag.ID = ProblemTag.TagID WHERE Tag.Name REGEXP ? ORDER BY Problem.CreatedTime DESC;");
		getProblemsByCategoryIdOrTagNames = connection.prepareStatement("SELECT * FROM Problem LEFT OUTER JOIN ProblemTag ON ProblemTag.ProblemID = Problem.ID LEFT OUTER JOIN Tag ON Tag.ID = ProblemTag.TagID WHERE Problem.CategoryID LIKE ? OR Tag.Name REGEXP ? ORDER BY Problem.CreatedTime DESC;");
		getProblemIdExists = connection.prepareStatement("SELECT * FROM Problem WHERE ID = ?;");
		getTitleExists = connection.prepareStatement("SELECT * FROM Problem WHERE Title = ?;");
		getProblemById = connection.prepareStatement("SELECT * FROM Problem WHERE ID = ?;");
		updateProblem = connection.prepareStatement("UPDATE Problem SET Title = ?, CategoryID = ?, Content = ?, LastEditTime = ?, Edited = TRUE, TrackingID = ? WHERE ID = ?;");
		incrementViewCountById = connection.prepareStatement("UPDATE Problem SET ViewCount = ViewCount + 1 WHERE ID = ?;");
		deleteProblemById = connection.prepareStatement("DELETE FROM Problem WHERE ID = ?;");
		deleteProblemTagById = connection.prepareStatement("DELETE FROM ProblemTag WHERE ProblemID = ?;");
	}

	@Override
	public int addProblem(Problem problem) {
		ResultSet rs = null;
		int resultId = 0;

		try {
			addProblem.setNull(1, Types.INTEGER);
			addProblem.setObject(2, problem.getCategoryId());
			addProblem.setString(3, problem.getTitle());
			addProblem.setString(4, problem.getContent());
			addProblem.setString(5, problem.getPasswordHash());
			addProblem.setTimestamp(6, problem.getCreatedTime());
			addProblem.setTimestamp(7, problem.getLastEditTime());
			addProblem.setInt(8, problem.getTrackingId());
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
				problem.setPasswordHash(rs.getString("PasswordHash"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problem.setLastEditTime(rs.getTimestamp("LastEditTime"));
				problem.setEdited(rs.getBoolean("Edited"));
				problem.setViewCount(rs.getInt("ViewCount"));
				problem.setTrackingId(rs.getInt("TrackingID"));
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
				problem.setPasswordHash(rs.getString("PasswordHash"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problem.setLastEditTime(rs.getTimestamp("LastEditTime"));
				problem.setEdited(rs.getBoolean("Edited"));
				problem.setViewCount(rs.getInt("ViewCount"));
				problem.setTrackingId(rs.getInt("TrackingID"));
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
	public List<Problem> getProblemsByTagNames(String tagNames) {
		ResultSet rs = null;

		try {
			getProblemsByTagNames.setString(1, tagNames);
			rs = getProblemsByTagNames.executeQuery();
			List<Problem> problems = new ArrayList<Problem>();
			while (rs.next()) {
				Problem problem = new Problem();
				problem.setId(rs.getInt("Problem.ID"));
				problem.setCategoryId(rs.getInt("Problem.CategoryID"));
				problem.setTitle(rs.getString("Problem.Title"));
				problem.setContent(rs.getString("Problem.Content"));
				problem.setPasswordHash(rs.getString("Problem.PasswordHash"));
				problem.setCreatedTime(rs.getTimestamp("Problem.CreatedTime"));
				problem.setLastEditTime(rs.getTimestamp("Problem.LastEditTime"));
				problem.setEdited(rs.getBoolean("Problem.Edited"));
				problem.setViewCount(rs.getInt("Problem.ViewCount"));
				problem.setTrackingId(rs.getInt("Problem.TrackingID"));
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
	public List<Problem> getProblemsByCategoryIdOrTagNames(int categoryId, String tagNames) {
		ResultSet rs = null;

		try {
			getProblemsByCategoryIdOrTagNames.setString(1, "%" + (categoryId >= 0 ? categoryId : "") + "%");
			getProblemsByCategoryIdOrTagNames.setString(2, tagNames);
			rs = getProblemsByCategoryIdOrTagNames.executeQuery();
			List<Problem> problems = new ArrayList<Problem>();
			while (rs.next()) {
				Problem problem = new Problem();
				problem.setId(rs.getInt("Problem.ID"));
				problem.setCategoryId(rs.getInt("Problem.CategoryID"));
				problem.setTitle(rs.getString("Problem.Title"));
				problem.setContent(rs.getString("Problem.Content"));
				problem.setPasswordHash(rs.getString("Problem.PasswordHash"));
				problem.setCreatedTime(rs.getTimestamp("Problem.CreatedTime"));
				problem.setLastEditTime(rs.getTimestamp("Problem.LastEditTime"));
				problem.setEdited(rs.getBoolean("Problem.Edited"));
				problem.setViewCount(rs.getInt("Problem.ViewCount"));
				problem.setTrackingId(rs.getInt("Problem.TrackingID"));
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
				problem.setPasswordHash(rs.getString("PasswordHash"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problem.setLastEditTime(rs.getTimestamp("LastEditTime"));
				problem.setEdited(rs.getBoolean("Edited"));
				problem.setViewCount(rs.getInt("ViewCount"));
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
			updateProblem.setTimestamp(4, problem.getLastEditTime());
			updateProblem.setInt(5, problem.getTrackingId());
			updateProblem.setInt(6, problem.getId());
			updateProblem.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void incrementViewCountById(int id) {
		try {
			incrementViewCountById.setInt(1, id);
			incrementViewCountById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProblemById(int id) {
		deleteProblemByIdFromProblemTag(id);

		try {
			deleteProblemById.setInt(1, id);
			deleteProblemById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteProblemByIdFromProblemTag(int id) {
		try {
			deleteProblemTagById.setInt(1, id);
			deleteProblemTagById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (addProblem != null && !addProblem.isClosed()) {
				addProblem.close();
			}
			if (getProblems != null && !getProblems.isClosed()) {
				getProblems.close();
			}
			if (getProblemsByCategoryId != null && !getProblemsByCategoryId.isClosed()) {
				getProblemsByCategoryId.close();
			}
			if (getProblemsByTagNames != null && !getProblemsByTagNames.isClosed()) {
				getProblemsByTagNames.close();
			}
			if (getProblemsByCategoryIdOrTagNames != null && !getProblemsByCategoryIdOrTagNames.isClosed()) {
				getProblemsByCategoryIdOrTagNames.close();
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
			if (incrementViewCountById != null && !incrementViewCountById.isClosed()) {
				incrementViewCountById.close();
			}
			if (deleteProblemById != null && !deleteProblemById.isClosed()) {
				deleteProblemById.close();
			}
			if (deleteProblemTagById != null && !deleteProblemTagById.isClosed()) {
				deleteProblemTagById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
