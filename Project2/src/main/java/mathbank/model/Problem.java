package main.java.mathbank.model;

import java.sql.Timestamp;

public class Problem {

	private int id;
	private int categoryId;
	private int createdByUserId;
	private Timestamp createdTimestamp;
	private int updatedByUserId;
	private Timestamp updatedTimestamp;
	private boolean canUpdate;
	private String title;
	private String question;
	private String answer;
	private int viewCount;

	public Problem() {
	}

	public Problem(int id, int categoryId, int createdByUserId, Timestamp createdTimestamp, int updatedByUserId, Timestamp updatedTimestamp, boolean canUpdate, String title, String question, String answer, int viewCount) {
		this.id = id;
		this.categoryId = categoryId;
		this.createdByUserId = createdByUserId;
		this.createdTimestamp = createdTimestamp;
		this.updatedByUserId = updatedByUserId;
		this.updatedTimestamp = updatedTimestamp;
		this.canUpdate = canUpdate;
		this.question = question;
		this.answer = answer;
		this.viewCount = viewCount;
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

	public boolean getCanUpdate() {
		return canUpdate;
	}

	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

}
