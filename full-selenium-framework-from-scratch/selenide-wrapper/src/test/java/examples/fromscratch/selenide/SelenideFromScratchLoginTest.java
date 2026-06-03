package examples.fromscratch.selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

/**
 * From-scratch Selenide test showing Playwright-style auto-waiting on top of Selenium.
 */
@Epic("Full Selenium Framework From Scratch")
@Feature("Selenide Wrapper Layer")
public class SelenideFromScratchLoginTest extends SelenideFromScratchBaseTest {

    @Test(description = "Valid login shows catalogue panel using Selenide auto-waiting")
    @Description("Validates that a Selenide Page Object can log in to the local mock application and assert the catalogue panel without explicit WebDriverWait code.")
    public void validLoginShowsCataloguePanel() {
        openApplication();
        loginAsDemoUser();
        verifyCatalogueLoaded();
    }

    @Step("Open local mock order application")
    private void openApplication() {
        openLocalMockApp();
    }

    @Step("Login as demo user")
    private void loginAsDemoUser() {
        new SelenideFromScratchLoginPage().login("demo_user", "demo_password");
    }

    @Step("Verify catalogue panel is visible")
    private void verifyCatalogueLoaded() {
        new SelenideFromScratchLoginPage().shouldShowCatalog();
    }
}
