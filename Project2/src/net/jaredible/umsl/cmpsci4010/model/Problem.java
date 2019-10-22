package net.jaredible.umsl.cmpsci4010.model;

public class Problem {

	private int id;
	private int categoryId;
	private String title;
	private String body;
	private String footer;

	public Problem() {
	}

	public Problem(int id, int categoryId, String title, String body, String footer) {
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.body = body;
		this.footer = footer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

}
