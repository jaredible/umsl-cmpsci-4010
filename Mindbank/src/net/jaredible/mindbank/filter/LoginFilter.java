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

import net.jaredible.mindbank.dao.user.UserDao;
import net.jaredible.mindbank.dao.user.UserDaoImpl;
import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.util.SecurityUtil;
import net.jaredible.mindbank.util.TimeUtil;

public class LoginFilter implements Filter {

	public LoginFilter() {
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
			Cookie cookieSelector = null;
			Cookie cookieRawValidator = null;

			for (Cookie c : cookies) {
				if (c.getName().equals("selector")) {
					cookieSelector = c;
				} else if (c.getName().equals("validator")) {
					cookieRawValidator = c;
				}
			}

			if (cookieSelector != null && cookieRawValidator != null) {
				UserDao userDao = new UserDaoImpl();
				User user = userDao.getUserByCookieSelector(cookieSelector.getValue());

				if (user != null) {
					String tokenSecretKey = user.getCookieSecretKey();
					String hashedValidatorDatabase = user.getHashedCookieValidator();
					String hashedValidatorCookie = SecurityUtil.generateSHA512Hash(cookieRawValidator.getValue(), tokenSecretKey);

					if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
						session = req.getSession();
						session.setAttribute("user", user);
						loggedIn = true;

						String newSecretKey = SecurityUtil.generateRandomSalt();
						String newSelector = RandomStringUtils.randomAlphanumeric(12);
						String newRawValidator = RandomStringUtils.randomAlphanumeric(64);
						String newHashedValidator = SecurityUtil.generateSHA512Hash(newRawValidator, newSecretKey);

						user.setCookieSecretKey(newSecretKey);
						user.setCookieSelector(newSelector);
						user.setHashedCookieValidator(newHashedValidator);

						if (userDao.updateUser(user) > 0) {
							int cookieMaxAge = TimeUtil.getNumSecondsInDay();
							cookieSelector.setMaxAge(cookieMaxAge);
							cookieSelector.setValue(newSelector);
							cookieRawValidator.setMaxAge(cookieMaxAge);
							cookieRawValidator.setValue(newRawValidator);

							res.addCookie(cookieSelector);
							res.addCookie(cookieRawValidator);
						}
					}
				}
			}
		}

		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
