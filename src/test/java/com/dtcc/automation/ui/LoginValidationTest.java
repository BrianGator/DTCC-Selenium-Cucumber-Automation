package com.dtcc.automation.ui;

import com.dtcc.automation.base.UiTestBase;
import com.dtcc.automation.pages.LoginPage;
import com.dtcc.automation.pages.OrderLifecyclePage;
import com.dtcc.automation.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginValidationTest extends UiTestBase {
    @Test(description = "Valid login redirects user to catalogue/order lifecycle area")
    public void validUserCanLoginToMockOrderApplication() {
        WebDriver driver = openMockOrderApplication();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("sdet_dtcc_user", "SecureP@ss1");
        OrderLifecyclePage orderPage = new OrderLifecyclePage(driver);
        Assert.assertTrue(orderPage.isCatalogueDisplayed(), "Catalogue should be displayed after login.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }
}
