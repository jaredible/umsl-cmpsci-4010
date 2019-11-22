package net.jaredible.mindbank.dao;

import java.sql.Connection;

import net.jaredible.mindbank.model.Category;
import net.jaredible.mindbank.util.CategoryList;

public interface CategoryDAO {

	// create
	public void addCategory(Category c);

	// read
	public CategoryList getCategories();
	public Category getCategory(int i);
	public boolean getCategoryExistsByName(String s);

	// update

	// delete
	public void deleteCategoryById(int i);
	
	public boolean getCategoryExistsById(int i);
	
	public Connection getConnection();

	public void closeConnections();

}
