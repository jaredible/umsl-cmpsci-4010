package net.jaredible.umsl.cmpsci4010;

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

import org.mariuszgromada.math.mxparser.Expression;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final boolean DEBUG = false;

	private static final BigInteger ZERO = BigInteger.valueOf(0);
	private static final BigInteger ONE = BigInteger.valueOf(1);
	private static final BigInteger TWO = BigInteger.valueOf(2);

	private static boolean isNegateInteger(String str) {
		return str.matches("^-\\d+$"); // (e.g -0, -000, -1, -123...)
	}

	private static boolean isNonnegativeInteger(String str) {
		return str.matches("^\\d+$"); // (e.g. 0, 1, 000, 111, 123...)
	}

	private static boolean isPrime(BigInteger bi) {
		// First, let us check if bi is probably prime.
		if (!bi.isProbablePrime(5)) {
			return false;
		}

		// Second, if bi is not 2 and even, then it is definitely not a prime.
		if (!TWO.equals(bi) && ZERO.equals(bi.mod(TWO))) { // (equivalent to bi != 2 && bi % 2 == 0)
			return false;
		}

		// Third, let us check if there are no factors in bi.
		for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(bi) < 1; i = i.add(bi)) { // (equivalent to for (int i = 3; i * i <= bi; i += bi))
			if (ZERO.equals(bi.mod(i))) { // (equivalent to bi % i == 0)
				return false;
			}
		}

		// Finally, we know this is prime.
		return true;
	}

	private static boolean isEven(BigInteger bi) {
		return ZERO.equals(bi.mod(TWO)); // (equivalent to bi % 2 == 0)
	}

	private static LinkedList<BigInteger> getFactors(BigInteger bi) {
		LinkedList<BigInteger> result = new LinkedList<BigInteger>();

		if (bi.compareTo(TWO) < 0) { // (equivalent to bi < 2)
			return result;
		}

		while (ZERO.equals(bi.mod(TWO))) { // (equivalent to bi % 2 == 0)
			result.add(TWO);
			bi = bi.divide(TWO); // (equivalent to bi = bi / 2)
		}

		if (bi.compareTo(ONE) > 0) { // (equivalent to bi > 1)
			BigInteger f = BigInteger.valueOf(3);
			while (f.multiply(f).compareTo(bi) < 1) { // (equivalent to f * f <= bi
				if (ZERO.equals(bi.mod(f))) { // (equivalent to bi % f == 0)
					result.add(f);
					bi = bi.divide(f); // (equivalent to bi = bi / f)
				} else {
					f = f.add(TWO);
				}
			}
			result.add(bi);
		}

		return result;
	}

	private static int getLast2DigitsOfBigInteger(BigInteger bi) {
		return bi == null ? 0 : bi.mod(BigInteger.valueOf(100)).intValue(); // (equivalent to bi == null ? 0 : bi % 100)
	}

	private static BigInteger[] find2SmallerOddFactorsOfOddInteger(BigInteger bi) {
		BigInteger factor1 = ONE;
		BigInteger factor2 = bi;

		for (BigInteger i = TWO; i.compareTo(bi) < 0; i = i.add(ONE)) { // (equivalent to for (int i = 2; i < bi; i++))
			if (bi.mod(i).compareTo(ZERO) == 0) { // (equivalent to bi % i == 0)
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
		return factors.isEmpty() ? null : factors.getLast();
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
		// Get the user's parameters.
		String cityIndex = request.getParameter("city");
		String number = request.getParameter("number");

		String[] errors = { "", "" };
		ServletContext ctx = getServletContext();
		String cities = ctx.getInitParameter("cities");
		String rates = ctx.getInitParameter("tax rates");
		String[] cityArr = cities.split(",");
		String[] rateArr = rates.split(",");
		int idx = -1;

		// We need to check if the user's city parameter is actually an integer.
		try {
			// Try to parse it as an integer.
			idx = Integer.parseInt(cityIndex);
		} catch (Exception e) {
			// If the user's city parameter was not an integer, then we give a helpful error message.
			errors[0] = "Please choose from the given cities.";
		}

		// So, we have that idx is an integer.
		// Also, we need to check if it is a nonnegative integer since negative integers cannot be used to index elements in an array.
		if (idx < 0) {
			errors[0] = "Please select a city.";
		} else {
			// Now, we need to check if the user's city actually exists in our database.
			try {
				// Try to index the element in the array.
				@SuppressWarnings("unused")
				String dummy = cityArr[idx];
			} catch (Exception e) {
				// If we couldn't index the element, meaning it doesn't exist, then we reset the cityIndex and give a helpful error message.
				cityIndex = "-1";
				errors[0] = "Please choose from the given cities.";
			}
		}

		if (number == null || (number.trim().isEmpty())) { // If the user didn't provide anything. (e.g. nothing or just spaces)
			errors[1] = "Please provide an integer.";
		} else if (isNegateInteger(number) || !isNonnegativeInteger(number)) { // (e.g. a negative integer or a string containing a nondigit)
			errors[1] = "Please provide a nonnegative integer.";
		}

		// TODO
		// Expression expr = new Expression(number);
		// System.out.println(expr.checkSyntax());
		// System.out.println(expr.checkLexSyntax());
		// System.out.println(expr.calculate());
		// System.out.println(expr.getErrorMessage());

		// Finally, we check if there are any errors.
		boolean isError = !errors[0].isEmpty() || !errors[1].isEmpty();
		if (isError) {
			request.setAttribute("cities", ctx.getInitParameter("cities").split(","));
			request.setAttribute("cityIndex", cityIndex);
			request.setAttribute("number", number.toString());
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("input").forward(request, response);
			return;
		}

		// Alright, so we made it here, which means there are no errors, and we can convert the user's number to a BigInteger.
		BigInteger bigInteger = new BigInteger(number);
		String result = "";
		int serviceCost = 0;

		if (DEBUG) {
			System.out.println("City: " + cityArr[idx]);
			System.out.println("Number: " + number.toString());
		}

		if (isPrime(bigInteger)) {
			result = "Prime";
			serviceCost = getLast2DigitsOfBigInteger(bigInteger);
		} else {
			if (isEven(bigInteger)) {
				if (bigInteger.compareTo(ZERO) == 0) {
					result = "1 × 0";
				} else {
					BigInteger largestPowerOf2 = getLargestPowerOf2(bigInteger);
					BigInteger remainingOddNumber = multiplyFactors(getOddFactors(bigInteger));

					if (isPrime(remainingOddNumber)) {
						result = largestPowerOf2.toString() + " × " + remainingOddNumber.toString();
					} else {
						BigInteger[] remaining2OddFactors = find2SmallerOddFactorsOfOddInteger(remainingOddNumber);
						result = largestPowerOf2.toString() + " × " + remaining2OddFactors[0].toString() + " × " + remaining2OddFactors[1].toString();
					}
				}

				if (DEBUG) {
					System.out.println("Last 2 digits of largest factor: " + getLast2DigitsOfBigInteger(getLargestFactorFromFactors(getFactors(bigInteger))));
					System.out.println("Prime factors: " + getFactors(bigInteger));
					System.out.println("Largest power of 2: " + getLargestPowerOf2(bigInteger));
					System.out.println("Even factors: " + getEvenFactors(bigInteger));
					System.out.println("Odd factors: " + getOddFactors(bigInteger));
					System.out.println("Largest odd factor: " + getLargestFactorFromFactors(getOddFactors(bigInteger)));
				}
			} else {
				BigInteger[] oddFactors = find2SmallerOddFactorsOfOddInteger(bigInteger);
				result = oddFactors[0].toString() + " × " + oddFactors[1].toString();

				if (DEBUG) {
					System.out.println("factor1: " + oddFactors[0].toString() + " factor2: " + oddFactors[1].toString());
				}
			}

			BigInteger largestFactor = getLargestFactorFromFactors(getFactors(bigInteger));
			int last2DigitsOfLargestFactor = getLast2DigitsOfBigInteger(largestFactor);
			serviceCost = last2DigitsOfLargestFactor;
		}

		// We are assuming that we use a Double to represent all cities' tax rates, so we don't need to check for any configurator errors.
		double invoice = Double.parseDouble(rateArr[idx]) + serviceCost;

		if (DEBUG) {
			System.out.println("Invoice: " + invoice);
		}

		request.setAttribute("city", cityArr[idx]);
		request.setAttribute("number", number);
		request.setAttribute("result", result);
		request.setAttribute("invoice", invoice);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}
