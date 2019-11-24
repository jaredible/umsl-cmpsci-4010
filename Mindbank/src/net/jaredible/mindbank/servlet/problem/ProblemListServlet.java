package net.jaredible.mindbank.servlet.problem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.dao.category.CategoryDao;
import net.jaredible.mindbank.dao.category.CategoryDaoImpl;
import net.jaredible.mindbank.dao.tag.TagDao;
import net.jaredible.mindbank.dao.tag.TagDaoImpl;

@WebServlet("/problems")
public class ProblemListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProblemListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDao categoryDao = new CategoryDaoImpl();
		TagDao tagDao = new TagDaoImpl();

		request.setAttribute("categories", categoryDao.getAllCategories());
		request.setAttribute("tags", tagDao.getAllTags());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/problem/problemList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] tagIds = request.getParameterValues("tagIds");
		for (String tagId : tagIds) {
			System.out.println(tagId);
		}
		doGet(request, response);
	}

}
