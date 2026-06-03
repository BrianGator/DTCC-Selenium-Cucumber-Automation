package com.dtcc.automation.selenide;

import com.dtcc.automation.selenide.pages.SelenideLoginPage;
import com.dtcc.automation.selenide.pages.SelenideOrderLifecyclePage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * Selenide rewrite of the end-to-end order lifecycle flow.
 */
public class SelenideOrderLifecycleTest extends SelenideTestBase {

    @Test(groups = {"selenide", "ui", "regression"})
    public void userCanLoginSelectCatalogueItemCheckoutAndPay() {
        open(mockAppUrl);

        SelenideLoginPage loginPage = new SelenideLoginPage();
        loginPage.login("sdet_dtcc_user", "SecureP@ss1");
        loginPage.verifyDashboardIsVisible();

        SelenideOrderLifecyclePage orderPage = new SelenideOrderLifecyclePage();
        orderPage.addItemToCart("HighVolumeEquity_X1");
        orderPage.convertCartToOrder();
        orderPage.processPayment();
        orderPage.verifyOrderWasProcessed();
    }
}
