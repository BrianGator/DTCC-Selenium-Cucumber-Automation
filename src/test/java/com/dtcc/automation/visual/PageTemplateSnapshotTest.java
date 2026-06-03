package com.dtcc.automation.visual;

import com.dtcc.automation.base.UiTestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PageTemplateSnapshotTest extends UiTestBase {

    @Test
    public void captureHomePageSnapshotForVisualReview() throws Exception {
        driver.get(System.getProperty("dtcc.home.url", "https://www.dtcc.com/"));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File target = new File("target/visual-snapshots/dtcc-home-page-smoke.png");
        FileUtils.copyFile(screenshot, target);
        Assert.assertTrue(target.exists(), "Visual snapshot should be saved for review.");
    }
}
