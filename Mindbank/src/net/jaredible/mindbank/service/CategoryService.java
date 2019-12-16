package net.jaredible.mindbank.service;

import java.util.List;

import net.jaredible.mindbank.dao.CategoryDAO;
import net.jaredible.mindbank.dao.DAOFactory;
import net.jaredible.mindbank.model.Category;
import net.jaredible.mindbank.util.TimeUtil;

public class CategoryService {

	private static CategoryService instance;

	private CategoryDAO categoryDAO = DAOFactory.getInstance("mindbank.jndi").getCategoryDAO();

	private CategoryService() {
	}

	public List<Category> listAllCategories() {
		return categoryDAO.list();
	}

	public List<Category> listCategoriesByProblemId(Long problemId) {
		return categoryDAO.list(problemId);
	}

	public void addNewCategory(String name, Long createdByUserId) {
		Category category = new Category();
		category.setName(name);
		category.setCreatedTime(TimeUtil.getNowTime());
		category.setCreatedByUserId(createdByUserId);
		categoryDAO.create(category);
	}

	public void addNewProblemAssociation(Long problemId, Long categoryId) {
		categoryDAO.createProblemAssociation(problemId, categoryId);
	}

	public void deleteAllProblemAssociations(Long problemId) {
		categoryDAO.deleteProblemAssociations(problemId);
	}

	public boolean nameExists(String name) {
		return categoryDAO.existName(name);
	}

	public static CategoryService getInstance() {
		if (instance == null) {
			instance = new CategoryService();
		}
		return instance;
	}

}
