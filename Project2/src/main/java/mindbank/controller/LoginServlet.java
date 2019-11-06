package main.java.mindbank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

import main.java.mindbank.dao.AuthDAO;
import main.java.mindbank.dao.AuthDAOImpl;
import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.Auth;
import main.java.mindbank.model.User;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.HashGenerator;
import main.java.mindbank.util.StringMap;

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
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String remember = request.getParameter("remember");

			Connection conn = DbConn.openConn();
			UserDAO userDAO = new UserDAOImpl(conn);

			Map<String, String> errors = new StringMap();

			if (!userDAO.isValidCredentials(email, HashGenerator.generateSHA256(password))) {
				errors.put("error", "Incorrect e-mail or password!");
			}

			if (errors.isEmpty()) {
				User user = userDAO.getUserByEmail(email);
				userDAO.updateLoginTimestampById(user.getId());

				request.getSession().setAttribute("user", user);

				if (remember != null && remember.equals("on")) {
					Auth auth = new Auth();

					String selector = RandomStringUtils.randomAlphanumeric(12);
					String rawValidator = RandomStringUtils.randomAlphanumeric(64);
					String hashedValidator = HashGenerator.generateSHA256(rawValidator);

					auth.setUserId(user.getId());
					auth.setSelector(selector);
					auth.setValidator(hashedValidator);

					AuthDAO authDAO = new AuthDAOImpl(conn);
					authDAO.createWithToken(auth); // TODO: if token exists with userId, just update the selector and validator

					int cookieMaxAge = 60 * 60 * 24;
					Cookie cookieSelector = new Cookie("selector", selector);
					cookieSelector.setMaxAge(cookieMaxAge);
					Cookie cookieValidator = new Cookie("validator", rawValidator);
					cookieValidator.setMaxAge(cookieMaxAge);

					response.addCookie(cookieSelector);
					response.addCookie(cookieValidator);
				}

				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("remember", remember);
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
