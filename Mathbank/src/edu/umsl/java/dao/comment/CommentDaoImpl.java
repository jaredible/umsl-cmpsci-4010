package edu.umsl.java.dao.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import edu.umsl.java.model.Comment;
import edu.umsl.java.util.DbConn;

public class CommentDaoImpl implements CommentDao {

	private Connection connection;
	private PreparedStatement getCommentsByProblemId;

	public CommentDaoImpl() throws Exception {
		connection = DbConn.openConn();
		getCommentsByProblemId = connection.prepareStatement("SELECT * FROM Comment WHERE ProblemID = ?;");
	}

	@Override
	public List<Comment> getCommentsByProblemId(int problemId) {
		return null;
	}

	@Override
	protected void finalize() {
		try {
			if (getCommentsByProblemId != null && !getCommentsByProblemId.isClosed()) {
				getCommentsByProblemId.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
