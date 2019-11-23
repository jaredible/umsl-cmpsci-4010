package edu.umsl.java.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SecurityUtil {

	public static String generateMD5Hash(String message) throws Exception {
		return hashString(message, "MD5", getSalt());
	}

	public static String generateSHA1Hash(String message) throws Exception {
		return hashString(message, "SHA-1", getSalt());
	}

	public static String generateSHA256Hash(String message) throws Exception {
		return hashString(message, "SHA-256", getSalt());
	}

	public static String generateSHA512Hash(String message) throws Exception {
		return hashString(message, "SHA-512", getSalt());
	}

	private static byte[] getSalt() throws Exception {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	private static String hashString(String message, String algorithm, byte[] salt) throws Exception {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.update(salt);
			byte[] bytes = digest.digest(message.getBytes());

			StringBuffer hash = new StringBuffer();
			hash.append(convertByteArrayToHexString(salt));
			hash.append(":");
			hash.append(convertByteArrayToHexString(bytes));

			return hash.toString();
		} catch (NoSuchAlgorithmException e) {
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

	public static SecretKey generateSecretKey() throws Exception {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			return keyGenerator.generateKey();
		} catch (Exception e) {
			throw new Exception("Could not generate key!", e);
		}
	}

	public static String encrypt(String data, SecretKey secretKey) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			throw new Exception("Could not encrypt!", e);
		}
	}

	public static String decrypt(String data, SecretKey secretKey) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
		} catch (Exception e) {
			throw new Exception("Could not decrypt!", e);
		}
	}

}
