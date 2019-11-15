package edu.umsl.java.controller.problem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.CategoryDao;
import edu.umsl.java.dao.CategoryDaoImpl;
import edu.umsl.java.dao.ProblemDao;
import edu.umsl.java.dao.ProblemDaoImpl;
import edu.umsl.java.model.Category;
import edu.umsl.java.model.Problem;

/**
 * Servlet implementation class ProblemListServlet
 */
@WebServlet("/problemList")
public class ProblemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProblemListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("id");

			ProblemDao problemDao = new ProblemDaoImpl();
			CategoryDao categoryDao = new CategoryDaoImpl();

			Map<String, String> errors = new HashMap<String, String>();

			if (categoryId != null) {
				int id = 0;

				try {
					id = Integer.parseInt(categoryId);
					if (id > 0) {
						if (!categoryDao.getCategoryIdExists(id)) {
							errors.put("categoryId", "Does not exist!");
						}
					} else if (id == 0) {
						response.sendRedirect("problemList");
						return;
					} else {
						errors.put("categoryId", "Invalid id!");
					}
				} catch (Exception e) {
					errors.put("categoryId", "Expected a number!");
				}

				if (errors.isEmpty()) {
					List<Problem> problems = problemDao.getProblemsByCategoryId(id);
					request.setAttribute("problems", problems);
				} else {
					request.setAttribute("errors", errors);
					List<Problem> problems = problemDao.getProblems();
					request.setAttribute("problems", problems);
				}
			} else {
				List<Problem> problems = problemDao.getProblems();
				request.setAttribute("problems", problems);
			}

			List<Category> categories = categoryDao.getCategories();

			request.setAttribute("categories", categories);
			request.setAttribute("categoryId", categoryId);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/problemList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
