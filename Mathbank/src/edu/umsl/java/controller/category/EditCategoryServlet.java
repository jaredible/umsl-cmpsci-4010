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

import edu.umsl.java.dao.category.CategoryDao;
import edu.umsl.java.dao.category.CategoryDaoImpl;
import edu.umsl.java.dao.tracking.TrackingDao;
import edu.umsl.java.dao.tracking.TrackingDaoImpl;
import edu.umsl.java.model.Category;
import edu.umsl.java.model.Tracking;
import edu.umsl.java.util.TrackingType;
import edu.umsl.java.util.Util;

/**
 * Servlet implementation class EditCategoryServlet
 */
@WebServlet("/editCategory")
public class EditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCategoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("id");

			if (categoryId == null) {
				response.sendRedirect("categoryList");
				return;
			} else {
				CategoryDao categoryDao = new CategoryDaoImpl();

				int id = 0;

				try {
					id = Integer.parseInt(categoryId);
					if (id > 0) {
						if (categoryDao.getCategoryIdExists(id)) {
							Category category = categoryDao.getCategoryById(id);

							request.setAttribute("id", category.getId());
							request.setAttribute("name", category.getName());
							request.setAttribute("description", category.getDescription());
							getServletContext().getRequestDispatcher("/WEB-INF/jsp/category/editCategory.jsp").forward(request, response);
						} else {
							response.sendRedirect("categoryList");
						}
					} else {
						response.sendRedirect("categoryList");
					}
				} catch (Exception e) {
					response.sendRedirect("categoryList");
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
			String categoryId = request.getParameter("id");
			String name = request.getParameter("name");
			String description = request.getParameter("description");

			CategoryDao categoryDao = new CategoryDaoImpl();

			Map<String, String> errors = new HashMap<String, String>();

			if (name.isEmpty()) {
				errors.put("name", "Cannot be empty!");
			} else if (name.length() > 20) {
				errors.put("name", "Max length is 20!");
			} else if (categoryDao.getNameExists(name) && !categoryDao.getCategoryById(Integer.parseInt(categoryId)).getName().equals(name)) {
				errors.put("name", "Already exists!");
			}
			if (description.length() > 420) {
				errors.put("description", "Max length is 420!");
			}

			if (errors.isEmpty()) {
				TrackingDao trackingDao = new TrackingDaoImpl();

				Category category = categoryDao.getCategoryById(Integer.parseInt(categoryId));
				Tracking tracking = new Tracking();

				tracking.setTrackingType(TrackingType.PROBLEM.getId());
				tracking.setIp(Util.getIPFromServletRequest(request));
				tracking.setUserAgent(request.getHeader("User-Agent"));
				tracking.setCreatedTime(new Timestamp(new Date().getTime()));
				tracking.setPreviousTrackingId(category.getTrackingId());
				int trackingId = trackingDao.addTracking(tracking);

				category.setName(name);
				category.setDescription(description);
				;
				category.setTrackingId(trackingId);
				categoryDao.updateCategory(category);

				response.sendRedirect("category?id=" + categoryId);
			} else {
				request.setAttribute("id", categoryId);
				request.setAttribute("name", name);
				request.setAttribute("description", description);
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/category/editCategory.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
