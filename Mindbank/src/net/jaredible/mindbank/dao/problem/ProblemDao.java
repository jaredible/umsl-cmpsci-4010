package net.jaredible.mindbank.dao.problem;

import java.util.List;

import net.jaredible.mindbank.model.Problem;

public interface ProblemDao {

	Problem getProblemById(long i);

	List<Problem> getAllProblems();

	long addProblem(Problem p);

	int updateProblem(Problem p);

	int deleteProblemById(long i);

	boolean getProblemExistsById(long i);

}
