package net.jaredible.mindbank.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String userName;
	private String name;
	private String bio;
	private String statusEmoji;
	private String statusText;
	private Date registeredTime;
	private Date lastLoginTime;
	private String passWordSalt;
	private String passWordHash;
	private String cookieSecretKey;
	private String cookieSelector;
	private String hashedCookieValidator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPassWordSalt() {
		return passWordSalt;
	}

	public void setPassWordSalt(String passWordSalt) {
		this.passWordSalt = passWordSalt;
	}

	public String getPassWordHash() {
		return passWordHash;
	}

	public void setPassWordHash(String passWordHash) {
		this.passWordHash = passWordHash;
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

	@Override
	public boolean equals(Object other) {
		return (other instanceof User) && (id != null) ? id.equals(((User) other).id) : (other == null);
	}

	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
	}

	@Override
	public String toString() {
		return String.format("User[id=%d,email=%s,userName=%s,name=%s]", id, email, userName, name);
	}

}
