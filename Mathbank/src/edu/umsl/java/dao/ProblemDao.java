package edu.umsl.java.dao;

import java.util.List;

import edu.umsl.java.model.Problem;

public interface ProblemDao {

	public int addProblem(Problem p);

	public List<Problem> getProblems();

	public List<Problem> getProblemsByCategoryId(int i);

	public boolean getProblemIdExists(int i);

	public boolean getTitleExists(String s);

	public Problem getProblemById(int i);

	public void updateProblem(Problem p);

	public void incrementViewCountById(int i);

}
