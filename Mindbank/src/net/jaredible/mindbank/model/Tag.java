package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class Tag {

	private int id;
	private String name;
	private Timestamp createdTime;

	public Tag() {
	}

	public Tag(int id, String name, Timestamp createdTime) {
		this.id = id;
		this.name = name;
		this.createdTime = createdTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}
