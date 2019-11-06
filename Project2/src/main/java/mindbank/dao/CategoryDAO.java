package main.java.mindbank.dao;

import java.sql.Connection;

import main.java.mindbank.model.Category;
import main.java.mindbank.util.CategoryList;

public interface CategoryDAO {

	public boolean getCategoryExistsById(int i);

	public CategoryList getCategories();

	public void addCategory(Category c);

	public Category getCategory(int i);

	public void updateCategory(Category c);

	public void deleteCategory(int i);

	public Connection getConnection();

}
