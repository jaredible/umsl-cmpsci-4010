package main.java.mindbank.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import main.java.mindbank.util.Util;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("email")) {
					response.sendRedirect(request.getContextPath());
					return;
				}
			}
		}

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

		try {
			UserDAO userDAO = new UserDAOImpl();

			Map<String, String> errors = new HashMap<String, String>();

			if (!userDAO.isValidCredentials(email, password)) {
				errors.put("error", "Incorrect e-mail or password.");
			}

			System.out.println(errors.toString());

			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setLastLoginTimestamp(Util.getGMTNowTime());

			if (errors.isEmpty()) {
				userDAO.setLogin(user);

				request.getSession().setAttribute("email", email);
				Cookie cookie = new Cookie("email", email);
				cookie.setMaxAge(60 * 30); // 30 minutes
				response.addCookie(cookie);

				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("user", user);
				request.getRequestDispatcher("login.jsp").forward(request, response); // TODO: getServletContext()?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
