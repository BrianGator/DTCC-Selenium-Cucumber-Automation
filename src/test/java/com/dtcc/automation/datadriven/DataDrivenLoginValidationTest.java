package com.dtcc.automation.datadriven;

import com.dtcc.automation.base.UiTestBase;
import com.dtcc.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenLoginValidationTest extends UiTestBase {

    @DataProvider(name = "loginMatrix")
    public Object[][] loginMatrix() {
        return new Object[][]{
                {"sdet_user", "SecureP@ss1", true},
                {"locked_user", "SecureP@ss1", false},
                {"sdet_user", "wrong-password", false}
        };
    }

    @Test(dataProvider = "loginMatrix")
    public void loginValidationShouldHandlePositiveAndNegativeData(String username, String password, boolean expectedSuccess) {
        driver.get(mockAppUrl());
        LoginPage page = new LoginPage(driver);
        page.login(username, password);

        boolean actualSuccess = driver.getPageSource().contains("Catalogue") || driver.getPageSource().contains("SUCCESS");
        Assert.assertEquals(actualSuccess, expectedSuccess, "Login outcome did not match expected test data.");
    }
}
