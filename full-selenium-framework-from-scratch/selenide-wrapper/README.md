# Selenide Wrapper Layer for the Full Selenium Framework From Scratch

This folder adds a Selenide implementation to the lower-level Selenium framework-from-scratch example.

Selenide does not replace Selenium WebDriver. It wraps Selenium and keeps Selenium Grid, Chrome, Firefox, remote execution, and CI/CD compatibility while reducing boilerplate around driver lookup, waiting, stale element handling, and assertions.

## Why add Selenide to a raw Selenium framework?

A traditional Selenium framework usually includes:

- WebDriverFactory
- BaseTest
- BasePage
- WebDriverWait utilities
- Page Object Model classes
- TestNG or JUnit tests
- Screenshot listeners

That is useful for learning how Selenium works internally. However, production UI automation often becomes flaky because every engineer must manually decide where to use explicit waits. Selenide moves that wait logic into the element interaction itself.

## Selenium vs. Selenide

| Task | Raw Selenium | Selenide |
|---|---|---|
| Find element | `driver.findElement(By.id("loginBtn"))` | `$("#loginBtn")` |
| Type text | `element.sendKeys("user")` | `$("#username").setValue("user")` |
| Click with wait | `wait.until(ExpectedConditions.elementToBeClickable(locator)).click()` | `$("#loginBtn").click()` |
| Assert text | `Assert.assertTrue(element.getText().contains("SUCCESS"))` | `$("#status").shouldHave(text("SUCCESS"))` |
| Failure evidence | Custom listener required | Screenshots and page source captured automatically |

## Maven dependency

The main project `pom.xml` already includes Selenide. For a standalone from-scratch project, add:

```xml
<dependency>
    <groupId>com.codeborne</groupId>
    <artifactId>selenide</artifactId>
    <version>7.3.0</version>
    <scope>test</scope>
</dependency>
```

## Recommended package layout

```text
full-selenium-framework-from-scratch/
└── selenide-wrapper/
    ├── README.md
    ├── pom-selenide-snippet.xml
    └── src/test/java/examples/fromscratch/selenide/
        ├── SelenideFromScratchBaseTest.java
        ├── SelenideFromScratchLoginPage.java
        └── SelenideFromScratchLoginTest.java
```

## How to run the real project Selenide suite

From the repository root:

```bash
mvn test -Pselenide -Dbrowser=chrome -Dheadless=true
```

For Allure output:

```bash
mvn clean test -Pselenide -Dbrowser=chrome -Dheadless=true -Dallure.results.directory=target/allure-results
mvn allure:report
```

## Interview talking point

The best answer is that you understand both levels:

- Raw Selenium is important because it teaches how WebDriver, waits, locators, driver lifecycle, screenshots, and CI execution work.
- Selenide is useful because it reduces timing-related flakiness, cuts boilerplate, and gives Playwright-style auto-waiting while staying in the Java/Selenium ecosystem.
