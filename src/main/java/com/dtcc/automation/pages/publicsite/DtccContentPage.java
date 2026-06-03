package com.dtcc.automation.pages.publicsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DtccContentPage extends PublicSiteBasePage {
    public DtccContentPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasHeadingOrContentLandmark() {
        return driver.findElements(By.cssSelector("h1, h2, main, article, section")).size() > 0;
    }

    public boolean hasFooter() {
        return driver.findElements(By.cssSelector("footer, .footer, #footer")).size() > 0;
    }

    public boolean hasNoObviousServerErrorText() {
        String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase();
        return !(bodyText.contains("server error") || bodyText.contains("application error") || bodyText.contains("stack trace"));
    }
}
