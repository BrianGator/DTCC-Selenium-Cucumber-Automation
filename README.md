# DTCC Selenium Cucumber Automation

**Author:** Brian McCarthy  
**Purpose:** Public-safe GitHub automation project demonstrating SDET skills using Java, Selenium WebDriver, Cucumber BDD, TestNG, JUnit, Rest Assured, Karate, SQL/JDBC validation, Maven, Gradle, Jenkins/CloudBees, GitHub Actions, reporting, and defect screenshot handling.

> Independent portfolio project. This repository is not affiliated with, endorsed by, or connected to DTCC. It uses public website checks against `https://www.dtcc.com/` plus safe mock application/API examples. It does not use DTCC internal systems, credentials, private APIs, or protected data.

---

## 1. What This Project Demonstrates

This project is designed for an SDET automation interview or GitHub portfolio. It demonstrates how to build a scalable hybrid automation framework with:

- Java framework design
- Selenium WebDriver UI automation
- Cucumber BDD scenarios
- TestNG execution suites and listeners
- JUnit unit test examples
- Page Object Model design
- Rest Assured backend API validation
- Karate API contract testing
- SQL/JDBC database validation using safe mock data
- Maven and Gradle build configuration
- Jenkins/CloudBees pipeline execution
- GitHub Actions CI/CD
- Defect screenshots and reporting
- Public website coverage for `dtcc.com`

---

## 2. File Structure

```text
DTCC-Selenium-Cucumber-Automation/
├── .github/
│   └── workflows/
│       └── ci-cd-pipeline.yml                  # GitHub Actions CI/CD workflow
├── 01_Core_Programming_Languages_Java_Python_TypeScript/
├── 02_Selenium_WebDriver/                      # Selenium examples and README
├── 03_TestNG_JUnit/                            # TestNG/JUnit execution notes
├── 04_Cucumber_BDD/                            # BDD explanation and sample feature usage
├── 05_Page_Object_Model_POM/                   # Page Object Model documentation
├── 06_API_Rest_Assured/                        # API testing documentation
├── 07_Karate_Framework/                        # Karate API contract testing docs
├── 08_Database_Testing_Framework/              # JDBC/SQL validation documentation
├── 09_Maven_Gradle/                            # Build tool documentation
├── 10_Jenkins_CloudBees_CI_CD/                 # Jenkins/CloudBees pipeline docs
├── 11_Git_Bitbucket_Workflow/                  # Version-control workflow docs
├── 12_Test_Cases/                              # Test case coverage documentation
├── 13_Reports/                                 # Reporting documentation
├── 14_Defects_and_Screenshots/                 # Defect and screenshot documentation
├── 15_Full_Selenium_Framework_From_Scratch/    # Standalone Selenium framework guide
├── 16_DTCC_Public_Website_Page_Coverage/       # DTCC.com public website tests and strategy
├── docs/
│   ├── defects/
│   │   └── Jira-Defect-Template.md
│   ├── reports/
│   ├── test-cases/
│   │   └── DTCC-Public-Website-Test-Case-Matrix.md
│   └── test-strategy/
│       └── DTCC-Public-Website-Automation-Strategy.md
├── full-selenium-framework-from-scratch/       # Separated framework-from-scratch example
├── src/
│   ├── main/
│   │   └── java/com/dtcc/automation/
│   │       ├── app/                            # Small application/business logic for unit tests
│   │       ├── config/                         # Configuration manager
│   │       ├── constants/                      # Framework constants
│   │       ├── database/                       # Database/JDBC helper classes
│   │       ├── pages/                          # Page Object Model classes
│   │       │   ├── common/                     # Shared components
│   │       │   └── publicsite/                 # DTCC.com public website POM classes
│   │       └── utils/                          # Driver factory, screenshots, reports, utilities
│   └── test/
│       ├── java/com/dtcc/automation/
│       │   ├── api/                            # Rest Assured API tests
│       │   ├── base/                           # UI/API base test classes
│       │   ├── karate/                         # Karate runner
│       │   ├── publicsite/                     # DTCC.com Selenium + sitemap tests
│       │   ├── runners/                        # Cucumber/TestNG runners
│       │   ├── stepdefs/                       # Cucumber step definitions
│       │   └── unit/                           # JUnit/TestNG unit tests
│       └── resources/
│           ├── config/                         # QA/stage property files
│           ├── features/                       # Cucumber feature files
│           │   ├── account-management/
│           │   ├── public-site/
│           │   └── trading-order-lifecycle/
│           ├── karate/                         # Karate feature files and config
│           ├── mock-web-app/                   # Local mock UI app used by sample Selenium tests
│           ├── schemas/                        # JSON schemas for API validation
│           ├── testdata/                       # JSON test data
│           └── testng-suites/                  # TestNG XML suites
├── Jenkinsfile                                 # Jenkins/CloudBees declarative pipeline
├── pom.xml                                     # Maven build and dependency configuration
├── build.gradle                                # Gradle build alternative
├── .gitignore
└── README.md
```

