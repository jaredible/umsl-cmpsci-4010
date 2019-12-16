package net.jaredible.mindbank.model;

import java.util.List;

public class ProblemDetail {

	private Problem problem;
	private User createdByUser;
	private List<Category> categories;
	private List<Tag> tags;

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
