package com.example.demo;

/** A utility class for string operations. */
public final class StringUtils {

  private StringUtils() {
    // Utility class - prevent instantiation
  }

  /**
   * Reverses a string.
   *
   * @param input the string to reverse
   * @return reversed string, or null if input is null
   */
  public static String reverse(String input) {
    if (input == null) {
      return null;
    }
    return new StringBuilder(input).reverse().toString();
  }

  /**
   * Checks if a string is a palindrome.
   *
   * @param input the string to check
   * @return true if palindrome, false otherwise
   */
  public static boolean isPalindrome(String input) {
    if (input == null || input.isEmpty()) {
      return false;
    }
    String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
    return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
  }

  /**
   * Capitalizes the first letter of a string.
   *
   * @param input the string to capitalize
   * @return capitalized string, or null if input is null
   */
  public static String capitalize(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }
    return input.substring(0, 1).toUpperCase() + input.substring(1);
  }
}
