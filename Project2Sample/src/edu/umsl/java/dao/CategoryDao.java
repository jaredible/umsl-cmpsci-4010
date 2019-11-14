package edu.umsl.java.dao;

import java.util.Map;

import edu.umsl.java.model.Category;

public interface CategoryDao {

	public void addCategory(Category c);

	public Map<Integer, Category> getCategories();

	public boolean getCategoryIdExists(int i);

}
