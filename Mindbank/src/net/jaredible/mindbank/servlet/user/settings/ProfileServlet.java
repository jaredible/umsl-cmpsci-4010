package net.jaredible.mindbank.servlet.user.settings;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

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

@WebServlet("/settings/profile")
@MultipartConfig(maxFileSize = 16777216)
public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession(false).getAttribute("user");

		String profileImage = null;
		String email = user.getEmail();
		String name = user.getName();
		String statusEmoji = user.getStatusEmoji();
		String statusText = user.getStatusText();
		String bio = user.getBio();

		try {
			Blob image = user.getProfileImage();
			if (image != null) {
				profileImage = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(IOUtils.toByteArray(image.getBinaryStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("profileImage", profileImage);
		request.setAttribute("email", email);
		request.setAttribute("name", name);
		request.setAttribute("statusEmoji", statusEmoji);
		request.setAttribute("statusText", statusText);
		request.setAttribute("bio", bio);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/settings/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("profileImage");
		String name = request.getParameter("name");
		String statusEmoji = request.getParameter("statusEmoji");
		String statusText = request.getParameter("statusText");
		String bio = request.getParameter("bio");

		UserDao userDao = new UserDaoImpl();
		User user = (User) request.getSession(false).getAttribute("user");

		InputStream is = null;
		if (filePart != null) {
			is = filePart.getInputStream();
			if (is != null) {
				try {
					user.setProfileImage(new SerialBlob(IOUtils.toByteArray(is)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		user.setName(name);
		user.setStatusEmoji(statusEmoji);
		user.setStatusText(statusText);
		user.setBio(bio);

		userDao.updateUser(user);

		String profileImage = null;
		String email = user.getEmail();

		try {
			Blob image = user.getProfileImage();
			if (image != null) {
				profileImage = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(IOUtils.toByteArray(image.getBinaryStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("profileImage", profileImage);
		request.setAttribute("email", email);
		request.setAttribute("name", name);
		request.setAttribute("statusEmoji", statusEmoji);
		request.setAttribute("statusText", statusText);
		request.setAttribute("bio", bio);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/settings/profile.jsp").forward(request, response);
	}

}
