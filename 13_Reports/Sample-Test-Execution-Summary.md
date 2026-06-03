# Sample Test Execution Summary

This folder contains static sample reports so a GitHub reviewer can see what pass/fail evidence looks like without connecting to Jenkins, CloudBees, or a private database.

| Suite | Passed | Failed | Skipped | Notes |
|---|---:|---:|---:|---|
| API Tests | 5 | 0 | 0 | REST Assured status/body/schema examples passed |
| Login Tests | 4 | 0 | 0 | Positive, negative, and data-driven login examples passed |
| Selenium Tests | 3 | 0 | 0 | UI order lifecycle examples passed |
| Cucumber Tests | 3 | 0 | 0 | BDD scenarios generated JSON/HTML-ready output |
| SQL Database Tests | 3 | 0 | 0 | H2 schema/data validation examples passed |
| Security Tests | 1 | 1 | 0 | One failure included as a realistic report example |
| Intentional Failure Demo | 0 | 1 | 2 | Isolated failure demo for portfolio review |

## Example Failed Tests

1. `SecurityHeadersTest.publicSiteShouldReturnCommonSecurityHeaders` — example failure for missing/changed security header expectations.
2. `IntentionalFailureExampleTest.intentionalApiContractFailureForReportDemo` — intentional failure showing how failed assertions appear in reports.

## Live Report Locations After Running Maven

```text
target/surefire-reports/
target/cucumber-reports/
target/failed-test-screenshots/
target/visual-snapshots/
```
