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
			
			System.out.println("categoryId: " + categoryId + ", tagNames: " + tagNames);

			ProblemDao problemDao = new ProblemDaoImpl();
			CategoryDao categoryDao = new CategoryDaoImpl();
			TagDao tagDao = new TagDaoImpl();

			List<Problem> problems;
			List<Category> categories = categoryDao.getCategories();
			List<Tag> tags = tagDao.getTags();

			Map<String, String> errors = new HashMap<String, String>();

			if ((categoryId == null || categoryId.isEmpty()) && (tagNames == null || tagNames.isEmpty())) {
				problems = problemDao.getProblems();
			} else {
				int id = -1;
				String names = null;

				if (categoryId != null) {
					try {
						id = Integer.parseInt(categoryId);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					errors.put("categoryId", "Select a category!");
				}
				if (tagNames != null) {
					names = tagNames.replaceAll(" ", "").replaceAll(",", "|");
				}

				problems = problemDao.getProblemsByCategoryIdOrTagNames(id, names);
			}

			request.setAttribute("errors", errors);
			request.setAttribute("problems", problems);
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
