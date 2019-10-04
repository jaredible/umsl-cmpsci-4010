package edu.umsl.java.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final BigInteger ZERO = BigInteger.valueOf(0);
	private static final BigInteger ONE = BigInteger.valueOf(1);
	private static final BigInteger TWO = BigInteger.valueOf(2);

	private static boolean isNegateInteger(String str) {
		return str.matches("-\\d+");
	}

	private static boolean isNonnegativeInteger(String str) {
		return str.matches("\\d+");
	}

	private static boolean isPrime(BigInteger bi) {
		if (!bi.isProbablePrime(5)) {
			return false;
		}

		if (!TWO.equals(bi) && ZERO.equals(bi.mod(TWO))) {
			return false;
		}

		for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(bi) < 1; i = i.add(bi)) {
			if (ZERO.equals(bi.mod(i))) {
				return false;
			}
		}

		return true;
	}

	private static boolean isEven(BigInteger bi) {
		return ZERO.equals(bi.mod(TWO));
	}

	private static LinkedList<BigInteger> getFactors(BigInteger bi) {
		LinkedList<BigInteger> result = new LinkedList<BigInteger>();

		if (bi.compareTo(TWO) < 0) {
			return result;
		}

		while (bi.mod(TWO).equals(ZERO)) {
			result.add(TWO);
			bi = bi.divide(TWO);
		}

		if (bi.compareTo(ONE) > 0) {
			BigInteger f = BigInteger.valueOf(3);
			while (f.multiply(f).compareTo(bi) <= 0) {
				if (bi.mod(f).equals(ZERO)) {
					result.add(f);
					bi = bi.divide(f);
				} else {
					f = f.add(TWO);
				}
			}
			result.add(bi);
		}

		return result;
	}

	private static int getLast2DigitsOfBigInteger(BigInteger bi) {
		return bi == null ? 0 : bi.mod(BigInteger.valueOf(100)).intValue();
	}

	private static BigInteger[] find2SmallerOddFactorsOfOddInteger(BigInteger bi) {
		BigInteger factor1 = ONE;
		BigInteger factor2 = bi;

		for (BigInteger i = TWO; i.compareTo(bi) < 0; i = i.add(ONE)) {
			if (bi.mod(i).compareTo(ZERO) == 0) {
				factor1 = i;
				break;
			}
		}

		factor2 = bi.divide(factor1);

		return new BigInteger[] { factor1, factor2 };
	}

	private static BigInteger getLargestPowerOf2(BigInteger bi) {
		return TWO.pow(getEvenFactors(bi).size());
	}

	private static LinkedList<BigInteger> getEvenFactors(BigInteger bi) {
		LinkedList<BigInteger> result = new LinkedList<BigInteger>();

		LinkedList<BigInteger> factors = getFactors(bi);
		Iterator<BigInteger> iterator = factors.iterator();
		while (iterator.hasNext()) {
			BigInteger next = iterator.next();
			if (next.compareTo(TWO) == 0) {
				result.add(next);
			}
		}

		return result;
	}

	private static LinkedList<BigInteger> getOddFactors(BigInteger bi) {
		LinkedList<BigInteger> result = new LinkedList<BigInteger>();

		LinkedList<BigInteger> factors = getFactors(bi);
		Iterator<BigInteger> iterator = factors.iterator();
		while (iterator.hasNext()) {
			BigInteger next = iterator.next();
			if (next.compareTo(TWO) != 0) {
				result.add(next);
			}
		}

		return result;
	}

	private static BigInteger getLargestFactorFromFactors(LinkedList<BigInteger> factors) {
		return factors.size() == 0 ? null : factors.getLast();
	}

	private static BigInteger multiplyFactors(LinkedList<BigInteger> factors) {
		BigInteger result = ONE;

		Iterator<BigInteger> iterator = factors.iterator();
		while (iterator.hasNext()) {
			BigInteger next = iterator.next();
			result = result.multiply(next);
		}

		return result;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("input");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cityIndex = request.getParameter("city");
		String number = request.getParameter("number");

		BigInteger bigInteger = null;
		String[] errors = { "", "" };

		if (Integer.parseInt(cityIndex) < 0) {
			errors[0] = "Please select a city.";
		}

		if (number == null || (number.trim().isEmpty())) {
			errors[1] = "Please provide a nonnegative integer.";
		} else if (isNegateInteger(number)) {
			errors[1] = "Please provide a nonnegative integer.";
		} else if (!isNonnegativeInteger(number)) {
			errors[1] = "Please provide a valid nonnegative integer.";
		} else {
			bigInteger = new BigInteger(number);
		}

		ServletContext ctx = getServletContext();

		if (!errors[0].isEmpty() || !errors[1].isEmpty()) {
			request.setAttribute("cities", ctx.getInitParameter("cities").split(","));
			request.setAttribute("cityIndex", cityIndex);
			request.setAttribute("number", number.toString());
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("input").forward(request, response);
			return;
		}

		String result = "";
		int serviceCost = 0;

		if (isPrime(bigInteger)) {
			result = "Prime";
			serviceCost = getLast2DigitsOfBigInteger(bigInteger);
		} else {
			if (isEven(bigInteger)) {
				BigInteger largestPowerOf2 = getLargestPowerOf2(bigInteger);
				BigInteger remainingOddNumber = multiplyFactors(getOddFactors(bigInteger));
				if (isPrime(remainingOddNumber)) {
					result = largestPowerOf2.toString() + " × " + remainingOddNumber.toString();
				} else {
					BigInteger[] remaining2OddFactors = find2SmallerOddFactorsOfOddInteger(remainingOddNumber);
					result = largestPowerOf2.toString() + " × " + remaining2OddFactors[0].toString() + " × " + remaining2OddFactors[1].toString();
				}
				// System.out.println("Last 2 digits of largest factor: " +
				// getLast2DigitsOfBigInteger(getLargestFactorFromFactors(getFactors(bigInteger))));
				// System.out.println("Prime factors: " + getFactors(bigInteger));
				// System.out.println("Largest power of 2: " + getLargestPowerOf2(bigInteger));
				// System.out.println("Even factors: " + getEvenFactors(bigInteger));
				// System.out.println("Odd factors: " + getOddFactors(bigInteger));
				// System.out.println("Largest odd factor: " +
				// getLargestFactorFromFactors(getOddFactors(bigInteger)));
			} else {
				BigInteger[] oddFactors = find2SmallerOddFactorsOfOddInteger(bigInteger);
				result = oddFactors[0].toString() + " × " + oddFactors[1].toString();
				// System.out.println("factor1: " + oddFactors[0].toString() + " factor2: " +
				// oddFactors[1].toString());
			}

			BigInteger largestFactor = getLargestFactorFromFactors(getFactors(bigInteger));
			int last2DigitsOfLargestFactor = getLast2DigitsOfBigInteger(largestFactor);
			serviceCost = last2DigitsOfLargestFactor;
		}

		String cities = ctx.getInitParameter("cities");
		String rates = ctx.getInitParameter("tax rates");

		String[] cityArr = cities.split(",");
		String[] rateArr = rates.split(",");

		int idx = Integer.parseInt(cityIndex);
		double invoice = Double.parseDouble(rateArr[idx]) + serviceCost;

		request.setAttribute("city", cityArr[idx]);
		request.setAttribute("number", number);
		request.setAttribute("result", result);
		request.setAttribute("invoice", invoice);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}
