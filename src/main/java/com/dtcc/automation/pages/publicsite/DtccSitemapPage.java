package com.dtcc.automation.pages.publicsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class DtccSitemapPage extends PublicSiteBasePage {
    private static final String SITEMAP_URL = "https://www.dtcc.com/sitemap";

    public DtccSitemapPage(WebDriver driver) {
        super(driver);
    }

    public void openSitemap() {
        open(SITEMAP_URL);
    }

    public List<String> collectInternalLinks() {
        List<String> urls = new ArrayList<>();
        for (WebElement link : links()) {
            String href = link.getAttribute("href");
            if (href != null && href.startsWith("https://www.dtcc.com/")) {
                urls.add(href);
            }
        }
        return urls;
    }
}
