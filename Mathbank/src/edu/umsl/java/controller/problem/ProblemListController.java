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

import edu.umsl.java.dao.category.CategoryDao;
import edu.umsl.java.dao.category.CategoryDaoImpl;
import edu.umsl.java.dao.problem.ProblemDao;
import edu.umsl.java.dao.problem.ProblemDaoImpl;
import edu.umsl.java.dao.tag.TagDao;
import edu.umsl.java.dao.tag.TagDaoImpl;
import edu.umsl.java.model.Category;
import edu.umsl.java.model.Problem;
import edu.umsl.java.model.Tag;

/**
 * Servlet implementation class ProblemListController
 */
@WebServlet("/problemList")
public class ProblemListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProblemListController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("categoryId");
			String tagNames = request.getParameter("tagNames");

			ProblemDao problemDao = new ProblemDaoImpl();
			CategoryDao categoryDao = new CategoryDaoImpl();
			TagDao tagDao = new TagDaoImpl();

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
			}
			if (tagNames != null) {
				List<Problem> problems = problemDao.getProblemsByTagNames(tagNames.replaceAll("\\s", "").replaceAll(",", "|"));
				request.setAttribute("problems", problems);
			}
			if (categoryId == null && tagNames == null) {
				List<Problem> problems = problemDao.getProblems();
				request.setAttribute("problems", problems);
			}

			List<Category> categories = categoryDao.getCategories();
			List<Tag> tags = tagDao.getTags();

			request.setAttribute("categories", categories);
			request.setAttribute("tags", tags);
			request.setAttribute("categoryId", categoryId);
			request.setAttribute("tagNames", tagNames);
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
