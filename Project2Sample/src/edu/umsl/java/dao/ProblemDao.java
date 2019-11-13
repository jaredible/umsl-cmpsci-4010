package edu.umsl.java.dao;

import java.util.List;

import edu.umsl.java.model.Problem;

public interface ProblemDao {

	public void addProblem(Problem p);

	public List<Problem> getProblems();

	public boolean getProblemIdExists(int i);

	public boolean getTitleExists(String s);

	public Problem getProblemById(int i);

}
