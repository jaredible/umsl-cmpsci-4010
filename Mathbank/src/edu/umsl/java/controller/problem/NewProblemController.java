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

import edu.umsl.java.dao.category.CategoryDao;
import edu.umsl.java.dao.category.CategoryDaoImpl;
import edu.umsl.java.dao.problem.ProblemDao;
import edu.umsl.java.dao.problem.ProblemDaoImpl;
import edu.umsl.java.dao.tag.TagDao;
import edu.umsl.java.dao.tag.TagDaoImpl;
import edu.umsl.java.dao.tracking.TrackingDao;
import edu.umsl.java.dao.tracking.TrackingDaoImpl;
import edu.umsl.java.model.Category;
import edu.umsl.java.model.Problem;
import edu.umsl.java.model.Tag;
import edu.umsl.java.model.Tracking;
import edu.umsl.java.util.SecurityUtil;
import edu.umsl.java.util.TrackingType;
import edu.umsl.java.util.Util;

/**
 * Servlet implementation class NewProblemController
 */
@WebServlet("/newProblem")
public class NewProblemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewProblemController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryDao categoryDao = new CategoryDaoImpl();
			TagDao tagDao = new TagDaoImpl();

			List<Category> categories = categoryDao.getCategories();
			List<Tag> tags = tagDao.getTags();

			request.setAttribute("categories", categories);
			request.setAttribute("tags", tags);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/newProblem.jsp").forward(request, response);
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
			String tagId = request.getParameter("tagId");
			String content = request.getParameter("content");
			String password = request.getParameter("password");

			ProblemDao problemDao = new ProblemDaoImpl();
			CategoryDao categoryDao = new CategoryDaoImpl();
			TagDao tagDao = new TagDaoImpl();

			Map<String, String> errors = new HashMap<String, String>();

			int id = 0;

			// String hash = SecurityUtil.generateSHA512Hash("testing");
			// String[] split = hash.split(":");
			// System.out.println(hash);
			// System.out.println(split[0]);
			// System.out.println(split[1]);

			// String data = "testing";
			// SecretKey secretKey = SecurityUtil.generateSecretKey();
			// String encrypt = SecurityUtil.encrypt(data, secretKey);
			// System.out.println(encrypt);
			// System.out.println(SecurityUtil.decrypt(encrypt, secretKey));

			if (title.isEmpty()) {
				errors.put("title", "Cannot be empty!");
			} else if (title.length() > 50) {
				errors.put("title", "Max length is 50!");
			} else if (problemDao.getTitleExists(title)) {
				errors.put("title", "Already exists!");
			}
			if (categoryId != null) { // TODO: check when null
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
			if (tagId != null) { // TODO: check when null
				try {
					id = Integer.parseInt(tagId);
					if (id > 0) {
						if (!tagDao.getTagIdExists(id)) {
							errors.put("tagId", "Does not exist!");
						}
					} else if (id == 0) {
						errors.put("tagId", "Select a category!");
					} else {
						errors.put("tagId", "Invalid id!");
					}
				} catch (Exception e) {
					errors.put("tagId", "Expected a number!");
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

				problem.setCategoryId(id);
				problem.setTitle(title);
				problem.setContent(content);
				problem.setPasswordHash(SecurityUtil.generateSHA512Hash(password));
				problem.setCreatedTime(new Timestamp(new Date().getTime()));
				problem.setTrackingId(trackingId);
				int problemId = problemDao.addProblem(problem);

				response.sendRedirect("problem?id=" + problemId);
			} else {
				request.setAttribute("title", title);
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("tagId", tagId);
				request.setAttribute("content", content);
				request.setAttribute("password", password);
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
