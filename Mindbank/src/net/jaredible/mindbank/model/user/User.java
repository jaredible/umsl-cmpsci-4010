package net.jaredible.mindbank.model.user;

import java.sql.Blob;
import java.sql.Timestamp;

public class User {

	private long id;
	private String email;
	private String userName;
	private String name;
	private String bio;
	private Blob profileImage;
	private String statusEmoji;
	private String statusText;
	private Timestamp registeredTime;
	private Timestamp lastLoginTime;
	private boolean emailVerified;
	private String passwordSalt;
	private String passwordHash;
	private String cookieSecretKey;
	private String cookieSelector;
	private String hashedCookieValidator;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getStatusEmoji() {
		return statusEmoji;
	}

	public void setStatusEmoji(String statusEmoji) {
		this.statusEmoji = statusEmoji;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
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

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getCookieSecretKey() {
		return cookieSecretKey;
	}

	public void setCookieSecretKey(String cookieSecretKey) {
		this.cookieSecretKey = cookieSecretKey;
	}

	public String getCookieSelector() {
		return cookieSelector;
	}

	public void setCookieSelector(String cookieSelector) {
		this.cookieSelector = cookieSelector;
	}

	public String getHashedCookieValidator() {
		return hashedCookieValidator;
	}

	public void setHashedCookieValidator(String hashedCookieValidator) {
		this.hashedCookieValidator = hashedCookieValidator;
	}

}
