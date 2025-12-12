#!/bin/bash
#
# Script to run tests only for changed Java files
# This mimics the behavior of lint-staged for Java/Maven projects
#

set -e

# Get all changed Java files passed as arguments
CHANGED_FILES=("$@")

if [ ${#CHANGED_FILES[@]} -eq 0 ]; then
  echo "No Java files changed, skipping tests"
  exit 0
fi

echo "Changed Java files detected:"
printf '%s\n' "${CHANGED_FILES[@]}"

# Extract test classes from changed files
TEST_CLASSES=()

for file in "${CHANGED_FILES[@]}"; do
  # Check if file is a test file (in src/test/java directory)
  if [[ $file == *"src/test/java"* ]]; then
    # Convert file path to test class name
    # Example: src/test/java/com/example/demo/CalculatorTest.java -> com.example.demo.CalculatorTest
    TEST_CLASS=$(echo "$file" | sed 's|src/test/java/||' | sed 's|/|.|g' | sed 's|\.java$||')
    TEST_CLASSES+=("$TEST_CLASS")
  fi
  
  # Check if file is a source file - find corresponding test
  if [[ $file == *"src/main/java"* ]]; then
    # Convert source file to potential test class name
    # Example: src/main/java/com/example/demo/Calculator.java -> com.example.demo.CalculatorTest
    BASE_NAME=$(echo "$file" | sed 's|src/main/java/||' | sed 's|/|.|g' | sed 's|\.java$||')
    POTENTIAL_TEST="${BASE_NAME}Test"
    
    # Check if test file exists
    TEST_FILE_PATH=$(echo "$file" | sed 's|src/main/java|src/test/java|' | sed 's|\.java$|Test.java|')
    if [ -f "$TEST_FILE_PATH" ]; then
      TEST_CLASSES+=("$POTENTIAL_TEST")
    fi
  fi
done

# Remove duplicates
TEST_CLASSES=($(printf '%s\n' "${TEST_CLASSES[@]}" | sort -u))

if [ ${#TEST_CLASSES[@]} -eq 0 ]; then
  echo "No test classes found for changed files"
  echo "This could mean:"
  echo "  - Changed files have no corresponding tests"
  echo "  - Only non-test Java files were changed without tests"
  exit 0
fi

echo ""
echo "Running tests for the following classes:"
printf '%s\n' "${TEST_CLASSES[@]}"
echo ""

# Build the Maven command to run specific tests
TEST_PATTERN=$(IFS=,; echo "${TEST_CLASSES[*]}")

# Run the tests
mvn test -Dtest="$TEST_PATTERN"

echo ""
echo "Tests completed successfully!"
