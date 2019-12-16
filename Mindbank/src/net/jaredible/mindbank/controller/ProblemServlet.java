package net.jaredible.mindbank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.model.ProblemDetail;
import net.jaredible.mindbank.service.CategoryService;
import net.jaredible.mindbank.service.ProblemService;
import net.jaredible.mindbank.service.TagService;
import net.jaredible.mindbank.service.UserService;

@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();
	private ProblemService problemService = ProblemService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();
	private TagService tagService = TagService.getInstance();

	public ProblemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id != null && NumberUtils.isDigits(id)) {
			ProblemDetail problemDetail = new ProblemDetail();
			Problem problem = problemService.getProblemById(Long.valueOf(id));
			problemDetail.setProblem(problem);
			problemDetail.setCreatedByUser(userService.getUserById(problem.getCreatedByUserId()));
			problemDetail.setCategories(categoryService.listCategoriesByProblemId(problem.getId()));
			problemDetail.setTags(tagService.listCategoriesByProblemId(problem.getId()));
			request.setAttribute("problemDetail", problemDetail);
		}

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
