package com.dtcc.automation.utils;

import com.dtcc.automation.constants.FrameworkConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestExecutionListener implements ITestListener, ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        writeAllureMetadata();
    }

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

    private void writeAllureMetadata() {
        String resultsDir = System.getProperty("allure.results.directory", "target/allure-results");
        Path allureResults = Path.of(resultsDir);
        try {
            Files.createDirectories(allureResults);
            Files.writeString(allureResults.resolve("environment.properties"), String.join(System.lineSeparator(),
                    "Website=https://www.dtcc.com/",
                    "Browser=" + System.getProperty("browser", "chrome"),
                    "Headless=" + System.getProperty("headless", "true"),
                    "Environment=" + System.getProperty("env", "qa"),
                    "Data_Source=Mock UI, WireMock REST API, H2 database",
                    "Intentional_Failures=" + System.getProperty("includeIntentionalFailures", "false")
            ), StandardCharsets.UTF_8);
            Files.writeString(allureResults.resolve("executor.json"), """
                    {
                      "name": "Local or GitHub Actions Maven",
                      "type": "maven",
                      "buildName": "DTCC Selenium Cucumber Automation",
                      "reportUrl": "https://briangator.github.io/DTCC-Selenium-Cucumber-Automation/allure/index.html"
                    }
                    """, StandardCharsets.UTF_8);
            Files.writeString(allureResults.resolve("categories.json"), """
                    [
                      {
                        "name": "Intentional demo skipped by default",
                        "matchedStatuses": ["skipped"],
                        "messageRegex": ".*Intentional failure demo skipped.*|.*Intentional failure demo skipped by design.*"
                      },
                      {
                        "name": "Intentional demo failures",
                        "matchedStatuses": ["failed"],
                        "messageRegex": ".*Intentional.*"
                      },
                      {
                        "name": "Security validation failures",
                        "matchedStatuses": ["failed", "broken"],
                        "messageRegex": ".*security.*|.*authorization.*|.*script.*|.*SQL.*"
                      },
                      {
                        "name": "UI workflow failures",
                        "matchedStatuses": ["failed", "broken"],
                        "messageRegex": ".*login.*|.*catalog.*|.*cart.*|.*checkout.*|.*payment.*"
                      },
                      {
                        "name": "REST API failures",
                        "matchedStatuses": ["failed", "broken"],
                        "messageRegex": ".*REST.*|.*endpoint.*|.*statusCode.*"
                      }
                    ]
                    """, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Unable to write Allure metadata: " + e.getMessage());
        }
    }
}
