package net.jaredible.mindbank.servlet.auth;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;
import net.jaredible.mindbank.model.user.User;
import net.jaredible.mindbank.util.SecurityUtil;
import net.jaredible.mindbank.util.TimeUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/auth/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");

		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByUserName(username);

		Map<String, String> errors = new HashMap<String, String>();

		if (user == null || !user.getPasswordHash().equals(SecurityUtil.generateSHA512Hash(password, user.getPasswordSalt()))) {
			errors.put("error", "Incorrect username or password");
		}

		if (errors.isEmpty()) {
			Timestamp nowTime = new Timestamp(new Date().getTime());

			user.setLastLoginTime(nowTime);

			if (remember != null && remember.equals("on")) {
				String secretKey = SecurityUtil.generateRandomSalt();
				String selector = RandomStringUtils.randomAlphanumeric(12);
				String rawValidator = RandomStringUtils.randomAlphanumeric(64);
				String hashedValidator = SecurityUtil.generateSHA512Hash(rawValidator, secretKey);

				user.setCookieSecretKey(secretKey);
				user.setCookieSelector(selector);
				user.setHashedCookieValidator(hashedValidator);

				int cookieMaxAge = TimeUtil.getNumSecondsInDay();

				Cookie cookieSelector = new Cookie("selector", selector);
				cookieSelector.setPath(getServletContext().getContextPath());
				cookieSelector.setMaxAge(cookieMaxAge);
				response.addCookie(cookieSelector);

				Cookie cookieValidator = new Cookie("validator", rawValidator);
				cookieSelector.setPath(getServletContext().getContextPath());
				cookieValidator.setMaxAge(cookieMaxAge);
				response.addCookie(cookieValidator);
			}

			if (userDao.updateUser(user) > 0) {
				request.getSession().setAttribute("user", user);
			}

			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("remember", remember);
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/auth/login.jsp").forward(request, response);
		}
	}

}
