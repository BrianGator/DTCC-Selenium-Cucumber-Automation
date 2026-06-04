package examples.defects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefectScreenshotCaptureListener implements ITestListener {
    private final WebDriver driver;

    public DefectScreenshotCaptureListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver == null) return;
        try {
            byte[] image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path output = Path.of("target", "failed-test-screenshots", result.getName() + "_" + timestamp + ".png");
            Files.createDirectories(output.getParent());
            Files.write(output, image);
        } catch (Exception ignored) {
            // Listener must never hide the original test failure.
        }
    }
}
