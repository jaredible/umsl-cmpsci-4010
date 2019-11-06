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
			System.out.println("[GET] - id: " + problemId + ", edit: " + edit);

			if (problemId == null) {
				// new problem
			} else if (edit != null && edit.equals("true")) {
				// edit problem
			} else if (problemId != null) {
				// display problem
			}

			Connection conn = DbConn.openConn();
			UserDAO userDAO = new UserDAOImpl(conn);
			CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
			ProblemDAO problemDAO = new ProblemDAOImpl(conn);
			List<Category> categories = categoryDAO.getCategories();
			Problem problem = problemDAO.getProblem(Integer.parseInt(problemId));

			String title = problem.getTitle();
			String time = new SimpleDateFormat("MMMM d, yyyy h:mm a").format(problem.getCreatedTimestamp());
			String author = userDAO.getUserById(problem.getCreatedByUserId()).getUserName();
			String category = categoryDAO.getCategory(problem.getCategoryId()).getName();
			String content = problem.getContent();

			request.setAttribute("categories", categories);
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
			HttpSession session = request.getSession(false);

			Connection conn = DbConn.openConn();
			CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
			ProblemDAO problemDAO = new ProblemDAOImpl(conn);

			User user = (User) session.getAttribute("user");
			boolean canEdit = session != null && user != null && problemDAO.getProblem(Integer.parseInt(problemId)).getCreatedByUserId() == user.getId();

			if (canEdit) {
				String edit = request.getParameter("edit");
				String title = request.getParameter("title");
				String categoryId = request.getParameter("categoryId");
				String content = request.getParameter("content");
				System.out.println("[POST] - id: " + problemId + ", edit: " + edit + ", title: " + title + ", categoryId: " + categoryId + ", content: " + content);

				Map<String, String> errors = new StringMap();
				errors.put("problemId", Problem.validateProblemId(problemDAO));
				errors.put("categoryId", Problem.validateCategoryId(categoryDAO, Integer.parseInt(categoryId)));
				errors.put("title", Problem.validateTitle(problemDAO));
				errors.put("content", Problem.validateContent(problemDAO));

				if (problemId == null) {
					// new problem
				} else if (edit != null && edit.equals("true")) {
					// edit problem
				} else if (problemId != null) { // problemId != null && edit == null
					// display problem
				}

				boolean error = false;
				error |= !errors.get("problemId").isEmpty();
				error |= !errors.get("categoryId").isEmpty();
				error |= !errors.get("title").isEmpty();
				error |= !errors.get("content").isEmpty();

				if (error) {
					request.setAttribute("errors", errors);
					doGet(request, response);
				} else {
					response.sendRedirect("problem?id=" + problemId);
				}
			} else {
				response.sendRedirect("problem?id=" + problemId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
