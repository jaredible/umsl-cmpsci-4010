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
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionEmail = (String) request.getSession().getAttribute("email");
		String cookieEmail = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("email")) {
					cookieEmail = c.getValue();
				}
			}
		}
		
		try {
			Connection conn = DbConn.openConn();
			UserDAO userDAO = new UserDAOImpl(conn);
			CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
			ProblemDAO problemDAO = new ProblemDAOImpl(conn);

			User user = userDAO.getUser(sessionEmail);
			userDAO.getUser(sessionEmail);
			List<Category> categories = categoryDAO.getCategories();
			List<Problem> problems = problemDAO.getProblems();

			request.setAttribute("user", user);
			request.setAttribute("categories", categories);
			request.setAttribute("problems", problems);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
