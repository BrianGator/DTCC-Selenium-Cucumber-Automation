package com.dtcc.automation.ui;

import com.dtcc.automation.base.UiTestBase;
import com.dtcc.automation.pages.LoginPage;
import com.dtcc.automation.pages.OrderLifecyclePage;
import com.dtcc.automation.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SeleniumOrderLifecycleTest extends UiTestBase {
    @Test(description = "Selenium validates login, catalogue selection, cart, checkout, payment, and confirmation")
    public void userCanCompleteFullOrderLifecycleWithSelenium() {
        WebDriver driver = openMockOrderApplication();
        new LoginPage(driver).login("sdet_dtcc_user", "SecureP@ss1");
        OrderLifecyclePage orderPage = new OrderLifecyclePage(driver);
        orderPage.addItemToCart("HighVolumeEquity_X1");
        orderPage.convertCartToOrder();
        orderPage.processPayment();
        Assert.assertTrue(orderPage.getConfirmationMessage().contains("SUCCESS"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }
}
