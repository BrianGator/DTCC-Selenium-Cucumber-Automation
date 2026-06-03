# GitHub Actions + Allure UI Tests

This test-case category demonstrates how Java Selenium/TestNG tests produce browser-based Allure reports in GitHub.

## Executable code

```text
src/test/java/com/dtcc/automation/reports/AllureEnabledUiReportTest.java
```

## Suite

```text
src/test/resources/testng-suites/allure-ui-suite.xml
```

## Run locally

```bash
mvn clean test -Pallure-ui -Dallure.results.directory=target/allure-results
mvn allure:report
open target/site/allure-report/index.html
```

## Run in GitHub

Use the **Actions** tab and select **Selenium CI/CD Pipeline with Allure Reports**.
