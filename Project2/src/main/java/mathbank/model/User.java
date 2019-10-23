package main.java.mathbank.model;

import java.awt.image.BufferedImage;

public class User {

	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String email;
	private String phoneNumber;
	private String password;
	private String role;
	private boolean emailVerified;
	private boolean phoneVerified;
	private BufferedImage image;

	public User() {
	}

	public User(int id, String firstname, String lastname, String username, String email, String phoneNumber, String password, String role, boolean emailVerified, boolean phoneVerified, BufferedImage image) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
		this.emailVerified = emailVerified;
		this.phoneVerified = phoneVerified;
		this.image = image;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isPhoneVerified() {
		return phoneVerified;
	}

	public void setPhoneVerified(boolean phoneVerified) {
		this.phoneVerified = phoneVerified;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
