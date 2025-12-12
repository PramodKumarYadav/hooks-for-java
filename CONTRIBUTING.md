# Contributing to hooks-for-java

Thank you for your interest in contributing! This guide will help you set up your development environment and understand our workflow.

## Prerequisites

Before you begin, ensure you have:

- Java 17 or higher
- Maven 3.6 or higher
- Python 3.7 or higher (for pre-commit)
- Git

## Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/PramodKumarYadav/hooks-for-java.git
   cd hooks-for-java
   ```

2. **Install pre-commit**:
   ```bash
   pip install pre-commit
   ```

3. **Install the git hooks**:
   ```bash
   pre-commit install
   ```

4. **Build the project**:
   ```bash
   mvn clean install
   ```

## Development Workflow

### Making Changes

1. Create a new branch for your feature or fix:
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. Make your changes to the code

3. Format your code (optional, will be done automatically):
   ```bash
   mvn spotless:apply
   ```

4. Run tests:
   ```bash
   mvn test
   ```

5. Stage and commit your changes:
   ```bash
   git add .
   git commit -m "Your descriptive commit message"
   ```

When you commit, the pre-commit hooks will automatically:
- ✅ Check code formatting with Spotless
- ✅ Verify style with Checkstyle
- ✅ Run tests for changed files only
- ✅ Check for trailing whitespace
- ✅ Ensure files end with a newline
- ✅ Validate YAML files
- ✅ Check for large files

### If Hooks Fail

#### Spotless Formatting Issues

If Spotless finds formatting issues:

```bash
# The error message will show which files need formatting
# Run this to auto-fix:
mvn spotless:apply

# Then try committing again:
git add .
git commit -m "Your message"
```

#### Checkstyle Violations

If Checkstyle reports violations:

1. Review the console output for specific violations
2. Fix the issues manually (e.g., add missing Javadoc, fix naming conventions)
3. Stage and commit again

#### Test Failures

If tests fail for changed files:

```bash
# Run the specific failing test to see detailed output
mvn test -Dtest=YourTestClass

# Fix the test or code
# Then commit again
```

## Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Test

```bash
mvn test -Dtest=CalculatorTest
```

### Run Tests for Changed Files (via pre-commit)

```bash
# This mimics what happens during commit
.pre-commit-scripts/run-changed-tests.sh src/main/java/com/example/demo/Calculator.java
```

## Code Quality

### Format Code

```bash
# Check formatting
mvn spotless:check

# Apply formatting
mvn spotless:apply
```

### Check Style

```bash
mvn checkstyle:check
```

### Run All Quality Checks

```bash
mvn clean verify
```

This runs:
1. Compilation
2. Checkstyle validation
3. All tests
4. Spotless formatting check

## Pre-commit Commands

### Run All Hooks on All Files

```bash
pre-commit run --all-files
```

### Run Specific Hook

```bash
# Run only Spotless check
pre-commit run spotless-check --all-files

# Run only Checkstyle
pre-commit run checkstyle --all-files

# Run only tests for changed files
pre-commit run test-changed --all-files
```

### Run Manual Hooks

Some hooks are configured to run only manually:

```bash
# Auto-format code
pre-commit run spotless-apply --all-files --hook-stage manual

# Run all tests
pre-commit run test-all --all-files --hook-stage manual
```

### Skip Hooks (Emergency Only)

In rare cases where you need to bypass hooks:

```bash
# Skip all hooks (not recommended)
git commit --no-verify -m "Emergency fix"

# Skip specific hook
SKIP=checkstyle git commit -m "Your message"
```

⚠️ **Warning**: Skipping hooks should be avoided as it bypasses quality checks.

## Coding Standards

### Java Style

- Follow Google Java Style Guide
- Use 2 spaces for indentation
- Maximum line length: 120 characters
- Use meaningful variable and method names
- Add Javadoc for all public classes and methods

### Example

```java
package com.example.demo;

/** A utility class for mathematical operations. */
public final class MathUtils {

  private MathUtils() {
    // Utility class - prevent instantiation
  }

  /**
   * Calculates the factorial of a number.
   *
   * @param n the number to calculate factorial for
   * @return the factorial of n
   * @throws IllegalArgumentException if n is negative
   */
  public static long factorial(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Number must be non-negative");
    }
    if (n == 0 || n == 1) {
      return 1;
    }
    long result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }
}
```

### Test Style

- Use JUnit 5
- Use descriptive test names
- Use `@DisplayName` for better test output
- Use parameterized tests when applicable
- Follow Arrange-Act-Assert pattern

### Example

```java
package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/** Test class for MathUtils. */
class MathUtilsTest {

  @Test
  @DisplayName("Factorial of 0 should be 1")
  void testFactorialZero() {
    assertEquals(1, MathUtils.factorial(0));
  }

  @ParameterizedTest
  @CsvSource({"1,1", "2,2", "3,6", "4,24", "5,120"})
  @DisplayName("Factorial calculation")
  void testFactorial(int n, long expected) {
    assertEquals(expected, MathUtils.factorial(n));
  }

  @Test
  @DisplayName("Factorial of negative number should throw exception")
  void testFactorialNegative() {
    assertThrows(IllegalArgumentException.class, () -> MathUtils.factorial(-1));
  }
}
```

## Pull Request Process

1. Ensure all pre-commit hooks pass locally
2. Push your branch to GitHub
3. Create a Pull Request with a clear description
4. Wait for CI checks to pass
5. Address any review comments
6. Once approved, your PR will be merged

## Troubleshooting

### Pre-commit not installed

```bash
pip install pre-commit
pre-commit install
```

### Hooks not running

```bash
# Reinstall hooks
pre-commit uninstall
pre-commit install

# Verify installation
pre-commit --version
```

### Maven build failures

```bash
# Clean and rebuild
mvn clean install

# Skip tests if needed (for dependency issues)
mvn clean install -DskipTests
```

### Java version issues

```bash
# Check Java version
java -version

# Should be Java 17 or higher
```

## Getting Help

If you encounter any issues:

1. Check the [README.md](README.md) for basic setup
2. Review existing [issues](https://github.com/PramodKumarYadav/hooks-for-java/issues)
3. Create a new issue with detailed information

## License

By contributing, you agree that your contributions will be licensed under the MIT License.
