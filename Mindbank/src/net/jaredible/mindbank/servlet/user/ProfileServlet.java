package net.jaredible.mindbank.servlet.user;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;
import net.jaredible.mindbank.model.User;

@WebServlet("/profile")
@MultipartConfig(maxFileSize = 16777216)
public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");

		UserDao userDao = new UserDaoImpl();
		User user;

		if (userId != null) {
			user = userDao.getUserById(Long.parseLong(userId));
		} else {
			user = (User) request.getSession(false).getAttribute("user");
		}

		//request.setAttribute("name", user.getName());
		//request.setAttribute("bio", user.getBio());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String bio = request.getParameter("bio");

		System.out.println(name);
		System.out.println(bio);

		InputStream is = null;

		Part filePart = request.getPart("profileImage");
		if (filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			is = filePart.getInputStream();
		}

		User user = new User();

		user.setName(name);
		user.setBio(bio);

		if (is != null) {
			try {
				user.setProfileImage(new SerialBlob(IOUtils.toByteArray(is)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("name", name);
		request.setAttribute("bio", bio);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/profile.jsp").forward(request, response);
	}

}
