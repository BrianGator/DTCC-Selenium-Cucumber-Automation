package com.dtcc.automation.pages.publicsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DtccHomePage extends PublicSiteBasePage {
    private static final String HOME_URL = "https://www.dtcc.com/";

    public DtccHomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        open(HOME_URL);
    }

    public boolean hasPrimaryNavigation() {
        return driver.findElements(By.cssSelector("nav, header, .header, #header")).size() > 0;
    }

    public boolean hasSearchCapability() {
        return driver.findElements(By.cssSelector("input[type='search'], input[name*='search'], button[aria-label*='Search'], a[href*='search']")).size() > 0;
    }

    public boolean hasDtccBranding() {
        return pageTitle().toLowerCase().contains("dtcc") || containsText("DTCC");
    }
}
