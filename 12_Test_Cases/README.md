# 12_Test_Cases

This folder is organized by test type so a reviewer can quickly see that the framework covers UI, API, database, unit, regression, Cucumber BDD, Selenium, CI/CD, and reporting concerns.

## Subfolders

| Folder | Purpose | Main executable source |
|---|---|---|
| `API_Tests/` | REST Assured API tests for status code, JSON body, schema, SLA, and DB persistence checks. | `src/test/java/com/dtcc/automation/api/` |
| `Login_Tests/` | Selenium login validation against the mock order app. | `src/test/java/com/dtcc/automation/ui/LoginValidationTest.java` |
| `Cucumber_Tests/` | BDD Gherkin scenarios for business-readable tests. | `src/test/resources/features/` and `src/test/java/com/dtcc/automation/stepdefs/` |
| `Selenium_Tests/` | Browser-based POM tests for login, catalogue, cart, order conversion, payment, and confirmation. | `src/test/java/com/dtcc/automation/ui/SeleniumOrderLifecycleTest.java` |
| `CI_CD_Tests/` | Validates Jenkins/GitHub Actions configuration and report publishing. | `src/test/java/com/dtcc/automation/cicd/CiCdConfigurationTest.java` |
| `Regression_Tests/` | Smoke and regression checks for critical routing/business rules. | `src/test/java/com/dtcc/automation/regression/RegressionSmokeTest.java` |
| `JUnit_Tests/` | JUnit 5 unit examples for isolated business logic. | `src/test/java/com/dtcc/automation/unit/OrderCalculatorTest.java` |
| `SQL_Database_Tests/` | H2 SQL schema/data examples for database validation when no enterprise DB is available. | `src/test/java/com/dtcc/automation/database/SqlDatabaseValidationTest.java` |
| `Unit_Tests/` | TestNG unit test examples for deterministic logic. | `src/test/java/com/dtcc/automation/unit/OrderCalculatorTestNgTest.java` |

## Run all showcase tests

```bash
mvn test -Ptestcases
```

## Run selected examples

```bash
mvn test -Papi
mvn test -Pui
mvn test -Dtest=SqlDatabaseValidationTest test
mvn test -Dtest=CiCdConfigurationTest test
mvn test -Dtest=RegressionSmokeTest test
```

## Reporting

Execution reports are generated under `target/surefire-reports/`, `target/cucumber-reports/`, and `target/failed-test-screenshots/`. Static sample pass/fail report artifacts are included in `13_Reports/` for GitHub reviewers who do not run the project locally.


## Additional Test Types Added

Beyond the original API, Login, Cucumber, Selenium, CI/CD, Regression, JUnit, SQL Database, and Unit test folders, the project now includes:

```text
12_Test_Cases/
|-- Security_Tests/
|-- Accessibility_Tests/
|-- Performance_Tests/
|-- Broken_Link_Tests/
|-- PDF_Download_Tests/
|-- Data_Driven_Tests/
|-- Negative_Tests/
|-- Smoke_Tests/
|-- Visual_Smoke_Tests/
`-- Intentional_Failure_Examples/
```

These were added because mature SDET automation frameworks usually include more than happy-path UI/API checks. A strong portfolio framework should also demonstrate security header checks, accessibility smoke checks, page performance thresholds, broken-link validation, PDF/download validation, negative testing, data-driven testing, and visual evidence capture.

## Expanded Suite Commands

```bash
# Run the larger showcase suite
mvn test -Pexpanded

# Run the isolated intentional failure suite for report examples
mvn test -Pfailure-demo -DincludeIntentionalFailures=true
```

Intentional failures are isolated so the normal portfolio suite does not fail unless you explicitly run the failure-demo profile.
