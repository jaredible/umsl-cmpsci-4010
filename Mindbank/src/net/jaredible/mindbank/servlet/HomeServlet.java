package net.jaredible.mindbank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.dao.CategoryDAO;
import net.jaredible.mindbank.dao.CategoryDAOImpl;
import net.jaredible.mindbank.dao.ProblemDAO;
import net.jaredible.mindbank.dao.ProblemDAOImpl;
import net.jaredible.mindbank.model.Category;
import net.jaredible.mindbank.model.ProblemInfo;
import net.jaredible.mindbank.util.DbUtil;
import net.jaredible.mindbank.util.EnumLimit;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// String limit = request.getParameter("limit");
			String page = request.getParameter("page");
			String categoryId = request.getParameter("category");

			Connection conn = DbUtil.openConn();
			CategoryDAO categoryDAO = new CategoryDAOImpl(conn);
			ProblemDAO problemDAO = new ProblemDAOImpl(conn);

			List<Category> categories = categoryDAO.getCategories();
			// List<ProblemInfo> problems = problemDAO.getProblemsWithLimit(0, EnumLimit.FIVE.getLimit());
			List<ProblemInfo> problems = null;
			
			int pg = 0;
			int lim = EnumLimit.TEN.getLimit();
						
			if (page != null) {
				try {
					pg = Integer.parseInt(page);
					if (pg < 1) {
						pg = 1;
					}
				} catch (Exception e) {
				}
			}

			if (categoryId != null) {
				int n = -1;
				try {
					n = Integer.parseInt(categoryId);
				} catch (Exception e) {
				}

				if (n > 0) {
					problems = problemDAO.getAllProblemsByCategoryId(n);
				} else if (n == 0) {
					response.sendRedirect(request.getContextPath());
					return;
				} else {
					problems = problemDAO.getAllProblems();
				}

				request.setAttribute("category", n);
			} else {
				problems = problemDAO.getAllProblems();
			}

			categoryDAO.closeConnections();
			problemDAO.closeConnections();

			request.setAttribute("previousEnabled", false);
			request.setAttribute("nextEnabled", true);
			request.setAttribute("pageItem1", 0);
			request.setAttribute("pageItem2", 1);
			request.setAttribute("pageItem3", 2);
			request.setAttribute("pageItem4", 3);
			request.setAttribute("pageItem5", 4);
			request.setAttribute("categories", categories);
			request.setAttribute("problems", problems);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
