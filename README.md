# DTCC Selenium Cucumber Automation

**Author:** Brian McCarthy  
**Purpose:** Public-safe GitHub automation project demonstrating SDET skills using Java, Selenium WebDriver, Selenide, Cucumber BDD, TestNG, JUnit, Rest Assured, Karate, SQL/JDBC validation, Maven, Gradle, Jenkins/CloudBees, GitHub Actions, Allure Reports, Maven Surefire/TestNG reports, Cucumber artifacts, CI/CD reporting dashboards, and defect screenshot handling.

> Independent portfolio project. This repository is not affiliated with, endorsed by, or connected to DTCC. It uses public website checks against `https://www.dtcc.com/` plus safe mock application/API examples. It does not use DTCC internal systems, credentials, private APIs, or protected data.

---

## Quick Links: Reports, Dashboards, and GitHub UI

### Repository and Website Under Test

| Item | Link |
|---|---|
| GitHub Repository | https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/ |
| Website Under Test | https://www.dtcc.com/ |
| GitHub Actions Runs | https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions |
| Run Selenium CI/CD Pipeline with Allure Reports | https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions/workflows/selenium.yml |

### Deployed GitHub Pages Report URLs

After the GitHub Actions workflow runs successfully, GitHub Pages publishes the report portal here:

| Report View | URL |
|---|---|
| Main Report Portal | https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/ |
| Live Allure Report | https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/allure/index.html |
| Sample Allure Report | https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/sample-reports/index.html |
| Sample Passed Report | https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/sample-reports/passed-report.html |
| Sample Failed Report | https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/sample-reports/failed-report.html |
| Sample Surefire/TestNG Report | https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/sample-reports/surefire-report.html |
| Runtime Surefire Folder | https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/surefire/ |

### PDF Report Exports

The `13_Reports/` folder is the report evidence folder. The following PDF exports are used for portfolio review and report evidence:

| PDF Report | Purpose |
|---|---|
| `Allure-Test-Report-6-3-2026.pdf` | Allure dashboard evidence with overview, suites, trend, environment, passed tests, failed tests, and defect details. |
| `Cucumber-Test-Report-6-3-2026.pdf` | Cucumber BDD evidence showing feature, scenario, Given/When/Then steps, and scenario status. |
| `Test-NG-Sunfire-Test-Report-6-3-2026.pdf` | TestNG/Maven Surefire evidence showing suite totals, pass/fail/skip counts, class names, methods, and execution time. |
| `Website-Dashboard-Consolidated-Report-6-3-2026.pdf` | Consolidated dashboard evidence summarizing report links, overall totals, suite status, and defect examples. |

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
- Jenkins/CloudBees pipeline examples
- GitHub Actions CI/CD
- Allure report generation and GitHub Pages publishing
- Maven Surefire/TestNG XML reporting
- Cucumber JSON/HTML BDD reporting
- Defect screenshots, failed-test logs, and report evidence exports
- Public website coverage for `dtcc.com`
- Full Selenium framework-from-scratch architecture, including driver factory, base test, configuration, reusable utilities, page objects, BDD runners, listeners, test data, and CI/CD reporting

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
│   ├── Accessibility_Tests/
│   ├── Broken_Link_Tests/
│   ├── CI_CD_Tests/
│   ├── Cucumber_Tests/
│   ├── Data_Driven_Tests/
│   ├── GitHub_Actions_Allure_UI_Tests/
│   ├── Intentional_Failure_Examples/
│   ├── JUnit_Tests/
│   ├── Login_Tests/
│   ├── Negative_Tests/
│   ├── PDF_Download_Tests/
│   ├── Performance_Tests/
│   ├── Regression_Tests/
│   ├── SQL_Database_Tests/
│   ├── Security_Tests/
│   ├── Selenide_Tests/
│   ├── Selenium_Tests/
│   ├── Smoke_Tests/
│   ├── Unit_Tests/
│   └── Visual_Smoke_Tests/
├── 13_Reports/
│   ├── allure-sample-results/
│   │   ├── index.html
│   │   ├── passed-report.html
│   │   ├── failed-report.html
│   │   └── surefire-report.html
│   ├── web-ui/
│   │   └── index.html
│   ├── Allure-Test-Report-6-3-2026.pdf
│   ├── Cucumber-Test-Report-6-3-2026.pdf
│   ├── Test-NG-Sunfire-Test-Report-6-3-2026.pdf
│   ├── Website-Dashboard-Consolidated-Report-6-3-2026.pdf
│   ├── Sample-Test-Execution-Summary.md
│   ├── sample-testng-results.xml
│   ├── sample-cucumber-report.json
│   ├── sample-selenide-testng-results.xml
│   └── sample-failed-test-log.txt
├── 14_Defects_and_Screenshots/
├── 15_Full_Selenium_Framework_From_Scratch/
│   └── README.md
├── 16_DTCC_Public_Website_Page_Coverage/
├── 17_Selenide_Playwright_Experience_For_Java_Selenium/
├── 18_GitHub_Actions_Allure_Report_UI/
├── docs/
│   ├── defects/
│   ├── reports/
│   ├── test-cases/
│   └── test-strategy/
├── full-selenium-framework-from-scratch/
│   ├── README.md
│   ├── pom.xml
│   ├── Jenkinsfile
│   ├── selenide-wrapper/
│   └── src/
│       ├── main/java/com/dtcc/automation/
│       │   ├── config/
│       │   ├── constants/
│       │   ├── driver/
│       │   ├── pages/
│       │   └── utils/
│       └── test/
│           ├── java/com/dtcc/automation/
│           │   ├── base/
│           │   ├── listeners/
│           │   ├── runners/
│           │   ├── stepdefs/
│           │   └── tests/
│           └── resources/
│               ├── features/
│               ├── testdata/
│               ├── testng-suites/
│               └── config.properties
├── scripts/
│   ├── build-report-portal.sh
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

