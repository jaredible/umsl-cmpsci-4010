package net.jaredible.mindbank.dao.category;

import java.util.List;

import net.jaredible.mindbank.model.Category;

public interface CategoryDao {

	Category getCategoryById(long i);

	List<Category> getAllCategories();

	int addCategory(Category c);

	int deleteCategoryById(long i);

	boolean getCategoryExistsByName(String s);

}
