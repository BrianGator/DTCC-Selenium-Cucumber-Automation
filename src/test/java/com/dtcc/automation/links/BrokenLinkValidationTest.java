package com.dtcc.automation.links;

import com.dtcc.automation.pages.publicsite.DtccHomePage;
import com.dtcc.automation.base.UiTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinkValidationTest extends UiTestBase {

    @Test
    public void homePageTopLinksShouldNotReturnBrokenStatusCodes() throws Exception {
        DtccHomePage home = new DtccHomePage(driver);
        home.openHomePage();

        List<WebElement> links = driver.findElements(By.cssSelector("a[href^='https://www.dtcc.com'], a[href^='/']"));
        List<String> broken = new ArrayList<>();
        int maxLinks = Integer.parseInt(System.getProperty("dtcc.link.max", "10"));

        for (int i = 0; i < Math.min(maxLinks, links.size()); i++) {
            String href = links.get(i).getAttribute("href");
            if (href == null || href.isBlank() || href.startsWith("mailto:")) continue;
            HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            int status = connection.getResponseCode();
            if (status >= 400) broken.add(href + " -> " + status);
        }

        Assert.assertTrue(broken.isEmpty(), "Broken public links detected: " + broken);
    }
}
