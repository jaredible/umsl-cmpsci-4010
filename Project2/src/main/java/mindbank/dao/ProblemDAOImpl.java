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

		Problem p1 = new Problem();
		p1.setId(1);
		p1.setTitle("Testing");
		p1.setContent("This is a test.");
		p1.setCreatedByUserId(1);
		result.add(p1);
		Problem p2 = new Problem();
		p2.setId(1);
		p2.setTitle("Testing");
		p2.setContent("This is a test.");
		p2.setCreatedByUserId(2);
		result.add(p2);

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
