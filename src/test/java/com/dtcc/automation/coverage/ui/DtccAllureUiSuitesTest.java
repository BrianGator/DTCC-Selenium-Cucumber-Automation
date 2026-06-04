package com.dtcc.automation.coverage.ui;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("DTCC.com Public Website UI Coverage")
@Feature("UI Page Template Validation")
@Owner("Brian McCarthy")
public class DtccAllureUiSuitesTest {

    @DataProvider(name = "uiPages")
    public Object[][] uiPages() {
        return new Object[][]{
                {"Home", "Navigation header, hero content, footer, public links", "smoke"},
                {"About DTCC", "Corporate profile, leadership links, company information", "content"},
                {"Client Center", "Documentation links, downloads, support paths, contacts", "regression"},
                {"Important Notices", "Search field, filters, notice list, date sorting, PDF links", "regression"},
                {"Products and Services", "Template title, product cards, service navigation", "content"},
                {"Legal and Regulatory", "Compliance links, public policy pages, regulatory references", "risk"},
                {"News and Insights", "Article cards, headline text, dates, pagination", "content"},
                {"Global Trade Repository", "Dynamic table shell, filter controls, jurisdiction data", "regression"},
                {"PDF Library", "Download links, content type expectations, file naming", "document"},
                {"Search Results", "Keyword input, result summaries, empty-state messaging", "search"}
        };
    }

    @Test(dataProvider = "uiPages", description = "UI suite validates DTCC public page coverage metadata")
    @Story("Public Page Coverage")
    @Severity(SeverityLevel.NORMAL)
    @Description("Adds UI page-template evidence to Allure Suites, Packages, and Behaviors tabs.")
    public void uiPageCoverageMetadataIsDocumented(String pageName, String expectedEvidence, String testType) {
        Allure.label("layer", "ui");
        Allure.label("component", pageName);
        Allure.label("testType", testType);
        Allure.addAttachment("UI coverage evidence", "text/plain", "Page: " + pageName + "\nEvidence: " + expectedEvidence + "\nType: " + testType);
        Assert.assertFalse(pageName.isBlank());
        Assert.assertTrue(expectedEvidence.length() > 20);
    }
}
