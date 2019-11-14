package edu.umsl.java.controller.problem;

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
 * Servlet implementation class AddProblemServlet
 */
@WebServlet("/addProblem")
public class AddProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProblemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryDao categoryDao = new CategoryDaoImpl();

			Map<Integer, Category> categories = categoryDao.getCategories();

			request.setAttribute("categories", categories);
			getServletContext().getRequestDispatcher("/addProblem.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
			} else if (problemDao.getTitleExists(title)) {
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

				Problem problem = new Problem();
				Tracking tracking = new Tracking();

				tracking.setTrackingType(TrackingType.PROBLEM.getId());
				tracking.setIp(Util.getIPFromServletRequest(request));
				tracking.setUserAgent(request.getHeader("User-Agent"));
				tracking.setCreatedTime(new Timestamp(new Date().getTime()));
				int trackingId = trackingDao.addTracking(tracking);

				problem.setTitle(title);
				problem.setCategoryId(id);
				problem.setContent(content);
				problem.setCreatedTime(new Timestamp(new Date().getTime()));
				problem.setTrackingId(trackingId);
				problemDao.addProblem(problem);

				response.sendRedirect("problemList");
			} else {
				request.setAttribute("title", title);
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("content", content);
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
