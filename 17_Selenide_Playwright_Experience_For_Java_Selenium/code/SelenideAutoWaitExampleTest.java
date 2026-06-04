package examples.selenide;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideAutoWaitExampleTest {
    @BeforeClass
    public void configure() {
        Configuration.browser = "chrome";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        Configuration.timeout = 8000;
    }

    @Test
    public void dtccHomePageLoadsWithAutoWaiting() {
        open("https://www.dtcc.com/");
        $("body").shouldBe(visible);
    }
}
