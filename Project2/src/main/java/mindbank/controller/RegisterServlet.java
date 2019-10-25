package main.java.mindbank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.Role;
import main.java.mindbank.model.User;
import main.java.mindbank.util.Util;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");

		System.out.println("firstname: " + firstname);
		System.out.println("lastname: " + lastname);
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		System.out.println("confirm: " + confirm);

		try {
			UserDAO userDAO = new UserDAOImpl();

			Map<String, String> errors = new HashMap<String, String>();

			if (!validFirstname(firstname)) {
				errors.put("firstname", "Invalid firstname!");
			}
			if (!validLastname(lastname)) {
				errors.put("lastname", "Invalid lastname!");
			}
			if (!validEmail(email)) {
				errors.put("email", "Invalid email!");
			} else if (userDAO.getEmailExists(email)) {
				errors.put("username", "E-mail already exists!");
			}
			if (!validPassword(password)) {
				errors.put("password", "Invalid password!");
			}
			if (!passwordsMatch(password, confirm)) {
				errors.put("confirm", "Passwords don't match!");
			}

			System.out.println(errors.toString());

			Timestamp timestamp = Util.getGMTNowTime();
			User user = new User(-1, firstname, lastname, "", email, "", password, Role.USER.getId(), false, false, timestamp, timestamp);

			if (errors.isEmpty()) {
				userDAO.addUser(user);
				userDAO.setLogin(user);

				request.getSession().setAttribute("email", email);
				Cookie cookie = new Cookie("email", email);
				cookie.setMaxAge(60 * 30); // 30 minutes
				response.addCookie(cookie);

				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("user", user);
				request.getRequestDispatcher("register.jsp").forward(request, response); // TODO: getServletContext()?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean validFirstname(String firstname) {
		return !firstname.isEmpty();
	}

	private boolean validLastname(String lastname) {
		return !lastname.isEmpty();
	}

	private boolean validEmail(String email) {
		return email.length() > 10;
	}

	private boolean validPassword(String password) {
		return password.length() > 5;
	}

	private boolean passwordsMatch(String password, String confirm) {
		return confirm.equals(password);
	}

}
