package net.jaredible.mindbank.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class SecurityUtil {

	public static String generateMD5Hash(String data, String salt) {
		return hashString(data, "MD5", salt);
	}

	public static String generateSHA1Hash(String data, String salt) {
		return hashString(data, "SHA-1", salt);
	}

	public static String generateSHA256Hash(String data, String salt) {
		return hashString(data, "SHA-256", salt);
	}

	public static String generateSHA512Hash(String data, String salt) {
		return hashString(data, "SHA-512", salt);
	}

	public static String generateRandomSalt() {
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return new String(salt);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String hashString(String data, String algorithm, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(salt.getBytes());
			return convertByteArrayToHexString(md.digest(data.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arrayBytes.length; i++) {
			sb.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

}