## 3. Full Selenium Framework From Scratch

The framework-from-scratch area explains how the project would be built from a blank repository into a maintainable enterprise SDET automation framework. It documents the same layers used in real Java/Selenium frameworks: Maven dependencies, browser driver management, base classes, Page Object Model, Cucumber BDD, TestNG suites, reporting, test data, utility classes, failure screenshots, and CI/CD execution.

### Framework-from-Scratch Folder Structure

```text
full-selenium-framework-from-scratch/
├── README.md
├── pom.xml
├── Jenkinsfile
├── selenide-wrapper/
│   ├── README.md
│   └── src/test/java/com/dtcc/automation/selenide/
│       ├── SelenideTestBase.java
│       ├── SelenideLoginValidationTest.java
│       ├── SelenideOrderLifecycleTest.java
│       ├── SelenidePublicSiteSmokeTest.java
│       └── pages/
│           ├── SelenideLoginPage.java
│           ├── SelenideOrderLifecyclePage.java
│           └── SelenidePublicHomePage.java
└── src/
    ├── main/java/com/dtcc/automation/
    │   ├── config/
    │   │   ├── ConfigReader.java
    │   │   └── EnvironmentConfig.java
    │   ├── constants/
    │   │   ├── FrameworkConstants.java
    │   │   └── TestGroups.java
    │   ├── driver/
    │   │   ├── DriverFactory.java
    │   │   ├── DriverManager.java
    │   │   └── BrowserOptionsFactory.java
    │   ├── pages/
    │   │   ├── BasePage.java
    │   │   ├── LoginPage.java
    │   │   ├── OrderLifecyclePage.java
    │   │   ├── CataloguePage.java
    │   │   ├── CheckoutPage.java
    │   │   ├── ConfirmationPage.java
    │   │   └── PublicHomePage.java
    │   └── utils/
    │       ├── WaitUtils.java
    │       ├── ScreenshotUtils.java
    │       ├── JsonUtils.java
    │       ├── FileDownloadUtils.java
    │       ├── PdfValidationUtils.java
    │       ├── ExcelReader.java
    │       ├── LogUtils.java
    │       └── EncryptionManager.java
    └── test/
        ├── java/com/dtcc/automation/
        │   ├── base/
        │   │   ├── BaseTest.java
        │   │   └── ApiBaseTest.java
        │   ├── listeners/
        │   │   ├── TestExecutionListener.java
        │   │   ├── RetryAnalyzer.java
        │   │   └── AnnotationTransformer.java
        │   ├── runners/
        │   │   ├── CucumberTestRunner.java
        │   │   └── TestNGSuiteRunner.java
        │   ├── stepdefs/
        │   │   ├── LoginSteps.java
        │   │   ├── OrderSteps.java
        │   │   └── PublicSiteSteps.java
        │   └── tests/
        │       ├── ui/
        │       │   ├── LoginValidationTest.java
        │       │   └── SeleniumOrderLifecycleTest.java
        │       ├── api/
        │       │   └── BackendTransactionTest.java
        │       ├── database/
        │       │   └── SqlDatabaseValidationTest.java
        │       ├── publicsite/
        │       │   └── DtccPublicSiteSmokeTest.java
        │       └── unit/
        │           └── FrameworkUtilityUnitTest.java
        └── resources/
            ├── config.properties
            ├── log4j2.xml
            ├── features/
            │   ├── Login.feature
            │   ├── OrderE2E.feature
            │   └── PublicSiteSmoke.feature
            ├── karate/
            │   ├── karate-config.js
            │   └── clearing-api-test.feature
            ├── sql/
            │   ├── schema.sql
            │   ├── data.sql
            │   └── validation-queries.sql
            ├── testdata/
            │   ├── login-users.json
            │   ├── order-test-data.json
            │   └── search-data.csv
            └── testng-suites/
                ├── smoke-suite.xml
                ├── ui-regression-suite.xml
                ├── backend-api-suite.xml
                ├── cucumber-suite.xml
                ├── database-suite.xml
                └── all-suites.xml
```

