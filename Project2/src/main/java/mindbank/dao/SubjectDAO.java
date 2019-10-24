package main.java.mindbank.dao;

import java.util.List;

import main.java.mindbank.model.Category;
import main.java.mindbank.model.Subject;

public interface SubjectDAO {

	public List<Subject> getList();

	public List<Category> getCategoryList(Subject s);

	public void addSubject(Subject s);

	public Subject getSubject(int i);

	public void updateSubject(Subject s);

	public void deleteSubject(int i);

}
