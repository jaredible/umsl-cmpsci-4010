package main.java.mindbank.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.mindbank.dao.CategoryDAO;
import main.java.mindbank.dao.CategoryDAOImpl;
import main.java.mindbank.model.Category;
import main.java.mindbank.util.StringMap;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/category.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");

			CategoryDAO categoryDAO = new CategoryDAOImpl();

			Map<String, String> errors = new StringMap();

			if (categoryDAO.getCategoryExistsByName(name)) {
				errors.put("name", "Category already exists!");
			} else if (name.isEmpty()) {
				errors.put("name", "Name cannot be empty!");
			} else if (name.length() > 30) {
				errors.put("name", "Max length is 30!");
			}
			if (description.isEmpty()) {
				errors.put("description", "Description cannot be empty!");
			} else if (description.length() > 100) {
				errors.put("description", "Max length is 100!");
			}

			if (errors.isEmpty()) {
				Category category = new Category();
				category.setName(name);
				category.setDescription(description);

				categoryDAO.addCategory(category);

				response.sendRedirect("category");
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("name", name);
				request.setAttribute("description", description);
				getServletContext().getRequestDispatcher("/category.jsp").forward(request, response);
			}

			categoryDAO.closeConnections();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
