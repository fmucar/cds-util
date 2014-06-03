package com.cooldatasoft.util;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Test;

/**
 * 
 * @author Fatih Mehmet UCAR - fmucar@cooldatasoft.com
 * 
 */
public class RandomStringTest {

	private final static String NUMERIC = "0123456789";
	private final static String UPPERCASE = "QWERTYUIOPASDFGHJKLZXCVBNM";
	private final static String LOWERCASE = "qwertyuiopasdfghjklzxcvbnm";

	@Test
	public void shouldNotHavePublicConstructor() {
		Constructor<?>[] declaredConstructors = RandomString.class.getDeclaredConstructors();

		for (Constructor<?> constructor : declaredConstructors) {
			assertFalse(constructor.isAccessible());
		}
	}

	@Test
	public void shouldGenerateCorrectRandomStringUsingSpecialCharsExpression() {
		int samplingSize = 100;

		for (int i = 0; i < samplingSize; i++) {
			String generatedString = RandomString.getString("#");
			assertTrue(NUMERIC.indexOf(generatedString) >= 0);
		}

		for (int i = 0; i < samplingSize; i++) {
			String generatedString = RandomString.getString("*");
			assertTrue(NUMERIC.indexOf(generatedString) >= 0 | UPPERCASE.indexOf(generatedString) >= 0 | LOWERCASE.indexOf(generatedString) >= 0);
		}

		for (int i = 0; i < samplingSize; i++) {
			String generatedString = RandomString.getString(">");
			assertTrue(UPPERCASE.indexOf(generatedString) >= 0);
		}

		for (int i = 0; i < samplingSize; i++) {
			String generatedString = RandomString.getString("<");
			assertTrue(LOWERCASE.indexOf(generatedString) >= 0);
		}

		for (int i = 0; i < samplingSize; i++) {
			String generatedString = RandomString.getString("$");
			assertTrue(UPPERCASE.indexOf(generatedString) >= 0 | LOWERCASE.indexOf(generatedString) >= 0);
		}

		for (int i = 0; i < samplingSize; i++) {
			String generatedString = RandomString.getString("]");
			assertTrue(NUMERIC.indexOf(generatedString) >= 0 | LOWERCASE.indexOf(generatedString) >= 0);
		}

		for (int i = 0; i < samplingSize; i++) {
			String generatedString = RandomString.getString("[");
			assertTrue(NUMERIC.indexOf(generatedString) >= 0 | UPPERCASE.indexOf(generatedString) >= 0);
		}
	}

	@Test
	public void shouldGenerateCorrectRandomStringUsingSpecialChars() {
		int samplingSize = 100;

		String expression = "#*><$][";
		for (int i = 0; i < samplingSize; i++) {
			String generatedString = RandomString.getString(expression);

			char ch = generatedString.charAt(expression.indexOf('#'));
			assertTrue(NUMERIC.indexOf(ch) >= 0);

			ch = generatedString.charAt(expression.indexOf('*'));
			assertTrue(NUMERIC.indexOf(ch) >= 0 | UPPERCASE.indexOf(ch) >= 0 | LOWERCASE.indexOf(ch) >= 0);

			ch = generatedString.charAt(expression.indexOf('>'));
			assertTrue(UPPERCASE.indexOf(ch) >= 0);

			ch = generatedString.charAt(expression.indexOf('<'));
			assertTrue(LOWERCASE.indexOf(ch) >= 0);

			ch = generatedString.charAt(expression.indexOf('$'));
			assertTrue(LOWERCASE.indexOf(ch) >= 0 || UPPERCASE.indexOf(ch) >= 0);

			ch = generatedString.charAt(expression.indexOf(']'));
			assertTrue(LOWERCASE.indexOf(ch) >= 0 || NUMERIC.indexOf(ch) >= 0);

			ch = generatedString.charAt(expression.indexOf('['));
			assertTrue(UPPERCASE.indexOf(ch) >= 0 || NUMERIC.indexOf(ch) >= 0);
		}
	}

	@Test
	public void shouldGenerateRandomStringWithGivenLengthAndNumericValuesOnly() {
		int stringLength = 10;

		String result = RandomString.getRandomString(true, false, false, stringLength);
		assertEquals(stringLength, result.length());
		for (int i = 0; i < stringLength; i++) {
			assertTrue(NUMERIC.indexOf(result.charAt(i)) >= 0);
		}
	}

