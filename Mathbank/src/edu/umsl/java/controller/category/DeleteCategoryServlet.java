package edu.umsl.java.controller.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.CategoryDao;
import edu.umsl.java.dao.CategoryDaoImpl;

/**
 * Servlet implementation class DeleteCategoryServlet
 */
@WebServlet("/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCategoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("categoryList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("id");

			CategoryDao categoryDao = new CategoryDaoImpl();

			int id = 0;

			if (categoryId != null) {
				try {
					id = Integer.parseInt(categoryId);
					if (id > 0) {
						if (categoryDao.getCategoryIdExists(id)) {
							categoryDao.deleteCategoryById(id);
							response.sendRedirect("categoryList");
							return;
						}
					}
				} catch (Exception e) {
				}
			}

			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
