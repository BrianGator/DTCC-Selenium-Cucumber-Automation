# Consolidated Report Portal

The primary report URL is:

https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/

The portal is designed as the main reporting dashboard. It embeds the live Allure report by default and keeps the supporting report links compact.

## Primary View

Use the embedded Allure report first. It should show:

- Stable pass/fail totals
- Test suites and packages
- Behaviors and feature/story groupings
- Timeline and duration data
- Categories for skipped opt-in demos and failures when intentional demos are enabled
- Test detail pages with attachments, quality gate evidence, and known warning/error registers

## Supporting Reports

| Report | Purpose |
|---|---|
| Allure | Main engineering report for Selenium UI, Selenide, REST API, SQL, quality gates, attachments, and intentional demos. |
| Cucumber BDD | Business-readable Given/When/Then feature and scenario evidence. |
| Surefire/TestNG | Raw Maven runner XML/HTML evidence for CI troubleshooting. |
| Static dashboard | Portfolio summary and fallback static report view. |
| GitHub Actions logs | Environment setup, Maven output, artifacts, and GitHub Pages publishing details. |

## Quality Gates

The stable workflow should pass with:

```text
mvn -B clean compile test-compile -DskipTests
mvn -B clean test -Pallure-ui -Dbrowser=chrome -Dheadless=true -DincludeIntentionalFailures=false
mvn -B allure:report
```

Stable expected result:

- Failures: 0
- Errors: 0
- Intentional failures: skipped by default
- Allure HTML: `target/site/allure-report/index.html`

## Intentional Failure Demos

Intentional failure tests should only run when explicitly enabled:

```text
-DincludeIntentionalFailures=true
```

They are useful for showing Allure failed-test categories, defect-style reports, screenshots, and attachments. They should not run in the default stable suite.

## Cucumber Safety Note

The Cucumber report uses safe text payloads for security examples. It should not execute JavaScript popups from test data.