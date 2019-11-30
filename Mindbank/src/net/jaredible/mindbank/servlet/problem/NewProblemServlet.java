package net.jaredible.mindbank.servlet.problem;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringUtils;

import net.jaredible.mindbank.dao.category.CategoryDao;
import net.jaredible.mindbank.dao.category.CategoryDaoImpl;
import net.jaredible.mindbank.dao.problem.ProblemDao;
import net.jaredible.mindbank.dao.problem.ProblemDaoImpl;
import net.jaredible.mindbank.dao.tag.TagDao;
import net.jaredible.mindbank.dao.tag.TagDaoImpl;
import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.model.User;

@WebServlet("/problem/new")
public class NewProblemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public NewProblemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDao categoryDao = new CategoryDaoImpl();
		TagDao tagDao = new TagDaoImpl();

		request.setAttribute("categories", categoryDao.getAllCategories());
		request.setAttribute("tags", tagDao.getAllTags());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/newProblem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String[] categoryIds = request.getParameterValues("categoryIds");
		String[] tagIds = request.getParameterValues("tagIds");
		String content = request.getParameter("content");

		ProblemDao problemDao = new ProblemDaoImpl();
		CategoryDao categoryDao = new CategoryDaoImpl();
		TagDao tagDao = new TagDaoImpl();

		Map<String, String> errors = new HashMap<String, String>();

		if (title == null || title.isEmpty()) {
			errors.put("title", "This field is required");
		} else if (title.length() > 50) {
			errors.put("title", "Max 50 characters");
		} else if (problemDao.getProblemByTitle(title) != null) {
			errors.put("title", "Already exists");
		}

		if (categoryIds == null || (categoryIds.length == 1 && categoryIds[0].isEmpty())) {
			errors.put("categoryIds", "This field is required");
		}

		if (tagIds == null || (tagIds.length == 1 && tagIds[0].isEmpty())) {
			errors.put("tagIds", "This field is required");
		}

		if (content == null || content.isEmpty()) {
			errors.put("content", "This field is required");
		}

		if (errors.isEmpty()) {
			Problem problem = new Problem();

			problem.setTitle(title);
			problem.setContent(content);
			problem.setCreatedTime(new Timestamp(new Date().getTime()));
			problem.setCreatedByUserId(((User) request.getSession(false).getAttribute("user")).getId());
			
			problemDao.addProblem(problem);

			response.sendRedirect(getServletContext().getContextPath() + "/problem/" + title);
		} else {
			request.setAttribute("title", title);
			request.setAttribute("categoryIds", StringUtils.join(categoryIds));
			request.setAttribute("tagIds", StringUtils.join(tagIds));
			request.setAttribute("content", content);
			request.setAttribute("categories", categoryDao.getAllCategories());
			request.setAttribute("tags", tagDao.getAllTags());
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/newProblem.jsp").forward(request, response);
		}
	}

}
