package net.jaredible.mindbank.model;

public class ProblemInfo {

	private Problem problem;
	private User user;

	public ProblemInfo() {
	}

	public ProblemInfo(Problem problem, User user) {
		this.problem = problem;
		this.user = user;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
