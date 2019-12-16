package net.jaredible.mindbank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.service.UserService;
import net.jaredible.mindbank.util.SecurityUtil;
import net.jaredible.mindbank.util.TimeUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String rememberMe = request.getParameter("rememberMe");

		Map<String, String> errors = getErrors(userName, passWord);
		if (errors.isEmpty()) {
			userService.login(userName);

			if (rememberMe != null && rememberMe.equals("on")) {
				String selector = RandomStringUtils.randomAlphanumeric(12);
				String rawValidator = RandomStringUtils.randomAlphanumeric(64);
				userService.updateCookieInfo(userName, selector, rawValidator);
				Cookie cookieSelector = new Cookie("selector", selector);
				cookieSelector.setPath(request.getContextPath());
				cookieSelector.setMaxAge(TimeUtil.getNumSecondsInDay());
				response.addCookie(cookieSelector);
				Cookie cookieValidator = new Cookie("validator", rawValidator);
				cookieValidator.setPath(request.getContextPath());
				cookieValidator.setMaxAge(TimeUtil.getNumSecondsInDay());
				response.addCookie(cookieValidator);
			}

			request.getSession().setAttribute("userName", userName);

			response.sendRedirect(request.getContextPath());
			return;
		} else {
			request.setAttribute("userName", userName);
			request.setAttribute("passWord", passWord);
			request.setAttribute("errors", errors);
		}

		doGet(request, response);
	}

	private Map<String, String> getErrors(String userName, String passWord) {
		Map<String, String> errors = new HashMap<String, String>();

		if (userName == null || userName.isEmpty()) {
			errors.put("userName", "This field is required.");
		} else if (userName.length() > 20) {
			errors.put("userName", "Max 20 characters.");
		}

		if (passWord == null || passWord.isEmpty()) {
			errors.put("passWord", "This field is required.");
		}

		User user = userService.getUserByUsername(userName);

		if (errors.isEmpty() && (user == null || !user.getPassWordHash().equals(SecurityUtil.generateSHA512Hash(passWord, user.getPassWordSalt())))) {
			errors.put("credentials", "Incorrect username or password.");
		}

		return errors;
	}

}
