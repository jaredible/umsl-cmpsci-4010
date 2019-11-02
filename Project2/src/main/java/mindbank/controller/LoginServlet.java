package main.java.mindbank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.User;
import main.java.mindbank.util.StringMap;
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
		String email = (String) request.getSession().getAttribute("email");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("email")) {
					email = c.getValue();
				}
			}
		}
		
		if (email != null) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");

		System.out.println("email: " + email);
		System.out.println("password: " + password);
		System.out.println("remember: " + remember);

		try {
			UserDAO userDAO = new UserDAOImpl();

			Map<String, String> errors = new StringMap();

			if (!userDAO.isValidCredentials(email, password)) {
				errors.put("error", "Incorrect e-mail or password!");
			}

			System.out.println(errors.toString());

			User user = new User();
			user.setEmail(email);
			user.setPasswordHash(password);
			user.setLoginTimestamp(Util.getGMTNowTime());

			if (errors.isEmpty()) {
				userDAO.setLogin(user);

				request.getSession().setAttribute("email", email);

				if (remember != null && remember == "on") {
					Cookie cookie = new Cookie("email", email);
					cookie.setMaxAge(60 * 30); // 30 minutes
					response.addCookie(cookie);
				}

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
