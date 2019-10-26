package main.java.mindbank.dao;

import java.sql.Connection;

import main.java.mindbank.model.Subject;
import main.java.mindbank.util.SubjectList;

public interface SubjectDAO {

	public SubjectList getSubjects();

	public void addSubject(Subject s);

	public Subject getSubject(int i);

	public void updateSubject(Subject s);

	public void deleteSubject(int i);
	
	public Connection getConnection();

}
