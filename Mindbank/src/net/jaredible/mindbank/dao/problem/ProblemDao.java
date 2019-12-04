package net.jaredible.mindbank.dao.problem;

import java.util.List;

import net.jaredible.mindbank.model.problem.ProblemModel;

public interface ProblemDao {

	ProblemModel getProblemById(long id);

	ProblemModel getProblemByTitle(String title);

	List<ProblemModel> getAllProblems();

	List<ProblemModel> getProblemsByFields(String titleLike, String categoryIdsRegex, String tagIdsRegex, String contentLike, String dateCreatedStart, String dateCreatedEnd, String userIdsRegex);

	long addProblem(ProblemModel problem);

	boolean getProblemExistsById(long id);

}
