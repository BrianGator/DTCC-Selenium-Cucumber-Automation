package com.dtcc.automation.utils;

import com.dtcc.automation.constants.FrameworkConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestExecutionListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = WebDriverFactory.getDriver();
        if (driver == null) return;
        try {
            Files.createDirectories(Path.of(FrameworkConstants.SCREENSHOT_DIR));
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = "FAILED_" + result.getName() + "_" + timestamp + ".png";
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(source.toPath(), Path.of(FrameworkConstants.SCREENSHOT_DIR, fileName));
        } catch (Exception e) {
            System.err.println("Unable to capture screenshot: " + e.getMessage());
        }
    }
}
