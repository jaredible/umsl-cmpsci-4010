package main.java.mindbank.model;

import java.sql.Timestamp;

import main.java.mindbank.dao.CategoryDAO;
import main.java.mindbank.dao.ProblemDAO;

public class Problem {

	private int id;
	private int categoryId;
	private String title;
	private String content;
	private boolean edited;
	private int createdByUserId;
	private Timestamp createdTimestamp;

	public Problem() {
	}

	public Problem(int id, int categoryId, String title, String content, boolean edited, int createdByUserId, Timestamp createdTimestamp) {
		this.id = id;
		this.categoryId = categoryId;
		this.content = content;
		this.edited = edited;
		this.createdByUserId = createdByUserId;
		this.createdTimestamp = createdTimestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

}
