package main.java.mindbank.util;

public class HashGenerationException extends Exception {

	private static final long serialVersionUID = 5448206985083194697L;

	public HashGenerationException(String message) {
		super(message);
	}

	public HashGenerationException(String message, Exception exception) {
		super(message, exception);
	}

}
