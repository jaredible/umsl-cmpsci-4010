package edu.umsl.java.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import edu.umsl.java.dao.tracking.TrackingDao;
import edu.umsl.java.dao.tracking.TrackingDaoImpl;
import edu.umsl.java.model.Tracking;
import edu.umsl.java.util.TrackingType;
import edu.umsl.java.util.Util;

/**
 * Servlet Filter implementation class ViewFilter
 */
@WebFilter("/*")
public class ViewFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ViewFilter() {
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
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			TrackingDao trackingDao = new TrackingDaoImpl();
			Tracking tracking = new Tracking();

			tracking.setTrackingType(TrackingType.VIEW.getId());
			tracking.setIp(Util.getIPFromServletRequest(request));
			tracking.setUserAgent(req.getHeader("User-Agent"));
			tracking.setCreatedTime(new Timestamp(new Date().getTime()));
			trackingDao.addTracking(tracking); // TODO: context param
		} catch (Exception e) {
			e.printStackTrace();
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
