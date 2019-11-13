package edu.umsl.java.model;

import java.sql.Timestamp;

public class Problem {

	private int id;
	private int categoryId;
	private String title;
	private String content;
	private Timestamp createdTime;

	public Problem() {
	}

	public Problem(int id, int categoryId, String title, String content, Timestamp createdTime) {
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.content = content;
		this.createdTime = createdTime;
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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}