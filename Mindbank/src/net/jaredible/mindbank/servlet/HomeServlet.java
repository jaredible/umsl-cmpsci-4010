package net.jaredible.mindbank.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringUtils;

import net.jaredible.mindbank.dao.category.CategoryDao;
import net.jaredible.mindbank.dao.category.CategoryDaoImpl;
import net.jaredible.mindbank.dao.problem.ProblemDao;
import net.jaredible.mindbank.dao.problem.ProblemDaoImpl;
import net.jaredible.mindbank.dao.tag.TagDao;
import net.jaredible.mindbank.dao.tag.TagDaoImpl;
import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;
import net.jaredible.mindbank.model.Problem;

@WebServlet("")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProblemDao problemDao = new ProblemDaoImpl();
		CategoryDao categoryDao = new CategoryDaoImpl();
		TagDao tagDao = new TagDaoImpl();
		UserDao userDao = new UserDaoImpl();

		request.setAttribute("problems", problemDao.getAllProblems());
		request.setAttribute("categories", categoryDao.getAllCategories());
		request.setAttribute("tags", tagDao.getAllTags());
		request.setAttribute("users", userDao.getAllUsers());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String[] categoryIds = request.getParameterValues("categoryIds");
		String[] tagIds = request.getParameterValues("tagIds");
		String content = request.getParameter("content");
		String datetimeStart = request.getParameter("datetimeStart");
		String datetimeEnd = request.getParameter("datetimeEnd");
		String[] userIds = request.getParameterValues("userIds");

		ProblemDao problemDao = new ProblemDaoImpl();
		CategoryDao categoryDao = new CategoryDaoImpl();
		TagDao tagDao = new TagDaoImpl();
		UserDao userDao = new UserDaoImpl();

		System.out.println("title:" + title);
		System.out.print("categoryIds:");
		for (String categoryId : categoryIds) {
			System.out.println(categoryId);
		}
		System.out.print("tagIds:");
		for (String tagId : tagIds) {
			System.out.println(tagId);
		}
		System.out.println("content:" + content);
		System.out.println("datetimeStart:" + datetimeStart);
		System.out.println("datetimeEnd:" + datetimeEnd);
		System.out.print("userIds:");
		for (String userId : userIds) {
			System.out.println(userId);
		}

		String titleLike = title;
		String categoryIdsRegex = StringUtils.join(Arrays.asList(categoryIds)).replaceAll(",", "|"); // TODO
		String tagIdsRegex = StringUtils.join(Arrays.asList(tagIds)).replaceAll(",", "|"); // TODO
		String contentLike = content;
		String dateCreatedStart = "1000-01-01 00:00:00.000000";
		String dateCreatedEnd = "9999-12-31 23:59:59.999999";
		String userIdsRegex = StringUtils.join(Arrays.asList(userIds)).replaceAll(",", "|"); // TODO

		try {
			String oldFormat = "MMM d, yyyy hh:mm aa";
			String newFormat = "yyyy-MM-dd HH:mm:ss";

			SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);

			Date dateStart = null;
			Date dateEnd = null;

			if (datetimeStart != null && !datetimeStart.isEmpty()) {
				dateStart = sdf.parse(datetimeStart);
			}
			if (datetimeEnd != null && !datetimeEnd.isEmpty()) {
				dateEnd = sdf.parse(datetimeEnd);
			}

			if (dateStart != null) {
				sdf.applyPattern(newFormat);
				dateCreatedStart = sdf.format(dateStart);
			}
			sdf.applyPattern(oldFormat);
			if (dateEnd != null) {
				sdf.applyPattern(newFormat);
				dateCreatedEnd = sdf.format(dateEnd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("titleLike:" + titleLike + ":");
		System.out.println("categoryIdsRegex:" + categoryIdsRegex + ":");
		System.out.println("tagIdsRegex:" + tagIdsRegex + ":");
		System.out.println("contentLike:" + contentLike + ":");
		System.out.println("dateCreatedStart:" + dateCreatedStart + ":");
		System.out.println("dateCreatedEnd:" + dateCreatedEnd + ":");
		System.out.println("userIds:" + userIdsRegex + ":");

		List<Problem> problems = problemDao.getProblemsByFields(titleLike, categoryIdsRegex, tagIdsRegex, contentLike, dateCreatedStart, dateCreatedEnd, userIdsRegex);

		request.setAttribute("title", title);
		request.setAttribute("categoryIds", StringUtils.join(categoryIds));
		request.setAttribute("tagIds", StringUtils.join(tagIds));
		request.setAttribute("content", content);
		request.setAttribute("datetimeStart", datetimeStart);
		request.setAttribute("datetimeEnd", datetimeEnd);
		request.setAttribute("userIds", StringUtils.join(userIds));
		request.setAttribute("problems", problems);
		request.setAttribute("categories", categoryDao.getAllCategories());
		request.setAttribute("tags", tagDao.getAllTags());
		request.setAttribute("users", userDao.getAllUsers());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

}
