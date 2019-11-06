package main.java.mindbank.controller;

import java.io.IOException;
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
import main.java.mindbank.util.HashGeneratorUtil;
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
			Map<String, String> errors = new StringMap();
			UserDAO userDao = new UserDAOImpl();

			if (!userDao.isValidCredentials(email, password)) {
				errors.put("error", "Incorrect e-mail or password!");
			}

			User user = new User();
			user.setEmail(email);
			user.setPasswordHash(password);

			if (errors.isEmpty()) {
				user = userDao.getUserByEmail(email);
				userDao.setLoginById(user.getId());

				request.getSession().setAttribute("user", user);

				if (remember != null && remember.equals("on")) {
					Auth newToken = new Auth();

					String selector = RandomStringUtils.randomAlphanumeric(12);
					String rawValidator = RandomStringUtils.randomAlphanumeric(64);

					String hashedValidator = HashGeneratorUtil.generateSHA256(rawValidator);

					newToken.setUserId(user.getId());
					newToken.setSelector(selector);
					newToken.setValidator(hashedValidator);

					AuthDAO authDao = new AuthDAOImpl();
					authDao.createWithToken(newToken); // TODO

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
				request.setAttribute("user", user);
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
