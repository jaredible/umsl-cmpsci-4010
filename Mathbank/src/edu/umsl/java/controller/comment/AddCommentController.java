package edu.umsl.java.controller.comment;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.comment.CommentDao;
import edu.umsl.java.dao.comment.CommentDaoImpl;
import edu.umsl.java.dao.problem.ProblemDao;
import edu.umsl.java.dao.problem.ProblemDaoImpl;
import edu.umsl.java.dao.tracking.TrackingDao;
import edu.umsl.java.dao.tracking.TrackingDaoImpl;
import edu.umsl.java.model.Comment;
import edu.umsl.java.model.Tracking;
import edu.umsl.java.util.TrackingType;
import edu.umsl.java.util.Util;

/**
 * Servlet implementation class AddCommentController
 */
@WebServlet("/addComment")
public class AddCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCommentController() {
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
			String problemId = request.getParameter("problemId");
			String content = request.getParameter("comment");

			ProblemDao problemDao = new ProblemDaoImpl();
			CommentDao commentDao = new CommentDaoImpl();

			if (problemId != null) {
				try {
					int id = Integer.parseInt(problemId);
					if (id > 0) {
						if (problemDao.getProblemIdExists(id)) {
							if (!content.isEmpty() && content.length() <= 1000) {
								TrackingDao trackingDao = new TrackingDaoImpl();

								Comment comment = new Comment();
								Tracking tracking = new Tracking();

								tracking.setTrackingType(TrackingType.COMMENT.getId());
								tracking.setIp(Util.getIPFromServletRequest(request));
								tracking.setUserAgent(request.getHeader("User-Agent"));
								tracking.setCreatedTime(new Timestamp(new Date().getTime()));
								int trackingId = trackingDao.addTracking(tracking);

								comment.setProblemId(Integer.parseInt(problemId));
								comment.setContent(content);
								comment.setCreatedTime(new Timestamp(new Date().getTime()));
								comment.setTrackingId(trackingId);

								commentDao.addComment(comment);
							}
							response.sendRedirect("problem?id=" + problemId);
							return;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
