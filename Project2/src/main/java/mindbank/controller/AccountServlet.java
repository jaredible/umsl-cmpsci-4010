package main.java.mindbank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.Category;
import main.java.mindbank.model.User;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.EnumRole;
import main.java.mindbank.util.StringMap;
import main.java.mindbank.util.Util;

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
		String email = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("email")) {
					email = c.getValue();
				}
			}
		}

		if (email == null) {
			response.sendRedirect("login");
			return;
		}

		try {
			if (request.getAttribute("user") == null) {
				UserDAO userDAO = new UserDAOImpl();
				User user = userDAO.getUser(email);
				request.setAttribute("user", user);
			}

			request.getRequestDispatcher("account.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String phoneNumber = request.getParameter("phoneNumber");

		System.out.println("firstName: " + firstName);
		System.out.println("lastName: " + lastName);
		System.out.println("userName: " + userName);
		System.out.println("phoneNumber: " + phoneNumber);

		try {
			UserDAO userDAO = new UserDAOImpl();

			Map<String, String> errors = new StringMap();

			if (!validFirstname(firstName)) {
				errors.put("firstName", "Invalid first name!");
			}
			if (!validLastname(lastName)) {
				errors.put("lastName", "Invalid last name!");
			}
			if (!validUserName(userName)) {
				errors.put("userName", "Invalid username!");
			}
			if (!validPhoneNumber(phoneNumber)) {
				errors.put("phoneNumber", "Invalid phone number!");
			}

			System.out.println(errors.toString());

			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserName(userName);
			user.setPhoneNumber(phoneNumber);

			if (errors.isEmpty()) {

			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("user", user);
			}
			
			System.out.println("HERE");

			request.getRequestDispatcher("account.jsp").forward(request, response);
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

	private boolean validUserName(String userName) {
		return true;
	}

	private boolean validPhoneNumber(String phoneNumber) {
		return true;
	}

}
