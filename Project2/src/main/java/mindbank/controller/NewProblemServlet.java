package main.java.mindbank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.mindbank.dao.CategoryDAO;
import main.java.mindbank.dao.CategoryDAOImpl;
import main.java.mindbank.dao.ProblemDAO;
import main.java.mindbank.dao.ProblemDAOImpl;
import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.Category;
import main.java.mindbank.model.Problem;
import main.java.mindbank.model.User;
import main.java.mindbank.util.DbConn;

/**
 * Servlet implementation class NewProblemServlet
 */
@WebServlet("/newProblem")
public class NewProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewProblemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("email")) {
					email = c.getValue();
				}
			}
		}

		if (email == null) {
			response.sendRedirect("login");
			return;
		}

		try {
			Connection conn = DbConn.openConn();
			CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
			// TODO: get user?

			List<Category> categories = categoryDAO.getCategories();

			request.setAttribute("categories", categories);
			request.getRequestDispatcher("newProblem.jsp").forward(request, response);
		} catch (SQLException e) {
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
