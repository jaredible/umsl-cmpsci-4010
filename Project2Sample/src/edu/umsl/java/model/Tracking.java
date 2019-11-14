package edu.umsl.java.model;

import java.sql.Timestamp;

public class Tracking {

	private int id;
	private String ip;
	private String userAgent;
	private Timestamp createdTime;

	public Tracking() {
	}

	public Tracking(int id, String ip, String userAgent, Timestamp createdTime) {
		this.id = id;
		this.ip = ip;
		this.userAgent = userAgent;
		this.createdTime = createdTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}
