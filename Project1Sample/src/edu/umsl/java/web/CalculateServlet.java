package edu.umsl.java.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletContext ctx = this.getServletContext();

		String cities = ctx.getInitParameter("cities");
		String rates = ctx.getInitParameter("tax rates");
		
		String city_index = request.getParameter("city");
		
		String[] cityArr = cities.split(",");
		String[] rateArr = rates.split(",");
		
		try {
			int idx = Integer.parseInt(city_index);
			
			System.out.println("Selected city: " + cityArr[idx]);
			System.out.println("Tax rates: " + rateArr[idx]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");

		view.forward(request, response);

	}

}
