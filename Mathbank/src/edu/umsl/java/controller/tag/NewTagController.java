package edu.umsl.java.controller.tag;

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

import edu.umsl.java.dao.tag.TagDao;
import edu.umsl.java.dao.tag.TagDaoImpl;
import edu.umsl.java.dao.tracking.TrackingDao;
import edu.umsl.java.dao.tracking.TrackingDaoImpl;
import edu.umsl.java.model.Tag;
import edu.umsl.java.model.Tracking;
import edu.umsl.java.util.TrackingType;
import edu.umsl.java.util.Util;

/**
 * Servlet implementation class NewTagController
 */
@WebServlet("/newTag")
public class NewTagController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewTagController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/tag/newTag.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");

			TagDao tagDao = new TagDaoImpl();

			Map<String, String> errors = new HashMap<String, String>();

			if (name.isEmpty()) {
				errors.put("name", "Cannot be empty!");
			} else if (name.length() > 20) {
				errors.put("name", "Max length is 20!");
			} else if (tagDao.getNameExists(name)) {
				errors.put("name", "Already exists!");
			}

			if (errors.isEmpty()) {
				TrackingDao trackingDao = new TrackingDaoImpl();

				Tag tag = new Tag();
				Tracking tracking = new Tracking();

				tracking.setTrackingType(TrackingType.CATEGORY.getId());
				tracking.setIp(Util.getIPFromServletRequest(request));
				tracking.setUserAgent(request.getHeader("User-Agent"));
				tracking.setCreatedTime(new Timestamp(new Date().getTime()));
				int trackingId = trackingDao.addTracking(tracking);

				tag.setName(name);
				tag.setCreatedTime(new Timestamp(new Date().getTime()));
				tag.setTrackingId(trackingId);
				int categoryId = tagDao.addTag(tag);

				response.sendRedirect("tag?id=" + categoryId);
			} else {
				request.setAttribute("name", name);
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