---

## 3. Test Coverage Summary

| Area | Files | What Is Tested |
|---|---|---|
| Selenium UI | `LoginPage`, `OrderLifecyclePage`, `OrderLifecycleSteps` | Login, catalogue item selection, cart, checkout, payment, confirmation |
| Cucumber BDD | `OrderE2E.feature`, `DtccPublicWebsite.feature` | Business-readable scenarios using Given/When/Then syntax |
| Page Object Model | `src/main/java/.../pages` | Reusable page classes that separate locators/actions from test logic |
| Rest Assured API | `BackendTransactionTest`, `DataDrivenTransactionTest` | Status code, JSON body, response time, schema, business fields |
| Karate API | `clearing-api-test.feature`, `KarateApiRunner` | BDD-style API contract validation |
| Database Testing | `DatabaseClient`, API tests | SQL/JDBC persistence validation using safe mock data |
| Unit Testing | `OrderCalculatorTest` | Functional requirement validation against core logic |
| Public Website | `DtccPublicSiteTemplateTest`, `DtccSitemapFullCoverageTest` | DTCC.com home/template rendering and sitemap HTTP validation |
| Reporting | Surefire, Cucumber JSON/HTML, screenshot listener | CI-friendly output and failed-test evidence |
| CI/CD | `Jenkinsfile`, GitHub Actions workflow | Automated suite execution and report publication |

---

## 4. How to Run

### Prerequisites

- Java 17+
- Maven 3.9+
- Chrome or Edge installed
- Git

### Compile

```bash
mvn clean compile test-compile -DskipTests
```

### Run Unit Tests

```bash
mvn test -Dtest=OrderCalculatorTest
```

### Run Selenium + Cucumber UI Tests

```bash
mvn test -Pui -Dheadless=true -Dbrowser=chrome
```

### Run REST Assured API Tests

```bash
mvn test -Papi -Dtest=BackendTransactionTest
```

### Run Karate API Tests

```bash
mvn test -Dtest=KarateApiRunner -Dkarate.env=qa
```

### Run DTCC.com Public Website Selenium Template Tests

```bash
mvn test -Dtest=DtccPublicSiteTemplateTest -Dheadless=true
```

### Run DTCC.com Sitemap Coverage

```bash
mvn test -Dtest=DtccSitemapFullCoverageTest -Ddtcc.sitemap.maxPages=25
```

To validate every XML sitemap URL, set max pages to `0`:

```bash
mvn test -Dtest=DtccSitemapFullCoverageTest -Ddtcc.sitemap.maxPages=0
```

### Run DTCC Public Website Suite

```bash
mvn test -Ppublic-site
```

---

## 5. Code Samples

### Selenium Page Object Model Sample

