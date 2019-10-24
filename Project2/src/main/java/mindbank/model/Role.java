package main.java.mindbank.model;

public class Role {

	public static final Role DEFAULT = new Role(0, "default", "");
	public static final Role USER = new Role(1, "user", "");
	public static final Role MODERATOR = new Role(2, "moderator", "");
	public static final Role ADMIN = new Role(3, "admin", "");

	private int id;
	private String name;
	private String description;

	public Role() {
	}

	public Role(int id, String name, String description) {
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
