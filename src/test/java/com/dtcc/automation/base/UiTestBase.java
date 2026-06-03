package com.dtcc.automation.base;

import com.dtcc.automation.constants.FrameworkConstants;
import com.dtcc.automation.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;

import java.nio.file.Path;

public class UiTestBase {
    protected WebDriver openMockOrderApplication() {
        WebDriver driver = WebDriverFactory.createDriver();
        String fileUrl = Path.of(FrameworkConstants.MOCK_APP_PATH).toAbsolutePath().toUri().toString();
        driver.get(fileUrl);
        return driver;
    }
}
