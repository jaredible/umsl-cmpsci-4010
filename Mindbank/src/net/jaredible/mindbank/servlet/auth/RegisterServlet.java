package net.jaredible.mindbank.servlet.auth;

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

import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;
import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.util.SecurityUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/auth/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");

		UserDao userDao = new UserDaoImpl();

		Map<String, String> errors = new HashMap<String, String>();

		if (email == null || email.isEmpty()) {
			errors.put("email", "This field is required");
		} else if (email.length() > 50) {
			errors.put("email", "Max 50 characters");
		} else if (userDao.getUserByEmail(email) != null) {
			errors.put("email", "Already in use");
		}

		if (username == null || username.isEmpty()) {
			errors.put("userName", "This field is required");
		} else if (username.length() > 20) {
			errors.put("userName", "Max 20 characters");
		} else if (userDao.getUserByUserName(username) != null) {
			errors.put("userName", "Already in use");
		}

		if (password == null || password.isEmpty()) {
			errors.put("password", "This field is required");
		}

		if (passwordConfirm != null && !passwordConfirm.equals(password)) {
			errors.put("passwordConfirm", "Does not match");
		}

		if (errors.isEmpty()) {
			User user = new User();

			Timestamp nowTime = new Timestamp(new Date().getTime());
			String passwordSalt = SecurityUtil.generateRandomSalt();
			String passwordHash = SecurityUtil.generateSHA512Hash(password, passwordSalt);

			user.setEmail(email);
			user.setUserName(username);
			user.setRegisteredTime(nowTime);
			user.setLastLoginTime(nowTime);
			user.setPasswordSalt(passwordSalt);
			user.setPasswordHash(passwordHash);

			userDao.addUser(user);

			response.sendRedirect(getServletContext().getContextPath() + "/login");
		} else {
			request.setAttribute("email", email);
			request.setAttribute("userName", username);
			request.setAttribute("password", password);
			request.setAttribute("passwordConfirm", passwordConfirm);
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/auth/register.jsp").forward(request, response);
		}
	}

}
