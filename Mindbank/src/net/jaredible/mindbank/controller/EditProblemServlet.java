package net.jaredible.mindbank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.service.CategoryService;
import net.jaredible.mindbank.service.ProblemService;
import net.jaredible.mindbank.service.TagService;
import net.jaredible.mindbank.service.UserService;

@WebServlet("/problem/edit")
public class EditProblemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();
	private ProblemService problemService = ProblemService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();
	private TagService tagService = TagService.getInstance();

	public EditProblemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id != null && NumberUtils.isDigits(id)) {
			String sessionUserName = (String) request.getSession(false).getAttribute("userName");
			String problemUserName = userService.getUserById(problemService.getProblemById(Long.valueOf(id)).getCreatedByUserId()).getUserName();
			if (!sessionUserName.equals(problemUserName)) {
				response.sendRedirect(request.getContextPath() + "/problem?id=" + id);
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath());
			return;
		}

		Problem problem = problemService.getProblemById(Long.valueOf(id));

		request.setAttribute("title", problem.getTitle());
		request.setAttribute("createdByUserName", userService.getUserById(problemService.getProblemById(Long.valueOf(id)).getCreatedByUserId()).getUserName());
		request.setAttribute("createdTime", problem.getCreatedTime());
		request.setAttribute("cid", categoryService.listCategoriesByProblemId(problem.getId()).stream().map(cid -> String.valueOf(cid.getId())).collect(Collectors.joining(",")));
		request.setAttribute("tid", tagService.listCategoriesByProblemId(problem.getId()).stream().map(tid -> String.valueOf(tid.getId())).collect(Collectors.joining(",")));
		request.setAttribute("content", problem.getContent());
		request.setAttribute("categories", categoryService.listAllCategories());
		request.setAttribute("tags", tagService.listAllTags());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProblem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String categories = request.getParameter("cid");
		String tags = request.getParameter("tid");
		String content = request.getParameter("content");

		if (id == null || !NumberUtils.isDigits(id)) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		String sessionUserName = (String) request.getSession(false).getAttribute("userName");
		String problemUserName = userService.getUserById(problemService.getProblemById(Long.valueOf(id)).getCreatedByUserId()).getUserName();
		if (!sessionUserName.equals(problemUserName)) {
			response.sendRedirect(request.getContextPath() + "/problem?id=" + id);
			return;
		}

		Map<String, String> errors = getErrors(content);
		if (errors.isEmpty()) {
			problemService.updateProblem(Long.valueOf(id), content, categories, tags);
			response.sendRedirect(request.getContextPath() + "/problem?id=" + id);
			return;
		} else {
			request.setAttribute("cid", categories);
			request.setAttribute("tid", tags);
			request.setAttribute("content", content);
			request.setAttribute("errors", errors);
		}

		Problem problem = problemService.getProblemById(Long.valueOf(id));

		request.setAttribute("title", problem.getTitle());
		request.setAttribute("createdByUserName", userService.getUserById(problemService.getProblemById(Long.valueOf(id)).getCreatedByUserId()).getUserName());
		request.setAttribute("createdTime", problem.getCreatedTime());
		request.setAttribute("cid", categories);
		request.setAttribute("tid", tags);
		request.setAttribute("content", content);
		request.setAttribute("categories", categoryService.listAllCategories());
		request.setAttribute("tags", tagService.listAllTags());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProblem.jsp").forward(request, response);
	}

	private Map<String, String> getErrors(String content) {
		Map<String, String> errors = new HashMap<String, String>();

		if (content == null || content.isEmpty()) {
			errors.put("content", "This field is required.");
		} else if (content.length() > 10000) {
			errors.put("content", "Max 10000 characters.");
		}

		return errors;
	}

}
