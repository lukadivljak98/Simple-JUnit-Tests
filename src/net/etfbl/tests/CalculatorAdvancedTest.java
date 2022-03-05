package net.etfbl.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import net.etfbl.exceptions.NotSupportedOperationException;
import net.etfbl.exceptions.NumberNotInAreaException;
import net.etfbl.model.CalculatorAdvanced;

@DisplayName("Test class for Advanced Calculator")
class CalculatorAdvancedTest {

	private CalculatorAdvanced calculatorAdvanced = new CalculatorAdvanced();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculatorAdvanced.setCurrentValue(Double.valueOf(0.0));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculatorAdvanced() {
		assertThat(calculatorAdvanced, is(notNullValue()));
	}

	@ParameterizedTest
	@MethodSource("getParameters")
	void testClalculateAdvanced(Double currentValue, char action, int result) throws NotSupportedOperationException, NumberNotInAreaException {
		calculatorAdvanced.setCurrentValue(currentValue);
		calculatorAdvanced.calculateAdvanced(action);
		assertThat(calculatorAdvanced.getCurrentValue(), is(Double.valueOf(result)));
	}
	
	public static Stream<Arguments> getParameters(){
		return Stream.of(
				Arguments.of(Double.valueOf(2.0), '2', 4),
				Arguments.of(Double.valueOf(15.0), '3', 3375),
				Arguments.of(Double.valueOf(11.0), '7', 19_487_171),
				Arguments.of(Double.valueOf(17.0), '1', 17),
				Arguments.of(Double.valueOf(1.0), '9', 1),
				Arguments.of(Double.valueOf(3.0), '!', 6),
				Arguments.of(Double.valueOf(7.0), '!', 5040),
				Arguments.of(Double.valueOf(9.0), '!', 362_880)
				);
	}

	@ParameterizedTest
	@MethodSource("getParams")
	void testHasCharacteristic(Double currentValue, char c, boolean test) throws NotSupportedOperationException {
		calculatorAdvanced.setCurrentValue(currentValue);
		Boolean result = calculatorAdvanced.hasCharacteristic(c);
		assertThat(result, is(Boolean.valueOf(test)));
	}
	
	@ParameterizedTest
	@MethodSource("getParametersExc")
	void testNotSupportedOperation(char action){
		Throwable thrown = assertThrows(NotSupportedOperationException.class, () -> calculatorAdvanced.calculateAdvanced(action));
		assertThat(thrown.getMessage(), is("Akcija nije podrzana!"));
	}
	
	private static List<Character> getParametersExc(){
		return List.of('?', '*', 'L', 'B', (char)-1, '0', (char)11);
	}
	
	@ParameterizedTest
	@MethodSource("getParametersExc")
	void testNotSupportedOperationChar(char action){
		Throwable thrown = assertThrows(NotSupportedOperationException.class, () -> calculatorAdvanced.hasCharacteristic(action));
		assertThat(thrown.getMessage(), is("Karakteristika nije podrzana!"));
	}

	@ParameterizedTest
	@MethodSource("getParamsArea")
	void testNotInArea(Double value, char action){
		calculatorAdvanced.setCurrentValue(value);
		Throwable thrown = assertThrows(NumberNotInAreaException.class, () -> calculatorAdvanced.calculateAdvanced(action));
		assertThat(thrown.getMessage(), is("Broj nije u opsegu!"));
	}
	
	public static Stream<Arguments> getParamsArea(){
		return Stream.of(
				Arguments.of(Double.valueOf(10), '!'),
				Arguments.of(Double.valueOf(-1), '!'),
				Arguments.of(Double.valueOf(0), '!')
				);
	}
	
	public static Stream<Arguments> getParams(){
		return Stream.of(
				Arguments.of(Double.valueOf(34), 'A', false),
				Arguments.of(Double.valueOf(143), 'P', false),
				Arguments.of(Double.valueOf(153), 'A', true),
				Arguments.of(Double.valueOf(496), 'P', true),
				Arguments.of(Double.valueOf(0), 'A', true),
				Arguments.of(Double.valueOf(0), 'P', false)
				);
	}

}
