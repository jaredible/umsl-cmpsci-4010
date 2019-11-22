package net.jaredible.mindbank.dao.problem;

import java.util.List;

import net.jaredible.mindbank.model.Problem;

public class ProblemDaoImpl extends ProblemDao {

	public ProblemDaoImpl() throws Exception {
		super();
	}

	public Problem getProblemById(long id) {
		return get(id);
	}

	public List<Problem> getAllProblems() {
		return getAll();
	}

	public boolean addProblem(Problem problem) {
		return save(problem) > 0;
	}

	public boolean updateProblem(Problem problem, int[] params) {
		return update(problem, params) > 0;
	}

	public boolean deleteProblemById(long id) {
		return delete(id) > 0;
	}

	public boolean getProblemExistsById(long id) {
		return get(id) != null;
	}

}
