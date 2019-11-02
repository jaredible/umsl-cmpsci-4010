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
import main.java.mindbank.model.Category;
import main.java.mindbank.util.DbConn;

/**
 * Servlet implementation class ProblemServlet
 */
@WebServlet("/problem/*")
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
		String email = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("email")) {
					email = c.getValue();
				}
			}
		}
		
		System.out.println(request.getParameter("edit"));
		System.out.println(request.getParameter("id"));

		if (email == null) {
			//response.sendRedirect("login");
			//return;
		}

		try {
			Connection conn = DbConn.openConn();
			CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
			// TODO: get user?
			
			String test = (String) request.getSession().getAttribute("email");
			System.out.println(test);

			List<Category> categories = categoryDAO.getCategories();

			request.setAttribute("categories", categories);
			getServletContext().getRequestDispatcher("/problem.jsp").forward(request, response);
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