```java
public class DtccHomePage extends PublicSiteBasePage {
    private static final String HOME_URL = "https://www.dtcc.com/";

    public void openHomePage() {
        open(HOME_URL);
    }

    public boolean hasPrimaryNavigation() {
        return driver.findElements(By.cssSelector("nav, header, .header, #header")).size() > 0;
    }
}
```

### TestNG Selenium Test Sample

```java
@Test
public void validateHomePageCoreTemplate() {
    DtccHomePage home = new DtccHomePage(driver);
    home.openHomePage();
    Assert.assertTrue(home.hasVisibleBody());
    Assert.assertTrue(home.hasDtccBranding());
    Assert.assertTrue(home.hasPrimaryNavigation());
}
```

### Cucumber BDD Sample

```gherkin
Feature: DTCC public website page coverage

  Scenario: Validate the DTCC home page loads with basic navigation
    Given I open the DTCC public home page
    Then the DTCC page should load successfully
    And the page should show public site navigation
```

### Rest Assured API Sample

```java
Response response = given()
    .header("Authorization", "Bearer mock-token")
    .contentType(ContentType.JSON)
    .body(transactionPayload)
.when()
    .post("/v1/clearing/orders")
.then()
    .statusCode(201)
    .contentType(ContentType.JSON)
    .body("status", equalTo("PROCESSED"))
    .extract().response();
```

### Sitemap HTTP Coverage Sample

```java
DtccSitemapClient client = new DtccSitemapClient("https://www.dtcc.com/sitemap.xml");
List<String> urls = client.fetchUrls(25);
for (String url : urls) {
    PageProbeResult result = client.probe(url);
    Assert.assertTrue(result.isSuccessful(), url + " failed with " + result.getStatusCode());
}
```

### Jenkins / CloudBees Pipeline Sample

```groovy
pipeline {
    agent any
    tools { maven 'Maven_3.9_Latest'; jdk 'OpenJDK_17_Latest' }
    parameters {
        choice(name: 'SUITE_TARGET', choices: ['All', 'UI-E2E', 'API-Backend', 'DTCC-Public-Site'])
    }
    stages {
        stage('Compile') { steps { sh 'mvn clean compile test-compile -DskipTests' } }
        stage('Test') {
            steps {
                script {
                    if (params.SUITE_TARGET == 'DTCC-Public-Site') {
                        sh 'mvn test -Ppublic-site -Dheadless=true'
                    } else {
                        sh 'mvn test -Pall -Dheadless=true'
                    }
                }
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/**/*.png,target/**/*.html,target/**/*.json'
        }
    }
}
```

---

## 6. Why Sitemap-Driven Website Coverage Is Used

For a public enterprise website, an SDET should not manually write one Selenium test per URL. That creates duplicate test logic and high maintenance cost. This framework uses a better strategy:

1. **Sitemap HTTP tests** validate broad page availability across many URLs.
2. **Selenium template tests** validate representative high-value page structures.
3. **Cucumber scenarios** describe expected public website behavior in business-readable language.
4. **CI/CD pipelines** run smoke tests on pull requests and broader suites on scheduled builds.

This is a practical SDET design because it balances coverage, runtime, maintainability, and risk.

---

## 7. Publishing Notes

Before publishing to GitHub:

- Do not add real credentials, private API tokens, or internal URLs.
- Keep `.env`, `target/`, screenshots, and test-output folders ignored.
- Keep test data fictional.
- Include this disclaimer: independent portfolio project, not affiliated with DTCC.

## Test Case Folder Structure Added for Reviewer Navigation

The `12_Test_Cases/` folder is intentionally organized by test type, while the executable best-practice Maven structure remains under `src/test/java` and `src/test/resources`.

