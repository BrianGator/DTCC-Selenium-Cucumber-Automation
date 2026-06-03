# GitHub Actions + Allure Report UI

This folder documents the Web UI used to run and review the Java Selenium, Selenide, TestNG, API, SQL database, and regression tests from GitHub.

## What this adds

- **GitHub Actions Dashboard**: Cloud execution UI that shows pipeline stages, logs, timing, pass/fail status, and artifacts.
- **Allure Report Dashboard**: Interactive HTML test report showing test cases, steps, screenshots, failures, categories, history, and defects.
- **GitHub Pages Deployment**: Publishes the Allure dashboard to the `gh-pages` branch after each workflow run.

## Main workflow file

```text
.github/workflows/selenium.yml
```

The workflow triggers on pushes to `main`, pull requests to `main`, or manual execution through the GitHub Actions **Run workflow** button.

## Typical commands

Run Allure-enabled tests locally:

```bash
mvn clean test -Pallure-ui -Dallure.results.directory=target/allure-results
```

Generate the Allure report locally:

```bash
mvn allure:report
```

Open the generated report:

```bash
open target/site/allure-report/index.html
```

Run a failure demo so the report shows failed tests:

```bash
mvn clean test -Pallure-ui -DincludeIntentionalFailures=true -Dallure.results.directory=target/allure-results
```

## GitHub Pages setup

1. Push this project to GitHub.
2. Go to **Settings > Pages**.
3. Set source to **Deploy from a branch**.
4. Select branch: `gh-pages`.
5. Select folder: `/root`.
6. Run the **Selenium CI/CD Pipeline with Allure Reports** workflow.
7. Open the published Pages URL to review the Allure dashboard.

## Review workflow in GitHub

- Go to **Actions**.
- Select **Selenium CI/CD Pipeline with Allure Reports**.
- Click **Run workflow**.
- Choose a Maven profile such as `allure-ui`, `expanded`, `selenide`, or `failure-demo`.
- Review logs and artifacts.
- Open the GitHub Pages Allure report after deployment.
