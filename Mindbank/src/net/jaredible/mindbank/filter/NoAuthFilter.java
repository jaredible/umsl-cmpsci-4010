package net.jaredible.mindbank.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NoAuthFilter implements Filter {

	public NoAuthFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		boolean loggedIn = session != null && session.getAttribute("userName") != null;

		if (loggedIn) {
			res.sendRedirect(req.getContextPath());
		} else {
			chain.doFilter(req, res);
		}
	}

	public void destroy() {
	}

}
