# Usage Examples

This document provides practical examples of using the pre-commit hooks in this repository.

## Example 1: Making a Simple Code Change

Let's add a new method to the `Calculator` class.

### Step 1: Create a feature branch

```bash
git checkout -b feature/add-power-method
```

### Step 2: Add the method

Edit `src/main/java/com/example/demo/Calculator.java`:

```java
/**
 * Calculates power of a number.
 *
 * @param base the base number
 * @param exponent the exponent
 * @return base raised to the power of exponent
 */
public int power(int base, int exponent) {
  int result = 1;
  for (int i = 0; i < exponent; i++) {
    result *= base;
  }
  return result;
}
```

### Step 3: Add tests

Edit `src/test/java/com/example/demo/CalculatorTest.java`:

```java
@Test
@DisplayName("Power calculation")
void testPower() {
  assertEquals(8, calculator.power(2, 3));
  assertEquals(1, calculator.power(5, 0));
  assertEquals(25, calculator.power(5, 2));
}
```

### Step 4: Commit changes

```bash
git add .
git commit -m "Add power method to Calculator"
```

**What happens:**
1. ✅ Spotless checks formatting
2. ✅ Checkstyle validates style
3. ✅ Only `CalculatorTest` runs (not `StringUtilsTest`)
4. ✅ Trailing whitespace is removed
5. ✅ Files end with newline

## Example 2: Fixing Formatting Issues

### Scenario: You write unformatted code

```java
public int sum(int a,int b){
return a+b;
}
```

### Step 1: Try to commit

```bash
git add .
git commit -m "Add sum method"
```

**Output:**
```
[ERROR] The following files had format violations:
    src/main/java/com/example/demo/Calculator.java
Run 'mvn spotless:apply' to fix these violations.
```

### Step 2: Auto-fix

```bash
mvn spotless:apply
```

**Result:**
```java
public int sum(int a, int b) {
  return a + b;
}
```

### Step 3: Commit again

```bash
git add .
git commit -m "Add sum method"
```

✅ **Success!**

## Example 3: Running Specific Hooks

### Run only formatting check

```bash
pre-commit run spotless-check --all-files
```

### Run only Checkstyle

```bash
pre-commit run checkstyle --all-files
```

### Auto-format all files

```bash
pre-commit run spotless-apply --all-files --hook-stage manual
```

### Run all hooks

```bash
pre-commit run --all-files
```

## Example 4: Selective Test Execution

### Scenario: You modify multiple files

```bash
# Modify Calculator.java
vim src/main/java/com/example/demo/Calculator.java

# Modify StringUtils.java
vim src/main/java/com/example/demo/StringUtils.java

# Stage changes
git add .

# Commit
git commit -m "Update Calculator and StringUtils"
```

**What happens:**
- The hook detects both files changed
- Runs `CalculatorTest` (13 tests)
- Runs `StringUtilsTest` (15 tests)
- **Does NOT** run other unrelated tests

This is much faster than running the entire test suite!

## Example 5: Testing the Changed Files Script

You can manually test the script:

```bash
# Test with Calculator.java
.pre-commit-scripts/run-changed-tests.sh src/main/java/com/example/demo/Calculator.java

# Test with a test file
.pre-commit-scripts/run-changed-tests.sh src/test/java/com/example/demo/StringUtilsTest.java

# Test with multiple files
.pre-commit-scripts/run-changed-tests.sh \
  src/main/java/com/example/demo/Calculator.java \
  src/main/java/com/example/demo/StringUtils.java
```

## Example 6: Bypassing Hooks (Emergency Only)

### Skip all hooks

```bash
git commit --no-verify -m "Emergency hotfix"
```

### Skip specific hook

```bash
SKIP=checkstyle git commit -m "Temporary - will fix style later"
```

⚠️ **Warning:** Only use this in emergencies. Skipped checks should be addressed ASAP.

## Example 7: Adding a New Class with Full Workflow

### Step 1: Create the class

`src/main/java/com/example/demo/MathUtils.java`:

