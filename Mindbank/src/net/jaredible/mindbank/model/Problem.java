package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class Problem {

	private int id;
	private String title;
	private String content;
	private boolean edited;
	private Timestamp createdTime;
	private Timestamp lastEditTime;

	public Problem() {
	}

	public Problem(int id, String title, String content, boolean edited, Timestamp createdTime, Timestamp lastEditTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.edited = edited;
		this.createdTime = createdTime;
		this.lastEditTime = lastEditTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Timestamp getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Timestamp lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
