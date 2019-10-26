package main.java.mindbank.model;

import java.sql.Timestamp;

public class User {

	private int id;
	private int roleId;
	private String firstName;
	private String lastName;
	private String displayName;
	private String email;
	private String phoneNumber;
	private String password;
	private boolean emailVerified;
	private boolean phoneVerified;
	private Timestamp registrationTimestamp;
	private Timestamp loginTimestamp;

	public User() {
	}

	public User(int id, int roleId, String firstName, String lastName, String displayName, String email, String phoneNumber, String password, boolean emailVerified, boolean phoneVerified, Timestamp registrationTimestamp, Timestamp loginTimestamp) {
		this.id = id;
		this.roleId = roleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.displayName = displayName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.emailVerified = emailVerified;
		this.phoneVerified = phoneVerified;
		this.registrationTimestamp = registrationTimestamp;
		this.loginTimestamp = loginTimestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public Timestamp getRegistrationTimestamp() {
		return registrationTimestamp;
	}

	public void setRegistrationTimestamp(Timestamp registrationTimestamp) {
		this.registrationTimestamp = registrationTimestamp;
	}

	public Timestamp getLoginTimestamp() {
		return loginTimestamp;
	}

	public void setLoginTimestamp(Timestamp loginTimestamp) {
		this.loginTimestamp = loginTimestamp;
	}

}
