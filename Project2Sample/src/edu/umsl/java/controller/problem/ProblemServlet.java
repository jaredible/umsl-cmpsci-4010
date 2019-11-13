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
import edu.umsl.java.model.Problem;

/**
 * Servlet implementation class ProblemServlet
 */
@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProblemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String problemId = request.getParameter("problemId");
			String categoryId = request.getParameter("categoryId");

			ProblemDao problemDao = new ProblemDaoImpl();

			if (problemId != null) {
				Map<String, String> errors = new HashMap<String, String>();

				int id = 0;

				try {
					id = Integer.parseInt(problemId);
					if (id > 0) {
						if (!problemDao.getProblemIdExists(id)) {
							errors.put("problemId", "Does not exist!");
						}
					} else {
						errors.put("problemId", "Invalid!");
					}
				} catch (Exception e) {
					errors.put("problemId", "Invalid!");
				}

				if (errors.isEmpty()) {
					Problem problem = problemDao.getProblemById(id);
					request.setAttribute("problem", problem);
					getServletContext().getRequestDispatcher("/problem.jsp").forward(request, response);
				} else {
					// error with id, redirect?
				}
			} else {
				if (categoryId != null) {
					Map<String, String> errors = new HashMap<String, String>();

					int id = 0;

					try {
						id = Integer.parseInt(problemId);
						if (id > 0) {
							if (!problemDao.getProblemIdExists(id)) {
								errors.put("categoryId", "Does not exist!");
							}
						} else {
							errors.put("categoryId", "Invalid!");
						}
					} catch (Exception e) {
						errors.put("categoryId", "Invalid!");
					}
					
					if (errors.isEmpty()) {
						Problem problem = problemDao.getProblemById(id);
						request.setAttribute("problem", problem);
						getServletContext().getRequestDispatcher("/problemList.jsp").forward(request, response);
					} else {
						// error with id, redirect?
					}
				} else {
					List<Problem> problems = problemDao.getProblems();
					request.setAttribute("problems", problems);
					getServletContext().getRequestDispatcher("/problemList.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title = request.getParameter("title");
			String categoryId = request.getParameter("categoryId");
			String content = request.getParameter("content");

			ProblemDao problemDao = new ProblemDaoImpl();
			CategoryDao categoryDao = new CategoryDaoImpl();

			Map<String, String> errors = new HashMap<String, String>();

			int id = 0;

			if (title.isEmpty()) {
				errors.put("title", "Cannot be empty!");
			} else if (title.length() > 50) {
				errors.put("title", "Max length is 50!");
			} else if (problemDao.getTitleExists(title)) {
				errors.put("title", "Already exists!");
			}
			if (categoryId != null) {
				try {
					id = Integer.parseInt(categoryId);
					if (id > 0) {
						if (!categoryDao.getCategoryIdExists(id)) {
							errors.put("categoryId", "Does not exist!");
						}
					} else {
						errors.put("categoryId", "Invalid!");
					}
				} catch (Exception e) {
					errors.put("categoryId", "Invalid!");
				}
			}
			if (content.isEmpty()) {
				errors.put("content", "Cannot be empty!");
			}

			if (errors.isEmpty()) {
				Problem problem = new Problem();
				problem.setTitle(title);
				problem.setCategoryId(id);
				problem.setContent(content);
				problemDao.addProblem(problem);

				response.sendRedirect("problem");
			} else {
				request.setAttribute("title", title);
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("content", content);
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/problem.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
