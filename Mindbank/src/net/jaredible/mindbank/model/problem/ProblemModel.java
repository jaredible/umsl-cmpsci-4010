package net.jaredible.mindbank.model.problem;

import java.io.Serializable;
import java.sql.Timestamp;

import net.jaredible.mindbank.service.ProblemService;

public class ProblemModel implements Serializable {

	private static final long serialVersionUID = 5212788150095690704L;

	private long id;
	private String title;
	private String content;
	private Timestamp createdTime;
	private long createdByUserId;

	private String titleError;
	private String contentError;

	public ProblemModel() {
	}

	public ProblemModel(long id, String title, String content, Timestamp createdTime, long createdByUserId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdTime = createdTime;
		this.createdByUserId = createdByUserId;
	}

	public ProblemModel(NewProblemModel newProblem) {
		this(newProblem.getId(), newProblem.getTitle(), newProblem.getContent(), newProblem.getCreatedTime(), newProblem.getCreatedByUserId());
	}

	public void validate(ProblemService problemService) {
		if (title == null || title.isEmpty()) {
			titleError = "This field is required";
		} else if (title.length() > 50) {
			titleError = "Max 50 characters";
		} else if (problemService.getProblemByTitle(title) != null) {
			titleError = "Already exists";
		}

		if (content == null || content.isEmpty()) {
			contentError = "This field is required";
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public long getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public String getTitleError() {
		return titleError;
	}

	public String getContentError() {
		return contentError;
	}

	public boolean isValid() {
		return titleError == null && contentError == null;
	}

}
