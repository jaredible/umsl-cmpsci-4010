package net.jaredible.mindbank.service;

import java.util.List;

import net.jaredible.mindbank.dao.DAOFactory;
import net.jaredible.mindbank.dao.ProblemDAO;
import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.util.TimeUtil;

public class ProblemService {

	private static ProblemService instance;

	private ProblemDAO problemDAO = DAOFactory.getInstance("mindbank.jndi").getProblemDAO();
	private CategoryService categoryService = CategoryService.getInstance();
	private TagService tagService = TagService.getInstance();

	private ProblemService() {
	}

	public Problem getProblemById(Long id) {
		return problemDAO.find(id);
	}

	public List<Problem> listProblemsByCategoriesTagsUsers(String categories, String tags, String users) {
		String regexCategories = categories != null ? categories.replaceAll(",", "|") : "";
		String regexTags = tags != null ? tags.replace(",", "|") : "";
		String regexUsers = users != null ? users.replace(",", "|") : "";
		return !regexCategories.isEmpty() || !regexTags.isEmpty() || !regexUsers.isEmpty() ? problemDAO.list(regexCategories, regexTags, regexUsers) : problemDAO.list();
	}

	public Problem addNewProblem(String title, String content, String categories, String tags, Long createdByUserId) {
		Problem problem = new Problem();
		problem.setTitle(title);
		problem.setContent(content);
		problem.setCreatedTime(TimeUtil.getNowTime());
		problem.setCreatedByUserId(createdByUserId);
		problemDAO.create(problem);

		String[] cids = categories.split(",");
		if (cids != null && cids.length > 1) {
			for (String cid : cids) {
				categoryService.addNewProblemAssociation(problem.getId(), Long.valueOf(cid));
			}
		}

		String[] tids = tags.split(",");
		if (tids != null && tids.length > 1) {
			for (String tid : tids) {
				tagService.addNewProblemAssociation(problem.getId(), Long.valueOf(tid));
			}
		}

		return problem;
	}

	public void updateProblem(Long id, String content, String categories, String tags) {
		Problem problem = problemDAO.find(id);
		problem.setContent(content);
		problemDAO.update(problem);

		String[] cids = categories.split(",");
		if (cids != null && cids.length > 1) {
			categoryService.deleteAllProblemAssociations(problem.getId());
			for (String cid : cids) {
				categoryService.addNewProblemAssociation(problem.getId(), Long.valueOf(cid));
			}
		}

		String[] tids = tags.split(",");
		if (tids != null && tids.length > 1) {
			tagService.deleteAllProblemAssociations(problem.getId());
			for (String tid : tids) {
				tagService.addNewProblemAssociation(problem.getId(), Long.valueOf(tid));
			}
		}
	}

	public boolean titleExists(String title) {
		return problemDAO.existTitle(title);
	}

	public static ProblemService getInstance() {
		if (instance == null) {
			instance = new ProblemService();
		}
		return instance;
	}

}
