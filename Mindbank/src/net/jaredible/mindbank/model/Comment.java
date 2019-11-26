package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class Comment {

	private long id;
	private long problemId;
	private String content;
	private Timestamp createdTime;
	private int createdByUserId;

	public Comment() {
	}

	public Comment(long id, long problemId, String content, Timestamp createdTime, int createdByUserId) {
		this.id = id;
		this.problemId = problemId;
		this.content = content;
		this.createdTime = createdTime;
		this.createdByUserId = createdByUserId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProblemId() {
		return problemId;
	}

	public void setProblemId(long problemId) {
		this.problemId = problemId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

}
