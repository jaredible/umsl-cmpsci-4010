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
import main.java.mindbank.util.StringMap;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
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

			String email = user.getEmail();
			String userName = user.getUserName();
			String phoneNumber = user.getPhoneNumber();

			request.setAttribute("email", email);
			request.setAttribute("userName", userName);
			request.setAttribute("phoneNumber", phoneNumber);
			getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delete = request.getParameter("delete");

		if (delete != null) {
			try {
				if (Boolean.parseBoolean(delete)) {
					int userId = (int) request.getSession(false).getAttribute("userId");
					UserDAO userDAO = new UserDAOImpl();
					userDAO.deleteUser(userId);
					userDAO.closeConnections();
					response.sendRedirect("logout");
					return;
				}
			} catch (Exception e) {
			}
		}

		try {
			String userName = request.getParameter("userName");
			String phoneNumber = request.getParameter("phoneNumber");

			int userId = (int) request.getSession(false).getAttribute("userId");
			UserDAO userDAO = new UserDAOImpl();
			User user = userDAO.getUserById(userId);

			Map<String, String> errors = new StringMap();

			if (!validUserName(userName)) {
				errors.put("userName", "Invalid username!");
			} else if (!userName.equals(user.getUserName()) && userDAO.getUserNameExists(userName)) {
				errors.put("userName", "Username already exists!");
			}
			if (!validPhoneNumber(phoneNumber)) {
				errors.put("phoneNumber", "Invalid phone number!");
			}

			if (errors.isEmpty()) {
				userDAO.updateUserNameById(userId, userName);
				userDAO.updatePhoneNumberById(userId, phoneNumber);

				response.sendRedirect("account");
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("userName", userName);
				request.setAttribute("phoneNumber", phoneNumber);
				getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
			}

			userDAO.closeConnections();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validUserName(String userName) {
		return userName.length() <= 12;
	}

	private boolean validPhoneNumber(String phoneNumber) {
		return phoneNumber.isEmpty() || phoneNumber.matches("\\d{10}|(?:\\d{3}-c){2}\\d{4}|\\(\\d{3}\\) \\d{3}-?\\d{4}");
	}

}
