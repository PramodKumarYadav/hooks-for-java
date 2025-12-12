package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Test class for StringUtils. */
class StringUtilsTest {

  @Test
  @DisplayName("Reverse a simple string")
  void testReverse() {
    assertEquals("olleh", StringUtils.reverse("hello"));
  }

  @Test
  @DisplayName("Reverse null string returns null")
  void testReverseNull() {
    assertNull(StringUtils.reverse(null));
  }

  @Test
  @DisplayName("Reverse empty string")
  void testReverseEmpty() {
    assertEquals("", StringUtils.reverse(""));
  }

  @ParameterizedTest
  @ValueSource(strings = {"racecar", "madam", "A man a plan a canal Panama"})
  @DisplayName("Check palindrome returns true for palindromes")
  void testIsPalindromeTrue(String input) {
    assertTrue(StringUtils.isPalindrome(input));
  }

  @ParameterizedTest
  @ValueSource(strings = {"hello", "world", "java"})
  @DisplayName("Check palindrome returns false for non-palindromes")
  void testIsPalindromeFalse(String input) {
    assertFalse(StringUtils.isPalindrome(input));
  }

  @Test
  @DisplayName("Palindrome check returns false for null")
  void testIsPalindromeNull() {
    assertFalse(StringUtils.isPalindrome(null));
  }

  @Test
  @DisplayName("Palindrome check returns false for empty string")
  void testIsPalindromeEmpty() {
    assertFalse(StringUtils.isPalindrome(""));
  }

  @Test
  @DisplayName("Capitalize first letter")
  void testCapitalize() {
    assertEquals("Hello", StringUtils.capitalize("hello"));
  }

  @Test
  @DisplayName("Capitalize null returns null")
  void testCapitalizeNull() {
    assertNull(StringUtils.capitalize(null));
  }

  @Test
  @DisplayName("Capitalize empty string returns empty")
  void testCapitalizeEmpty() {
    assertEquals("", StringUtils.capitalize(""));
  }

  @Test
  @DisplayName("Capitalize already capitalized string")
  void testCapitalizeAlreadyCapitalized() {
    assertEquals("Hello", StringUtils.capitalize("Hello"));
  }
}
