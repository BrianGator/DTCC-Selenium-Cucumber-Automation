package examples.frameworkscratch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class MinimalFrameworkFromScratch {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
        DRIVER.set(new ChromeDriver(options));
    }

    protected WebDriver driver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) throw new IllegalStateException("Driver was not initialized");
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        WebDriver driver = DRIVER.get();
        if (driver != null) driver.quit();
        DRIVER.remove();
    }
}
