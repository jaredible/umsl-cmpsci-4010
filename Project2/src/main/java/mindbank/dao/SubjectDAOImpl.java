package main.java.mindbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.mindbank.model.Category;
import main.java.mindbank.model.Subject;
import main.java.mindbank.util.CategoryList;
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
		try {
			ResultSet rs = getSubjects.executeQuery();
			SubjectList sl = new SubjectList();
			while (rs.next()) {
				Subject s = new Subject();
				s.setId(rs.getInt("ID"));
				s.setName(rs.getString("Name"));
				s.setDescription(rs.getString("Description"));
				sl.add(s);
			}
			return sl;
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
			if (!getSubjects.isClosed()) {
				getSubjects.close();
			}
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

}
