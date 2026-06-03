package com.dtcc.automation.selenide;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

import static com.codeborne.selenide.Selenide.closeWebDriver;

/**
 * Shared Selenide base setup. Browser lifecycle is simpler than raw Selenium driver management.
 */
public class SelenideTestBase {
    protected String mockAppUrl;

    @BeforeMethod(alwaysRun = true)
    public void configureSelenide() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        Configuration.timeout = Long.parseLong(System.getProperty("selenide.timeout", "6000"));
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.reportsFolder = "target/selenide-reports";

        File mockApp = new File("src/test/resources/mock-web-app/order-lifecycle.html");
        mockAppUrl = mockApp.toURI().toString();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        closeWebDriver();
    }
}
