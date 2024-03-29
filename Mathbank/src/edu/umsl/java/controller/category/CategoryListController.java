package edu.umsl.java.controller.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.category.CategoryDao;
import edu.umsl.java.dao.category.CategoryDaoImpl;
import edu.umsl.java.model.Category;

/**
 * Servlet implementation class CategoryListController
 */
@WebServlet("/categoryList")
public class CategoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryListController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryDao categoryDao = new CategoryDaoImpl();

			List<Category> categories = categoryDao.getCategories();

			request.setAttribute("categories", categories);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/category/categoryList.jsp").forward(request, response);
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
