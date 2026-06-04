package com.dtcc.automation.reports;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

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

    public static void attachText(String name, String message) {
        Allure.addAttachment(name, "text/plain", new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)), ".txt");
    }

    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)), ".json");
    }

    @Attachment(value = "HTML Source", type = "text/html")
    public static String attachPageSource(WebDriver driver) {
        if (driver == null) {
            return "No active WebDriver session was available.";
        }
        return driver.getPageSource();
    }
}
