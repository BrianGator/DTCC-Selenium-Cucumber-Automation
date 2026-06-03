package com.dtcc.automation.smoke;

import com.dtcc.automation.base.UiTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PublicSiteSmokeTest extends UiTestBase {

    @Test
    public void publicHomePageShouldLoadWithTitleAndBody() {
        driver.get(System.getProperty("dtcc.home.url", "https://www.dtcc.com/"));
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("dtcc"), "Title should contain DTCC.");
        Assert.assertTrue(driver.getPageSource().length() > 1000, "Page source should contain meaningful content.");
    }
}
