package com.dtcc.automation.negative;

import com.dtcc.automation.base.UiTestBase;
import com.dtcc.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginValidationTest extends UiTestBase {

    @Test
    public void invalidPasswordShouldDisplayValidationMessage() {
        driver.get(mockAppUrl());
        new LoginPage(driver).login("sdet_user", "bad-password");
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("invalid")
                        || driver.getPageSource().toLowerCase().contains("error"),
                "Invalid login should show an error or validation message.");
    }
}
