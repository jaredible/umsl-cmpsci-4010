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
		getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String userName = request.getParameter("userName");
		String phoneNumber = request.getParameter("phoneNumber");

		System.out.println("name: " + name);
		System.out.println("userName: " + userName);
		System.out.println("phoneNumber: " + phoneNumber);

		try {
			UserDAO userDAO = new UserDAOImpl();

			Map<String, String> errors = new StringMap();

			if (!validUserName(userName)) {
				errors.put("userName", "Invalid username!");
			}
			if (!validPhoneNumber(phoneNumber)) {
				errors.put("phoneNumber", "Invalid phone number!");
			}

			System.out.println(errors.toString());

			User user = new User();
			user.setName(name);
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

	private boolean validUserName(String userName) {
		return true;
	}

	private boolean validPhoneNumber(String phoneNumber) {
		return true;
	}

}
