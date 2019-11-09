package main.java.mindbank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		try {
			String userName = request.getParameter("userName");
			String phoneNumber = request.getParameter("phoneNumber");
			
			Map<String, String> errors = new StringMap();

			if (!validUserName(userName)) {
				errors.put("name", "Invalid username!");
			}
			if (!validPhoneNumber(phoneNumber)) {
				errors.put("bio", "Invalid phone number!");
			}

			if (errors.isEmpty()) {
				HttpSession session = request.getSession(false);
				User user = (User) session.getAttribute("user");
				UserDAO userDAO = new UserDAOImpl();

				//userDAO.updateNameById(user.getId(), name);
				//userDAO.updateBioById(user.getId(), bio);
				//user.setName(name);
				//user.setBio(bio);

				response.sendRedirect("account");
			} else {
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
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
