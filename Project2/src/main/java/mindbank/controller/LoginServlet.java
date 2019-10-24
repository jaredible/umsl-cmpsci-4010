package main.java.mindbank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO = new UserDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println("email: " + email);
		System.out.println("password: " + password);
		
		Map<String, String> errors = new HashMap<String, String>();
		
		if (!validLoginCredentials(email, password)) {
			errors.put("error", "Incorrect e-mail or password.");
		}
		
		System.out.println(errors.toString());
		
		if (errors.isEmpty()) {
			String username = "Jaredible";
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			userDAO.login(username, password);
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60 * 30); // 30 minutes
			response.addCookie(cookie);
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("login").forward(request, response); // TODO: getServletContext()?
		}
	}
	
	private boolean validLoginCredentials(String email, String password) {
		return true;
	}

}