```java
package com.example.demo;

/** Utility class for advanced mathematical operations. */
public final class MathUtils {

  private MathUtils() {
    // Utility class
  }

  /**
   * Calculates factorial.
   *
   * @param n the number
   * @return factorial of n
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

### Step 2: Create tests

`src/test/java/com/example/demo/MathUtilsTest.java`:

```java
package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Test class for MathUtils. */
class MathUtilsTest {

  @Test
  @DisplayName("Factorial of 5 should be 120")
  void testFactorial() {
    assertEquals(120, MathUtils.factorial(5));
  }

  @Test
  @DisplayName("Factorial of 0 should be 1")
  void testFactorialZero() {
    assertEquals(1, MathUtils.factorial(0));
  }

  @Test
  @DisplayName("Factorial of negative should throw exception")
  void testFactorialNegative() {
    assertThrows(IllegalArgumentException.class, () -> MathUtils.factorial(-1));
  }
}
```

### Step 3: Format and test locally

```bash
# Format
mvn spotless:apply

# Run tests
mvn test -Dtest=MathUtilsTest
```

### Step 4: Commit

```bash
git add .
git commit -m "Add MathUtils with factorial method"
```

**Result:**
- ✅ Code is formatted
- ✅ Style is checked
- ✅ Only `MathUtilsTest` runs
- ✅ Commit succeeds

## Example 8: Pre-commit Installation in New Clone

### Scenario: A new team member clones the repo

```bash
# Clone
git clone https://github.com/YourOrg/hooks-for-java.git
cd hooks-for-java

# Install pre-commit
pip install pre-commit

# Install hooks
pre-commit install

# Build project
mvn clean install
```

Now they're ready to contribute with all hooks enabled!

## Example 9: Updating Hooks

### Check for hook updates

```bash
pre-commit autoupdate
```

This updates hook versions in `.pre-commit-config.yaml`.

### Review changes

```bash
git diff .pre-commit-config.yaml
```

### Test updated hooks

```bash
pre-commit run --all-files
```

### Commit update

```bash
git add .pre-commit-config.yaml
git commit -m "Update pre-commit hooks to latest versions"
```

## Example 10: Continuous Integration

The same checks can run in CI:

```bash
# In CI pipeline
mvn clean verify

# This runs:
# 1. Checkstyle validation
# 2. Compilation
# 3. All tests
# 4. Spotless formatting check
```

## Common Patterns

### Before Starting Work

```bash
git checkout main
git pull
git checkout -b feature/my-feature
```

### During Development

```bash
# Make changes
# Test locally
mvn test

# Format
mvn spotless:apply

# Commit (hooks run automatically)
git add .
git commit -m "Description"
```

### Before Pull Request

```bash
# Run all checks
mvn clean verify

# Run all hooks
pre-commit run --all-files

# If all pass, push
git push origin feature/my-feature
```

## Tips

1. **Run `mvn spotless:apply` often** to avoid formatting failures
2. **Test incrementally** with `mvn test -Dtest=YourTest`
3. **Use descriptive commit messages** for better history
4. **Don't skip hooks** unless absolutely necessary
5. **Keep tests green** - fix failures immediately

## Troubleshooting Examples

### Problem: Hook fails with "command not found"

**Solution:**
```bash
# Ensure Maven is installed and in PATH
mvn --version

# Reinstall hooks
pre-commit uninstall
pre-commit install
```

### Problem: Tests fail unexpectedly

**Solution:**
```bash
# Clean build
mvn clean test

# Run specific test with verbose output
mvn test -Dtest=FailingTest -X
```

### Problem: Formatting keeps failing

**Solution:**
```bash
# Check what Spotless wants to change
mvn spotless:check

# Apply all fixes
mvn spotless:apply

# Verify
mvn spotless:check
```

## Learning More

- Check [README.md](README.md) for overview
- See [CONTRIBUTING.md](CONTRIBUTING.md) for workflow
- See [QUICK_REFERENCE.md](QUICK_REFERENCE.md) for commands
- Visit [pre-commit.com](https://pre-commit.com/) for documentation
