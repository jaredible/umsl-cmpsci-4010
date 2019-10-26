package main.java.mindbank.util;

public enum EnumRole {

	DEFAULT(0, "default", ""),
	USER(1, "user", ""),
	MODERATOR(2, "moderator", ""),
	ADMIN(3, "admin", "");

	private int id;
	private String name;
	private String description;

	private EnumRole(int id, String name, String description) {
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