### Framework Layers and Responsibilities

| Layer | Files / Folders | Purpose |
|---|---|---|
| Build and dependencies | `pom.xml`, `build.gradle` | Defines Java 17, Selenium, TestNG, JUnit, Cucumber, Rest Assured, Karate, H2, Selenide, Allure, and Maven plugins. |
| Configuration | `config.properties`, `ConfigReader.java`, `EnvironmentConfig.java` | Centralizes base URL, browser, headless mode, timeout, environment, test data path, and reporting paths. |
| Driver management | `DriverFactory.java`, `DriverManager.java`, `BrowserOptionsFactory.java` | Creates browser instances, applies Chrome/Edge options, supports headless execution, and stores WebDriver safely for parallel execution. |
| Base test layer | `BaseTest.java`, `ApiBaseTest.java` | Handles setup/teardown, browser lifecycle, environment loading, API base URI setup, and reusable test initialization. |
| Page Object Model | `BasePage.java`, `LoginPage.java`, `OrderLifecyclePage.java`, `CataloguePage.java`, `CheckoutPage.java`, `ConfirmationPage.java`, `PublicHomePage.java` | Keeps locators and page actions separate from test assertions so tests remain readable and maintainable. |
| BDD layer | `features/`, `stepdefs/`, `CucumberTestRunner.java` | Maps business-readable Gherkin scenarios to Java step definitions and TestNG/Cucumber execution. |
| API layer | `BackendTransactionTest.java`, `karate/`, `JsonUtils.java` | Validates service contracts, response status, JSON payloads, schema-like expectations, and backend transaction behavior. |
| SQL/database layer | `schema.sql`, `data.sql`, `validation-queries.sql`, `SqlDatabaseValidationTest.java` | Builds safe H2 data, runs SQL queries, and verifies persisted backend state without private enterprise database access. |
| Utilities | `WaitUtils.java`, `ScreenshotUtils.java`, `FileDownloadUtils.java`, `PdfValidationUtils.java`, `ExcelReader.java`, `LogUtils.java` | Provides reusable waits, screenshots, downloads, PDF validation, test data access, logging, and common framework helpers. |
| Listeners and retries | `TestExecutionListener.java`, `RetryAnalyzer.java`, `AnnotationTransformer.java` | Captures screenshots/logs on failure, attaches evidence to reports, and optionally retries unstable tests when configured. |
| Reporting | `target/allure-results`, `target/site/allure-report`, `target/surefire-reports`, `target/cucumber-reports` | Produces Allure, Surefire/TestNG, Cucumber, screenshots, and CI/CD artifacts. |
| CI/CD | `.github/workflows/selenium.yml`, `Jenkinsfile` | Runs tests headlessly, publishes artifacts, generates Allure, and deploys report evidence to GitHub Pages. |

