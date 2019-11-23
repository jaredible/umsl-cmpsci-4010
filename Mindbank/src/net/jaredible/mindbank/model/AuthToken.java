package net.jaredible.mindbank.model;

import java.sql.Timestamp;

public class AuthToken {

	public static final int ID = 1;
	public static final int USER_ID = 2;
	public static final int SELECTOR = 3;
	public static final int VALIDATOR = 4;
	public static final int CREATED_TIME = 5;

	private long id;
	private int userId;
	private String selector;
	private String validator;
	private Timestamp createdTime;

	public AuthToken() {
	}

	public AuthToken(long id, int userId, String selector, String validator, Timestamp createdTime) {
		super();
		this.id = id;
		this.userId = userId;
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
