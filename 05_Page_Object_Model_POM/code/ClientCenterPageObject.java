package examples.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientCenterPageObject {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By body = By.tagName("body");
    private final By links = By.cssSelector("a[href]");

    public ClientCenterPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.dtcc.com/client-center");
        wait.until(ExpectedConditions.presenceOfElementLocated(body));
    }

    public boolean pageLoaded() {
        return driver.getCurrentUrl().contains("dtcc.com") && driver.findElement(body).isDisplayed();
    }

    public int publicLinkCount() {
        return driver.findElements(links).size();
    }

    public boolean hasExpectedClientCenterContent() {
        String text = driver.findElement(body).getText().toLowerCase();
        return text.contains("client") || text.contains("support") || text.contains("documentation");
    }
}
