# hooks-for-java

A template repository for working with pre-commit hooks for Java projects. This repository demonstrates how to set up automated code quality checks using pre-commit hooks, Maven Spotless for formatting, Checkstyle for style enforcement, and intelligent test execution similar to lint-staged in Node.js.

## Features

- ✅ **Pre-commit hooks** using [pre-commit](https://pre-commit.com/)
- ✅ **Code formatting** with Maven Spotless (Google Java Format)
- ✅ **Style checking** with Checkstyle
- ✅ **Intelligent test execution** - runs only tests for changed files (like lint-staged)
- ✅ **Java 17** with latest Maven
- ✅ **JUnit 5** for testing with sample tests

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Python 3.7 or higher (for pre-commit)

## Quick Start

1. **Clone this repository** (or use it as a template):
   ```bash
   git clone <your-repo-url>
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

## Usage

### Running Hooks Manually

You can run all pre-commit hooks manually without committing:

```bash
# Run all hooks on all files
pre-commit run --all-files

# Run all hooks on staged files
pre-commit run
```

### Code Formatting (Spotless)

The project uses Maven Spotless with Google Java Format for consistent code formatting.

```bash
# Check if code is formatted correctly
mvn spotless:check

# Automatically format code
mvn spotless:apply

# Run via pre-commit (manual stage)
pre-commit run spotless-apply --all-files --hook-stage manual
```

### Style Checking (Checkstyle)

Checkstyle enforces coding standards based on the Google Java Style Guide.

```bash
# Run Checkstyle
mvn checkstyle:check

# Checkstyle runs automatically during pre-commit
```

### Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CalculatorTest

# Run tests for changed files only (via pre-commit)
pre-commit run test-changed --all-files
```

## Pre-commit Hooks

This repository includes the following pre-commit hooks:

### Standard Hooks
- **trailing-whitespace**: Removes trailing whitespace
- **end-of-file-fixer**: Ensures files end with a newline
- **check-yaml**: Validates YAML files
- **check-added-large-files**: Prevents large files from being committed
- **check-merge-conflict**: Detects merge conflict markers
- **mixed-line-ending**: Prevents mixed line endings

### Java-Specific Hooks
- **spotless-check**: Verifies code formatting (runs automatically on commit)
- **spotless-apply**: Auto-formats code (manual, run with `--hook-stage manual`)
- **checkstyle**: Enforces coding standards (runs automatically on commit)
- **test-changed**: Runs tests only for changed files (runs automatically on commit)
- **test-all**: Runs all tests (manual, run with `--hook-stage manual`)

## How It Works

### Lint-Staged Behavior

This setup mimics the behavior of [lint-staged](https://www.npmjs.com/package/lint-staged) from the Node.js ecosystem:

1. When you commit Java files, pre-commit automatically detects which files changed
2. For source files in `src/main/java`, it finds and runs corresponding test files
3. For test files in `src/test/java`, it runs those specific tests
4. This makes commits faster by only running relevant tests

The logic is implemented in `.pre-commit-scripts/run-changed-tests.sh`.

### Code Quality Pipeline

When you commit code, the following happens automatically:

1. **Formatting Check**: Spotless verifies code is formatted correctly
2. **Style Check**: Checkstyle enforces coding standards
3. **Targeted Testing**: Only tests related to changed files are executed

If any check fails, the commit is rejected, and you'll see detailed error messages.

## Project Structure

```
hooks-for-java/
├── .pre-commit-config.yaml          # Pre-commit configuration
├── .pre-commit-scripts/
│   └── run-changed-tests.sh         # Script for running tests on changed files
├── checkstyle.xml                   # Checkstyle configuration
├── pom.xml                          # Maven configuration
├── src/
│   ├── main/java/com/example/demo/
│   │   ├── Calculator.java          # Sample class
│   │   └── StringUtils.java         # Sample utility class
│   └── test/java/com/example/demo/
│       ├── CalculatorTest.java      # Sample tests
│       └── StringUtilsTest.java     # Sample tests
└── README.md
```

## Configuration Files

### pom.xml
Contains:
- Java 17 configuration
- JUnit 5 dependencies
- Maven Spotless plugin with Google Java Format
- Checkstyle plugin with custom configuration
- Surefire plugin for test execution

### checkstyle.xml
Based on the Google Java Style Guide with:
- Naming conventions
- Import rules
- Whitespace and formatting rules
- Code structure checks

### .pre-commit-config.yaml
Defines all pre-commit hooks and their behavior.

## Customization

### Changing Code Format Style

Edit `pom.xml` and modify the Spotless configuration:

```xml
<googleJavaFormat>
    <version>1.19.1</version>
    <style>GOOGLE</style>  <!-- or AOSP -->
</googleJavaFormat>
```

### Modifying Checkstyle Rules

Edit `checkstyle.xml` to add or modify rules according to your project needs.

### Adjusting Hook Behavior

Edit `.pre-commit-config.yaml` to:
- Enable/disable specific hooks
- Change when hooks run (commit vs. push vs. manual)
- Add new custom hooks

## Skip Hooks (Not Recommended)

In rare cases, you can skip hooks:

```bash
# Skip all hooks
git commit --no-verify

# Skip specific hook
SKIP=checkstyle git commit -m "your message"
```

**Note**: Skipping hooks is generally not recommended as it bypasses quality checks.

## Troubleshooting

### Pre-commit not running
```bash
# Reinstall hooks
pre-commit uninstall
pre-commit install
```

### Spotless failures
```bash
# Auto-fix formatting issues
mvn spotless:apply
```

### Checkstyle failures
Review the console output for specific violations and fix them manually.

### Test failures
```bash
# Run tests manually to see detailed output
mvn test

# Run specific failing test
mvn test -Dtest=YourTestClass
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Ensure all pre-commit hooks pass
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Resources

- [Pre-commit](https://pre-commit.com/)
- [Maven Spotless](https://github.com/diffplug/spotless)
- [Checkstyle](https://checkstyle.org/)
- [Google Java Format](https://github.com/google/google-java-format)
- [JUnit 5](https://junit.org/junit5/)
- [lint-staged](https://www.npmjs.com/package/lint-staged) (Node.js inspiration)

## Example Workflow

```bash
# Make changes to Java files
vim src/main/java/com/example/demo/Calculator.java

# Stage your changes
git add .

# Commit (hooks run automatically)
git commit -m "Add new calculator method"

# If formatting is wrong, fix it
mvn spotless:apply

# Try commit again
git commit -m "Add new calculator method"
```

The pre-commit hooks will:
1. Check code formatting with Spotless
2. Verify style with Checkstyle
3. Run tests for Calculator.java (CalculatorTest.java)
4. Only allow commit if all checks pass

## Template Usage

This is a template repository. To use it for your own project:

1. Click "Use this template" on GitHub
2. Clone your new repository
3. Update package names in `pom.xml` and Java files
4. Add your own source and test files
5. Customize `checkstyle.xml` if needed
6. Start developing with pre-commit hooks already configured!

