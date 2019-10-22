package main.java.mathbank.model;

import java.sql.Timestamp;

public class Problem {

	private int id;
	private int categoryId;
	private int createdByUserId;
	private Timestamp createdTimestamp;
	private int updatedByUserId;
	private Timestamp updatedTimestamp;
	private boolean updateable;
	private String title;
	private String body;
	private String footer;

	public Problem() {
	}

	public Problem(int id, int categoryId, String title, String body, String footer) {
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.body = body;
		this.footer = footer;
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

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void getCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public int getUpdatedByUserId() {
		return createdByUserId;
	}

	public void getUpdatedByUserId(int updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
	}

	public Timestamp getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public boolean isUpdateable() {
		return updateable;
	}

	public void setUpdateable(boolean updateable) {
		this.updateable = updateable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

}
