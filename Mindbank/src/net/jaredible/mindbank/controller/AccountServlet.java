package net.jaredible.mindbank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.service.UserService;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();

	public AccountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionUserName = (String) request.getSession(false).getAttribute("userName");
		if (sessionUserName != null) {
			User user = userService.getUserByUsername(sessionUserName);
			if (user != null) {
				request.setAttribute("email", user.getEmail());
				request.setAttribute("userName", user.getUserName());
				request.setAttribute("lastLoginTime", user.getLastLoginTime());
				request.setAttribute("registeredTime", user.getRegisteredTime());
			}
		}

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
