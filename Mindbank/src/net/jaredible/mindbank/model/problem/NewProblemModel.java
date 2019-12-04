package net.jaredible.mindbank.model.problem;

import java.sql.Timestamp;

public class NewProblemModel {

	private long id;
	private String title;
	private String content;
	private Timestamp createdTime;
	private long createdByUserId;
	private int[] categoryIds;
	private int[] tagIds;

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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public long getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public int[] getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(int[] categoryIds) {
		this.categoryIds = categoryIds;
	}

	public int[] getTagIds() {
		return tagIds;
	}

	public void setTagIds(int[] tagIds) {
		this.tagIds = tagIds;
	}

}
