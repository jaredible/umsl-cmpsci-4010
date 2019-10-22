package main.java.mathbank.dao;

import java.util.List;

import main.java.mathbank.model.Category;

public interface CategoryDAO {

	public List<Category> getList();

	public void addCategory(Category c);

	public Category getCategory(int i);

	public void updateCategory(Category c);

	public void deleteCategory(int i);

}
