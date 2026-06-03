# DTCC Selenium Cucumber Automation

**Author:** Brian McCarthy  
**Purpose:** Public-safe GitHub automation project demonstrating SDET skills using Java, Selenium WebDriver, Selenide, Cucumber BDD, TestNG, JUnit, Rest Assured, Karate, SQL/JDBC validation, Maven, Gradle, Jenkins/CloudBees, GitHub Actions, Allure Reports, Surefire reports, reporting dashboards, and defect screenshot handling.

> Independent portfolio project. This repository is not affiliated with, endorsed by, or connected to DTCC. It uses public website checks against `https://www.dtcc.com/` plus safe mock application/API examples. It does not use DTCC internal systems, credentials, private APIs, or protected data.

---

## Quick Links: Reports, Dashboards, and GitHub UI

### Repository and Website Under Test

- **GitHub Repository:** https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/
- **Website Under Test:** https://www.dtcc.com/
- **GitHub Actions Runs:** https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions
- **Run Selenium CI/CD Pipeline with Allure Reports:** https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions/workflows/selenium.yml

### Deployed GitHub Pages Report URLs

After the GitHub Actions workflow runs successfully, GitHub Pages publishes the report portal here:

- **Main Report Portal:** https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/
- **Live Allure Report:** https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/allure/index.html
- **Portfolio / Static Dashboard:** https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/dashboard/index.html
- **Static Sample Allure Reports:** https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/sample-reports/index.html
- **Sample Passed Report:** https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/sample-reports/passed-report.html
- **Sample Failed Report:** https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/sample-reports/failed-report.html
- **Sample Surefire Report:** https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/sample-reports/surefire-report.html
- **Surefire Runtime Folder:** https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/surefire/

### How to Run Tests from the GitHub UI

1. Open: https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions/workflows/selenium.yml
2. Click **Run workflow**.
3. Select a Maven profile.
4. Click **Run workflow** again.
5. Open the completed workflow run to review logs, artifacts, execution time, and summary links.
6. Open the deployed GitHub Pages report portal after the deployment step completes.

Recommended stable settings:

```text
test_profile: allure-ui
include_intentional_failures: false
```

Use this only when you want failed-test examples in the report:

```text
test_profile: failure-demo
include_intentional_failures: true
```

Important: GitHub Pages is a static website, so it cannot safely start workflows directly without a GitHub token. The report portal links to GitHub Actions, which is the correct UI for running tests.

---

## 1. What This Project Demonstrates

This project is designed for an SDET automation interview or GitHub portfolio. It demonstrates how to build a scalable hybrid automation framework with:

- Java framework design
- Selenium WebDriver UI automation
- Selenide Playwright-style auto-waiting for Java/Selenium
- Cucumber BDD scenarios
- TestNG execution suites and listeners
- JUnit unit test examples
- Page Object Model design
- Rest Assured backend API validation
- Karate API contract testing
- SQL/JDBC database validation using safe H2 mock data
- Maven and Gradle build configuration
- Jenkins/CloudBees pipeline execution
- GitHub Actions CI/CD
- Allure report generation and GitHub Pages publishing
- Surefire/TestNG XML reports
- Defect screenshots and reporting
- Public website coverage for `dtcc.com`

---

## 2. File Structure

```text
DTCC-Selenium-Cucumber-Automation/
├── .github/workflows/
│   ├── ci-cd-pipeline.yml
│   └── selenium.yml
├── 01_Core_Programming_Languages_Java_Python_TypeScript/
├── 02_Selenium_WebDriver/
├── 03_TestNG_JUnit/
├── 04_Cucumber_BDD/
├── 05_Page_Object_Model_POM/
├── 06_API_Rest_Assured/
├── 07_Karate_Framework/
├── 08_Database_Testing_Framework/
├── 09_Maven_Gradle/
├── 10_Jenkins_CloudBees_CI_CD/
├── 11_Git_Bitbucket_Workflow/
├── 12_Test_Cases/
│   ├── API_Tests/
│   ├── Login_Tests/
│   ├── Cucumber_Tests/
│   ├── Selenium_Tests/
│   ├── Selenide_Tests/
│   ├── CI_CD_Tests/
│   ├── Regression_Tests/
│   ├── JUnit_Tests/
│   ├── SQL_Database_Tests/
│   ├── Unit_Tests/
│   ├── Security_Tests/
│   ├── Accessibility_Tests/
│   ├── Performance_Tests/
│   ├── Broken_Link_Tests/
│   ├── PDF_Download_Tests/
│   ├── Data_Driven_Tests/
│   ├── Negative_Tests/
│   ├── Smoke_Tests/
│   ├── Visual_Smoke_Tests/
│   └── Intentional_Failure_Examples/
├── 13_Reports/
│   ├── allure-sample-results/
│   ├── web-ui/
│   ├── Sample-Test-Execution-Summary.md
│   ├── sample-testng-results.xml
│   ├── sample-cucumber-report.json
│   └── sample-failed-test-log.txt
├── 14_Defects_and_Screenshots/
├── 15_Full_Selenium_Framework_From_Scratch/
├── 16_DTCC_Public_Website_Page_Coverage/
├── 17_Selenide_Playwright_Experience_For_Java_Selenium/
├── 18_GitHub_Actions_Allure_Report_UI/
├── docs/
│   ├── defects/
│   ├── reports/
│   ├── test-cases/
│   └── test-strategy/
├── full-selenium-framework-from-scratch/
│   └── selenide-wrapper/
├── scripts/
│   └── open-report-ui.sh
├── src/
│   ├── main/java/com/dtcc/automation/
│   │   ├── app/
│   │   ├── config/
│   │   ├── constants/
│   │   ├── database/
│   │   ├── pages/
│   │   └── utils/
│   └── test/
│       ├── java/com/dtcc/automation/
│       │   ├── api/
│       │   ├── base/
│       │   ├── database/
│       │   ├── publicsite/
│       │   ├── reports/
│       │   ├── runners/
│       │   ├── selenide/
│       │   ├── stepdefs/
│       │   ├── ui/
│       │   └── unit/
│       └── resources/
│           ├── features/
│           ├── karate/
│           ├── mock-web-app/
│           ├── schemas/
│           ├── sql/
│           ├── testdata/
│           └── testng-suites/
├── Jenkinsfile
├── pom.xml
├── build.gradle
├── .gitignore
└── README.md
```

