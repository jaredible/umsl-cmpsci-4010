package net.jaredible.mindbank.db.problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.problem.ProblemModel;
import net.jaredible.mindbank.util.DbUtil;

public class ProblemDaoImpl implements ProblemDao {

	private PreparedStatement getProblemById;
	private PreparedStatement getProblemByTitle;
	private PreparedStatement getAllProblems;
	private PreparedStatement getProblemsByFields;
	private PreparedStatement addProblem;

	@Override
	public ProblemModel getProblemById(long id) {
		ResultSet rs = null;

		try {
			Connection connection = DbUtil.getConnection();

			if (connection != null) {
				if (getProblemById == null) {
					getProblemById = connection.prepareStatement("SELECT * FROM Problem WHERE ID = ?;");
				}

				getProblemById.setLong(1, id);

				rs = getProblemById.executeQuery();

				if (rs.next()) {
					return extractProblemFromResultSet(rs);
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

		return null;
	}

	@Override
	public ProblemModel getProblemByTitle(String title) {
		ResultSet rs = null;

		try {
			Connection connection = DbUtil.getConnection();

			if (connection != null) {
				if (getProblemByTitle == null) {
					getProblemByTitle = connection.prepareStatement("SELECT * FROM Problem WHERE Title = ?;");
				}

				getProblemByTitle.setString(1, title);

				rs = getProblemByTitle.executeQuery();

				if (rs.next()) {
					return extractProblemFromResultSet(rs);
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

		return null;
	}

	@Override
	public List<ProblemModel> getAllProblems() {
		ResultSet rs = null;

		try {
			Connection connection = DbUtil.getConnection();

			if (connection != null) {
				if (getAllProblems == null) {
					getAllProblems = connection.prepareStatement("SELECT * FROM Problem ORDER BY CreatedTime DESC, Title ASC;");
				}

				rs = getAllProblems.executeQuery();

				List<ProblemModel> problems = new ArrayList<ProblemModel>();

				while (rs.next()) {
					problems.add(extractProblemFromResultSet(rs));
				}

				return problems;
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
	public List<ProblemModel> getProblemsByFields(String titleLike, String categoryIdsRegex, String tagIdsRegex, String contentLike, String dateCreatedStart, String dateCreatedEnd, String userIdsRegex) {
		ResultSet rs = null;

		try {
			Connection connection = DbUtil.getConnection();

			if (connection != null) {
				if (getProblemsByFields == null) {
					getProblemsByFields = connection
							.prepareStatement("SELECT Problem.* FROM Problem LEFT OUTER JOIN ProblemCategory ON ProblemCategory.ProblemID = Problem.ID LEFT OUTER JOIN ProblemTag ON ProblemTag.ProblemID = Problem.ID WHERE Problem.Title LIKE ? AND ProblemCategory.CategoryID REGEXP ? AND ProblemTag.TagID REGEXP ? AND Problem.Content LIKE ? AND Problem.CreatedTime BETWEEN ? AND ? AND Problem.CreatedByUserID REGEXP ? GROUP BY Problem.ID ORDER BY Problem.CreatedTime DESC, Problem.Title ASC;");
				}

				getProblemsByFields.setString(1, "%" + titleLike + "%");
				getProblemsByFields.setString(2, categoryIdsRegex);
				getProblemsByFields.setString(3, tagIdsRegex);
				getProblemsByFields.setString(4, "%" + contentLike + "%");
				getProblemsByFields.setString(5, dateCreatedStart);
				getProblemsByFields.setString(6, dateCreatedEnd);
				getProblemsByFields.setString(7, userIdsRegex);

				rs = getProblemsByFields.executeQuery();

				List<ProblemModel> problems = new ArrayList<ProblemModel>();

				while (rs.next()) {
					problems.add(extractProblemFromResultSet(rs));
				}

				return problems;
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
	public long addProblem(ProblemModel problem) {
		ResultSet rs = null;

		try {
			Connection connection = DbUtil.getConnection();

			if (connection != null) {
				if (addProblem == null) {
					addProblem = connection.prepareStatement("INSERT INTO Problem (ID, Title, Content, CreatedTime, CreatedByUserID) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
				}

				addProblem.setNull(1, Types.INTEGER);
				addProblem.setString(2, problem.getTitle());
				addProblem.setString(3, problem.getContent());
				addProblem.setTimestamp(4, problem.getCreatedTime());
				addProblem.setLong(5, problem.getCreatedByUserId());

				int rowAffected = addProblem.executeUpdate();

				if (rowAffected == 1) {
					rs = addProblem.getGeneratedKeys();

					if (rs.next()) {
						return rs.getLong(1);
					}
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
	public boolean getProblemExistsById(long id) {
		return getProblemById(id) != null;
	}

	private ProblemModel extractProblemFromResultSet(ResultSet rs) throws SQLException {
		ProblemModel problem = new ProblemModel();

		problem.setId(rs.getInt("ID"));
		problem.setTitle(rs.getString("Title"));
		problem.setContent(rs.getString("Content"));
		problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
		problem.setCreatedByUserId(rs.getInt("CreatedByUserID"));

		return problem;
	}

}
