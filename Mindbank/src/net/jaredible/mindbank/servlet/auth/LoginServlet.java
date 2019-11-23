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

import net.jaredible.mindbank.dao.auth.TokenDao;
import net.jaredible.mindbank.dao.auth.TokenDaoImpl;
import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;
import net.jaredible.mindbank.model.AuthToken;
import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.util.SecurityUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	public static void doLogin(long userId, HttpServletRequest request, HttpServletResponse response) {
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserById(userId);

		Timestamp nowTime = new Timestamp(new Date().getTime());

		user.setLastLoginTime(nowTime);

		if (userDao.updateUser(user) > 0) {
			request.getSession().setAttribute("user", user);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/auth/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailUsernameParam = request.getParameter("emailUsername");
		String passwordParam = request.getParameter("password");
		String rememberParam = request.getParameter("remember");

		UserDao userDao = new UserDaoImpl();
		User user;

		if (User.isEmail(emailUsernameParam)) {
			user = userDao.getUserByEmail(emailUsernameParam);
		} else {
			user = userDao.getUserByUserName(emailUsernameParam);
		}

		Map<String, String> errors = new HashMap<String, String>();

		System.out.println(passwordParam);
		System.out.println(user.getPasswordSalt());
		System.out.println(user.getPasswordHash());
		System.out.println(SecurityUtil.generateSHA512Hash(passwordParam, user.getPasswordSalt()));

		if (user == null || !user.getPasswordHash().equals(SecurityUtil.generateSHA512Hash(passwordParam, user.getPasswordSalt()))) {
			errors.put("error", "Incorrect email or password!");
		}

		if (errors.isEmpty()) {
			Timestamp nowTime = new Timestamp(new Date().getTime());

			user.setLastLoginTime(nowTime);

			if (userDao.updateUser(user) > 0) {
				request.getSession().setAttribute("user", user);
			}

			if (rememberParam != null && rememberParam.equals("on")) {
				AuthToken authToken = new AuthToken();

				String secretKey = SecurityUtil.generateRandomSalt();
				String selector = RandomStringUtils.randomAlphanumeric(12);
				String rawValidator = RandomStringUtils.randomAlphanumeric(64);
				String hashedValidator = SecurityUtil.generateSHA512Hash(rawValidator, secretKey);

				authToken.setUserId(user.getId());
				authToken.setSecretKey(secretKey);
				authToken.setSelector(selector);
				authToken.setValidator(hashedValidator);

				TokenDao tokenDao = new TokenDaoImpl();
				tokenDao.addToken(authToken);

				int cookieMaxAge = 60 * 60 * 24;
				Cookie cookieSelector = new Cookie("selector", selector);
				cookieSelector.setMaxAge(cookieMaxAge);
				Cookie cookieValidator = new Cookie("validator", rawValidator);
				cookieValidator.setMaxAge(cookieMaxAge);

				response.addCookie(cookieSelector);
				response.addCookie(cookieValidator);
			}

			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("emailUsername", emailUsernameParam);
			request.setAttribute("password", passwordParam);
			request.setAttribute("remember", rememberParam);
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/auth/login.jsp").forward(request, response);
		}
	}

}
