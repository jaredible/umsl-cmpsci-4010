package edu.umsl.java.dao.comment;

import java.util.List;

import edu.umsl.java.model.Comment;

public interface CommentDao {

	public int addComment(Comment c);

	public List<Comment> getCommentsByProblemId(int i);

}
