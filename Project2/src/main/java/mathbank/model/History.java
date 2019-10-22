package main.java.mathbank.model;

import java.sql.Timestamp;

public class History {

	private int id;
	private int userId;
	private Timestamp timestamp;
	private int problemId;

	public History() {
	}

	public History(int id, int userId, Timestamp timestamp, int problemId) {
		this.id = id;
		this.userId = userId;
		this.timestamp = timestamp;
		this.problemId = problemId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

}
