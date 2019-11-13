package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.umsl.java.model.Problem;
import edu.umsl.java.util.DbConn;

public class ProblemDaoImpl implements ProblemDao {

	private Connection connection;
	private PreparedStatement addProblem;
	private PreparedStatement getProblems;

	public ProblemDaoImpl() throws Exception {
		connection = DbConn.openConn();
		addProblem = connection.prepareStatement("INSERT INTO Problem (ID, CategoryID, Title, Content, CreatedTime) VALUES (?, ?, ?, ?, ?);");
		getProblems = connection.prepareStatement("SELECT * FROM Problem;");
	}

	@Override
	public void addProblem(Problem problem) {
		try {
			addProblem.setNull(1, Types.INTEGER);
			addProblem.setInt(2, problem.getCategoryId());
			addProblem.setString(3, problem.getTitle());
			addProblem.setString(4, problem.getContent());
			addProblem.setTimestamp(5, problem.getCreatedTime());
			addProblem.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Problem> getProblems() {
		try {
			ResultSet rs = getProblems.executeQuery();
			List<Problem> problems = new ArrayList<Problem>();
			while (rs.next()) {
				Problem problem = new Problem();
				problem.setId(rs.getInt("ID"));
				problem.setCategoryId(rs.getInt("CategoryID"));
				problem.setTitle(rs.getString("Title"));
				problem.setContent(rs.getString("Content"));
				problem.setCreatedTime(rs.getTimestamp("CreatedTime"));
				problems.add(problem);
			}
			return problems;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
