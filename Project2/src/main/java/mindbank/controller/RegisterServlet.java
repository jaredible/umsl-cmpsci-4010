package main.java.mindbank.controller;

import java.io.IOException;
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
import main.java.mindbank.util.HashGenerator;
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
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirm = request.getParameter("confirm");

			UserDAO userDAO = new UserDAOImpl();

			Map<String, String> errors = new StringMap();

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

			if (errors.isEmpty()) {
				User user = new User();

				user.setRoleId(EnumRole.DEFAULT.getId());
				user.setEmail(email);
				user.setPasswordHash(HashGenerator.generateSHA256(password));
				userDAO.addUser(user);
				user = userDAO.getUserByEmail(email);
				user.setUserName("User" + user.getId());
				userDAO.updateUserNameById(user.getId(), user.getUserName());

				response.sendRedirect("login");
			} else {
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("confirm", confirm);
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validEmail(String email) {
		return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") && email.length() <= 50;
	}

	private boolean validPassword(String password) {
		return password.matches("(?=.*[0-9])(?=\\S+$).{8,}");
	}

	private boolean passwordsMatch(String password, String confirm) {
		return confirm.equals(password);
	}

}