### Framework Build Sequence From Scratch

| Step | What Is Added | Outcome |
|---|---|---|
| 1 | Create Maven Java project and `pom.xml` | Project can compile and manage dependencies. |
| 2 | Add `config.properties` and config reader | Environment-specific values are no longer hard-coded. |
| 3 | Add driver factory and browser options | Chrome/Edge execution works locally and in CI. |
| 4 | Add `BaseTest` and setup/teardown | Tests share consistent lifecycle management. |
| 5 | Add Page Object Model | UI tests become readable and maintainable. |
| 6 | Add TestNG suites | Smoke, regression, API, DB, and full-suite execution can be controlled by XML/profile. |
| 7 | Add Cucumber feature files and step definitions | Business-readable BDD coverage is available. |
| 8 | Add Rest Assured/Karate API checks | Backend/API validation is part of the same framework. |
| 9 | Add H2 SQL validation | Database testing pattern is demonstrated safely without private DB access. |
| 10 | Add listeners, screenshots, logs, and retries | Failed tests create useful defect evidence. |
| 11 | Add Allure, Surefire/TestNG, and Cucumber reports | Test results are reviewer-friendly and CI-ready. |
| 12 | Add GitHub Actions/Jenkins | Framework runs in a cloud CI/CD execution UI. |

### Key Framework Files Explained

| File | Responsibility |
|---|---|
| `ConfigReader.java` | Loads properties such as browser, base URL, timeout, headless mode, and environment from `config.properties`. |
| `DriverFactory.java` | Creates WebDriver instances based on the configured browser and runtime options. |
| `DriverManager.java` | Stores and retrieves the active WebDriver instance, usually with ThreadLocal for parallel safety. |
| `BrowserOptionsFactory.java` | Builds ChromeOptions/EdgeOptions for headless runs, CI stability, window size, downloads, and security settings. |
| `BasePage.java` | Provides common Selenium operations such as click, type, wait, get text, and element visibility checks. |
| `BaseTest.java` | Starts the browser before each test, opens the target URL, and quits the driver after execution. |
| `TestExecutionListener.java` | Captures screenshots, logs, and report attachments when tests fail. |
| `RetryAnalyzer.java` | Optionally reruns selected failed tests to reduce noise from transient failures. |
| `CucumberTestRunner.java` | Connects Cucumber feature files to TestNG execution and Cucumber reporting. |
| `OrderSteps.java` | Implements Given/When/Then workflow steps for login, catalogue selection, checkout, and confirmation. |
| `BackendTransactionTest.java` | Uses API checks to verify mock backend transaction behavior. |
| `SqlDatabaseValidationTest.java` | Loads safe H2 test data and validates backend persistence with SQL queries. |
| `PdfValidationUtils.java` | Supports PDF-heavy public website testing through file download and document content checks. |
| `ExcelReader.java` | Demonstrates data-driven testing patterns for spreadsheet-based test input. |
| `Jenkinsfile` | Shows how the same Maven/TestNG/Cucumber framework can run through Jenkins or CloudBees. |
| `.github/workflows/selenium.yml` | Runs the framework in GitHub Actions and publishes report artifacts. |

### Example Framework Execution Flow

```text
GitHub Actions / Local Maven Command
        ↓
Maven profile selects TestNG suite XML
        ↓
BaseTest loads configuration and starts WebDriver
        ↓
Test class or Cucumber runner executes scenario
        ↓
Page objects perform UI actions and assertions
        ↓
API/SQL utilities validate backend state when needed
        ↓
TestNG listener captures screenshots/logs on failure
        ↓
Surefire, Cucumber, and Allure artifacts are generated
        ↓
GitHub Pages publishes reviewer-friendly report portal
```

### Framework Design Patterns Used

