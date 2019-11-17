package edu.umsl.java.util;

public class HashGeneratorException extends Exception {
	private static final long serialVersionUID = 1L;

	public HashGeneratorException(String message) {
		super(message);
	}

	public HashGeneratorException(String message, Exception exception) {
		super(message, exception);
	}

}
