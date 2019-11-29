package net.jaredible.mindbank.dao.problem;

import java.util.List;

import net.jaredible.mindbank.model.Problem;

public interface ProblemDao {

	Problem getProblemById(long id);

	Problem getProblemByTitle(String title);

	List<Problem> getAllProblems();

	List<Problem> getProblemsByFields(String titleLike, String categoryIdsRegex, String tagIdsRegex, String contentLike, String dateCreatedStart, String dateCreatedEnd, String userIdsRegex);

	long addProblem(Problem problem);

	boolean getProblemExistsById(long id);

}
