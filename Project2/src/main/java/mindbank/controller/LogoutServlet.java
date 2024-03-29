package main.java.mindbank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.mindbank.dao.AuthDAO;
import main.java.mindbank.dao.AuthDAOImpl;
import main.java.mindbank.model.Auth;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getSession().removeAttribute("userId");
			request.getSession().removeAttribute("isAdmin");

			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				String selector = null;

				for (Cookie c : cookies) {
					if (c.getName().equals("selector")) {
						selector = c.getValue();
					}
				}

				if (selector != null) {
					AuthDAO authDAO = new AuthDAOImpl();
					Auth auth = authDAO.getBySelector(selector);

					if (auth != null) {
						authDAO.deleteById(auth.getId());

						int cookieMaxAge = 0;
						Cookie cookieSelector = new Cookie("selector", null);
						cookieSelector.setMaxAge(cookieMaxAge);
						Cookie cookieValidator = new Cookie("validator", null);
						cookieValidator.setMaxAge(cookieMaxAge);

						response.addCookie(cookieSelector);
						response.addCookie(cookieValidator);
					}

					authDAO.closeConnections();
				}
			}

			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
