package net.jaredible.mindbank.servlet.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;
import net.jaredible.mindbank.model.User;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		if (user != null) {
			UserDao userDao = new UserDaoImpl();

			user.setCookieSecretKey(null);
			user.setCookieSelector(null);
			user.setHashedCookieValidator(null);

			if (userDao.updateUser(user) > 0) {
				Cookie cookieSelector = new Cookie("selector", null);
				cookieSelector.setPath(getServletContext().getContextPath());
				cookieSelector.setMaxAge(0);
				response.addCookie(cookieSelector);

				Cookie cookieValidator = new Cookie("validator", null);
				cookieValidator.setPath(getServletContext().getContextPath());
				cookieValidator.setMaxAge(0);
				response.addCookie(cookieValidator);
			}
		}

		session.removeAttribute("user");

		response.sendRedirect(request.getServletContext().getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
