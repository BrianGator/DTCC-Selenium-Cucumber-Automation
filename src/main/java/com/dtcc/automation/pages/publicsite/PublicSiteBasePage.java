package com.dtcc.automation.pages.publicsite;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class PublicSiteBasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public PublicSiteBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    public void open(String url) {
        driver.get(url);
        waitForDocumentReady();
    }

    public void waitForDocumentReady() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    public String pageTitle() {
        return driver.getTitle();
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean hasVisibleBody() {
        return driver.findElement(By.tagName("body")).isDisplayed();
    }

    public List<WebElement> links() {
        return driver.findElements(By.cssSelector("a[href]"));
    }

    public int visibleLinkCount() {
        int count = 0;
        for (WebElement link : links()) {
            if (link.isDisplayed() && link.getAttribute("href") != null && !link.getAttribute("href").isBlank()) {
                count++;
            }
        }
        return count;
    }

    public boolean containsText(String text) {
        return driver.findElement(By.tagName("body")).getText().contains(text);
    }

    public WebElement waitFor(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
