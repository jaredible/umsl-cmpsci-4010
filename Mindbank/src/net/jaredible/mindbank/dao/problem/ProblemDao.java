package net.jaredible.mindbank.dao.problem;

import java.util.List;

import net.jaredible.mindbank.model.Problem;

public interface ProblemDao {

	Problem getProblemById(long id);

	List<Problem> getAllProblems();

	List<Problem> getProblemsByFields(String titleLike, String categoryIdsRegex, String tagIdsRegex, String contentLike, String dateCreatedStart, String dateCreatedEnd, String userIdsRegex);

	long addProblem(Problem problem);

	int updateProblem(Problem problem);

	int deleteProblemById(long id);

	boolean getProblemExistsById(long id);

}
