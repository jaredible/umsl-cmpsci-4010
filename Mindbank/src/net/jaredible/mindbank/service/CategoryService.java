package net.jaredible.mindbank.service;

import java.util.List;

import net.jaredible.mindbank.db.category.CategoryDao;
import net.jaredible.mindbank.db.category.CategoryDaoImpl;
import net.jaredible.mindbank.model.category.CategoryModel;

public class CategoryService {

	private final CategoryDao categoryDao = new CategoryDaoImpl();

	public List<CategoryModel> getAllCategories() {
		return categoryDao.getAllCategories();
	}

}
