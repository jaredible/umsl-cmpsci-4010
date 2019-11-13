package edu.umsl.java.dao;

import java.util.List;

import edu.umsl.java.model.Category;

public interface CategoryDao {

	public void addCategory(Category c);

	public List<Category> getCategories();

}
