package edu.umsl.java.controller.problem;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.CategoryDao;
import edu.umsl.java.dao.CategoryDaoImpl;
import edu.umsl.java.dao.ProblemDao;
import edu.umsl.java.dao.ProblemDaoImpl;
import edu.umsl.java.dao.TrackingDao;
import edu.umsl.java.dao.TrackingDaoImpl;
import edu.umsl.java.model.Category;
import edu.umsl.java.model.Problem;
import edu.umsl.java.model.Tracking;
import edu.umsl.java.util.TrackingType;
import edu.umsl.java.util.Util;

/**
 * Servlet implementation class EditProblemServlet
 */
@WebServlet("/editProblem")
public class EditProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProblemServlet() {
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
				ProblemDao problemDao = new ProblemDaoImpl();
				CategoryDao categoryDao = new CategoryDaoImpl();

				int id = 0;

				try {
					id = Integer.parseInt(problemId);
					if (id > 0) {
						if (problemDao.getProblemIdExists(id)) {
							Problem problem = problemDao.getProblemById(id);
							List<Category> categories = categoryDao.getCategories();

							request.setAttribute("id", problem.getId());
							request.setAttribute("title", problem.getTitle());
							request.setAttribute("categoryId", problem.getCategoryId());
							request.setAttribute("content", problem.getContent());
							request.setAttribute("categories", categories);
							getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/editProblem.jsp").forward(request, response);
						} else {
							response.sendRedirect("problemList");
						}
					} else {
						response.sendRedirect("problemList");
					}
				} catch (Exception e) {
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
		try {
			String problemId = request.getParameter("id");
			String title = request.getParameter("title");
			String categoryId = request.getParameter("categoryId");
			String content = request.getParameter("content");

			ProblemDao problemDao = new ProblemDaoImpl();
			CategoryDao categoryDao = new CategoryDaoImpl();

			Map<String, String> errors = new HashMap<String, String>();
			
			int id = 0;

			if (title.isEmpty()) {
				errors.put("title", "Cannot be empty!");
			} else if (title.length() > 50) {
				errors.put("title", "Max length is 50!");
			} else if (problemDao.getTitleExists(title) && !problemDao.getProblemById(Integer.parseInt(problemId)).getTitle().equals(title)) {
				errors.put("title", "Already exists!");
			}
			if (categoryId != null) {
				try {
					id = Integer.parseInt(categoryId);
					if (id > 0) {
						if (!categoryDao.getCategoryIdExists(id)) {
							errors.put("categoryId", "Does not exist!");
						}
					} else if (id == 0) {
						errors.put("categoryId", "Select a category!");
					} else {
						errors.put("categoryId", "Invalid id!");
					}
				} catch (Exception e) {
					errors.put("categoryId", "Expected a number!");
				}
			}
			if (content.isEmpty()) {
				errors.put("content", "Cannot be empty!");
			}

			if (errors.isEmpty()) {
				TrackingDao trackingDao = new TrackingDaoImpl();

				Problem problem = problemDao.getProblemById(Integer.parseInt(problemId));
				Tracking tracking = new Tracking();

				tracking.setTrackingType(TrackingType.PROBLEM.getId());
				tracking.setIp(Util.getIPFromServletRequest(request));
				tracking.setUserAgent(request.getHeader("User-Agent"));
				tracking.setCreatedTime(new Timestamp(new Date().getTime()));
				tracking.setPreviousTrackingId(problem.getTrackingId());
				int trackingId = trackingDao.addTracking(tracking);

				problem.setTitle(title);
				problem.setCategoryId(id);
				problem.setContent(content);
				problem.setTrackingId(trackingId);
				problemDao.updateProblem(problem);

				response.sendRedirect("problem?id=" + problemId);
			} else {
				List<Category> categories = categoryDao.getCategories();

				request.setAttribute("id", problemId);
				request.setAttribute("categories", categories);
				request.setAttribute("title", title);
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("content", content);
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/editProblem.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
