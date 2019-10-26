package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.mindbank.model.Subject;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.SubjectList;

public class SubjectDAOImpl implements SubjectDAO {

	private Connection connection;
	private PreparedStatement getSubjects;

	public SubjectDAOImpl() throws SQLException {
		connection = DbConn.openConn();
		getSubjects = connection.prepareStatement("SELECT * FROM Subject;");
	}

	public SubjectDAOImpl(Connection connection) throws SQLException {
		this.connection = connection;
		getSubjects = connection.prepareStatement("SELECT * FROM Subject;");
	}

	@Override
	public SubjectList getSubjects() {
		return null;
	}

	@Override
	public void addSubject(Subject s) {
	}

	@Override
	public Subject getSubject(int i) {
		return null;
	}

	@Override
	public void updateSubject(Subject s) {
	}

	@Override
	public void deleteSubject(int i) {
	}

	public Connection getConnection() {
		return connection;
	}

	protected void finalize() {
		try {
			getSubjects.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

}
