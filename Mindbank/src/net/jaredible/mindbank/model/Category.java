package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class Category {

	public static final int ID = 1;
	public static final int NAME = 2;
	public static final int CREATED_TIME = 3;
	public static final int CREATED_BY_USER_ID = 4;

	private long id;
	private String name;
	private Timestamp createdTime;
	private int createdByUserId;

	public Category() {
	}

	public Category(long id, String name, Timestamp createdTime, int createdByUserId) {
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

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

}
