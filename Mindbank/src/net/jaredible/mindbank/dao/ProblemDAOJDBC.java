package net.jaredible.mindbank.dao;

import static net.jaredible.mindbank.dao.DAOUtil.preparedStatement;
import static net.jaredible.mindbank.dao.DAOUtil.toSqlDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.Problem;

public class ProblemDAOJDBC implements ProblemDAO {

	private static final String SQL_FIND_BY_ID = "SELECT * FROM Problem WHERE ProblemID = ?";
	private static final String SQL_FIND_BY_TITLE = "SELECT * FROM Problem WHERE Title = ?";
	private static final String SQL_LIST_ORDER_BY_TITLE = "SELECT * FROM Problem ORDER BY Title ASC";
	private static final String SQL_LIST_BY_CATEGORIES_TAGS_USERS_ORDER_BY_TITLE = "SELECT Problem.* FROM Problem LEFT OUTER JOIN ProblemCategory ON ProblemCategory.ProblemID = Problem.ProblemID LEFT OUTER JOIN ProblemTag ON ProblemTag.ProblemID = Problem.ProblemID WHERE ProblemCategory.CategoryID REGEXP ? AND ProblemTag.TagID REGEXP ? AND Problem.CreatedByUserID REGEXP ? GROUP BY Problem.ProblemID ORDER BY Problem.Title ASC";
	private static final String SQL_INSERT = "INSERT INTO Problem (Title, Content, CreatedTime, CreatedByUserID) VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Problem SET Title = ?, Content = ?, CreatedTime = ?, CreatedByUserID = ? WHERE ProblemID = ?";
	private static final String SQL_DELETE = "DELETE FROM Problem WHERE ProblemID = ?";
	private static final String SQL_EXIST_TITLE = "SELECT ProblemID FROM Problem WHERE Title = ?";

	private DAOFactory daoFactory;

	ProblemDAOJDBC(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Problem find(Long id) throws DAOException {
		return find(SQL_FIND_BY_ID, id);
	}

	@Override
	public Problem find(String title) throws DAOException {
		return find(SQL_FIND_BY_TITLE, title);
	}

	private Problem find(String sql, Object... values) throws DAOException {
		Problem problem = null;

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, sql, false, values); ResultSet resultSet = statement.executeQuery();) {
			if (resultSet.next()) {
				problem = map(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return problem;
	}

	@Override
	public List<Problem> list() throws DAOException {
		List<Problem> problems = new ArrayList<Problem>();

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_TITLE); ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				problems.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return problems;
	}

	@Override
	public List<Problem> list(String categories, String tags, String users) throws DAOException {
		List<Problem> problems = new ArrayList<Problem>();

		Object[] values = { categories, tags, users };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_LIST_BY_CATEGORIES_TAGS_USERS_ORDER_BY_TITLE, false, values); ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				problems.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return problems;
	}

	@Override
	public void create(Problem problem) throws IllegalArgumentException, DAOException {
		if (problem.getId() != null) {
			throw new IllegalArgumentException("Problem is already created, the problem ID is not null.");
		}

		Object[] values = { problem.getTitle(), problem.getContent(), toSqlDate(problem.getCreatedTime()), problem.getCreatedByUserId() };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_INSERT, true, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Creating problem failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					problem.setId(generatedKeys.getLong(1));
				} else {
					throw new DAOException("Creating problem failed, no generated key obtained.");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Problem problem) throws IllegalArgumentException, DAOException {
		if (problem.getId() == null) {
			throw new IllegalArgumentException("Problem is not created yet, the problem ID is null.");
		}

		Object[] values = { problem.getTitle(), problem.getContent(), toSqlDate(problem.getCreatedTime()), problem.getCreatedByUserId(), problem.getId() };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_UPDATE, false, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Updating problem failed, no rows affected.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Problem problem) throws DAOException {
		Object[] values = { problem.getId() };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_DELETE, false, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Deleting problem failed, no rows affected.");
			} else {
				problem.setId(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean existTitle(String title) throws DAOException {
		Object[] values = { title };

		boolean exist = false;

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_EXIST_TITLE, false, values); ResultSet resultSet = statement.executeQuery()) {
			exist = resultSet.next();
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return exist;
	}

	private static Problem map(ResultSet resultSet) throws SQLException {
		Problem problem = new Problem();
		problem.setId(resultSet.getLong("ProblemID"));
		problem.setTitle(resultSet.getString("Title"));
		problem.setContent(resultSet.getString("Content"));
		problem.setCreatedTime(resultSet.getTimestamp("CreatedTime"));
		problem.setCreatedByUserId(resultSet.getLong("CreatedByUserID"));
		return problem;
	}

}
