package edu.umsl.java.model;

import java.sql.Timestamp;

public class Category {

	private int id;
	private String name;
	private String description;
	private Timestamp createdTime;
	private int trackingId;

	public Category() {
	}

	public Category(int id, String name, String description, Timestamp createdTime, int trackingId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdTime = createdTime;
		this.trackingId = trackingId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
