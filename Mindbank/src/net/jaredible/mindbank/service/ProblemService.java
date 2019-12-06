package net.jaredible.mindbank.service;

import net.jaredible.mindbank.db.problem.ProblemDao;
import net.jaredible.mindbank.db.problem.ProblemDaoImpl;
import net.jaredible.mindbank.model.problem.NewProblemModel;
import net.jaredible.mindbank.model.problem.ProblemModel;

public class ProblemService {

	private final ProblemDao problemDao = new ProblemDaoImpl();

	public ProblemModel getProblemByTitle(String title) {
		return problemDao.getProblemByTitle(title);
	}

	public void addProblem(NewProblemModel newProblem) {
		ProblemModel problem = newProblem.getProblemModel();

		problemDao.addProblem(problem);
	}

}
