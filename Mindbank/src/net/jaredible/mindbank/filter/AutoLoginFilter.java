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

import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.service.UserService;
import net.jaredible.mindbank.util.SecurityUtil;
import net.jaredible.mindbank.util.TimeUtil;

public class AutoLoginFilter implements Filter {

	private UserService userService = UserService.getInstance();

	public AutoLoginFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		boolean loggedIn = session != null && session.getAttribute("userName") != null;

		Cookie[] cookies = req.getCookies();

		if (!loggedIn && cookies != null) {
			Cookie cookieSelector = null;
			Cookie cookieRawValidator = null;

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("selector")) {
					cookieSelector = cookie;
				} else if (cookie.getName().equals("validator")) {
					cookieRawValidator = cookie;
				}
			}

			if (cookieSelector != null && cookieRawValidator != null) {
				User user = userService.getUserByCookieSelector(cookieSelector.getValue());

				if (user != null) {
					String tokenSecretKey = user.getCookieSecretKey();
					String dbHashedValidatorDatabase = user.getHashedCookieValidator();
					String clientHashedValidatorCookie = SecurityUtil.generateSHA512Hash(cookieRawValidator.getValue(), tokenSecretKey);

					if (clientHashedValidatorCookie.equals(dbHashedValidatorDatabase)) {
						session = req.getSession();
						session.setAttribute("userName", user.getUserName());
						loggedIn = true;

						String newSelector = RandomStringUtils.randomAlphanumeric(12);
						String newRawValidator = RandomStringUtils.randomAlphanumeric(64);
						userService.updateCookieInfo(user.getUserName(), newSelector, newRawValidator);

						int cookieMaxAge = TimeUtil.getNumSecondsInDay();
						cookieSelector.setValue(newSelector);
						cookieSelector.setPath(req.getServletContext().getContextPath());
						cookieSelector.setMaxAge(cookieMaxAge);
						res.addCookie(cookieSelector);
						cookieRawValidator.setValue(newRawValidator);
						cookieRawValidator.setPath(req.getServletContext().getContextPath());
						cookieRawValidator.setMaxAge(cookieMaxAge);
						res.addCookie(cookieRawValidator);
					}
				}
			}
		}

		if (loggedIn) {
			chain.doFilter(req, res);
		} else {
			res.sendRedirect(req.getContextPath() + "/login");
		}
	}

	public void destroy() {
	}

}
