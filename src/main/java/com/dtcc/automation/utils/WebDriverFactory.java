package com.dtcc.automation.utils;

import com.dtcc.automation.config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class WebDriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    private WebDriverFactory() {}

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", ConfigManager.get("browser"));
        boolean headless = ConfigManager.getBoolean("headless", true);
        WebDriver driver;
        if ("edge".equalsIgnoreCase(browser)) {
            EdgeOptions options = new EdgeOptions();
            if (headless) options.addArguments("--headless=new");
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
            driver = new EdgeDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            if (headless) options.addArguments("--headless=new");
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
            driver = new ChromeDriver(options);
        }
        DRIVER_POOL.set(driver);
        return driver;
    }

    public static WebDriver getDriver() {
        return DRIVER_POOL.get();
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER_POOL.get();
        if (driver != null) {
            driver.quit();
            DRIVER_POOL.remove();
        }
    }
}
