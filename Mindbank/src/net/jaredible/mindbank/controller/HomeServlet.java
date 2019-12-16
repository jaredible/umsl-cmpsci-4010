package net.jaredible.mindbank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.model.ProblemDetail;
import net.jaredible.mindbank.service.CategoryService;
import net.jaredible.mindbank.service.ProblemService;
import net.jaredible.mindbank.service.TagService;
import net.jaredible.mindbank.service.UserService;

@WebServlet("")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserService.getInstance();
	private ProblemService problemService = ProblemService.getInstance();
	private CategoryService categoryService = CategoryService.getInstance();
	private TagService tagService = TagService.getInstance();

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categories = request.getParameter("cid");
		String tags = request.getParameter("tid");
		String users = request.getParameter("uid");

		if ((categories != null && categories.isEmpty()) && (tags != null && tags.isEmpty()) && (users != null && users.isEmpty())) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		List<ProblemDetail> problemDetails = new ArrayList<ProblemDetail>();
		List<Problem> problems = problemService.listProblemsByCategoriesTagsUsers(categories, tags, users);
		for (Problem problem : problems) {
			ProblemDetail problemDetail = new ProblemDetail();
			problemDetail.setProblem(problem);
			problemDetail.setCreatedByUser(userService.getUserById(problem.getCreatedByUserId()));
			problemDetail.setCategories(categoryService.listCategoriesByProblemId(problem.getId()));
			problemDetail.setTags(tagService.listCategoriesByProblemId(problem.getId()));
			problemDetails.add(problemDetail);
		}

		request.setAttribute("cid", categories);
		request.setAttribute("tid", tags);
		request.setAttribute("uid", users);
		request.setAttribute("problemDetails", problemDetails);
		request.setAttribute("categories", categoryService.listAllCategories());
		request.setAttribute("tags", tagService.listAllTags());
		request.setAttribute("users", userService.listAllUsers());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
