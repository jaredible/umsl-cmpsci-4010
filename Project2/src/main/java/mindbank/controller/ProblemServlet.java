package main.java.mindbank.controller;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.mindbank.dao.CategoryDAO;
import main.java.mindbank.dao.CategoryDAOImpl;
import main.java.mindbank.dao.ProblemDAO;
import main.java.mindbank.dao.ProblemDAOImpl;
import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.Category;
import main.java.mindbank.model.Problem;
import main.java.mindbank.model.User;
import main.java.mindbank.util.DbConn;
import main.java.mindbank.util.StringMap;

/**
 * Servlet implementation class ProblemServlet
 */
@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProblemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String problemId = request.getParameter("id");
			String edit = request.getParameter("edit");

			Connection conn = DbConn.openConn();

			int userId = (int) request.getSession(false).getAttribute("userId");
			UserDAO userDAO = new UserDAOImpl(conn);
			User user = userDAO.getUserById(userId);

			String title = "";
			String time = "";
			String author = "";
			String category = "";
			String content = "";

			if (problemId == null) {
				// new problem
				CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
				List<Category> categories = categoryDAO.getCategories();

				request.setAttribute("categories", categories);
			} else if (edit != null && edit.equals("true")) {
				// edit problem
				ProblemDAO problemDAO = new ProblemDAOImpl(conn);
				Problem problem = problemDAO.getProblemById(Integer.parseInt(problemId));
				CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
				List<Category> categories = categoryDAO.getCategories();

				title = problem.getTitle();
				time = new SimpleDateFormat("MMMM d, yyyy h:mm a").format(problem.getCreatedTimestamp());
				author = userDAO.getUserById(problem.getCreatedByUserId()).getUserName();
				category = categoryDAO.getCategory(problem.getCategoryId()).getName();
				content = problem.getContent();

				request.setAttribute("categories", categories);
			} else if (problemId != null) {
				// display problem
				ProblemDAO problemDAO = new ProblemDAOImpl(conn);
				Problem problem = problemDAO.getProblemById(Integer.parseInt(problemId));
				CategoryDAO categoryDAO = new CategoryDAOImpl(conn);

				title = problem.getTitle();
				time = new SimpleDateFormat("MMMM d, yyyy h:mm a").format(problem.getCreatedTimestamp());
				author = userDAO.getUserById(problem.getCreatedByUserId()).getUserName();
				category = categoryDAO.getCategory(problem.getCategoryId()).getName();
				content = problem.getContent();
			}

			request.setAttribute("title", title);
			request.setAttribute("time", time);
			request.setAttribute("author", author);
			request.setAttribute("category", category);
			request.setAttribute("content", content);
			getServletContext().getRequestDispatcher("/problem.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String problemId = request.getParameter("id");
			String edit = request.getParameter("edit");

			Connection conn = DbConn.openConn();

			HttpSession session = request.getSession(false);
			int userId = (int) session.getAttribute("userId");
			UserDAO userDAO = new UserDAOImpl(conn);
			User user = userDAO.getUserById(userId);

			ProblemDAO problemDAO = new ProblemDAOImpl(conn);
			// Problem problem = problemDAO.getProblem(Integer.parseInt(problemId));

			if (problemId == null) {
				// new problem
				String title = request.getParameter("title");
				String categoryId = request.getParameter("categoryId");
				String content = request.getParameter("content");

				Map<String, String> errors = new StringMap();

				if (!validTitle(title)) {
					errors.put("title", "Invalid title!");
				}
				if (!validContent(content)) {
					errors.put("content", "Invalid content!");
				}

				if (errors.isEmpty()) {
					Problem newProblem = new Problem();
					newProblem.setCategoryId(Integer.parseInt(categoryId));
					newProblem.setTitle(title);
					newProblem.setContent(content);
					newProblem.setCreatedByUserId(userId);

					problemDAO.addProblem(newProblem);

					response.sendRedirect(request.getContextPath() + "?category=ai");
				} else {
					CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
					List<Category> categories = categoryDAO.getCategories();
					
					request.setAttribute("errors", errors);
					request.setAttribute("title", title);
					request.setAttribute("categoryId", categoryId);
					request.setAttribute("content", content);
					request.setAttribute("categories", categories);
					getServletContext().getRequestDispatcher("/problem.jsp").forward(request, response);
				}
			} else if (edit != null && edit.equals("true")) {
				// edit problem
			} else if (problemId != null) {
				// display problem
			}

			// boolean canEdit = user.getId() == problem.getCreatedByUserId();

			if (false) {
				// String edit = request.getParameter("edit");
				String title = request.getParameter("title");
				String categoryId = request.getParameter("categoryId");
				String content = request.getParameter("content");

				Map<String, String> errors = new StringMap();

				if (problemId == null) {
					// new problem
				} else if (edit != null && edit.equals("true")) {
					// edit problem
				} else if (problemId != null) {
					// display problem
				}

				boolean error = false;

				if (error) {
					request.setAttribute("errors", errors);
					doGet(request, response);
				} else {
					response.sendRedirect("problem?id=" + problemId);
				}
			} else {
				// response.sendRedirect("problem?id=" + problemId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validTitle(String title) {
		return !title.isEmpty() && title.length() <= 100;
	}

	private boolean validContent(String content) {
		return !content.isEmpty();
	}

}
