package examples.fromscratch.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Minimal Selenide base test.
 * Selenide manages the underlying Selenium WebDriver lifecycle and waits.
 */
public abstract class SelenideFromScratchBaseTest {
    @BeforeMethod(alwaysRun = true)
    public void configureBrowser() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        Configuration.timeout = Long.parseLong(System.getProperty("selenide.timeout", "8000"));
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
    }

    protected void openLocalMockApp() {
        String appUrl = System.getProperty(
                "mock.app.url",
                "file://" + System.getProperty("user.dir") + "/src/test/resources/mock-web-app/order-app.html"
        );
        Selenide.open(appUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
