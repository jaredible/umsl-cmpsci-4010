package net.jaredible.mindbank.util;

public class StringUtil {

	public static int[] convertStringToIntArray(String commaDelimitedString) {
		int[] result = null;

		if (commaDelimitedString != null && !commaDelimitedString.isEmpty()) {
			String[] items = commaDelimitedString.split(",");
			int length = items.length;
			result = new int[length];
			for (int i = 0; i < length; i++) {
				result[i] = Integer.valueOf(items[i]);
			}
		}

		return result;
	}

	public static String convertIntArrayToString(int[] array) {
		String result = "";

		if (array != null) {
			int length = array.length;
			for (int i = 0; i < length; i++) {
				result += array[i];
				if (i < length - 1) {
					result += ",";
				}
			}
		}

		return result;
	}

}
