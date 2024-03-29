package edu.umsl.java.controller.problem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.category.CategoryDao;
import edu.umsl.java.dao.category.CategoryDaoImpl;
import edu.umsl.java.dao.comment.CommentDao;
import edu.umsl.java.dao.comment.CommentDaoImpl;
import edu.umsl.java.dao.problem.ProblemDao;
import edu.umsl.java.dao.problem.ProblemDaoImpl;
import edu.umsl.java.model.Problem;

/**
 * Servlet implementation class ProblemController
 */
@WebServlet("/problem")
public class ProblemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProblemController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String problemId = request.getParameter("id");

			if (problemId == null) {
				response.sendRedirect("problemList");
				return;
			} else {
				int id = 0;

				try {
					id = Integer.parseInt(problemId);
					if (id > 0) {
						ProblemDao problemDao = new ProblemDaoImpl();
						CategoryDao categoryDao = new CategoryDaoImpl();
						CommentDao commentDao = new CommentDaoImpl();

						if (problemDao.getProblemIdExists(id)) {
							problemDao.incrementViewCountById(id);

							Problem problem = problemDao.getProblemById(id);

							request.setAttribute("problem", problem);
							request.setAttribute("categoryName", categoryDao.getCategoryById(problem.getCategoryId()).getName());
							request.setAttribute("comments", commentDao.getCommentsByProblemId(id));
							getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/problem.jsp").forward(request, response);
						} else {
							response.sendRedirect("problemList");
						}
					} else {
						response.sendRedirect("problemList");
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("problemList");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
