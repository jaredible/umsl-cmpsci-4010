package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class Problem {

	private long id;
	private String title;
	private String content;
	private boolean edited;
	private Timestamp createdTime;
	private Timestamp lastEditedTime;
	private int createdByUserId;

	public Problem() {
	}

	public Problem(long id, String title, String content, boolean edited, Timestamp createdTime, Timestamp lastEditedTime, int createdByUserId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.edited = edited;
		this.createdTime = createdTime;
		this.lastEditedTime = lastEditedTime;
		this.createdByUserId = createdByUserId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getLastEditedTime() {
		return lastEditedTime;
	}

	public void setLastEditedTime(Timestamp lastEditedTime) {
		this.lastEditedTime = lastEditedTime;
	}

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

}
