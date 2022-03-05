package net.etfbl.model;

import net.etfbl.exceptions.DivisionByZeroException;
import net.etfbl.exceptions.NotSupportedOperationException;

/**
 * The Calculator program implements application that
 * allows users to make simple calculations with basic
 * mathematical operations: addition, substitution, 
 * multiplication and division.
 * @author Luka Divljak
 * @version 1.0
 * @since 28.01.2022.
 */
public class Calculator {

	/**
	 * This variable of type Double represents acumulator which gets
	 * updated after every operation and stores the 
	 * resulting value.
	 */
	private Double currentValue;
	
	/**
	 * Default constructor: when calculator object
	 * is created, 0 is set as initial value of currentValue variable.
	 */
	public Calculator() {
		this.currentValue = Double.valueOf(0);
	}

	/**
	 * This method is used to perform one of the basic mathematical operations specified by the user.
	 * @param value This is the value of second operand
	 * @param operator Specifies basic mathematical operation performed
	 * @throws NotSupportedOperationException thrown when operator is not one of the following: +, -, * or /.
	 * @throws DivisionByZeroException thrown when user tries to perform division by 0.
	 */
	public void calculate(Double value, char operator) throws NotSupportedOperationException, DivisionByZeroException {
		if(operator == '+') {
			this.currentValue += value;
		}
		else if(operator == '-') {
			this.currentValue -= value;
		}
		else if(operator == '*') {
			this.currentValue *= value;
		}
		else if(operator == '/') {
			if(value == 0)
				throw new DivisionByZeroException("Dijeljenje sa 0!");
			this.currentValue /= value;
		}
		else
			throw new NotSupportedOperationException("Operator nije podrzan!");
	}
	
	/**
	 * Getter method
	 * @return Double
	 */
	public Double getCurrentValue() {
		return currentValue;
	}

	/**
	 * Setter method
	 * @param currentValue Value that is set
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	
}
