package net.jaredible.umsl.cmpsci4010.dao;

import net.jaredible.umsl.cmpsci4010.model.Problem;

public interface ProblemDAO {

	public void addProblem(Problem p);

	public Problem getProblem(int i);

	public void updateProblem(Problem p);

	public void deleteProblem(int i);

}
