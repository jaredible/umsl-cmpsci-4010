package edu.umsl.java.controller.category;

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
import edu.umsl.java.dao.TrackingDao;
import edu.umsl.java.dao.TrackingDaoImpl;
import edu.umsl.java.model.Category;
import edu.umsl.java.model.Tracking;
import edu.umsl.java.util.TrackingType;
import edu.umsl.java.util.Util;

/**
 * Servlet implementation class NewCategoryServlet
 */
@WebServlet("/newCategory")
public class NewCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewCategoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/category/newCategory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");

			CategoryDao categoryDao = new CategoryDaoImpl();

			Map<String, String> errors = new HashMap<String, String>();

			if (name.isEmpty()) {
				errors.put("name", "Cannot be empty!");
			} else if (name.length() > 20) {
				errors.put("name", "Max length is 20!");
			} else if (categoryDao.getNameExists(name)) {
				errors.put("name", "Already exists!");
			}
			if (description.length() > 420) {
				errors.put("description", "Max length is 420!");
			}

			if (errors.isEmpty()) {
				TrackingDao trackingDao = new TrackingDaoImpl();

				Category category = new Category();
				Tracking tracking = new Tracking();

				tracking.setTrackingType(TrackingType.CATEGORY.getId());
				tracking.setIp(Util.getIPFromServletRequest(request));
				tracking.setUserAgent(request.getHeader("User-Agent"));
				tracking.setCreatedTime(new Timestamp(new Date().getTime()));
				int trackingId = trackingDao.addTracking(tracking);

				category.setName(name);
				category.setDescription(description);
				category.setCreatedTime(new Timestamp(new Date().getTime()));
				category.setTrackingId(trackingId);
				int categoryId = categoryDao.addCategory(category);

				response.sendRedirect("category?id=" + categoryId);
			} else {
				request.setAttribute("name", name);
				request.setAttribute("description", description);
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
