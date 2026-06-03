# Allure Report UI Guide

Allure is the recommended Web UI report layer for this GitHub-hosted Java automation project.

## What Allure shows

- Test case names and descriptions
- Pass, fail, skipped, and broken status
- Step-by-step execution details
- Failure stack traces
- Screenshots and logs attached to failed tests
- Defect-style categories
- Historical trend charts when published through GitHub Pages

## Generate locally

```bash
mvn clean test -Pallure-ui -Dallure.results.directory=target/allure-results
mvn allure:report
open target/site/allure-report/index.html
```

## Generate in GitHub

Push to `main` or manually run `.github/workflows/selenium.yml` from the **Actions** tab. The workflow publishes the dashboard to GitHub Pages from the `gh-pages` branch.

## Failure examples

Use this command to intentionally create a few failing tests for report and defect-management demonstration:

```bash
mvn clean test -Pallure-ui -DincludeIntentionalFailures=true -Dallure.results.directory=target/allure-results
```

The failing examples are controlled by the `includeIntentionalFailures` system property so they do not accidentally fail normal CI runs.
