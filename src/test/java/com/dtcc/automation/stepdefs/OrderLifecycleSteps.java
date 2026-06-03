package com.dtcc.automation.stepdefs;

import com.dtcc.automation.base.UiTestBase;
import com.dtcc.automation.pages.LoginPage;
import com.dtcc.automation.pages.OrderLifecyclePage;
import com.dtcc.automation.utils.EncryptionManager;
import com.dtcc.automation.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OrderLifecycleSteps extends UiTestBase {
    private LoginPage loginPage;
    private OrderLifecyclePage orderPage;

    @Before
    public void setup() {
        WebDriver driver = openMockOrderApplication();
        loginPage = new LoginPage(driver);
        orderPage = new OrderLifecyclePage(driver);
    }

    @Given("User is on the login screen")
    public void userIsOnLoginScreen() {
        Assert.assertTrue(WebDriverFactory.getDriver().getTitle().contains("Mock DTCC Order Lifecycle"));
    }

    @When("User authenticates with valid credentials {string} and encrypted password {string}")
    public void userAuthenticates(String username, String encryptedPassword) {
        loginPage.login(username, EncryptionManager.decryptString(encryptedPassword));
    }

    @When("User authenticates with valid credentials {string} and {string}")
    public void userAuthenticatesPlain(String username, String password) {
        loginPage.login(username, password);
    }

    @And("User adds a specific item {string} from the catalogue to the shopping cart")
    public void userAddsItem(String item) {
        orderPage.addItemToCart(item);
    }

    @And("User converts the cart contents into a finalized order")
    public void userConvertsCart() {
        orderPage.convertCartToOrder();
    }

    @And("User submits a valid payment processing profile")
    public void userSubmitsPayment() {
        orderPage.submitPayment();
    }

    @Then("The order confirmation payload should display a successful transition status")
    public void orderConfirmationShouldBeSuccessful() {
        Assert.assertTrue(orderPage.confirmationText().contains("SUCCESS"), "Expected SUCCESS confirmation banner.");
    }

    @After
    public void teardown() {
        WebDriverFactory.quitDriver();
    }
}