---

## 3. Test Coverage Summary

| Area | What Is Tested |
|---|---|
| Selenium UI | Login, catalogue item selection, cart, checkout, payment, confirmation |
| Selenide UI | Auto-waiting UI tests with cleaner Selenium syntax |
| Cucumber BDD | Business-readable Given/When/Then workflows |
| Page Object Model | Reusable page classes separating locators/actions from assertions |
| Rest Assured API | Status codes, JSON body, response time, schema, and business fields |
| Karate API | BDD-style API contract validation |
| SQL Database | H2/JDBC schema, seed data, and SQL validation queries |
| Unit Testing | Business logic validation with JUnit and TestNG |
| Public Website | DTCC.com home/template rendering, sitemap checks, and public-page coverage |
| Reporting | Allure, Surefire/TestNG XML, Cucumber artifacts, screenshots, and static dashboards |
| CI/CD | GitHub Actions and Jenkins/CloudBees execution patterns |

---

## 4. How to Run Locally

### Prerequisites

- Java 17+
- Maven 3.9+
- Chrome or Edge installed
- Git

### Compile

```bash
mvn clean compile test-compile -DskipTests
```

### Run stable Allure-enabled suite

```bash
mvn clean test -Pallure-ui -Dbrowser=chrome -Dheadless=true -DincludeIntentionalFailures=false -Dallure.results.directory=target/allure-results
mvn allure:report
```

Open:

```text
target/site/allure-report/index.html
```

### Run Selenium UI tests

```bash
mvn test -Pui -Dheadless=true -Dbrowser=chrome
```

### Run Selenide tests

```bash
mvn test -Pselenide -Dheadless=true -Dbrowser=chrome
```

### Run API tests

```bash
mvn test -Papi
```

### Run grouped test-case showcase

```bash
mvn test -Ptestcases
```

### Run DTCC.com public-site tests

```bash
mvn test -Ppublic-site
```

### Run failure demo for report examples

```bash
mvn test -Pfailure-demo -DincludeIntentionalFailures=true
```

---

## 5. GitHub Actions Dashboard + Allure Report UI

The primary workflow is:

```text
.github/workflows/selenium.yml
```

The workflow does the following:

- Checks out the repository
- Installs Java JDK 17
- Installs stable Chrome for headless Selenium/Selenide execution
- Runs the selected Maven profile
- Stores raw Allure data in `target/allure-results`
- Stores Surefire/TestNG output in `target/surefire-reports`
- Generates the Allure HTML report in `target/site/allure-report`
- Builds a GitHub Pages report portal in `target/report-portal`
- Publishes the portal to GitHub Pages
- Uploads artifacts for raw test results, Allure HTML, and the Pages portal

GitHub Actions is the execution UI. GitHub Pages is the reporting UI.

---

## 6. Report Portal Structure

The deployed report portal contains:

```text
/
├── index.html                    # Landing page with links to all report views
├── allure/index.html             # Live Allure report generated by the latest workflow
├── dashboard/index.html          # Static portfolio dashboard
├── sample-reports/index.html     # Static Allure-style sample dashboard
├── sample-reports/passed-report.html
├── sample-reports/failed-report.html
├── sample-reports/surefire-report.html
└── surefire/                     # Runtime Surefire/TestNG output copied from workflow
```

---

## 7. Lightweight Static Dashboard

A local static dashboard is included here:

```text
13_Reports/web-ui/index.html
```

Run it locally:

```bash
bash scripts/open-report-ui.sh
```

Then open:

```text
http://localhost:8080
```

This dashboard links to the sample Allure reports, passed reports, failed reports, Surefire report, and report screenshot.

---

## 8. Selenide - Playwright-Style Auto-Waiting for Java/Selenium

