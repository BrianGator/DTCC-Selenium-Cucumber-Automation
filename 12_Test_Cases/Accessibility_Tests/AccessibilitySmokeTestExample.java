package com.dtcc.automation.accessibility;

import com.dtcc.automation.base.UiTestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccessibilitySmokeTest extends UiTestBase {

    @Test
    public void loginPageShouldExposeBasicAccessibilityAttributes() {
        driver.get(mockAppUrl());

        Assert.assertTrue(driver.findElement(By.id("username")).getAttribute("aria-label") != null
                        || driver.findElement(By.cssSelector("label[for='username']")).isDisplayed(),
                "Username field should have a visible label or aria-label.");

        Assert.assertTrue(driver.findElement(By.id("password")).getAttribute("aria-label") != null
                        || driver.findElement(By.cssSelector("label[for='password']")).isDisplayed(),
                "Password field should have a visible label or aria-label.");
    }
}
