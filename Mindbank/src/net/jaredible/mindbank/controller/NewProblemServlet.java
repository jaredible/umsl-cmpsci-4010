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

import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.service.CategoryService;
import net.jaredible.mindbank.service.ProblemService;
import net.jaredible.mindbank.service.TagService;
import net.jaredible.mindbank.service.UserService;

@WebServlet("/problem/new")
public class NewProblemServlet extends HttpServlet {

	private static final long serialVersionUID = 9071369870989511847L;

	private UserService userService = UserService.getInstance();
	private ProblemService problemService = ProblemService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();
	private TagService tagService = TagService.getInstance();

	public NewProblemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categories", categoryService.listAllCategories());
		request.setAttribute("tags", tagService.listAllTags());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/newProblem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String categories = request.getParameter("cid");
		String tags = request.getParameter("tid");
		String content = request.getParameter("content");

		Map<String, String> errors = getErrors(title, content);
		if (errors.isEmpty()) {
			HttpSession session = request.getSession(false);
			String sessionUserName = (String) session.getAttribute("userName");
			if (sessionUserName != null) {
				User user = userService.getUserByUsername(sessionUserName);
				if (user != null) {
					Problem problem = problemService.addNewProblem(title, content, categories, tags, user.getId());
					response.sendRedirect(request.getContextPath() + "/problem?id=" + problem.getId());
					return;
				}
			}
		} else {
			request.setAttribute("title", title);
			request.setAttribute("cid", categories);
			request.setAttribute("tid", tags);
			request.setAttribute("content", content);
			request.setAttribute("errors", errors);
		}

		doGet(request, response);
	}

	private Map<String, String> getErrors(String title, String content) {
		Map<String, String> errors = new HashMap<String, String>();

		if (title == null || title.isEmpty()) {
			errors.put("title", "This field is required.");
		} else if (title.length() > 100) {
			errors.put("title", "Max 100 characters.");
		} else if (problemService.titleExists(title)) {
			errors.put("title", "Already in use.");
		}

		if (content == null || content.isEmpty()) {
			errors.put("content", "This field is required.");
		} else if (content.length() > 10000) {
			errors.put("content", "Max 10000 characters.");
		}

		return errors;
	}

}
