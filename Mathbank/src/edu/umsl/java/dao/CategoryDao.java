package edu.umsl.java.dao;

import java.util.List;

import edu.umsl.java.model.Category;

public interface CategoryDao {

	public int addCategory(Category c);

	public List<Category> getCategories();

	public boolean getCategoryIdExists(int i);

	public boolean getNameExists(String s);

	public Category getCategoryById(int i);

	public void updateCategory(Category c);

}