| Pattern | How It Is Used |
|---|---|
| Page Object Model | Keeps page locators/actions in page classes and test assertions in test classes. |
| Factory Pattern | Creates WebDriver instances and browser options from centralized configuration. |
| Singleton / ThreadLocal Driver Management | Keeps browser sessions isolated for stable parallel execution. |
| Data-Driven Testing | Allows JSON, CSV, Excel, or properties-based input to drive repeated scenarios. |
| BDD Step Mapping | Connects Cucumber Gherkin steps to Java step definition methods. |
| Listener Pattern | Hooks into TestNG execution for screenshots, logging, retries, and report evidence. |
| Layered Framework Architecture | Separates config, driver, pages, tests, utilities, reports, and CI/CD. |

---

## 4. Different Types of Tests

The `12_Test_Cases/` folder organizes portfolio evidence by test type so reviewers can quickly identify UI, API, database, CI/CD, BDD, reporting, negative, and non-functional coverage.

| Test Type Folder | What It Demonstrates |
|---|---|
| `12_Test_Cases/API_Tests/` | REST API validation, status codes, payload fields, schema-style checks, and backend service assertions. |
| `12_Test_Cases/Accessibility_Tests/` | Accessibility-focused checks such as page structure, labels, keyboard-friendly behavior, and screen-reader-oriented validation examples. |
| `12_Test_Cases/Broken_Link_Tests/` | Link availability, HTTP status validation, sitemap-driven checks, and public-page link regression coverage. |
| `12_Test_Cases/CI_CD_Tests/` | CI/CD validation patterns for Jenkins, CloudBees, GitHub Actions, build stages, artifacts, and pipeline reporting. |
| `12_Test_Cases/Cucumber_Tests/` | BDD feature files, scenarios, step definitions, and Given/When/Then business workflows. |
| `12_Test_Cases/Data_Driven_Tests/` | Parameterized test data, reusable fixtures, CSV/JSON-style test inputs, and repeatable multi-data validation. |
| `12_Test_Cases/GitHub_Actions_Allure_UI_Tests/` | GitHub Actions execution UI, Allure report publishing, workflow artifact review, and GitHub Pages report verification. |
| `12_Test_Cases/Intentional_Failure_Examples/` | Controlled failed-test examples used only for report evidence, screenshots, logs, and defect triage demonstrations. |
| `12_Test_Cases/JUnit_Tests/` | JUnit-based unit or framework-level test examples. |
| `12_Test_Cases/Login_Tests/` | Positive and negative login validation, credential handling against mock data, and authentication flow checks. |
| `12_Test_Cases/Negative_Tests/` | Invalid input, error handling, validation messages, blocked workflows, and failure-path coverage. |
| `12_Test_Cases/PDF_Download_Tests/` | PDF download checks, file existence validation, content checks, and document-heavy public website testing patterns. |
| `12_Test_Cases/Performance_Tests/` | Response-time, page-load, throughput, and lightweight performance assertion examples. |
| `12_Test_Cases/Regression_Tests/` | Reusable end-to-end regression coverage for stable business-critical flows. |
| `12_Test_Cases/SQL_Database_Tests/` | H2/JDBC database setup, seed data, SQL validation queries, and backend persistence checks. |
| `12_Test_Cases/Security_Tests/` | Public-safe security checks such as headers, input handling, and intentional security expectation examples. |
| `12_Test_Cases/Selenide_Tests/` | Selenide UI automation with smart waits, concise selectors, screenshots, and page-source capture on failure. |
| `12_Test_Cases/Selenium_Tests/` | Raw Selenium WebDriver UI automation, Page Object usage, browser actions, waits, and end-to-end UI flows. |
| `12_Test_Cases/Smoke_Tests/` | Fast high-level checks for build verification, basic page availability, and critical-path readiness. |
| `12_Test_Cases/Unit_Tests/` | Isolated method/class validation for framework utilities and business logic examples. |
| `12_Test_Cases/Visual_Smoke_Tests/` | Basic visual rendering checks, screenshot evidence, and UI layout sanity validation. |

---

## 5. Test Coverage Summary

