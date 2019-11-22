package main.java.mindbank.util;

public class HashGeneratorException extends Exception {

	private static final long serialVersionUID = 5448206985083194697L;

	public HashGeneratorException(String message) {
		super(message);
	}

	public HashGeneratorException(String message, Exception exception) {
		super(message, exception);
	}

}
