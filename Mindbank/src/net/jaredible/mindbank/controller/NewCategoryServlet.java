package net.jaredible.mindbank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.service.CategoryService;
import net.jaredible.mindbank.service.UserService;

@WebServlet("/category/new")
public class NewCategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();

	public NewCategoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/newCategory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");

		Map<String, String> errors = getErrors(name);
		if (errors.isEmpty()) {
			HttpSession session = request.getSession(false);
			String sessionUserName = (String) session.getAttribute("userName");
			if (sessionUserName != null) {
				User user = userService.getUserByUsername(sessionUserName);
				if (user != null) {
					categoryService.addNewCategory(name, user.getId());
				}
			}
			response.sendRedirect(request.getContextPath());
			return;
		} else {
			request.setAttribute("name", name);
			request.setAttribute("errors", errors);
		}

		doGet(request, response);
	}

	private Map<String, String> getErrors(String name) {
		Map<String, String> errors = new HashMap<String, String>();

		if (name == null || name.isEmpty()) {
			errors.put("name", "This field is required.");
		} else if (name.length() > 20) {
			errors.put("name", "Max 20 characters.");
		} else if (categoryService.nameExists(name)) {
			errors.put("name", "Already in use.");
		}

		return errors;
	}

}
