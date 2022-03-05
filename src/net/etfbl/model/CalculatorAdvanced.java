package net.etfbl.model;

import net.etfbl.exceptions.NotSupportedOperationException;
import net.etfbl.exceptions.NumberNotInAreaException;

/**
 * This program implements application that allows users to make some
 * more advanced calculations such as to find power of the number, factorial or to check whether
 * the number is Armstrong or Perfect number. This class extends Calculator class.
 * @author Luka Divljak
 * @version 1.0
 * @since 28.01.2022.
 */
public class CalculatorAdvanced extends Calculator {
	
	/**
	 * Default constructor explicitly calls parent class constructor.
	 */
	public CalculatorAdvanced() {
		super();
	}

	/**
	 * This method calculates factorial or specified power of the currentValue variable.
	 * @param action This parameter allows user to specify what action to be performed on acumulator variable: power or factorial.
	 * @throws NotSupportedOperationException thrown when power is to large to calculate or undefined operator is specified as action.
	 * @throws NumberNotInAreaException thrown when value of acumulator variable is out of range e.g. when resulting factorial value would be too large.
	 */
	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
		if (action == '!') {
			int fact = 1;
			int number = getCurrentValue().intValue();
			if(number > 0 && number < 10) {
				for(int i = 1; i <= number; i++) 
					fact *= i;
				setCurrentValue(Double.valueOf(fact));
			}
			else throw new NumberNotInAreaException("Broj nije u opsegu!");
		}
		else if(Character.getNumericValue(action) > 0 && Character.getNumericValue(action) <= 9) {
			int result = 1;
			int power = Character.getNumericValue(action);
			int currentValueInt = getCurrentValue().intValue();
			while(power != 0) {
				result = result * currentValueInt;
				power--;
			}
			setCurrentValue(Double.valueOf(result));
		}
		else throw new NotSupportedOperationException("Akcija nije podrzana!");
	}
	
	/**
	 * This method checks whether currentValue has certain characteristic
	 * @param value This parameter represents characteristic that user wants to find out about currentValue variable.
	 * 				A checks whether currentValue is Armstrong number
	 * 				P check whether currentValue is Perfect number.
	 * @return Boolean true if value has certain characteristic or false when value doesn't have it.
	 * @throws NotSupportedOperationException thrown when characteristic is not defined.
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException {
		if(value == 'A') {
			int originalNumber, remainder, result = 0;
			originalNumber = getCurrentValue().intValue();
			while(originalNumber != 0) {
				remainder = originalNumber % 10;
	            result += remainder * remainder * remainder;
	            originalNumber /= 10;
			}
			if(result == getCurrentValue().intValue())
				return true;
		}
		else if(value == 'P') {
			int n, sum = 0;
	        n = getCurrentValue().intValue();
	        if(n == 0) return false;
	        for(int i = 1; i < n; i++)
	        {
	            if(n % i == 0)
	            {
	                sum = sum + i;
	            }
	        }
	        if(sum == n)
	        	return true;
		}
		else throw new NotSupportedOperationException("Karakteristika nije podrzana!");
		return false;
	}
}