| Area | Coverage | Status / Evidence |
|---|---|---|
| Selenium UI | Login, catalogue item selection, cart, checkout, payment, and confirmation flows using browser automation. | Covered by UI profiles and included in Allure/TestNG reporting. |
| Selenide UI | Auto-waiting Selenium wrapper tests for login, order lifecycle, and public website smoke coverage. | Covered by `selenide` profile with screenshot/page-source support on failure. |
| Cucumber BDD | Business-readable feature/scenario workflow using Given/When/Then steps for order lifecycle validation. | Covered by `sample-cucumber-report.json` and Cucumber report export. |
| Page Object Model | Reusable page classes that separate locators/actions from assertions and test flow. | Covered under `src/main/java/com/dtcc/automation/pages/` and Selenide page classes. |
| Rest Assured API | HTTP status codes, response body fields, JSON schema-style checks, response time, and business-state validation. | Covered by `api` profile and backend API test classes. |
| Karate API | BDD-style API contract validation and readable API feature syntax. | Covered under `src/test/resources/karate/`. |
| SQL Database | H2/JDBC schema setup, seed data, validation queries, and backend state assertions. | Covered by SQL database validation tests. |
| TestNG/JUnit | Suite-based execution, unit examples, listener-driven reporting, and XML output. | Covered by `target/surefire-reports/` and `sample-testng-results.xml`. |
| Security Tests | Public-safe security header expectation example and intentional defect evidence. | Covered in failed-test report examples. |
| Accessibility / Broken Link / PDF Download / Performance | Portfolio-level examples for common enterprise QA coverage areas. | Organized under `12_Test_Cases/` with expandable suites. |
| Public Website | DTCC.com public-page smoke checks, template rendering, sitemap-driven strategy, and selected high-value page areas. | Covered by `public-site` profile. |
| CI/CD | GitHub Actions and Jenkins/CloudBees execution patterns. | Covered by `.github/workflows/selenium.yml` and `Jenkinsfile`. |
| Reporting | Allure, Cucumber, Maven Surefire/TestNG, PDF exports, screenshots, and failure logs. | Covered under `13_Reports/` and workflow artifacts. |
| Framework From Scratch | Driver factory, base tests, utilities, POM, BDD runners, listeners, reporting, config, and CI/CD wiring. | Covered in `15_Full_Selenium_Framework_From_Scratch/` and `full-selenium-framework-from-scratch/`. |

### Current Report Snapshot - 6/3/2026

| Report | Total / Scope | Passed | Failed | Skipped | Notes |
|---|---:|---:|---:|---:|---|
| Allure Report | 21 test cases | 16 | 2 | 3 | Shows overview, trend, suites, environment, and detailed pass/fail evidence. |
| Cucumber Report | 1 feature / 1 scenario / 4 steps | 4 steps | 0 | 0 | Shows end-to-end order lifecycle workflow in Given/When/Then format. |
| TestNG / Maven Surefire Report | 8 methods | 7 | 1 | 0 | Shows suite/class/method status and execution evidence. |
| Consolidated Dashboard Report | Portfolio summary | 16 | 2 | 3 | Summarizes report links, suite status, and failed-test examples. |

### DTCC Website Area Status

| Public Website Area | Automation Status | Notes |
|---|---|---|
| DTCC Home / Public Template | Covered | Smoke checks validate page availability and public template rendering. |
| Public Navigation / Key Links | Covered / Expandable | Suitable for sitemap-driven link validation and broken-link checks. |
| Client Center | Priority Expansion Area | Data-heavy area recommended for targeted regression checks. |
| Important Notices | Priority Expansion Area | Recommended for table, link, date, and PDF validation coverage. |
| PDF-heavy Public Content | Priority Expansion Area | Recommended for Apache PDFBox or download validation utilities. |
| Global Trade Repository / Table-heavy Pages | Priority Expansion Area | Recommended for Selenide auto-waiting and resilient table assertions. |
| Internal DTCC Systems | Not Covered | Out of scope. This project intentionally avoids private systems, credentials, APIs, and internal data. |

---

## 6. How to Run Locally

### Prerequisites

| Tool | Version / Requirement |
|---|---|
| Java | JDK 17+ |
| Maven | 3.9+ |
| Browser | Chrome or Edge installed |
| Git | Required to clone and run locally |

### Local Test Commands

