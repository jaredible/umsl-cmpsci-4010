package net.jaredible.mindbank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.service.UserService;

@WebServlet("/profile")
@MultipartConfig(maxFileSize = 16777216)
public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();

	public ProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionUserName = (String) request.getSession(false).getAttribute("userName");
		if (sessionUserName != null) {
			User user = userService.getUserByUsername(sessionUserName);
			if (user != null) {
				request.setAttribute("statusEmoji", user.getStatusEmoji());
				request.setAttribute("statusText", user.getStatusText());
				request.setAttribute("name", user.getName());
				request.setAttribute("bio", user.getBio());
			}
		}

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String statusEmoji = request.getParameter("statusEmoji");
		String statusText = request.getParameter("statusText");
		String name = request.getParameter("name");
		String bio = request.getParameter("bio");

		Map<String, String> errors = getErrors(statusText, name, bio);
		if (errors.isEmpty()) {
			HttpSession session = request.getSession(false);
			String sessionUserName = (String) session.getAttribute("userName");
			if (sessionUserName != null) {
				User user = userService.getUserByUsername(sessionUserName);
				if (user != null) {
					userService.updateProfileInfo(user.getUserName(), name, bio, statusEmoji, statusText);
				}
			}
			response.sendRedirect(request.getContextPath() + "/profile");
			return;
		} else {
			request.setAttribute("statusEmoji", statusEmoji);
			request.setAttribute("statusText", statusText);
			request.setAttribute("name", name);
			request.setAttribute("bio", bio);
			request.setAttribute("errors", errors);
		}

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
	}

	private Map<String, String> getErrors(String statusText, String name, String bio) {
		Map<String, String> errors = new HashMap<String, String>();

		if (statusText.length() > 30) {
			errors.put("statusText", "Max 30 characters.");
		}

		if (name.length() > 30) {
			errors.put("name", "Max 30 characters.");
		}

		if (bio.length() > 420) {
			errors.put("bio", "Max 420 characters.");
		}

		return errors;
	}

}
