package net.jaredible.mindbank.dao;

import static net.jaredible.mindbank.dao.DAOUtil.preparedStatement;
import static net.jaredible.mindbank.dao.DAOUtil.toSqlDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.Category;

public class CategoryDAOJDBC implements CategoryDAO {

	private static final String SQL_LIST_ORDER_BY_NAME = "SELECT * FROM Category ORDER BY Name ASC";
	private static final String SQL_LIST_BY_PROBLEM_ID_ORDER_BY_NAME = "SELECT Category.* FROM ProblemCategory LEFT OUTER JOIN Category ON Category.CategoryID = ProblemCategory.CategoryID WHERE ProblemID = ? ORDER BY Category.Name ASC";
	private static final String SQL_INSERT = "INSERT INTO Category (Name, CreatedTime, CreatedByUserID) VALUES (?, ?, ?)";
	private static final String SQL_INSERT_PROBLEM_ASSOCIATION = "INSERT INTO ProblemCategory (ProblemID, CategoryID) VALUES (?, ?)";
	private static final String SQL_DELETE_PROBLEM_ASSOCIATIONS = "DELETE FROM ProblemCategory WHERE ProblemID = ?";
	private static final String SQL_EXIST_NAME = "SELECT CategoryID FROM Category WHERE Name = ?";

	private DAOFactory daoFactory;

	CategoryDAOJDBC(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Category> list() throws DAOException {
		List<Category> categories = new ArrayList<Category>();

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_NAME); ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				categories.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return categories;
	}

	@Override
	public List<Category> list(Long problemId) throws DAOException {
		List<Category> categories = new ArrayList<Category>();

		Object[] values = { problemId };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_LIST_BY_PROBLEM_ID_ORDER_BY_NAME, false, values); ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				categories.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return categories;
	}

	@Override
	public void create(Category category) throws IllegalArgumentException, DAOException {
		if (category.getId() != null) {
			throw new IllegalArgumentException("Category is already created, the category ID is not null.");
		}

		Object[] values = { category.getName(), toSqlDate(category.getCreatedTime()), category.getCreatedByUserId() };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_INSERT, true, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Creating category failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					category.setId(generatedKeys.getLong(1));
				} else {
					throw new DAOException("Creating category failed, no generated key obtained.");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void createProblemAssociation(Long problemId, Long categoryId) throws IllegalArgumentException, DAOException {
		Object[] values = { problemId, categoryId };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_INSERT_PROBLEM_ASSOCIATION, true, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Creating problem assocation failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					// TODO
				} else {
					throw new DAOException("Creating problem association failed, no generated key obtained.");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteProblemAssociations(Long problemId) throws DAOException {
		Object[] values = { problemId };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_DELETE_PROBLEM_ASSOCIATIONS, false, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				// TODO
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean existName(String name) throws DAOException {
		Object[] values = { name };

		boolean exist = false;

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_EXIST_NAME, false, values); ResultSet resultSet = statement.executeQuery()) {
			exist = resultSet.next();
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return exist;
	}

	private static Category map(ResultSet resultSet) throws SQLException {
		Category category = new Category();
		category.setId(resultSet.getLong("CategoryID"));
		category.setName(resultSet.getString("Name"));
		category.setCreatedTime(resultSet.getTimestamp("CreatedTime"));
		category.setCreatedByUserId(resultSet.getLong("CreatedByUserID"));
		return category;
	}

}
