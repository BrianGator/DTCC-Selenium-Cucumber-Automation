package com.dtcc.automation.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

/**
 * Interview code sample: Account/security lookup page with Playwright-style Selenide syntax.
 */
public class AccountDashboardPage {
    private final SelenideElement txtSearch = $("#security-search-input");
    private final SelenideElement btnSubmit = $("#search-submit-button");
    private final SelenideElement lblStatus = $(".settlement-status-badge");

    public void processSecurityLookup(String securityId) {
        txtSearch.setValue(securityId);
        btnSubmit.click();
    }

    public void verifySettlementProcessed() {
        lblStatus.shouldHave(text("PROCESSED"));
    }
}
