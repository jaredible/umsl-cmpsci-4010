package net.jaredible.mindbank.dao.problem;

import java.util.List;

import net.jaredible.mindbank.model.Problem;

public interface ProblemDao {

	Problem getProblemById(long i);

	List<Problem> getAllProblems();

	int addProblem(Problem p);

	int updateProblem(Problem p, int[] arr);

	int deleteProblemById(long i);

	boolean getProblemExistsById(long i);

}
