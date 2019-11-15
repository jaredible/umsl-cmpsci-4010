package edu.umsl.java.controller.problem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.ProblemDao;
import edu.umsl.java.dao.ProblemDaoImpl;

/**
 * Servlet implementation class DeleteProblemServlet
 */
@WebServlet("/deleteProblem")
public class DeleteProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProblemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("problemList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String problemId = request.getParameter("id");

			ProblemDao problemDao = new ProblemDaoImpl();

			int id = 0;

			if (problemId != null) {
				try {
					id = Integer.parseInt(problemId);
					if (id > 0) {
						if (problemDao.getProblemIdExists(id)) {
							problemDao.deleteProblemById(id);
							response.sendRedirect("problemList");
							return;
						}
					}
				} catch (Exception e) {
				}
			}

			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
