package net.jaredible.mindbank.dao.category;

import java.util.List;

import net.jaredible.mindbank.model.Category;

public interface CategoryDao {

	Category getCategoryById(long id);

	Category getCategoryByName(String name);

	List<Category> getAllCategories();

	long addCategory(Category category);

}
