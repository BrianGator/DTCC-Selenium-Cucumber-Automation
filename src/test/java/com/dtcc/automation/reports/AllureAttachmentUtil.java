package com.dtcc.automation.reports;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class AllureAttachmentUtil {
    private AllureAttachmentUtil() {}

    @Attachment(value = "Browser Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        if (driver == null) {
            return new byte[0];
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Execution Log", type = "text/plain")
    public static String attachLog(String message) {
        return message;
    }

    @Attachment(value = "HTML Source", type = "text/html")
    public static String attachPageSource(WebDriver driver) {
        if (driver == null) {
            return "No active WebDriver session was available.";
        }
        return driver.getPageSource();
    }
}
