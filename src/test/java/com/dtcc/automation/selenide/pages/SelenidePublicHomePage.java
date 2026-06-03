package com.dtcc.automation.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;

/**
 * Public DTCC.com home page object using Selenide auto-waiting.
 */
public class SelenidePublicHomePage {
    private final SelenideElement body = $("body");
    private final SelenideElement header = $("header, .header, #header");
    private final SelenideElement searchTrigger = $("a[href*='search'], button[aria-label*='Search'], .search, #site-search");

    public void openHomePage() {
        open("https://www.dtcc.com/");
    }

    public void verifyCoreTemplateLoaded() {
        body.shouldBe(visible).shouldHave(text("DTCC"));
        header.shouldBe(visible);
    }

    public void verifySearchOrNavigationAvailable() {
        searchTrigger.shouldBe(visible);
    }
}
