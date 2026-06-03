package com.dtcc.automation.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

/**
 * Selenide order lifecycle page object.
 * Selenide auto-waiting makes catalogue, cart, checkout, and payment tests much cleaner.
 */
public class SelenideOrderLifecyclePage {
    private final ElementsCollection catalogItems = $$(".catalog-item");
    private final SelenideElement btnCart = $("#cart-icon");
    private final SelenideElement btnCheckout = $("#checkout-btn");
    private final SelenideElement btnSubmitPayment = $("#pay-submit-btn");
    private final SelenideElement lblConfirmation = $(".confirmation-banner");

    public void addItemToCart(String itemName) {
        catalogItems.findBy(text(itemName)).$("button").click();
    }

    public void convertCartToOrder() {
        btnCart.click();
        btnCheckout.click();
    }

    public void processPayment() {
        btnSubmitPayment.click();
    }

    public void verifyOrderWasProcessed() {
        lblConfirmation.shouldBe(visible).shouldHave(text("SUCCESS"));
    }
}
