package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class Tag {

	private long id;
	private String name;
	private Timestamp createdTime;
	private long createdByUserId;

	public Tag() {
	}

	public Tag(long id, String name, Timestamp createdTime, long createdByUserId) {
		this.id = id;
		this.name = name;
		this.createdTime = createdTime;
		this.createdByUserId = createdByUserId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
