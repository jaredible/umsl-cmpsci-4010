package edu.umsl.java.dao.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.umsl.java.model.Comment;
import edu.umsl.java.util.DbConn;

public class CommentDaoImpl implements CommentDao {

	private Connection connection;
	private PreparedStatement addComment;
	private PreparedStatement getCommentsByProblemId;

	public CommentDaoImpl() throws Exception {
		connection = DbConn.openConn();
		addComment = connection.prepareStatement("INSERT INTO Comment (ID, ProblemID, Content, CreatedTime, TrackingID) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		getCommentsByProblemId = connection.prepareStatement("SELECT * FROM Comment WHERE ProblemID = ? ORDER BY CreatedTime DESC;");
	}

	@Override
	public int addComment(Comment comment) {
		ResultSet rs = null;
		int resultId = 0;

		try {
			addComment.setNull(1, Types.INTEGER);
			addComment.setInt(2, comment.getProblemId());
			addComment.setString(3, comment.getContent());
			addComment.setTimestamp(4, comment.getCreatedTime());
			addComment.setInt(5, comment.getTrackingId());
			int rowAffected = addComment.executeUpdate();
			if (rowAffected == 1) {
				rs = addComment.getGeneratedKeys();
				if (rs.next()) {
					resultId = rs.getInt(1);
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

		return resultId;
	}

	@Override
	public List<Comment> getCommentsByProblemId(int problemId) {
		ResultSet rs = null;

		try {
			getCommentsByProblemId.setInt(1, problemId);
			rs = getCommentsByProblemId.executeQuery();
			List<Comment> comments = new ArrayList<Comment>();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("ID"));
				comment.setProblemId(rs.getInt("ProblemID"));
				comment.setContent(rs.getString("Content"));
				comment.setCreatedTime(rs.getTimestamp("CreatedTime"));
				comment.setTrackingId(rs.getInt("TrackingID"));
				comments.add(comment);
			}
			return comments;
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
	protected void finalize() {
		try {
			if (addComment != null && !addComment.isClosed()) {
				addComment.close();
			}
			if (getCommentsByProblemId != null && !getCommentsByProblemId.isClosed()) {
				getCommentsByProblemId.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
