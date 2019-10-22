package net.jaredible.umsl.cmpsci4010.dao;

import java.util.List;

import net.jaredible.umsl.cmpsci4010.model.Category;

public interface CategoryDAO {

	public List<Category> getList();

	public void addCategory(Category c);

	public Category getCategory(int i);

	public void updateCategory(Category c);

	public void deleteCategory(int i);

}