	@Test
	public void shouldGenerateRandomStringWithGivenLengthAndLowercaseValuesOnly() {
		int stringLength = 10;

		String result = RandomString.getRandomString(false, true, false, stringLength);
		assertEquals(stringLength, result.length());
		for (int i = 0; i < stringLength; i++) {
			assertTrue(LOWERCASE.indexOf(result.charAt(i)) >= 0);
		}
	}

	@Test
	public void shouldGenerateRandomStringWithGivenLengthAndUppercaseValuesOnly() {
		int stringLength = 10;

		String result = RandomString.getRandomString(false, false, true, stringLength);
		assertEquals(stringLength, result.length());
		for (int i = 0; i < stringLength; i++) {
			assertTrue(UPPERCASE.indexOf(result.charAt(i)) >= 0);
		}
	}

	@Test
	public void shouldThrowExceptionIfAllParametersAreFalse() {
		int stringLength = 10;
		try {
			RandomString.getRandomString(false, false, false, stringLength);
			fail("Shown have thrown exception!");
		} catch (Exception e) {
			assertEquals("Zero length string cannot be generated!", e.getMessage());
		}
	}

	@Test
	public void shouldGenerateRandomStringWithGivenLengthAndDifferentCombinationOfParameters() {
		int stringLength = 10;

		String result = RandomString.getRandomString(true, true, true, stringLength);
		assertEquals(stringLength, result.length());
		for (int i = 0; i < stringLength; i++) {
			assertTrue(NUMERIC.indexOf(result.charAt(i)) >= 0 | LOWERCASE.indexOf(result.charAt(i)) >= 0 | UPPERCASE.indexOf(result.charAt(i)) >= 0);
		}

		result = RandomString.getRandomString(true, true, false, stringLength);
		assertEquals(stringLength, result.length());
		for (int i = 0; i < stringLength; i++) {
			assertTrue(NUMERIC.indexOf(result.charAt(i)) >= 0 | LOWERCASE.indexOf(result.charAt(i)) >= 0);
		}

		result = RandomString.getRandomString(true, false, true, stringLength);
		assertEquals(stringLength, result.length());
		for (int i = 0; i < stringLength; i++) {
			assertTrue(NUMERIC.indexOf(result.charAt(i)) >= 0 | UPPERCASE.indexOf(result.charAt(i)) >= 0);
		}

		result = RandomString.getRandomString(false, true, true, stringLength);
		assertEquals(stringLength, result.length());
		for (int i = 0; i < stringLength; i++) {
			assertTrue(LOWERCASE.indexOf(result.charAt(i)) >= 0 | UPPERCASE.indexOf(result.charAt(i)) >= 0);
		}
	}

	@Test
	public void shouldThrowExceptionWhenGenerateCharWithAllParametersFalse() {
		try {
			RandomString.getRandomChar(false, false, false);
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertEquals("Zero length char cannot be generated!", e.getMessage());
		}
	}

	@Test
	public void shouldGenerateRandomCharWithDifferentCombinationOfParameters() {

		char ch = RandomString.getRandomChar(false, false, true);
		assertTrue(UPPERCASE.indexOf(ch) >= 0);

		ch = RandomString.getRandomChar(false, true, false);
		assertTrue(LOWERCASE.indexOf(ch) >= 0);

		ch = RandomString.getRandomChar(false, true, true);
		assertTrue(UPPERCASE.indexOf(ch) >= 0 || LOWERCASE.indexOf(ch) >= 0);

		ch = RandomString.getRandomChar(true, false, false);
		assertTrue(NUMERIC.indexOf(ch) >= 0);

		ch = RandomString.getRandomChar(true, false, true);
		assertTrue(NUMERIC.indexOf(ch) >= 0 || UPPERCASE.indexOf(ch) >= 0);

		ch = RandomString.getRandomChar(true, true, false);
		assertTrue(NUMERIC.indexOf(ch) >= 0 || LOWERCASE.indexOf(ch) >= 0);

		ch = RandomString.getRandomChar(true, true, true);
		assertTrue(NUMERIC.indexOf(ch) >= 0 || LOWERCASE.indexOf(ch) >= 0 || UPPERCASE.indexOf(ch) >= 0);
	}
}
