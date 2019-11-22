package net.jaredible.mindbank.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.dao.UserDAO;
import net.jaredible.mindbank.dao.UserDAOImpl;
import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.util.HashGenerator;
import net.jaredible.mindbank.util.StringMap;

/**
 * Servlet implementation class SecurityServlet
 */
@WebServlet("/security")
public class SecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SecurityServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int userId = (int) request.getSession(false).getAttribute("userId");
			UserDAO userDAO = new UserDAOImpl();
			User user = userDAO.getUserById(userId);
			userDAO.closeConnections();

			String phoneNumber = user.getPhoneNumber();

			request.setAttribute("phoneNumber", phoneNumber);
			getServletContext().getRequestDispatcher("/security.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String newPasswordConfirm = request.getParameter("newPasswordConfirm");

			int userId = (int) request.getSession(false).getAttribute("userId");
			UserDAO userDAO = new UserDAOImpl();
			User user = userDAO.getUserById(userId);

			String phoneNumber = user.getPhoneNumber();

			Map<String, String> errors = new StringMap();

			if (!HashGenerator.generateSHA256(oldPassword).equals(user.getPasswordHash())) {
				errors.put("password", "Incorrect password!");
			}
			if (newPassword.equals(oldPassword)) {
				errors.put("newPassword", "Cannot be the same!");
			}
			if (!validPassword(newPassword)) {
				errors.put("newPassword", "Invalid password!");
			}
			if (!passwordsMatch(newPassword, newPasswordConfirm)) {
				errors.put("newPasswordConfirm", "Passwords don't match!");
			}

			if (errors.isEmpty()) {
				userDAO.updatePasswordHashById(userId, HashGenerator.generateSHA256(newPassword));

				response.sendRedirect("security");
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("password", oldPassword);
				request.setAttribute("newPassword", newPassword);
				request.setAttribute("newPasswordConfirm", newPasswordConfirm);
				request.setAttribute("phoneNumber", phoneNumber);
				getServletContext().getRequestDispatcher("/security.jsp").forward(request, response);
			}

			userDAO.closeConnections();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validPassword(String password) {
		return password.matches("(?=.*[0-9])(?=\\S+$).{8,}");
	}

	private boolean passwordsMatch(String password, String confirm) {
		return confirm.equals(password);
	}

}
