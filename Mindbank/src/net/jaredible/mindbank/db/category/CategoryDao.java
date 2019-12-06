package net.jaredible.mindbank.db.category;

import java.util.List;

import net.jaredible.mindbank.model.category.CategoryModel;

public interface CategoryDao {

	CategoryModel getCategoryById(long id);

	CategoryModel getCategoryByName(String name);

	List<CategoryModel> getAllCategories();

	List<CategoryModel> getCategoriesByProblemId(long id);

	long addCategory(CategoryModel category);

}
