package net.etfbl.exceptions;

/**
 * Custom exception that is thrown when user enters invalid parameter.
 * @author Luka Divljak
 * @version 1.0
 * @since 28.01.2022;
 */
public class NotSupportedOperationException extends Exception {
	
	/**
	 * Constructor for exception
	 * @param message Message being displayed when exception is thrown.
	 */
	public NotSupportedOperationException(String message) {
		super(message);
	}
}
