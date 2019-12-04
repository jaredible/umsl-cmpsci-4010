package net.jaredible.mindbank.model.problem;

import java.sql.Timestamp;

import net.jaredible.mindbank.service.CategoryService;
import net.jaredible.mindbank.service.ProblemService;
import net.jaredible.mindbank.service.TagService;
import net.jaredible.mindbank.util.StringUtil;

public class NewProblemModel extends ProblemModel {

	private static final long serialVersionUID = -3312328940368075771L;

	private int[] categoryIds;
	private int[] tagIds;

	private String categoryIdsError;
	private String tagIdsError;

	public NewProblemModel() {
	}

	public NewProblemModel(long id, String title, String content, Timestamp createdTime, long createdByUserId, int[] categoryIds, int[] tagIds) {
		super(id, title, content, createdTime, createdByUserId);
		this.categoryIds = categoryIds;
		this.tagIds = tagIds;
	}

	public void validate(ProblemService problemService, CategoryService categoryService, TagService tagService) {
		super.validate(problemService);

		if (categoryIds == null || categoryIds.length == 0) {
			categoryIdsError = "This field is required";
		}

		if (tagIds == null || tagIds.length == 0) {
			tagIdsError = "This field is required";
		}
	}

	public int[] getCategoryIds() {
		return categoryIds;
	}

	public String getCategoryIdsAsString() {
		return StringUtil.convertIntArrayToString(categoryIds);
	}

	public void setCategoryIds(int[] categoryIds) {
		this.categoryIds = categoryIds;
	}

	public int[] getTagIds() {
		return tagIds;
	}

	public String getTagIdsAsString() {
		return StringUtil.convertIntArrayToString(tagIds);
	}

	public void setTagIds(int[] tagIds) {
		this.tagIds = tagIds;
	}

	public String getCategoryIdsError() {
		return categoryIdsError;
	}

	public String getTagIdsError() {
		return tagIdsError;
	}

	public boolean isValid() {
		return super.isValid() && categoryIdsError == null && tagIdsError == null;
	}

	public ProblemModel getProblemModel() {
		return new ProblemModel(this);
	}

}
