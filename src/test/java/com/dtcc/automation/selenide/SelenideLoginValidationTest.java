package com.dtcc.automation.selenide;

import com.dtcc.automation.selenide.pages.SelenideLoginPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * Selenide rewrite of login validation using built-in auto-waiting assertions.
 */
public class SelenideLoginValidationTest extends SelenideTestBase {

    @Test(groups = {"selenide", "login", "smoke"})
    public void validLoginDisplaysDashboard() {
        open(mockAppUrl);
        SelenideLoginPage loginPage = new SelenideLoginPage();
        loginPage.login("sdet_dtcc_user", "SecureP@ss1");
        loginPage.verifyDashboardIsVisible();
    }

    @Test(groups = {"selenide", "login", "negative"})
    public void invalidLoginDisplaysValidationMessage() {
        open(mockAppUrl);
        SelenideLoginPage loginPage = new SelenideLoginPage();
        loginPage.login("bad_user", "bad_password");
        loginPage.verifyLoginErrorIsVisible();
    }
}
