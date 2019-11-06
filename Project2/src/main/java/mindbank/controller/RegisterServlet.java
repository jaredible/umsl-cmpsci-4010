package main.java.mindbank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.User;
import main.java.mindbank.util.EnumRole;
import main.java.mindbank.util.StringMap;

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
		getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirm = request.getParameter("confirm");

			UserDAO userDAO = new UserDAOImpl();

			Map<String, String> errors = new StringMap();

			if (!validFirstname(firstName)) {
				errors.put("firstName", "Invalid first name!");
			}
			if (!validLastname(lastName)) {
				errors.put("lastName", "Invalid last name!");
			}
			if (!validEmail(email)) {
				errors.put("email", "Invalid e-mail!");
			} else if (userDAO.getEmailExists(email)) {
				errors.put("email", "E-mail already exists!");
			}
			if (!validPassword(password)) {
				errors.put("password", "Invalid password!");
			}
			if (!passwordsMatch(password, confirm)) {
				errors.put("confirm", "Passwords don't match!");
			}

			User user = new User();
			user.setRoleId(EnumRole.USER.getId());
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPasswordHash(password);

			if (errors.isEmpty()) {
				userDAO.addUser(user);
				user = userDAO.getUserByEmail(email);
				user.setUserName("User" + user.getId());
				request.setAttribute("user", user);
				response.sendRedirect("login");
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("user", user);
				doGet(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean validFirstname(String firstName) {
		return firstName.matches("[a-zA-Z]+");
	}

	private boolean validLastname(String lastName) {
		return lastName.matches("[a-zA-Z]+");
	}

	private boolean validEmail(String email) {
		return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
	}

	private boolean validPassword(String password) {
		return !password.isEmpty(); // password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
	}

	private boolean passwordsMatch(String password, String confirm) {
		return confirm.equals(password);
	}

}
