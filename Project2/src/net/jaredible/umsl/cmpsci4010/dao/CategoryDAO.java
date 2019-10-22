package net.jaredible.umsl.cmpsci4010.dao;

import java.util.List;

import net.jaredible.umsl.cmpsci4010.model.Category;

public interface CategoryDAO {

	public void addCategory(Category c);

	public Category getCategory(int i);

	public List<Category> getList();

}
