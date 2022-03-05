package net.etfbl.exceptions;

/**
 * Custom exception that is thrown when user tries to perform division by zero.
 * @author Luka Divljak
 * @version 1.0
 * @since 18.01.2022;
 */
public class DivisionByZeroException extends Exception{

	/**
	 * Constructor for exception
	 * @param message Message being displayed when exception is thrown.
	 */
	public DivisionByZeroException(String message) {
		super(message);
	}
}
