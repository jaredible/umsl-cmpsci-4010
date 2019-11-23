package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class Comment {

	public static final int ID = 1;
	public static final int CONTENT = 2;
	public static final int CREATED_TIME = 3;
	public static final int CREATED_BY_USER_ID = 4;

	private long id;
	private String content;
	private Timestamp createdTime;
	private int createdByUserId;

	public Comment() {
	}

	public Comment(long id, String content, Timestamp createdTime, int createdByUserId) {
		this.id = id;
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
