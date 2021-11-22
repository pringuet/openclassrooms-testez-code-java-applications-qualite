package fr.pax.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

	private Calculator calculatorUnderTest;
	private static Instant startedAt;

	@Test
	public void testAddTwoPositiveNumbers() {
		int a = 2, b = 3;
		int somme = calculatorUnderTest.add(a, b);
		assertEquals(5, somme);
	}

	@Test
	public void testMultiplyTwoPositiveNumbers() {
		int a = 42, b = 11;
		int produit = calculatorUnderTest.multiply(a, b);
		assertEquals(462, produit);
	}

	@ParameterizedTest(name = "{0} x 0 == 0 ???")
	@ValueSource(ints = { 1, 54, -8, 99, 1254, 0 })
	public void testMultiplyByZero(int value) {
		int result = calculatorUnderTest.multiply(value, 0);
		assertEquals(0, result);
	}

	@ParameterizedTest(name = "{0} x {1} == {2} ???")
	@CsvSource({ "1,2,2", "-2,3,-6", "-9,-4,36" })
	public void testMultiply(int op1, int op2, int expected) {
		int actual = calculatorUnderTest.multiply(op1, op2);
		assertEquals(expected, actual);
	}
	
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	public void testLongCalculation() {
		calculatorUnderTest.longCalculation();
	}

	@BeforeAll
	public static void initStartingTime() {
		System.out.println("CalculatorTest.initStartingTime()");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration() {
		System.out.println("CalculatorTest.showTestDuration()");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des test : {0} ms", duration));
	}

	@BeforeEach
	public void initCalculator() {
		System.out.println("CalculatorTest.initCalculator()");
		calculatorUnderTest = new Calculator();
	}

	@AfterEach
	public void undefCalculator() {
		System.out.println("CalculatorTest.undefCalculator()");
		calculatorUnderTest = null;
	}
}
