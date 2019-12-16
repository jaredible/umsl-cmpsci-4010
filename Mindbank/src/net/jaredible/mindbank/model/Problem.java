package net.jaredible.mindbank.model;

import java.io.Serializable;
import java.util.Date;

public class Problem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String content;
	private Date createdTime;
	private Long createdByUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return (other instanceof Problem) && (id != null) ? id.equals(((Problem) other).id) : (other == null);
	}

	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
	}

	@Override
	public String toString() {
		return String.format("Problem[id=%d,title=%s,content=%s,createdTime=%s,createdByUserId=%d]", id, title, content, createdTime, createdByUserId);
	}

}
