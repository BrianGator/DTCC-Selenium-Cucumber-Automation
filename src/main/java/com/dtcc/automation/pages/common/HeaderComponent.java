package com.dtcc.automation.pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent {
    @FindBy(id = "cart-icon") private WebElement cartIcon;
    public void openCart() { cartIcon.click(); }
}