```text
12_Test_Cases/
|-- API_Tests/
|   |-- ApiTransactionTestExample.java
|   `-- README.md
|-- Login_Tests/
|   |-- LoginTestExample.java
|   `-- README.md
|-- Cucumber_Tests/
|   |-- OrderE2E.feature
|   `-- README.md
|-- Selenium_Tests/
|   |-- SeleniumOrderLifecycleTestExample.java
|   `-- README.md
|-- CI_CD_Tests/
|   |-- PipelineValidationExample.groovy
|   `-- README.md
|-- Regression_Tests/
|   |-- RegressionSmokeTestExample.java
|   `-- README.md
|-- JUnit_Tests/
|   |-- OrderCalculatorJUnitExample.java
|   `-- README.md
|-- SQL_Database_Tests/
|   |-- settlement_orders_test_data.sql
|   `-- README.md
`-- Unit_Tests/
    |-- OrderCalculatorUnitTestExample.java
    `-- README.md
```

Each folder contains a code file and README so the project is easy to review on GitHub. The real executable source files are placed in the optimized Maven layout:

```text
src/test/java/com/dtcc/automation/api/             REST Assured API tests
src/test/java/com/dtcc/automation/ui/              Selenium login and order workflow tests
src/test/java/com/dtcc/automation/database/        H2 SQL database validation tests
src/test/java/com/dtcc/automation/cicd/            Jenkins/GitHub Actions configuration checks
src/test/java/com/dtcc/automation/regression/      Regression smoke tests
src/test/java/com/dtcc/automation/unit/            JUnit and TestNG unit tests
src/test/resources/features/                       Cucumber BDD feature files
src/test/resources/sql/                            SQL schema, seed data, and validation queries
src/test/resources/testng-suites/                  TestNG suite XML files
```

## Test Reports and Pass/Fail Evidence

Runtime reports are created when tests run:

```text
target/surefire-reports/          TestNG/JUnit XML and text reports
target/cucumber-reports/          Cucumber HTML/JSON BDD reports
target/failed-test-screenshots/   Screenshots captured by TestExecutionListener on failures
```

Static sample reports are also included for GitHub review:

```text
13_Reports/
|-- Sample-Test-Execution-Summary.md
|-- sample-testng-results.xml
|-- sample-cucumber-report.json
|-- sample-failed-test-log.txt
`-- ReportArtifactReader.java
```

## SQL Database Tests Without Enterprise DB Access

Because reviewers will not have access to private enterprise databases, the project includes H2 in-memory SQL database examples:

```text
src/test/resources/sql/schema.sql
src/test/resources/sql/data.sql
src/test/resources/sql/validation-queries.sql
src/test/java/com/dtcc/automation/database/SqlDatabaseValidationTest.java
```

This lets the project demonstrate the same SDET pattern used for real Oracle/DB2/Snowflake validation: create schema, load test data, execute SQL queries, and assert that backend state matches expected application behavior.

## Main Commands

```bash
# Run the organized showcase suite with API, login, Selenium, Cucumber, CI/CD, regression, SQL, and unit tests
mvn test -Ptestcases

# Run API-only tests
mvn test -Papi

# Run UI/Selenium tests
mvn test -Pui

# Run public DTCC.com sitemap/template tests
mvn test -Ppublic-site

# Run a specific SQL database test
mvn test -Dtest=SqlDatabaseValidationTest test

