package edu.umsl.java.model;

import java.sql.Timestamp;

public class Problem {

	private int id;
	private int categoryId;
	private String title;
	private String content;
	private String passwordHash;
	private Timestamp createdTime;
	private Timestamp lastEditTime;
	private boolean edited;
	private int viewCount;
	private int trackingId;

	public Problem() {
	}

	public Problem(int id, int categoryId, String title, String content, String passwordHash, Timestamp createdTime, Timestamp lastEditTime, boolean edited, int viewCount, int trackingId) {
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.content = content;
		this.passwordHash = passwordHash;
		this.createdTime = createdTime;
		this.lastEditTime = lastEditTime;
		this.edited = edited;
		this.viewCount = viewCount;
		this.trackingId = trackingId;
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}

}
