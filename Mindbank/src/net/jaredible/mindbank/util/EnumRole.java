package net.jaredible.mindbank.util;

public enum EnumRole {

	DEFAULT(0, "DEFAULT", ""),
	ADMIN(1, "ADMIN", "");

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
