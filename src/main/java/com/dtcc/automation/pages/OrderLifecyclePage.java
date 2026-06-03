package com.dtcc.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderLifecyclePage {
    private final WebDriverWait wait;

    @FindBy(id = "cart-icon") private WebElement cartButton;
    @FindBy(id = "checkout-btn") private WebElement checkoutButton;
    @FindBy(id = "pay-submit-btn") private WebElement paymentButton;
    @FindBy(id = "confirmation-banner") private WebElement confirmationBanner;

    public OrderLifecyclePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addItemToCart(String itemName) {
        By itemButton = By.xpath("//div[contains(@class,'catalog-item') and contains(., '" + itemName + "')]//button[contains(@class,'add-to-cart')]");
        wait.until(ExpectedConditions.elementToBeClickable(itemButton)).click();
    }

    public void convertCartToOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void submitPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentButton)).click();
    }

    public String confirmationText() {
        return wait.until(ExpectedConditions.visibilityOf(confirmationBanner)).getText();
    }
}
