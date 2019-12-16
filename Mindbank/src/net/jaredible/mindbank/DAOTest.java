package net.jaredible.mindbank;

import java.text.SimpleDateFormat;
import java.util.List;

import net.jaredible.mindbank.dao.DAOFactory;
import net.jaredible.mindbank.dao.ProblemDAO;
import net.jaredible.mindbank.dao.UserDAO;
import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.model.User;

public class DAOTest {

	public static void main(String[] args) throws Exception {
		DAOFactory mindbank = DAOFactory.getInstance("mindbank.jdbc");
		System.out.println("DAOFactory successfully obtained: " + mindbank);

		ProblemDAO problemDAO = mindbank.getProblemDAO();
		System.out.println("ProblemDAO successfully obtained: " + problemDAO);

		String title = "Test16";

		Problem problem = new Problem();
		problem.setTitle(title);
		problem.setContent("Testing");
		problem.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-12-15 12:30:00"));
		problem.setCreatedByUserId(Long.valueOf(1));
		problemDAO.create(problem);
		System.out.println("Problem successfully created: " + problem);

		problem.setContent("Testing Updated");
		problemDAO.update(problem);
		System.out.println("Problem successfully updated: " + problem);

		problem = problemDAO.find(problem.getId());
		System.out.println("User successfully queried: " + problem);

		problemDAO.delete(problem);
		System.out.println("Problem successfully deleted: " + problem);

		boolean exists = problemDAO.existTitle(title);
		System.out.println("This title exists: " + exists);

		List<Problem> problems = problemDAO.list();
		System.out.println("List of problems successfully queried: " + problems);
		System.out.println("Thus, amount of problems in database is: " + problems.size());

		problems = problemDAO.list("1|3|6|7", "1|3|6|7", "1");
		System.out.println("List of filtered problems successfully queried: " + problems);
		System.out.println("Thus, amount of filtered problems in database is: " + problems.size());

		UserDAO userDAO = mindbank.getUserDAO();
		System.out.println("UserDAO successfully obtained: " + userDAO);

		User user = userDAO.find(Long.valueOf(1));
		System.out.println("User successfully queried: " + user);
		
		user.setName("Testing");
		userDAO.update(user);
		System.out.println("User successfully updated: " + user);
	}

}
