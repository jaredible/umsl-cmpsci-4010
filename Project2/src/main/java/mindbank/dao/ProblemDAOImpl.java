package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.mindbank.model.Problem;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.ProblemList;

public class ProblemDAOImpl implements ProblemDAO {

	private Connection conn;
	private PreparedStatement getList;

	public ProblemDAOImpl() throws SQLException {
		conn = DbConn.openConn();
		getList = conn.prepareStatement("SELECT * FROM Problems");
	}

	@Override
	public ProblemList getList() {
		ProblemList result = new ProblemList();

		Problem p = new Problem();
		p.setId(1);
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
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