Selenide was added as a modern UI automation layer on top of Selenium WebDriver. It does not replace Selenium; it wraps Selenium to reduce boilerplate and flakiness.

### Benefits

- Automatic browser management and cleanup
- Built-in smart waits for visibility, clickability, text, state, and assertions
- Concise jQuery-style syntax using `$()` and `$$()`
- Automatic screenshots and page source capture on failure
- Cleaner Page Objects with fewer custom wait wrappers
- Better handling of AJAX and asynchronous rendering

### Selenium vs. Selenide

| Feature | Raw Selenium WebDriver | Selenide Wrapper |
|---|---|---|
| Finding an element | `driver.findElement(By.id("submit"));` | `$("#submit");` |
| Waiting and clicking | `new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("btn"))).click();` | `$("#btn").click();` |
| Checking text | `assertTrue(driver.findElement(By.id("status")).getText().contains("Success"));` | `$("#status").shouldHave(text("Success"));` |
| Closing browser | `driver.quit();` | `closeWebDriver();` |

### Selenide files

```text
17_Selenide_Playwright_Experience_For_Java_Selenium/README.md
12_Test_Cases/Selenide_Tests/README.md
src/test/java/com/dtcc/automation/selenide/SelenideTestBase.java
src/test/java/com/dtcc/automation/selenide/SelenideLoginValidationTest.java
src/test/java/com/dtcc/automation/selenide/SelenideOrderLifecycleTest.java
src/test/java/com/dtcc/automation/selenide/SelenidePublicSiteSmokeTest.java
src/test/java/com/dtcc/automation/selenide/pages/SelenideLoginPage.java
src/test/java/com/dtcc/automation/selenide/pages/SelenideOrderLifecyclePage.java
src/test/java/com/dtcc/automation/selenide/pages/SelenidePublicHomePage.java
src/test/resources/testng-suites/selenide-ui-suite.xml
full-selenium-framework-from-scratch/selenide-wrapper/
```

---

## 9. DTCC.com Public Website Automation Strategy

When automating `dtcc.com`, do not hand-code one brittle Selenium test per URL. Use a sitemap-driven model for broad HTTP/page availability and POM/Selenide tests for high-value templates and data-heavy pages.

Priority areas:

- **Client Center** and **Important Notices** because they are data-heavy and prone to regression.
- **PDF-heavy sections** using PDF validation utilities such as Apache PDFBox.
- **Global Trade Repository** and dynamic table-heavy pages using strong synchronization and Selenide auto-waiting.

---

## 10. Reporting Artifacts

Runtime reports are created when tests run:

```text
target/allure-results/            Raw Allure test result files
target/site/allure-report/        Generated live Allure HTML dashboard
target/surefire-reports/          TestNG/JUnit XML and text reports
target/cucumber-reports/          Cucumber HTML/JSON BDD artifacts
target/failed-test-screenshots/   Screenshots captured by TestExecutionListener on failures
target/report-portal/             GitHub Pages report portal package
```

Static sample reports are included for GitHub review:

```text
13_Reports/allure-sample-results/index.html
13_Reports/allure-sample-results/passed-report.html
13_Reports/allure-sample-results/failed-report.html
13_Reports/allure-sample-results/surefire-report.html
13_Reports/web-ui/index.html
13_Reports/sample-testng-results.xml
13_Reports/sample-cucumber-report.json
13_Reports/sample-failed-test-log.txt
```

---

## 11. SQL Database Tests Without Enterprise DB Access

Because reviewers will not have access to private enterprise databases, the project includes H2 in-memory SQL database examples:

```text
src/test/resources/sql/schema.sql
src/test/resources/sql/data.sql
src/test/resources/sql/validation-queries.sql
src/test/java/com/dtcc/automation/database/SqlDatabaseValidationTest.java
```

This demonstrates the same SDET pattern used for real Oracle/DB2/Snowflake validation: create schema, load test data, execute SQL queries, and assert that backend state matches expected application behavior.

---

## 12. Publishing and Safety Notes

Before publishing or sharing:

- Do not add real credentials, private API tokens, or internal URLs.
- Keep `.env`, `target/`, screenshots, and test-output folders ignored.
- Keep test data fictional.
- Keep the disclaimer that this is an independent portfolio project not affiliated with DTCC.

---

## 13. Recommended Reviewer Flow

1. Open the repository.
2. Review the README and folder structure.
3. Open the GitHub Actions workflow.
4. Run the `allure-ui` profile.
5. Review the workflow logs and artifacts.
6. Open the deployed GitHub Pages report portal.
7. Open the live Allure report and static sample reports.
8. Review code under `src/test/java/com/dtcc/automation/`.

---

## 14. Notes About Running Tests

This project was generated as a public-safe portfolio framework. Some suites use mock applications, mock APIs, H2 sample data, and public website checks. Intentional failure examples are isolated behind the `failure-demo` profile and should not be used as the default CI profile.

Use this as the default stable workflow setting:

```text
test_profile: allure-ui
include_intentional_failures: false
```
