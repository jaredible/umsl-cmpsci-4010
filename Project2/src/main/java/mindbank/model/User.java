package main.java.mindbank.model;

import java.sql.Timestamp;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String displayName;
	private String email;
	private String phoneNumber;
	private String password;
	private int roleId;
	private boolean emailVerified;
	private boolean phoneVerified;
	private Timestamp registrationTimestamp;
	private Timestamp lastLoginTimestamp;

	public User() {
	}

	public User(int id, String firstName, String lastName, String displayName, String email, String phoneNumber, String password, int roleId, boolean emailVerified, boolean phoneVerified, Timestamp registrationTimestamp, Timestamp lastLoginTimestamp) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.displayName = displayName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.roleId = roleId;
		this.emailVerified = emailVerified;
		this.phoneVerified = phoneVerified;
		this.registrationTimestamp = registrationTimestamp;
		this.lastLoginTimestamp = lastLoginTimestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public Timestamp getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(Timestamp lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
	}

}
