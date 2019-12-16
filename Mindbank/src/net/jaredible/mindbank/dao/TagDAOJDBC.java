package net.jaredible.mindbank.dao;

import static net.jaredible.mindbank.dao.DAOUtil.preparedStatement;
import static net.jaredible.mindbank.dao.DAOUtil.toSqlDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.Tag;

public class TagDAOJDBC implements TagDAO {

	private static final String SQL_LIST_ORDER_BY_NAME = "SELECT * FROM Tag ORDER BY Name ASC";
	private static final String SQL_LIST_BY_PROBLEM_ID_ORDER_BY_NAME = "SELECT Tag.* FROM ProblemTag LEFT OUTER JOIN Tag ON Tag.TagID = ProblemTag.TagID WHERE ProblemID = ? ORDER BY Tag.Name ASC";
	private static final String SQL_INSERT = "INSERT INTO Tag (Name, CreatedTime, CreatedByUserID) VALUES (?, ?, ?)";
	private static final String SQL_INSERT_PROBLEM_ASSOCIATION = "INSERT INTO ProblemTag (ProblemID, TagID) VALUES (?, ?)";
	private static final String SQL_DELETE_PROBLEM_ASSOCIATIONS = "DELETE FROM ProblemTag WHERE ProblemID = ?";
	private static final String SQL_EXIST_NAME = "SELECT TagID FROM Tag WHERE Name = ?";

	private DAOFactory daoFactory;

	TagDAOJDBC(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Tag> list() throws DAOException {
		List<Tag> tags = new ArrayList<Tag>();

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_NAME); ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				tags.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return tags;
	}

	@Override
	public List<Tag> list(Long problemId) throws DAOException {
		List<Tag> tags = new ArrayList<Tag>();

		Object[] values = { problemId };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_LIST_BY_PROBLEM_ID_ORDER_BY_NAME, false, values); ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				tags.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return tags;
	}

	@Override
	public void create(Tag tag) throws IllegalArgumentException, DAOException {
		if (tag.getId() != null) {
			throw new IllegalArgumentException("Tag is already created, the tag ID is not null.");
		}

		Object[] values = { tag.getName(), toSqlDate(tag.getCreatedTime()), tag.getCreatedByUserId() };

		try (Connection connection = daoFactory.getConnection(); PreparedStatement statement = preparedStatement(connection, SQL_INSERT, true, values);) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("Creating tag failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					tag.setId(generatedKeys.getLong(1));
				} else {
					throw new DAOException("Creating tag failed, no generated key obtained.");
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

	private static Tag map(ResultSet resultSet) throws SQLException {
		Tag tag = new Tag();
		tag.setId(resultSet.getLong("TagID"));
		tag.setName(resultSet.getString("Name"));
		tag.setCreatedTime(resultSet.getTimestamp("CreatedTime"));
		tag.setCreatedByUserId(resultSet.getLong("CreatedByUserID"));
		return tag;
	}

}
