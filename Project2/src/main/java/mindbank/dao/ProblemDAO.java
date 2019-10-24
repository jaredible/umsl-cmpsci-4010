package main.java.mindbank.dao;

import main.java.mindbank.model.Problem;

public interface ProblemDAO {

	public void addProblem(Problem p);

	public Problem getProblem(int i);

	public void updateProblem(Problem p);

	public void deleteProblem(int i);

}
