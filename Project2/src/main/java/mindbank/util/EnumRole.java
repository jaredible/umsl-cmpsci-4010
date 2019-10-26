package main.java.mindbank.util;

public enum EnumRole {

	DEFAULT(0, "DEFAULT", ""),
	USER(1, "USER", ""),
	MOD(2, "MOD", ""),
	ADMIN(3, "ADMIN", "");

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
