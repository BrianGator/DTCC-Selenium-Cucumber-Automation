package com.dtcc.automation.base;

import com.dtcc.automation.constants.FrameworkConstants;
import com.dtcc.automation.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Path;

public class UiTestBase {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUpUiDriver() {
        driver = WebDriverFactory.createDriver();
    }

    protected WebDriver openMockOrderApplication() {
        if (driver == null) {
            driver = WebDriverFactory.createDriver();
        }
        driver.get(mockAppUrl());
        return driver;
    }

    protected String mockAppUrl() {
        return Path.of(FrameworkConstants.MOCK_APP_PATH).toAbsolutePath().toUri().toString();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownUiDriver() {
        WebDriverFactory.quitDriver();
        driver = null;
    }
}