| Test | Command |
|---|---|
| Compile only | `mvn clean compile test-compile -DskipTests` |
| Stable Allure-enabled suite | `mvn clean test -Pallure-ui -Dbrowser=chrome -Dheadless=true -DincludeIntentionalFailures=false -Dallure.results.directory=target/allure-results` |
| Generate Allure HTML after test run | `mvn allure:report` |
| Selenium UI tests | `mvn test -Pui -Dheadless=true -Dbrowser=chrome` |
| Selenide UI tests | `mvn test -Pselenide -Dheadless=true -Dbrowser=chrome` |
| API tests | `mvn test -Papi` |
| Grouped test-case showcase | `mvn test -Ptestcases` |
| Expanded showcase suite | `mvn test -Pexpanded -Dheadless=true -Dbrowser=chrome` |
| DTCC.com public-site tests | `mvn test -Ppublic-site` |
| Failure demo for report examples | `mvn test -Pfailure-demo -DincludeIntentionalFailures=true` |
| All configured suite profile | `mvn test -Pall -Dheadless=true -Dbrowser=chrome` |

After generating the Allure report, open:

```text
target/site/allure-report/index.html
```

---

## 7. GitHub Actions Dashboard + Allure Report UI

The primary workflow is:

```text
.github/workflows/selenium.yml
```

The workflow does the following:

- Checks out the repository
- Installs Java JDK 17
- Installs stable Chrome for headless Selenium/Selenide execution
- Runs the selected Maven profile
- Stores raw Allure result data in `target/allure-results`
- Stores Maven Surefire/TestNG output in `target/surefire-reports`
- Stores Cucumber output in `target/cucumber-reports` when BDD tests run
- Stores failed-test screenshots in `target/failed-test-screenshots`
- Generates the Allure HTML report in `target/site/allure-report`
- Builds a GitHub Pages report portal in `target/report-portal`
- Publishes the portal to GitHub Pages
- Uploads workflow artifacts for raw test results, Allure HTML, Surefire/TestNG output, and report portal evidence

GitHub Actions is the execution UI. GitHub Pages is the reporting UI.

### Recommended GitHub UI Settings

| Scenario | `test_profile` | `include_intentional_failures` |
|---|---|---|
| Stable portfolio/demo run | `allure-ui` | `false` |
| Show intentional failed-test evidence | `failure-demo` | `true` |
| Public DTCC.com smoke coverage | `public-site` | `false` |
| Selenide-focused UI run | `selenide` | `false` |
| API/backend run | `api` | `false` |

Important: GitHub Pages is a static website, so it cannot safely start workflows directly without a GitHub token. The report portal links to GitHub Actions, which is the correct UI for running tests.

---

## 8. Report Portal Structure

The deployed report portal contains runtime and sample report views:

```text
/
├── index.html                    # Landing page with links to report views
├── allure/index.html             # Live Allure report generated by the latest workflow
├── sample-reports/index.html     # Sample Allure-style dashboard
├── sample-reports/passed-report.html
├── sample-reports/failed-report.html
├── sample-reports/surefire-report.html
└── surefire/                     # Runtime Maven Surefire/TestNG output copied from workflow
```

---

## 9. Report Artifacts and Evidence

Runtime reports are created when tests run:

```text
target/allure-results/            Raw Allure test result files
target/site/allure-report/        Generated live Allure HTML dashboard
target/surefire-reports/          Maven Surefire/TestNG XML and text reports
target/cucumber-reports/          Cucumber HTML/JSON BDD artifacts
target/failed-test-screenshots/   Screenshots captured on failures
target/selenide-reports/          Selenide screenshots, page source, and browser logs
target/report-portal/             GitHub Pages report portal package
```

Static/sample and portfolio evidence reports are stored under `13_Reports/`:

```text
13_Reports/allure-sample-results/index.html
13_Reports/allure-sample-results/passed-report.html
13_Reports/allure-sample-results/failed-report.html
13_Reports/allure-sample-results/surefire-report.html
13_Reports/Allure-Test-Report-6-3-2026.pdf
13_Reports/Cucumber-Test-Report-6-3-2026.pdf
13_Reports/Test-NG-Sunfire-Test-Report-6-3-2026.pdf
13_Reports/Website-Dashboard-Consolidated-Report-6-3-2026.pdf
13_Reports/sample-testng-results.xml
13_Reports/sample-cucumber-report.json
13_Reports/sample-selenide-testng-results.xml
13_Reports/sample-failed-test-log.txt
```

