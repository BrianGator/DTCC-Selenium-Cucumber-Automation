package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class StandaloneSeleniumSmokeTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
        }
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void dtccHomePageShouldLoadWithNavigation() {
        driver.get("https://www.dtcc.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("dtcc") || driver.getCurrentUrl().contains("dtcc.com"));
        Assert.assertTrue(driver.findElements(By.cssSelector("a[href]")).size() > 10, "Expected public navigation links.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