# Run CI/CD configuration tests
mvn test -Dtest=CiCdConfigurationTest test
```


---

## 8. Additional Tests Added for a More Complete SDET Portfolio

In addition to the original API, login, Selenium, Cucumber, CI/CD, regression, JUnit, SQL database, and unit tests, this project now includes these additional test categories:

| Test Type | Folder | Executable Source | Purpose |
|---|---|---|---|
| Security Header Tests | `12_Test_Cases/Security_Tests/` | `src/test/java/com/dtcc/automation/security/` | Validates HTTPS/security response headers such as HSTS and MIME-sniffing protection. |
| Accessibility Smoke Tests | `12_Test_Cases/Accessibility_Tests/` | `src/test/java/com/dtcc/automation/accessibility/` | Verifies basic field labeling and accessibility-ready UI behavior. |
| Performance Smoke Tests | `12_Test_Cases/Performance_Tests/` | `src/test/java/com/dtcc/automation/performance/` | Checks public-page response time against a basic threshold. |
| Broken Link Tests | `12_Test_Cases/Broken_Link_Tests/` | `src/test/java/com/dtcc/automation/links/` | Checks representative public links for HTTP 4xx/5xx errors. |
| PDF Download Tests | `12_Test_Cases/PDF_Download_Tests/` | `src/test/java/com/dtcc/automation/pdf/` | Verifies configured PDF resources return valid downloadable content. |
| Data-Driven Tests | `12_Test_Cases/Data_Driven_Tests/` | `src/test/java/com/dtcc/automation/datadriven/` | Uses TestNG `@DataProvider` to validate multiple input combinations. |
| Negative Tests | `12_Test_Cases/Negative_Tests/` | `src/test/java/com/dtcc/automation/negative/` | Confirms invalid inputs are rejected and validation messages appear. |
| Smoke Tests | `12_Test_Cases/Smoke_Tests/` | `src/test/java/com/dtcc/automation/smoke/` | Runs fast release-readiness checks. |
| Visual Smoke Tests | `12_Test_Cases/Visual_Smoke_Tests/` | `src/test/java/com/dtcc/automation/visual/` | Captures screenshot artifacts for visual review. |
| Intentional Failure Examples | `12_Test_Cases/Intentional_Failure_Examples/` | `src/test/java/com/dtcc/automation/reports/` | Generates controlled failed-test examples for report review. |

These additions make the project look more like a mature SDET framework because they cover functional, API, database, CI/CD, non-functional, reporting, and failure-evidence scenarios.

---

## 9. Expanded Run Commands

### Run the Larger Showcase Suite

```bash
mvn test -Pexpanded
```

This runs the expanded suite:

```text
src/test/resources/testng-suites/expanded-showcase-suite.xml
```

### Run Intentional Failure Examples

```bash
mvn test -Pfailure-demo -DincludeIntentionalFailures=true
```

This suite intentionally fails a couple of tests so the repository has realistic failed-test evidence for screenshots, XML reports, and CI review. These tests are isolated from the normal suite so the project does not fail by default.

### Run Specific New Test Types

```bash
mvn test -Dtest=SecurityHeadersTest test
mvn test -Dtest=AccessibilitySmokeTest test
mvn test -Dtest=PageLoadPerformanceTest test
mvn test -Dtest=BrokenLinkValidationTest test
mvn test -Dtest=PdfDownloadValidationTest -Ddtcc.sample.pdf.url="https://example.com/sample.pdf" test
mvn test -Dtest=DataDrivenLoginValidationTest test
mvn test -Dtest=NegativeLoginValidationTest test
mvn test -Dtest=PageTemplateSnapshotTest test
```

---

## 10. Reviewing Reports in a Web UI

Runtime reports are generated by Maven/TestNG/Cucumber in the `target/` folder:

```text
target/surefire-reports/          TestNG/JUnit XML and text reports
target/cucumber-reports/          Cucumber JSON/HTML report artifacts
target/failed-test-screenshots/   Failure screenshots from the TestNG listener
target/visual-snapshots/          Visual smoke screenshots
```

A lightweight static browser dashboard is included for easy review:

```text
13_Reports/web-ui/index.html
```

Open it directly in a browser, or run:

```bash
./scripts/open-report-ui.sh
```

Then browse to:

```text
http://localhost:8080
```

### What Is Typically Used in Real SDET Teams

For enterprise SDET teams, the usual report interfaces are:

- Jenkins / CloudBees build pages
- JUnit/Surefire XML trend reports
- Cucumber HTML reports
- Allure Reports
- Extent Reports
- GitHub Actions artifacts
- Jenkins HTML Publisher Plugin

This project includes both live-runtime report locations and static sample reports showing passed and failed tests.

---

## 11. Note About Running Tests in This Generated Package

Maven and Gradle were not installed in the ChatGPT sandbox where this ZIP was assembled, so Java/Maven execution could not be performed here. The repository includes runnable Maven/TestNG commands and static sample reports. To generate live reports, install Java 17+ and Maven 3.9+, then run:

```bash
mvn test -Pexpanded
mvn test -Pfailure-demo -DincludeIntentionalFailures=true
```

## Selenide - Playwright-Style Auto-Waiting for Java/Selenium

Selenide was added as a modern UI automation layer on top of Selenium WebDriver. It does not replace Selenium; it wraps Selenium to reduce boilerplate and flakiness.

### Why Add Selenide?

In high-volume enterprise web applications, the most common cause of flaky UI tests is timing mismatch: AJAX updates, dynamic elements, loading overlays, JavaScript redraws, and stale DOM references. Raw Selenium solves this with explicit waits, but every engineer must know where to place `WebDriverWait`, which `ExpectedConditions` to use, and how much timeout margin to apply.

Selenide embeds waiting into every element interaction and assertion. This creates a Playwright-like experience in Java while keeping Selenium Grid and browser compatibility.

### Benefits

- Automatic browser management and cleanup
- Built-in smart waits for visibility, clickability, text, state, and assertions
- Concise jQuery-style syntax using `$()` and `$$()`
- Automatic screenshots and page source capture on failure
- Cleaner Page Objects with fewer custom wait wrappers
- Better handling of AJAX and asynchronous rendering

### Maven Dependency

```xml
<dependency>
    <groupId>com.codeborne</groupId>
    <artifactId>selenide</artifactId>
    <version>7.3.0</version>
    <scope>test</scope>
