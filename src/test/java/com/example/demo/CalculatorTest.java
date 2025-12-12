package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/** Test class for Calculator. */
class CalculatorTest {

  private Calculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new Calculator();
  }

  @Test
  @DisplayName("Addition of two positive numbers")
  void testAddPositiveNumbers() {
    assertEquals(5, calculator.add(2, 3));
  }

  @Test
  @DisplayName("Addition with negative numbers")
  void testAddNegativeNumbers() {
    assertEquals(-1, calculator.add(-3, 2));
  }

  @Test
  @DisplayName("Subtraction of two numbers")
  void testSubtract() {
    assertEquals(1, calculator.subtract(3, 2));
  }

  @Test
  @DisplayName("Multiplication of two numbers")
  void testMultiply() {
    assertEquals(6, calculator.multiply(2, 3));
  }

  @Test
  @DisplayName("Division of two numbers")
  void testDivide() {
    assertEquals(2, calculator.divide(6, 3));
  }

  @Test
  @DisplayName("Division by zero should throw ArithmeticException")
  void testDivideByZero() {
    assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
  }

  @ParameterizedTest
  @CsvSource({"2,true", "3,false", "0,true", "-4,true", "-5,false"})
  @DisplayName("Check if number is even")
  void testIsEven(int number, boolean expected) {
    assertEquals(expected, calculator.isEven(number));
  }

  @Test
  @DisplayName("Even number check returns true for even numbers")
  void testIsEvenTrue() {
    assertTrue(calculator.isEven(4));
  }

  @Test
  @DisplayName("Even number check returns false for odd numbers")
  void testIsEvenFalse() {
    assertFalse(calculator.isEven(5));
  }
}
