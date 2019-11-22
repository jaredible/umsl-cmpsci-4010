package net.jaredible.mindbank.dao.problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.dao.Dao;
import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.util.DbUtil;

public class ProblemDao implements Dao<Problem> {

	protected Connection connection;
	protected PreparedStatement get;
	protected PreparedStatement getAll;
	protected PreparedStatement save;
	protected PreparedStatement update;
	protected PreparedStatement delete;

	public ProblemDao() throws Exception {
		connection = DbUtil.openConnection();
		get = connection.prepareStatement("SELECT * FROM Problem WHERE ID = ?;");
		getAll = connection.prepareStatement("SELECT * FROM Problem;");
		save = connection.prepareStatement("INSERT INTO Problem (ID, Title, Content, Edited, CreatedTime, LastEditedTime, CreatedByUserID) VALUES (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		update = connection.prepareStatement("UPDATE Problem SET Title = ?, Content = ?, Edited = ?, CreatedTime = ?, LastEditedTime = ?, CreatedByUserID = ? WHERE ID = ?;");
		delete = connection.prepareStatement("DELETE FROM Problem WHERE ID = ?;");
	}

	@Override
	public Problem get(long id) {
		ResultSet rs = null;

		try {
			get.setLong(1, id);

			rs = get.executeQuery();

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
	public List<Problem> getAll() {
		try {
			ResultSet rs = getAll.executeQuery();

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
	public int save(Problem problem) {
		ResultSet rs = null;

		try {
			save.setNull(1, Types.INTEGER);
			save.setString(2, problem.getTitle());
			save.setString(3, problem.getContent());
			save.setBoolean(4, problem.isEdited());
			save.setTimestamp(5, problem.getCreatedTime());
			save.setTimestamp(6, problem.getLastEditedTime());
			save.setInt(7, problem.getCreatedByUserId());

			int rowAffected = save.executeUpdate();

			if (rowAffected == 1) {
				rs = save.getGeneratedKeys();

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
	public int update(Problem problem, int[] params) {
		try {
			for (int param : params) {
				switch (param) {
				case Problem.TITLE:
					update.setString(1, problem.getTitle());
					break;
				case Problem.CONTENT:
					update.setString(2, problem.getContent());
					break;
				case Problem.EDITED:
					update.setBoolean(3, problem.isEdited());
					break;
				case Problem.CREATED_TIME:
					update.setTimestamp(4, problem.getCreatedTime());
					break;
				case Problem.LAST_EDITED_TIME:
					update.setTimestamp(5, problem.getLastEditedTime());
					break;
				case Problem.CREATED_BY_USER_ID:
					update.setInt(6, problem.getCreatedByUserId());
					break;
				default:
					throw new Exception("Unknown parameter!");
				}
			}

			update.setLong(7, problem.getId());

			return update.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public int delete(long id) {
		try {
			delete.setLong(1, id);

			return delete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	protected void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (get != null && !get.isClosed()) {
				get.close();
			}
			if (getAll != null && !getAll.isClosed()) {
				getAll.close();
			}
			if (save != null && !save.isClosed()) {
				save.close();
			}
			if (update != null && !update.isClosed()) {
				update.close();
			}
			if (delete != null && !delete.isClosed()) {
				delete.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
