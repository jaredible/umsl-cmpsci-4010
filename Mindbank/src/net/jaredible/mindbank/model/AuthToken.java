package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class AuthToken {

	private long id;
	private long userId;
	private String secretKey;
	private String selector;
	private String validator;
	private Timestamp createdTime;

	public AuthToken() {
	}

	public AuthToken(long id, long userId, String secretKey, String selector, String validator, Timestamp createdTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.secretKey = secretKey;
		this.selector = selector;
		this.validator = validator;
		this.createdTime = createdTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}
