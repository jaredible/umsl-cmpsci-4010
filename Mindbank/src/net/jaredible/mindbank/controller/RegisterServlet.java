package net.jaredible.mindbank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.service.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String passWordConfirm = request.getParameter("passWordConfirm");

		Map<String, String> errors = getErrors(email, userName, passWord, passWordConfirm);
		if (errors.isEmpty()) {
			userService.register(email, userName, passWord);
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			request.setAttribute("email", email);
			request.setAttribute("userName", userName);
			request.setAttribute("passWord", passWord);
			request.setAttribute("passWordConfirm", passWordConfirm);
			request.setAttribute("errors", errors);
		}

		doGet(request, response);
	}

	private Map<String, String> getErrors(String email, String userName, String passWord, String passWordConfirm) {
		Map<String, String> errors = new HashMap<String, String>();

		if (email == null || email.isEmpty()) {
			errors.put("email", "This field is required.");
		} else if (email.length() > 50) {
			errors.put("email", "Max 50 characters.");
		} else if (userService.emailExists(email)) {
			errors.put("email", "Already in use.");
		}

		if (userName == null || userName.isEmpty()) {
			errors.put("userName", "This field is required.");
		} else if (userName.length() > 20) {
			errors.put("userName", "Max 20 characters.");
		} else if (userService.userNameExists(userName)) {
			errors.put("userName", "Already in use.");
		}

		if (passWord == null || passWord.isEmpty()) {
			errors.put("passWord", "This field is required.");
		}

		if (passWordConfirm != null && !passWordConfirm.equals(passWord)) {
			errors.put("passWordConfirm", "Does not match.");
		}

		return errors;
	}

}
