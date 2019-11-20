package edu.umsl.java.controller.tag;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.tag.TagDao;
import edu.umsl.java.dao.tag.TagDaoImpl;
import edu.umsl.java.model.Tag;

/**
 * Servlet implementation class TagController
 */
@WebServlet("/tag")
public class TagController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TagController() {
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

							request.setAttribute("tag", tag);
							// TODO: canDelete
							getServletContext().getRequestDispatcher("/WEB-INF/jsp/tag/tag.jsp").forward(request, response);
						} else {
							response.sendRedirect("tagList");
						}
					} else {
						response.sendRedirect("tagList");
					}
				} catch (Exception e) {
					e.printStackTrace();
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
		doGet(request, response);
	}

}
