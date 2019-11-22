package net.jaredible.mindbank.controller;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.jaredible.mindbank.dao.CategoryDAO;
import net.jaredible.mindbank.dao.CategoryDAOImpl;
import net.jaredible.mindbank.dao.ProblemDAO;
import net.jaredible.mindbank.dao.ProblemDAOImpl;
import net.jaredible.mindbank.dao.UserDAO;
import net.jaredible.mindbank.dao.UserDAOImpl;
import net.jaredible.mindbank.model.Category;
import net.jaredible.mindbank.model.Problem;
import net.jaredible.mindbank.model.ProblemInfo;
import net.jaredible.mindbank.util.DbUtil;
import net.jaredible.mindbank.util.StringMap;

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
			String delete = request.getParameter("delete");

			Connection conn = DbUtil.openConn();

			String title = "";
			String time = "";
			String author = "";
			String category = "";
			String content = "";

			if (problemId == null) {
				// new problem
				CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
				List<Category> categories = categoryDAO.getCategories();
				categoryDAO.closeConnections();
				request.setAttribute("categories", categories);
			} else if (edit != null && edit.equals("true")) {
				// edit problem
				UserDAO userDAO = new UserDAOImpl(conn);
				ProblemDAO problemDAO = new ProblemDAOImpl(conn);
				ProblemInfo problemInfo = problemDAO.getProblemById(Integer.parseInt(problemId));
				Problem problem = problemInfo.getProblem();
				CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
				List<Category> categories = categoryDAO.getCategories();

				title = problem.getTitle();
				time = new SimpleDateFormat("MMMM d, yyyy h:mm a").format(problem.getCreatedTimestamp());
				author = userDAO.getUserById(problem.getCreatedByUserId()).getUserName();
				category = categoryDAO.getCategory(problem.getCategoryId()).getName();
				content = problem.getContent();

				request.setAttribute("problem", problem);
				request.setAttribute("categories", categories);
				request.setAttribute("categoryId", problem.getCategoryId());

				userDAO.closeConnections();
				problemDAO.closeConnections();
				categoryDAO.closeConnections();
			} else if (delete != null && delete.equals("true")) {
				// delete problem
				ProblemDAO problemDAO = new ProblemDAOImpl(conn);
				problemDAO.deleteProblemById(Integer.parseInt(problemId));
				problemDAO.closeConnections();
				response.sendRedirect(request.getContextPath());
				return;
			} else if (problemId != null) {
				// display problem
				UserDAO userDAO = new UserDAOImpl(conn);
				ProblemDAO problemDAO = new ProblemDAOImpl(conn);
				ProblemInfo problemInfo = problemDAO.getProblemById(Integer.parseInt(problemId));
				Problem problem = problemInfo.getProblem();
				CategoryDAO categoryDAO = new CategoryDAOImpl(conn);

				title = problem.getTitle();
				time = new SimpleDateFormat("MMMM d, yyyy h:mm a").format(problem.getCreatedTimestamp());
				author = userDAO.getUserById(problem.getCreatedByUserId()).getUserName();
				category = categoryDAO.getCategory(problem.getCategoryId()).getName();
				content = problem.getContent();

				request.setAttribute("problem", problem);

				userDAO.closeConnections();
				problemDAO.closeConnections();
				categoryDAO.closeConnections();
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

			Connection conn = DbUtil.openConn();

			HttpSession session = request.getSession(false);
			int userId = (int) session.getAttribute("userId");

			if (problemId == null) {
				// new problem
				String title = request.getParameter("title");
				String categoryId = request.getParameter("categoryId");
				String content = request.getParameter("content");

				ProblemDAO problemDAO = new ProblemDAOImpl(conn);

				Map<String, String> errors = new StringMap();

				if (!validTitle(title)) {
					errors.put("title", "Invalid title!");
				}
				if (!validCategoryId(categoryId)) {
					errors.put("categoryId", "Invalid category!");
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
					newProblem.setCreatedTimestamp(new Timestamp(new Date().getTime()));

					problemDAO.addProblem(newProblem);

					response.sendRedirect(request.getContextPath() + "?category=ai");
				} else {
					CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
					List<Category> categories = categoryDAO.getCategories();
					categoryDAO.closeConnections();

					request.setAttribute("errors", errors);
					request.setAttribute("title", title);
					request.setAttribute("categoryId", categoryId);
					request.setAttribute("content", content);
					request.setAttribute("categories", categories);
					getServletContext().getRequestDispatcher("/problem.jsp").forward(request, response);
				}

				problemDAO.closeConnections();
			} else if (edit != null && edit.equals("true")) {
				// edit problem
				String title = request.getParameter("title");
				String categoryId = request.getParameter("categoryId");
				String content = request.getParameter("content");

				ProblemDAO problemDAO = new ProblemDAOImpl(conn);

				Map<String, String> errors = new StringMap();

				if (!validTitle(title)) {
					errors.put("title", "Invalid title!");
				}
				if (!validCategoryId(categoryId)) {
					errors.put("categoryId", "Invalid category!");
				}
				if (!validContent(content)) {
					errors.put("content", "Invalid content!");
				}

				if (errors.isEmpty()) {
					// TODO: update problem
					problemDAO.updateCategoryIdById(Integer.parseInt(problemId), Integer.parseInt(categoryId));
					problemDAO.updateTitleById(Integer.parseInt(problemId), title);
					problemDAO.updateContentById(Integer.parseInt(problemId), content);
					problemDAO.updateEditedById(Integer.parseInt(problemId), true);

					response.sendRedirect("problem?id=" + problemId);
				} else {
					CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
					List<Category> categories = categoryDAO.getCategories();
					categoryDAO.closeConnections();

					request.setAttribute("errors", errors);
					request.setAttribute("title", title);
					request.setAttribute("categoryId", categoryId);
					request.setAttribute("content", content);
					request.setAttribute("categories", categories);
					getServletContext().getRequestDispatcher("/problem.jsp").forward(request, response);
				}

				problemDAO.closeConnections();
			} else if (problemId != null) {
				// display problem
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validTitle(String title) {
		return !title.isEmpty() && title.length() <= 100;
	}

	private boolean validCategoryId(String categoryId) {
		return !categoryId.equals("0");
	}

	private boolean validContent(String content) {
		return !content.isEmpty();
	}

}
