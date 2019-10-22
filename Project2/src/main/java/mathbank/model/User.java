package main.java.mathbank.model;

public class User {

	private int id;
	private String firstname;
	private String lastname;
	private int age;
	private String username;
	private String email;
	private String password;
	private String role;
	private boolean verified;

	public User() {
	}

	public User(int id, String firstname, String lastname, int age, String username, String email, String password, String role, boolean verified) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.verified = verified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

}
