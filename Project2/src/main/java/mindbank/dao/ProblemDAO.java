package main.java.mindbank.dao;

import main.java.mindbank.model.Problem;
import main.java.mindbank.util.ProblemList;

public interface ProblemDAO {

	public ProblemList getList();

	public void addProblem(Problem p);

	public Problem getProblem(int i);

	public void updateProblem(Problem p);

	public void deleteProblem(int i);

}
