package edu.umsl.java.controller.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.category.CategoryDao;
import edu.umsl.java.dao.category.CategoryDaoImpl;
import edu.umsl.java.dao.problem.ProblemDao;
import edu.umsl.java.dao.problem.ProblemDaoImpl;
import edu.umsl.java.model.Category;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/category")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("id");

			if (categoryId == null) {
				response.sendRedirect("categoryList");
				return;
			} else {
				ProblemDao problemDao = new ProblemDaoImpl();
				CategoryDao categoryDao = new CategoryDaoImpl();

				int id = 0;

				try {
					id = Integer.parseInt(categoryId);
					if (id > 0) {
						if (categoryDao.getCategoryIdExists(id)) {
							Category category = categoryDao.getCategoryById(id);

							request.setAttribute("category", category);
							request.setAttribute("canDelete", problemDao.getProblemsByCategoryId(id).isEmpty());
							getServletContext().getRequestDispatcher("/WEB-INF/jsp/category/category.jsp").forward(request, response);
						} else {
							response.sendRedirect("categoryList");
						}
					} else {
						response.sendRedirect("categoryList");
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("categoryList");
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
