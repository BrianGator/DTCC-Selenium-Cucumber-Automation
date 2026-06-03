package examples.fromscratch.selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object Model using Selenide.
 * Notice there is no WebDriver, WebElement, WebDriverWait, or ExpectedConditions code here.
 */
public class SelenideFromScratchLoginPage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#loginBtn");
    private final SelenideElement catalogPanel = $("#catalog-panel");
    private final SelenideElement loginError = $("#login-error");

    public void login(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
    }

    public void shouldShowCatalog() {
        catalogPanel.shouldBe(visible).shouldHave(text("Catalogue"));
    }

    public void shouldShowLoginError() {
        loginError.shouldBe(visible).shouldHave(text("Invalid"));
    }
}
