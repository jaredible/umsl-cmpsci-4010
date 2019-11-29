package net.jaredible.mindbank.servlet.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jaredible.mindbank.dao.auth.TokenDao;
import net.jaredible.mindbank.dao.auth.TokenDaoImpl;
import net.jaredible.mindbank.model.AuthToken;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("user");

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			String selector = null;

			for (Cookie c : cookies) {
				if (c.getName().equals("selector")) {
					selector = c.getValue();
				}
			}

			if (selector != null) {
				TokenDao tokenDao = new TokenDaoImpl();
				AuthToken authToken = tokenDao.getTokenBySelector(selector);

				if (authToken != null) {
					tokenDao.deleteTokenById(authToken.getId());

					int cookieMaxAge = 0;
					Cookie cookieSelector = new Cookie("selector", null);
					cookieSelector.setMaxAge(cookieMaxAge);
					Cookie cookieValidator = new Cookie("validator", null);
					cookieValidator.setMaxAge(cookieMaxAge);

					response.addCookie(cookieSelector);
					response.addCookie(cookieValidator);
				}
			}
		}

		response.sendRedirect(request.getServletContext().getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
