package edu.umsl.java.dao.problem;

import java.util.List;

import edu.umsl.java.model.Problem;

public interface ProblemDao {

	public int addProblem(Problem p);

	public List<Problem> getProblems();

	public List<Problem> getProblemsByCategoryId(int i);

	public List<Problem> getProblemsByTagNames(String s);

	public List<Problem> getProblemsByCategoryIdOrTagNames(int i, String s);

	public boolean getProblemIdExists(int i);

	public boolean getTitleExists(String s);

	public Problem getProblemById(int i);

	public void updateProblem(Problem p);

	public void incrementViewCountById(int i);

	public void deleteProblemById(int i);

}
