package main.java.mindbank.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import main.java.mindbank.dao.AuthDAO;
import main.java.mindbank.dao.AuthDAOImpl;
import main.java.mindbank.dao.UserDAO;
import main.java.mindbank.dao.UserDAOImpl;
import main.java.mindbank.model.AuthToken;
import main.java.mindbank.model.User;
import main.java.mindbank.util.HashGenerationException;
import main.java.mindbank.util.HashGeneratorUtil;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = { "account", "problem", "settings" })
public class AuthFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
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
				try {
					AuthDAO authDAO = new AuthDAOImpl();
					AuthToken token = authDAO.findBySelector(selector);

					if (token != null) {
						String hashedValidatorDatabase = token.getValidator();
						String hashedValidatorCookie = HashGeneratorUtil.generateSHA256(rawValidator);

						if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
							UserDAO userDAO = new UserDAOImpl();
							User user = userDAO.getUser(token.getUserId());

							session = req.getSession();
							session.setAttribute("user", user);
							loggedIn = true;

							String newSelector = RandomStringUtils.randomAlphanumeric(12);
							String newRawValidator = RandomStringUtils.randomAlphanumeric(64);

							String newHashedValidator = HashGeneratorUtil.generateSHA256(newRawValidator);

							token.setSelector(newSelector);
							token.setValidator(newHashedValidator);
							authDAO.updateWithToken(token);

							int cookieMaxAge = 60 * 60 * 24;

							Cookie cookieSelector = new Cookie("selector", newSelector);
							cookieSelector.setMaxAge(cookieMaxAge);

							Cookie cookieValidator = new Cookie("validator", newRawValidator);
							cookieValidator.setMaxAge(cookieMaxAge);

							res.addCookie(cookieSelector);
							res.addCookie(cookieValidator);
						}
					}
				} catch (HashGenerationException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		String uri = req.getRequestURI();
		if (uri.endsWith("css") || uri.endsWith("js")) {
			chain.doFilter(req, res);
			return;
		}

		System.out.println(loggedIn);

		if (!loggedIn) {
			res.sendRedirect("login");
		} else {
			chain.doFilter(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
