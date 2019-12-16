package net.jaredible.mindbank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.jaredible.mindbank.service.UserService;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String sessionUserName = (String) session.getAttribute("userName");

		if (sessionUserName != null && userService.userNameExists(sessionUserName)) {
			userService.updateCookieInfo(sessionUserName, "", "");

			Cookie cookieSelector = new Cookie("selector", null);
			cookieSelector.setPath(getServletContext().getContextPath());
			cookieSelector.setMaxAge(0);
			response.addCookie(cookieSelector);

			Cookie cookieValidator = new Cookie("validator", null);
			cookieValidator.setPath(getServletContext().getContextPath());
			cookieValidator.setMaxAge(0);
			response.addCookie(cookieValidator);
		}

		session.removeAttribute("userName");

		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
