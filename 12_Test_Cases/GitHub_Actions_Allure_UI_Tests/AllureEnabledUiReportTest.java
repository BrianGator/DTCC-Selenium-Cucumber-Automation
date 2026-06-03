package com.dtcc.automation.reports;

import com.dtcc.automation.base.UiTestBase;
import com.dtcc.automation.pages.LoginPage;
import com.dtcc.automation.pages.OrderLifecyclePage;
import com.dtcc.automation.utils.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Epic("DTCC SDET Automation Framework")
@Feature("Allure Report UI Demonstration")
@Owner("Brian McCarthy")
public class AllureEnabledUiReportTest extends UiTestBase {

    private WebDriver driver;

    @Test(description = "Allure-enabled order flow should complete successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validates login, catalogue selection, cart conversion, payment submission, and confirmation using Allure steps and attachments.")
    public void orderLifecycleProducesDetailedAllureReport() {
        driver = openMockOrderApplication();
        attachCheckpoint("Opened local mock order lifecycle application.");

        loginAsValidUser("sdet_dtcc_user", "SecureP@ss1");
        addHighValueEquityItem("HighVolumeEquity_X1");
        convertCartAndSubmitPayment();

        String confirmation = readConfirmationMessage();
        AllureAttachmentUtil.attachLog("Confirmation banner text: " + confirmation);
        AllureAttachmentUtil.attachScreenshot(driver);
        Assert.assertTrue(confirmation.contains("SUCCESS"), "Expected successful order confirmation.");
    }

    @Test(description = "Intentional Allure failure example for defect management")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test intentionally fails only when includeIntentionalFailures=true so the Allure report shows a realistic failed defect example.")
    public void intentionalFailureCreatesAllureDefectExample() {
        if (!Boolean.getBoolean("includeIntentionalFailures")) {
            throw new org.testng.SkipException("Intentional failure demo skipped. Run with -DincludeIntentionalFailures=true.");
        }
        driver = openMockOrderApplication();
        AllureAttachmentUtil.attachScreenshot(driver);
        AllureAttachmentUtil.attachLog("Intentional failure: expected status PROCESSED but received PENDING_REVIEW.");
        Assert.fail("Intentional Allure failure example: order status was PENDING_REVIEW instead of PROCESSED.");
    }

    @Step("Record checkpoint: {message}")
    private void attachCheckpoint(String message) {
        AllureAttachmentUtil.attachLog(message);
    }

    @Step("Login as valid user: {username}")
    private void loginAsValidUser(String username, String password) {
        new LoginPage(driver).login(username, password);
    }

    @Step("Add item from catalogue: {itemName}")
    private void addHighValueEquityItem(String itemName) {
        new OrderLifecyclePage(driver).addItemToCart(itemName);
    }

    @Step("Convert cart to order and submit payment")
    private void convertCartAndSubmitPayment() {
        OrderLifecyclePage orderPage = new OrderLifecyclePage(driver);
        orderPage.convertCartToOrder();
        orderPage.submitPayment();
    }

    @Step("Read order confirmation banner")
    private String readConfirmationMessage() {
        return new OrderLifecyclePage(driver).confirmationText();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            AllureAttachmentUtil.attachPageSource(driver);
        }
        WebDriverFactory.quitDriver();
    }
}
