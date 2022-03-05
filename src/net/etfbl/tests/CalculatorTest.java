package net.etfbl.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import net.etfbl.exceptions.DivisionByZeroException;
import net.etfbl.exceptions.NotSupportedOperationException;
import net.etfbl.model.Calculator;

@DisplayName("Test class for Calculator")
public class CalculatorTest {

	private Calculator calculator = new Calculator();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(Double.valueOf(0.0));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculator() {
		assertThat(calculator, is(notNullValue()));
	}

	@ParameterizedTest
	@MethodSource("getParameters")
	void testCalculate(Double currentValue, Double otherOperand, Double result, char operator) throws NotSupportedOperationException, DivisionByZeroException {
		calculator.setCurrentValue(currentValue);
		calculator.calculate(otherOperand, operator);
		assertThat(calculator.getCurrentValue(), is(Double.valueOf(result)));
	}
	
	@ParameterizedTest
	@MethodSource("getParametersExc")
	void testCalculateExceptions(char operator){
		Throwable thrown = assertThrows(NotSupportedOperationException.class, () -> calculator.calculate(Double.valueOf(0), operator));
		assertThat(thrown.getMessage(), is("Operator nije podrzan!"));
	}
	
	@Test
	void testDivisionByZero() {
		Throwable thrown = assertThrows(DivisionByZeroException.class, () -> calculator.calculate(Double.valueOf(0), '/'));
		assertThat(thrown.getMessage(), is("Dijeljenje sa 0!"));
	}
	
	private static List<Character> getParametersExc(){
		return List.of('#', '!', '?', ';');
	}

	private static Stream<Arguments> getParameters(){
		return Stream.of(
				Arguments.of(Double.valueOf(4.0), Double.valueOf(5.0), Double.valueOf(9.0), '+'),
				Arguments.of(Double.valueOf(-4.0), Double.valueOf(5.0), Double.valueOf(1.0), '+'),
				Arguments.of(Double.valueOf(4.0), Double.valueOf(-5.0), Double.valueOf(-1.0), '+'),
				Arguments.of(Double.valueOf(-4.0), Double.valueOf(-5.0), Double.valueOf(-9.0), '+'),
				Arguments.of(Double.valueOf(11.0), Double.valueOf(23.0), Double.valueOf(-12.0), '-'),
				Arguments.of(Double.valueOf(-11.0), Double.valueOf(23.0), Double.valueOf(-34.0), '-'),
				Arguments.of(Double.valueOf(11.0), Double.valueOf(-23.0), Double.valueOf(34.0), '-'),
				Arguments.of(Double.valueOf(-11.0), Double.valueOf(-23.0), Double.valueOf(12.0), '-'),
				Arguments.of(Double.valueOf(4.0), Double.valueOf(5.0), Double.valueOf(20.0), '*'),
				Arguments.of(Double.valueOf(-4.0), Double.valueOf(5.0), Double.valueOf(-20.0), '*'),
				Arguments.of(Double.valueOf(4.0), Double.valueOf(-5.0), Double.valueOf(-20.0), '*'),
				Arguments.of(Double.valueOf(-4.0), Double.valueOf(-5.0), Double.valueOf(20.0), '*'),
				Arguments.of(Double.valueOf(0.0), Double.valueOf(5.0), Double.valueOf(0.0), '*'),
				Arguments.of(Double.valueOf(20.0), Double.valueOf(4.0), Double.valueOf(5.0), '/'),
				Arguments.of(Double.valueOf(-20.0), Double.valueOf(5.0), Double.valueOf(-4.0), '/'),
				Arguments.of(Double.valueOf(4.0), Double.valueOf(20.0), Double.valueOf(0.2), '/'),
				Arguments.of(Double.valueOf(-5.0), Double.valueOf(-20.0), Double.valueOf(0.25), '/'),
				Arguments.of(Double.valueOf(5.0), Double.valueOf(-20.0), Double.valueOf(-0.25), '/')
				);
	}
	
	@Test
	void testGetCurrentValue() {
		assertThat(calculator.getCurrentValue(), is(Double.valueOf(0.0)));
	}

	@Test
	void testSetCurrentValue() {
		calculator.setCurrentValue(Double.valueOf(1.0));
		assertThat(calculator.getCurrentValue(), is(Double.valueOf(1.0)));	
		calculator.setCurrentValue(Double.valueOf(-1.0));
		assertThat(calculator.getCurrentValue(), is(Double.valueOf(-1.0)));
	}

}