</dependency>
```

### Selenium vs. Selenide

| Feature | Raw Selenium WebDriver | Selenide Wrapper |
|---|---|---|
| Finding an element | `driver.findElement(By.id("submit"));` | `$("#submit");` |
| Waiting and clicking | `new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("btn"))).click();` | `$("#btn").click();` |
| Checking text | `assertTrue(driver.findElement(By.id("status")).getText().contains("Success"));` | `$("#status").shouldHave(text("Success"));` |
| Closing browser | `driver.quit();` | `closeWebDriver();` |

### Selenide Implementation Example

```java
package com.dtcc.automation.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideLoginPage {
    private final SelenideElement txtUsername = $("#username");
    private final SelenideElement txtPassword = $("#password");
    private final SelenideElement btnLogin = $("#loginBtn");
    private final SelenideElement lblDashboard = $(".dashboard-title");

    public void login(String user, String pass) {
        txtUsername.setValue(user);
        txtPassword.setValue(pass);
        btnLogin.click();
    }

    public void verifyDashboardIsVisible() {
        lblDashboard.shouldBe(visible).shouldHave(text("Dashboard"));
    }
}
```

### Selenide Run Commands

Run the full Selenide suite:

```bash
mvn test -Pselenide
```

Run individual Selenide tests:

```bash
mvn test -Dtest=SelenideLoginValidationTest
mvn test -Dtest=SelenideOrderLifecycleTest
mvn test -Dtest=SelenidePublicSiteSmokeTest
```

### Selenide Files Added

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
```

## DTCC.com Public Website Automation Priority Notes

When automating `dtcc.com`, do not attempt to hand-code one brittle Selenium test per URL. Use a sitemap-driven coverage model for broad HTTP/page availability and POM/Selenide tests for high-value templates and data-heavy pages.

Priority areas:

- Client Center and Important Notices should be automated first because they are data-heavy and prone to regression.
- PDF-heavy sections should be validated with a PDF utility such as Apache PDFBox to confirm download status, file type, and expected notice/report text.
- Dynamic table-heavy sections such as Global Trade Repository pages require strong synchronization. Prefer Selenide auto-waiting for UI interactions and keep robust FluentWait utilities in the raw Selenium `BasePage` for specialized dynamic-table checks.

---

## GitHub Actions Dashboard + Allure Web Report UI

