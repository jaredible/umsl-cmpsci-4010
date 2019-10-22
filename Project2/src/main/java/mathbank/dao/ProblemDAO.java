package main.java.mathbank.dao;

import main.java.mathbank.model.Problem;

public interface ProblemDAO {

	public void addProblem(Problem p);

	public Problem getProblem(int i);

	public void updateProblem(Problem p);

	public void deleteProblem(int i);

}
