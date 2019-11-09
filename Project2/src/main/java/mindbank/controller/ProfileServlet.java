package main.java.mindbank.controller;

import java.io.IOException;
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
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");

			String name = user.getName();
			String bio = user.getBio();

			request.setAttribute("name", name);
			request.setAttribute("bio", bio);
			getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String bio = request.getParameter("bio");
			
			Map<String, String> errors = new StringMap();

			if (!validName(name)) {
				errors.put("name", "Invalid name!");
			}
			if (!validBio(bio)) {
				errors.put("bio", "Invalid bio!");
			}

			if (errors.isEmpty()) {
				HttpSession session = request.getSession(false);
				User user = (User) session.getAttribute("user");
				UserDAO userDAO = new UserDAOImpl();

				userDAO.updateNameById(user.getId(), name);
				userDAO.updateBioById(user.getId(), bio);
				user.setName(name);
				user.setBio(bio);

				response.sendRedirect("profile");
			} else {
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validName(String name) {
		return name.length() <= 100;
	}

	private boolean validBio(String bio) {
		return bio.length() <= 420;
	}

}
