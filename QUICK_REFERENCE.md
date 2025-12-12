# Quick Reference Guide

## Quick Start

```bash
# Install pre-commit
pip install pre-commit

# Install hooks
pre-commit install

# Build project
mvn clean install

# Run all hooks manually
pre-commit run --all-files
```

## Common Commands

### Maven

```bash
# Compile
mvn compile

# Run tests
mvn test

# Format code
mvn spotless:apply

# Check formatting
mvn spotless:check

# Check style
mvn checkstyle:check

# Full build with all checks
mvn clean verify

# Run specific test
mvn test -Dtest=CalculatorTest
```

### Pre-commit

```bash
# Run all hooks on staged files
pre-commit run

# Run all hooks on all files
pre-commit run --all-files

# Run specific hook
pre-commit run spotless-check --all-files
pre-commit run checkstyle --all-files
pre-commit run test-changed --all-files

# Manual hooks (don't run automatically)
pre-commit run spotless-apply --all-files --hook-stage manual
pre-commit run test-all --all-files --hook-stage manual

# Update hooks to latest versions
pre-commit autoupdate

# Uninstall hooks
pre-commit uninstall

# Clean hook cache
pre-commit clean
```

### Git Workflow

```bash
# Make changes
# ... edit files ...

# Stage changes
git add .

# Commit (hooks run automatically)
git commit -m "Your message"

# If formatting fails, auto-fix:
mvn spotless:apply
git add .
git commit -m "Your message"

# Skip hooks (emergency only)
git commit --no-verify -m "Emergency"
SKIP=checkstyle git commit -m "Skip specific hook"
```

## Hook Reference

| Hook | Runs On | Purpose | Auto-fix |
|------|---------|---------|----------|
| `trailing-whitespace` | commit | Remove trailing whitespace | Yes |
| `end-of-file-fixer` | commit | Ensure newline at EOF | Yes |
| `check-yaml` | commit | Validate YAML syntax | No |
| `check-added-large-files` | commit | Prevent large files | No |
| `check-merge-conflict` | commit | Detect merge markers | No |
| `mixed-line-ending` | commit | Prevent mixed line endings | Yes |
| `spotless-check` | commit | Check code formatting | No |
| `spotless-apply` | manual | Format code | Yes |
| `checkstyle` | commit | Check code style | No |
| `test-changed` | commit | Run tests for changed files | No |
| `test-all` | manual | Run all tests | No |

## Troubleshooting

### Pre-commit not running
```bash
pre-commit install
```

### Formatting issues
```bash
mvn spotless:apply
```

### Test failures
```bash
mvn test  # See full output
mvn test -Dtest=YourTest  # Run specific test
```

### Java version issues
```bash
java -version  # Should be 17+
```

### Maven issues
```bash
mvn clean install -U  # Force update dependencies
```

## File Structure

```
hooks-for-java/
├── .pre-commit-config.yaml      # Pre-commit configuration
├── .pre-commit-scripts/
│   └── run-changed-tests.sh     # Lint-staged behavior script
├── checkstyle.xml               # Checkstyle rules
├── pom.xml                      # Maven configuration
├── src/
│   ├── main/java/               # Source code
│   └── test/java/               # Tests
├── README.md                    # Main documentation
├── CONTRIBUTING.md              # Contribution guide
└── QUICK_REFERENCE.md          # This file
```

## Key Features

✅ **Automatic Code Formatting**: Spotless with Google Java Format
✅ **Style Enforcement**: Checkstyle with Google Java Style Guide
✅ **Intelligent Testing**: Only runs tests for changed files (like lint-staged)
✅ **Pre-commit Validation**: Catches issues before commit
✅ **Java 17**: Modern Java version
✅ **JUnit 5**: Latest testing framework

## Example Workflow

1. Make changes to `Calculator.java`
2. Stage: `git add src/main/java/com/example/demo/Calculator.java`
3. Commit: `git commit -m "Add new method"`
4. Hooks automatically:
   - Check formatting
   - Check style
   - Run only `CalculatorTest` (not all tests!)
5. If all pass, commit succeeds

## Performance Tips

- **Faster commits**: The `test-changed` hook only runs relevant tests
- **Manual formatting**: Run `mvn spotless:apply` before committing to avoid failures
- **Cache**: Pre-commit caches environments for faster subsequent runs
- **Parallel**: Maven runs tests in parallel when possible

## Getting Help

- See [README.md](README.md) for setup and features
- See [CONTRIBUTING.md](CONTRIBUTING.md) for development workflow
- Check [pre-commit.com](https://pre-commit.com/) for hook documentation
- Maven: [maven.apache.org](https://maven.apache.org/)
- Spotless: [github.com/diffplug/spotless](https://github.com/diffplug/spotless)
- Checkstyle: [checkstyle.org](https://checkstyle.org/)
