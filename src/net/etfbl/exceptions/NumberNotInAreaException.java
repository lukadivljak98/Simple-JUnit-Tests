package net.etfbl.exceptions;

/**
 * Custom exception that is thrown when the value of currentValue variable is invalid or too large for the factorial to be
 * computed.
 * @author Luka Divljak
 * @version 1.0
 * @since 28.01.2022;
 */
public class NumberNotInAreaException extends Exception {

	/**
	 * Constructor for exception
	 * @param message Message being displayed when exception is thrown.
	 */
	public NumberNotInAreaException(String message) {
		super(message);
	}
}
