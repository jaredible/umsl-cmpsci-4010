package main.java.mindbank.dao;

import java.sql.Connection;

import main.java.mindbank.model.Problem;
import main.java.mindbank.util.ProblemInfoList;
import main.java.mindbank.util.ProblemList;

public interface ProblemDAO {

	public ProblemInfoList getProblemsWithLimit(int i, int j);

	public ProblemList getProblemsByCategoryIdWithLimit(int i, int j, int k);

	public void addProblem(Problem p);

	public Problem getProblemById(int i);

	public void deleteProblemById(int i);

	public Connection getConnection();

}
