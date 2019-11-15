package edu.umsl.java.dao.comment;

import java.util.List;

import edu.umsl.java.model.Comment;

public interface CommentDao {

	public List<Comment> getCommentsByProblemId(int i);

}
