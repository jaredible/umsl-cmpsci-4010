package edu.umsl.java.controller.problem;

import java.io.IOException;
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
			String problemId = request.getParameter("id");

			if (problemId == null) {
				response.sendRedirect("problemList");
				return;
			} else {
				ProblemDao problemDao = new ProblemDaoImpl();
				CategoryDao categoryDao = new CategoryDaoImpl();

				int id = 0;

				try {
					id = Integer.parseInt(problemId);
					if (id > 0) {
						if (problemDao.getProblemIdExists(id)) {
							Problem problem = problemDao.getProblemById(id);
							Map<Integer, Category> categories = categoryDao.getCategories();

							request.setAttribute("problem", problem);
							request.setAttribute("categories", categories);
							getServletContext().getRequestDispatcher("/problem.jsp").forward(request, response);
						} else {
							response.sendRedirect("problemList");
						}
					} else {
						response.sendRedirect("problemList");
					}
				} catch (Exception e) {
					response.sendRedirect("problemList");
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
		doGet(request, response);
	}

}
