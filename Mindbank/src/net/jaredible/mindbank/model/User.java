package net.jaredible.mindbank.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class User {

	private int id;
	private String email;
	private String userName;
	private String name;
	private String bio;
	private Blob profileImage;
	private Timestamp registeredTime;
	private Timestamp lastLoginTime;
	private boolean emailVerified;
	private String passwordHash;

	public User() {
	}

	public User(int id, String email, String userName, String name, String bio, Blob profileImage, Timestamp registeredTime, Timestamp lastLoginTime, boolean emailVerified, String passwordHash) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.name = name;
		this.bio = bio;
		this.profileImage = profileImage;
		this.registeredTime = registeredTime;
		this.lastLoginTime = lastLoginTime;
		this.emailVerified = emailVerified;
		this.passwordHash = passwordHash;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Timestamp getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Timestamp registeredTime) {
		this.registeredTime = registeredTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

}