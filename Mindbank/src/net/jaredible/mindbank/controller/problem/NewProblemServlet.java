package net.jaredible.mindbank.controller.problem;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.model.problem.NewProblemModel;
import net.jaredible.mindbank.model.user.User;
import net.jaredible.mindbank.service.CategoryService;
import net.jaredible.mindbank.service.ProblemService;
import net.jaredible.mindbank.service.TagService;
import net.jaredible.mindbank.util.StringUtil;

@WebServlet("/problem/new")
public class NewProblemServlet extends HttpServlet {

	private static final long serialVersionUID = 9071369870989511847L;

	private ProblemService problemService;
	private CategoryService categoryService;
	private TagService tagService;

	public NewProblemServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		problemService = new ProblemService();
		categoryService = new CategoryService();
		tagService = new TagService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categories", categoryService.getAllCategories());
		request.setAttribute("tags", tagService.getAllTags());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/newProblem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String categoryIds = request.getParameter("categoryIds");
		String tagIds = request.getParameter("tagIds");
		String content = request.getParameter("content");

		NewProblemModel newProblem = new NewProblemModel();
		newProblem.setTitle(title);
		newProblem.setContent(content);
		newProblem.setCreatedTime(new Timestamp(new Date().getTime()));
		newProblem.setCreatedByUserId(((User) request.getSession(false).getAttribute("user")).getId());
		newProblem.setCategoryIds(StringUtil.convertStringToIntArray(categoryIds));
		newProblem.setTagIds(StringUtil.convertStringToIntArray(tagIds));

		newProblem.validate(problemService, categoryService, tagService);

		if (newProblem.isValid()) {
			problemService.addProblem(newProblem);

			response.sendRedirect(getServletContext().getContextPath() + "/problem/" + newProblem.getTitle());
		} else {
			request.setAttribute("newProblem", newProblem);
			doGet(request, response);
		}
	}

	public void destroy() {
		problemService = null;
		categoryService = null;
		tagService = null;
	}

}
