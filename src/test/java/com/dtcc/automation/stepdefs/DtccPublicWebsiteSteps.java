package com.dtcc.automation.stepdefs;

import com.dtcc.automation.pages.publicsite.DtccContentPage;
import com.dtcc.automation.pages.publicsite.DtccHomePage;
import com.dtcc.automation.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class DtccPublicWebsiteSteps {
    private WebDriver driver;
    private DtccHomePage homePage;
    private DtccContentPage contentPage;

    @Before("@publicSite")
    public void beforePublicSiteScenario() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
        driver = new ChromeDriver(options);
        WebDriverFactory.setDriver(driver);
        homePage = new DtccHomePage(driver);
        contentPage = new DtccContentPage(driver);
    }

    @Given("I open the DTCC public home page")
    public void iOpenTheDtccPublicHomePage() {
        homePage.openHomePage();
    }

    @Given("I open the DTCC public page {string}")
    public void iOpenTheDtccPublicPage(String url) {
        contentPage.open(url);
    }

    @Then("the DTCC page should load successfully")
    public void theDtccPageShouldLoadSuccessfully() {
        Assert.assertTrue(contentPage.hasVisibleBody(), "The page body should be visible.");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("dtcc") || driver.getCurrentUrl().contains("dtcc.com"));
    }

    @Then("the page should show public site navigation")
    public void thePageShouldShowPublicSiteNavigation() {
        Assert.assertTrue(homePage.hasPrimaryNavigation(), "Expected visible header or nav elements.");
        Assert.assertTrue(homePage.visibleLinkCount() > 10, "Expected multiple public site links.");
    }

    @Then("the page should contain a visible content area")
    public void thePageShouldContainAVisibleContentArea() {
        Assert.assertTrue(contentPage.hasHeadingOrContentLandmark(), "Expected heading or content landmark.");
        Assert.assertTrue(contentPage.hasNoObviousServerErrorText(), "No obvious server error should appear.");
    }

    @After("@publicSite")
    public void afterPublicSiteScenario() {
        if (driver != null) {
            driver.quit();
            WebDriverFactory.removeDriver();
        }
    }
}
