package net.jaredible.mindbank.servlet.problem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.dao.category.CategoryDao;
import net.jaredible.mindbank.dao.category.CategoryDaoImpl;
import net.jaredible.mindbank.dao.problem.ProblemDao;
import net.jaredible.mindbank.dao.problem.ProblemDaoImpl;
import net.jaredible.mindbank.dao.tag.TagDao;
import net.jaredible.mindbank.dao.tag.TagDaoImpl;
import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;

@WebServlet("/problems")
public class ProblemListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProblemListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProblemDao problemDao = new ProblemDaoImpl();
		CategoryDao categoryDao = new CategoryDaoImpl();
		TagDao tagDao = new TagDaoImpl();
		UserDao userDao = new UserDaoImpl();

		request.setAttribute("problems", problemDao.getAllProblems());
		request.setAttribute("categories", categoryDao.getAllCategories());
		request.setAttribute("tags", tagDao.getAllTags());
		request.setAttribute("users", userDao.getAllUsers());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/problemList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String[] categoryIds = request.getParameterValues("categoryIds");
		String[] tagIds = request.getParameterValues("tagIds");
		String content = request.getParameter("content");
		String dateStart = request.getParameter("dateStart");
		String dateEnd = request.getParameter("dateEnd");
		String[] userIds = request.getParameterValues("userIds");

		System.out.println(title);
		for (String categoryId : categoryIds) {
			System.out.println(categoryId);
		}
		for (String tagId : tagIds) {
			System.out.println(tagId);
		}
		System.out.println(content);
		System.out.println(dateStart);
		System.out.println(dateEnd);
		for (String userId : userIds) {
			System.out.println(userId);
		}

		request.setAttribute("title", title);
		request.setAttribute("content", content);
		doGet(request, response);
	}

}
