package net.jaredible.mindbank.viewmodel;

import java.util.Map;

import net.jaredible.mindbank.model.User;

public class ProblemViewModel {

	private ProblemViewModel problem;
	private Map<String, String> categories;
	private Map<String, String> tags;
	private User createdByUser;

	public ProblemViewModel getProblem() {
		return problem;
	}

	public void setProblem(ProblemViewModel problem) {
		this.problem = problem;
	}

	public Map<String, String> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

}
