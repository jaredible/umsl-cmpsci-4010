package edu.umsl.java.model;

import java.sql.Timestamp;

public class Tracking {

	private int id;
	private int trackingType;
	private String ip;
	private String userAgent;
	private Timestamp createdTime;
	private int previousTrackingId;

	public Tracking() {
	}

	public Tracking(int id, int trackingType, String ip, String userAgent, Timestamp createdTime, int previousTrackingId) {
		this.id = id;
		this.trackingType = trackingType;
		this.ip = ip;
		this.userAgent = userAgent;
		this.createdTime = createdTime;
		this.previousTrackingId = previousTrackingId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrackingType() {
		return trackingType;
	}

	public void setTrackingType(int trackingType) {
		this.trackingType = trackingType;
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

	public int getPreviousTrackingId() {
		return previousTrackingId;
	}

	public void setPreviousTrackingId(int previousTrackingId) {
		this.previousTrackingId = previousTrackingId;
	}

}
