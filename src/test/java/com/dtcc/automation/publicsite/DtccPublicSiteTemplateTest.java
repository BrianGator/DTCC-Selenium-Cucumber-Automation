package com.dtcc.automation.publicsite;

import com.dtcc.automation.pages.publicsite.DtccContentPage;
import com.dtcc.automation.pages.publicsite.DtccHomePage;
import com.dtcc.automation.pages.publicsite.DtccSitemapPage;
import com.dtcc.automation.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class DtccPublicSiteTemplateTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
        }
        driver = new ChromeDriver(options);
        WebDriverFactory.setDriver(driver);
    }

    @Test(description = "Validate DTCC home page loads with branding, navigation, search, and visible page body")
    public void validateHomePageCoreTemplate() {
        DtccHomePage home = new DtccHomePage(driver);
        home.openHomePage();
        Assert.assertTrue(home.hasVisibleBody(), "Home page body should be visible.");
        Assert.assertTrue(home.hasDtccBranding(), "Home page should include DTCC branding.");
        Assert.assertTrue(home.hasPrimaryNavigation(), "Home page should expose navigation/header elements.");
        Assert.assertTrue(home.visibleLinkCount() > 10, "Home page should expose multiple navigation/content links.");
    }

    @Test(description = "Validate DTCC sitemap page exposes crawlable internal links")
    public void validateSitemapPageContainsInternalLinks() {
        DtccSitemapPage sitemap = new DtccSitemapPage(driver);
        sitemap.openSitemap();
        Assert.assertTrue(sitemap.hasVisibleBody(), "Sitemap body should be visible.");
        Assert.assertTrue(sitemap.collectInternalLinks().size() > 20, "Sitemap should expose many internal DTCC links.");
    }

    @DataProvider(name = "representativePublicPages")
    public Object[][] representativePublicPages() {
        return new Object[][]{
                {"https://www.dtcc.com/about", "About"},
                {"https://www.dtcc.com/client-center", "Client Center"},
                {"https://www.dtcc.com/news", "News"},
                {"https://www.dtcc.com/legal", "Legal"},
                {"https://www.dtcc.com/products", "Products"}
        };
    }

    @Test(dataProvider = "representativePublicPages", description = "Validate high-value public DTCC templates")
    public void validateRepresentativeContentTemplates(String url, String expectedText) {
        DtccContentPage content = new DtccContentPage(driver);
        content.open(url);
        Assert.assertTrue(content.hasVisibleBody(), "Body should be visible for " + url);
        Assert.assertTrue(content.hasHeadingOrContentLandmark(), "Content landmark or heading should exist for " + url);
        Assert.assertTrue(content.hasNoObviousServerErrorText(), "No obvious server error should appear for " + url);
        Assert.assertTrue(content.visibleLinkCount() > 5, "Page should expose useful internal links for " + url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            WebDriverFactory.removeDriver();
        }
    }
}
