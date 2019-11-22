package net.jaredible.mindbank.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class User {

	private int id;
	private int roleId;
	private String email;
	private String userName;
	private String name;
	private String bio;
	private Blob profileImage;
	private String phoneNumber;
	private String passwordHash;
	private boolean emailVerified;
	private boolean phoneNumberVerified;
	private Timestamp registrationTimestamp;
	private Timestamp loginTimestamp;

	public User() {
	}

	public User(int id, int roleId, String email, String userName, String name, String bio, Blob profileImage, String phoneNumber, String passwordHash, boolean emailVerified, boolean phoneNumberVerified, Timestamp registrationTimestamp, Timestamp loginTimestamp) {
		this.id = id;
		this.roleId = roleId;
		this.email = email;
		this.userName = userName;
		this.name = name;
		this.bio = bio;
		this.profileImage = profileImage;
		this.phoneNumber = phoneNumber;
		this.passwordHash = passwordHash;
		this.emailVerified = emailVerified;
		this.phoneNumberVerified = phoneNumberVerified;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Blob getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isPhoneNumberVerified() {
		return phoneNumberVerified;
	}

	public void setPhoneNumberVerified(boolean phoneNumberVerified) {
		this.phoneNumberVerified = phoneNumberVerified;
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
