package main.java.mathbank.controller;

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

import main.java.mathbank.dao.UserDAO;
import main.java.mathbank.dao.UserDAOImpl;
import main.java.mathbank.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAOImpl();

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
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phone");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");

		System.out.println("firstname: " + firstname);
		System.out.println("lastname: " + lastname);
		System.out.println("username: " + username);
		System.out.println("email: " + email);
		System.out.println("phone: " + phoneNumber);
		System.out.println("password: " + password);
		System.out.println("confirm: " + confirm);

		Map<String, String> errors = new HashMap<String, String>();

		if (!validFirstname(firstname)) {
			errors.put("firstname", "Invalid firstname!");
		}
		if (!validLastname(lastname)) {
			errors.put("lastname", "Invalid lastname!");
		}
		if (!validUsername(username)) {
			errors.put("username", "Invalid username!");
		} else if (userDAO.checkUsername(username)) {
			errors.put("username", "Username already exists!");
		}
		if (!validEmail(email)) {
			errors.put("email", "Invalid email!");
		}
		if (!validPhoneNumber(phoneNumber)) {
			errors.put("phone", "Invalid phone number!");
		}
		if (!validPassword(password)) {
			errors.put("password", "Invalid password!");
		}
		if (!passwordsMatch(password, confirm)) {
			errors.put("confirm", "Passwords don't match!");
		}

		System.out.println(errors.toString());

		if (errors.isEmpty()) {
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			userDAO.addUser(new User(0, firstname, lastname, username, email, phoneNumber, password, "default", false, false, null, null));
			userDAO.login(username, password);
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60 * 30); // 30 minutes
			response.addCookie(cookie);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("register.jsp").forward(request, response); // TODO: getServletContext()?
		}
	}

	private boolean validFirstname(String firstname) {
		return !firstname.isEmpty();
	}

	private boolean validLastname(String lastname) {
		return !lastname.isEmpty();
	}

	private boolean validUsername(String username) {
		return !username.isEmpty();
	}

	private boolean validEmail(String email) {
		return email.length() > 10;
	}

	private boolean validPhoneNumber(String phoneNumber) {
		return phoneNumber.length() > 0;
	}

	private boolean validPassword(String password) {
		return password.length() > 5;
	}

	private boolean passwordsMatch(String password, String confirm) {
		return confirm.equals(password);
	}

}
