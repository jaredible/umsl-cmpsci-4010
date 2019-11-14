package edu.umsl.java.util;

public enum TrackingType {

	VIEW(0, "VIEW", ""),
	PROBLEM(1, "PROBLEM", ""),
	CATEGORY(2, "CATEGORY", "");

	private int id;
	private String name;
	private String description;

	private TrackingType(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
