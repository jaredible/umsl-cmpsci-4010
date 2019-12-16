package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import main.java.mindbank.model.Problem;
import main.java.mindbank.model.ProblemInfo;
import main.java.mindbank.model.User;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.ProblemInfoList;

public class ProblemDAOImpl implements ProblemDAO {

	private Connection connection;

	// create
	private PreparedStatement addProblem;

	// read
	private PreparedStatement getProblemById;
	private PreparedStatement getAllProblems;
	private PreparedStatement getAllProblemsByCategoryId;
	private PreparedStatement getProblemsWithLimit;
	private PreparedStatement getProblemsByCategoryIdWithLimit;

	// update
	private PreparedStatement updateCategoryIdById;
	private PreparedStatement updateTitleById;
	private PreparedStatement updateContentById;
	private PreparedStatement updateEditedById;
	private PreparedStatement updateCreatedByUserIdById;
	private PreparedStatement updateCreatedTimestampById;

	// delete
	private PreparedStatement deleteProblemById;

	public ProblemDAOImpl() throws SQLException {
		this(DbConn.openConn());
	}

	public ProblemDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;

		init();
	}

	private void init() throws SQLException {
		// create
		addProblem = connection.prepareStatement("INSERT INTO Problem (ID, CategoryID, Title, Content, Edited, CreatedByUserID, CreatedTimestamp) VALUES (?, ?, ?, ?, ?, ?, ?);");

		// read
		getProblemById = connection.prepareStatement("SELECT * FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID WHERE Problem.ID = ?;");
		getAllProblems = connection.prepareStatement("SELECT * FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID ORDER BY Problem.CreatedTimestamp DESC;");
		getAllProblemsByCategoryId = connection.prepareStatement("SELECT * FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID WHERE Problem.CategoryID = ? ORDER BY Problem.CreatedTimestamp DESC;");
		getProblemsWithLimit = connection.prepareStatement("SELECT * FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID ORDER BY Problem.CreatedTimestamp DESC LIMIT ?, ?;");
		getProblemsByCategoryIdWithLimit = connection.prepareStatement("SELECT * FROM Problem LEFT OUTER JOIN User ON Problem.CreatedByUserID = User.ID WHERE Problem.CategoryID = ? ORDER BY Problem.CreatedTimestamp DESC LIMIT ?, ?;");

		// update
		updateCategoryIdById = connection.prepareStatement("UPDATE Problem SET CategoryId = ? WHERE ID = ?;");
		updateTitleById = connection.prepareStatement("UPDATE Problem SET Title = ? WHERE ID = ?;");
		updateContentById = connection.prepareStatement("UPDATE Problem SET Content = ? WHERE ID = ?;");
		updateEditedById = connection.prepareStatement("UPDATE Problem SET Edited = ? WHERE ID = ?;");
		updateCreatedByUserIdById = connection.prepareStatement("UPDATE Problem SET CreatedByUserId = ? WHERE ID = ?;");
		updateCreatedTimestampById = connection.prepareStatement("UPDATE Problem SET CreatedTimestamp = ? WHERE ID = ?;");

		// delete
		deleteProblemById = connection.prepareStatement("DELETE FROM Problem WHERE ID = ?;");
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
	public ProblemInfo getProblemById(int id) {
		try {
			getProblemById.setInt(1, id);
			ResultSet rs = getProblemById.executeQuery();
			if (rs.next()) {
				ProblemInfo pi = new ProblemInfo();
				Problem p = new Problem();
				User u = new User();

				p.setId(rs.getInt("Problem.ID"));
				p.setCategoryId(rs.getInt("Problem.CategoryID"));
				p.setTitle(rs.getString("Problem.Title"));
				p.setContent(rs.getString("Problem.Content"));
				p.setEdited(rs.getBoolean("Problem.Edited"));
				p.setCreatedByUserId(rs.getInt("Problem.CreatedByUserID"));
				p.setCreatedTimestamp(rs.getTimestamp("Problem.CreatedTimestamp"));

				u.setId(rs.getInt("User.ID"));
				u.setRoleId(rs.getInt("User.RoleID"));
				u.setEmail(rs.getString("User.Email"));
				u.setUserName(rs.getString("User.UserName"));
				u.setName(rs.getString("User.Name"));
				u.setBio(rs.getString("User.Bio"));
				u.setPhoneNumber(rs.getString("User.PhoneNumber"));
				u.setPasswordHash(rs.getString("User.PasswordHash"));
				u.setEmailVerified(rs.getBoolean("User.EmailVerified"));
				u.setPhoneNumberVerified(rs.getBoolean("User.PhoneNumberVerified"));
				u.setRegistrationTimestamp(rs.getTimestamp("User.RegistrationTimestamp"));
				u.setLoginTimestamp(rs.getTimestamp("User.LoginTimestamp"));

				pi.setProblem(p);
				pi.setUser(u);

				return pi;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ProblemInfoList getAllProblems() {
		try {
			ResultSet rs = getAllProblems.executeQuery();
			ProblemInfoList pil = new ProblemInfoList();
			while (rs.next()) {
				ProblemInfo pi = new ProblemInfo();
				Problem p = new Problem();
				User u = new User();

				p.setId(rs.getInt("Problem.ID"));
				p.setCategoryId(rs.getInt("Problem.CategoryID"));
				p.setTitle(rs.getString("Problem.Title"));
				p.setContent(rs.getString("Problem.Content"));
				p.setEdited(rs.getBoolean("Problem.Edited"));
				p.setCreatedByUserId(rs.getInt("Problem.CreatedByUserID"));
				p.setCreatedTimestamp(rs.getTimestamp("Problem.CreatedTime"));

				u.setId(rs.getInt("User.ID"));
				u.setRoleId(rs.getInt("User.RoleID"));
				u.setEmail(rs.getString("User.Email"));
				u.setUserName(rs.getString("User.UserName"));
				u.setName(rs.getString("User.Name"));
				u.setBio(rs.getString("User.Bio"));
				u.setPhoneNumber(rs.getString("User.PhoneNumber"));
				u.setPasswordHash(rs.getString("User.PasswordHash"));
				u.setEmailVerified(rs.getBoolean("User.EmailVerified"));
				u.setPhoneNumberVerified(rs.getBoolean("User.PhoneNumberVerified"));
				u.setRegistrationTimestamp(rs.getTimestamp("User.RegistrationTime"));
				u.setLoginTimestamp(rs.getTimestamp("User.LoginTimestamp"));

				pi.setProblem(p);
				pi.setUser(u);

				pil.add(pi);
			}
			return pil;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ProblemInfoList getAllProblemsByCategoryId(int categoryId) {
		try {
			getAllProblemsByCategoryId.setInt(1, categoryId);
			ResultSet rs = getAllProblemsByCategoryId.executeQuery();
			ProblemInfoList pil = new ProblemInfoList();
			while (rs.next()) {
				ProblemInfo pi = new ProblemInfo();
				Problem p = new Problem();
				User u = new User();

				p.setId(rs.getInt("Problem.ID"));
				p.setCategoryId(rs.getInt("Problem.CategoryID"));
				p.setTitle(rs.getString("Problem.Title"));
				p.setContent(rs.getString("Problem.Content"));
				p.setEdited(rs.getBoolean("Problem.Edited"));
				p.setCreatedByUserId(rs.getInt("Problem.CreatedByUserID"));
				p.setCreatedTimestamp(rs.getTimestamp("Problem.CreatedTimestamp"));

				u.setId(rs.getInt("User.ID"));
				u.setRoleId(rs.getInt("User.RoleID"));
				u.setEmail(rs.getString("User.Email"));
				u.setUserName(rs.getString("User.UserName"));
				u.setName(rs.getString("User.Name"));
				u.setBio(rs.getString("User.Bio"));
				u.setPhoneNumber(rs.getString("User.PhoneNumber"));
				u.setPasswordHash(rs.getString("User.PasswordHash"));
				u.setEmailVerified(rs.getBoolean("User.EmailVerified"));
				u.setPhoneNumberVerified(rs.getBoolean("User.PhoneNumberVerified"));
				u.setRegistrationTimestamp(rs.getTimestamp("User.RegistrationTimestamp"));
				u.setLoginTimestamp(rs.getTimestamp("User.LoginTimestamp"));

				pi.setProblem(p);
				pi.setUser(u);

				pil.add(pi);
			}
			return pil;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ProblemInfoList getProblemsWithLimit(int offset, int rowCount) {
		try {
			getProblemsWithLimit.setInt(1, offset);
			getProblemsWithLimit.setInt(2, rowCount);
			ResultSet rs = getProblemsWithLimit.executeQuery();
			ProblemInfoList pil = new ProblemInfoList();
			while (rs.next()) {
				ProblemInfo pi = new ProblemInfo();
				Problem p = new Problem();
				User u = new User();

				p.setId(rs.getInt("Problem.ID"));
				p.setCategoryId(rs.getInt("Problem.CategoryID"));
				p.setTitle(rs.getString("Problem.Title"));
				p.setContent(rs.getString("Problem.Content"));
				p.setEdited(rs.getBoolean("Problem.Edited"));
				p.setCreatedByUserId(rs.getInt("Problem.CreatedByUserID"));
				p.setCreatedTimestamp(rs.getTimestamp("Problem.CreatedTimestamp"));

				u.setId(rs.getInt("User.ID"));
				u.setRoleId(rs.getInt("User.RoleID"));
				u.setEmail(rs.getString("User.Email"));
				u.setUserName(rs.getString("User.UserName"));
				u.setName(rs.getString("User.Name"));
				u.setBio(rs.getString("User.Bio"));
				u.setPhoneNumber(rs.getString("User.PhoneNumber"));
				u.setPasswordHash(rs.getString("User.PasswordHash"));
				u.setEmailVerified(rs.getBoolean("User.EmailVerified"));
				u.setPhoneNumberVerified(rs.getBoolean("User.PhoneNumberVerified"));
				u.setRegistrationTimestamp(rs.getTimestamp("User.RegistrationTimestamp"));
				u.setLoginTimestamp(rs.getTimestamp("User.LoginTimestamp"));

				pi.setProblem(p);
				pi.setUser(u);

				pil.add(pi);
			}
			return pil;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ProblemInfoList getProblemsByCategoryIdWithLimit(int categoryId, int offset, int rowCount) {
		try {
			getProblemsByCategoryIdWithLimit.setInt(1, categoryId);
			getProblemsByCategoryIdWithLimit.setInt(2, offset);
			getProblemsByCategoryIdWithLimit.setInt(3, rowCount);
			ResultSet rs = getProblemsByCategoryIdWithLimit.executeQuery();
			ProblemInfoList pil = new ProblemInfoList();
			while (rs.next()) {
				ProblemInfo pi = new ProblemInfo();
				Problem p = new Problem();
				User u = new User();

				p.setId(rs.getInt("Problem.ID"));
				p.setCategoryId(rs.getInt("Problem.CategoryID"));
				p.setTitle(rs.getString("Problem.Title"));
				p.setContent(rs.getString("Problem.Content"));
				p.setEdited(rs.getBoolean("Problem.Edited"));
				p.setCreatedByUserId(rs.getInt("Problem.CreatedByUserID"));
				p.setCreatedTimestamp(rs.getTimestamp("Problem.CreatedTimestamp"));

				u.setId(rs.getInt("User.ID"));
				u.setRoleId(rs.getInt("User.RoleID"));
				u.setEmail(rs.getString("User.Email"));
				u.setUserName(rs.getString("User.UserName"));
				u.setName(rs.getString("User.Name"));
				u.setBio(rs.getString("User.Bio"));
				u.setPhoneNumber(rs.getString("User.PhoneNumber"));
				u.setPasswordHash(rs.getString("User.PasswordHash"));
				u.setEmailVerified(rs.getBoolean("User.EmailVerified"));
				u.setPhoneNumberVerified(rs.getBoolean("User.PhoneNumberVerified"));
				u.setRegistrationTimestamp(rs.getTimestamp("User.RegistrationTimestamp"));
				u.setLoginTimestamp(rs.getTimestamp("User.LoginTimestamp"));

				pi.setProblem(p);
				pi.setUser(u);

				pil.add(pi);
			}
			return pil;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateCategoryIdById(int id, int categoryId) {
		try {
			updateCategoryIdById.setInt(1, categoryId);
			updateCategoryIdById.setInt(2, id);
			updateCategoryIdById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTitleById(int id, String title) {
		try {
			updateTitleById.setString(1, title);
			updateTitleById.setInt(2, id);
			updateTitleById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateContentById(int id, String content) {
		try {
			updateContentById.setString(1, content);
			updateContentById.setInt(2, id);
			updateContentById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEditedById(int id, boolean edited) {
		try {
			updateEditedById.setBoolean(1, edited);
			updateEditedById.setInt(2, id);
			updateEditedById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCreatedByUserIdById(int id, int createdByUserId) {
		try {
			updateCreatedByUserIdById.setInt(1, createdByUserId);
			updateCreatedByUserIdById.setInt(2, id);
			updateCreatedByUserIdById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCreatedTimestampById(int id, Timestamp createdTimestamp) {
		try {
			updateCreatedTimestampById.setTimestamp(1, createdTimestamp);
			updateCreatedTimestampById.setInt(2, id);
			updateCreatedTimestampById.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void closeConnections() {
		try {
			// create
			if (!addProblem.isClosed()) {
				addProblem.close();
			}

			// read
			if (!getProblemById.isClosed()) {
				getProblemById.close();
			}
			if (!getProblemsWithLimit.isClosed()) {
				getProblemsWithLimit.close();
			}
			if (!getProblemsByCategoryIdWithLimit.isClosed()) {
				getProblemsByCategoryIdWithLimit.close();
			}

			// update
			if (!updateCategoryIdById.isClosed()) {
				updateCategoryIdById.close();
			}
			if (!updateTitleById.isClosed()) {
				updateTitleById.close();
			}
			if (!updateContentById.isClosed()) {
				updateContentById.close();
			}
			if (!updateEditedById.isClosed()) {
				updateEditedById.close();
			}
			if (!updateCreatedByUserIdById.isClosed()) {
				updateCreatedByUserIdById.close();
			}
			if (!updateCreatedTimestampById.isClosed()) {
				updateCreatedTimestampById.close();
			}

			// delete
			if (!deleteProblemById.isClosed()) {
				deleteProblemById.close();
			}

			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// create
		// addProblem = null;

		// read
		// getProblemById = null;
		// getProblemsWithLimit = null;
		// getProblemsByCategoryIdWithLimit = null;

		// update
		// updateCategoryIdById = null;
		// updateTitleById = null;
		// updateContentById = null;
		// updateEditedById = null;
		// updateCreatedByUserIdById = null;
		// updateCreatedTimestampById = null;

		// delete
		// deleteProblemById = null;

		// connection = null;
	}

	protected void finalize() {
		closeConnections();
	}

	public static void main(String[] args) {
	}

}
