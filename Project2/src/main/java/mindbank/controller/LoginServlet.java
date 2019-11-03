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

import org.apache.commons.lang3.RandomStringUtils;

import main.java.mindbank.dao.AuthDAO;
import main.java.mindbank.dao.AuthDAOImpl;
import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.AuthToken;
import main.java.mindbank.model.User;
import main.java.mindbank.util.HashGenerationException;
import main.java.mindbank.util.HashGeneratorUtil;
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
				user = userDAO.getUser(email);
				userDAO.setLogin(user);

				request.getSession().setAttribute("user", user);

				if (remember != null && remember.equals("on")) {
					AuthToken newToken = new AuthToken();

					String selector = RandomStringUtils.randomAlphanumeric(12);
					String rawValidator = RandomStringUtils.randomAlphanumeric(64);

					String hashedValidator = HashGeneratorUtil.generateSHA256(rawValidator);

					newToken.setSelector(selector);
					newToken.setValidator(hashedValidator);

					newToken.setUserId(user.getId());

					AuthDAO authDao = new AuthDAOImpl();
					authDao.createWithToken(newToken);

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
				request.setAttribute("errors", errors);
				request.setAttribute("user", user);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (HashGenerationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
