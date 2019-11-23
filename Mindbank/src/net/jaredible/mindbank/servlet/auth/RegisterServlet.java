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
		String emailParam = request.getParameter("email");
		String userNameParam = request.getParameter("userName");
		String passwordParam = request.getParameter("password");
		String passwordConfirmParam = request.getParameter("passwordConfirm");

		UserDao userDao = new UserDaoImpl();

		Map<String, String> errors = new HashMap<String, String>();

		// TODO: validate params
		if (emailParam.isEmpty()) {
			errors.put("email", "Cannot be empty!");
		} else if (emailParam.length() > 50) {
			errors.put("email", "Max length is 50!");
		} else if (userDao.getUserByEmail(emailParam) != null) {
			errors.put("email", "Already exists!");
		}

		if (userNameParam.isEmpty()) {
			errors.put("userName", "Cannot be empty!");
		} else if (userNameParam.length() > 20) {
			errors.put("userName", "Max length is 20!");
		} else if (userDao.getUserByUserName(userNameParam) != null) {
			errors.put("userName", "Already exists!");
		}

		if (passwordParam.isEmpty()) {
			errors.put("password", "Cannot be empty!");
		}

		if (passwordConfirmParam.isEmpty()) {
			errors.put("passwordConfirm", "Cannot be empty!");
		} else if (!passwordConfirmParam.equals(passwordParam)) {
			errors.put("passwordConfirm", "Doesn't match!");
		}

		if (errors.isEmpty()) {
			User user = new User();

			Timestamp nowTime = new Timestamp(new Date().getTime());
			String passwordSalt = SecurityUtil.generateRandomSalt();
			String passwordHash = SecurityUtil.generateSHA512Hash(passwordParam, passwordSalt);

			user.setEmail(emailParam);
			user.setUserName(userNameParam);
			user.setRegisteredTime(nowTime);
			user.setLastLoginTime(nowTime);
			user.setPasswordSalt(passwordSalt);
			user.setPasswordHash(passwordHash);

			long userId = userDao.addUser(user);

			LoginServlet.doLogin(userId);

			response.sendRedirect("problems");
		} else {
			request.setAttribute("email", emailParam);
			request.setAttribute("userName", userNameParam);
			request.setAttribute("password", passwordParam);
			request.setAttribute("passwordConfirm", passwordConfirmParam);
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/auth/register.jsp").forward(request, response);
		}
	}

}
