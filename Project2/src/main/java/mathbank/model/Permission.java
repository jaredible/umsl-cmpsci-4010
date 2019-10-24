package main.java.mathbank.model;

public class Permission {

	public static final Permission DEFAULT = new Permission(0, "default", "");
	public static final Permission USER = new Permission(1, "user", "");
	public static final Permission MODERATOR = new Permission(2, "moderator", "");
	public static final Permission ADMIN = new Permission(3, "admin", "");

	private int id;
	private String name;
	private String description;

	public Permission() {
	}

	public Permission(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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

}
