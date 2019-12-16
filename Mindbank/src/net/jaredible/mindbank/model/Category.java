package net.jaredible.mindbank.model;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Date createdTime;
	private Long createdByUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Category) && (id != null) ? id.equals(((Category) other).id) : (other == null);
	}

	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
	}

	@Override
	public String toString() {
		return String.format("Category[id=%d,name=%s,createdTime=%s,createdByUserId=%d]", id, name, createdTime, createdByUserId);
	}

}
