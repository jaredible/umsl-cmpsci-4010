package edu.umsl.java.controller.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.tag.TagDao;
import edu.umsl.java.dao.tag.TagDaoImpl;
import edu.umsl.java.model.Tag;

/**
 * Servlet implementation class TagListController
 */
@WebServlet("/tagList")
public class TagListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TagListController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			TagDao tagDao = new TagDaoImpl();

			List<Tag> tags = tagDao.getTags();

			request.setAttribute("tags", tags);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/tag/tagList.jsp").forward(request, response);
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
