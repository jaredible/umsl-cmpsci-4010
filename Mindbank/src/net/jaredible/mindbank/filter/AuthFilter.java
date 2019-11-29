package net.jaredible.mindbank.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import net.jaredible.mindbank.dao.auth.TokenDao;
import net.jaredible.mindbank.dao.auth.TokenDaoImpl;
import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;
import net.jaredible.mindbank.model.AuthToken;
import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.util.SecurityUtil;

public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		boolean loggedIn = session != null && session.getAttribute("user") != null;

		Cookie[] cookies = req.getCookies();

		if (!loggedIn && cookies != null) {
			String selector = null;
			String rawValidator = null;

			for (Cookie c : cookies) {
				if (c.getName().equals("selector")) {
					selector = c.getValue();
				} else if (c.getName().equals("validator")) {
					rawValidator = c.getValue();
				}
			}

			if (selector != null && rawValidator != null) {
				TokenDao tokenDao = new TokenDaoImpl();
				AuthToken authToken = tokenDao.getTokenBySelector(selector);

				if (authToken != null) {
					String tokenSecretKey = authToken.getSecretKey();
					String hashedValidatorDatabase = authToken.getValidator();
					String hashedValidatorCookie = SecurityUtil.generateSHA512Hash(rawValidator, tokenSecretKey);

					if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
						UserDao userDao = new UserDaoImpl();
						User user = userDao.getUserById(authToken.getUserId());

						session = req.getSession();
						session.setAttribute("user", user);
						loggedIn = true;

						String newSecretKey = SecurityUtil.generateRandomSalt();
						String newSelector = RandomStringUtils.randomAlphanumeric(12);
						String newRawValidator = RandomStringUtils.randomAlphanumeric(64);
						String newHashedValidator = SecurityUtil.generateSHA512Hash(newRawValidator, newSecretKey);

						authToken.setSecretKey(newSecretKey);
						authToken.setSelector(newSelector);
						authToken.setValidator(newHashedValidator);
						tokenDao.updateToken(authToken);

						int cookieMaxAge = 60 * 60 * 24;
						Cookie cookieSelector = new Cookie("selector", newSelector);
						cookieSelector.setMaxAge(cookieMaxAge);
						Cookie cookieValidator = new Cookie("validator", newRawValidator);
						cookieValidator.setMaxAge(cookieMaxAge);

						res.addCookie(cookieSelector);
						res.addCookie(cookieValidator);
						// res.addCookie(new Cookie("selector", "test"));
					}
				}
			}
		}

		if (loggedIn) {
			chain.doFilter(req, res);
		} else {
			res.sendRedirect(req.getServletContext().getContextPath() + "/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
