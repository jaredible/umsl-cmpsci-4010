package edu.umsl.java.controller;

import java.io.IOException;
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
 * Servlet implementation class ListServlet
 */
@WebServlet("/test")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProblemDao problemDao = new ProblemDaoImpl();
			CategoryDao categoryDao = new CategoryDaoImpl();

			List<Problem> problems = problemDao.getProblems();
			Map<Integer, Category> categories = categoryDao.getCategories();

			request.setAttribute("problems", problems);
			request.setAttribute("categories", categories);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
