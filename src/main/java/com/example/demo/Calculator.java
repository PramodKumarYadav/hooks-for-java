package com.example.demo;

/** A simple calculator class to demonstrate pre-commit hooks. */
public class Calculator {

  /**
   * Adds two integers.
   *
   * @param a first number
   * @param b second number
   * @return sum of a and b
   */
  public int add(int a, int b) {
    return a + b;
  }

  /**
   * Subtracts second integer from first.
   *
   * @param a first number
   * @param b second number
   * @return difference of a and b
   */
  public int subtract(int a, int b) {
    return a - b;
  }

  /**
   * Multiplies two integers.
   *
   * @param a first number
   * @param b second number
   * @return product of a and b
   */
  public int multiply(int a, int b) {
    return a * b;
  }

  /**
   * Divides first integer by second.
   *
   * @param a dividend
   * @param b divisor
   * @return quotient of a divided by b
   * @throws ArithmeticException if b is zero
   */
  public int divide(int a, int b) {
    if (b == 0) {
      throw new ArithmeticException("Cannot divide by zero");
    }
    return a / b;
  }

  /**
   * Checks if a number is even.
   *
   * @param number the number to check
   * @return true if even, false otherwise
   */
  public boolean isEven(int number) {
    return number % 2 == 0;
  }
}
