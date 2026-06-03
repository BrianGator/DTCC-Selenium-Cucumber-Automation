package com.dtcc.automation.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;

/**
 * Selenide version of the login page.
 * This intentionally avoids raw WebDriver, WebElement, WebDriverWait, and ExpectedConditions.
 * Selenide automatically waits for elements before setValue(), click(), and should*() assertions.
 */
public class SelenideLoginPage {
    private final SelenideElement txtUsername = $("#username");
    private final SelenideElement txtPassword = $("#password");
    private final SelenideElement btnLogin = $("#loginBtn");
    private final SelenideElement lblDashboard = $("#catalog-panel");
    private final SelenideElement lblError = $("#login-error");

    public void login(String user, String pass) {
        txtUsername.setValue(user);
        txtPassword.setValue(pass);
        btnLogin.click();
    }

    public void verifyDashboardIsVisible() {
        lblDashboard.shouldBe(visible).shouldHave(text("Catalogue"));
    }

    public void verifyLoginErrorIsVisible() {
        lblError.shouldBe(visible).shouldHave(text("Invalid"));
    }
}