This project is now configured so GitHub can act as the cloud UI for running Selenium, Selenide, Cucumber, TestNG, API, SQL database, and regression tests.

### Primary GitHub workflow

```text
.github/workflows/selenium.yml
```

The workflow runs when you push to `main`, open a pull request, or manually click **Run workflow** in the GitHub Actions tab. It performs the following steps:

- Checks out the repository
- Installs Java JDK 17
- Installs stable Google Chrome for headless Selenium/Selenide execution
- Runs the selected Maven test profile
- Saves Surefire/TestNG and Allure raw results as workflow artifacts
- Generates an Allure HTML dashboard
- Publishes the dashboard to GitHub Pages through the `gh-pages` branch

### Run tests from the GitHub web UI

1. Push the repository to GitHub.
2. Open the repository in GitHub.
3. Go to **Actions**.
4. Select **Selenium CI/CD Pipeline with Allure Reports**.
5. Click **Run workflow**.
6. Choose a profile:
   - `ui` for Selenium UI regression
   - `selenide` for Selenide UI tests
   - `api` for Rest Assured API tests
   - `testcases` for grouped showcase tests
   - `expanded` for the larger showcase suite
   - `public-site` for DTCC.com public-site coverage
   - `failure-demo` for intentional failing examples
7. Open the workflow run to review stage logs, execution time, and raw report artifacts.
8. Open the GitHub Pages Allure dashboard after deployment completes.

### Enable the Allure report dashboard through GitHub Pages

1. Go to **Settings > Pages** in the GitHub repository.
2. Select **Deploy from a branch**.
3. Choose branch: `gh-pages`.
4. Choose folder: `/root`.
5. Save.
6. Run the workflow again.
7. GitHub Pages will publish the interactive Allure report.

### Run Allure-enabled tests locally

```bash
mvn clean test -Pallure-ui -Dallure.results.directory=target/allure-results
mvn allure:report
open target/site/allure-report/index.html
```

Windows PowerShell:

```powershell
mvn clean test -Pallure-ui -Dallure.results.directory=target/allure-results
mvn allure:report
start target/site/allure-report/index.html
```

### Generate example failed tests for reports

Normal CI runs should not intentionally fail. To demonstrate defect handling in Allure, run:

```bash
mvn clean test -Pallure-ui -DincludeIntentionalFailures=true -Dallure.results.directory=target/allure-results
```

This creates controlled failing examples so the report shows failed test status, failure messages, stack traces, and attachments.

### Allure Maven setup

The project includes the Allure TestNG adapter and Allure Maven plugin in `pom.xml`:

```xml
<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-testng</artifactId>
    <version>2.13.9</version>
    <scope>test</scope>
</dependency>
```

The Allure-enabled example test is located here:

```text
src/test/java/com/dtcc/automation/reports/AllureEnabledUiReportTest.java
```

It demonstrates:

- `@Epic`, `@Feature`, `@Description`, `@Severity`, and `@Owner`
- `@Step` methods for readable report flow
- screenshot attachments
- execution log attachments
- page-source attachments for failure triage

### New project folders for reporting UI

```text
18_GitHub_Actions_Allure_Report_UI/
|-- README.md
|-- selenium.yml
`-- AllureEnabledUiReportTest.java

13_Reports/
|-- Allure-Report-UI-Guide.md
`-- allure-sample-results/
    |-- sample-passed-result.json
    `-- sample-failed-result.json
```

### Typical Web UI reporting stack used in Java Selenium projects

- **GitHub Actions Dashboard**: run history, stages, logs, timing, and artifact downloads.
- **Allure Reports**: interactive pass/fail dashboard, steps, screenshots, defects, and trends.
- **GitHub Pages**: public or private static hosting for the generated Allure dashboard.
- **Surefire/TestNG XML**: raw machine-readable test results used by CI systems.
- **Jenkins/CloudBees**: common enterprise alternative for internal dashboards and report publishing.
