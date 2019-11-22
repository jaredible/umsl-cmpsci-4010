package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class AuthToken {

	private int id;
	private int userId;
	private String selector;
	private String validator;
	private Timestamp createdTime;

	public AuthToken() {
	}

	public AuthToken(int id, int userId, String selector, String validator, Timestamp createdTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.selector = selector;
		this.validator = validator;
		this.createdTime = createdTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
