# Selenide Tests

This folder documents the Selenide-based UI tests. Selenide is used as a Selenium wrapper to provide Playwright-style auto-waiting, concise selectors, automatic screenshots, and cleaner Page Objects.

## Executable Test Classes

- `src/test/java/com/dtcc/automation/selenide/SelenideLoginValidationTest.java`
- `src/test/java/com/dtcc/automation/selenide/SelenideOrderLifecycleTest.java`
- `src/test/java/com/dtcc/automation/selenide/SelenidePublicSiteSmokeTest.java`

## Run Commands

```bash
mvn test -Pselenide
mvn test -Dtest=SelenideLoginValidationTest
mvn test -Dtest=SelenideOrderLifecycleTest
```

## What These Tests Validate

- Valid login and dashboard state
- Negative login validation message
- Catalogue item selection
- Cart conversion
- Payment submission
- Order confirmation
- DTCC.com public website smoke coverage

## Why Selenide Was Added

Selenide reduces flaky tests by applying built-in waits to interactions and assertions. It removes most `WebDriverWait`, `ExpectedConditions`, and manual `driver.findElement()` boilerplate.
