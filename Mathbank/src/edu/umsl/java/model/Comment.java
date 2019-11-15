package edu.umsl.java.model;

import java.sql.Timestamp;

public class Comment {

	private int id;
	private int problemId;
	private String content;
	private Timestamp createdTime;
	private int trackingId;

	public Comment() {
	}

	public Comment(int id, int problemId, String content, Timestamp createdTime, int trackingId) {
		this.id = id;
		this.problemId = problemId;
		this.content = content;
		this.createdTime = createdTime;
		this.trackingId = trackingId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
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

	public int getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}

}
