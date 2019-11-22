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
 * Servlet implementation class EditTagController
 */
@WebServlet("/editTag")
public class EditTagController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditTagController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String tagId = request.getParameter("id");

			if (tagId == null) {
				response.sendRedirect("tagList");
				return;
			} else {
				TagDao tagDao = new TagDaoImpl();

				int id = 0;

				try {
					id = Integer.parseInt(tagId);
					if (id > 0) {
						if (tagDao.getTagIdExists(id)) {
							Tag tag = tagDao.getTagById(id);

							request.setAttribute("id", tag.getId());
							request.setAttribute("name", tag.getName());
							getServletContext().getRequestDispatcher("/WEB-INF/jsp/tag/editTag.jsp").forward(request, response);
						} else {
							response.sendRedirect("tagList");
						}
					} else {
						response.sendRedirect("tagList");
					}
				} catch (Exception e) {
					response.sendRedirect("tagList");
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
			String tagId = request.getParameter("id");
			String name = request.getParameter("name");

			TagDao tagDao = new TagDaoImpl();

			Map<String, String> errors = new HashMap<String, String>();

			if (name.isEmpty()) {
				errors.put("name", "Cannot be empty!");
			} else if (name.length() > 20) {
				errors.put("name", "Max length is 20!");
			} else if (tagDao.getNameExists(name) && !tagDao.getTagById(Integer.parseInt(tagId)).getName().equals(name)) {
				errors.put("name", "Already exists!");
			}

			if (errors.isEmpty()) {
				TrackingDao trackingDao = new TrackingDaoImpl();

				Tag tag = tagDao.getTagById(Integer.parseInt(tagId));
				Tracking tracking = new Tracking();

				tracking.setTrackingType(TrackingType.PROBLEM.getId());
				tracking.setIp(Util.getIPFromServletRequest(request));
				tracking.setUserAgent(request.getHeader("User-Agent"));
				tracking.setCreatedTime(new Timestamp(new Date().getTime()));
				tracking.setPreviousTrackingId(tag.getTrackingId());
				int trackingId = trackingDao.addTracking(tracking);

				tag.setName(name);
				tag.setTrackingId(trackingId);
				tagDao.updateTag(tag);

				response.sendRedirect("tag?id=" + tagId);
			} else {
				request.setAttribute("id", tagId);
				request.setAttribute("name", name);
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/tag/editTag.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
