# 17 - Selenide: The Playwright Experience for Java/Selenium

Selenide is an open-source Java automation framework built on top of Selenium WebDriver. It does not replace Selenium; it wraps Selenium and removes much of the boilerplate around driver setup, waits, element lookup, assertions, screenshots, and asynchronous rendering.

## Why This Matters for SDET Interviews

Raw Selenium often fails because the page is still rendering, an AJAX request is still updating the DOM, or an element becomes stale between lookup and click. Traditional Selenium solves this with `WebDriverWait`, `ExpectedConditions`, and custom wrapper classes. That works, but it creates a lot of repeated code and depends on every engineer remembering where to put waits.

Selenide embeds automatic waiting into every element interaction and assertion. This gives Java/Selenium teams a Playwright-like developer experience while keeping Selenium Grid compatibility.

## Key Benefits

- Automatic browser management: opens, configures, and closes browsers with less manual driver setup.
- Smart waiting: automatically waits for elements to appear, become visible, become enabled, or satisfy an assertion.
- Concise syntax: replaces long Selenium locators with jQuery-style `$()` and `$$()` commands.
- Built-in screenshots: captures screenshots on failure for faster defect triage.
- Better AJAX handling: retries element lookup and conditions while the DOM is changing.
- Cleaner Page Objects: reduces WebDriver, WebElement, WebDriverWait, and ExpectedConditions boilerplate.

## Selenium vs. Selenide Syntax

| Action | Raw Selenium WebDriver | Selenide |
|---|---|---|
| Find element | `driver.findElement(By.id("submit"))` | `$("#submit")` |
| Wait and click | `new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("btn"))).click();` | `$("#btn").click();` |
| Check text | `assertTrue(driver.findElement(By.id("status")).getText().contains("Success"));` | `$("#status").shouldHave(text("Success"));` |
| Close browser | `driver.quit();` | `closeWebDriver();` |

## Maven Dependency

```xml
<dependency>
    <groupId>com.codeborne</groupId>
    <artifactId>selenide</artifactId>
    <version>7.3.0</version>
    <scope>test</scope>
</dependency>
```

## How to Run the Selenide Suite

```bash
mvn test -Pselenide
```

Run a single Selenide test class:

```bash
mvn test -Dtest=SelenideOrderLifecycleTest
```

## Selenide Page Object Example

```java
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
    lblDashboard.shouldBe(visible);
}
```

## DTCC.com Automation Priorities

- Start by automating Client Center and Important Notices because they are data-heavy and prone to regression.
- Use PDFBox or a similar PDF utility for DTCC.com PDF download validation and text verification.
- Use Selenide auto-waiting for public website templates and use FluentWait/custom waits only where raw Selenium tests must validate dynamic tables such as Global Trade Repository pages.