### Report Types

| Report Type | Generated By | Main Use |
|---|---|---|
| Allure | Allure TestNG adapter + Allure Maven plugin | Executive-friendly visual dashboard, suite breakdown, environment data, trend, and failed-test detail. |
| Cucumber | Cucumber Java/TestNG runner | BDD scenario evidence using business-readable Given/When/Then syntax. |
| Maven Surefire / TestNG | Maven Surefire plugin + TestNG XML suites | CI-compatible XML/text output for pass/fail/skip counts, class names, method names, and execution time. |
| Failed-Test Screenshots | Selenium/TestNG listener and Selenide reporting | Visual defect evidence for failed UI tests. |
| Consolidated PDF | Exported report evidence | Portfolio-ready snapshot combining dashboard totals, linked reports, suite status, and defect examples. |

---

## 10. Selenide - Playwright-Style Auto-Waiting for Java/Selenium

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

### Selenide Files

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

## 11. DTCC.com Public Website Automation Strategy

When automating `dtcc.com`, do not hand-code one brittle Selenium test per URL. Use a sitemap-driven model for broad HTTP/page availability and Page Object/Selenide tests for high-value templates and data-heavy pages.

Priority areas:

- **Public home/template rendering:** validate page load, title/body rendering, public navigation, and key visible regions.
- **Client Center and Important Notices:** data-heavy areas that are more likely to regress and benefit from table/link/date assertions.
- **PDF-heavy sections:** use download checks and PDF validation utilities such as Apache PDFBox.
- **Global Trade Repository and dynamic table-heavy pages:** use strong synchronization, stable locators, and Selenide auto-waiting.
- **Broken-link and sitemap-driven checks:** broaden public coverage without creating one brittle test per page.

Out of scope:

- Private DTCC applications
- Internal APIs
- Authenticated DTCC workflows
- Production data
- Any credentials, tokens, or protected enterprise information

---

## 12. SQL Database Tests Without Enterprise DB Access

Because reviewers will not have access to private enterprise databases, the project includes H2 in-memory SQL database examples:

```text
src/test/resources/sql/schema.sql
src/test/resources/sql/data.sql
src/test/resources/sql/validation-queries.sql
src/test/java/com/dtcc/automation/database/SqlDatabaseValidationTest.java
```

This demonstrates the same SDET pattern used for real Oracle/DB2/Snowflake validation: create schema, load test data, execute SQL queries, and assert that backend state matches expected application behavior.

---

## 13. Publishing and Safety Notes

Before publishing or sharing:

- Do not add real credentials, private API tokens, or internal URLs.
- Keep `.env`, `target/`, screenshots, and test-output folders ignored unless intentionally publishing sanitized portfolio artifacts.
- Keep test data fictional.
- Keep the disclaimer that this is an independent portfolio project not affiliated with DTCC.
- Use public website checks only and avoid private or authenticated DTCC systems.

---

## 14. Recommended Reviewer Flow

1. Open the repository.
2. Review this README and the folder structure.
3. Review the **Full Selenium Framework From Scratch** section.
4. Open the GitHub Actions workflow.
5. Run the `allure-ui` profile with `include_intentional_failures=false`.
6. Review the workflow logs, artifacts, execution time, and generated report links.
7. Open the deployed GitHub Pages report portal.
8. Open the live Allure report, Cucumber evidence, Surefire/TestNG report, and PDF exports.
9. Review code under `src/test/java/com/dtcc/automation/`.
10. Review `13_Reports/` for portfolio-ready report evidence.
11. Review `12_Test_Cases/` for organized examples by test type.

---

## 15. Notes About Running Tests

This project was generated as a public-safe portfolio framework. Some suites use mock applications, mock APIs, H2 sample data, and public website checks. Intentional failure examples are isolated behind the `failure-demo` profile and should not be used as the default CI profile.

Use this as the default stable workflow setting:

```text
test_profile: allure-ui
include_intentional_failures: false
```

Use this only when failed-test examples are intentionally needed in the report evidence:

```text
test_profile: failure-demo
include_intentional_failures: true
```
