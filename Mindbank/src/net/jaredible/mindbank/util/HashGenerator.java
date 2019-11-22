package net.jaredible.mindbank.util;

import java.security.MessageDigest;

public class HashGenerator {

	public static String generateMD5(String message) throws HashGeneratorException {
		return hashString(message, "MD5");
	}

	public static String generateSHA1(String message) throws HashGeneratorException {
		return hashString(message, "SHA-1");
	}

	public static String generateSHA256(String message) throws HashGeneratorException {
		return hashString(message, "SHA-256");
	}

	private static String hashString(String message, String algorithm) throws HashGeneratorException {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

			return convertByteArrayToHexString(hashedBytes);
		} catch (Exception e) {
			throw new HashGeneratorException("Could not generate hash using " + algorithm + "!", e);
		}
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < arrayBytes.length; i++) {
			result.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return result.toString();
	}
}
