package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.mindbank.model.Problem;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.ProblemList;

public class ProblemDAOImpl implements ProblemDAO {

	private Connection connection;
	private PreparedStatement getList;

	public ProblemDAOImpl() throws SQLException {
		connection = DbConn.openConn();
		getList = connection.prepareStatement("SELECT * FROM Problems");
	}

	public ProblemDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;
		getList = connection.prepareStatement("SELECT * FROM Problems");
	}

	@Override
	public ProblemList getList() {
		ProblemList result = new ProblemList();

		Problem p = new Problem();
		p.setId(1);
		p.setTitle("Testing");
		p.setContent("This is a test.");
		p.setCreatedByUserId(1);
		result.add(p);

		return result;
	}

	@Override
	public void addProblem(Problem problem) {
	}

	@Override
	public Problem getProblem(int id) {
		return null;
	}

	@Override
	public void updateProblem(Problem problem) {
	}

	@Override
	public void deleteProblem(int id) {
	}

	protected void finalize() {
		try {
			getList.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static void main(String[] args) {
	}

}
