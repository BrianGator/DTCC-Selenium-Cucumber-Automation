package com.dtcc.automation.selenide;

import com.dtcc.automation.selenide.pages.SelenidePublicHomePage;
import org.testng.annotations.Test;

/**
 * DTCC.com public website smoke test using Selenide.
 * This complements sitemap HTTP checks and raw Selenium template tests.
 */
public class SelenidePublicSiteSmokeTest extends SelenideTestBase {

    @Test(groups = {"selenide", "public-site", "smoke"})
    public void dtccHomePageLoadsWithBrandingAndNavigation() {
        SelenidePublicHomePage home = new SelenidePublicHomePage();
        home.openHomePage();
        home.verifyCoreTemplateLoaded();
        home.verifySearchOrNavigationAvailable();
    }
}
