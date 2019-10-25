package main.java.mindbank.model;

import java.sql.Timestamp;

public class Problem {

	private int id;
	private int subjectId;
	private int categoryId;
	private int createdByUserId;
	private Timestamp createdTimestamp;
	private int updatedByUserId;
	private Timestamp updatedTimestamp;
	private String title;
	private String content;

	public Problem() {
	}

	public Problem(int id, int subjectId, int categoryId, int createdByUserId, Timestamp createdTimestamp, int updatedByUserId, Timestamp updatedTimestamp, String title, String content) {
		this.id = id;
		this.subjectId = subjectId;
		this.categoryId = categoryId;
		this.createdByUserId = createdByUserId;
		this.createdTimestamp = createdTimestamp;
		this.updatedByUserId = updatedByUserId;
		this.updatedTimestamp = updatedTimestamp;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
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
		return updatedByUserId;
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

}
